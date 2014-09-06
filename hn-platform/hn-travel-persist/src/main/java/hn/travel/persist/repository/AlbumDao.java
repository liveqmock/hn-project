package hn.travel.persist.repository;


import hn.travel.persist.entity.album.Album;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumDao extends PagingAndSortingRepository<Album, Long>, JpaSpecificationExecutor<Album> {
	
}
