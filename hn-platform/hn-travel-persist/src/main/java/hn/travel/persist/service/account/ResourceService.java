/**
 * 
 */
package hn.travel.persist.service.account;

import hn.travel.persist.generic.bo.Resource;
import hn.travel.persist.generic.bo.ResourceUtils;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenbing
 * @description 系统菜单
 */
@Component
@Transactional
public class ResourceService {

	public List<Resource> getTopMenus() {
		return ResourceUtils.getTopMenus();
	}
	
	public List<Resource> getByParentId(Long pid) {
		return ResourceUtils.getByParentId(pid);
	}
}
