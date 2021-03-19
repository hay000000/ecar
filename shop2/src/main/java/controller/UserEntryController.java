package controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.ShopService;
import logic.User;
import util.UserValidator;

public class UserEntryController {
	private ShopService shopService;
	private UserValidator userValidator;
	
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	public void setUserValidator(UserValidator userValildator) {
		this.userValidator = userValildator;
	}
	@GetMapping //Get방식 요청시 호출되는 메서드
	public ModelAndView userEntryForm() {
		ModelAndView mav = new ModelAndView();
		User u = new User();
		mav.addObject(u);
		return mav;
	}
	@PostMapping //Post방식 요청시 호출되는 메서드
	public ModelAndView userEntry(User user,BindingResult bindResult) {
		ModelAndView mav = new ModelAndView();
		userValidator.validate(user,bindResult);
		if(bindResult.hasErrors()) { //입력값 오류 발생?
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		try {
			shopService.insertUser(user);
			mav.addObject("user",user);
			//중복키 오류 발생. 스프링JDBC 프레임워크에서 발생.
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		mav.setViewName("userEntrySuccess");
		return mav;
	}
	@InitBinder	//파라미터값을 프로퍼티의 자료형이 맞도록 형변환
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//Date.class : 프로퍼티의 자료형 지정.
		//CustomDateEditor : 형식 지정.
		//false : 빈칸 불가능.
		//true : 빈칸 가능.
		binder.registerCustomEditor
		(Date.class, new CustomDateEditor(format, false));
	}
}
