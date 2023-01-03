package com.ruoyi.logistics.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
/**
 * 物流信息对象 na_logistics
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
public class NaLogistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流信息ID */
    private Long logiId;

    /** 物流状态 */
    @Excel(name = "物流状态")
    private Integer logiStatus;

    /** 物流人员ID */
    @Excel(name = "物流人员ID")
    private Long deliveryId;

    /** 检测点ID */
    @Excel(name = "检测点ID")
    private Long pointId;

    /** 检测点交接时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检测点交接时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logiPointTime;

    /** 实验室ID */
    @Excel(name = "实验室ID")
    private Long laboratoryId;

    /** 实验室交接时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实验室交接时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logiLabTime;

    /** 核酸信息信息 */
    private List<NucleicAcid> nucleicAcidList;

    public void setLogiId(Long logiId) 
    {
        this.logiId = logiId;
    }

    public Long getLogiId() 
    {
        return logiId;
    }
    public void setLogiStatus(Integer logiStatus) 
    {
        this.logiStatus = logiStatus;
    }

    public Integer getLogiStatus() 
    {
        return logiStatus;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setLogiPointTime(Date logiPointTime) 
    {
        this.logiPointTime = logiPointTime;
    }

    public Date getLogiPointTime() 
    {
        return logiPointTime;
    }
    public void setLaboratoryId(Long laboratoryId) 
    {
        this.laboratoryId = laboratoryId;
    }

    public Long getLaboratoryId() 
    {
        return laboratoryId;
    }
    public void setLogiLabTime(Date logiLabTime) 
    {
        this.logiLabTime = logiLabTime;
    }

    public Date getLogiLabTime() 
    {
        return logiLabTime;
    }

    public List<NucleicAcid> getNucleicAcidList()
    {
        return nucleicAcidList;
    }

    public void setNucleicAcidList(List<NucleicAcid> nucleicAcidList)
    {
        this.nucleicAcidList = nucleicAcidList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("logiId", getLogiId())
            .append("logiStatus", getLogiStatus())
            .append("deliveryId", getDeliveryId())
            .append("pointId", getPointId())
            .append("logiPointTime", getLogiPointTime())
            .append("laboratoryId", getLaboratoryId())
            .append("logiLabTime", getLogiLabTime())
            .append("nucleicAcidList", getNucleicAcidList())
            .toString();
    }
}
