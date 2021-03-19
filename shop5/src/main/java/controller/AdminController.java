package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.LoginException;
import logic.Mail;
import logic.ShopService;
import logic.User;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private ShopService service;
	
	@RequestMapping("list")
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<User> userlist = service.getUserList();
		for(int i=0;i<userlist.size();i++) {
			String email = null;
			try {
			email = aes.CipherUtil.decrypte(userlist.get(i).getEmail(), aes.CipherUtil.makehash(userlist.get(i).getUserid()).substring(0,16));
			}catch (Exception e) {
				 e.printStackTrace();
			}
			userlist.get(i).setEmail(email);
		}
		mav.addObject("list",userlist);
		return mav;
	}
	@RequestMapping("mailForm")
	public ModelAndView mailform (String[] idchks, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView("admin/mail");
		if(idchks == null || idchks.length==0) {
			throw new LoginException("메일을 보낼 대상자를 선택하세요","list.shop");
		}
		User login =(User)session.getAttribute("loginUser");
		List<User> list = service.userList(idchks);
		String decrylogin=null;
		decrylogin=aes.CipherUtil.decrypte(login.getEmail(), aes.CipherUtil.makehash(login.getUserid()).substring(0,16));
		login.setEmail(decrylogin);
		for(int i=0;i<list.size();i++) {
			String email = null;
			try {
			email = aes.CipherUtil.decrypte(list.get(i).getEmail(), aes.CipherUtil.makehash(list.get(i).getUserid()).substring(0,16));
			}catch (Exception e) {
				 e.printStackTrace();
			}
			list.get(i).setEmail(email);
		}
		mav.addObject("list",list);
		return mav;
	}
	@RequestMapping("mail")
	public ModelAndView mail(Mail mail,HttpSession session) {
		ModelAndView mav = new ModelAndView("alert");
		mailSend(mail);
		mav.addObject("message","메일 전송이 완료 되었습니다.");
		mav.addObject("url","list.shop");
		return mav;
	}
	private final class MyAuthenticator extends Authenticator{
		private String id;
		private String pw;
		public MyAuthenticator(String id,String pw) {
			this.id = id;
			this.pw = pw;
		}
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pw);
		}
	}
	private void mailSend(Mail mail) {
	MyAuthenticator auth = new MyAuthenticator(mail.getNaverid(),mail.getNaverpw());
	Properties prop = new Properties();
	try {
		FileInputStream fis = new FileInputStream("C:/Users/sa365/Desktop/springworkspace/shop3/src/main/resources/mail.properties");
		prop.load(fis);
		prop.put("mail.smtp.user", mail.getNaverid());
	}catch (IOException e) {
		e.printStackTrace();
	}
	Session session = Session.getInstance(prop,auth);
	MimeMessage mimeMsg = new MimeMessage(session);
	try {mimeMsg.setFrom(new InternetAddress(mail.getNaverid()+"@naver.com"));
		List<InternetAddress> addrs = new ArrayList<InternetAddress>();
		String[] emails = mail.getRecipient().split(",");
		for(String email : emails) {
			try {
				addrs.add(new InternetAddress
						(new String(email.getBytes("utf-8"),"8859_1")));
			}catch(UnsupportedEncodingException ue) {
				ue.printStackTrace();
			}
		}
		InternetAddress[] arr = new InternetAddress[emails.length];
		for(int i=0;i<addrs.size();i++) {
			arr[i] = addrs.get(i);
		}
		mimeMsg.setSentDate(new Date());
		mimeMsg.setRecipients(Message.RecipientType.TO,arr);
		mimeMsg.setSubject(mail.getTitle());
		MimeMultipart multipart = new MimeMultipart();
		MimeBodyPart message = new MimeBodyPart();
		message.setContent(mail.getContents(),mail.getMtype());
		multipart.addBodyPart(message);
		for(MultipartFile mf : mail.getFile1()) {
			if((mf != null) && (!mf.isEmpty())) {
				multipart.addBodyPart(bodyPart(mf));
			}
		}
		mimeMsg.setContent(multipart);
		Transport.send(mimeMsg);
	} catch(MessagingException me) {
		me.printStackTrace();
	}		
}
	private BodyPart bodyPart(MultipartFile mf) {
		 MimeBodyPart body = new MimeBodyPart();
		 String orgFile = mf.getOriginalFilename();
		 String path = "c:/mailupload/";
		 File f = new File(path);
		 if(!f.exists()) f.mkdirs();
		 File f1 = new File(path + orgFile);
		 try {
			 mf.transferTo(f1);
			 body.attachFile(f1);
			 body.setFileName
			 (new String(orgFile.getBytes("UTF-8"),"8859_1"));
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		return body;
	}
}
