package hn.travel.persist.repository;

import hn.travel.persist.entity.scenic.Scenic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScenicDao extends PagingAndSortingRepository<Scenic, Long>, JpaSpecificationExecutor<Scenic> {
	
}
