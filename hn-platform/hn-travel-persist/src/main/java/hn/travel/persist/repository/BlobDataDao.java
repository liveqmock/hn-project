/**
 * 
 */
package hn.travel.persist.repository;

import hn.travel.persist.entity.BlobData;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author XFZP
 * @date 2014年10月9日
 */
public interface BlobDataDao extends
		PagingAndSortingRepository<BlobData, Long>,
		JpaSpecificationExecutor<BlobData> {

}
