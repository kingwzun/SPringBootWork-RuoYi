package com.ruoyi.logistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import com.ruoyi.logistics.mapper.NaLogisticsMapper;
import com.ruoyi.logistics.domain.NaLogistics;
import com.ruoyi.logistics.service.INaLogisticsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物流信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
@Service
public class NaLogisticsServiceImpl implements INaLogisticsService 
{
    @Autowired
    private NaLogisticsMapper naLogisticsMapper;

    /**
     * 查询物流信息
     * 
     * @param logiId 物流信息主键
     * @return 物流信息
     */
    @Override
    public NaLogistics selectNaLogisticsByLogiId(Long logiId)
    {
        return naLogisticsMapper.selectNaLogisticsByLogiId(logiId);
    }

    /**
     * 查询物流信息列表
     * 
     * @param naLogistics 物流信息
     * @return 物流信息
     */
    @Override
    public List<NaLogistics> selectNaLogisticsList(NaLogistics naLogistics)
    {
        return naLogisticsMapper.selectNaLogisticsList(naLogistics);
    }

    /**
     * 新增物流信息
     * 
     * @param naLogistics 物流信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNaLogistics(NaLogistics naLogistics)
    {
        int rows = naLogisticsMapper.insertNaLogistics(naLogistics);
        insertNucleicAcid(naLogistics);
        return rows;
    }

    /**
     * 修改物流信息
     * 
     * @param naLogistics 物流信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNaLogistics(NaLogistics naLogistics)
    {
        naLogisticsMapper.deleteNucleicAcidByNaId(naLogistics.getLogiId());
        insertNucleicAcid(naLogistics);
        return naLogisticsMapper.updateNaLogistics(naLogistics);
    }

    /**
     * 批量删除物流信息
     * 
     * @param logiIds 需要删除的物流信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNaLogisticsByLogiIds(String logiIds)
    {
        naLogisticsMapper.deleteNucleicAcidByNaIds(Convert.toStrArray(logiIds));
        return naLogisticsMapper.deleteNaLogisticsByLogiIds(Convert.toStrArray(logiIds));
    }

    /**
     * 删除物流信息信息
     * 
     * @param logiId 物流信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNaLogisticsByLogiId(Long logiId)
    {
        naLogisticsMapper.deleteNucleicAcidByNaId(logiId);
        return naLogisticsMapper.deleteNaLogisticsByLogiId(logiId);
    }

    /**
     * 新增核酸信息信息
     * 
     * @param naLogistics 物流信息对象
     */
    public void insertNucleicAcid(NaLogistics naLogistics)
    {
        List<NucleicAcid> nucleicAcidList = naLogistics.getNucleicAcidList();
        Long logiId = naLogistics.getLogiId();
        if (StringUtils.isNotNull(nucleicAcidList))
        {
            List<NucleicAcid> list = new ArrayList<NucleicAcid>();
            for (NucleicAcid nucleicAcid : nucleicAcidList)
            {
                nucleicAcid.setNaId(logiId);
                list.add(nucleicAcid);
            }
            if (list.size() > 0)
            {
                naLogisticsMapper.batchNucleicAcid(list);
            }
        }
    }
}
