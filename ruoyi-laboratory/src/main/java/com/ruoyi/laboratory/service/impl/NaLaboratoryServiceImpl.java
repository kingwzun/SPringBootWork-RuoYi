package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.NaLaboratoryMapper;
import com.ruoyi.laboratory.domain.NaLaboratory;
import com.ruoyi.laboratory.service.INaLaboratoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 实验室管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-25
 */
@Service
public class NaLaboratoryServiceImpl implements INaLaboratoryService 
{
    @Autowired
    private NaLaboratoryMapper naLaboratoryMapper;

    /**
     * 查询实验室管理
     * 
     * @param labId 实验室管理主键
     * @return 实验室管理
     */
    @Override
    public NaLaboratory selectNaLaboratoryByLabId(Long labId)
    {
        return naLaboratoryMapper.selectNaLaboratoryByLabId(labId);
    }

    /**
     * 查询实验室管理列表
     * 
     * @param naLaboratory 实验室管理
     * @return 实验室管理
     */
    @Override
    public List<NaLaboratory> selectNaLaboratoryList(NaLaboratory naLaboratory)
    {
        return naLaboratoryMapper.selectNaLaboratoryList(naLaboratory);
    }

    /**
     * 新增实验室管理
     * 
     * @param naLaboratory 实验室管理
     * @return 结果
     */
    @Override
    public int insertNaLaboratory(NaLaboratory naLaboratory)
    {
        return naLaboratoryMapper.insertNaLaboratory(naLaboratory);
    }

    /**
     * 修改实验室管理
     * 
     * @param naLaboratory 实验室管理
     * @return 结果
     */
    @Override
    public int updateNaLaboratory(NaLaboratory naLaboratory)
    {
        return naLaboratoryMapper.updateNaLaboratory(naLaboratory);
    }

    /**
     * 批量删除实验室管理
     * 
     * @param labIds 需要删除的实验室管理主键
     * @return 结果
     */
    @Override
    public int deleteNaLaboratoryByLabIds(String labIds)
    {
        return naLaboratoryMapper.deleteNaLaboratoryByLabIds(Convert.toStrArray(labIds));
    }

    /**
     * 删除实验室管理信息
     * 
     * @param labId 实验室管理主键
     * @return 结果
     */
    @Override
    public int deleteNaLaboratoryByLabId(Long labId)
    {
        return naLaboratoryMapper.deleteNaLaboratoryByLabId(labId);
    }
}
