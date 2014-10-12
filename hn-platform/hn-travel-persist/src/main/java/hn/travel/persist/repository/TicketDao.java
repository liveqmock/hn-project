/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.Ticket;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月12日
 */
public interface TicketDao extends PagingAndSortingRepository<Ticket, Long>,
		JpaSpecificationExecutor<Ticket> {

}
