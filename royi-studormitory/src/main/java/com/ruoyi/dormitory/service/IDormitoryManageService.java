package com.ruoyi.dormitory.service;

import java.util.List;
import com.ruoyi.dormitory.domain.DormitoryManage;

/**
 * 宿舍Service接口
 *
 * @author ruoyi
 * @date 2022-11-19
 */
public interface IDormitoryManageService
{
    /**
     * 查询宿舍
     *
     * @param id 宿舍主键
     * @return 宿舍
     */
    public DormitoryManage selectDormitoryManageById(Long id);

    /**
     * 查询宿舍列表
     *
     * @param dormitoryManage 宿舍
     * @return 宿舍集合
     */
    public List<DormitoryManage> selectDormitoryManageList(DormitoryManage dormitoryManage);

    /**
     * 新增宿舍
     *
     * @param dormitoryManage 宿舍
     * @return 结果
     */
    public int insertDormitoryManage(DormitoryManage dormitoryManage);

    /**
     * 修改宿舍
     *
     * @param dormitoryManage 宿舍
     * @return 结果
     */
    public int updateDormitoryManage(DormitoryManage dormitoryManage);

    /**
     * 批量删除宿舍
     *
     * @param ids 需要删除的宿舍主键集合
     * @return 结果
     */
    public int deleteDormitoryManageByIds(String ids);

    /**
     * 删除宿舍信息
     *
     * @param id 宿舍主键
     * @return 结果
     */
    public int deleteDormitoryManageById(Long id);
}
