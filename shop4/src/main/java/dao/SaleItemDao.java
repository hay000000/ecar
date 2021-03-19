package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import dao.mapper.SaleItemMapper;
import logic.SaleItem;

@Repository
public class SaleItemDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<>();

	public void insert(SaleItem saleItem) {
		
		template.getMapper(SaleItemMapper.class).update(saleItem);
	}
	public List<SaleItem> saleItemList(int saleid) {
		param.clear();
		param.put("saleid", saleid);
		return template.getMapper(SaleItemMapper.class).saleItemList(param);
	}
}
