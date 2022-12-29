package com.ruoyi.point.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检测点管理对象 na_point
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
public class NaPoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检测点ID */
    private Long pointId;

    /** 检测点名称 */
    @Excel(name = "检测点名称")
    private String pointName;

    /** 检测点位置 */
    @Excel(name = "检测点位置")
    private String pointAddress;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setPointName(String pointName) 
    {
        this.pointName = pointName;
    }

    public String getPointName() 
    {
        return pointName;
    }
    public void setPointAddress(String pointAddress) 
    {
        this.pointAddress = pointAddress;
    }

    public String getPointAddress() 
    {
        return pointAddress;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pointId", getPointId())
            .append("pointName", getPointName())
            .append("pointAddress", getPointAddress())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
