/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.Itinerary;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月14日
 */
public interface ItineraryDao extends PagingAndSortingRepository<Itinerary, Long>,
		JpaSpecificationExecutor<Itinerary> {

}
