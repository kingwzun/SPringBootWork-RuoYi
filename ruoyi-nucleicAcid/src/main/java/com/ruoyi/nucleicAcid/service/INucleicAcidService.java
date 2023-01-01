package com.ruoyi.nucleicAcid.service;

import java.util.List;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import org.springframework.transaction.annotation.Transactional;

/**
 * 核酸信息Service接口
 *
 * @author ruoyi
 * @date 2022-12-31
 */
public interface INucleicAcidService
{
    /**
     * 查询核酸信息
     *
     * @param naId 核酸信息主键
     * @return 核酸信息
     */
    public NucleicAcid selectNucleicAcidByNaId(Long naId);

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
     * 修改核酸结果
     *
     * @param nucleicAcid 核酸信息
     * @return 结果
     */
    @Transactional
    int updateNaResult(NucleicAcid nucleicAcid);

    /**
     * 批量删除核酸信息
     *
     * @param naIds 需要删除的核酸信息主键集合
     * @return 结果
     */
    public int deleteNucleicAcidByNaIds(String naIds);

    /**
     * 删除核酸信息信息
     *
     * @param naId 核酸信息主键
     * @return 结果
     */
    public int deleteNucleicAcidByNaId(Long naId);
}
