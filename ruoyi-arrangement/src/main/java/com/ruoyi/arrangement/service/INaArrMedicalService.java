package com.ruoyi.arrangement.service;

import java.util.List;
import com.ruoyi.arrangement.domain.NaArrMedical;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 任务与医护人员关联Service接口
 * 
 * @author ruoyi
 * @date 2022-12-27
 */
public interface INaArrMedicalService 
{

    /**
     * 根据任务ID查询任务与医护人员关联
     *
     * @param arrId 任务ID
     * @return 任务与医护人员关联
     */
    public List<SysUser> selectMedicalsByArrId(Long arrId);
    /**
     * 根据医护人员ID查询任务与医护人员关联
     *
     * @param medId 医护人员Id
     * @return 任务与医护人员关联
     */
    public List<NaArrMedical> selectNaArrMedicalByMedId(Long medId);
    /**
     * 查询所有未分配的医护人员
     *
     * @return 所有未分配的医护人员
     */
    List<SysUser> SelectUnAllocatedList();
    /**
     * 查询任务与医护人员关联列表
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 任务与医护人员关联集合
     */
    public List<NaArrMedical> selectNaArrMedicalList(NaArrMedical naArrMedical);

    /**
     * 新增任务与医护人员关联
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 结果
     */
    public int insertNaArrMedical(NaArrMedical naArrMedical);
    /**
     * 批量 新增任务与医护人员关联
     *
     * @param arrId 任务Id
     * @param userIds 医护人员Id序列
     * @return 结果
     */
    Integer saveAuthUserAll(Long arrId, String userIds);
    /**
     * 修改任务与医护人员关联
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 结果
     */
    public int updateNaArrMedical(NaArrMedical naArrMedical);



    /**
     * 删除任务与医护人员关联信息
     * 
     * @param arrMedical 信息
     * @return 结果
     */
    public int deleteNaArrMedical(NaArrMedical arrMedical);

    /**
     * 查询所有的物流人员
     *
     * @return 查询所有的物流人员
     */
    List<SysUser> selectDeliveryList();
}
