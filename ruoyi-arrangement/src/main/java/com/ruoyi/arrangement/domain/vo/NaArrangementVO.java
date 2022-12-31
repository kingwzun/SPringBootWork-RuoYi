package com.ruoyi.arrangement.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 任务安排对象 na_arrangement
 * 
 * @author ruoyi
 * @date 2022-12-26
 */
@Data
public class NaArrangementVO extends BaseEntity
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
    @Excel(name = "检测点")
    private String pointName;

    /** 物流人员ID */
    @Excel(name = "物流人员")
    private String deliveryName;

    /** 实验室ID */
    @Excel(name = "实验室")
    private String laboratoryName;


}
