package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import exception.CartEmptyException;
import logic.Cart;
import logic.Item;
import logic.ItemSet;
import logic.Sale;
import logic.ShopService;
import logic.User;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private ShopService service;
	
	@RequestMapping("cartAdd")
	public ModelAndView add(Integer id,Integer quantity,HttpSession session) {
		ModelAndView mav = new ModelAndView("cart/cart");
		//item : id해당하는 item 정보 저장
		Item item = service.getItem(id);
		Cart cart= (Cart)session.getAttribute("CART");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("CART", cart);
		}
		cart.push(new ItemSet(item,quantity));
		mav.addObject("message",item.getName()+":"+quantity+"개 장바구니 추가");
		mav.addObject("cart",cart);
		return mav;
	}
	
	@RequestMapping("cartDelete")
	public ModelAndView delete(Integer index,HttpSession session) {
		ModelAndView mav = new ModelAndView("cart/cart");
		Cart cart= (Cart)session.getAttribute("CART");
		int idx = index;
		mav.addObject("message",cart.getItemSetList().remove(idx).getItem().getName()+"가(이) 삭제되었습니다.");
		mav.addObject("cart",cart);
		return mav;
	}
	@RequestMapping("cartView")
	public ModelAndView view(HttpSession session) {
		ModelAndView mav = new ModelAndView("cart/cart");
		Cart cart= (Cart)session.getAttribute("CART");
		
		if(cart ==null || cart.getItemSetList().size() ==0) {
			//throw : 예외 발생
			//throws : 예외 내보내기. 예외처리
			throw new CartEmptyException("장바구니에 상품이 없습니다.","../item/list.shop");
		}
		
		mav.addObject("cart",cart);
		return mav;
	}
	@RequestMapping("checkout")
	public ModelAndView checkout(HttpSession session) {
		ModelAndView mav = new ModelAndView("cart/checkout");
		Cart cart= (Cart)session.getAttribute("CART");
		User user= (User)session.getAttribute("loginUser");
		mav.addObject("user",user);
		mav.addObject("cart",cart);
		
		return mav;
	}
	@RequestMapping("end")
	public ModelAndView checkend (HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Cart cart = (Cart)session.getAttribute("CART");
		User loginUser=(User)session.getAttribute("loginUser");
		Sale sale = service.checkend(loginUser,cart);
		long total = cart.getTotal();
		session.removeAttribute("CART");
		mav.addObject("sale",sale);
		mav.addObject("total",total);
		return mav;
	}
}
