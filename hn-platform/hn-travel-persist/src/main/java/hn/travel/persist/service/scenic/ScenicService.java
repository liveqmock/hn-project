/**
 * 
 */
package hn.travel.persist.service.scenic;

import hn.travel.persist.entity.BlobData;
import hn.travel.persist.entity.Scenic;
import hn.travel.persist.repository.BlobDataDao;
import hn.travel.persist.repository.ScenicDao;

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

	public Page<Scenic> page(Pageable pageable) {
		return scenicDao.findAll(pageable);
	}

	@Transactional
	public Scenic save(Scenic scenic, String notice, String introduce,
			String traffic) {
		if (StringUtils.hasText(notice)) {
			BlobData noticeBd = blobDataDao.save(new BlobData(notice));
			scenic.setNoticeId(noticeBd.getId());
		}
		if (StringUtils.hasText(introduce)) {
			BlobData introduceBd = blobDataDao.save(new BlobData(introduce));
			scenic.setIntroduceId(introduceBd.getId());
		}
		if (StringUtils.hasText(traffic)) {
			BlobData trafficBd = blobDataDao.save(new BlobData(traffic));
			scenic.setTrafficId(trafficBd.getId());
		}
		return scenicDao.save(scenic);
	}
}
