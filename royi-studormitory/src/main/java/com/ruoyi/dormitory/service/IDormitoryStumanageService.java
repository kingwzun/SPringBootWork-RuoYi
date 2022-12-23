package com.ruoyi.dormitory.service;

import java.util.List;
import com.ruoyi.dormitory.domain.DormitoryStumanage;

/**
 * 住宿生信息Service接口
 * 
 * @author ruoyi
 * @date 2022-11-19
 */
public interface IDormitoryStumanageService 
{
    /**
     * 查询住宿生信息
     * 
     * @param sid 住宿生信息主键
     * @return 住宿生信息
     */
    public DormitoryStumanage selectDormitoryStumanageBySid(Long sid);

    /**
     * 查询住宿生信息列表
     * 
     * @param dormitoryStumanage 住宿生信息
     * @return 住宿生信息集合
     */
    public List<DormitoryStumanage> selectDormitoryStumanageList(DormitoryStumanage dormitoryStumanage);

    /**
     * 新增住宿生信息
     * 
     * @param dormitoryStumanage 住宿生信息
     * @return 结果
     */
    public int insertDormitoryStumanage(DormitoryStumanage dormitoryStumanage);

    /**
     * 修改住宿生信息
     * 
     * @param dormitoryStumanage 住宿生信息
     * @return 结果
     */
    public int updateDormitoryStumanage(DormitoryStumanage dormitoryStumanage);

    /**
     * 批量删除住宿生信息
     * 
     * @param sids 需要删除的住宿生信息主键集合
     * @return 结果
     */
    public int deleteDormitoryStumanageBySids(String sids);

    /**
     * 删除住宿生信息信息
     * 
     * @param sid 住宿生信息主键
     * @return 结果
     */
    public int deleteDormitoryStumanageBySid(Long sid);
}
