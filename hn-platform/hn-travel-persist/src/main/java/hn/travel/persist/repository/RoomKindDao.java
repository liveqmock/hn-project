/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.RoomKind;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
public interface RoomKindDao extends
		PagingAndSortingRepository<RoomKind, Long>,
		JpaSpecificationExecutor<RoomKind> {

	Iterable<RoomKind> findByRoomIdIn(Long[] roomIds);

	Page<RoomKind> findByRoomId(Long roomId, Pageable pageable);

}
