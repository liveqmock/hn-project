package hn.travel.persist.entity.hotelroomdetail;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "hotelRoomDetail")
public class HotelRoomDetail extends IdEntity{
	
    /**
     *酒店ID
     */
    private Long hotelId;
    /**
     *房型ID
     */
    private Long hotelRoomId;
    /**
     *日期
     */
    private Date roomTime;
    /**
     *总数量
     */
    private Integer countTotal;
    /**
     *已经订购数量
     */
    private Integer countSale;
    /**
     *原价
     */
    private Double priceMarket;
    /**
     *现价
     */
    private Double priceNow;
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
    public Long getHotelRoomId(){ 
        return this.hotelRoomId;
    }
    public void setHotelRoomId(Long hotelRoomId){ 
        this.hotelRoomId=hotelRoomId;
    }
    public Date getRoomTime(){ 
        return this.roomTime;
    }
    public void setRoomTime(Date roomTime){ 
        this.roomTime=roomTime;
    }
    public Integer getCountTotal(){ 
        return this.countTotal;
    }
    public void setCountTotal(Integer countTotal){ 
        this.countTotal=countTotal;
    }
    public Integer getCountSale(){ 
        return this.countSale;
    }
    public void setCountSale(Integer countSale){ 
        this.countSale=countSale;
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
        return "hotelId:"+hotelId+",hotelRoomId:"+hotelRoomId+",roomTime:"+roomTime+",countTotal:"+countTotal+",countSale:"+countSale+",priceMarket:"+priceMarket+",priceNow:"+priceNow+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
