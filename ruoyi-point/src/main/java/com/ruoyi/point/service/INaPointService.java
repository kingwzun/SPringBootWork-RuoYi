package com.ruoyi.point.service;

import java.util.List;
import com.ruoyi.point.domain.NaPoint;

/**
 * 检测点管理Service接口
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
public interface INaPointService 
{
    /**
     * 查询检测点管理
     * 
     * @param pointId 检测点管理主键
     * @return 检测点管理
     */
    public NaPoint selectNaPointByPointId(Long pointId);

    /**
     * 查询检测点管理列表
     * 
     * @param naPoint 检测点管理
     * @return 检测点管理集合
     */
    public List<NaPoint> selectNaPointList(NaPoint naPoint);

    /**
     * 新增检测点管理
     * 
     * @param naPoint 检测点管理
     * @return 结果
     */
    public int insertNaPoint(NaPoint naPoint);

    /**
     * 修改检测点管理
     * 
     * @param naPoint 检测点管理
     * @return 结果
     */
    public int updateNaPoint(NaPoint naPoint);

    /**
     * 批量删除检测点管理
     * 
     * @param pointIds 需要删除的检测点管理主键集合
     * @return 结果
     */
    public int deleteNaPointByPointIds(String pointIds);

    /**
     * 删除检测点管理信息
     * 
     * @param pointId 检测点管理主键
     * @return 结果
     */
    public int deleteNaPointByPointId(Long pointId);
}
