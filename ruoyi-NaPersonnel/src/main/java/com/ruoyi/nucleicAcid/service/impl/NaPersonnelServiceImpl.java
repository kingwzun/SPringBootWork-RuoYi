package com.ruoyi.nucleicAcid.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.nucleicAcid.mapper.NaPersonnelMapper;
import com.ruoyi.nucleicAcid.domain.NaPersonnel;
import com.ruoyi.nucleicAcid.service.INaPersonnelService;
import com.ruoyi.common.core.text.Convert;

/**
 * 信息人员对照表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class NaPersonnelServiceImpl implements INaPersonnelService 
{
    @Autowired
    private NaPersonnelMapper naPersonnelMapper;

    /**
     * 查询信息人员对照表
     * 
     * @param naPerId 信息人员对照表主键
     * @return 信息人员对照表
     */
    @Override
    public NaPersonnel selectNaPersonnelByNaPerId(Long naPerId)
    {
        return naPersonnelMapper.selectNaPersonnelByNaPerId(naPerId);
    }

    /**
     * 查询信息人员对照表列表
     * 
     * @param naPersonnel 信息人员对照表
     * @return 信息人员对照表
     */
    @Override
    @DataScope( userAlias = "na_personnel")
    public List<NaPersonnel> selectNaPersonnelList(NaPersonnel naPersonnel)
    {
        return naPersonnelMapper.selectNaPersonnelList(naPersonnel);
    }

    /**
     * 新增信息人员对照表
     * 
     * @param naPersonnel 信息人员对照表
     * @return 结果
     */
    @Override
    public int insertNaPersonnel(NaPersonnel naPersonnel)
    {
        return naPersonnelMapper.insertNaPersonnel(naPersonnel);
    }

    /**
     * 修改信息人员对照表
     * 
     * @param naPersonnel 信息人员对照表
     * @return 结果
     */
    @Override
    public int updateNaPersonnel(NaPersonnel naPersonnel)
    {
        return naPersonnelMapper.updateNaPersonnel(naPersonnel);
    }

    /**
     * 批量删除信息人员对照表
     * 
     * @param naPerIds 需要删除的信息人员对照表主键
     * @return 结果
     */
    @Override
    public int deleteNaPersonnelByNaPerIds(String naPerIds)
    {
        return naPersonnelMapper.deleteNaPersonnelByNaPerIds(Convert.toStrArray(naPerIds));
    }

    /**
     * 删除信息人员对照表信息

     * @param naPerId 信息人员对照表主键
     * @return 结果
     */
    @Override
    public int deleteNaPersonnelByNaPerId(Long naPerId)
    {
        return naPersonnelMapper.deleteNaPersonnelByNaPerId(naPerId);
    }
}
