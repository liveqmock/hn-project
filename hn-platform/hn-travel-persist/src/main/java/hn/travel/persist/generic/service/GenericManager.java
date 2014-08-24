package hn.travel.persist.generic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericManager<T> {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(GenericManager.class);
	
	private CrudRepository<T, Long> repository;
	
	public GenericManager() {}

	public GenericManager(CrudRepository<T, Long> repository) {
		this.repository = repository;
	}

	// -- User Manager --//
	public T getEntity(Long id) {
		return repository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public T save(T entity) {
		return repository.save(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long[] idList) {
		for(Long id : idList) {
			delete(id);
		}
	}
	
}
