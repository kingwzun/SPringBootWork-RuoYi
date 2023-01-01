package com.ruoyi.nucleicAcid.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 核酸信息对象 nucleic_acid
 *
 * @author ruoyi
 * @date 2022-12-31
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

    /** 混样方式(0混管，1单管) */
    @Excel(name = "混样方式(0混管，1单管)")
    private Integer naMixType;

    /** 核酸类型 */
    @Excel(name = "核酸类型")
    private Integer naType;

    /** 试管编号 */
    @Excel(name = "试管编号")
    private Long tubeId;

    /** 检测点ID */
    @Excel(name = "检测点ID")
    private Long pointId;

    /** 检测点名称 */
    @Excel(name = "检测点名称")
    private String pointName;

    /** 核酸结果(0未录入，1阴性，2阳性) */
    @Excel(name = "核酸结果(0未录入，1阴性，2阳性)")
    private Long naResult;

    /** 结果最后一次修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结果最后一次修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date naResultTime;

    /** 物流信息ID */
    @Excel(name = "物流信息ID")
    private Long logiId;

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
    public void setNaMixType(Integer naMixType)
    {
        this.naMixType = naMixType;
    }

    public Integer getNaMixType()
    {
        return naMixType;
    }
    public void setNaType(Integer naType)
    {
        this.naType = naType;
    }

    public Integer getNaType()
    {
        return naType;
    }
    public void setTubeId(Long tubeId)
    {
        this.tubeId = tubeId;
    }

    public Long getTubeId()
    {
        return tubeId;
    }
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
    public void setNaResult(Long naResult)
    {
        this.naResult = naResult;
    }

    public Long getNaResult()
    {
        return naResult;
    }
    public void setNaResultTime(Date naResultTime)
    {
        this.naResultTime = naResultTime;
    }

    public Date getNaResultTime()
    {
        return naResultTime;
    }
    public void setLogiId(Long logiId)
    {
        this.logiId = logiId;
    }

    public Long getLogiId()
    {
        return logiId;
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
                .append("naMixType", getNaMixType())
                .append("naType", getNaType())
                .append("tubeId", getTubeId())
                .append("pointId", getPointId())
                .append("pointName", getPointName())
                .append("naResult", getNaResult())
                .append("naResultTime", getNaResultTime())
                .append("logiId", getLogiId())
                .append("naPersonnelList", getNaPersonnelList())
                .toString();
    }
}
