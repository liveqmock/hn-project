package hn.travel.persist.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ticket_kind database table.
 * 
 */
@Entity
@Table(name="ticket_kind")
public class TicketKind implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private String desciption;

	@Column(name="market_price")
	private double marketPrice;

	private String name;

	@Column(name="now_price")
	private double nowPrice;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional many-to-one association to DatePrice
	@OneToMany(mappedBy="ticketKind")
	private List<DatePrice> datePrices;

	//bi-directional many-to-one association to Ticket
	@ManyToOne
	private Ticket ticket;

	public TicketKind() {
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

	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(double nowPrice) {
		this.nowPrice = nowPrice;
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

	public List<DatePrice> getDatePrices() {
		return this.datePrices;
	}

	public void setDatePrices(List<DatePrice> datePrices) {
		this.datePrices = datePrices;
	}

	public DatePrice addDatePrice(DatePrice datePrice) {
		getDatePrices().add(datePrice);
		datePrice.setTicketKind(this);

		return datePrice;
	}

	public DatePrice removeDatePrice(DatePrice datePrice) {
		getDatePrices().remove(datePrice);
		datePrice.setTicketKind(null);

		return datePrice;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}