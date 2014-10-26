/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.Hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
public interface HotelDao extends PagingAndSortingRepository<Hotel, Long>,
		JpaSpecificationExecutor<Hotel> {

	Page<Hotel> findByNameLikeOrTitleLike(String name, String title,
			Pageable pageable);

}
