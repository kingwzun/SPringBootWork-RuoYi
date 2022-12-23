package com.ruoyi.dormitory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dormitory.mapper.DormitoryStumanageMapper;
import com.ruoyi.dormitory.domain.DormitoryStumanage;
import com.ruoyi.dormitory.service.IDormitoryStumanageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 住宿生信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Service
public class DormitoryStumanageServiceImpl implements IDormitoryStumanageService 
{
    @Autowired
    private DormitoryStumanageMapper dormitoryStumanageMapper;

    /**
     * 查询住宿生信息
     * 
     * @param sid 住宿生信息主键
     * @return 住宿生信息
     */
    @Override
    public DormitoryStumanage selectDormitoryStumanageBySid(Long sid)
    {
        return dormitoryStumanageMapper.selectDormitoryStumanageBySid(sid);
    }

    /**
     * 查询住宿生信息列表
     * 
     * @param dormitoryStumanage 住宿生信息
     * @return 住宿生信息
     */
    @Override
    public List<DormitoryStumanage> selectDormitoryStumanageList(DormitoryStumanage dormitoryStumanage)
    {
        return dormitoryStumanageMapper.selectDormitoryStumanageList(dormitoryStumanage);
    }

    /**
     * 新增住宿生信息
     * 
     * @param dormitoryStumanage 住宿生信息
     * @return 结果
     */
    @Override
    public int insertDormitoryStumanage(DormitoryStumanage dormitoryStumanage)
    {
        dormitoryStumanage.setCreateTime(DateUtils.getNowDate());
        return dormitoryStumanageMapper.insertDormitoryStumanage(dormitoryStumanage);
    }

    /**
     * 修改住宿生信息
     * 
     * @param dormitoryStumanage 住宿生信息
     * @return 结果
     */
    @Override
    public int updateDormitoryStumanage(DormitoryStumanage dormitoryStumanage)
    {
        dormitoryStumanage.setUpdateTime(DateUtils.getNowDate());
        return dormitoryStumanageMapper.updateDormitoryStumanage(dormitoryStumanage);
    }

    /**
     * 批量删除住宿生信息
     * 
     * @param sids 需要删除的住宿生信息主键
     * @return 结果
     */
    @Override
    public int deleteDormitoryStumanageBySids(String sids)
    {
        return dormitoryStumanageMapper.deleteDormitoryStumanageBySids(Convert.toStrArray(sids));
    }

    /**
     * 删除住宿生信息信息
     * 
     * @param sid 住宿生信息主键
     * @return 结果
     */
    @Override
    public int deleteDormitoryStumanageBySid(Long sid)
    {
        return dormitoryStumanageMapper.deleteDormitoryStumanageBySid(sid);
    }
}
