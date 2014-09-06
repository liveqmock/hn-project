package hn.travel.persist.repository;


import hn.travel.persist.entity.addressbook.AddressBook;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressBookDao extends PagingAndSortingRepository<AddressBook, Long>, JpaSpecificationExecutor<AddressBook> {
	
}
