package hn.travel.persist.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * The persistent class for the itinerary database table.
 * 
 */
@Entity
public class Itinerary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "end_place")
	private String endPlace;

	@Column(name = "img_uri")
	private String imgUri;

	@Column(name = "issue_place")
	private String issuePlace;

	@Column(name = "market_price")
	private Double marketPrice;

	private String name;

	@Column(name = "now_price")
	private Double nowPrice;

	@Column(name = "pay_type")
	private Integer payType;

	@Column(name = "price_instructio")
	private String priceInstructio;

	private String title;

	private Integer type;

	private Integer status;

	// bi-directional one-to-one association to Ticket
	@OneToOne
	@JoinColumn(name = "id")
	private Ticket ticket;

	// bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name = "feature_id")
	private BlobData blobData1;

	// bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name = "explain_id")
	private BlobData blobData2;

	// bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name = "prompt_id")
	private BlobData blobData3;

	// bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name = "traffic_id")
	private BlobData blobData4;

	// bi-directional many-to-many association to Scenic
	@ManyToMany
	@JoinTable(name = "itinerary_scenic", joinColumns = { @JoinColumn(name = "itinerary_id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
	private List<Scenic> scenics;

	public Itinerary() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndPlace() {
		return this.endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public String getImgUri() {
		return this.imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	public String getIssuePlace() {
		return this.issuePlace;
	}

	public void setIssuePlace(String issuePlace) {
		this.issuePlace = issuePlace;
	}

	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(Double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPriceInstructio() {
		return this.priceInstructio;
	}

	public void setPriceInstructio(String priceInstructio) {
		this.priceInstructio = priceInstructio;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
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

	public BlobData getBlobData4() {
		return this.blobData4;
	}

	public void setBlobData4(BlobData blobData4) {
		this.blobData4 = blobData4;
	}

	public List<Scenic> getScenics() {
		return this.scenics;
	}

	public void setScenics(List<Scenic> scenics) {
		this.scenics = scenics;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}