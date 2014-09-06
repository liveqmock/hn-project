/**
 * 
 */
package hn.travel.persist.generic.bo;

import java.util.Set;

/**
 * @author chenbing
 * 
 */
public class Resource {

	private Long id;

	private String name;

	private String url;

	private Integer type;
	
	private Set<String> roles;

	private Integer priority;

	private Long parentId;

	public Resource() {
	}

	public Resource(Long id, String name, String url, Integer type,
			Integer priority, Long parentId) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.priority = priority;
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

}
