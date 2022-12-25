package com.ruoyi.laboratory.controller;

import java.util.List;
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
import com.ruoyi.laboratory.domain.NaLaboratory;
import com.ruoyi.laboratory.service.INaLaboratoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实验室管理Controller
 * 
 * @author ruoyi
 * @date 2022-12-25
 */
@Controller
@RequestMapping("/laboratory/laboratoryManage")
public class NaLaboratoryController extends BaseController
{
    private String prefix = "laboratory/laboratoryManage";

    @Autowired
    private INaLaboratoryService naLaboratoryService;

    @RequiresPermissions("laboratory:laboratoryManage:view")
    @GetMapping()
    public String laboratoryManage()
    {
        return prefix + "/laboratoryManage";
    }

    /**
     * 查询实验室管理列表
     */
    @RequiresPermissions("laboratory:laboratoryManage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NaLaboratory naLaboratory)
    {
        startPage();
        List<NaLaboratory> list = naLaboratoryService.selectNaLaboratoryList(naLaboratory);
        return getDataTable(list);
    }

    /**
     * 导出实验室管理列表
     */
    @RequiresPermissions("laboratory:laboratoryManage:export")
    @Log(title = "实验室管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NaLaboratory naLaboratory)
    {
        List<NaLaboratory> list = naLaboratoryService.selectNaLaboratoryList(naLaboratory);
        ExcelUtil<NaLaboratory> util = new ExcelUtil<NaLaboratory>(NaLaboratory.class);
        return util.exportExcel(list, "实验室管理数据");
    }

    /**
     * 新增实验室管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实验室管理
     */
    @RequiresPermissions("laboratory:laboratoryManage:add")
    @Log(title = "实验室管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NaLaboratory naLaboratory)
    {
        return toAjax(naLaboratoryService.insertNaLaboratory(naLaboratory));
    }

    /**
     * 修改实验室管理
     */
    @RequiresPermissions("laboratory:laboratoryManage:edit")
    @GetMapping("/edit/{labId}")
    public String edit(@PathVariable("labId") Long labId, ModelMap mmap)
    {
        NaLaboratory naLaboratory = naLaboratoryService.selectNaLaboratoryByLabId(labId);
        mmap.put("naLaboratory", naLaboratory);
        return prefix + "/edit";
    }

    /**
     * 修改保存实验室管理
     */
    @RequiresPermissions("laboratory:laboratoryManage:edit")
    @Log(title = "实验室管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NaLaboratory naLaboratory)
    {
        return toAjax(naLaboratoryService.updateNaLaboratory(naLaboratory));
    }

    /**
     * 删除实验室管理
     */
    @RequiresPermissions("laboratory:laboratoryManage:remove")
    @Log(title = "实验室管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(naLaboratoryService.deleteNaLaboratoryByLabIds(ids));
    }
}
