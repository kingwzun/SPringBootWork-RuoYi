package com.ruoyi.nucleicAcid.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 核酸信息对象 nucleic_acid
 * 
 * @author ruoyi
 * @date 2022-11-23
 */
public class NucleicAcid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 核酸信息编号 */
    private Long naId;

    /** 核酸时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核酸时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date naTime;

    /** 核酸结果 */
    @Excel(name = "核酸结果")
    private Long naResult;

    /** 核酸区域ID */
    private Long deptId;

    /** 核酸区域 */
    @Excel(name = "核酸区域")
    private String deptName;

    /** 核酸类型 */
    @Excel(name = "核酸类型")
    private Long naType;

    /** 信息人员对照表信息 */
    private List<NaPersonnel> naPersonnelList;

    public void setNaId(Long naId) 
    {
        this.naId = naId;
    }

    public Long getNaId() 
    {
        return naId;
    }
    public void setNaTime(Date naTime) 
    {
        this.naTime = naTime;
    }

    public Date getNaTime() 
    {
        return naTime;
    }
    public void setNaResult(Long naResult) 
    {
        this.naResult = naResult;
    }

    public Long getNaResult() 
    {
        return naResult;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setNaType(Long naType) 
    {
        this.naType = naType;
    }

    public Long getNaType() 
    {
        return naType;
    }

    public List<NaPersonnel> getNaPersonnelList()
    {
        return naPersonnelList;
    }

    public void setNaPersonnelList(List<NaPersonnel> naPersonnelList)
    {
        this.naPersonnelList = naPersonnelList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("naId", getNaId())
            .append("naTime", getNaTime())
            .append("naResult", getNaResult())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("naType", getNaType())
            .append("naPersonnelList", getNaPersonnelList())
            .toString();
    }
}
