package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.SaleItem;

public interface SaleItemMapper {

	@Update("insert into saleitem (saleid,seq,itemid,quantity) "
				+" values (#{saleid},#{seq},#{itemid},#{quantity})")
	void update(SaleItem saleItem);

	@Select("select * from saleitem where saleid=#{saleid}")
	List<SaleItem> saleItemList(Map<String, Object> param);

}
