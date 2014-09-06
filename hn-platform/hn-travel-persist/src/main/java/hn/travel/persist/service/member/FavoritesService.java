package hn.travel.persist.service.member;

import hn.travel.persist.entity.member.Favorites;
import hn.travel.persist.repository.member.FavoritesDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FavoritesService {
	
	private FavoritesDao favoritesDao;
	
	@Autowired
	public void setFavoritesDao(FavoritesDao favoritesDao) {
		this.favoritesDao = favoritesDao;
	}

	/**
	 * 查询所有会员收藏信息
	 * 
	 * @return
	 */
	public List<Favorites> getAllFavorites(){
		return (List<Favorites>)favoritesDao.findAll();
	}
	
	/*public List<Favorites> getMembersByCriteria(Member member){
		return (List<Favorites>)favoritesDao.findAll(MemberSpecUtils.getMemberSpec(member));
	}*/
	
	/**
	 * 根据ID查询会员收藏信息
	 * 
	 * @param id 会员ID
	 * @return
	 */
	public Favorites getFavorites(Long id){
		return favoritesDao.findOne(id);
	}
	
	/**
	 * 根据会员ID查询会员收藏信息
	 * 
	 * @param memId 会员ID
	 * @return
	 */
	public List<Favorites> findByMemId(Long memId){
		return favoritesDao.findByMemId(memId);
	}
	
}
