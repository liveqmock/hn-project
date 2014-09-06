package hn.travel.persist.repository.member;

import hn.travel.persist.entity.member.OrderAddr;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderAddrDao extends PagingAndSortingRepository<OrderAddr, Long>,
		JpaSpecificationExecutor<OrderAddr> {
	
	OrderAddr findByMemId(Long memId);
	
	OrderAddr findByConsigneeName(String consigneeName);
	
}
