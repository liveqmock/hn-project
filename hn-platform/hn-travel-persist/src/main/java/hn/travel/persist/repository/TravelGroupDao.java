package hn.travel.persist.repository;

import hn.travel.persist.entity.travelgroup.TravelGroup;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TravelGroupDao extends PagingAndSortingRepository<TravelGroup, Long>, JpaSpecificationExecutor<TravelGroup> {
	
}
