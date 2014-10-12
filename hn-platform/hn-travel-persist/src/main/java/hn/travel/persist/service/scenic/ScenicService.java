/**
 * 
 */
package hn.travel.persist.service.scenic;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.Scenic;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.ScenicDao;

import java.util.ArrayList;
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
	private BlobDataDao blobDataDao;

	public Page<Scenic> page(String keyword, Pageable pageable) {
		if (StringUtils.hasText(keyword)) {
			keyword = "%" + keyword + "%";
			return scenicDao.findByNameLikeOrTitleLike(keyword, keyword,
					pageable);
		}
		return scenicDao.findAll(pageable);
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
		if (scenic.getNoticeId() != null)
			blobDataDao.delete(scenic.getNoticeId());
		if (scenic.getIntroduceId() != null)
			blobDataDao.delete(scenic.getIntroduceId());
		if (scenic.getTrafficId() != null)
			blobDataDao.delete(scenic.getIntroduceId());

		if (StringUtils.hasText(scenic.getNotice())) {
			BlobData noticeBd = blobDataDao.save(new BlobData(scenic
					.getNotice()));
			scenic.setNoticeId(noticeBd.getId());
		}
		if (StringUtils.hasText(scenic.getIntroduce())) {
			BlobData introduceBd = blobDataDao.save(new BlobData(scenic
					.getIntroduce()));
			scenic.setIntroduceId(introduceBd.getId());
		}
		if (StringUtils.hasText(scenic.getTraffic())) {
			BlobData trafficBd = blobDataDao.save(new BlobData(scenic
					.getTraffic()));
			scenic.setTrafficId(trafficBd.getId());
		}
		if (scenic.getCreateTime() == null)
			scenic.setCreateTime(new Date());

		scenic.setUpdateTime(new Date());
		return scenicDao.save(scenic);
	}

	@Transactional
	public void delete(Long... ids) {
		for (Long id : ids)
			scenicDao.delete(id);
	}
}
