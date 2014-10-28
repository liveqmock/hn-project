/**
 * 
 */
package hn.travel.persist.service.hotel;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.Hotel;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.HotelDao;

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
public class HotelService {

	@Autowired
	private HotelDao dao;
	@Autowired
	private BlobDataDao blobDataDao;
	@Autowired
	private HotelRoomService hotelRoomSrv;

	public Page<Hotel> page(String keyword, Pageable pageable) {
		if (StringUtils.hasText(keyword)) {
			keyword = "%" + keyword + "%";
			return dao.findByNameLikeOrTitleLike(keyword, keyword, pageable);
		}
		return dao.findAll(pageable);
	}

	public Hotel getDetail(Long id) {
		Hotel hotel = dao.findOne(id);
		if (hotel != null) {
			List<Long> ids = new ArrayList<Long>(2);
			if (hotel.getFacilityId() != null)
				ids.add(hotel.getFacilityId());
			if (hotel.getPositionId() != null)
				ids.add(hotel.getPositionId());

			if (ids.size() > 0) {
				for (BlobData bd : blobDataDao.findAll(ids)) {
					if (bd.getId().equals(hotel.getFacilityId())) {
						hotel.setFacility(bd.getData());
					} else if (bd.getId().equals(hotel.getPositionId())) {
						hotel.setPosition(bd.getData());
					}
				}
			}
		}
		return hotel;
	}

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);

		hotelRoomSrv.deleteByHotelIds(ids);

		Iterable<Hotel> hotels = dao.findAll(idList);
		dao.delete(hotels);

		for (Hotel hotel : hotels) {
			if (hotel.getFacilityId() != null)
				blobDataDao.delete(hotel.getFacilityId());
			if (hotel.getPositionId() != null)
				blobDataDao.delete(hotel.getPositionId());
		}
	}

	@Transactional
	public Hotel save(Hotel hotel) {
		if (StringUtils.hasLength(hotel.getFacility())) {
			BlobData bd = blobDataDao.save(new BlobData(hotel.getFacilityId(),
					hotel.getFacility()));
			hotel.setFacilityId(bd.getId());
		} else if (hotel.getFacilityId() != null) {
			blobDataDao.delete(hotel.getFacilityId());
			hotel.setFacilityId(null);
		}

		if (StringUtils.hasLength(hotel.getPosition())) {
			BlobData bd = blobDataDao.save(new BlobData(hotel.getPositionId(),
					hotel.getPosition()));
			hotel.setPositionId(bd.getId());
		} else if (hotel.getPositionId() != null) {
			blobDataDao.delete(hotel.getPositionId());
			hotel.setPositionId(null);
		}

		if (hotel.getCreateTime() == null)
			hotel.setCreateTime(new Date());

		hotel.setUpdateTime(new Date());
		return dao.save(hotel);
	}

	public Hotel get(Long id) {
		return dao.findOne(id);
	}

}
