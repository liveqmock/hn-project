/**
 * 
 */
package hn.travel.persist.repository;

import java.util.List;

import hn.travel.persist.entity.HotelRoom;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
public interface HotelRoomDao extends PagingAndSortingRepository<HotelRoom, Long>,
		JpaSpecificationExecutor<HotelRoom> {

	List<HotelRoom> findByHotelIdIn(Long[] hotelIds);

}
