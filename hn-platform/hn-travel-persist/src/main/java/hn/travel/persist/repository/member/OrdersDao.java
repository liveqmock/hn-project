package hn.travel.persist.repository.member;

import hn.travel.persist.entity.member.Orders;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersDao extends PagingAndSortingRepository<Orders, Long>,
		JpaSpecificationExecutor<Orders> {
	
	Orders findByMemId(Long memId);
	
}
