/**
 * 
 */
package hn.travel.persist.service.hotel;

import hn.travel.persist.entity.HotelRoom;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.HotelRoomDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author XFZP
 * @date 2014年10月26日
 */
@Component
@Transactional(readOnly = true)
public class HotelRoomService {

	@Autowired
	private HotelRoomDao dao;
	@Autowired
	private BlobDataDao blobDataDao;
	@Autowired
	private RoomKindService roomKindSrv;

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);

		Iterable<HotelRoom> hotelRooms = dao.findAll(idList);
		delete(hotelRooms);
	}

	@Transactional
	public void deleteByHotelIds(Long... hotelIds) {
		if (hotelIds == null || hotelIds.length == 0)
			return;

		List<HotelRoom> hotelRooms = dao.findByHotelIdIn(hotelIds);
		delete(hotelRooms);
	}

	@Transactional
	public void delete(Iterable<? extends HotelRoom> hotelRooms) {
		dao.delete(hotelRooms);

		List<Long> idList = new ArrayList<Long>();
		for (HotelRoom hr : hotelRooms) {
			if (hr.getInfoId() != null)
				blobDataDao.delete(hr.getInfoId());
			if (hr.getCostId() != null)
				blobDataDao.delete(hr.getCostId());
			if (hr.getNoticeId() != null)
				blobDataDao.delete(hr.getNoticeId());
			idList.add(hr.getId());
		}

		roomKindSrv.deleteByRoomIds(idList.toArray(new Long[idList.size()]));
	}

}
