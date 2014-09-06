package hn.travel.persist.repository;

import hn.travel.persist.entity.travelscenic.TravelScenic;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TravelScenicDao extends PagingAndSortingRepository<TravelScenic, Long>, JpaSpecificationExecutor<TravelScenic> {
	
}
