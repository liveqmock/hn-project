package hn.travel.persist.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the scenic_ticket database table.
 * 
 */
@Entity
@Table(name = "scenic_ticket")
public class ScenicTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	@Column(name = "pay_type")
	private Integer payType;

	private Integer type;

	@Column(name = "agreement_id")
	private Long agreementId;

	@Column(name = "scenic_id")
	private Long scenicId;

	private Integer status;

	/**
	 * 非表字段
	 */
	@Transient
	private String agreement;
	@Transient
	private Ticket ticket;

	public ScenicTicket() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Long agreementId) {
		this.agreementId = agreementId;
	}

	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}