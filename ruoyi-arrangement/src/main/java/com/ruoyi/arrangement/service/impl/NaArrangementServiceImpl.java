package com.ruoyi.arrangement.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.arrangement.domain.vo.NaArrangementVO;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.laboratory.domain.NaLaboratory;
import com.ruoyi.laboratory.service.INaLaboratoryService;
import com.ruoyi.point.domain.NaPoint;
import com.ruoyi.point.service.INaPointService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysUserService;
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
    @Autowired
    private INaLaboratoryService laboratoryService;
    @Autowired
    private INaPointService pointService;
    @Autowired
    private ISysPostService postService;
    @Autowired
    private ISysUserService userService;
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
     * 查询任务安排
     *
     * @param arrId 任务安排主键
     * @return 任务安排
     */
    @Override
    public NaArrangementVO selectNaArrangementVOByArrId(Long arrId)
    {
        NaArrangementVO naArrangementVO = new NaArrangementVO();
        NaArrangement arr = naArrangementMapper.selectNaArrangementByArrId(arrId);

        naArrangementVO.setArrId(arr.getArrId());
        naArrangementVO.setArrStatus(arr.getArrStatus());
        naArrangementVO.setArrAddress(arr.getArrAddress());
        naArrangementVO.setArrTime(arr.getArrTime());

        SysUser sysUser = userService.selectUserById(arr.getDeliveryId());
        if (sysUser==null) naArrangementVO.setDeliveryName("未查询到物流人员");
        else naArrangementVO.setDeliveryName(sysUser.getUserName());

        NaLaboratory laboratory = laboratoryService.selectNaLaboratoryByLabId(arr.getLaboratoryId());
        if(laboratory==null) naArrangementVO.setLaboratoryName("未查询到实验室");
        else naArrangementVO.setLaboratoryName(laboratory.getLabName());

        NaPoint point = pointService.selectNaPointByPointId(arr.getPointId());
        if (point==null) naArrangementVO.setPointName("未查询到检测点");
        else  naArrangementVO.setPointName(point.getPointName());

        return naArrangementVO;
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
     * 查询任务安排列表
     *
     * @param naArrangement 任务安排
     * @return 任务安排
     */
    @Override
    public List<NaArrangementVO> selectNaArrangementVOList(NaArrangement naArrangement) {
        List<NaArrangementVO> list=new ArrayList<>();
        List<NaArrangement> arrangements = naArrangementMapper.selectNaArrangementList(naArrangement);
        if(arrangements!=null){
            for (NaArrangement arr : arrangements) {
                NaArrangementVO naArrangementVO = new NaArrangementVO();
                naArrangementVO.setArrId(arr.getArrId());
                naArrangementVO.setArrStatus(arr.getArrStatus());
                naArrangementVO.setArrAddress(arr.getArrAddress());
                naArrangementVO.setArrTime(arr.getArrTime());


                SysUser sysUser = userService.selectUserById(arr.getDeliveryId());
                if (sysUser==null) naArrangementVO.setDeliveryName("未查询到物流人员");
                else naArrangementVO.setDeliveryName(sysUser.getUserName());

                NaLaboratory laboratory = laboratoryService.selectNaLaboratoryByLabId(arr.getLaboratoryId());
                if(laboratory==null) naArrangementVO.setLaboratoryName("未查询到实验室");
                else naArrangementVO.setLaboratoryName(laboratory.getLabName());

                NaPoint point = pointService.selectNaPointByPointId(arr.getPointId());
                if (point==null) naArrangementVO.setPointName("未查询到检测点");
                else  naArrangementVO.setPointName(point.getPointName());

                list.add(naArrangementVO);
            }

        }
        return list;
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
    /**
     * 角色状态修改
     */
    @Override
    public Integer changeStatus(NaArrangement naArrangement) {
        return naArrangementMapper.updateNaArrangement(naArrangement);
    }
}
