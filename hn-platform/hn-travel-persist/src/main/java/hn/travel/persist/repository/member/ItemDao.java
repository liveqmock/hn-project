package hn.travel.persist.repository.member;

import hn.travel.persist.entity.member.Item;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemDao extends PagingAndSortingRepository<Item, Long>,
		JpaSpecificationExecutor<Item> {
	
	Item findByItemName(String itemName);
	
	Item findByItemType(String itemType);
	
}
