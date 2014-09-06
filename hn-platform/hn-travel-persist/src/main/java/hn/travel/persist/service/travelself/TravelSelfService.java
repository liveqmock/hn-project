package hn.travel.persist.service.travelself;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.travelself.TravelSelf;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.TravelSelfDao;

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
public class TravelSelfService extends GenericManager<TravelSelf>{
	private TravelSelfDao travelSelfDao;
	
	
	public long queryCount(TravelSelf travelSelf){
		return travelSelfDao.count(TravelSelfService.getTravelSelfSpecificationByObject(travelSelf));
	}
	public Page<TravelSelf> queryList(TravelSelf travelSelf,Pageable pageable){
		return travelSelfDao.findAll(TravelSelfService.getTravelSelfSpecificationByObject(travelSelf), pageable);
	}
	
	
	
	
	
	public Page<TravelSelf> queryTravelSelfListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<TravelSelf> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		travelSelfDao.findAll(TravelSelfService.getTravelSelfSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<TravelSelf> queryTravelSelfList(TravelSelf travelSelf){
		return travelSelfDao.findAll(TravelSelfService.getTravelSelfSpecificationByObject(travelSelf));
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
	
	
	
	public static Specification<TravelSelf> getTravelSelfSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<TravelSelf> specification = DynamicSpecifications.bySearchFilter(filters.values(), TravelSelf.class);
		return specification;
	}
	
	
	
	public static Specification<TravelSelf> getTravelSelfSpecificationByObject(TravelSelf travelSelf) {
		
		//final Byte state=travelSelf.getState();
//getTravelSelfSpecificationByObject_param
		
		
		return new Specification<TravelSelf>() {
			public Predicate toPredicate(Root<TravelSelf> travelSelfRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<TravelSelf> userEntityType = travelSelfRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(travelSelfRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getTravelSelfSpecificationByObject_method
				
			
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
	public TravelSelfService(TravelSelfDao travelSelfDao) {
		super(travelSelfDao);
		this.travelSelfDao = travelSelfDao;
	}
}
