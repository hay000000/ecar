package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;
import logic.Comment;

@Repository
public class BoardDao {
	@Autowired//DataSource 객체 주입.
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<>();
	
// =================== 게시물 건수 ===============================
	public int count(int btype, String searchtype, String searchcontent,String man) {
		if(searchtype != null && searchcontent != null) { //검색 요청
			param.put("searchtype", searchtype);
			param.put("searchcontent", "%" + searchcontent);

		}
		param.put("btype",btype);
		param.put("man",man);
		return template.getMapper(BoardMapper.class).count(param);
	}
// =================== 게시물 목록 =====================
	public List<Board>list(Integer pageNum, int limit, String searchtype, String searchcontent, int btype,String man) {
		if(searchtype != null && searchcontent != null) {
			param.clear();
			param.put("searchtype", searchtype);
			param.put("searchcontent", searchcontent);
			
		}
		param.put("startrow", (pageNum - 1) * limit);
		param.put("limit",limit);
		param.put("btype",btype);
		param.put("man",man);
		return template.getMapper(BoardMapper.class).select(param);
	}
//=================== 게시물 상세보기 ==========================
	public Board detail(int cnt, int btype, String man) {
		return template.getMapper(BoardMapper.class).detail(cnt,btype,man);
	}
//====================== 조회수 증가 ========================
	public void readcntAdd(int cnt) {
		template.getMapper(BoardMapper.class).readcnt(cnt);
		
	}
	public int maxCnt() {
		return template.getMapper(BoardMapper.class).maxcnt();
	}
//===================== 게시물 등록 ===================
	public void write(Board board) {
		template.getMapper(BoardMapper.class).write(board);
	}
//==================== 게시글 수정 =====================
	public void update(Board board) {
		template.getMapper(BoardMapper.class).update(board);
	}
	public void delete(Board board) {
		template.getMapper(BoardMapper.class).delete(board);
	}	
}
