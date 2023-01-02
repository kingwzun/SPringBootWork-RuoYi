package com.ruoyi.arrangement.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.arrangement.domain.NaArrMedical;
import com.ruoyi.arrangement.domain.vo.NaArrangementVO;
import com.ruoyi.arrangement.service.INaArrMedicalService;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.laboratory.domain.NaLaboratory;
import com.ruoyi.laboratory.service.INaLaboratoryService;
import com.ruoyi.point.domain.NaPoint;
import com.ruoyi.point.service.INaPointService;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;
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
import com.ruoyi.arrangement.domain.NaArrangement;
import com.ruoyi.arrangement.service.INaArrangementService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务安排Controller
 * 
 * @author ruoyi
 * @date 2022-12-26
 */
@Controller
@RequestMapping("/arrangement/arrangementManager")
public class NaArrangementController extends BaseController
{
    private String prefix = "arrangement/arrangementManager";

    @Autowired
    private INaArrangementService naArrangementService;
    @Autowired
    private INaArrMedicalService arrMedicalService;
    @Autowired
    private INaLaboratoryService laboratoryService;
    @Autowired
    private INaPointService pointService;
    @Autowired
    private  ISysPostService postService;
    @Autowired
    private ISysUserService userService;
    @RequiresPermissions("arrangement:arrangementManager:view")
    @GetMapping()
    public String arrangementManager()
    {
        return prefix + "/arrangementManager";
    }

    /**
     * 查询任务安排列表
     */
    @RequiresPermissions("arrangement:arrangementManager:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NaArrangement naArrangement)
    {
        startPage();
//        List<NaArrangement> arrangements =
        List<NaArrangementVO> list=naArrangementService.selectNaArrangementVOList(naArrangement);
        return getDataTable(list);
    }

    /**
     * 导出任务安排列表
     */
    @RequiresPermissions("arrangement:arrangementManager:export")
    @Log(title = "任务安排", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NaArrangement naArrangement)
    {
        List<NaArrangement> list = naArrangementService.selectNaArrangementList(naArrangement);
        ExcelUtil<NaArrangement> util = new ExcelUtil<NaArrangement>(NaArrangement.class);
        return util.exportExcel(list, "任务安排数据");
    }

    /**
     * 新增任务安排
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("laboratories",laboratoryService.selectNaLaboratoryList(new NaLaboratory()));
        mmap.put("points", pointService.selectNaPointList(new NaPoint()));
        mmap.put("deliveryId","");
        mmap.put("deliveryName","");
        return prefix + "/add";
    }

    /**
     * 新增保存任务安排
     */
    @RequiresPermissions("arrangement:arrangementManager:add")
    @Log(title = "任务安排", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NaArrangement naArrangement)
    {
        return toAjax(naArrangementService.insertNaArrangement(naArrangement));
    }

    /**
     * 修改任务安排
     */
    @RequiresPermissions("arrangement:arrangementManager:edit")
    @GetMapping("/edit/{arrId}")
    public String edit(@PathVariable("arrId") Long arrId, ModelMap mmap)
    {
        System.out.println("NaArrangementController.edit");
        NaArrangement naArrangement = naArrangementService.selectNaArrangementByArrId(arrId);
        mmap.put("naArrangement", naArrangement);
        String deliveryName="";
        SysUser sysUser = userService.selectUserById(naArrangement.getDeliveryId());
        if (sysUser==null){
            deliveryName="未查询到物流人员";
        }
        else {
            deliveryName=sysUser.getUserName();
        }


        mmap.put("deliveryName",deliveryName);
        mmap.put("laboratories",laboratoryService.selectNaLaboratoryList(new NaLaboratory()));
        mmap.put("points", pointService.selectNaPointList(new NaPoint()));
        return prefix + "/edit";
    }

    /**
     * 修改保存任务安排
     */
    @RequiresPermissions("arrangement:arrangementManager:edit")
    @Log(title = "任务安排", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NaArrangement naArrangement)
    {
        System.out.println("NaArrangementController.editSave");
        return toAjax(naArrangementService.updateNaArrangement(naArrangement));
    }

    /**
     * 删除任务安排
     */
    @RequiresPermissions("arrangement:arrangementManager:remove")
    @Log(title = "任务安排", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(naArrangementService.deleteNaArrangementByArrIds(ids));
    }

    /**
     * 分配用户
     */
    @RequiresPermissions("arrangement:arrangementManager:authUser")
    @GetMapping("/authUser/{arrId}")
    public String authUser(@PathVariable("arrId") Long arrId, ModelMap mmap)
    {
        System.out.println("NaArrangementController.authUser");

        mmap.put("arrId", arrId);
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("arrangement:arrangementManager:authUser")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(Long arrId,SysUser user)
    {
        System.out.println("NaArrangementController.allocatedList "+arrId);

        startPage();
        List<SysUser> medicals = arrMedicalService.selectMedicalsByArrId(arrId);
        return getDataTable(medicals);
    }

    /**
     * 取消授权
     */
    @RequiresPermissions("arrangement:arrangementManager:authUser")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(NaArrMedical arrMedical)
    {
        System.out.println("NaArrangementController.cancelAuthUser");
        System.out.println(arrMedical.getArrId()+arrMedical.getMedId());
//        System.out.println(arrMedicalService.deleteNaArrMedical(arrMedical));
        return toAjax(arrMedicalService.deleteNaArrMedical(arrMedical));
    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{arrId}")
    public String selectUser(@PathVariable("arrId") Long arrId, ModelMap mmap)
    {
        System.out.println("NaArrangementController.selectUser");

        mmap.put("arrId",arrId);
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     * (只有对照表里面有改医生，就不显示)
     */
    @RequiresPermissions("system:post:list")
    @PostMapping("/authUser/unAllocatedList")
    @ResponseBody
    public TableDataInfo unAllocatedList()
    {
        System.out.println("NaArrangementController.unAllocatedList");

        startPage();
        List<SysUser> list = arrMedicalService.SelectUnAllocatedList();
        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/saveAll")
    @ResponseBody
    public AjaxResult saveAuthUserAll(Long arrId, String userIds)
    {
        System.out.println("NaArrangementController.selectAuthUserAll");

        return  toAjax(arrMedicalService.saveAuthUserAll(arrId,userIds));
    }


    /**
     * 选择物流人员
     */
    @GetMapping("/authDelivery")
    public String selectDelivery(ModelMap mmap)
    {
        System.out.println("NaArrangementController.selectDelivery");

//        mmap.put("arrId",arrId);
        return prefix + "/selectDelivery";
    }

    /**
     * 查询物流人员角色列表
     */
    @RequiresPermissions("system:post:list")
    @PostMapping("/authDelivery/deliveryList")
    @ResponseBody
    public TableDataInfo deliveryList()
    {
        System.out.println("NaArrangementController.deliveryList");

        startPage();

        List<SysUser> list = arrMedicalService.selectDeliveryList();
        return getDataTable(list);
    }
    /**
     * 角色状态修改
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(NaArrangement naArrangement)
    {
        System.out.println("NaArrangementController.changeStatus");
        return toAjax(naArrangementService.changeStatus(naArrangement));
    }
}
