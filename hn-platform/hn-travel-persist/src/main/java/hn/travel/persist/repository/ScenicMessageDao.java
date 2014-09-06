package hn.travel.persist.repository;

import hn.travel.persist.entity.scenicmessage.ScenicMessage;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScenicMessageDao extends PagingAndSortingRepository<ScenicMessage, Long>, JpaSpecificationExecutor<ScenicMessage> {
	
}
