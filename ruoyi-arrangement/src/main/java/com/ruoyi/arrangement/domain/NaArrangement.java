package com.ruoyi.arrangement.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务安排对象 na_arrangement
 * 
 * @author ruoyi
 * @date 2022-12-26
 */
public class NaArrangement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long arrId;

    /** 检测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrTime;

    /** 检测区域 */
    @Excel(name = "检测区域")
    private String arrAddress;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String arrStatus;

    /** 检测点ID */
    @Excel(name = "检测点ID")
    private Long pointId;

    /** 物流人员ID */
    @Excel(name = "物流人员ID")
    private Long deliveryId;

    /** 实验室ID */
    @Excel(name = "实验室ID")
    private Long laboratoryId;

    public void setArrId(Long arrId) 
    {
        this.arrId = arrId;
    }

    public Long getArrId() 
    {
        return arrId;
    }
    public void setArrTime(Date arrTime) 
    {
        this.arrTime = arrTime;
    }

    public Date getArrTime() 
    {
        return arrTime;
    }
    public void setArrAddress(String arrAddress) 
    {
        this.arrAddress = arrAddress;
    }

    public String getArrAddress() 
    {
        return arrAddress;
    }
    public void setArrStatus(String arrStatus) 
    {
        this.arrStatus = arrStatus;
    }

    public String getArrStatus() 
    {
        return arrStatus;
    }
    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setLaboratoryId(Long laboratoryId) 
    {
        this.laboratoryId = laboratoryId;
    }

    public Long getLaboratoryId() 
    {
        return laboratoryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("arrId", getArrId())
            .append("arrTime", getArrTime())
            .append("arrAddress", getArrAddress())
            .append("arrStatus", getArrStatus())
            .append("pointId", getPointId())
            .append("deliveryId", getDeliveryId())
            .append("laboratoryId", getLaboratoryId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
