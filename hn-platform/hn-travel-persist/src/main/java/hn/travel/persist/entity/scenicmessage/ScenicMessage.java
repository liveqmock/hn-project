package hn.travel.persist.entity.scenicmessage;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "scenicMessage")
public class ScenicMessage extends IdEntity{
	
    /**
     *景点ID
     */
    private Long scenicId;
    /**
     *订票须知
     */
    private Long dataValueIdKnow;
    /**
     *景点介绍
     */
    private Long dataValueIdIntroduce;
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
    public Long getDataValueIdKnow(){ 
        return this.dataValueIdKnow;
    }
    public void setDataValueIdKnow(Long dataValueIdKnow){ 
        this.dataValueIdKnow=dataValueIdKnow;
    }
    public Long getDataValueIdIntroduce(){ 
        return this.dataValueIdIntroduce;
    }
    public void setDataValueIdIntroduce(Long dataValueIdIntroduce){ 
        this.dataValueIdIntroduce=dataValueIdIntroduce;
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
        return "scenicId:"+scenicId+",dataValueIdKnow:"+dataValueIdKnow+",dataValueIdIntroduce:"+dataValueIdIntroduce+",dataValueIdTraffic:"+dataValueIdTraffic+",dataValueIdNotice:"+dataValueIdNotice+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
