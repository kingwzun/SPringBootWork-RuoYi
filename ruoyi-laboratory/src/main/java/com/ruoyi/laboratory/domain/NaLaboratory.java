package com.ruoyi.laboratory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 实验室管理对象 na_laboratory
 * 
 * @author ruoyi
 * @date 2022-12-25
 */
public class NaLaboratory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 实验室ID */
    private Long labId;

    /** 实验室名称 */
    @Excel(name = "实验室名称")
    private String labName;

    /** 实验室位置 */
    @Excel(name = "实验室位置")
    private String labAddress;

    public void setLabId(Long labId) 
    {
        this.labId = labId;
    }

    public Long getLabId() 
    {
        return labId;
    }
    public void setLabName(String labName) 
    {
        this.labName = labName;
    }

    public String getLabName() 
    {
        return labName;
    }
    public void setLabAddress(String labAddress) 
    {
        this.labAddress = labAddress;
    }

    public String getLabAddress() 
    {
        return labAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("labId", getLabId())
            .append("labName", getLabName())
            .append("labAddress", getLabAddress())
            .toString();
    }
}
