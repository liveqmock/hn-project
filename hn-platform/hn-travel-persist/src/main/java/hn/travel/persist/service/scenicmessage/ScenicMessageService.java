package hn.travel.persist.service.scenicmessage;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.scenicmessage.ScenicMessage;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.ScenicMessageDao;

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
public class ScenicMessageService extends GenericManager<ScenicMessage>{
	private ScenicMessageDao scenicMessageDao;
	
	
	public long queryCount(ScenicMessage scenicMessage){
		return scenicMessageDao.count(ScenicMessageService.getScenicMessageSpecificationByObject(scenicMessage));
	}
	public Page<ScenicMessage> queryList(ScenicMessage scenicMessage,Pageable pageable){
		return scenicMessageDao.findAll(ScenicMessageService.getScenicMessageSpecificationByObject(scenicMessage), pageable);
	}
	
	
	
	
	
	public Page<ScenicMessage> queryScenicMessageListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<ScenicMessage> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		scenicMessageDao.findAll(ScenicMessageService.getScenicMessageSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<ScenicMessage> queryScenicMessageList(ScenicMessage scenicMessage){
		return scenicMessageDao.findAll(ScenicMessageService.getScenicMessageSpecificationByObject(scenicMessage));
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
	
	
	
	public static Specification<ScenicMessage> getScenicMessageSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<ScenicMessage> specification = DynamicSpecifications.bySearchFilter(filters.values(), ScenicMessage.class);
		return specification;
	}
	
	
	
	public static Specification<ScenicMessage> getScenicMessageSpecificationByObject(ScenicMessage scenicMessage) {
		
		//final Byte state=scenicMessage.getState();
//getScenicMessageSpecificationByObject_param
		
		
		return new Specification<ScenicMessage>() {
			public Predicate toPredicate(Root<ScenicMessage> scenicMessageRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<ScenicMessage> userEntityType = scenicMessageRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(scenicMessageRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getScenicMessageSpecificationByObject_method
				
			
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
	public ScenicMessageService(ScenicMessageDao scenicMessageDao) {
		super(scenicMessageDao);
		this.scenicMessageDao = scenicMessageDao;
	}
}
