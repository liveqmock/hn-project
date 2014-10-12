package hn.travel.persist.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	@ManyToOne
	@JoinColumn(name = "refund_id")
	private BlobData refund;

	@ManyToOne
	@JoinColumn(name = "cost_id")
	private BlobData cost;

	@ManyToOne
	@JoinColumn(name = "notice_id")
	private BlobData notice;

	public Ticket() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BlobData getRefund() {
		return refund;
	}

	public void setRefund(BlobData refund) {
		this.refund = refund;
	}

	public BlobData getCost() {
		return cost;
	}

	public void setCost(BlobData cost) {
		this.cost = cost;
	}

	public BlobData getNotice() {
		return notice;
	}

	public void setNotice(BlobData notice) {
		this.notice = notice;
	}
}