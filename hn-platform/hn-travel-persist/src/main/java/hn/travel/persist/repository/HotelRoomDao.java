package hn.travel.persist.repository;


import hn.travel.persist.entity.hotelroom.HotelRoom;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRoomDao extends PagingAndSortingRepository<HotelRoom, Long>, JpaSpecificationExecutor<HotelRoom> {
	
}
