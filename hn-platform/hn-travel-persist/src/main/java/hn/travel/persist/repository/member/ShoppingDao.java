package hn.travel.persist.repository.member;

import hn.travel.persist.entity.member.Shopping;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShoppingDao extends PagingAndSortingRepository<Shopping, Long>,
		JpaSpecificationExecutor<Shopping> {
	
	Shopping findByMemId(Long memId);
	
	Shopping findByItemId(Long itemId);
	
}
