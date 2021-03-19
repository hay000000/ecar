package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import dao.BoardDao;
import dao.ItemDao;
import dao.SaleDao;
import dao.SaleItemDao;
import dao.UserDao;


@Service	//@Component + Service ���. : Controller�� Dao�� �߰� ���� ����
public class ShopService {
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private SaleItemDao saleItemDao;
	@Autowired
	private BoardDao boardDao;
	
	
	
	public List<Item> getItemList(){
		return itemDao.list();
	}
	public List<User> getUserList(){
		return userDao.list();
	}
	

	public Item getItem(Integer id) {
		return itemDao.selectOne(id);
	}
	public void itemCreate(Item item, HttpServletRequest request) {
		if(item.getPicture() != null && !item.getPicture().isEmpty()) {
			uploadFileCreate(item.getPicture(),request,"img/");
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.insert(item);
	}
	public void userCreate(User user) {
		userDao.insert(user);
		
	}
	//MultipartFile�� �����͸� ���Ϸ� ����
	private void uploadFileCreate
		(MultipartFile picture, HttpServletRequest request,String path) {
		String orgFile = picture.getOriginalFilename();	 //���ε�� ������ �̸�
		String uploadPath = request.getServletContext().getRealPath("/")+path;
		File fpath = new File(uploadPath);
		if(!fpath.exists()) fpath.mkdirs();	//�ɷε� ������ ���� ��� ���� ����
		try {
			//picture : ���ε�� ������ ���� ����
			//transferTo : ���ε�� ������ ������ File�� ����
			picture.transferTo(new File(uploadPath+orgFile));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void itemUpdate(Item item, HttpServletRequest request) {
		//���� ���ε�
		if(item.getPicture()!=null && !item.getPicture().isEmpty()) {
			uploadFileCreate(item.getPicture(), request, "img/");
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		//db ���� ����
		itemDao.update(item);
	}


	public void itemDelete(Integer id) {
		itemDao.delete(id);
	}


	public User getUser(String userid) {
		return userDao.selectOne(userid);
	}
	public List<Sale> salelist(String id){
		return saleDao.salelist(id);
	}
	public List<SaleItem> saleItemList(int saleid){
		return saleItemDao.saleItemList(saleid);
	}


	public void userUpdate(User user) {
		userDao.update(user);
		
	}


	public void delete(String userid) {
		userDao.delete(userid);
		
	}
	public List<User> userList(String[] idchks) {
		return userDao.userlist(idchks);
	}
	
	public String getSearch(User user) {
		return userDao.search(user);
	}
	public void userPasswordUpdate(String userid, String pass) {
		 userDao.passwordUpdate(userid, pass);
		
	}
	public Sale checkend(User loginUser, Cart cart) {
		int maxid = saleDao.getMaxSaleid();
		Sale sale = new Sale();
		sale.setSaleid(maxid+1);
		sale.setUser(loginUser);
		sale.setUserid(loginUser.getUserid());
		saleDao.insert(sale);
		
		int i =0;
		for(ItemSet iset : cart.getItemSetList()) {
			int seq = ++i;
			SaleItem saleItem = new SaleItem(sale.getSaleid(),seq,iset);
			sale.getItemList().add(saleItem);
			saleItemDao.insert(saleItem);
		}
		return sale;
	}
	public int boardcount(String searchtype, String searchcontent) {
		return boardDao.count(searchtype,searchcontent);
	}
	public List<Board> boardlist(Integer pageNum, int limit, String searchtype, String searchcontent) {
		return boardDao.list(pageNum,limit,searchtype,searchcontent);
	}
	public Board detail(int num) {
		return boardDao.detail(num);
	}
	public void readupdate(int num) {
		 boardDao.readudate(num);
	}
	public boolean boardwrite(Board board,HttpServletRequest request) {
		if(board.getFile1()!=null && !board.getFile1().isEmpty()) {
			uploadFileCreate(board.getFile1(), request, "img/");
			board.setFileurl(board.getFile1().getOriginalFilename());
		}
		try{
			boardDao.write(board);
			return true;
		}catch(Exception e	) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int maxnum() {
		return boardDao.maxnum();
	}

	
	public void boardReply(Board board) {
		boardDao.updateGrpstep(board);
		int max = maxnum();
		board.setNum(++max);
		board.setGrplevel(board.getGrplevel() + 1);
		board.setGrpstep(board.getGrpstep() + 1);
		boardDao.write(board);
	}
	public void boardupdate(Board board, HttpServletRequest request) {
		if(board.getFile1()!=null && !board.getFile1().isEmpty()) {
			uploadFileCreate(board.getFile1(), request, "img/");
			board.setFileurl(board.getFile1().getOriginalFilename());
		}
		boardDao.boardupdate(board);
		
	}
	public void boarddelete(int num) {
		 boardDao.boarddelete(num);
		
	}

	public Map<String, Object> graph1() {
		Map<String, Object> map = new HashMap<String, Object>();
		for(Map<String, Object> m : boardDao.graph1()) {
			map.put((String)m.get("name"),m.get("cnt"));
		}
		return map;
	}
	public Map<String, Object> graph2() {
		Map<String, Object> map = new HashMap<String, Object>();
		for(Map<String, Object> m : boardDao.graph2()) {
			map.put((String)m.get("regdate"),m.get("cnt"));
		}
		return map;
	}


}
