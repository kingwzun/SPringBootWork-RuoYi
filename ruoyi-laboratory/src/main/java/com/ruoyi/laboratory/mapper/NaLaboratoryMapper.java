package com.ruoyi.laboratory.mapper;

import java.util.List;
import com.ruoyi.laboratory.domain.NaLaboratory;

/**
 * 实验室管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-12-25
 */
public interface NaLaboratoryMapper 
{
    /**
     * 查询实验室管理
     * 
     * @param labId 实验室管理主键
     * @return 实验室管理
     */
    public NaLaboratory selectNaLaboratoryByLabId(Long labId);

    /**
     * 查询实验室管理列表
     * 
     * @param naLaboratory 实验室管理
     * @return 实验室管理集合
     */
    public List<NaLaboratory> selectNaLaboratoryList(NaLaboratory naLaboratory);

    /**
     * 新增实验室管理
     * 
     * @param naLaboratory 实验室管理
     * @return 结果
     */
    public int insertNaLaboratory(NaLaboratory naLaboratory);

    /**
     * 修改实验室管理
     * 
     * @param naLaboratory 实验室管理
     * @return 结果
     */
    public int updateNaLaboratory(NaLaboratory naLaboratory);

    /**
     * 删除实验室管理
     * 
     * @param labId 实验室管理主键
     * @return 结果
     */
    public int deleteNaLaboratoryByLabId(Long labId);

    /**
     * 批量删除实验室管理
     * 
     * @param labIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNaLaboratoryByLabIds(String[] labIds);
}
