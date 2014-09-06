package hn.travel.persist.service.album;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import hn.travel.persist.entity.album.Album;
import hn.travel.persist.generic.service.GenericManager;
import hn.travel.persist.repository.AlbumDao;

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
public class AlbumService extends GenericManager<Album>{
	private AlbumDao albumDao;
	
	
	public long queryCount(Album album){
		return albumDao.count(AlbumService.getAlbumSpecificationByObject(album));
	}
	public Page<Album> queryList(Album album,Pageable pageable){
		return albumDao.findAll(AlbumService.getAlbumSpecificationByObject(album), pageable);
	}
	
	
	
	
	
	public Page<Album> queryAlbumListPage( Map<String, Object> searchParams,int pageNumber, int pageSize,
			String sortType){
		Page<Album> page=null;
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		albumDao.findAll(AlbumService.getAlbumSpecification(searchParams), pageRequest);
		return page;
	}
	
	public List<Album> queryAlbumList(Album album){
		return albumDao.findAll(AlbumService.getAlbumSpecificationByObject(album));
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
	
	
	
	public static Specification<Album> getAlbumSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Album> specification = DynamicSpecifications.bySearchFilter(filters.values(), Album.class);
		return specification;
	}
	
	
	
	public static Specification<Album> getAlbumSpecificationByObject(Album album) {
		
		//final Byte state=album.getState();
//getAlbumSpecificationByObject_param
		
		
		return new Specification<Album>() {
			public Predicate toPredicate(Root<Album> albumRoot,
					CriteriaQuery<?> query, CriteriaBuilder builder) {

				Predicate pc = null;
				// query.select(couponIssue);

				// 存放多个条件
				List<Predicate> predicateList = Lists.newArrayList();

				EntityType<Album> userEntityType = albumRoot.getModel();

//				if (state!=null) {
//					Predicate accountPredicate = builder.equal(albumRoot
//							.get(userEntityType.getSingularAttribute("state",
//									String.class)), state.toString());
//					predicateList.add(accountPredicate);
//				}
				
//getAlbumSpecificationByObject_method
				
			
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
	public AlbumService(AlbumDao albumDao) {
		super(albumDao);
		this.albumDao = albumDao;
	}
}
