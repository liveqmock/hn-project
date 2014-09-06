package hn.travel.persist.entity.line;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "line")
public class Line extends IdEntity{
	
    /**
     *(自由行，跟团)ID
     */
    private Long travelId;
    /**
     *产品特色
     */
    private Long dataValueIdFeature;
    /**
     *行程说明
     */
    private Long dataValueIdDescription;
    /**
     *费用说明
     */
    private Long dataValueIdCost;
    /**
     *重要提示
     */
    private Long dataValueIdImportant;
    /**
     *目的地情报
     */
    private Long dataValueIdDestination;
    /**
     *公告
     */
    private Long dataValueIdNotice;
    /**
     *类型
     */
    private Byte lineType;
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

	
    public Long getTravelId(){ 
        return this.travelId;
    }
    public void setTravelId(Long travelId){ 
        this.travelId=travelId;
    }
    public Long getDataValueIdFeature(){ 
        return this.dataValueIdFeature;
    }
    public void setDataValueIdFeature(Long dataValueIdFeature){ 
        this.dataValueIdFeature=dataValueIdFeature;
    }
    public Long getDataValueIdDescription(){ 
        return this.dataValueIdDescription;
    }
    public void setDataValueIdDescription(Long dataValueIdDescription){ 
        this.dataValueIdDescription=dataValueIdDescription;
    }
    public Long getDataValueIdCost(){ 
        return this.dataValueIdCost;
    }
    public void setDataValueIdCost(Long dataValueIdCost){ 
        this.dataValueIdCost=dataValueIdCost;
    }
    public Long getDataValueIdImportant(){ 
        return this.dataValueIdImportant;
    }
    public void setDataValueIdImportant(Long dataValueIdImportant){ 
        this.dataValueIdImportant=dataValueIdImportant;
    }
    public Long getDataValueIdDestination(){ 
        return this.dataValueIdDestination;
    }
    public void setDataValueIdDestination(Long dataValueIdDestination){ 
        this.dataValueIdDestination=dataValueIdDestination;
    }
    public Long getDataValueIdNotice(){ 
        return this.dataValueIdNotice;
    }
    public void setDataValueIdNotice(Long dataValueIdNotice){ 
        this.dataValueIdNotice=dataValueIdNotice;
    }
    public Byte getLineType(){ 
        return this.lineType;
    }
    public void setLineType(Byte lineType){ 
        this.lineType=lineType;
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
        return "travelId:"+travelId+",dataValueIdFeature:"+dataValueIdFeature+",dataValueIdDescription:"+dataValueIdDescription+",dataValueIdCost:"+dataValueIdCost+",dataValueIdImportant:"+dataValueIdImportant+",dataValueIdDestination:"+dataValueIdDestination+",dataValueIdNotice:"+dataValueIdNotice+",lineType:"+lineType+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;    
    }

}
