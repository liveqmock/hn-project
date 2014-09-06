package hn.travel.persist.repository;


import hn.travel.persist.entity.hotelmessage.HotelMessage;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelMessageDao extends PagingAndSortingRepository<HotelMessage, Long>, JpaSpecificationExecutor<HotelMessage> {
	
}
