package com.ruoyi.stuprofile.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生档案3对象 stu_profile
 * 
 * @author ruoyi
 * @date 2022-11-19
 */
public class StuProfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private Long userId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String studentName;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date studentBirthday;

    /** 性别（0男 1女 2未知） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String studentSex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String studentPhone;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date studentAdmissionTime;

    /** 学院 */
    @Excel(name = "学院")
    private String studentCollege;

    /** 专业 */
    @Excel(name = "专业")
    private String studentSpecialized;

    /** 状态（0在读 1未在读） */
    @Excel(name = "状态", readConverterExp = "0=在读,1=未在读")
    private String studentStatus;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setStudentBirthday(Date studentBirthday) 
    {
        this.studentBirthday = studentBirthday;
    }

    public Date getStudentBirthday() 
    {
        return studentBirthday;
    }
    public void setStudentSex(String studentSex) 
    {
        this.studentSex = studentSex;
    }

    public String getStudentSex() 
    {
        return studentSex;
    }
    public void setStudentPhone(String studentPhone) 
    {
        this.studentPhone = studentPhone;
    }

    public String getStudentPhone() 
    {
        return studentPhone;
    }
    public void setStudentAdmissionTime(Date studentAdmissionTime) 
    {
        this.studentAdmissionTime = studentAdmissionTime;
    }

    public Date getStudentAdmissionTime() 
    {
        return studentAdmissionTime;
    }
    public void setStudentCollege(String studentCollege) 
    {
        this.studentCollege = studentCollege;
    }

    public String getStudentCollege() 
    {
        return studentCollege;
    }
    public void setStudentSpecialized(String studentSpecialized) 
    {
        this.studentSpecialized = studentSpecialized;
    }

    public String getStudentSpecialized() 
    {
        return studentSpecialized;
    }
    public void setStudentStatus(String studentStatus) 
    {
        this.studentStatus = studentStatus;
    }

    public String getStudentStatus() 
    {
        return studentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("studentName", getStudentName())
            .append("studentBirthday", getStudentBirthday())
            .append("studentSex", getStudentSex())
            .append("studentPhone", getStudentPhone())
            .append("studentAdmissionTime", getStudentAdmissionTime())
            .append("studentCollege", getStudentCollege())
            .append("studentSpecialized", getStudentSpecialized())
            .append("studentStatus", getStudentStatus())
            .toString();
    }
}
