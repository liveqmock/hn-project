package hn.travel.persist.entity.travelscenic;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "travelScenic")
public class TravelScenic extends IdEntity{
	
    /**
     *自由行，跟团游ID
     */
    private Long travelId;
    /**
     *景点ID
     */
    private Long scenicId;
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

	
    public Long getTravelId(){ 
        return this.travelId;
    }
    public void setTravelId(Long travelId){ 
        this.travelId=travelId;
    }
    public Long getScenicId(){ 
        return this.scenicId;
    }
    public void setScenicId(Long scenicId){ 
        this.scenicId=scenicId;
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
        return "travelId:"+travelId+",scenicId:"+scenicId+",type:"+type+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
