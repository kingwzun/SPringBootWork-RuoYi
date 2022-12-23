package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.userPost.domain.UserPost;
import com.ruoyi.userPost.service.IUserPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.service.ISysPostService;

/**
 * 岗位信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/post")
public class SysPostController extends BaseController
{
    private String prefix = "system/post";

    @Autowired
    private ISysPostService postService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IUserPostService userPostService;
    @RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/post";
    }

    @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPost post)
    {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:post:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPost post)
    {
        List<SysPost> list = postService.selectPostList(post);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        return util.exportExcel(list, "岗位数据");
    }

    @RequiresPermissions("system:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(postService.deletePostByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }



    /**
     * 新增岗位
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存岗位
     */
    @RequiresPermissions("system:post:add")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysPost post)
    {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy(getLoginName());
        return toAjax(postService.insertPost(post));
    }
    /**
     * 打开到医生分配信息页面
     */
    @RequiresPermissions("system:post:authUser")
    @GetMapping("/listColumn/{postId}")
    public String detail(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        mmap.put("post", postService.selectPostById(postId));
        List<UserPost> userPosts = userPostService.selectUserPostByPostId(postId);
        System.out.println(userPosts.size()+"dfddfdfd");
        ArrayList<SysUser> users = new ArrayList<>();
        if(userPosts.size()>0){
            for(UserPost userPost :userPosts){
                users.add( userService.selectUserById(userPost.getUserId()));
            }

        }
        mmap.put("users",users);
//        List<SysUser> sysUsers = postService.SelectUserById(postId);
//        System.out.println(sysUsers.size());
//        SysUser sysUser = userService.selectSysUserByPerIdentityTypeANDpIdentity(naPersonnel.getPerIdentityType(), naPersonnel.getPerIdentity());
//        System.out.println(sysUser);
//        mmap.put("doctor", dormitoryManageService.selectDormitoryManageAll());
        return prefix+"/detail";
    }
    /**
     * 修改岗位
     */
    @RequiresPermissions("system:post:edit")
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysPost post)
    {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
        {
            return error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
        {
            return error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy(getLoginName());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 校验岗位名称
     */
    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public String checkPostNameUnique(SysPost post)
    {
        return postService.checkPostNameUnique(post);
    }

    /**
     * 校验岗位编码
     */
    @PostMapping("/checkPostCodeUnique")
    @ResponseBody
    public String checkPostCodeUnique(SysPost post)
    {
        return postService.checkPostCodeUnique(post);
    }

    /**
     * 分配用户
     */
    @RequiresPermissions("system:post:authUser")
    @GetMapping("/authUser/{postId}")
    public String authUser(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        System.out.println(postId+"dfsdf");
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     */
    @RequiresPermissions("system:post:list")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysPost post,SysUser user)
    {
        startPage();
//        mmap.get(post);
        Long postId = post.getPostId();
        System.out.println(postId+"3333333333333333333");

//        Object post = mmap.get("post");

        List<SysUser> users=new ArrayList<>();
        for (UserPost userPost : userPostService.selectUserPostByPostId(postId)) {
            SysUser sysUser = userService.selectUserById(userPost.getUserId());
            users.add(sysUser);
//            System.out.println(user);
        }
        System.out.println(users.size()+"3333");

        System.out.println( userPostService.selectUserPostByPostId(postId)+"dfdsf");

        List<SysUser> list = userService.selectAllocatedList(user);
        System.out.println(list.size()+"fdsfsd");
        return getDataTable(users);
    }

    /**
     * 取消授权
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(SysUserPost sysUserPost)
    {
//        postService.deletePostByIds()
//        userPostService.deleteUserPostByUserIds()
        UserPost userPost = new UserPost();
        userPost.setPostId(sysUserPost.getPostId());
        userPost.setUserId(sysUserPost.getUserId());
        return toAjax(userPostService.deleteUserPost(userPost));
    }

//    /**不实现=========================================================
//     * 批量取消授权
//     */
//    @RequiresPermissions("system:role:edit")
//    @Log(title = "角色管理", businessType = BusinessType.GRANT)
//    @PostMapping("/authUser/cancelAll")
//    @ResponseBody
//    public AjaxResult cancelAuthUserAll(Long roleId, String userIds)
//    {
//        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
//    }

    /**
     * 选择用户
     */
    @GetMapping("/authUser/selectUser/{postId}")
    public String selectUser(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        mmap.put("post",postService.selectPostById(postId));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("system:post:list")
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysPost post,SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        System.out.println(list.size()+"2222222");
        //        mmap.get(post);
        Long postId = post.getPostId();
//        System.out.println(postId+"22222222");

//        Object post = mmap.get("post");

//        List<SysUser> users=new ArrayList<>();
//        for (UserPost userPost : userPostService.selectUserPostByPostId(postId)) {
//            SysUser sysUser = userService.selectUserById(userPost.getUserId());
//            list.remove(sysUser);
//        }
        //只有对照表里面有改医生，就不显示
        for (UserPost userPost : userPostService.selectUserPostList(new UserPost())) {
            SysUser sysUser = userService.selectUserById(userPost.getUserId());
            list.remove(sysUser);
        }
//        for (SysUser sysUser : list) {
//            if()
//        }


        System.out.println(list.size()+"fdsfsd");

        return getDataTable(list);
    }

    /**
     * 批量选择用户授权
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(SysPost post, String userIds)
    {
        Long postId = post.getPostId();
//        roleService.checkRoleDataScope(roleId);
//        return toAjax(roleService.insertAuthUsers(roleId, userIds));
//        postService.checkPostCodeUnique()
//        postService.checkPostNameUnique(postId);
        System.out.println(userIds+"___________________________");
        Integer num=0;
        for(String userId:userIds.split(",")){

            System.out.println(userId);
            UserPost userPost = new UserPost();
            userPost.setUserId(new Long(userId));
            userPost.setPostId(post.getPostId());
            userPostService.insertUserPost(userPost);
            num+=1;
        }
//        .insertUserPost
//        System.out.println(userIds+"userIds");
//        roleService.checkRoleDataScope(roleId);
        return toAjax(num);
    }
}
