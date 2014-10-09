package hn.travel.persist.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the date_price database table.
 * 
 */
@Entity
@Table(name="date_price")
public class DatePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="market_price")
	private double marketPrice;

	@Column(name="now_price")
	private double nowPrice;

	@Temporal(TemporalType.DATE)
	private Date pdate;

	@Column(name="remain_num")
	private int remainNum;

	@Column(name="total_num")
	private int totalNum;

	//bi-directional many-to-one association to TicketKind
	@ManyToOne
	@JoinColumn(name="kind_id")
	private TicketKind ticketKind;

	public DatePrice() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public double getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Date getPdate() {
		return this.pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public int getRemainNum() {
		return this.remainNum;
	}

	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}

	public int getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public TicketKind getTicketKind() {
		return this.ticketKind;
	}

	public void setTicketKind(TicketKind ticketKind) {
		this.ticketKind = ticketKind;
	}

}