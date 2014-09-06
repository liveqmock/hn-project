package hn.travel.persist.service.datavalue;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.datavalue.DataValue;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.DataValueDao;

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
public class DataValueService extends GenericManager<DataValue>{
	private DataValueDao dataValueDao;
	
	
	public long queryCount(DataValue dataValue){
		return dataValueDao.count(DataValueService.getDataValueSpecificationByObject(dataValue));
	}
	public Page<DataValue> queryList(DataValue dataValue,Pageable pageable){
		return dataValueDao.findAll(DataValueService.getDataValueSpecificationByObject(dataValue), pageable);
	}
	
	
	
	
	
	public Page<DataValue> queryDataValueListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<DataValue> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		dataValueDao.findAll(DataValueService.getDataValueSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<DataValue> queryDataValueList(DataValue dataValue){
		return dataValueDao.findAll(DataValueService.getDataValueSpecificationByObject(dataValue));
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
	
	
	
	public static Specification<DataValue> getDataValueSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<DataValue> specification = DynamicSpecifications.bySearchFilter(filters.values(), DataValue.class);
		return specification;
	}
	
	
	
	public static Specification<DataValue> getDataValueSpecificationByObject(DataValue dataValue) {
		
		//final Byte state=dataValue.getState();
//getDataValueSpecificationByObject_param
		
		
		return new Specification<DataValue>() {
			public Predicate toPredicate(Root<DataValue> dataValueRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<DataValue> userEntityType = dataValueRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(dataValueRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getDataValueSpecificationByObject_method
				
			
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
	public DataValueService(DataValueDao dataValueDao) {
		super(dataValueDao);
		this.dataValueDao = dataValueDao;
	}
}
