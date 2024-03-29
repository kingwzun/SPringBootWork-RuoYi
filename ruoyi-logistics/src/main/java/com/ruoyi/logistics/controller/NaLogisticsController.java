package com.ruoyi.logistics.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.arrangement.domain.NaArrangement;
import com.ruoyi.arrangement.domain.vo.NaArrangementVO;
import com.ruoyi.arrangement.service.INaArrangementService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.laboratory.domain.NaLaboratory;
import com.ruoyi.laboratory.service.INaLaboratoryService;
import com.ruoyi.logistics.domain.vo.NaLogisticsVO;
import com.ruoyi.nucleicAcid.domain.NaPersonnel;
import com.ruoyi.nucleicAcid.service.INucleicAcidService;
import com.ruoyi.point.domain.NaPoint;
import com.ruoyi.point.service.INaPointService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.logistics.domain.NaLogistics;
import com.ruoyi.logistics.service.INaLogisticsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
/**
 * 物流信息Controller
 * 
 * @author ruoyi
 * @date 2023-01-01
 */
@Controller
@RequestMapping("/logistics/logisticsManager")
public class NaLogisticsController extends BaseController
{
    private String prefix = "logistics/logisticsManager";

    @Autowired
    private INaLogisticsService naLogisticsService;
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
    @RequiresPermissions("logistics:logisticsManager:view")
    @GetMapping()
    public String logisticsManager()
    {
        return prefix + "/logisticsManager";
    }

