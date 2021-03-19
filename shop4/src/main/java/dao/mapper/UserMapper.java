package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.User;

public interface UserMapper {

	@Insert("insert into useraccount (userid,password,username,phoneno,postcode,address,email,birthday)"
				 	+"values(#{userid},#{password},#{username},#{phoneno},#{postcode},#{address},#{email},#{birthday})")
	void insert(User user);

	@Select({"<script>",
		"select * from useraccount",
		"<if test='userid != null'>where userid=#{userid}</if>",
		"<if test='userids != null'>where userid in "
		+ "<foreach collection='userids' item='id' separator=',' open='(' close=')'>#{id}</foreach></if>",
	"</script>"})
	List<User> select(Map<String, Object> param);

	@Update("update useraccount set username=#{username},"
				 +" phoneno=#{phoneno}, postcode=#{postcode}, address=#{address}, email=#{email}, birthday=#{birthday}"
				 +" where userid=#{userid}")
	void update(User user);

	@Delete("delete from useraccount where userid=#{userid}")
	void delete(Map<String, Object> param);

	@Select("select userid from useraccount where email=#{email} and phoneno=#{phoneno} ")
	User idSearch(Map<String, Object> param);

	@Select("select concat(substr(userid,1,char_length(userid)-2),'**')"
				+" from useraccount "
				+ "where email=#{email} and phoneno=#{phoneno}")
	String search(User user);

	@Select("select concat('**',substr(password,3,char_length(password)-2))"
			+" from useraccount "
			+"where userid=#{userid} and email=#{email} and phoneno=#{phoneno}")
	String search1(User user);

	@Update("update useraccount set password=#{password} where userid=#{userid}")
	void passUpdate(Map<String, Object> param);

}
