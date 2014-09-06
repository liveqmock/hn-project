package hn.travel.persist.entity.hotelroom;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "hotelRoom")
public class HotelRoom extends IdEntity{
	
    /**
     *酒店ID
     */
    private Long hotelId;
    /**
     *房型分类ID
     */
    private Long hotelRoomCategoryId;
    /**
     *名称
     */
    private String name;
    /**
     *床型
     */
    private Byte bedType;
    /**
     *早餐
     */
    private String breakfast;
    /**
     *宽带
     */
    private String network;
    /**
     *原价
     */
    private Double priceMarket;
    /**
     *现价
     */
    private Double priceNow;
    /**
     *总数量
     */
    private Integer coount;
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

	
    public Long getHotelId(){ 
        return this.hotelId;
    }
    public void setHotelId(Long hotelId){ 
        this.hotelId=hotelId;
    }
    public Long getHotelRoomCategoryId(){ 
        return this.hotelRoomCategoryId;
    }
    public void setHotelRoomCategoryId(Long hotelRoomCategoryId){ 
        this.hotelRoomCategoryId=hotelRoomCategoryId;
    }
    public String getName(){ 
        return this.name;
    }
    public void setName(String name){ 
        this.name=name;
    }
    public Byte getBedType(){ 
        return this.bedType;
    }
    public void setBedType(Byte bedType){ 
        this.bedType=bedType;
    }
    public String getBreakfast(){ 
        return this.breakfast;
    }
    public void setBreakfast(String breakfast){ 
        this.breakfast=breakfast;
    }
    public String getNetwork(){ 
        return this.network;
    }
    public void setNetwork(String network){ 
        this.network=network;
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
    public Integer getCoount(){ 
        return this.coount;
    }
    public void setCoount(Integer coount){ 
        this.coount=coount;
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
        return "hotelId:"+hotelId+",hotelRoomCategoryId:"+hotelRoomCategoryId+",name:"+name+",bedType:"+bedType+",breakfast:"+breakfast+",network:"+network+",priceMarket:"+priceMarket+",priceNow:"+priceNow+",coount:"+coount+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
