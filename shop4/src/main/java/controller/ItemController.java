package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;

@Controller		//@Component + Controller 기능
@RequestMapping("item") //item/* 요청시 호출되는 컨트롤러
public class ItemController {
	@Autowired	//객체 주입
	private ShopService service;
	@RequestMapping("list") // /item/list.shop
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();	//	item/list.jsp
		//itemList : item 테이블의 모든 레코드 정보 저장
		List<Item> itemList = service.getItemList();
		mav.addObject("itemList",itemList);
		return mav;
	}
	@RequestMapping("*")
	public ModelAndView detail(Integer id) {
		ModelAndView mav = new ModelAndView();
		//item : id에 해당하는 item 테이블의 1개의 레코드를 저장
		Item item = service.getItem(id);
		mav.addObject("item",item);
		return mav;
	}
	@RequestMapping("create") // /item/create.shop
	public String addform(Model model) {
		model.addAttribute(new Item());
		return "item/add";
	}
	@RequestMapping("register")
	//@Valid : 어노테이션으로 유효성 검사 실행.
	public ModelAndView add(@Valid Item item,BindingResult bresult,
					HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/add");
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		//item 테이블에 insert, picture 파일을 파일로 생성
		service.itemCreate(item,request);
		mav.setViewName("redirect:/item/list.shop");
		return mav;
	}
	@PostMapping("update")
	public ModelAndView update (@Valid Item item,
			BindingResult bresult,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/edit");
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		//삼품 수정
		// item 테이블에 내용 수정.
		// upload 이미지변경시 업로드 실행.
		service.itemUpdate(item,request);
		mav.setViewName("redirect:detail.shop?id="+item.getId());
		return mav;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete (Integer id) {
		ModelAndView mav = new ModelAndView("item/delete");
		service.itemDelete(id);
		mav.setViewName("redirect:/item/list.shop");
		return mav;
	}
}
