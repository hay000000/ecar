package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.Board;


@Repository	
public class BoardDao {
	private NamedParameterJdbcTemplate template;
	private RowMapper<Board> mapper=new BeanPropertyRowMapper<Board>(Board.class);
	private Map<String,Object> param = new HashMap<>();
	private String column = "select num,name,pass,subject,content,file1 fileurl,"
			+ " regdate, readcnt, grp, grplevel, grpstep from board";
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//dataSource : db ¿¬°á °´Ã¼
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int count(String searchtype, String searchcontent) {
		String sql = "select count(*) from board";
		if(searchtype != null && searchcontent != null) {
			sql += " where "+searchtype+" like :searchcontent";
			param.clear();
			param.put("searchcontent","%"+searchcontent + "%");
		}
		return template.queryForObject(sql, param, Integer.class);
	}
	
	public List<Board> list(Integer pageNum, int limit, String searchtype, String searchcontent) {
		String sql = column;
		if(searchtype != null && searchcontent != null) {
			sql += " where "+searchtype+" like :searchcontent";
			param.clear();
			param.put("searchcontent","%"+searchcontent + "%");
		}
		sql +=" order by grp desc, grpstep asc limit :startrow, :limit";
		param.put("startrow", (pageNum - 1) * limit);
		param.put("limit", limit);
		
		return template.query(sql, param,mapper);
	}

	public Board detail(int num) {
		param.clear();
		param.put("num", num);
		return template.queryForObject(column + " where num=:num",param,mapper);
	}

	public void readudate(int num) {
		param.clear();
		param.put("num", num);
		String sql = " update board set readcnt = (readcnt+1) where num =:num";
		template.update(sql, param);
	}

	public void write(Board board) {
		param.clear();
		String sql ="insert into board (num,name,pass,subject,content,file1,regdate,readcnt, grp,grplevel,grpstep)"
				+ "values (:num,:name,:pass,:subject,:content,:fileurl,now(),0,:grp,:grplevel,:grpstep)";
		SqlParameterSource prop = new BeanPropertySqlParameterSource(board);
		template.update(sql, prop);
		
	}

	public Board getboard(Integer num, boolean b) {
		param.clear();
		param.put("num", num);
		return template.queryForObject
				(column+" where num=:num",param,mapper);
	}

	public int maxnum() {
		String sql ="select ifnull(MAX(num),0) from board";
		return template.queryForObject(sql, param, Integer.class);
	}

	public void grpadd(int grp, int grpstep) {
		 param.clear();
		 param.put("grp", grp);
		 param.put("grpstep", grpstep);
		 String sql = "update board set grpstep = grpstep + 1 where grp =:grp and grpstep > :grpstep";
		 template.update(sql, param);
		
	}

	public void updateGrpstep(Board board) {
		param.clear();
		String sql = "update board set grpstep = grpstep + 1 where grp =:grp and grpstep > :grpstep";
		SqlParameterSource prop = new BeanPropertySqlParameterSource(board);
		template.update(sql, prop);
		
	}

	public void boardupdate(Board board) {
		 param.clear();
		 String sql = "update board set name=:name,subject=:subject,content=:content,file1=:fileurl where num=:num";
		 SqlParameterSource prop = new BeanPropertySqlParameterSource(board);
		 template.update(sql, prop);
		
	}

	public void boarddelete(int num) {
		 param.clear();
		 param.put("num", num);
		 String sql="delete from board where num=:num";
		 template.update(sql, param);
		
	}
}
