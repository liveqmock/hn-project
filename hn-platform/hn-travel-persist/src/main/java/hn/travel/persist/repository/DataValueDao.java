package hn.travel.persist.repository;

import hn.travel.persist.entity.datavalue.DataValue;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataValueDao extends PagingAndSortingRepository<DataValue, Long>, JpaSpecificationExecutor<DataValue> {
	
}
