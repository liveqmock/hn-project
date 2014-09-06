package hn.travel.persist.entity.scenicticket;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "scenicTicket")
public class ScenicTicket extends IdEntity{
	
    /**
     *景点ID
     */
    private Long scenicId;
    /**
     *票名称
     */
    private String name;
    /**
     *介绍
     */
    private String description;
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
     *类型
     */
    private Byte type;
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

	
    public Long getScenicId(){ 
        return this.scenicId;
    }
    public void setScenicId(Long scenicId){ 
        this.scenicId=scenicId;
    }
    public String getName(){ 
        return this.name;
    }
    public void setName(String name){ 
        this.name=name;
    }
    public String getDescription(){ 
        return this.description;
    }
    public void setDescription(String description){ 
        this.description=description;
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
    public Byte getType(){ 
        return this.type;
    }
    public void setType(Byte type){ 
        this.type=type;
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
        return "scenicId:"+scenicId+",name:"+name+",description:"+description+",priceMarket:"+priceMarket+",priceNow:"+priceNow+",payType:"+payType+",type:"+type+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
