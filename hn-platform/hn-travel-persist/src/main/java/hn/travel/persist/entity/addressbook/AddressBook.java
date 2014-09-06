package hn.travel.persist.entity.addressbook;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "addressBook")
public class AddressBook extends IdEntity{
	
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
     *地址名称
     */
    private String name;
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
    public String getName(){ 
        return this.name;
    }
    public void setName(String name){ 
        this.name=name;
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
        return "provinceNum:"+provinceNum+",cityNum:"+cityNum+",townshipNum:"+townshipNum+",name:"+name+",type:"+type+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
