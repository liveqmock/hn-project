package hn.travel.persist.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the scenic database table.
 * 
 */
@Entity
@NamedQuery(name="Scenic.findAll", query="SELECT s FROM Scenic s")
public class Scenic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String address;

	@Column(name="area_code")
	private String areaCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="good_rate")
	private BigDecimal goodRate;

	@Column(name="img_uri")
	private String imgUri;

	@Column(name="in_time")
	private String inTime;

	private double latitude;

	private double longitude;

	@Column(name="low_price")
	private double lowPrice;

	private String name;

	private String services;

	private int status;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	//bi-directional many-to-many association to Itinerary
	@ManyToMany(mappedBy="scenics")
	private List<Itinerary> itineraries;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="traffic_id")
	private BlobData blobData1;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="notice_id")
	private BlobData blobData2;

	//bi-directional many-to-one association to BlobData
	@ManyToOne
	@JoinColumn(name="introduce_id")
	private BlobData blobData3;

	//bi-directional many-to-one association to ScenicTicket
	@OneToMany(mappedBy="scenic")
	private List<ScenicTicket> scenicTickets;

	public Scenic() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getGoodRate() {
		return this.goodRate;
	}

	public void setGoodRate(BigDecimal goodRate) {
		this.goodRate = goodRate;
	}

	public String getImgUri() {
		return this.imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	public String getInTime() {
		return this.inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServices() {
		return this.services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Itinerary> getItineraries() {
		return this.itineraries;
	}

	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
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

	public List<ScenicTicket> getScenicTickets() {
		return this.scenicTickets;
	}

	public void setScenicTickets(List<ScenicTicket> scenicTickets) {
		this.scenicTickets = scenicTickets;
	}

	public ScenicTicket addScenicTicket(ScenicTicket scenicTicket) {
		getScenicTickets().add(scenicTicket);
		scenicTicket.setScenic(this);

		return scenicTicket;
	}

	public ScenicTicket removeScenicTicket(ScenicTicket scenicTicket) {
		getScenicTickets().remove(scenicTicket);
		scenicTicket.setScenic(null);

		return scenicTicket;
	}

}