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
			if(dbuser == null) {
				bresult.reject("error.login.id");
				mav.getModel().putAll(bresult.getModel());
				return mav;
			}
			//user.getPassword() : 입력비밀번호
			//dbuser.getPassword() : 등록된 비밀번호
			
			if(user.getPassword().equals(dbuser.getPassword())) {//정상로그인
				session.setAttribute("loginUser", dbuser);
				mav.setViewName("redirect:main.shop");
			} else { //비밀번호 오류
				bresult.reject("error.login.password");
				mav.getModel().putAll(bresult.getModel());
			}
		} catch(Exception e) { //해당아이디없음
			bresult.reject("error.login.id");
			mav.getModel().putAll(bresult.getModel());
		}
		return mav;
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); // loginUser 속성, CART 속성 제거. 새로운 session 객체변경
		return "redirect:login.shop";
	}
	/*
	 * AOP 설정하기
	 * 1.UserController의 idcheck로 시작하는 메서드 +
	 * 		매개변수가 id,session 인 경우를 pointcut으로 설정
	 * 2.로그인 안된경우 : 로그인하세요 메세지 출력 => login.shop 페이지 이동
	 * 	 admin이 아니고, 다른 아이디 정보 조회시 : 
	 * 						본인만 조회가능합니다. main.shop페이지 이동
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
				//주문상품id에 해당하는 Item 객체를 리턴
				Item item = service.getItem(Integer.parseInt(si.getItemid()));
				si.setItem(item);
			}
			sa.setItemList(saleitemlist); //한개의 주문에 해당하는 주문상품 목록추가
		}
		mav.addObject("user",user); //회원정보
		mav.addObject("salelist",salelist); //회원의 주문정보
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
	         bindResult.reject("error.input.user"); // 글로벌 오류
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
	    	  }else { //비밀번호 오류
	    		  bindResult.reject("error.login.password");
					mav.getModel().putAll(bindResult.getModel());
				}
			} catch(EmptyResultDataAccessException e) { //해당아이디없음
				bindResult.reject("error.login.id");
				mav.getModel().putAll(bindResult.getModel());
			}
	      //비밀번호 검증. 비밀번호가 일치하는 경우 useraccount 테이블 수정
	      //로그인한 사용자의 비밀번호와, 입력된 비밀번호가 일치 검증
	      //비밀번호가 일치하면 user 정보로 db수정.
	      //일치하지 않으면 : error.login.password 코드를 입력하여 update.jsp 페이지에
	      // 				글로벌 오류 메시지 출력.
	 return mav;
	}
	@PostMapping("delete")
	  public ModelAndView idcheckdelete(String userid, String password, HttpSession session) {
	      ModelAndView mav = new ModelAndView();
	      User login = (User) session.getAttribute("loginUser");
	      // user.getPassword() : 입력한 비밀번호
	      // dbuser.getPassword() : 등록된 비밀번호
	      if (userid.equals("admin")) {
	         throw new LoginException("관리자 탈퇴는 불가합니다.", "main.shop");
	      }
	      if (!password.equals(login.getPassword())) {
	         throw new LoginException("탈퇴 시 비밀번호가 틀립니다.", "delete.shop?id=" + userid);
	      }
	      try {
	         service.delete(userid);
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new LoginException("탈퇴시 오류가 발생했습니다.", "delete.shop?id=" + userid);
	      } // 탈퇴 이후
	      if (login.getUserid().equals("admin")) {
	         mav.setViewName("redirect:/admin/list.shop");
	      } else {
//	             mav.setViewName("redirect:logout.shop");
	         session.invalidate();
	         throw new LoginException(userid + "회원님의 탈퇴처리가 되었습니다.", "login.shop");
	      }
//	           if(login.getPassword().equals(user.getPassword())) { //비밀번호가 같으면
//	              shopService.UserDelete(user.getUserid()); 
//	              mav.setViewName("redirect:/user/login.shop");
//	           } else { //비밀번호가 틀린 경우
//	              mav.setViewName("redirect:/user/delete.shop?id="+user.getUserid());
//	           }
	      return mav;
	   }
	//{url}search : {url} : 지정되지않음. *search인 요청 url인 경우 호출되는 메서드
	//@PathVariable : {url}에 해당되는 문자열 매개변수 전달.
	//{url}search : {url} : ������������. *search�� ��û url�� ��� ȣ��Ǵ� �޼���
	//@PathVariable : {url}�� �ش�Ǵ� ���ڿ� �Ű����� ����.
	@PostMapping("{url}search")
	public ModelAndView search(User user,BindingResult bresult,@PathVariable String url) {
		ModelAndView mav = new ModelAndView();
		String code = "error.userid.search"; // messages.properties �����ؾ���.
		String title = "아이디";
		if(url.equals("pw")) {
			code = "error.password.search";
			title = "비밀번호";
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
		String result = service.getSearch(user);
		if(result==null) {
			bresult.reject(code);
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		
		mav.addObject("result",result);
		mav.addObject("title",title);
		mav.setViewName("search");
		return mav;
	}
	
	@PostMapping("password")
	public ModelAndView loginCheckpassword(@RequestParam Map<String, String> param,HttpSession session	) {
		ModelAndView mav = new ModelAndView();
		System.out.println(param);
		User loginUser=(User)session.getAttribute("loginUser");
		
		if(param.get("password").equals(loginUser.getPassword())) {
			try {
				service.userPasswordUpdate(loginUser.getUserid(),param.get("chgpass"));
				loginUser.setPassword(param.get("chgpass"));
				mav.addObject("message",loginUser.getUsername()+"님 비밀번호가 변경되었습니다.");
				mav.addObject("url","main.shop");
				mav.setViewName("alert");
			}catch(Exception e) {
				throw new LoginException
							("비밀번호 변경시 오류가 있습니다.","password.shop");
			}
		}else {
			throw new LoginException("현재 비밀번호가 틀립니다.", "password.shop");
		}
		return mav;
	}
	
}
