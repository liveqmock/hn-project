package hn.travel.persist.repository;

import hn.travel.persist.entity.scenicticket.ScenicTicket;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScenicTicketDao extends PagingAndSortingRepository<ScenicTicket, Long>, JpaSpecificationExecutor<ScenicTicket> {
	
}
