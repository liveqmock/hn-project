/**
 * 
 */
package hn.travel.persist.generic.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenbing
 * 
 */
public class ResourceUtils {

	private static List<Resource> rs;
	/** 根节点ID[我还原222ssss*/
	public static final Long ROOT_ID = 0L;

	static {
		rs = new ArrayList<Resource>();
		rs.add(new Resource(1L, "系统管理", "", 1, 1, ROOT_ID));
		rs.add(new Resource(2L, "会员管理", "", 1, 1, ROOT_ID));
		rs.add(new Resource(3L, "酒店管理", "", 1, 1, ROOT_ID));
		rs.add(new Resource(4L, "系统管理_用户管理", "/admin/user", 1, 1, 1L));
		rs.add(new Resource(5L, "会员管理_会员列表", "/mem", 1, 1, 2L));
		rs.add(new Resource(6L, "酒店管理_酒店列表", "/hotel", 1, 1, 3L));
	}

	public static List<Resource> getByParentId(Long pid) {
		List<Resource> list = new ArrayList<>();
		for (Resource r : rs) {
			if (r.getParentId().compareTo(pid) == 0) {
				list.add(r);
			}
		}
		return list;
	}

	public static List<Resource> getTopMenus() {
		List<Resource> list = new ArrayList<>();
		for (Resource r : rs) {
			if (r.getParentId().compareTo(ROOT_ID) == 0) {
				list.add(r);
			}
		}
		return list;
	}
}
