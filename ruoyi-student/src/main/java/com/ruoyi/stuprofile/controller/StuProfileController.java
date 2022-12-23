package com.ruoyi.stuprofile.controller;

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
import com.ruoyi.stuprofile.domain.StuProfile;
import com.ruoyi.stuprofile.service.IStuProfileService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生档案3Controller
 * 
 * @author ruoyi
 * @date 2022-11-19
 */
@Controller
@RequestMapping("/student/manageProfile")
public class StuProfileController extends BaseController
{
    private String prefix = "student/manageProfile";

    @Autowired
    private IStuProfileService stuProfileService;

    @RequiresPermissions("student:manageProfile:view")
    @GetMapping()
    public String manageProfile()
    {
        return prefix + "/manageProfile";
    }

    /**
     * 查询学生档案3列表
     */
    @RequiresPermissions("student:manageProfile:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StuProfile stuProfile)
    {
        startPage();
        List<StuProfile> list = stuProfileService.selectStuProfileList(stuProfile);
        return getDataTable(list);
    }

    /**
     * 导出学生档案3列表
     */
    @RequiresPermissions("student:manageProfile:export")
    @Log(title = "学生档案3", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StuProfile stuProfile)
    {
        List<StuProfile> list = stuProfileService.selectStuProfileList(stuProfile);
        ExcelUtil<StuProfile> util = new ExcelUtil<StuProfile>(StuProfile.class);
        return util.exportExcel(list, "学生档案3数据");
    }

    /**
     * 新增学生档案3
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生档案3
     */
    @RequiresPermissions("student:manageProfile:add")
    @Log(title = "学生档案3", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StuProfile stuProfile)
    {
        return toAjax(stuProfileService.insertStuProfile(stuProfile));
    }

    /**
     * 修改学生档案3
     */
    @RequiresPermissions("student:manageProfile:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        StuProfile stuProfile = stuProfileService.selectStuProfileByUserId(userId);
        mmap.put("stuProfile", stuProfile);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生档案3
     */
    @RequiresPermissions("student:manageProfile:edit")
    @Log(title = "学生档案3", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StuProfile stuProfile)
    {
        return toAjax(stuProfileService.updateStuProfile(stuProfile));
    }

    /**
     * 删除学生档案3
     */
    @RequiresPermissions("student:manageProfile:remove")
    @Log(title = "学生档案3", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(stuProfileService.deleteStuProfileByUserIds(ids));
    }
}
