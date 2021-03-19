package dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import logic.Item;
import logic.User;

@Repository
public class UserDao {

	private NamedParameterJdbcTemplate template;
	private RowMapper<User> mapper=
				new BeanPropertyRowMapper<User>(User.class);
	private Map<String,Object> param = new HashMap<>();
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//dataSource : db ¿¬°á °´Ã¼
		template = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void insert(User user) {
		 param.clear();
		 String sql="insert into useraccount (userid,password,username,phoneno,postcode,address,email,birthday)"
				 	+"values(:userid,:password,:username,:phoneno,:postcode,:address,:email,:birthday)";
		 SqlParameterSource prop = new BeanPropertySqlParameterSource(user);
			template.update(sql, prop);
		
	}

	public User selectOne(String userid) {
		param.clear();
		param.put("userid", userid);
		return template.queryForObject
				("select * from useraccount where userid=:userid",param,mapper);
	}

	public void update(User user) {
		String sql = "update useraccount set username=:username,"
				 +" phoneno=:phoneno, postcode=:postcode, address=:address, email=:email, birthday=:birthday"
				 +" where userid=:userid";
		 SqlParameterSource prop = new BeanPropertySqlParameterSource(user);
		 template.update(sql, prop);
		
	}

	public void delete(String userid) {
		param.clear();
		param.put("userid", userid);
		String sql = "delete from useraccount where userid=:userid";
		template.update(sql, param);
		
	}

	public List<User> list() {
		
		return template.query("select * from useraccount", mapper);
		
	}

	public List<User> userlist(String[] idchks) {
		String result = "";
		for(int i=0;i<idchks.length;i++) {
			result += "'"+(idchks[i] + ((i!=idchks.length-1)?"',":"'"));
		}
		
		return template.query("select username,email from useraccount where userid in ("+result+")", mapper);
	}

	public User idSearch(String email, String phoneno) {
		 param.clear();
		 param.put("email", email);
		 param.put("phoneno", phoneno);
		 return template.queryForObject("select userid from useraccount where email=:email and phoneno=:phoneno "
				 ,param,mapper);
		
	}

	public String search(User user) {
		String sql = null;
		if(user.getUserid() == null)
			sql="select concat(substr(userid,1,char_length(userid)-2),'**')"
				+" from useraccount "
				+ "where email=:email and phoneno=:phoneno";
		else
			sql = "select concat('**',substr(password,3,char_length(password)-2))"
			+" from useraccount "
			+"where userid=:userid and email=:email and phoneno=:phoneno";
		System.out.println(sql);
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		return template.queryForObject(sql, param, String.class);
	}
	public void passwordUpdate(String userid,String pass) {
		param.clear();
		param.put("userid", userid);
		param.put("password", pass);
		String sql = "update useraccount set password=:password where userid=:userid";
		template.update(sql, param);
	}

}
