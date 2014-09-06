package hn.travel.persist.entity.hotelroomcategory;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "hotelRoomCategory")
public class HotelRoomCategory extends IdEntity{
	
    /**
     *酒店ID
     */
    private Long hotelId;
    /**
     *名称
     */
    private String name;
    /**
     *面积
     */
    private String area;
    /**
     *楼层
     */
    private String floor;
    /**
     *床型
     */
    private Byte bedType;
    /**
     *最多入住人数
     */
    private Integer peopleNum;
    /**
     *宽带
     */
    private String network;
    /**
     *描述
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

	
    public Long getHotelId(){ 
        return this.hotelId;
    }
    public void setHotelId(Long hotelId){ 
        this.hotelId=hotelId;
    }
    public String getName(){ 
        return this.name;
    }
    public void setName(String name){ 
        this.name=name;
    }
    public String getArea(){ 
        return this.area;
    }
    public void setArea(String area){ 
        this.area=area;
    }
    public String getFloor(){ 
        return this.floor;
    }
    public void setFloor(String floor){ 
        this.floor=floor;
    }
    public Byte getBedType(){ 
        return this.bedType;
    }
    public void setBedType(Byte bedType){ 
        this.bedType=bedType;
    }
    public Integer getPeopleNum(){ 
        return this.peopleNum;
    }
    public void setPeopleNum(Integer peopleNum){ 
        this.peopleNum=peopleNum;
    }
    public String getNetwork(){ 
        return this.network;
    }
    public void setNetwork(String network){ 
        this.network=network;
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
        return "hotelId:"+hotelId+",name:"+name+",area:"+area+",floor:"+floor+",bedType:"+bedType+",peopleNum:"+peopleNum+",network:"+network+",description:"+description+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;    
    }

}
