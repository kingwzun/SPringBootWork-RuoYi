package com.ruoyi.site.controller;

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
import com.ruoyi.site.domain.NaSite;
import com.ruoyi.site.service.INaSiteService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测站点Controller
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Controller
@RequestMapping("/site/siteManage")
public class NaSiteController extends BaseController
{
    private String prefix = "site/siteManage";

    @Autowired
    private INaSiteService naSiteService;

    @RequiresPermissions("site:siteManage:view")
    @GetMapping()
    public String siteManage()
    {
        return prefix + "/siteManage";
    }

    /**
     * 查询检测站点列表
     */
    @RequiresPermissions("site:siteManage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NaSite naSite)
    {
        startPage();
        List<NaSite> list = naSiteService.selectNaSiteList(naSite);
        return getDataTable(list);
    }

    /**
     * 导出检测站点列表
     */
    @RequiresPermissions("site:siteManage:export")
    @Log(title = "检测站点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NaSite naSite)
    {
        List<NaSite> list = naSiteService.selectNaSiteList(naSite);
        ExcelUtil<NaSite> util = new ExcelUtil<NaSite>(NaSite.class);
        return util.exportExcel(list, "检测站点数据");
    }

    /**
     * 新增检测站点
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检测站点
     */
    @RequiresPermissions("site:siteManage:add")
    @Log(title = "检测站点", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NaSite naSite)
    {
        return toAjax(naSiteService.insertNaSite(naSite));
    }

    /**
     * 修改检测站点
     */
    @RequiresPermissions("site:siteManage:edit")
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        NaSite naSite = naSiteService.selectNaSiteByPostId(postId);
        mmap.put("naSite", naSite);
        return prefix + "/edit";
    }

    /**
     * 修改保存检测站点
     */
    @RequiresPermissions("site:siteManage:edit")
    @Log(title = "检测站点", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NaSite naSite)
    {
        return toAjax(naSiteService.updateNaSite(naSite));
    }

    /**
     * 删除检测站点
     */
    @RequiresPermissions("site:siteManage:remove")
    @Log(title = "检测站点", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(naSiteService.deleteNaSiteByPostIds(ids));
    }
}
