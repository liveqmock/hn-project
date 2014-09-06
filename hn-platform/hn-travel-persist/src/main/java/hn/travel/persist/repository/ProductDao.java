package hn.travel.persist.repository;

import hn.travel.persist.entity.product.Product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
}
