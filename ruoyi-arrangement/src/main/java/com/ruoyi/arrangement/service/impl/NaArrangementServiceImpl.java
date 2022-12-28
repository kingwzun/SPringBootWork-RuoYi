package com.ruoyi.arrangement.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.arrangement.mapper.NaArrangementMapper;
import com.ruoyi.arrangement.domain.NaArrangement;
import com.ruoyi.arrangement.service.INaArrangementService;
import com.ruoyi.common.core.text.Convert;

/**
 * 任务安排Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-26
 */
@Service
public class NaArrangementServiceImpl implements INaArrangementService 
{
    @Autowired
    private NaArrangementMapper naArrangementMapper;

    /**
     * 查询任务安排
     * 
     * @param arrId 任务安排主键
     * @return 任务安排
     */
    @Override
    public NaArrangement selectNaArrangementByArrId(Long arrId)
    {
        return naArrangementMapper.selectNaArrangementByArrId(arrId);
    }

    /**
     * 查询任务安排列表
     * 
     * @param naArrangement 任务安排
     * @return 任务安排
     */
    @Override
    public List<NaArrangement> selectNaArrangementList(NaArrangement naArrangement)
    {
        return naArrangementMapper.selectNaArrangementList(naArrangement);
    }

    /**
     * 新增任务安排
     * 
     * @param naArrangement 任务安排
     * @return 结果
     */
    @Override
    public int insertNaArrangement(NaArrangement naArrangement)
    {
        naArrangement.setCreateTime(DateUtils.getNowDate());
        return naArrangementMapper.insertNaArrangement(naArrangement);
    }

    /**
     * 修改任务安排
     * 
     * @param naArrangement 任务安排
     * @return 结果
     */
    @Override
    public int updateNaArrangement(NaArrangement naArrangement)
    {
        naArrangement.setUpdateTime(DateUtils.getNowDate());
        return naArrangementMapper.updateNaArrangement(naArrangement);
    }

    /**
     * 批量删除任务安排
     * 
     * @param arrIds 需要删除的任务安排主键
     * @return 结果
     */
    @Override
    public int deleteNaArrangementByArrIds(String arrIds)
    {
        return naArrangementMapper.deleteNaArrangementByArrIds(Convert.toStrArray(arrIds));
    }

    /**
     * 删除任务安排信息
     * 
     * @param arrId 任务安排主键
     * @return 结果
     */
    @Override
    public int deleteNaArrangementByArrId(Long arrId)
    {
        return naArrangementMapper.deleteNaArrangementByArrId(arrId);
    }
}
