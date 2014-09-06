package hn.travel.persist.entity.hotelmessage;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "hotelMessage")
public class HotelMessage extends IdEntity{
	
    /**
     *酒店ID
     */
    private Long hotelId;
    /**
     *设施服务
     */
    private Long dataValueIdService;
    /**
     *交通指南
     */
    private Long dataValueIdTraffic;
    /**
     *公告
     */
    private Long dataValueIdNotice;
    /**
     *修改时间
     */
    private Long modifyTime;
    /**
     *创建时间
     */
    private Long createTime;
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
    public Long getDataValueIdService(){ 
        return this.dataValueIdService;
    }
    public void setDataValueIdService(Long dataValueIdService){ 
        this.dataValueIdService=dataValueIdService;
    }
    public Long getDataValueIdTraffic(){ 
        return this.dataValueIdTraffic;
    }
    public void setDataValueIdTraffic(Long dataValueIdTraffic){ 
        this.dataValueIdTraffic=dataValueIdTraffic;
    }
    public Long getDataValueIdNotice(){ 
        return this.dataValueIdNotice;
    }
    public void setDataValueIdNotice(Long dataValueIdNotice){ 
        this.dataValueIdNotice=dataValueIdNotice;
    }
    public Long getModifyTime(){ 
        return this.modifyTime;
    }
    public void setModifyTime(Long modifyTime){ 
        this.modifyTime=modifyTime;
    }
    public Long getCreateTime(){ 
        return this.createTime;
    }
    public void setCreateTime(Long createTime){ 
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
        return "hotelId:"+hotelId+",dataValueIdService:"+dataValueIdService+",dataValueIdTraffic:"+dataValueIdTraffic+",dataValueIdNotice:"+dataValueIdNotice+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
