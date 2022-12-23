package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.StuStudentMapper;
import com.ruoyi.student.domain.StuStudent;
import com.ruoyi.student.service.IStuStudentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
@Service
public class StuStudentServiceImpl implements IStuStudentService 
{
    @Autowired
    private StuStudentMapper stuStudentMapper;

    /**
     * 查询学生信息
     * 
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    @Override
    public StuStudent selectStuStudentByStudentId(Long studentId)
    {
        return stuStudentMapper.selectStuStudentByStudentId(studentId);
    }

    /**
     * 查询学生信息列表
     * 
     * @param stuStudent 学生信息
     * @return 学生信息
     */
    @Override
    public List<StuStudent> selectStuStudentList(StuStudent stuStudent)
    {
        return stuStudentMapper.selectStuStudentList(stuStudent);
    }

    /**
     * 新增学生信息
     * 
     * @param stuStudent 学生信息
     * @return 结果
     */
    @Override
    public int insertStuStudent(StuStudent stuStudent)
    {
        return stuStudentMapper.insertStuStudent(stuStudent);
    }

    /**
     * 修改学生信息
     * 
     * @param stuStudent 学生信息
     * @return 结果
     */
    @Override
    public int updateStuStudent(StuStudent stuStudent)
    {
        return stuStudentMapper.updateStuStudent(stuStudent);
    }

    /**
     * 批量删除学生信息
     * 
     * @param studentIds 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteStuStudentByStudentIds(String studentIds)
    {
        return stuStudentMapper.deleteStuStudentByStudentIds(Convert.toStrArray(studentIds));
    }

    /**
     * 删除学生信息信息
     * 
     * @param studentId 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteStuStudentByStudentId(Long studentId)
    {
        return stuStudentMapper.deleteStuStudentByStudentId(studentId);
    }
}
