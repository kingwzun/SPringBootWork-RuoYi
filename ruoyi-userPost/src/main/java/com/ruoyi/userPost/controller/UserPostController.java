package com.ruoyi.userPost.controller;

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
import com.ruoyi.userPost.domain.UserPost;
import com.ruoyi.userPost.service.IUserPostService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医务人员分配Controller
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Controller
@RequestMapping("/post/userPostManage")
public class UserPostController extends BaseController
{
    private String prefix = "post/userPostManage";

    @Autowired
    private IUserPostService userPostService;

    @RequiresPermissions("post:userPostManage:view")
    @GetMapping()
    public String userPostManage()
    {
        return prefix + "/userPostManage";
    }

    /**
     * 查询医务人员分配列表
     */
    @RequiresPermissions("post:userPostManage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserPost userPost)
    {
        startPage();
        List<UserPost> list = userPostService.selectUserPostList(userPost);
        return getDataTable(list);
    }

    /**
     * 导出医务人员分配列表
     */
    @RequiresPermissions("post:userPostManage:export")
    @Log(title = "医务人员分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserPost userPost)
    {
        List<UserPost> list = userPostService.selectUserPostList(userPost);
        ExcelUtil<UserPost> util = new ExcelUtil<UserPost>(UserPost.class);
        return util.exportExcel(list, "医务人员分配数据");
    }

    /**
     * 新增医务人员分配
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医务人员分配
     */
    @RequiresPermissions("post:userPostManage:add")
    @Log(title = "医务人员分配", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserPost userPost)
    {
        return toAjax(userPostService.insertUserPost(userPost));
    }

    /**
     * 修改医务人员分配
     */
    @RequiresPermissions("post:userPostManage:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        UserPost userPost = userPostService.selectUserPostByUserId(userId);
        mmap.put("userPost", userPost);
        return prefix + "/edit";
    }

    /**
     * 修改保存医务人员分配
     */
    @RequiresPermissions("post:userPostManage:edit")
    @Log(title = "医务人员分配", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserPost userPost)
    {
        return toAjax(userPostService.updateUserPost(userPost));
    }

    /**
     * 删除医务人员分配
     */
    @RequiresPermissions("post:userPostManage:remove")
    @Log(title = "医务人员分配", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userPostService.deleteUserPostByUserIds(ids));
    }
}
