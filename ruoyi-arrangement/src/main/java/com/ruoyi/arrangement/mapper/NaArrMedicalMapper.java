package com.ruoyi.arrangement.mapper;

import java.util.List;
import com.ruoyi.arrangement.domain.NaArrMedical;

/**
 * 任务与医护人员关联Mapper接口
 * 
 * @author ruoyi
 * @date 2022-12-27
 */
public interface NaArrMedicalMapper 
{
    /**
     * 根据任务ID查询任务与医护人员关联
     * 
     * @param arrId 任务ID
     * @return 任务与医护人员关联
     */
    public List<NaArrMedical> selectNaArrMedicalByArrId(Long arrId);
    /**
     * 根据医护人员ID查询任务与医护人员关联
     *
     * @param medId 医护人员Id
     * @return 任务与医护人员关联
     */
    public List<NaArrMedical> selectNaArrMedicalByMedId(Long medId);

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
     * 修改任务与医护人员关联
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 结果
     */
    public int updateNaArrMedical(NaArrMedical naArrMedical);

    /**
     * 删除任务与医护人员关联
     * 
     * @param arrMedical 任务与医护人员关联主键
     * @return 结果
     */
    public int deleteNaArrMedical(NaArrMedical arrMedical);


}
