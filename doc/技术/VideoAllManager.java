package wofs.common.persist.video.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import wofs.common.persist.generic.dao.VideoGenericSpecUtils;
import wofs.common.persist.generic.service.GenericManager;
import wofs.common.persist.utils.StringTools;
import wofs.common.persist.video.bo.MyImageUtils;
import wofs.common.persist.video.bo.enums.IdxUpdateEnum;
import wofs.common.persist.video.bo.enums.VideoClassifyEnum;
import wofs.common.persist.video.bo.enums.VideoImgSizeEnum;
import wofs.common.persist.video.dao.VideoAllDao;
import wofs.common.persist.video.dao.VideoLinkDao;
import wofs.common.persist.video.entity.VideoAll;

import com.google.common.collect.Lists;

@Component
@Transactional(readOnly = true)
public class VideoAllManager extends GenericManager<VideoAll> {

	private static Logger logger = LoggerFactory
			.getLogger(VideoAllManager.class);

	private VideoAllDao videoAllDao;
	@Autowired
	private VideoLinkDao videoLinkDao;
	@PersistenceContext
	private EntityManager em;

	public VideoAllManager() {
	}

	@Autowired
	public VideoAllManager(VideoAllDao videoAllDao) {
		super(videoAllDao);
		this.videoAllDao = videoAllDao;
	}

	public List<VideoAll> findAll(VideoAll videoAll) {
		return videoAllDao.findAll(VideoGenericSpecUtils
				.getVideoAllSpec(videoAll));
	}

	public Page<VideoAll> findAll(VideoAll videoAll, Pageable pageable) {
		return videoAllDao.findAll(
				VideoGenericSpecUtils.getVideoAllSpec(videoAll), pageable);
	}

	public Page<VideoAll> findAllWithNotNull(VideoAll videoAll,
			List<String> notNullItems, Pageable pageable) {
		return videoAllDao.findAll(VideoGenericSpecUtils
				.getVideoAllSpecWithNotNull(videoAll, notNullItems), pageable);
	}

	public Page<VideoAll> findAllWithImg(VideoAll videoAll, Pageable pageable) {
		return videoAllDao.findAll(
				VideoGenericSpecUtils.getVideoAllSpecWithImg(videoAll),
				pageable);
	}

	@Transactional(readOnly = false)
	public VideoAll addPlay(Long id) {
		VideoAll va = videoAllDao.findOne(id);
		if (va != null) {
			Long ps = va.getPlay() == null ? 0L : va.getPlay();
			va.setPlay(ps + 1);
			va.setIdxUpdateFlag(IdxUpdateEnum.NO.getIntValue());
			videoAllDao.save(va);
		} else {
			va = new VideoAll();
			va.setPlay(0L);
			va.setRate("5");
		}
		return va;
	}

	public List<VideoAll> findByRefId(Long refId) {
		return videoAllDao.findByRefId(refId);
	}

	@Transactional(readOnly = false)
	public void wrapVideoAllWithPicSizeAndLink(List<VideoAll> list,
			String[] picSize, FtpManager ftpManager) {
		if (list != null && !list.isEmpty()) {
			if (picSize.length == 1) {
				for (VideoAll v : list) {
					v.setClassifyName(VideoClassifyEnum.getDisplayNameByValue(v
							.getClassify()));
					v.setLinkList(videoLinkDao.findByRefId(v.getId()));
					if (!StringUtils.hasText(v.getImgPaths())) {
						System.out
								.println("+++++++++++v.getImgPaths()为空+++++++++++++++++"
										+ v.getName());
						v.setImgPaths(MyImageUtils.processImg(
								StringTools.getFileLocalPath(v.getImgLink()),
								v.getClassify(), picSize, true, ftpManager));
						v.setIdxUpdateFlag(IdxUpdateEnum.NO.getIntValue());
						videoAllDao.save(v);
						v.setMiniUrl(StringTools.getRescUrl(v.getImgPaths(),
								picSize[0], false));
						v.setShowUrl(StringTools.getRescUrl(v.getImgPaths(),
								true));
						System.out
								.println("+++++++++++v.getImgPaths()为空+++++++++++++++++处理结束"
										+ v.getName());
					} else {
						System.out
								.println("------------------v.getImgPaths()不为空---------------"
										+ v.getName());
						v.setMiniUrl(plugVideoImg(v, picSize[0], ftpManager));
						v.setShowUrl(v.getMiniUrl());
						System.out
								.println("------------------v.getImgPaths()不为空---------------处理结束"
										+ v.getName());
					}
				}
			} else if (picSize.length == 2) {
				for (VideoAll v : list) {
					v.setClassifyName(VideoClassifyEnum.getDisplayNameByValue(v
							.getClassify()));
					v.setLinkList(videoLinkDao.findByRefId(v.getId()));
					if (!StringUtils.hasText(v.getImgPaths())) {
						v.setImgPaths(MyImageUtils.processImg(
								StringTools.getFileLocalPath(v.getImgLink()),
								v.getClassify(), picSize, true, ftpManager));
						v.setIdxUpdateFlag(IdxUpdateEnum.NO.getIntValue());
						videoAllDao.save(v);
						v.setMiniUrl(StringTools.getRescUrl(v.getImgPaths(),
								picSize[0], false));
						v.setShowUrl(StringTools.getRescUrl(v.getImgPaths(),
								picSize[1], false));
					} else {
						v.setMiniUrl(plugVideoImg(v, picSize[0], ftpManager));
						v.setShowUrl(plugVideoImg(v, picSize[1], ftpManager));
					}
				}
			}
		}
	}

