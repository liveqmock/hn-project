package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.Item;
import hn.travel.persist.repository.member.ItemDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ItemService {

	private ItemDao itemDao;

	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 查询所有会员信息
	 * 
	 * @return
	 */
	public List<Item> getAllItem() {
		return (List<Item>) itemDao.findAll();
	}

}
