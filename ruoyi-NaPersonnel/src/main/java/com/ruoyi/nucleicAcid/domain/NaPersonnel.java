package com.ruoyi.nucleicAcid.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 信息人员对照表对象 na_personnel
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public class NaPersonnel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 对照编号 */
    private Long naPerId;

    /** 核酸信息编号 */
    @Excel(name = "核酸信息编号")
    private Long naId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userId;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String perIdentityType;

    /** 证件号 */
    @Excel(name = "证件号")
    private String perIdentity;

    public void setNaPerId(Long naPerId) 
    {
        this.naPerId = naPerId;
    }

    public Long getNaPerId() 
    {
        return naPerId;
    }
    public void setNaId(Long naId) 
    {
        this.naId = naId;
    }

    public Long getNaId() 
    {
        return naId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setPerIdentityType(String perIdentityType) 
    {
        this.perIdentityType = perIdentityType;
    }

    public String getPerIdentityType() 
    {
        return perIdentityType;
    }
    public void setPerIdentity(String perIdentity) 
    {
        this.perIdentity = perIdentity;
    }

    public String getPerIdentity() 
    {
        return perIdentity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("naPerId", getNaPerId())
            .append("naId", getNaId())
            .append("userId", getUserId())
            .append("perIdentityType", getPerIdentityType())
            .append("perIdentity", getPerIdentity())
            .toString();
    }
}
