package dao;


import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;


import dao.mapper.UserMapper;

import logic.User;
import util.CipherUtil;


@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<>();
	@Autowired
	private CipherUtil cipher;


	public void insert(User user) {
		 param.clear();		 
			template.getMapper(UserMapper.class).insert(user);
	}

	public User selectOne(String userid) {
		param.clear();
		param.put("userid", userid);
		List<User> list = template.getMapper(UserMapper.class).select(param);
		if(list == null) return null;
		else return list.get(0);
	}

	public void update(User user) {
		 template.getMapper(UserMapper.class).update(user);
		
	}

	public void delete(String userid) {
		param.clear();
		param.put("userid", userid);
		template.getMapper(UserMapper.class).delete(param);
		
	}

	public List<User> list() {
		
		return template.getMapper(UserMapper.class).select(null);
		
	}

	public List<User> userlist(String[] idchks) {
		param.clear();
		param.put("userids",idchks);
		
		return template.getMapper(UserMapper.class).select(param);
	}


	public String search(User user){
		param.clear();
		param.put("email", user.getEmail());
		param.put("phoneno", user.getPhoneno());
		if(user.getUserid() == null)
			return template.getMapper(UserMapper.class).search(user);
			
		else
			param.put("userid", user.getUserid());
			return template.getMapper(UserMapper.class).search1(user);
		
	}
	public void passwordUpdate(String userid,String pass) {
		param.clear();
		param.put("userid", userid);
		param.put("password", pass);
		template.getMapper(UserMapper.class).passUpdate(param);
	}

}
