package com.ruoyi.arrangement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 任务与医护人员关联对象 na_arr_medical
 * 
 * @author ruoyi
 * @date 2022-12-27
 */
public class NaArrMedical extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long arrId;

    /** 医护人员ID */
    private Long medId;

    public void setArrId(Long arrId) 
    {
        this.arrId = arrId;
    }

    public Long getArrId() 
    {
        return arrId;
    }
    public void setMedId(Long medId) 
    {
        this.medId = medId;
    }

    public Long getMedId() 
    {
        return medId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("arrId", getArrId())
            .append("medId", getMedId())
            .toString();
    }
}
