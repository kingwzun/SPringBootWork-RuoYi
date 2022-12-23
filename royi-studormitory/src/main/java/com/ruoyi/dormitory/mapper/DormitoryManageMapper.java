package com.ruoyi.dormitory.mapper;

import java.util.List;
import com.ruoyi.dormitory.domain.DormitoryManage;
import com.ruoyi.dormitory.domain.DormitoryStumanage;

/**
 * 宿舍Mapper接口
 *
 * @author ruoyi
 * @date 2022-11-19
 */
public interface DormitoryManageMapper
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
     * 删除宿舍
     *
     * @param id 宿舍主键
     * @return 结果
     */
    public int deleteDormitoryManageById(Long id);

    /**
     * 批量删除宿舍
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDormitoryManageByIds(String[] ids);

    /**
     * 批量删除住宿生信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDormitoryStumanageByDormitoryNums(String[] ids);

    /**
     * 批量新增住宿生信息
     *
     * @param dormitoryStumanageList 住宿生信息列表
     * @return 结果
     */
    public int batchDormitoryStumanage(List<DormitoryStumanage> dormitoryStumanageList);


    /**
     * 通过宿舍主键删除住宿生信息信息
     *
     * @param id 宿舍ID
     * @return 结果
     */
    public int deleteDormitoryStumanageByDormitoryNum(Long id);
}
