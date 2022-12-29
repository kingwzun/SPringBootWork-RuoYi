package com.ruoyi.point.controller;

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
import com.ruoyi.point.domain.NaPoint;
import com.ruoyi.point.service.INaPointService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 检测点管理Controller
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
@Controller
@RequestMapping("/point/pointManager")
public class NaPointController extends BaseController
{
    private String prefix = "point/pointManager";

    @Autowired
    private INaPointService naPointService;

    @RequiresPermissions("point:pointManager:view")
    @GetMapping()
    public String pointManager()
    {
        return prefix + "/pointManager";
    }

    /**
     * 查询检测点管理列表
     */
    @RequiresPermissions("point:pointManager:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NaPoint naPoint)
    {
        startPage();
        List<NaPoint> list = naPointService.selectNaPointList(naPoint);
        return getDataTable(list);
    }

    /**
     * 导出检测点管理列表
     */
    @RequiresPermissions("point:pointManager:export")
    @Log(title = "检测点管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NaPoint naPoint)
    {
        List<NaPoint> list = naPointService.selectNaPointList(naPoint);
        ExcelUtil<NaPoint> util = new ExcelUtil<NaPoint>(NaPoint.class);
        return util.exportExcel(list, "检测点管理数据");
    }

    /**
     * 新增检测点管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检测点管理
     */
    @RequiresPermissions("point:pointManager:add")
    @Log(title = "检测点管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NaPoint naPoint)
    {
        return toAjax(naPointService.insertNaPoint(naPoint));
    }

    /**
     * 修改检测点管理
     */
    @RequiresPermissions("point:pointManager:edit")
    @GetMapping("/edit/{pointId}")
    public String edit(@PathVariable("pointId") Long pointId, ModelMap mmap)
    {
        NaPoint naPoint = naPointService.selectNaPointByPointId(pointId);
        mmap.put("naPoint", naPoint);
        return prefix + "/edit";
    }

    /**
     * 修改保存检测点管理
     */
    @RequiresPermissions("point:pointManager:edit")
    @Log(title = "检测点管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NaPoint naPoint)
    {
        return toAjax(naPointService.updateNaPoint(naPoint));
    }

    /**
     * 删除检测点管理
     */
    @RequiresPermissions("point:pointManager:remove")
    @Log(title = "检测点管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(naPointService.deleteNaPointByPointIds(ids));
    }
}
