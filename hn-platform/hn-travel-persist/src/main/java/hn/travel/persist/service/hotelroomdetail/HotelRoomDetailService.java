package hn.travel.persist.service.hotelroomdetail;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.hotelroomdetail.HotelRoomDetail;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.HotelRoomDetailDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.google.common.collect.Lists;


@Component
@Transactional
public class HotelRoomDetailService extends GenericManager<HotelRoomDetail>{
	private HotelRoomDetailDao hotelRoomDetailDao;
	
	
	public long queryCount(HotelRoomDetail hotelRoomDetail){
		return hotelRoomDetailDao.count(HotelRoomDetailService.getHotelRoomDetailSpecificationByObject(hotelRoomDetail));
	}
	public Page<HotelRoomDetail> queryList(HotelRoomDetail hotelRoomDetail,Pageable pageable){
		return hotelRoomDetailDao.findAll(HotelRoomDetailService.getHotelRoomDetailSpecificationByObject(hotelRoomDetail), pageable);
	}
	
	
	
	
	
	public Page<HotelRoomDetail> queryHotelRoomDetailListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<HotelRoomDetail> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		hotelRoomDetailDao.findAll(HotelRoomDetailService.getHotelRoomDetailSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<HotelRoomDetail> queryHotelRoomDetailList(HotelRoomDetail hotelRoomDetail){
		return hotelRoomDetailDao.findAll(HotelRoomDetailService.getHotelRoomDetailSpecificationByObject(hotelRoomDetail));
	}
	
	
	
	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if(!StringUtils.hasText(sortType)){
			sort = new Sort(Direction.DESC, "createTime");
		}else{
			sort = new Sort(Direction.DESC, sortType);
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	
	
	
	public static Specification<HotelRoomDetail> getHotelRoomDetailSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<HotelRoomDetail> specification = DynamicSpecifications.bySearchFilter(filters.values(), HotelRoomDetail.class);
		return specification;
	}
	
	
	
	public static Specification<HotelRoomDetail> getHotelRoomDetailSpecificationByObject(HotelRoomDetail hotelRoomDetail) {
		
		//final Byte state=hotelRoomDetail.getState();
//getHotelRoomDetailSpecificationByObject_param
		
		
		return new Specification<HotelRoomDetail>() {
			public Predicate toPredicate(Root<HotelRoomDetail> hotelRoomDetailRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<HotelRoomDetail> userEntityType = hotelRoomDetailRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(hotelRoomDetailRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getHotelRoomDetailSpecificationByObject_method
				
			
				// 公共
				Predicate[] predicates = new Predicate[predicateList.size()];
				predicateList.toArray(predicates);

				CriteriaQuery<?> cqy = query.where(predicates);
				// Selection<?> selectionList = cqy.getSelection();
				pc = cqy.getGroupRestriction();
				return pc;
			}
		};
	}
	
	
	
	@Autowired
	public HotelRoomDetailService(HotelRoomDetailDao hotelRoomDetailDao) {
		super(hotelRoomDetailDao);
		this.hotelRoomDetailDao = hotelRoomDetailDao;
	}
}
