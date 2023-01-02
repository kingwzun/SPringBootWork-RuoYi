package com.ruoyi.logistics.service;

import java.util.List;
import com.ruoyi.logistics.domain.NaLogistics;

/**
 * 物流信息Service接口
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
public interface INaLogisticsService 
{
    /**
     * 查询物流信息
     * 
     * @param logiId 物流信息主键
     * @return 物流信息
     */
    public NaLogistics selectNaLogisticsByLogiId(Long logiId);

    /**
     * 查询物流信息列表
     * 
     * @param naLogistics 物流信息
     * @return 物流信息集合
     */
    public List<NaLogistics> selectNaLogisticsList(NaLogistics naLogistics);

    /**
     * 新增物流信息
     * 
     * @param naLogistics 物流信息
     * @return 结果
     */
    public int insertNaLogistics(NaLogistics naLogistics);

    /**
     * 修改物流信息
     * 
     * @param naLogistics 物流信息
     * @return 结果
     */
    public int updateNaLogistics(NaLogistics naLogistics);

    /**
     * 批量删除物流信息
     * 
     * @param logiIds 需要删除的物流信息主键集合
     * @return 结果
     */
    public int deleteNaLogisticsByLogiIds(String logiIds);

    /**
     * 删除物流信息信息
     * 
     * @param logiId 物流信息主键
     * @return 结果
     */
    public int deleteNaLogisticsByLogiId(Long logiId);
}
