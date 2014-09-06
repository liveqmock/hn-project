package hn.travel.persist.repository;

import hn.travel.persist.entity.hotelroomcategory.HotelRoomCategory;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelRoomCategoryDao extends PagingAndSortingRepository<HotelRoomCategory, Long>, JpaSpecificationExecutor<HotelRoomCategory> {
	
}
