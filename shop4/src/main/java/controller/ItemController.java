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

@Controller		//@Component + Controller ���
@RequestMapping("item") //item/* ��û�� ȣ��Ǵ� ��Ʈ�ѷ�
public class ItemController {
	@Autowired	//��ü ����
	private ShopService service;
	@RequestMapping("list") // /item/list.shop
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();	//	item/list.jsp
		//itemList : item ���̺��� ��� ���ڵ� ���� ����
		List<Item> itemList = service.getItemList();
		mav.addObject("itemList",itemList);
		return mav;
	}
	@RequestMapping("*")
	public ModelAndView detail(Integer id) {
		ModelAndView mav = new ModelAndView();
		//item : id�� �ش��ϴ� item ���̺��� 1���� ���ڵ带 ����
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
	//@Valid : ������̼����� ��ȿ�� �˻� ����.
	public ModelAndView add(@Valid Item item,BindingResult bresult,
					HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/add");
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		//item ���̺� insert, picture ������ ���Ϸ� ����
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
		//��ǰ ����
		// item ���̺� ���� ����.
		// upload �̹�������� ���ε� ����.
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
