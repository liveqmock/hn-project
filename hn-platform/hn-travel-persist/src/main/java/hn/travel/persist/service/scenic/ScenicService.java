package hn.travel.persist.service.scenic;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.scenic.Scenic;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.ScenicDao;

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
public class ScenicService extends GenericManager<Scenic>{
	private ScenicDao scenicDao;
	
	
	public long queryCount(Scenic scenic){
		return scenicDao.count(ScenicService.getScenicSpecificationByObject(scenic));
	}
	public Page<Scenic> queryList(Scenic scenic,Pageable pageable){
		return scenicDao.findAll(ScenicService.getScenicSpecificationByObject(scenic), pageable);
	}
	
	
	
	
	
	public Page<Scenic> queryScenicListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<Scenic> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		scenicDao.findAll(ScenicService.getScenicSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<Scenic> queryScenicList(Scenic scenic){
		return scenicDao.findAll(ScenicService.getScenicSpecificationByObject(scenic));
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
	
	
	
	public static Specification<Scenic> getScenicSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Scenic> specification = DynamicSpecifications.bySearchFilter(filters.values(), Scenic.class);
		return specification;
	}
	
	
	
	public static Specification<Scenic> getScenicSpecificationByObject(Scenic scenic) {
		
		//final Byte state=scenic.getState();
//getScenicSpecificationByObject_param
		
		
		return new Specification<Scenic>() {
			public Predicate toPredicate(Root<Scenic> scenicRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Scenic> userEntityType = scenicRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(scenicRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getScenicSpecificationByObject_method
				
			
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
	public ScenicService(ScenicDao scenicDao) {
		super(scenicDao);
		this.scenicDao = scenicDao;
	}
}
