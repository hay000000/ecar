package dao.mapper;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;
import logic.Comment;

public interface BoardMapper {

	final String select = " select cnt, id, pw, title, con, file1 fileurl"
			+ ", date, readcnt, btype, man from board ";

	@Select({"<script>",
		" select count(*) from board where btype=#{btype} ",
		"<if test='man != null'>and man=#{man}</if>", 
		"<if test='searchtype !=null and searchcontent != null'>"
		+ " where ${searchtype} like '%${searchcontent}%'</if>",
		" ORDER BY date desc ",
		"</script>"})
	int count(Map<String, Object> param);

	@Select({"<script>",select,
		"<if test='searchtype !=null and searchcontent != null'>"
		+ " where ${searchtype} like '%${searchcontent}%'",
		"and btype=#{btype}and man=#{man} </if>",
		"<if test='cnt != null'>where cnt = #{cnt}</if>",
		"<if test='btype != null'> where btype = #{btype}</if>",
		"<if test='man != null'> and man = #{man}</if>",
		" ORDER BY date desc ",
		"</script>"})
	List<Board> select(Map<String, Object> param);

	@Select(select +" where cnt=#{cnt} and btype=#{btype}")
	//Board detail(Map<Integer, Integer> param);
	Board detail(@Param("cnt")int cnt, @Param("btype")int btype, @Param("man")String man);

	@Update("update board set readcnt = readcnt + 1 where cnt=#{cnt}")
	void readcnt(int cnt);     

	@Select("select ifnull(max(cnt),0) from board")
	int maxcnt();

	@Insert(" insert into board (cnt,id,pw,title,con,file1,"
			+ "date,readcnt,btype,man)"
			+" values (#{cnt},#{id},#{pw},#{title},#{con},#{fileurl},"
			+ "now(),0,#{btype},#{man})")
	void write(@Valid Board board);

	@Update(" update board set "
			+ " id=#{id},pw=#{pw},title=#{title},"
			+ " con=#{con},file1=#{fileurl},man=#{man} where cnt=#{cnt} and btype=#{btype}")
	void update(Board board);

	@Delete("delete from board where cnt=#{cnt}")
	void delete(Board board);
	
}
