/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.ScenicTicket;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月12日
 */
public interface ScenicTicketDao extends
		PagingAndSortingRepository<ScenicTicket, Long>,
		JpaSpecificationExecutor<ScenicTicket> {

	Page<ScenicTicket> findByScenicId(Long scenicId, Pageable pageable);
	
	List<ScenicTicket> findByScenicIdIn(Long... scenicId);
}
