package hn.travel.persist.service.addressbook;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.addressbook.AddressBook;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.AddressBookDao;

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
public class AddressBookService extends GenericManager<AddressBook>{
	private AddressBookDao addressBookDao;
	
	public long queryCount(AddressBook addressBook){
		return addressBookDao.count(AddressBookService.getAddressBookSpecificationByObject(addressBook));
	}
	public Page<AddressBook> queryList(AddressBook addressBook,Pageable pageable){
		return addressBookDao.findAll(AddressBookService.getAddressBookSpecificationByObject(addressBook), pageable);
	}
	
	
	
	
	
	public Page<AddressBook> queryAddressBookListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<AddressBook> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		addressBookDao.findAll(AddressBookService.getAddressBookSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<AddressBook> queryAddressBookList(AddressBook addressBook){
		return addressBookDao.findAll(AddressBookService.getAddressBookSpecificationByObject(addressBook));
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
	
	
	
	public static Specification<AddressBook> getAddressBookSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<AddressBook> specification = DynamicSpecifications.bySearchFilter(filters.values(), AddressBook.class);
		return specification;
	}
	
	
	
	public static Specification<AddressBook> getAddressBookSpecificationByObject(AddressBook addressBook) {
		
		//final Byte state=addressBook.getState();
//getAddressBookSpecificationByObject_param
		
		
		return new Specification<AddressBook>() {
			public Predicate toPredicate(Root<AddressBook> addressBookRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<AddressBook> userEntityType = addressBookRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(addressBookRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getAddressBookSpecificationByObject_method
				
			
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
	public AddressBookService(AddressBookDao addressBookDao) {
		super(addressBookDao);
		this.addressBookDao = addressBookDao;
	}
}
