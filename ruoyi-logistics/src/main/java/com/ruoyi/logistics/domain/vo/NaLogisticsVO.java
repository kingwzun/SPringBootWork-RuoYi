package com.ruoyi.logistics.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 物流信息对象 na_logistics
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
@Data
public class NaLogisticsVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流信息ID */
    private Long logiId;

    /** 物流状态 */
    @Excel(name = "物流状态")
    private Integer logiStatus;

    /** 物流人员 */
    @Excel(name = "物流人员")
    private String deliveryName;

    /** 检测点 */
    @Excel(name = "检测点")
    private String pointName;

    /** 检测点交接时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "检测点交接时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logiPointTime;

    /** 实验室 */
    @Excel(name = "实验室")
    private String laboratoryName;

    /** 实验室交接时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实验室交接时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date logiLabTime;

    /** 核酸信息信息 */
    private List<NucleicAcid> nucleicAcidList;

}
