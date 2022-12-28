package com.ruoyi.arrangement.mapper;

import java.util.List;
import com.ruoyi.arrangement.domain.NaArrangement;

/**
 * 任务安排Mapper接口
 * 
 * @author ruoyi
 * @date 2022-12-26
 */
public interface NaArrangementMapper 
{
    /**
     * 查询任务安排
     * 
     * @param arrId 任务安排主键
     * @return 任务安排
     */
    public NaArrangement selectNaArrangementByArrId(Long arrId);

    /**
     * 查询任务安排列表
     * 
     * @param naArrangement 任务安排
     * @return 任务安排集合
     */
    public List<NaArrangement> selectNaArrangementList(NaArrangement naArrangement);

    /**
     * 新增任务安排
     * 
     * @param naArrangement 任务安排
     * @return 结果
     */
    public int insertNaArrangement(NaArrangement naArrangement);

    /**
     * 修改任务安排
     * 
     * @param naArrangement 任务安排
     * @return 结果
     */
    public int updateNaArrangement(NaArrangement naArrangement);

    /**
     * 删除任务安排
     * 
     * @param arrId 任务安排主键
     * @return 结果
     */
    public int deleteNaArrangementByArrId(Long arrId);

    /**
     * 批量删除任务安排
     * 
     * @param arrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNaArrangementByArrIds(String[] arrIds);
}
