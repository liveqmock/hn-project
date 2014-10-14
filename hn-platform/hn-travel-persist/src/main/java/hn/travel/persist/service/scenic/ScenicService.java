/**
 * 
 */
package hn.travel.persist.service.scenic;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.Scenic;
import hn.travel.persist.entity.ScenicTicket;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.ScenicDao;
import hn.travel.persist.repository.ScenicTicketDao;
import hn.travel.persist.repository.TicketDao;

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
 * @date 2014年10月8日
 */
@Component
@Transactional(readOnly = true)
public class ScenicService {

	@Autowired
	private ScenicDao scenicDao;
	@Autowired
	private ScenicTicketDao stDao;
	@Autowired
	private BlobDataDao blobDataDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private ScenicTicketService scenicTicketSrv;

	public Page<Scenic> page(String keyword, Pageable pageable) {
		if (StringUtils.hasText(keyword)) {
			keyword = "%" + keyword + "%";
			return scenicDao.findByNameLikeOrTitleLike(keyword, keyword,
					pageable);
		}
		return scenicDao.findAll(pageable);
	}

	public Scenic getSimple(Long id) {
		return scenicDao.findOne(id);
	}

	public Scenic get(Long id) {
		Scenic scenic = scenicDao.findOne(id);
		if (scenic != null) {
			List<Long> ids = new ArrayList<Long>(3);
			if (scenic.getNoticeId() != null)
				ids.add(scenic.getNoticeId());
			if (scenic.getIntroduceId() != null)
				ids.add(scenic.getIntroduceId());
			if (scenic.getTrafficId() != null)
				ids.add(scenic.getIntroduceId());

			if (ids.size() > 0) {
				for (BlobData bd : blobDataDao.findAll(ids)) {
					if (bd.getId().equals(scenic.getNoticeId())) {
						scenic.setNotice(bd.getData());
					} else if (bd.getId().equals(scenic.getIntroduceId())) {
						scenic.setIntroduce(bd.getData());
					} else if (bd.getId().equals(scenic.getTrafficId())) {
						scenic.setTraffic(bd.getData());
					}
				}
			}
		}
		return scenic;
	}

	@Transactional
	public Scenic save(Scenic scenic) {
		if (StringUtils.hasLength(scenic.getNotice())) {
			BlobData bd = blobDataDao.save(new BlobData(scenic.getNoticeId(),
					scenic.getNotice()));
			scenic.setNoticeId(bd.getId());
		} else if (scenic.getNoticeId() != null) {
			blobDataDao.delete(scenic.getNoticeId());
			scenic.setNoticeId(null);
		}

		if (StringUtils.hasLength(scenic.getIntroduce())) {
			BlobData bd = blobDataDao.save(new BlobData(
					scenic.getIntroduceId(), scenic.getIntroduce()));
			scenic.setIntroduceId(bd.getId());
		} else if (scenic.getIntroduceId() != null) {
			blobDataDao.delete(scenic.getIntroduceId());
			scenic.setIntroduceId(null);
		}

		if (StringUtils.hasLength(scenic.getTraffic())) {
			BlobData bd = blobDataDao.save(new BlobData(scenic.getTrafficId(),
					scenic.getTraffic()));
			scenic.setTrafficId(bd.getId());
		} else if (scenic.getTrafficId() != null) {
			blobDataDao.delete(scenic.getTrafficId());
			scenic.setTrafficId(null);
		}

		if (scenic.getCreateTime() == null)
			scenic.setCreateTime(new Date());

		scenic.setUpdateTime(new Date());
		return scenicDao.save(scenic);
	}

	@Transactional
	public void delete(Long... ids) {
		List<Long> idList = Arrays.asList(ids);

		List<ScenicTicket> scenicTickets = stDao.findByScenicIdIn(ids);
		scenicTicketSrv.delete(scenicTickets);

		Iterable<Scenic> scenics = scenicDao.findAll(idList);
		scenicDao.delete(scenics);

		for (Scenic scenic : scenics) {
			if (scenic.getNoticeId() != null)
				blobDataDao.delete(scenic.getNoticeId());
			if (scenic.getIntroduceId() != null)
				blobDataDao.delete(scenic.getIntroduceId());
			if (scenic.getTrafficId() != null)
				blobDataDao.delete(scenic.getTrafficId());
		}
	}
}
