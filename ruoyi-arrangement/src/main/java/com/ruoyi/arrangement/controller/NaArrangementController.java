package com.ruoyi.arrangement.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.arrangement.domain.NaArrMedical;
import com.ruoyi.arrangement.service.INaArrMedicalService;
import com.ruoyi.common.core.domain.entity.SysUser;
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
        List<NaArrangement> list = naArrangementService.selectNaArrangementList(naArrangement);
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
    public String add()
    {
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
        NaArrangement naArrangement = naArrangementService.selectNaArrangementByArrId(arrId);
        mmap.put("naArrangement", naArrangement);
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

//    /**
//     * 打开到医生分配信息页面
//     */
//    @RequiresPermissions("arrangement:arrangementManager:authUser")
//    @GetMapping("/listColumn/{arrId}")
//    public String detail(@PathVariable("arrId") Long arrId, ModelMap mmap)
//    {
//        System.out.println("NaArrangementController.detail");
//
//        mmap.put("arrId",arrId);
//        List<SysUser> medicals = arrMedicalService.selectMedicalsByArrId(arrId);
//        mmap.put("medicals",medicals);
//
//        return prefix+"/detail";
//    }
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

//    /**
//     * 选择用户
//     */
//    @GetMapping("/authUser/selectUser/{postId}")
//    public String selectUser(@PathVariable("postId") Long postId, ModelMap mmap)
//    {
//        mmap.put("post",postService.selectPostById(postId));
//        return prefix + "/selectUser";
//    }
//
//    /**
//     * 查询未分配用户角色列表
//     */
//    @RequiresPermissions("system:post:list")
//    @PostMapping("/authUser/unallocatedList")
//    @ResponseBody
//    public TableDataInfo unallocatedList(SysPost post,SysUser user)
//    {
//        startPage();
//        List<SysUser> list = userService.selectUserList(user);
//        System.out.println(list.size()+"2222222");
//        //        mmap.get(post);
//        Long postId = post.getPostId();
////        System.out.println(postId+"22222222");
//
////        Object post = mmap.get("post");
//
////        List<SysUser> users=new ArrayList<>();
////        for (UserPost userPost : userPostService.selectUserPostByPostId(postId)) {
////            SysUser sysUser = userService.selectUserById(userPost.getUserId());
////            list.remove(sysUser);
////        }
//        //只有对照表里面有改医生，就不显示
//        for (UserPost userPost : userPostService.selectUserPostList(new UserPost())) {
//            SysUser sysUser = userService.selectUserById(userPost.getUserId());
//            list.remove(sysUser);
//        }
////        for (SysUser sysUser : list) {
////            if()
////        }
//
//
//        System.out.println(list.size()+"fdsfsd");
//
//        return getDataTable(list);
//    }
//
//    /**
//     * 批量选择用户授权
//     */
//    @RequiresPermissions("system:post:edit")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
//    @PostMapping("/authUser/selectAll")
//    @ResponseBody
//    public AjaxResult selectAuthUserAll(SysPost post, String userIds)
//    {
//        Long postId = post.getPostId();
////        roleService.checkRoleDataScope(roleId);
////        return toAjax(roleService.insertAuthUsers(roleId, userIds));
////        postService.checkPostCodeUnique()
////        postService.checkPostNameUnique(postId);
//        System.out.println(userIds+"___________________________");
//        Integer num=0;
//        for(String userId:userIds.split(",")){
//
//            System.out.println(userId);
//            UserPost userPost = new UserPost();
//            userPost.setUserId(new Long(userId));
//            userPost.setPostId(post.getPostId());
//            userPostService.insertUserPost(userPost);
//            num+=1;
//        }
////        .insertUserPost
////        System.out.println(userIds+"userIds");
////        roleService.checkRoleDataScope(roleId);
//        return toAjax(num);
//    }
}