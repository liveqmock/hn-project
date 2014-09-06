package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.OrderDetail;
import hn.travel.persist.repository.member.OrderDetailDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderDetailService {
	
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}
	
	/**
	 * 查询所有会员信息
	 * 
	 * @return
	 */
	public List<OrderDetail> getAllOrderDetail(){
		return (List<OrderDetail>)orderDetailDao.findAll();
	}
	
}
