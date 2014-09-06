package hn.travel.persist.repository.member;

import java.util.List;

import hn.travel.persist.entity.member.Favorites;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FavoritesDao extends PagingAndSortingRepository<Favorites, Long>,
		JpaSpecificationExecutor<Favorites> {
	
	List<Favorites> findByMemId(Long memId);
	
}
