/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.DatePrice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月14日
 */
public interface DatePriceDao extends
		PagingAndSortingRepository<DatePrice, Long>,
		JpaSpecificationExecutor<DatePrice> {

	List<DatePrice> findByKindIdIn(Long... kindId);
}
