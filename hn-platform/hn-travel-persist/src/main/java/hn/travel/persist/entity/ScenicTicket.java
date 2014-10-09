package hn.travel.persist.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the scenic_ticket database table.
 * 
 */
@Entity
@Table(name="scenic_ticket")
public class ScenicTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name="pay_type")
	private int payType;

	private int type;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="agreement_id")
	private BlobData blobData;

	//bi-directional one-to-one association to Ticket
	@OneToOne
	@JoinColumn(name="id")
	private Ticket ticket;

	//bi-directional many-to-one association to Scenic
	@ManyToOne
	private Scenic scenic;

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

	public int getPayType() {
		return this.payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BlobData getBlobData() {
		return this.blobData;
	}

	public void setBlobData(BlobData blobData) {
		this.blobData = blobData;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Scenic getScenic() {
		return this.scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

}