package hn.travel.persist.repository;

import hn.travel.persist.entity.hotel.Hotel;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HotelDao extends PagingAndSortingRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
	
}
