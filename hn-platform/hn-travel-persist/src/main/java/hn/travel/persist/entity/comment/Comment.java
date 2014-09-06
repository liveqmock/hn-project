package hn.travel.persist.entity.comment;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "comment")
public class Comment extends IdEntity{
	
    /**
     *评论对象ID
     */
    private Long objectId;
    /**
     *评论内容
     */
    private String content;
    /**
     *属性1
     */
    private Integer paramOne;
    /**
     *属性2
     */
    private Integer paramTwo;
    /**
     *属性3
     */
    private Integer paramThree;
    /**
     *属性4
     */
    private Integer paramFour;
    /**
     *类型
     */
    private Byte commentType;
    /**
     *用户ID
     */
    private Long userId;
    /**
     *用户名称
     */
    private String userName;
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

	
    public Long getObjectId(){ 
        return this.objectId;
    }
    public void setObjectId(Long objectId){ 
        this.objectId=objectId;
    }
    public String getContent(){ 
        return this.content;
    }
    public void setContent(String content){ 
        this.content=content;
    }
    public Integer getParamOne(){ 
        return this.paramOne;
    }
    public void setParamOne(Integer paramOne){ 
        this.paramOne=paramOne;
    }
    public Integer getParamTwo(){ 
        return this.paramTwo;
    }
    public void setParamTwo(Integer paramTwo){ 
        this.paramTwo=paramTwo;
    }
    public Integer getParamThree(){ 
        return this.paramThree;
    }
    public void setParamThree(Integer paramThree){ 
        this.paramThree=paramThree;
    }
    public Integer getParamFour(){ 
        return this.paramFour;
    }
    public void setParamFour(Integer paramFour){ 
        this.paramFour=paramFour;
    }
    public Byte getCommentType(){ 
        return this.commentType;
    }
    public void setCommentType(Byte commentType){ 
        this.commentType=commentType;
    }
    public Long getUserId(){ 
        return this.userId;
    }
    public void setUserId(Long userId){ 
        this.userId=userId;
    }
    public String getUserName(){ 
        return this.userName;
    }
    public void setUserName(String userName){ 
        this.userName=userName;
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
        return "objectId:"+objectId+",content:"+content+",paramOne:"+paramOne+",paramTwo:"+paramTwo+",paramThree:"+paramThree+",paramFour:"+paramFour+",commentType:"+commentType+",userId:"+userId+",userName:"+userName+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
