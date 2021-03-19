package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;

public interface BoardMapper {
	String column = "select num,name,pass,subject,content,file1 fileurl,"
			+ " regdate, readcnt, grp, grplevel, grpstep from board";

	@Select({"<script>",
		"select count(*) from board",
		"<if test='searchtype != null and searchcontent != null'>"
		+ " where ${searchtype} like '%${searchcontent}%' </if>",
	"</script>"})
	int count(Map<String, Object> param);

	@Select({"<script>",column ,
		"<if test='searchtype != null and searchcontent != null'>"
		+ " where ${searchtype} like '%${searchcontent}%' </if>",
		"<if test='num !=null'>where num = #{num}</if>",
		"<if test='limit != null'>"
		+ " order by grp desc, grpstep asc limit #{startrow}, #{limit}</if>",
		"</script>"
	})
	List<Board> list(Map<String, Object> param);

	@Select(column+" where num=#{num}")
	Board detail(Map<String, Object> param);

	@Update("update board set readcnt = (readcnt+1) where num =#{num}")
	void readupdate(Map<String, Object> param);

	@Insert("insert into board (num,name,pass,subject,content,file1,regdate,readcnt, grp,grplevel,grpstep)"
				+ "values (#{num},#{name},#{pass},#{subject},#{content},#{fileurl},now(),0,#{grp},#{grplevel},#{grpstep})")
	void insert(Board board);

	@Select("select ifnull(MAX(num),0) from board")
	int maxnum();

	@Update("update board set grpstep = grpstep + 1 where grp =#{grp} and grpstep > #{grpstep}")
	void updateGrpstep(Board board);

	@Update("update board set name=#{name},subject=#{subject},content=#{content},file1=#{fileurl} where num=#{num}")
	void boardupdate(Board board);

	@Delete("delete from board where num=#{num}")
	void boarddelete(Map<String, Object> param);

	/*
	 * Map<String,Object> : 결과의 자료형
	 * {컬럼명1:컬럼값1,컬럼명2:컬럼값2}
	 * [{name:홍길동,cnt:7}
	 * {name:김삿갓,cnt:5}
	 * {name:이몽룡,cnt:3}]
	 */
	@Select("select name,count(*) cnt from board group by name "
			+ "order by cnt desc LIMIT 0,7")
	List<Map<String, Object>> graph1();

	@Select("select name,count(*) cnt from board group by name "
			+ "order by cnt desc LIMIT 0,7")
	List<Map<String, Object>> graph2();
	@Select("select date_format(regdate,'%Y-%m-%d') regdate,"
			+ "count(*) cnt from board "
			+ " group by date_format(regdate, '%Y%m%d') "
			+ " order by regdate desc LIMIT 0,7")
	List<Map<String, Object>> graph3();
			

}
