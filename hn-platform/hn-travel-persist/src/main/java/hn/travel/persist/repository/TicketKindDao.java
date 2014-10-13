/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.TicketKind;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月13日
 */
public interface TicketKindDao extends
		PagingAndSortingRepository<TicketKind, Long>,
		JpaSpecificationExecutor<TicketKind> {

	Page<TicketKind> findByTicketId(Long ticketId, Pageable pageable);
	
	List<TicketKind> findByTicketIdIn(Long... ticketId);
}
