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
import com.ruoyi.dormitory.domain.DormitoryStumanage;
import com.ruoyi.dormitory.service.IDormitoryStumanageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住宿生信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-18
 */
@Controller
@RequestMapping("/dormitory/stumanage")
public class DormitoryStumanageController extends BaseController
{
    private String prefix = "dormitory/stumanage";

    @Autowired
    private IDormitoryStumanageService dormitoryStumanageService;

    @RequiresPermissions("dormitory:stumanage:view")
    @GetMapping()
    public String stumanage()
    {
        return prefix + "/stumanage";
    }

    /**
     * 查询住宿生信息列表
     */
    @RequiresPermissions("dormitory:stumanage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DormitoryStumanage dormitoryStumanage)
    {
        startPage();
        List<DormitoryStumanage> list = dormitoryStumanageService.selectDormitoryStumanageList(dormitoryStumanage);
        return getDataTable(list);
    }

    /**
     * 导出住宿生信息列表
     */
    @RequiresPermissions("dormitory:stumanage:export")
    @Log(title = "住宿生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DormitoryStumanage dormitoryStumanage)
    {
        List<DormitoryStumanage> list = dormitoryStumanageService.selectDormitoryStumanageList(dormitoryStumanage);
        ExcelUtil<DormitoryStumanage> util = new ExcelUtil<DormitoryStumanage>(DormitoryStumanage.class);
        return util.exportExcel(list, "住宿生信息数据");
    }

    /**
     * 新增住宿生信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存住宿生信息
     */
    @RequiresPermissions("dormitory:stumanage:add")
    @Log(title = "住宿生信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DormitoryStumanage dormitoryStumanage)
    {
        return toAjax(dormitoryStumanageService.insertDormitoryStumanage(dormitoryStumanage));
    }

    /**
     * 修改住宿生信息
     */
    @RequiresPermissions("dormitory:stumanage:edit")
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") Long sid, ModelMap mmap)
    {
        DormitoryStumanage dormitoryStumanage = dormitoryStumanageService.selectDormitoryStumanageBySid(sid);
        mmap.put("dormitoryStumanage", dormitoryStumanage);
        return prefix + "/edit";
    }

    /**
     * 修改保存住宿生信息
     */
    @RequiresPermissions("dormitory:stumanage:edit")
    @Log(title = "住宿生信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DormitoryStumanage dormitoryStumanage)
    {
        return toAjax(dormitoryStumanageService.updateDormitoryStumanage(dormitoryStumanage));
    }

    /**
     * 删除住宿生信息
     */
    @RequiresPermissions("dormitory:stumanage:remove")
    @Log(title = "住宿生信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dormitoryStumanageService.deleteDormitoryStumanageBySids(ids));
    }
}
