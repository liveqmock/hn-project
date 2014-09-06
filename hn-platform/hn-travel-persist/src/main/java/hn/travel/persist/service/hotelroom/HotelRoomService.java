package hn.travel.persist.service.hotelroom;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.hotelroom.HotelRoom;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.HotelRoomDao;

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
public class HotelRoomService extends GenericManager<HotelRoom>{
	private HotelRoomDao hotelRoomDao;
	
	
	public long queryCount(HotelRoom hotelRoom){
		return hotelRoomDao.count(HotelRoomService.getHotelRoomSpecificationByObject(hotelRoom));
	}
	public Page<HotelRoom> queryList(HotelRoom hotelRoom,Pageable pageable){
		return hotelRoomDao.findAll(HotelRoomService.getHotelRoomSpecificationByObject(hotelRoom), pageable);
	}
	
	
	
	
	
	public Page<HotelRoom> queryHotelRoomListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<HotelRoom> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		hotelRoomDao.findAll(HotelRoomService.getHotelRoomSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<HotelRoom> queryHotelRoomList(HotelRoom hotelRoom){
		return hotelRoomDao.findAll(HotelRoomService.getHotelRoomSpecificationByObject(hotelRoom));
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
	
	
	
	public static Specification<HotelRoom> getHotelRoomSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<HotelRoom> specification = DynamicSpecifications.bySearchFilter(filters.values(), HotelRoom.class);
		return specification;
	}
	
	
	
	public static Specification<HotelRoom> getHotelRoomSpecificationByObject(HotelRoom hotelRoom) {
		
		//final Byte state=hotelRoom.getState();
//getHotelRoomSpecificationByObject_param
		
		
		return new Specification<HotelRoom>() {
			public Predicate toPredicate(Root<HotelRoom> hotelRoomRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<HotelRoom> userEntityType = hotelRoomRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(hotelRoomRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getHotelRoomSpecificationByObject_method
				
			
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
	public HotelRoomService(HotelRoomDao hotelRoomDao) {
		super(hotelRoomDao);
		this.hotelRoomDao = hotelRoomDao;
	}
}
