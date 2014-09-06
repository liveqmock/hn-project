package hn.travel.persist.service.hotelmessage;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.hotelmessage.HotelMessage;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.HotelMessageDao;

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
public class HotelMessageService extends GenericManager<HotelMessage>{
	private HotelMessageDao hotelMessageDao;
	
	
	public long queryCount(HotelMessage hotelMessage){
		return hotelMessageDao.count(HotelMessageService.getHotelMessageSpecificationByObject(hotelMessage));
	}
	public Page<HotelMessage> queryList(HotelMessage hotelMessage,Pageable pageable){
		return hotelMessageDao.findAll(HotelMessageService.getHotelMessageSpecificationByObject(hotelMessage), pageable);
	}
	
	
	
	
	
	public Page<HotelMessage> queryHotelMessageListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<HotelMessage> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		hotelMessageDao.findAll(HotelMessageService.getHotelMessageSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<HotelMessage> queryHotelMessageList(HotelMessage hotelMessage){
		return hotelMessageDao.findAll(HotelMessageService.getHotelMessageSpecificationByObject(hotelMessage));
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
	
	
	
	public static Specification<HotelMessage> getHotelMessageSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<HotelMessage> specification = DynamicSpecifications.bySearchFilter(filters.values(), HotelMessage.class);
		return specification;
	}
	
	
	
	public static Specification<HotelMessage> getHotelMessageSpecificationByObject(HotelMessage hotelMessage) {
		
		//final Byte state=hotelMessage.getState();
//getHotelMessageSpecificationByObject_param
		
		
		return new Specification<HotelMessage>() {
			public Predicate toPredicate(Root<HotelMessage> hotelMessageRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<HotelMessage> userEntityType = hotelMessageRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(hotelMessageRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getHotelMessageSpecificationByObject_method
				
			
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
	public HotelMessageService(HotelMessageDao hotelMessageDao) {
		super(hotelMessageDao);
		this.hotelMessageDao = hotelMessageDao;
	}
}
