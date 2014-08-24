package hn.travel.cms.generic.bo;

import java.util.List;
import java.util.Map;

/***
 * 前端树对象
 * @author Administrator
 *
 */
public class Node {
	
	public static final String ROOT_NAME = "根节点";
	
	public static final String STATE_OPEN = "open";
	public static final String STATE_CLOSED = "closed";

	private String id;
	
	private String text;
	
	private String state;
	
	private boolean checked;
	
	private Map<String, Object> attributes;
	
	private List<Node> children;
	
	public Node() {
	}

	public Node(String id, String text, String state, boolean checked,
			Map<String, Object> attributes, List<Node> children) {
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.attributes = attributes;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public static String getStateOpen() {
		return STATE_OPEN;
	}

	public static String getStateClosed() {
		return STATE_CLOSED;
	}
	
}
