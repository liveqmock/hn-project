package hn.travel.persist.entity.product;

import hn.travel.persist.entity.IdEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product extends IdEntity{
	
    /**
     *时间
     */
    private Date productTime;
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
     *是否促销
     */
    private Byte saleFlag;
    /**
     *优惠算法
     */
    private String method;
    /**
     *总数量
     */
    private Integer countTotal;
    /**
     *已购数量
     */
    private Integer countSale;
    /**
     *说明
     */
    private String description;
    /**
     *类型
     */
    private Byte productType;
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

	
    public Date getProductTime(){ 
        return this.productTime;
    }
    public void setProductTime(Date productTime){ 
        this.productTime=productTime;
    }
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
    public Byte getSaleFlag(){ 
        return this.saleFlag;
    }
    public void setSaleFlag(Byte saleFlag){ 
        this.saleFlag=saleFlag;
    }
    public String getMethod(){ 
        return this.method;
    }
    public void setMethod(String method){ 
        this.method=method;
    }
    public Integer getCountTotal(){ 
        return this.countTotal;
    }
    public void setCountTotal(Integer countTotal){ 
        this.countTotal=countTotal;
    }
    public Integer getCountSale(){ 
        return this.countSale;
    }
    public void setCountSale(Integer countSale){ 
        this.countSale=countSale;
    }
    public String getDescription(){ 
        return this.description;
    }
    public void setDescription(String description){ 
        this.description=description;
    }
    public Byte getProductType(){ 
        return this.productType;
    }
    public void setProductType(Byte productType){ 
        this.productType=productType;
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
        return "productTime:"+productTime+",name:"+name+",priceMarket:"+priceMarket+",priceNow:"+priceNow+",saleFlag:"+saleFlag+",method:"+method+",countTotal:"+countTotal+",countSale:"+countSale+",description:"+description+",productType:"+productType+",modifyTime:"+modifyTime+",createTime:"+createTime+",state:"+state;   
    }

}
