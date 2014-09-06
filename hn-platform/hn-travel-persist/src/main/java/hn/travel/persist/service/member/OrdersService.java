package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.Orders;
import hn.travel.persist.repository.member.OrdersDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrdersService {

	private OrdersDao ordersDao;

	@Autowired
	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	/**
	 * 查询所有订单信息
	 * 
	 * @return
	 */
	public List<Orders> getAllOrders() {
		return (List<Orders>) ordersDao.findAll();
	}

}
