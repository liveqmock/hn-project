package hn.travel.persist.repository;

import hn.travel.persist.entity.line.Line;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LineDao extends PagingAndSortingRepository<Line, Long>, JpaSpecificationExecutor<Line> {
	
}
