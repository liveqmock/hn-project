/**
 * 
 */
package hn.travel.persist.service.hotel;

import hn.travel.persist.entity.RoomKind;
import hn.travel.persist.repository.RoomKindDao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
@Component
@Transactional(readOnly = true)
public class RoomKindService {

	@Autowired
	private RoomKindDao dao;

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);
		Iterable<RoomKind> rks = dao.findAll(idList);

		delete(rks);
	}

	@Transactional
	public void deleteByRoomIds(Long... roomIds) {
		if (roomIds == null || roomIds.length == 0)
			return;

		Iterable<RoomKind> rks = dao.findByRoomIdIn(roomIds);

		delete(rks);
	}

	@Transactional
	public void delete(Iterable<? extends RoomKind> roomKinds) {
		dao.delete(roomKinds);
	}

	public Page<RoomKind> page(Long roomId, Pageable pageable) {
		return dao.findByRoomId(roomId, pageable);
	}

	public RoomKind get(Long id) {
		return dao.findOne(id);
	}

	@Transactional
	public RoomKind save(RoomKind rk) {
		if (rk.getCreateTime() == null)
			rk.setCreateTime(new Date());
		rk.setUpdateTime(new Date());

		return dao.save(rk);
	}
}
