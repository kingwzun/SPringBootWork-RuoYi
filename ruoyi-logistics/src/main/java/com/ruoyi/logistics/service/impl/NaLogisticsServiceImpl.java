package com.ruoyi.logistics.service.impl;

import java.util.List;

import com.ruoyi.arrangement.domain.NaArrangement;
import com.ruoyi.arrangement.domain.vo.NaArrangementVO;
import com.ruoyi.arrangement.service.INaArrangementService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.laboratory.domain.NaLaboratory;
import com.ruoyi.laboratory.service.INaLaboratoryService;
import com.ruoyi.logistics.domain.vo.NaLogisticsVO;
import com.ruoyi.nucleicAcid.service.INucleicAcidService;
import com.ruoyi.point.domain.NaPoint;
import com.ruoyi.point.service.INaPointService;
import com.ruoyi.system.service.ISysPostService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import com.ruoyi.logistics.mapper.NaLogisticsMapper;
import com.ruoyi.logistics.domain.NaLogistics;
import com.ruoyi.logistics.service.INaLogisticsService;
import com.ruoyi.common.core.text.Convert;

import static com.ruoyi.common.utils.ShiroUtils.getSysUser;

/**
 * 物流信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
@Service
public class NaLogisticsServiceImpl implements INaLogisticsService 
{
    @Autowired
    private NaLogisticsMapper naLogisticsMapper;
    @Autowired
    private INaArrangementService naArrangementService;
    @Autowired
    private INaLaboratoryService laboratoryService;
    @Autowired
    private INaPointService pointService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private INucleicAcidService nucleicAcidService;
    /**
     * 查询物流信息
     * 
     * @param logiId 物流信息主键
     * @return 物流信息
     */
    @Override
    public NaLogistics selectNaLogisticsByLogiId(Long logiId)
    {
        return naLogisticsMapper.selectNaLogisticsByLogiId(logiId);
    }

    /**
     * 查询物流信息列表
     * 
     * @param naLogistics 物流信息
     * @return 物流信息
     */
    @Override
    public List<NaLogistics> selectNaLogisticsList(NaLogistics naLogistics)
    {
        return naLogisticsMapper.selectNaLogisticsList(naLogistics);
    }
    /**
     * 查询物流信息列表
     *
     * @param naLogistics 物流信息
     * @return 物流信息集合
     */
    public List<NaLogisticsVO> selectNaLogisticsVOList(NaLogistics naLogistics){
        List<NaLogisticsVO> logisticsVOList=new ArrayList<>();
        List<NaLogistics> list = naLogisticsMapper.selectNaLogisticsList(naLogistics);
        if(list!=null){
            for (NaLogistics it : list) {
                NaLogisticsVO vo = new NaLogisticsVO();
                vo.setLogiId(it.getLogiId());
                vo.setLogiStatus(it.getLogiStatus());

                SysUser sysUser = userService.selectUserById(it.getDeliveryId());
                if (sysUser==null) vo.setDeliveryName("未查询到物流人员");
                else vo.setDeliveryName(sysUser.getUserName());

                NaPoint point = pointService.selectNaPointByPointId(it.getPointId());
                if (point==null) vo.setPointName("未查询到检测点");
                else  vo.setPointName(point.getPointName());

                NaLaboratory laboratory = laboratoryService.selectNaLaboratoryByLabId(it.getLaboratoryId());
                if(laboratory==null) vo.setLaboratoryName("未查询到实验室");
                else vo.setLaboratoryName(laboratory.getLabName());

                vo.setLogiLabTime(it.getLogiLabTime());
                vo.setLogiPointTime(it.getLogiPointTime());
                vo.setNucleicAcidList(it.getNucleicAcidList());
                logisticsVOList.add(vo);
            }
        }
        return logisticsVOList;
    }
    /**
     * 新增物流信息
     * 
     * @param naLogistics 物流信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNaLogistics(NaLogistics naLogistics)
    {
        System.out.println("NaLogisticsServiceImpl.insertNaLogistics");
        int rows = naLogisticsMapper.insertNaLogistics(naLogistics);
        
        insertNucleicAcid(naLogistics);
        return rows;
    }

    /**
     * 修改物流信息
     * 
     * @param naLogistics 物流信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNaLogistics(NaLogistics naLogistics)
    {
        naLogisticsMapper.deleteNucleicAcidByNaId(naLogistics.getLogiId());
        insertNucleicAcid(naLogistics);
        return naLogisticsMapper.updateNaLogistics(naLogistics);
    }

    /**
     * 批量删除物流信息
     * 
     * @param logiIds 需要删除的物流信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNaLogisticsByLogiIds(String logiIds)
    {
        naLogisticsMapper.deleteNucleicAcidByNaIds(Convert.toStrArray(logiIds));
        return naLogisticsMapper.deleteNaLogisticsByLogiIds(Convert.toStrArray(logiIds));
    }

    /**
     * 删除物流信息信息
     * 
     * @param logiId 物流信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNaLogisticsByLogiId(Long logiId)
    {
        naLogisticsMapper.deleteNucleicAcidByNaId(logiId);
        return naLogisticsMapper.deleteNaLogisticsByLogiId(logiId);
    }
    /**
     * 根据任务，生成物流信息
     *
     *
     * @param arrId 任务信息主键
     * @return 结果
     */
    @Override
    public NaLogistics generateLogisticsByArrId(Long arrId) {

        NaArrangement arr = naArrangementService.selectNaArrangementByArrId(arrId);
        NaLogistics logistics = new NaLogistics();
        logistics.setDeliveryId(getSysUser().getUserId());
        logistics.setLaboratoryId(arr.getLaboratoryId());
        logistics.setPointId(arr.getPointId());
        List<NucleicAcid> list = new ArrayList<NucleicAcid>();
        logistics.setNucleicAcidList(list);
        return logistics;
    }

    /**
     * 新增核酸信息信息
     * 
     * @param naLogistics 物流信息对象
     */
    public void insertNucleicAcid(NaLogistics naLogistics)
    {
        System.out.println("NaLogisticsServiceImpl.insertNucleicAcid");
        List<NucleicAcid> nucleicAcidList = naLogistics.getNucleicAcidList();
        Long logiId = naLogistics.getLogiId();
        if (StringUtils.isNotNull(nucleicAcidList))
        {
//            List<NucleicAcid> list = new ArrayList<NucleicAcid>();
            for (NucleicAcid nucleicAcid : nucleicAcidList)
            {
                nucleicAcid.setLogiId(logiId);
                System.out.println(nucleicAcid);
                nucleicAcidService.updateNucleicAcid(nucleicAcid);
            }
        }
    }
}
