package hn.travel.persist.entity.travelgroup;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "travelGroup")
public class TravelGroup extends IdEntity{
	
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
     *起始省编号
     */
    private Long provinceNumStart;
    /**
     *起始市编号
     */
    private Long cityNumStart;
    /**
     *起始区县编号
     */
    private Long townshipNumStart;
    /**
     *目的省编号
     */
    private Long provinceNumEnd;
    /**
     *目的市编号
     */
    private Long cityNumEnd;
    /**
     *目的区县编号
     */
    private Long townshipNumEnd;
    /**
     *简介
     */
    private String description;
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
    public Long getProvinceNumStart(){ 
        return this.provinceNumStart;
    }
    public void setProvinceNumStart(Long provinceNumStart){ 
        this.provinceNumStart=provinceNumStart;
    }
    public Long getCityNumStart(){ 
        return this.cityNumStart;
    }
    public void setCityNumStart(Long cityNumStart){ 
        this.cityNumStart=cityNumStart;
    }
    public Long getTownshipNumStart(){ 
        return this.townshipNumStart;
    }
    public void setTownshipNumStart(Long townshipNumStart){ 
        this.townshipNumStart=townshipNumStart;
    }
    public Long getProvinceNumEnd(){ 
        return this.provinceNumEnd;
    }
    public void setProvinceNumEnd(Long provinceNumEnd){ 
        this.provinceNumEnd=provinceNumEnd;
    }
    public Long getCityNumEnd(){ 
        return this.cityNumEnd;
    }
    public void setCityNumEnd(Long cityNumEnd){ 
        this.cityNumEnd=cityNumEnd;
    }
    public Long getTownshipNumEnd(){ 
        return this.townshipNumEnd;
    }
    public void setTownshipNumEnd(Long townshipNumEnd){ 
        this.townshipNumEnd=townshipNumEnd;
    }
    public String getDescription(){ 
        return this.description;
    }
    public void setDescription(String description){ 
        this.description=description;
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
        return "name:"+name+",priceMarket:"+priceMarket+",priceNow:"+priceNow+",payType:"+payType+",priceDescription:"+priceDescription+",activity:"+activity+",provinceNumStart:"+provinceNumStart+",cityNumStart:"+cityNumStart+",townshipNumStart:"+townshipNumStart+",provinceNumEnd:"+provinceNumEnd+",cityNumEnd:"+cityNumEnd+",townshipNumEnd:"+townshipNumEnd+",description:"+description+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;    
    }

}
