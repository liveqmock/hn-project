package hn.travel.persist.repository;

import hn.travel.persist.entity.hotelroomdetail.HotelRoomDetail;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRoomDetailDao extends PagingAndSortingRepository<HotelRoomDetail, Long>, JpaSpecificationExecutor<HotelRoomDetail> {
	
}
