package hn.travel.persist.entity.travelself;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "travelSelf")
public class TravelSelf extends IdEntity{
	
    /**
     *名称
     */
    private String name;
    /**
     *市场价
     */
    private Double priceMarket;
    /**
     *现价
     */
    private Double priceNow;
    /**
     *支付方式
     */
    private Byte payType;
    /**
     *起价说明
     */
    private String priceDescription;
    /**
     *优惠活动
     */
    private Byte activity;
    /**
     *目的省编号
     */
    private Long provinceNum;
    /**
     *目的市编号
     */
    private Long cityNum;
    /**
     *目的区县编号
     */
    private Long townshipNum;
    /**
     *简介
     */
    private String description;
    /**
     *是否景点
     */
    private Byte scenicFlag;
    /**
     *是否酒店
     */
    private Byte hotelFlag;
    /**
     *是否机票
     */
    private Byte airTicketFlag;
    /**
     *修改时间
     */
    private Date modifyTime;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *状态
     */
    private Byte state;

	
    public String getName(){ 
        return this.name;
    }
    public void setName(String name){ 
        this.name=name;
    }
    public Double getPriceMarket(){ 
        return this.priceMarket;
    }
    public void setPriceMarket(Double priceMarket){ 
        this.priceMarket=priceMarket;
    }
    public Double getPriceNow(){ 
        return this.priceNow;
    }
    public void setPriceNow(Double priceNow){ 
        this.priceNow=priceNow;
    }
    public Byte getPayType(){ 
        return this.payType;
    }
    public void setPayType(Byte payType){ 
        this.payType=payType;
    }
    public String getPriceDescription(){ 
        return this.priceDescription;
    }
    public void setPriceDescription(String priceDescription){ 
        this.priceDescription=priceDescription;
    }
    public Byte getActivity(){ 
        return this.activity;
    }
    public void setActivity(Byte activity){ 
        this.activity=activity;
    }
    public Long getProvinceNum(){ 
        return this.provinceNum;
    }
    public void setProvinceNum(Long provinceNum){ 
        this.provinceNum=provinceNum;
    }
    public Long getCityNum(){ 
        return this.cityNum;
    }
    public void setCityNum(Long cityNum){ 
        this.cityNum=cityNum;
    }
    public Long getTownshipNum(){ 
        return this.townshipNum;
    }
    public void setTownshipNum(Long townshipNum){ 
        this.townshipNum=townshipNum;
    }
    public String getDescription(){ 
        return this.description;
    }
    public void setDescription(String description){ 
        this.description=description;
    }
    public Byte getScenicFlag(){ 
        return this.scenicFlag;
    }
    public void setScenicFlag(Byte scenicFlag){ 
        this.scenicFlag=scenicFlag;
    }
    public Byte getHotelFlag(){ 
        return this.hotelFlag;
    }
    public void setHotelFlag(Byte hotelFlag){ 
        this.hotelFlag=hotelFlag;
    }
    public Byte getAirTicketFlag(){ 
        return this.airTicketFlag;
    }
    public void setAirTicketFlag(Byte airTicketFlag){ 
        this.airTicketFlag=airTicketFlag;
    }
    public Date getModifyTime(){ 
        return this.modifyTime;
    }
    public void setModifyTime(Date modifyTime){ 
        this.modifyTime=modifyTime;
    }
    public Date getCreateTime(){ 
        return this.createTime;
    }
    public void setCreateTime(Date createTime){ 
        this.createTime=createTime;
    }
    public Byte getState(){ 
        return this.state;
    }
    public void setState(Byte state){ 
        this.state=state;
    }

	
    @Override
    public String toString(){
        return "name:"+name+",priceMarket:"+priceMarket+",priceNow:"+priceNow+",payType:"+payType+",priceDescription:"+priceDescription+",activity:"+activity+",provinceNum:"+provinceNum+",cityNum:"+cityNum+",townshipNum:"+townshipNum+",description:"+description+",scenicFlag:"+scenicFlag+",hotelFlag:"+hotelFlag+",airTicketFlag:"+airTicketFlag+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;  
    }

}
