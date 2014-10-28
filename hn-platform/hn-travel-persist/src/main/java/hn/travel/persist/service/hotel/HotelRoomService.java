/**
 * 
 */
package hn.travel.persist.service.hotel;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.HotelRoom;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.HotelRoomDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
		if (hotelRooms == null)
			return;

		List<Long> idList = new ArrayList<Long>();
		List<Long> bdIdList = new ArrayList<Long>();
		for (HotelRoom hr : hotelRooms) {
			if (hr.getInfoId() != null)
				bdIdList.add(hr.getInfoId());
			if (hr.getCostId() != null)
				bdIdList.add(hr.getCostId());
			if (hr.getNoticeId() != null)
				bdIdList.add(hr.getNoticeId());
			idList.add(hr.getId());
		}
		if (idList.size() == 0)
			return;

		roomKindSrv.deleteByRoomIds(idList.toArray(new Long[idList.size()]));

		dao.delete(hotelRooms);

		if (bdIdList.size() > 0) {
			Iterable<BlobData> bds = blobDataDao.findAll(bdIdList);
			blobDataDao.delete(bds);
		}
	}

	public Page<HotelRoom> page(Long hotelId, Pageable pageable) {
		return dao.findByHotelId(hotelId, pageable);
	}

	public HotelRoom getDetail(Long id) {
		HotelRoom hr = dao.findOne(id);
		if (hr != null) {
			List<Long> ids = new ArrayList<Long>(3);
			if (hr.getInfoId() != null)
				ids.add(hr.getInfoId());
			if (hr.getCostId() != null)
				ids.add(hr.getCostId());
			if (hr.getNoticeId() != null)
				ids.add(hr.getNoticeId());

			if (ids.size() > 0) {
				for (BlobData bd : blobDataDao.findAll(ids)) {
					if (bd.getId().equals(hr.getInfoId())) {
						hr.setInfo(bd.getData());
					} else if (bd.getId().equals(hr.getCostId())) {
						hr.setCost(bd.getData());
					} else if (bd.getId().equals(hr.getNoticeId())) {
						hr.setNotice(bd.getData());
					}
				}
			}
		}
		return hr;
	}

	@Transactional
	public HotelRoom save(HotelRoom hr) {
		if (StringUtils.hasLength(hr.getInfo())) {
			BlobData bd = blobDataDao.save(new BlobData(hr.getInfoId(), hr
					.getInfo()));
			hr.setInfoId(bd.getId());
		} else if (hr.getInfoId() != null) {
			blobDataDao.delete(hr.getInfoId());
			hr.setInfoId(null);
		}

		if (StringUtils.hasLength(hr.getCost())) {
			BlobData bd = blobDataDao.save(new BlobData(hr.getCostId(), hr
					.getCost()));
			hr.setCostId(bd.getId());
		} else if (hr.getCostId() != null) {
			blobDataDao.delete(hr.getCostId());
			hr.setCostId(null);
		}

		if (StringUtils.hasLength(hr.getNotice())) {
			BlobData bd = blobDataDao.save(new BlobData(hr.getNoticeId(), hr
					.getNotice()));
			hr.setNoticeId(bd.getId());
		} else if (hr.getNoticeId() != null) {
			blobDataDao.delete(hr.getNoticeId());
			hr.setNoticeId(null);
		}

		if (hr.getCreateTime() == null)
			hr.setCreateTime(new Date());
		hr.setUpdateTime(new Date());

		dao.save(hr);
		return hr;
	}

}
