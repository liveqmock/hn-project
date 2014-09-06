package hn.travel.persist.service.hotelroomcategory;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.hotelroomcategory.HotelRoomCategory;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.HotelRoomCategoryDao;

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
public class HotelRoomCategoryService extends GenericManager<HotelRoomCategory>{
	private HotelRoomCategoryDao hotelRoomCategoryDao;
	
	
	public long queryCount(HotelRoomCategory hotelRoomCategory){
		return hotelRoomCategoryDao.count(HotelRoomCategoryService.getHotelRoomCategorySpecificationByObject(hotelRoomCategory));
	}
	public Page<HotelRoomCategory> queryList(HotelRoomCategory hotelRoomCategory,Pageable pageable){
		return hotelRoomCategoryDao.findAll(HotelRoomCategoryService.getHotelRoomCategorySpecificationByObject(hotelRoomCategory), pageable);
	}
	
	
	
	
	
	public Page<HotelRoomCategory> queryHotelRoomCategoryListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<HotelRoomCategory> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		hotelRoomCategoryDao.findAll(HotelRoomCategoryService.getHotelRoomCategorySpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<HotelRoomCategory> queryHotelRoomCategoryList(HotelRoomCategory hotelRoomCategory){
		return hotelRoomCategoryDao.findAll(HotelRoomCategoryService.getHotelRoomCategorySpecificationByObject(hotelRoomCategory));
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
	
	
	
	public static Specification<HotelRoomCategory> getHotelRoomCategorySpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<HotelRoomCategory> specification = DynamicSpecifications.bySearchFilter(filters.values(), HotelRoomCategory.class);
		return specification;
	}
	
	
	
	public static Specification<HotelRoomCategory> getHotelRoomCategorySpecificationByObject(HotelRoomCategory hotelRoomCategory) {
		
		//final Byte state=hotelRoomCategory.getState();
//getHotelRoomCategorySpecificationByObject_param
		
		
		return new Specification<HotelRoomCategory>() {
			public Predicate toPredicate(Root<HotelRoomCategory> hotelRoomCategoryRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<HotelRoomCategory> userEntityType = hotelRoomCategoryRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(hotelRoomCategoryRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getHotelRoomCategorySpecificationByObject_method
				
			
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
	public HotelRoomCategoryService(HotelRoomCategoryDao hotelRoomCategoryDao) {
		super(hotelRoomCategoryDao);
		this.hotelRoomCategoryDao = hotelRoomCategoryDao;
	}
}
