package hn.travel.persist.service.comment;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.comment.Comment;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.CommentDao;

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
public class CommentService extends GenericManager<Comment>{
	private CommentDao commentDao;
	
	
	public long queryCount(Comment comment){
		return commentDao.count(CommentService.getCommentSpecificationByObject(comment));
	}
	public Page<Comment> queryList(Comment comment,Pageable pageable){
		return commentDao.findAll(CommentService.getCommentSpecificationByObject(comment), pageable);
	}
	
	
	
	
	
	public Page<Comment> queryCommentListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<Comment> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		commentDao.findAll(CommentService.getCommentSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<Comment> queryCommentList(Comment comment){
		return commentDao.findAll(CommentService.getCommentSpecificationByObject(comment));
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
	
	
	
	public static Specification<Comment> getCommentSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Comment> specification = DynamicSpecifications.bySearchFilter(filters.values(), Comment.class);
		return specification;
	}
	
	
	
	public static Specification<Comment> getCommentSpecificationByObject(Comment comment) {
		
		//final Byte state=comment.getState();
//getCommentSpecificationByObject_param
		
		
		return new Specification<Comment>() {
			public Predicate toPredicate(Root<Comment> commentRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Comment> userEntityType = commentRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(commentRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getCommentSpecificationByObject_method
				
			
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
	public CommentService(CommentDao commentDao) {
		super(commentDao);
		this.commentDao = commentDao;
	}
}
