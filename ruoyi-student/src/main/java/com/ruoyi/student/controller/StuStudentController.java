package com.ruoyi.student.controller;

import java.util.List;

import com.ruoyi.dormitory.domain.DormitoryStumanage;
import com.ruoyi.dormitory.service.IDormitoryManageService;
import com.ruoyi.dormitory.service.IDormitoryStumanageService;
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
import com.ruoyi.student.domain.StuStudent;
import com.ruoyi.student.service.IStuStudentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-17
 */
@Controller
@RequestMapping("/student/manage")
public class StuStudentController extends BaseController
{
    private String prefix = "student/manage";

    @Autowired
    private IStuStudentService stuStudentService;
    @Autowired
    private IDormitoryStumanageService dormitoryStumanageService;

    @RequiresPermissions("student:manage:view")
    @GetMapping()
    public String manage()
    {
        return prefix + "/manage";
    }

    /**
     * 查询学生信息列表
     */
    @RequiresPermissions("student:manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StuStudent stuStudent)
    {
        startPage();
        List<StuStudent> list = stuStudentService.selectStuStudentList(stuStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @RequiresPermissions("student:manage:export")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StuStudent stuStudent)
    {
        List<StuStudent> list = stuStudentService.selectStuStudentList(stuStudent);
        ExcelUtil<StuStudent> util = new ExcelUtil<StuStudent>(StuStudent.class);
        return util.exportExcel(list, "学生信息数据");
    }

    /**
     * 新增学生信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生信息
     */
    @RequiresPermissions("student:manage:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StuStudent stuStudent)
    {
        DormitoryStumanage dormitoryStumanage = new DormitoryStumanage();
        dormitoryStumanage.setSid(stuStudent.getStudentId());
        System.out.println(stuStudent.getStudentId()+" "+dormitoryStumanage.getSid());

        dormitoryStumanage.setStuName(stuStudent.getStudentName());
        System.out.println(stuStudent.getStudentName()+" "+dormitoryStumanage.getStuName());

        dormitoryStumanageService.insertDormitoryStumanage( dormitoryStumanage);
        return toAjax(stuStudentService.insertStuStudent(stuStudent));
    }

    /**
     * 修改学生信息
     */
    @RequiresPermissions("student:manage:edit")
    @GetMapping("/edit/{studentId}")
    public String edit(@PathVariable("studentId") Long studentId, ModelMap mmap)
    {
        StuStudent stuStudent = stuStudentService.selectStuStudentByStudentId(studentId);
        mmap.put("stuStudent", stuStudent);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生信息
     */
    @RequiresPermissions("student:manage:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StuStudent stuStudent)
    {
        return toAjax(stuStudentService.updateStuStudent(stuStudent));
    }

    /**
     * 删除学生信息
     */
    @RequiresPermissions("student:manage:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(stuStudentService.deleteStuStudentByStudentIds(ids));
    }
}
