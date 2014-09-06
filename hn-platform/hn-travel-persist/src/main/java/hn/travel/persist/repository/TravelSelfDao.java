package hn.travel.persist.repository;

import hn.travel.persist.entity.travelself.TravelSelf;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TravelSelfDao extends PagingAndSortingRepository<TravelSelf, Long>, JpaSpecificationExecutor<TravelSelf> {
	
}
