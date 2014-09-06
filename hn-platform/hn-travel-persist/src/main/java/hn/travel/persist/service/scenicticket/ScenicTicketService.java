package hn.travel.persist.service.scenicticket;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.scenicticket.ScenicTicket;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.ScenicTicketDao;

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
public class ScenicTicketService extends GenericManager<ScenicTicket>{
	private ScenicTicketDao scenicTicketDao;
	
	
	public long queryCount(ScenicTicket scenicTicket){
		return scenicTicketDao.count(ScenicTicketService.getScenicTicketSpecificationByObject(scenicTicket));
	}
	public Page<ScenicTicket> queryList(ScenicTicket scenicTicket,Pageable pageable){
		return scenicTicketDao.findAll(ScenicTicketService.getScenicTicketSpecificationByObject(scenicTicket), pageable);
	}
	
	
	
	
	
	public Page<ScenicTicket> queryScenicTicketListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<ScenicTicket> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		scenicTicketDao.findAll(ScenicTicketService.getScenicTicketSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<ScenicTicket> queryScenicTicketList(ScenicTicket scenicTicket){
		return scenicTicketDao.findAll(ScenicTicketService.getScenicTicketSpecificationByObject(scenicTicket));
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
	
	
	
	public static Specification<ScenicTicket> getScenicTicketSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<ScenicTicket> specification = DynamicSpecifications.bySearchFilter(filters.values(), ScenicTicket.class);
		return specification;
	}
	
	
	
	public static Specification<ScenicTicket> getScenicTicketSpecificationByObject(ScenicTicket scenicTicket) {
		
		//final Byte state=scenicTicket.getState();
//getScenicTicketSpecificationByObject_param
		
		
		return new Specification<ScenicTicket>() {
			public Predicate toPredicate(Root<ScenicTicket> scenicTicketRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<ScenicTicket> userEntityType = scenicTicketRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(scenicTicketRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getScenicTicketSpecificationByObject_method
				
			
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
	public ScenicTicketService(ScenicTicketDao scenicTicketDao) {
		super(scenicTicketDao);
		this.scenicTicketDao = scenicTicketDao;
	}
}
