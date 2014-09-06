package hn.travel.persist.entity.hotel;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "hotel")
public class Hotel extends IdEntity{
	
    /**
     *名称
     */
    private String name;
    /**
     *地址
     */
    private String address;
    /**
     *起始价格
     */
    private Double priceStart;
    /**
     *经度
     */
    private Double longitude;
    /**
     *纬度
     */
    private Double latitude;
    /**
     *省编号
     */
    private Long provinceNum;
    /**
     *市编号
     */
    private Long cityNum;
    /**
     *区县编号
     */
    private Long townshipNum;
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
    public String getAddress(){ 
        return this.address;
    }
    public void setAddress(String address){ 
        this.address=address;
    }
    public Double getPriceStart(){ 
        return this.priceStart;
    }
    public void setPriceStart(Double priceStart){ 
        this.priceStart=priceStart;
    }
    public Double getLongitude(){ 
        return this.longitude;
    }
    public void setLongitude(Double longitude){ 
        this.longitude=longitude;
    }
    public Double getLatitude(){ 
        return this.latitude;
    }
    public void setLatitude(Double latitude){ 
        this.latitude=latitude;
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
        return "name:"+name+",address:"+address+",priceStart:"+priceStart+",longitude:"+longitude+",latitude:"+latitude+",provinceNum:"+provinceNum+",cityNum:"+cityNum+",townshipNum:"+townshipNum+",description:"+description+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
