package com.ruoyi.logistics.mapper;

import java.util.List;
import com.ruoyi.logistics.domain.NaLogistics;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
/**
 * 物流信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
public interface NaLogisticsMapper 
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
     * 删除物流信息
     * 
     * @param logiId 物流信息主键
     * @return 结果
     */
    public int deleteNaLogisticsByLogiId(Long logiId);

    /**
     * 批量删除物流信息
     * 
     * @param logiIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNaLogisticsByLogiIds(String[] logiIds);

    /**
     * 批量删除核酸信息
     * 
     * @param logiIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNucleicAcidByNaIds(String[] logiIds);
    
    /**
     * 批量新增核酸信息
     * 
     * @param nucleicAcidList 核酸信息列表
     * @return 结果
     */
    public int batchNucleicAcid(List<NucleicAcid> nucleicAcidList);
    

    /**
     * 通过物流信息主键删除核酸信息信息
     * 
     * @param logiId 物流信息ID
     * @return 结果
     */
    public int deleteNucleicAcidByNaId(Long logiId);
}