    /**
     * 查询物流信息列表
     */
    @RequiresPermissions("logistics:logisticsManager:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NaLogistics naLogistics)
    {
        startPage();

        List<NaLogisticsVO> list = naLogisticsService.selectNaLogisticsVOList(naLogistics);
        return getDataTable(list);
    }

    /**
     * 导出物流信息列表
     */
    @RequiresPermissions("logistics:logisticsManager:export")
    @Log(title = "物流信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NaLogistics naLogistics)
    {
        List<NaLogistics> list = naLogisticsService.selectNaLogisticsList(naLogistics);
        ExcelUtil<NaLogistics> util = new ExcelUtil<NaLogistics>(NaLogistics.class);
        return util.exportExcel(list, "物流信息数据");
    }

    /**
     * 新增物流信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("deliveryId",getSysUser().getUserId());
        mmap.put("laboratories",laboratoryService.selectNaLaboratoryList(new NaLaboratory()));
        mmap.put("points", pointService.selectNaPointList(new NaPoint()));

        return prefix + "/add";
    }

    /**
     * 新增保存物流信息
     */
    @RequiresPermissions("logistics:logisticsManager:add")
    @Log(title = "物流信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NaLogistics naLogistics)
    {
        System.out.println("NaLogisticsController.addSave");
        List<NucleicAcid> list =new ArrayList<>();
        List<NucleicAcid> nucleicAcidList = naLogistics.getNucleicAcidList();
        if(nucleicAcidList!=null){
            for (NucleicAcid acid : nucleicAcidList) {
                NucleicAcid nucleicAcid = nucleicAcidService.selectNucleicAcidByTubeId(acid.getTubeId());
                if (nucleicAcid==null){
                    return error("新增试管" + acid.getTubeId() + "'失败，因为试管未使用");
                }
                list.add(nucleicAcid);
            }
        }
        naLogistics.setNucleicAcidList(list);
        return toAjax(naLogisticsService.insertNaLogistics(naLogistics));
    }

    /**
     * 修改物流信息
     */
    @RequiresPermissions("logistics:logisticsManager:edit")
    @GetMapping("/edit/{logiId}")
    public String edit(@PathVariable("logiId") Long logiId, ModelMap mmap)
    {
        NaLogistics naLogistics = naLogisticsService.selectNaLogisticsByLogiId(logiId);
        mmap.put("naLogistics", naLogistics);

        mmap.put("laboratories",laboratoryService.selectNaLaboratoryList(new NaLaboratory()));
        mmap.put("points", pointService.selectNaPointList(new NaPoint()));

        return prefix + "/edit";
    }

    /**
     * 修改保存物流信息
     */
    @RequiresPermissions("logistics:logisticsManager:edit")
    @Log(title = "物流信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NaLogistics naLogistics)
    {

        List<NucleicAcid> nucleicAcidList = naLogistics.getNucleicAcidList();
        List<NucleicAcid> list =new ArrayList<>();
        if(nucleicAcidList!=null){
            for (NucleicAcid acid : nucleicAcidList) {
                NucleicAcid nucleicAcid = nucleicAcidService.selectNucleicAcidByTubeId(acid.getTubeId());
                if (nucleicAcid==null){
                    return error("新增试管" + acid.getTubeId() + "'失败，因为试管未使用");
                }
                list.add(nucleicAcid);
            }
        }
        naLogistics.setNucleicAcidList(list);
        return toAjax(naLogisticsService.updateNaLogistics(naLogistics));
    }

    /**
     * 删除物流信息
     */
    @RequiresPermissions("logistics:logisticsManager:remove")
    @Log(title = "物流信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(naLogisticsService.deleteNaLogisticsByLogiIds(ids));
    }

    /**
     * 选择任务安排
     */
    @GetMapping("/authArr")
    public String selectArr(ModelMap mmap)
    {
        System.out.println("NaLogisticsController.selectArr");

//        mmap.put("arrId",arrId);
        return prefix + "/selectArr";
    }

    /**
     * 查询任务安排列表
     */
    @RequiresPermissions("system:post:list")
    @PostMapping("/arrList")
    @ResponseBody
    public TableDataInfo arrList()
    {
        System.out.println("NaLogisticsController.arrList");

        startPage();
        NaArrangement arrangement = new NaArrangement();
        arrangement.setArrStatus("0");
        List<NaArrangementVO> list = naArrangementService.selectNaArrangementVOList(arrangement);

        return getDataTable(list);
    }

    /**
     * 自动生成物流信息
     */
    @RequiresPermissions("logistics:logisticsManager:add")
    @GetMapping("/addLogisticsByArr/{arrId}")
    public String addLogisticsByArr(@PathVariable("arrId") Long  arrId,ModelMap mmap)
    {
        System.out.println("NaLogisticsController.addLogisticsByArr "+arrId);

        NaLogistics logistics = naLogisticsService.generateLogisticsByArrId(arrId);
        mmap.put("naLogistics", logistics);

        mmap.put("deliveryName",getSysUser().getUserName());
        mmap.put("laboratories",laboratoryService.selectNaLaboratoryList(new NaLaboratory()));
        mmap.put("points", pointService.selectNaPointList(new NaPoint()));

        return prefix + "/addByArr";
    }

    /**
     * 送达检测点
     */
    @RequiresPermissions("logistics:logisticsManager:edit")
    @Log(title = "物流信息", businessType = BusinessType.UPDATE)
    @GetMapping("/deliveredPoint/{logiId}")
    @ResponseBody
    public AjaxResult deliveredPoint(@PathVariable("logiId") Long  logiId)
    {

        NaLogistics naLogistics = naLogisticsService.selectNaLogisticsByLogiId(logiId);
        if(naLogistics.getLogiPointTime()!=null){
            return toAjax(0);
        }
        Date date = new Date();
        naLogistics.setLogiPointTime(date);

        return toAjax(naLogisticsService.updateNaLogistics(naLogistics));
    }
    /**
     * 送达实验室
     */
    @RequiresPermissions("logistics:logisticsManager:edit")
    @Log(title = "送达实验室", businessType = BusinessType.UPDATE)
    @GetMapping("/deliveredLab/{logiId}")
    @ResponseBody
    public AjaxResult deliveredLab(@PathVariable("logiId") Long  logiId)
    {

        NaLogistics naLogistics = naLogisticsService.selectNaLogisticsByLogiId(logiId);
        if(naLogistics.getLogiLabTime()!=null){
            return toAjax(0);
        }
        Date date = new Date();
        naLogistics.setLogiLabTime(date);

        return toAjax(naLogisticsService.updateNaLogistics(naLogistics));
    }
}
