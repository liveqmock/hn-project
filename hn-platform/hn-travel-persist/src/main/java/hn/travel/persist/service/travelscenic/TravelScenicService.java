package hn.travel.persist.service.travelscenic;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.travelscenic.TravelScenic;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.TravelScenicDao;

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
public class TravelScenicService extends GenericManager<TravelScenic>{
	private TravelScenicDao travelScenicDao;
	
	
	public long queryCount(TravelScenic travelScenic){
		return travelScenicDao.count(TravelScenicService.getTravelScenicSpecificationByObject(travelScenic));
	}
	public Page<TravelScenic> queryList(TravelScenic travelScenic,Pageable pageable){
		return travelScenicDao.findAll(TravelScenicService.getTravelScenicSpecificationByObject(travelScenic), pageable);
	}
	
	
	
	
	
	public Page<TravelScenic> queryTravelScenicListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<TravelScenic> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		travelScenicDao.findAll(TravelScenicService.getTravelScenicSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<TravelScenic> queryTravelScenicList(TravelScenic travelScenic){
		return travelScenicDao.findAll(TravelScenicService.getTravelScenicSpecificationByObject(travelScenic));
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
	
	
	
	public static Specification<TravelScenic> getTravelScenicSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<TravelScenic> specification = DynamicSpecifications.bySearchFilter(filters.values(), TravelScenic.class);
		return specification;
	}
	
	
	
	public static Specification<TravelScenic> getTravelScenicSpecificationByObject(TravelScenic travelScenic) {
		
		//final Byte state=travelScenic.getState();
//getTravelScenicSpecificationByObject_param
		
		
		return new Specification<TravelScenic>() {
			public Predicate toPredicate(Root<TravelScenic> travelScenicRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<TravelScenic> userEntityType = travelScenicRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(travelScenicRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getTravelScenicSpecificationByObject_method
				
			
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
	public TravelScenicService(TravelScenicDao travelScenicDao) {
		super(travelScenicDao);
		this.travelScenicDao = travelScenicDao;
	}
}
