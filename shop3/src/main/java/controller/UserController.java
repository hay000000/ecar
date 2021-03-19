package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import exception.LoginException;
import logic.Item;
import logic.Sale;
import logic.SaleItem;
import logic.ShopService;
import logic.User;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private ShopService service;

	
	@GetMapping("*")
//	public ModelAndView entry() {
//		ModelAndView mav = new ModelAndView();
//		User user= new User();
//		mav.addObject(user);
//		return mav;
//	}
	public String user(Model model) {
		model.addAttribute(new User());
		return null;
	}
	
	@RequestMapping({"main","password"}) //login�Ǿ� ���ϴ� �޼����̸��� loginCheckxxx�� ����
	public String loginCheckmain(HttpSession session) {
		return null;
	}
	
	@PostMapping("userEntry")
	   public ModelAndView userEntry(@Valid User user, BindingResult bindResult) {
	      ModelAndView mav = new ModelAndView();
	      if (bindResult.hasErrors()) {
	         mav.getModel().putAll(bindResult.getModel());
	         bindResult.reject("error.input.user"); // �۷ι� ����
	         return mav;
	      }
	      try {
	         service.userCreate(user);
	         mav.addObject("user", user);
	      } catch (DataIntegrityViolationException e) {
	         e.printStackTrace();
	         bindResult.reject("error.duplicate.user"); // �۷ι� ����
	         mav.getModel().putAll(bindResult.getModel());
	         return mav;
	      }
	      mav.setViewName("redirect:login.shop"); //redict : ��� ����, url ����
	      return mav; 
	   }

	
	@PostMapping("login")
	public ModelAndView login(@Valid User user,BindingResult bresult,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			bresult.reject("error.input.user");
			return mav;
		}
		try {
			User dbuser = service.getUser(user.getUserid());
			//user.getPassword() : �Էº�й�ȣ
			//dbuser.getPassword() : ��ϵ� ��й�ȣ
			
			if(user.getPassword().equals(dbuser.getPassword())) {//����α���
				session.setAttribute("loginUser", dbuser);
				mav.setViewName("redirect:main.shop");
			} else { //��й�ȣ ����
				bresult.reject("error.login.password");
				mav.getModel().putAll(bresult.getModel());
			}
		} catch(EmptyResultDataAccessException e) { //�ش���̵����
			bresult.reject("error.login.id");
			mav.getModel().putAll(bresult.getModel());
		}
		return mav;
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); // loginUser �Ӽ�, CART �Ӽ� ����. ���ο� session ��ü����
		return "redirect:login.shop";
	}
	/*
	 * AOP �����ϱ�
	 * 1.UserController�� idcheck�� �����ϴ� �޼��� +
	 * 		�Ű������� id,session �� ��츦 pointcut���� ����
	 * 2.�α��� �ȵȰ�� : �α����ϼ��� �޼��� ��� => login.shop ������ �̵�
	 * 	 admin�� �ƴϰ�, �ٸ� ���̵� ���� ��ȸ�� : 
	 * 						���θ� ��ȸ�����մϴ�. main.shop������ �̵�
	 * 
	 */
	
	
	@RequestMapping("mypage")
	public ModelAndView idcheckmypage(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = service.getUser(id);
		List<Sale> salelist = service.salelist(id);
		for(Sale sa : salelist) {
			List<SaleItem> saleitemlist = service.saleItemList(sa.getSaleid());
			for(SaleItem si : saleitemlist) {
				//�ֹ���ǰid�� �ش��ϴ� Item ��ü�� ����
				Item item = service.getItem(Integer.parseInt(si.getItemid()));
				si.setItem(item);
			}
			sa.setItemList(saleitemlist); //�Ѱ��� �ֹ��� �ش��ϴ� �ֹ���ǰ ����߰�
		}
		mav.addObject("user",user); //ȸ������
		mav.addObject("salelist",salelist); //ȸ���� �ֹ�����
		return mav;
	}
	
	@GetMapping({"update","delete"})
	public ModelAndView idcheckupdate(String id,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = service.getUser(id);
		mav.addObject("user",user);
		return mav;
	}
	
	@PostMapping("update")
	public ModelAndView update(@Valid User user,BindingResult bindResult,HttpSession session) {
		ModelAndView mav = new ModelAndView();
	      if (bindResult.hasErrors()) {
	         mav.getModel().putAll(bindResult.getModel());
	         bindResult.reject("error.input.user"); // �۷ι� ����
	         return mav;
	      }
	      try {
	    	  User login =(User)session.getAttribute("loginUser");
	    	  if(user.getPassword().equals(login.getPassword())) {
	    		  service.userUpdate(user);
	    		  mav.setViewName("redirect:/user/mypage.shop?id="+user.getUserid());
	    		  if(user.getUserid().equals(login.getUserid())) {
	    			  session.setAttribute("loginUser", user);
	    		  }
	    	  }else { //��й�ȣ ����
	    		  bindResult.reject("error.login.password");
					mav.getModel().putAll(bindResult.getModel());
				}
			} catch(EmptyResultDataAccessException e) { //�ش���̵����
				bindResult.reject("error.login.id");
				mav.getModel().putAll(bindResult.getModel());
			}
	      //��й�ȣ ����. ��й�ȣ�� ��ġ�ϴ� ��� useraccount ���̺� ����
	      //�α����� ������� ��й�ȣ��, �Էµ� ��й�ȣ�� ��ġ ����
	      //��й�ȣ�� ��ġ�ϸ� user ������ db����.
	      //��ġ���� ������ : error.login.password �ڵ带 �Է��Ͽ� update.jsp ��������
	      // 				�۷ι� ���� �޽��� ���.
	 return mav;
	}
	@PostMapping("delete")
	  public ModelAndView idcheckdelete(String userid, String password, HttpSession session) {
	      ModelAndView mav = new ModelAndView();
	      User login = (User) session.getAttribute("loginUser");
	      // user.getPassword() : �Է��� ��й�ȣ
	      // dbuser.getPassword() : ��ϵ� ��й�ȣ
	      if (userid.equals("admin")) {
	         throw new LoginException("������ Ż��� �Ұ��մϴ�.", "main.shop");
	      }
	      if (!password.equals(login.getPassword())) {
	         throw new LoginException("Ż�� �� ��й�ȣ�� Ʋ���ϴ�.", "delete.shop?id=" + userid);
	      }
	      try {
	         service.delete(userid);
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new LoginException("Ż��� ������ �߻��߽��ϴ�.", "delete.shop?id=" + userid);
	      } // Ż�� ����
	      if (login.getUserid().equals("admin")) {
	         mav.setViewName("redirect:/admin/list.shop");
	      } else {
//	             mav.setViewName("redirect:logout.shop");
	         session.invalidate();
	         throw new LoginException(userid + "ȸ������ Ż��ó���� �Ǿ����ϴ�.", "login.shop");
	      }
//	           if(login.getPassword().equals(user.getPassword())) { //��й�ȣ�� ������
//	              shopService.UserDelete(user.getUserid()); 
//	              mav.setViewName("redirect:/user/login.shop");
//	           } else { //��й�ȣ�� Ʋ�� ���
//	              mav.setViewName("redirect:/user/delete.shop?id="+user.getUserid());
//	           }
	      return mav;
	   }
	//{url}search : {url} : ������������. *search�� ��û url�� ��� ȣ��Ǵ� �޼���
	//@PathVariable : {url}�� �ش�Ǵ� ���ڿ� �Ű����� ����.
	@PostMapping("{url}search")
	public ModelAndView search(User user,BindingResult bresult,@PathVariable String url) {
		ModelAndView mav = new ModelAndView();
		String code = "error.userid.search"; // messages.properties �����ؾ���.
		String title = "���̵�";
		if(url.equals("pw")) {
			code = "error.password.search";
			title = "��й�ȣ";
			if(user.getUserid()==null || user.getUserid().equals("")) {
				bresult.rejectValue("userid","error.required");
			}
		}
		if(user.getEmail()==null || user.getEmail().equals("")) {
			bresult.rejectValue("email", "error.required");
		}
		if(user.getPhoneno()==null || user.getPhoneno().equals("")) {
			bresult.rejectValue("phoneno", "error.required");
		}
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		if(user.getUserid() != null && user.getUserid().equals(""))
			user.setUserid(null);
		String result = null;
		try {
			result = service.getSearch(user);
		} catch (EmptyResultDataAccessException e) {
			bresult.reject(code);
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		mav.addObject("result",result);
		mav.addObject("title",title);
		mav.setViewName("search");
		return mav;
	}
	
	//@RequestMapping�� PostMapping�� ���� ������ ��� Post��� ��û�� ��� �� �޼��� ȣ��
	//@RequestParam : ��û �Ķ���� ���� �����ϱ� ���� ��ü ����
	//			��û �Ķ���� : 1.�Ķ���� �̸��� �Ű����� �̸��� ���� ���
	//						2.Bean Ŭ������ ������Ƽ�� �Ķ���Ͱ� ���� ��� BeanŬ������ ��ü�� ����
	@PostMapping("password")
	public ModelAndView loginCheckpassword(@RequestParam Map<String, String> param,HttpSession session	) {
		ModelAndView mav = new ModelAndView();
		System.out.println(param);
		User loginUser=(User)session.getAttribute("loginUser");
		
		if(param.get("password").equals(loginUser.getPassword())) {
			try {
				service.userPasswordUpdate(loginUser.getUserid(),param.get("chgpass"));
				loginUser.setPassword(param.get("chgpass"));
				mav.addObject("message",loginUser.getUsername()+"�� ��й�ȣ�� ����Ǿ����ϴ�.");
				mav.addObject("url","main.shop");
				mav.setViewName("alert");
			}catch(Exception e) {
				throw new LoginException
							("��й�ȣ ����� ������ �ֽ��ϴ�.","password.shop");
			}
		}else {
			throw new LoginException("���� ��й�ȣ�� Ʋ���ϴ�.", "password.shop");
		}
		return mav;
	}
	
}
