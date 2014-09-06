package hn.travel.persist.entity.album;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "album")
public class Album extends IdEntity{
	
    /**
     *评论对象ID
     */
    private Long objectId;
    /**
     *名称
     */
    private String name;
    /**
     *说明
     */
    private String description;
    /**
     *原图地址
     */
    private String originalPath;
    /**
     *大图地址
     */
    private String largePath;
    /**
     *中图地址
     */
    private String middlePath;
    /**
     *小图地址
     */
    private String smallPath;
    /**
     *类型
     */
    private Byte albumType;
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
    public String getOriginalPath(){ 
        return this.originalPath;
    }
    public void setOriginalPath(String originalPath){ 
        this.originalPath=originalPath;
    }
    public String getLargePath(){ 
        return this.largePath;
    }
    public void setLargePath(String largePath){ 
        this.largePath=largePath;
    }
    public String getMiddlePath(){ 
        return this.middlePath;
    }
    public void setMiddlePath(String middlePath){ 
        this.middlePath=middlePath;
    }
    public String getSmallPath(){ 
        return this.smallPath;
    }
    public void setSmallPath(String smallPath){ 
        this.smallPath=smallPath;
    }
    public Byte getAlbumType(){ 
        return this.albumType;
    }
    public void setAlbumType(Byte albumType){ 
        this.albumType=albumType;
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
        return "objectId:"+objectId+",name:"+name+",description:"+description+",originalPath:"+originalPath+",largePath:"+largePath+",middlePath:"+middlePath+",smallPath:"+smallPath+",albumType:"+albumType+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state ;   
    }

}
