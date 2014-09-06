package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.Shopping;
import hn.travel.persist.repository.member.ShoppingDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ShoppingService {
	
	private ShoppingDao shoppingDao;
	
	@Autowired
	public void setShoppingDao(ShoppingDao shoppingDao) {
		this.shoppingDao = shoppingDao;
	}

	/**
	 * 查询所有会员购物车信息
	 * 
	 * @return
	 */
	public List<Shopping> getAllShopping(){
		return (List<Shopping>)shoppingDao.findAll();
	}
	
	/**
	 * 根据ID查询会员信息
	 * 
	 * @param id 会员ID
	 * @return
	 */
	public Shopping getShopping(Long id){
		return shoppingDao.findOne(id);
	}
	
}
