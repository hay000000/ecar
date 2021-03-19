package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.BoardException;
import logic.Board;
import logic.ShopService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	ShopService service;

	@RequestMapping("list")
	public ModelAndView list(Integer pageNum, String searchtype, String searchcontent) {
		ModelAndView mav = new ModelAndView();
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		if (searchtype == null || searchcontent == null || searchtype.trim().equals("")
				|| searchcontent.trim().equals("")) {
			searchtype = null;
			searchcontent = null;
		}
		int limit = 10;
		int listcount = service.boardcount(searchtype, searchcontent);
		List<Board> boardlist = service.boardlist(pageNum, limit, searchtype, searchcontent);
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (int) ((pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardno = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount", listcount);
		mav.addObject("boardlist", boardlist);
		mav.addObject("boardno", boardno);
		mav.addObject("today", new SimpleDateFormat("yyyyMMdd").format(new Date()));
		return mav;
	}

	@RequestMapping("detail")
	public ModelAndView detail(int num) {
		ModelAndView mav = new ModelAndView();
		Board board = service.detail(num);
		service.readupdate(num);
		mav.addObject("board", board);
		return mav;
	}

	@GetMapping("*")
	public ModelAndView getBoard(Integer num) {
		ModelAndView mav = new ModelAndView();
		Board board = null;
		if (num == null)
			board = new Board();
		else
			board = service.detail(num);
		mav.addObject("board", board);
		return mav;
	}

	@PostMapping("write")
	public ModelAndView write(@Valid Board board, BindingResult bindResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println(board);
		if (bindResult.hasErrors()) {
			mav.getModel().putAll(bindResult.getModel());
			return mav;
		}
		try {
			int num = service.maxnum();
			board.setNum(++num);
			board.setGrp(num);
			if (service.boardwrite(board, request))
				mav.setViewName("redirect:list.shop");

		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException("��Ͻ���", "write.shop");
		}

		return mav;
	}

	@PostMapping("reply")
	public ModelAndView reply(@Valid Board board, BindingResult bindresult) {
		ModelAndView mav = new ModelAndView();
		if (bindresult.hasErrors()) {
			Board dbBoard = service.detail(board.getNum());
			Map<String, Object> map = bindresult.getModel();
			Board b = (Board) map.get("board");
			b.setSubject(dbBoard.getSubject());
			return mav;
		}
		try {
			service.boardReply(board);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException("��Ͻ���", "reply.shop?num=" + board.getNum());
		}
//		service.grpadd(board.getGrp(),board.getGrplevel());
//		int grplevel = board.getGrplevel();
//		int grpstep = board.getGrpstep();
//		int num = service.maxnum();
//		board.setNum(++num);
//		board.setGrplevel(grplevel + 1);
//		board.setGrpstep(grpstep + 1);
//		try {
//		if(service.boardreply(board)) {
//			mav.setViewName("redirect:list.shop");
//			mav.addObject("board",board);
//		}
//		}catch(Exception e) {
//			e.printStackTrace();
//			throw new BoardException("��Ͻ���", "reply.shop");
//		}
		return mav;
	}

	/*
	 * 1. �Ķ���� �� board��ü ����. ��ȿ�� ����. 2. �Էµ� ��й�ȣ�� db�� ��й�ȣ�� �� ��й�ȣ�� �´°�� ���������� db��
	 * ���� ÷������ ���� : ÷������ ���ε�, fileurl ���� ���� detail.shop ������ ��û 3. Ʋ����� '��й�ȣ��
	 * Ʋ���ϴ�.', update.shop Get������� ȣ��
	 */
	@PostMapping("update")
	public ModelAndView update(@Valid Board board, BindingResult bindresult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board dbBoard = service.detail(board.getNum());
		if (bindresult.hasErrors()) {
			mav.getModel().putAll(bindresult.getModel());
			return mav;
		}
		if (dbBoard.getPass().equals(board.getPass())) {
			service.boardupdate(board, request);
			mav.setViewName("redirect:det" + "ail.shop?num=" + board.getNum());
		} else {
			throw new BoardException("��й�ȣ�� Ʋ���ϴ�.", "update.shop?num=" + board.getNum());
		}

		return mav;

	}

	@PostMapping("delete")
	public ModelAndView delete(Board board, BindingResult bindresult) {
		ModelAndView mav = new ModelAndView();
		
		Board dbboard = service.detail(board.getNum());
		
		if (!dbboard.getPass().equals(board.getPass())) {
			bindresult.reject("error.login.password");
			return mav;
		} else {
			try {
				service.boarddelete(board.getNum());
				mav.setViewName("redirect:list.shop");
			} catch (Exception e) {
				e.printStackTrace();
				throw new BoardException("��������", "detail.shop?num=" + board.getNum());
			}
		}
		return mav;
	}
	//upload : ckeditor���� ������ �ִ� ������ �̸�.
	//			<input type="file" name="upload"...>
	//CKEditorFuncNum : ckeditor���� ������ �Ķ���� �̸�
	@RequestMapping("imgupload")
	public String imgupload(MultipartFile upload,
			String CKEditorFuncNum,HttpServletRequest request,Model model) {
		String path=request.getServletContext().getRealPath("/")
					+"board/imgfile/";
		File f = new File(path);
		if(!f.exists()) f.mkdirs();
		if(!upload.isEmpty()) {
			File file = new File(path,upload.getOriginalFilename());
			try {
				upload.transferTo(file);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		String fileName = request.getContextPath() +
					"/board/imgfile/" + upload.getOriginalFilename();
		model.addAttribute("fileName",fileName);
		model.addAttribute("CKEditorFuncNum",CKEditorFuncNum);
		return "ckedit"; //view �̸�
				
	}
}
