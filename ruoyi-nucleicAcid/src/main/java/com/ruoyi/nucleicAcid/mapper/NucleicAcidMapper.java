package com.ruoyi.nucleicAcid.mapper;

import java.util.List;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import com.ruoyi.nucleicAcid.domain.NaPersonnel;

/**
 * 核酸信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-31
 */
public interface NucleicAcidMapper
{
    /**
     * 查询核酸信息
     *
     * @param naId 核酸信息主键
     * @return 核酸信息
     */
    public NucleicAcid selectNucleicAcidByNaId(Long naId);
    /**
     * 查询核酸信息
     *
     * @param tubeId 试管编号
     * @return 核酸信息
     */
    public NucleicAcid selectNucleicAcidByTubeId(Long tubeId);
    /**
     * 查询核酸信息列表
     *
     * @param nucleicAcid 核酸信息
     * @return 核酸信息集合
     */
    public List<NucleicAcid> selectNucleicAcidList(NucleicAcid nucleicAcid);

    /**
     * 新增核酸信息
     *
     * @param nucleicAcid 核酸信息
     * @return 结果
     */
    public int insertNucleicAcid(NucleicAcid nucleicAcid);

    /**
     * 修改核酸信息
     *
     * @param nucleicAcid 核酸信息
     * @return 结果
     */
    public int updateNucleicAcid(NucleicAcid nucleicAcid);

    /**
     * 删除核酸信息
     *
     * @param naId 核酸信息主键
     * @return 结果
     */
    public int deleteNucleicAcidByNaId(Long naId);

    /**
     * 批量删除核酸信息
     *
     * @param naIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNucleicAcidByNaIds(String[] naIds);

    /**
     * 批量删除信息人员对照表
     *
     * @param naIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNaPersonnelByNaIds(String[] naIds);

    /**
     * 批量新增信息人员对照表
     *
     * @param naPersonnelList 信息人员对照表列表
     * @return 结果
     */
    public int batchNaPersonnel(List<NaPersonnel> naPersonnelList);


    /**
     * 通过核酸信息主键删除信息人员对照表信息
     *
     * @param naId 核酸信息ID
     * @return 结果
     */
    public int deleteNaPersonnelByNaId(Long naId);

}
