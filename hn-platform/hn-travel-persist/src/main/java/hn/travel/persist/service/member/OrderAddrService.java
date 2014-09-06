package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.OrderAddr;
import hn.travel.persist.repository.member.OrderAddrDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderAddrService {

	private OrderAddrDao orderAddrDao;

	@Autowired
	public void setOrderAddrDao(OrderAddrDao orderAddrDao) {
		this.orderAddrDao = orderAddrDao;
	}

	/**
	 * 查询所有会员信息
	 * 
	 * @return
	 */
	public List<OrderAddr> getAllOrderDetail() {
		return (List<OrderAddr>) orderAddrDao.findAll();
	}

}
