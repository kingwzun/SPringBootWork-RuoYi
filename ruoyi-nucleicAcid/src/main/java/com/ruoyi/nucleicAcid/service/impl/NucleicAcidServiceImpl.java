package com.ruoyi.nucleicAcid.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.nucleicAcid.domain.NaPersonnel;
import com.ruoyi.nucleicAcid.mapper.NucleicAcidMapper;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import com.ruoyi.nucleicAcid.service.INucleicAcidService;
import com.ruoyi.common.core.text.Convert;

/**
 * 核酸信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class NucleicAcidServiceImpl implements INucleicAcidService
{
    @Autowired
    private NucleicAcidMapper nucleicAcidMapper;

    /**
     * 查询核酸信息
     *
     * @param naId 核酸信息主键
     * @return 核酸信息
     */
    @Override
    public NucleicAcid selectNucleicAcidByNaId(Long naId)
    {
        return nucleicAcidMapper.selectNucleicAcidByNaId(naId);
    }

    /**
     * 查询核酸信息列表
     *
     * @param nucleicAcid 核酸信息
     * @return 核酸信息
     */
    @Override
    public List<NucleicAcid> selectNucleicAcidList(NucleicAcid nucleicAcid)
    {
        return nucleicAcidMapper.selectNucleicAcidList(nucleicAcid);
    }

    /**
     * 新增核酸信息
     *
     * @param nucleicAcid 核酸信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNucleicAcid(NucleicAcid nucleicAcid)
    {
        int rows = nucleicAcidMapper.insertNucleicAcid(nucleicAcid);
        insertNaPersonnel(nucleicAcid);
        return rows;
    }

    /**
     * 修改核酸信息
     *
     * @param nucleicAcid 核酸信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNucleicAcid(NucleicAcid nucleicAcid)
    {
        nucleicAcidMapper.deleteNaPersonnelByNaId(nucleicAcid.getNaId());
        insertNaPersonnel(nucleicAcid);
        return nucleicAcidMapper.updateNucleicAcid(nucleicAcid);
    }

    /**
     * 修改核酸结果
     *
     * @param nucleicAcid 核酸信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNaResult(NucleicAcid nucleicAcid)
    {
        return nucleicAcidMapper.updateNucleicAcid(nucleicAcid);
    }

    /**
     * 批量删除核酸信息
     *
     * @param naIds 需要删除的核酸信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNucleicAcidByNaIds(String naIds)
    {
        nucleicAcidMapper.deleteNaPersonnelByNaIds(Convert.toStrArray(naIds));
        return nucleicAcidMapper.deleteNucleicAcidByNaIds(Convert.toStrArray(naIds));
    }

    /**
     * 删除核酸信息信息
     *
     * @param naId 核酸信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNucleicAcidByNaId(Long naId)
    {
        nucleicAcidMapper.deleteNaPersonnelByNaId(naId);
        return nucleicAcidMapper.deleteNucleicAcidByNaId(naId);
    }

    /**
     * 新增信息人员对照表信息
     *
     * @param nucleicAcid 核酸信息对象
     */
    public void insertNaPersonnel(NucleicAcid nucleicAcid)
    {
        List<NaPersonnel> naPersonnelList = nucleicAcid.getNaPersonnelList();
        Long naId = nucleicAcid.getNaId();
        if (StringUtils.isNotNull(naPersonnelList))
        {
            List<NaPersonnel> list = new ArrayList<NaPersonnel>();
            for (NaPersonnel naPersonnel : naPersonnelList)
            {
                naPersonnel.setNaId(naId);
                list.add(naPersonnel);
            }
            if (list.size() > 0)
            {
                nucleicAcidMapper.batchNaPersonnel(list);
            }
        }
    }
}
