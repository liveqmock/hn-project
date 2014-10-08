/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.Scenic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月8日
 */
public interface ScenicDao extends PagingAndSortingRepository<Scenic, Long>,
		JpaSpecificationExecutor<Scenic> {

}
