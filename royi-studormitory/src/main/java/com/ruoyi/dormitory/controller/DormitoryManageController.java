package com.ruoyi.dormitory.controller;

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
import com.ruoyi.dormitory.domain.DormitoryManage;
import com.ruoyi.dormitory.service.IDormitoryManageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宿舍Controller
 *
 * @author ruoyi
 * @date 2022-11-19
 */
@Controller
@RequestMapping("/dormitorymanage/manage")
public class DormitoryManageController extends BaseController
{
    private String prefix = "dormitorymanage/manage";

    @Autowired
    private IDormitoryManageService dormitoryManageService;

    @RequiresPermissions("dormitorymanage:manage:view")
    @GetMapping()
    public String manage()
    {
        return prefix + "/manage";
    }

    /**
     * 查询宿舍列表
     */
    @RequiresPermissions("dormitorymanage:manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DormitoryManage dormitoryManage)
    {
        startPage();
        List<DormitoryManage> list = dormitoryManageService.selectDormitoryManageList(dormitoryManage);
        return getDataTable(list);
    }

    /**
     * 导出宿舍列表
     */
    @RequiresPermissions("dormitorymanage:manage:export")
    @Log(title = "宿舍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DormitoryManage dormitoryManage)
    {
        List<DormitoryManage> list = dormitoryManageService.selectDormitoryManageList(dormitoryManage);
        ExcelUtil<DormitoryManage> util = new ExcelUtil<DormitoryManage>(DormitoryManage.class);
        return util.exportExcel(list, "宿舍数据");
    }

    /**
     * 新增宿舍
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存宿舍
     */
    @RequiresPermissions("dormitorymanage:manage:add")
    @Log(title = "宿舍", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DormitoryManage dormitoryManage)
    {
        return toAjax(dormitoryManageService.insertDormitoryManage(dormitoryManage));
    }

    /**
     * 修改宿舍
     */
    @RequiresPermissions("dormitorymanage:manage:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DormitoryManage dormitoryManage = dormitoryManageService.selectDormitoryManageById(id);
        mmap.put("dormitoryManage", dormitoryManage);
        return prefix + "/edit";
    }

    /**
     * 修改保存宿舍
     */
    @RequiresPermissions("dormitorymanage:manage:edit")
    @Log(title = "宿舍", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DormitoryManage dormitoryManage)
    {
        return toAjax(dormitoryManageService.updateDormitoryManage(dormitoryManage));
    }

    /**
     * 删除宿舍
     */
    @RequiresPermissions("dormitorymanage:manage:remove")
    @Log(title = "宿舍", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dormitoryManageService.deleteDormitoryManageByIds(ids));
    }
}
