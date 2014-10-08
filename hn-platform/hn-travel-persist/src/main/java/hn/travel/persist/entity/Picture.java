package hn.travel.persist.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the picture database table.
 * 
 */
@Entity
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="arge_uri")
	private String argeUri;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String desciption;

	@Column(name="middle_uri")
	private String middleUri;

	private String name;

	@Column(name="raw_uri")
	private String rawUri;

	@Column(name="ref_id")
	private BigInteger refId;

	@Column(name="ref_type")
	private int refType;

	@Column(name="small_uri")
	private String smallUri;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	public Picture() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArgeUri() {
		return this.argeUri;
	}

	public void setArgeUri(String argeUri) {
		this.argeUri = argeUri;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getMiddleUri() {
		return this.middleUri;
	}

	public void setMiddleUri(String middleUri) {
		this.middleUri = middleUri;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRawUri() {
		return this.rawUri;
	}

	public void setRawUri(String rawUri) {
		this.rawUri = rawUri;
	}

	public BigInteger getRefId() {
		return this.refId;
	}

	public void setRefId(BigInteger refId) {
		this.refId = refId;
	}

	public int getRefType() {
		return this.refType;
	}

	public void setRefType(int refType) {
		this.refType = refType;
	}

	public String getSmallUri() {
		return this.smallUri;
	}

	public void setSmallUri(String smallUri) {
		this.smallUri = smallUri;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}