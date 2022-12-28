package com.ruoyi.arrangement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.arrangement.mapper.NaArrMedicalMapper;
import com.ruoyi.arrangement.service.INaArrMedicalService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.arrangement.domain.NaArrMedical;

import com.ruoyi.common.core.text.Convert;

/**
 * 任务与医护人员关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-27
 */
@Service
public class NaArrMedicalServiceImpl implements INaArrMedicalService
{
    @Autowired
    private  NaArrMedicalMapper naArrMedicalMapper;
    @Autowired
    private ISysUserService userService;
    /**
     * 查询任务与医护人员关联
     * 
     * @param arrId 任务与医护人员关联主键
     * @return 任务与医护人员关联
     */
    @Override
    public List<SysUser> selectMedicalsByArrId(Long arrId)
    {
        List<NaArrMedical> list = naArrMedicalMapper.selectNaArrMedicalByArrId(arrId);
        ArrayList<SysUser> medicals = new ArrayList<>();
        if(list.size()>0){
            for (NaArrMedical arrMedical : list) {
                medicals.add(userService.selectUserById(arrMedical.getMedId()));
            }
        }
        return medicals;
    }
    /**
     * 根据医护人员ID查询任务与医护人员关联
     *
     * @param medId 医护人员Id
     * @return 任务与医护人员关联
     */
    public List<NaArrMedical> selectNaArrMedicalByMedId(Long medId){
        return naArrMedicalMapper.selectNaArrMedicalByMedId(medId);
    }


    /**
     * 查询任务与医护人员关联列表
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 任务与医护人员关联
     */
    @Override
    public List<NaArrMedical> selectNaArrMedicalList(NaArrMedical naArrMedical)
    {
        return naArrMedicalMapper.selectNaArrMedicalList(naArrMedical);
    }

    /**
     * 新增任务与医护人员关联
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 结果
     */
    @Override
    public int insertNaArrMedical(NaArrMedical naArrMedical)
    {
        return naArrMedicalMapper.insertNaArrMedical(naArrMedical);
    }

    /**
     * 修改任务与医护人员关联
     * 
     * @param naArrMedical 任务与医护人员关联
     * @return 结果
     */
    @Override
    public int updateNaArrMedical(NaArrMedical naArrMedical)
    {
        return naArrMedicalMapper.updateNaArrMedical(naArrMedical);
    }


    /**
     * 删除任务与医护人员关联信息
     * 
     * @param arrMedical 任务与医护人员关联主键
     * @return 结果
     */
    @Override
    public int deleteNaArrMedical(NaArrMedical arrMedical)
    {
        return naArrMedicalMapper.deleteNaArrMedical(arrMedical);
    }
}
