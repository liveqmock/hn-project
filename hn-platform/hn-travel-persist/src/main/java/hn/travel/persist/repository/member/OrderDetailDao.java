package hn.travel.persist.repository.member;

import hn.travel.persist.entity.member.OrderDetail;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDetailDao extends PagingAndSortingRepository<OrderDetail, Long>,
		JpaSpecificationExecutor<OrderDetail> {
	
	OrderDetail findByItemId(Long ItemId);
	
	OrderDetail findByOrderId(Long orderId);
	
}
