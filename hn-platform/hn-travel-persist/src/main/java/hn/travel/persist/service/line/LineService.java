package hn.travel.persist.service.line;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.line.Line;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.LineDao;

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
public class LineService extends GenericManager<Line>{
	private LineDao lineDao;
	
	
	public long queryCount(Line line){
		return lineDao.count(LineService.getLineSpecificationByObject(line));
	}
	public Page<Line> queryList(Line line,Pageable pageable){
		return lineDao.findAll(LineService.getLineSpecificationByObject(line), pageable);
	}
	
	
	
	
	
	public Page<Line> queryLineListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<Line> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		lineDao.findAll(LineService.getLineSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<Line> queryLineList(Line line){
		return lineDao.findAll(LineService.getLineSpecificationByObject(line));
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
	
	
	
	public static Specification<Line> getLineSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Line> specification = DynamicSpecifications.bySearchFilter(filters.values(), Line.class);
		return specification;
	}
	
	
	
	public static Specification<Line> getLineSpecificationByObject(Line line) {
		
		//final Byte state=line.getState();
//getLineSpecificationByObject_param
		
		
		return new Specification<Line>() {
			public Predicate toPredicate(Root<Line> lineRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Line> userEntityType = lineRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(lineRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getLineSpecificationByObject_method
				
			
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
	public LineService(LineDao lineDao) {
		super(lineDao);
		this.lineDao = lineDao;
	}
}