	@Transactional(readOnly = false)
	public VideoAll saveVideo(VideoAll all, FtpManager ftpManager) {
		if (all.getImgFile1() != null) {
			try {
				String path1 = MyImageUtils.copyImg(all.getImgFile1()
						.getInputStream(), all.getClassify(), ftpManager);
				List<String> list = Lists.newArrayList();
				String op1 = StringTools.removePathViaSize(all.getImgPaths(),
						list);

				// 更新100x140尺寸图片
				String listPic = MyImageUtils.processImg(StringTools
						.getFileLocalPath(path1), all.getClassify(),
						new String[] { VideoImgSizeEnum.LIST_VIDEO_100x140
								.getValue() }, false, ftpManager);
				list.add(VideoImgSizeEnum.LIST_VIDEO_100x140.getValue());
				String op2 = StringTools.removePathViaSize(op1, list);
				Set<String> ps = StringTools.split2SetFilterEmpty(op2, ";",
						String.class);
				ps.add(path1);
				ps.add(listPic);
				all.setImgPaths(StringTools.list2StrWithRegex(ps, ";"));
				if (!StringUtils.hasText(all.getImgLink())) {
					all.setImgLink(path1);
				}
			} catch (IOException e) {
				logger.error("获取文件流出现异常{}", e);
				e.printStackTrace();
			}
		}
		all.setIdxUpdateFlag(IdxUpdateEnum.NO.getIntValue());
		return videoAllDao.save(all);
	}

	/***
	 * 将Video中之前没有的图片路径，填充到imgPaths中
	 * 
	 * @param v
	 * @param picSize
	 * @return
	 */
	@Transactional(readOnly = false)
	public String plugVideoImg(VideoAll v, String picSize, FtpManager ftpManager) {
		String hitImg = StringTools.getRescUrl(v.getImgPaths(), picSize, false);
		System.out.println("hitImg==================> " + hitImg);
		if (!StringUtils.hasText(hitImg)) {
			Set<String> ps = StringTools.split2SetFilterEmpty(v.getImgPaths(),
					";", String.class);
			String noSizePic = StringTools
					.getPathViaSize(v.getImgPaths(), true);
			System.out.println("noSizePic============>" + noSizePic);
			if (StringUtils.hasText(noSizePic)) {
				ps.add(MyImageUtils.processImg(
						StringTools.getFileLocalPath(noSizePic),
						v.getClassify(), new String[] { picSize }, false,
						ftpManager));
			} else if (StringUtils.hasText(v.getImgLink())) {
				ps.add(MyImageUtils.processImg(
						StringTools.getFileLocalPath(v.getImgLink()),
						v.getClassify(), new String[] { picSize }, false,
						ftpManager));
			}
			System.out.println("*******************图片处理完成********************");
			v.setImgPaths(StringTools.list2StrWithRegex(ps, ";"));
			v.setIdxUpdateFlag(IdxUpdateEnum.NO.getIntValue());
			videoAllDao.save(v);
			return StringTools.getRescUrl(v.getImgPaths(), picSize, false);
		}
		return hitImg;
	}

	public List<VideoAll> getByClassifyAndArea(String classify, String area,
			int count) {
		StringBuilder b = new StringBuilder(
				"SELECT a FROM VideoAll a where a.state=1 and a.status <> '预告' and a.pub is not null and a.multiSource is not null");
		int i = 1;
		List<Object> params = Lists.newLinkedList();
		if (StringUtils.hasText(classify)) {
			b.append(" and a.classify=?").append(i++);
			params.add(classify);
		}
		if (StringUtils.hasText(area)) {
			Set<String> areaList = StringTools.split2SetFilterEmpty(area, ";",
					String.class);
			StringBuilder ab = new StringBuilder();
			for (String a : areaList) {
				ab.append(" a.area like ?").append(i++).append(" or");
				params.add(a);
			}
			if (ab.toString().endsWith("or")) {
				String astr = ab.delete(ab.length() - 2, ab.length())
						.toString();
				b.append(" and (").append(astr).append(")");
			}
		}
		b.append(" order by a.pub desc");
		Query query = em.createQuery(b.toString());
		for (int num = 1; num < i; num++) {
			query.setParameter(num, params.get(num - 1));
		}
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

	public static void main(String[] args) {
		String area = "中国;大陆;香港";
		if (StringUtils.hasText(area)) {
			Set<String> areaList = StringTools.split2SetFilterEmpty(area, ";",
					String.class);
			StringBuilder ab = new StringBuilder();
			for (String a : areaList) {
				ab.append(" a.area like ?").append(" xx ").append(" or");
			}
			if (ab.toString().endsWith("or")) {
				String astr = ab.delete(ab.length() - 2, ab.length())
						.toString();
				System.out.println(astr);
			}
		}
	}

	public long count() {
		return videoAllDao.count();
	}

	public long count(VideoAll videoAll) {
		return videoAllDao.count(VideoGenericSpecUtils
				.getVideoAllSpec(videoAll));
	}

}
