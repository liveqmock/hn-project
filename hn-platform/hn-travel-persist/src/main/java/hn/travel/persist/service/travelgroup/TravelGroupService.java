package hn.travel.persist.service.travelgroup;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.travelgroup.TravelGroup;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.TravelGroupDao;

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
public class TravelGroupService extends GenericManager<TravelGroup>{
	private TravelGroupDao travelGroupDao;
	
	
	public long queryCount(TravelGroup travelGroup){
		return travelGroupDao.count(TravelGroupService.getTravelGroupSpecificationByObject(travelGroup));
	}
	public Page<TravelGroup> queryList(TravelGroup travelGroup,Pageable pageable){
		return travelGroupDao.findAll(TravelGroupService.getTravelGroupSpecificationByObject(travelGroup), pageable);
	}
	
	
	
	
	
	public Page<TravelGroup> queryTravelGroupListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<TravelGroup> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		travelGroupDao.findAll(TravelGroupService.getTravelGroupSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<TravelGroup> queryTravelGroupList(TravelGroup travelGroup){
		return travelGroupDao.findAll(TravelGroupService.getTravelGroupSpecificationByObject(travelGroup));
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
	
	
	
	public static Specification<TravelGroup> getTravelGroupSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<TravelGroup> specification = DynamicSpecifications.bySearchFilter(filters.values(), TravelGroup.class);
		return specification;
	}
	
	
	
	public static Specification<TravelGroup> getTravelGroupSpecificationByObject(TravelGroup travelGroup) {
		
		//final Byte state=travelGroup.getState();
//getTravelGroupSpecificationByObject_param
		
		
		return new Specification<TravelGroup>() {
			public Predicate toPredicate(Root<TravelGroup> travelGroupRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<TravelGroup> userEntityType = travelGroupRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(travelGroupRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getTravelGroupSpecificationByObject_method
				
			
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
	public TravelGroupService(TravelGroupDao travelGroupDao) {
		super(travelGroupDao);
		this.travelGroupDao = travelGroupDao;
	}
}
