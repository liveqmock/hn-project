package hn.travel.persist.service.product;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.product.Product;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.ProductDao;

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
public class ProductService extends GenericManager<Product>{
	private ProductDao productDao;
	
	
	public long queryCount(Product product){
		return productDao.count(ProductService.getProductSpecificationByObject(product));
	}
	public Page<Product> queryList(Product product,Pageable pageable){
		return productDao.findAll(ProductService.getProductSpecificationByObject(product), pageable);
	}
	
	
	
	
	
	public Page<Product> queryProductListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<Product> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		productDao.findAll(ProductService.getProductSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<Product> queryProductList(Product product){
		return productDao.findAll(ProductService.getProductSpecificationByObject(product));
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
	
	
	
	public static Specification<Product> getProductSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Product> specification = DynamicSpecifications.bySearchFilter(filters.values(), Product.class);
		return specification;
	}
	
	
	
	public static Specification<Product> getProductSpecificationByObject(Product product) {
		
		//final Byte state=product.getState();
//getProductSpecificationByObject_param
		
		
		return new Specification<Product>() {
			public Predicate toPredicate(Root<Product> productRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Product> userEntityType = productRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(productRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getProductSpecificationByObject_method
				
			
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
	public ProductService(ProductDao productDao) {
		super(productDao);
		this.productDao = productDao;
	}
}
