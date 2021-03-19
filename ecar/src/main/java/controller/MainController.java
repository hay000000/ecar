package controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.ShopService;

@Controller
@RequestMapping("user")
public class MainController {
	@Autowired
	private ShopService service;
	@GetMapping("*")
	public String main(Model model) {
		model.addAttribute(model);
		return null;
	}
	@RequestMapping("main")
	public ModelAndView charger() {
		ModelAndView mav = new ModelAndView();
		List<String> list = service.charger();
		List<String> list1 = service.list1();
		List<Map<String, String>> listmap = service.ecarlist();
		mav.addObject("list",list);
		mav.addObject("list1",list1);
		mav.addObject("listmap",listmap);
		return mav;
	}
}


