package hn.travel.persist.service.hotel;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.hotel.Hotel;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.HotelDao;

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
public class HotelService extends GenericManager<Hotel>{
	private HotelDao hotelDao;
	
	
	public long queryCount(Hotel hotel){
		return hotelDao.count(HotelService.getHotelSpecificationByObject(hotel));
	}
	public Page<Hotel> queryList(Hotel hotel,Pageable pageable){
		return hotelDao.findAll(HotelService.getHotelSpecificationByObject(hotel), pageable);
	}
	
	
	
	
	
	public Page<Hotel> queryHotelListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<Hotel> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		hotelDao.findAll(HotelService.getHotelSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<Hotel> queryHotelList(Hotel hotel){
		return hotelDao.findAll(HotelService.getHotelSpecificationByObject(hotel));
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
	
	
	
	public static Specification<Hotel> getHotelSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Hotel> specification = DynamicSpecifications.bySearchFilter(filters.values(), Hotel.class);
		return specification;
	}
	
	
	
	public static Specification<Hotel> getHotelSpecificationByObject(Hotel hotel) {
		
		//final Byte state=hotel.getState();
//getHotelSpecificationByObject_param
		
		
		return new Specification<Hotel>() {
			public Predicate toPredicate(Root<Hotel> hotelRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Hotel> userEntityType = hotelRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(hotelRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getHotelSpecificationByObject_method
				
			
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
	public HotelService(HotelDao hotelDao) {
		super(hotelDao);
		this.hotelDao = hotelDao;
	}
}
