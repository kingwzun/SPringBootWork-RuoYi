package com.ruoyi.dormitory.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宿舍对象 dormitory_manage
 *
 * @author ruoyi
 * @date 2022-11-19
 */
public class DormitoryManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 宿舍编号 */
    @Excel(name = "宿舍编号")
    private Long dormitoryNum;

    /** 宿舍类型 */
    @Excel(name = "宿舍类型")
    private String dormitoryType;

    /** 宿舍区域 */
    @Excel(name = "宿舍区域")
    private String dormitoryRegion;

    /** 宿舍长 */
    @Excel(name = "宿舍长")
    private String dormitoryManager;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 住宿生信息信息 */
    private List<DormitoryStumanage> dormitoryStumanageList;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDormitoryNum(Long dormitoryNum)
    {
        this.dormitoryNum = dormitoryNum;
    }

    public Long getDormitoryNum()
    {
        return dormitoryNum;
    }
    public void setDormitoryType(String dormitoryType)
    {
        this.dormitoryType = dormitoryType;
    }

    public String getDormitoryType()
    {
        return dormitoryType;
    }
    public void setDormitoryRegion(String dormitoryRegion)
    {
        this.dormitoryRegion = dormitoryRegion;
    }

    public String getDormitoryRegion()
    {
        return dormitoryRegion;
    }
    public void setDormitoryManager(String dormitoryManager)
    {
        this.dormitoryManager = dormitoryManager;
    }

    public String getDormitoryManager()
    {
        return dormitoryManager;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public List<DormitoryStumanage> getDormitoryStumanageList()
    {
        return dormitoryStumanageList;
    }

    public void setDormitoryStumanageList(List<DormitoryStumanage> dormitoryStumanageList)
    {
        this.dormitoryStumanageList = dormitoryStumanageList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("dormitoryNum", getDormitoryNum())
                .append("dormitoryType", getDormitoryType())
                .append("dormitoryRegion", getDormitoryRegion())
                .append("dormitoryManager", getDormitoryManager())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("dormitoryStumanageList", getDormitoryStumanageList())
                .toString();
    }
}
