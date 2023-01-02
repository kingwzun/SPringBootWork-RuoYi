package com.ruoyi.arrangement.service;

import java.util.List;
import com.ruoyi.arrangement.domain.NaArrangement;
import com.ruoyi.arrangement.domain.vo.NaArrangementVO;
import com.ruoyi.common.core.domain.entity.SysRole;

/**
 * 任务安排Service接口
 * 
 * @author ruoyi
 * @date 2022-12-26
 */
public interface INaArrangementService 
{
    /**
     * 查询任务安排
     * 
     * @param arrId 任务安排主键
     * @return 任务安排
     */
    public NaArrangement selectNaArrangementByArrId(Long arrId);
    /**
     * 查询任务安排
     *
     * @param arrId 任务安排主键
     * @return 任务安排
     */
    public NaArrangementVO selectNaArrangementVOByArrId(Long arrId);
    /**
     * 查询任务安排列表
     * 
     * @param naArrangement 任务安排
     * @return 任务安排集合
     */
    public List<NaArrangement> selectNaArrangementList(NaArrangement naArrangement);


    /**
     * 查询任务安排列表
     */
    public List<NaArrangementVO> selectNaArrangementVOList(NaArrangement naArrangement);
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
     * 批量删除任务安排
     * 
     * @param arrIds 需要删除的任务安排主键集合
     * @return 结果
     */
    public int deleteNaArrangementByArrIds(String arrIds);

    /**
     * 删除任务安排信息
     * 
     * @param arrId 任务安排主键
     * @return 结果
     */
    public int deleteNaArrangementByArrId(Long arrId);
    /**
     * 角色状态修改
     */
    public Integer changeStatus(NaArrangement naArrangement);

}
