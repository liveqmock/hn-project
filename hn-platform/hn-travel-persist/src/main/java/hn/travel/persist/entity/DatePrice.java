package hn.travel.persist.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the date_price database table.
 * 
 */
@Entity
@Table(name = "date_price")
public class DatePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "market_price")
	private Double marketPrice;

	@Column(name = "now_price")
	private Double nowPrice;

	@Temporal(TemporalType.DATE)
	private Date pdate;

	@Column(name = "remain_num")
	private Integer remainNum;

	@Column(name = "total_num")
	private Integer totalNum;

	@Column(name = "kind_id")
	private Long kindId;

	/**
	 * 非表字段
	 */
	@Transient
	private TicketKind ticketKind;

	public DatePrice() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(Double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Date getPdate() {
		return this.pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getRemainNum() {
		return this.remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Long getKindId() {
		return kindId;
	}

	public void setKindId(Long kindId) {
		this.kindId = kindId;
	}

	public TicketKind getTicketKind() {
		return this.ticketKind;
	}

	public void setTicketKind(TicketKind ticketKind) {
		this.ticketKind = ticketKind;
	}

}