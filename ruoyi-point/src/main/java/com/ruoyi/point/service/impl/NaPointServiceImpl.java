package com.ruoyi.point.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.point.mapper.NaPointMapper;
import com.ruoyi.point.domain.NaPoint;
import com.ruoyi.point.service.INaPointService;
import com.ruoyi.common.core.text.Convert;

/**
 * 检测点管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
@Service
public class NaPointServiceImpl implements INaPointService 
{
    @Autowired
    private NaPointMapper naPointMapper;

    /**
     * 查询检测点管理
     * 
     * @param pointId 检测点管理主键
     * @return 检测点管理
     */
    @Override
    public NaPoint selectNaPointByPointId(Long pointId)
    {
        return naPointMapper.selectNaPointByPointId(pointId);
    }

    /**
     * 查询检测点管理列表
     * 
     * @param naPoint 检测点管理
     * @return 检测点管理
     */
    @Override
    public List<NaPoint> selectNaPointList(NaPoint naPoint)
    {
        return naPointMapper.selectNaPointList(naPoint);
    }

    /**
     * 新增检测点管理
     * 
     * @param naPoint 检测点管理
     * @return 结果
     */
    @Override
    public int insertNaPoint(NaPoint naPoint)
    {
        naPoint.setCreateTime(DateUtils.getNowDate());
        return naPointMapper.insertNaPoint(naPoint);
    }

    /**
     * 修改检测点管理
     * 
     * @param naPoint 检测点管理
     * @return 结果
     */
    @Override
    public int updateNaPoint(NaPoint naPoint)
    {
        naPoint.setUpdateTime(DateUtils.getNowDate());
        return naPointMapper.updateNaPoint(naPoint);
    }

    /**
     * 批量删除检测点管理
     * 
     * @param pointIds 需要删除的检测点管理主键
     * @return 结果
     */
    @Override
    public int deleteNaPointByPointIds(String pointIds)
    {
        return naPointMapper.deleteNaPointByPointIds(Convert.toStrArray(pointIds));
    }

    /**
     * 删除检测点管理信息
     * 
     * @param pointId 检测点管理主键
     * @return 结果
     */
    @Override
    public int deleteNaPointByPointId(Long pointId)
    {
        return naPointMapper.deleteNaPointByPointId(pointId);
    }
}
