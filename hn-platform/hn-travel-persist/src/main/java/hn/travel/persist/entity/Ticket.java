package hn.travel.persist.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional one-to-one association to Itinerary
	@OneToOne(mappedBy="ticket")
	private Itinerary itinerary;

	//bi-directional one-to-one association to ScenicTicket
	@OneToOne(mappedBy="ticket")
	private ScenicTicket scenicTicket;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="refund_id")
	private BlobData blobData1;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="cost_id")
	private BlobData blobData2;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="notice_id")
	private BlobData blobData3;

	//bi-directional many-to-one association to TicketKind
	@OneToMany(mappedBy="ticket")
	private List<TicketKind> ticketKinds;

	public Ticket() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Itinerary getItinerary() {
		return this.itinerary;
	}

	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}

	public ScenicTicket getScenicTicket() {
		return this.scenicTicket;
	}

	public void setScenicTicket(ScenicTicket scenicTicket) {
		this.scenicTicket = scenicTicket;
	}

	public BlobData getBlobData1() {
		return this.blobData1;
	}

	public void setBlobData1(BlobData blobData1) {
		this.blobData1 = blobData1;
	}

	public BlobData getBlobData2() {
		return this.blobData2;
	}

	public void setBlobData2(BlobData blobData2) {
		this.blobData2 = blobData2;
	}

	public BlobData getBlobData3() {
		return this.blobData3;
	}

	public void setBlobData3(BlobData blobData3) {
		this.blobData3 = blobData3;
	}

	public List<TicketKind> getTicketKinds() {
		return this.ticketKinds;
	}

	public void setTicketKinds(List<TicketKind> ticketKinds) {
		this.ticketKinds = ticketKinds;
	}

	public TicketKind addTicketKind(TicketKind ticketKind) {
		getTicketKinds().add(ticketKind);
		ticketKind.setTicket(this);

		return ticketKind;
	}

	public TicketKind removeTicketKind(TicketKind ticketKind) {
		getTicketKinds().remove(ticketKind);
		ticketKind.setTicket(null);

		return ticketKind;
	}

}