package com.ruoyi.nucleicAcid.controller;

import java.util.LinkedList;
import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.nucleicAcid.domain.NaPersonnel;

import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import com.ruoyi.nucleicAcid.service.INucleicAcidService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 核酸信息Controller
 * 
 * @author ruoyi
 * @date 2022-11-22
 */
@Controller
@RequestMapping("/nucleicAcid/acid")
public class NucleicAcidController extends BaseController {
    private String prefix = "nucleicAcid/acid";
    @Autowired
    private ISysUserService userService;
    @Autowired
    private INucleicAcidService nucleicAcidService;

    @Autowired
    private ISysDeptService iSysDeptService;

    @RequiresPermissions("nucleicAcid:acid:view")
    @GetMapping()
    public String acid() {
        return prefix + "/acid";
    }

    /**
     * 查询核酸信息列表
     */
    @RequiresPermissions("nucleicAcid:acid:list")
    @PostMapping("/list")
    @ResponseBody
    @DataScope(deptAlias = "a")
    public TableDataInfo list(NucleicAcid nucleicAcid) {
        startPage();
        List<NucleicAcid> list = nucleicAcidService.selectNucleicAcidList(nucleicAcid);
        return getDataTable(list);
    }

    /**
     * 导出核酸信息列表
     */
    @RequiresPermissions("nucleicAcid:acid:export")
    @Log(title = "核酸信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NucleicAcid nucleicAcid) {
        List<NucleicAcid> list = nucleicAcidService.selectNucleicAcidList(nucleicAcid);
        ExcelUtil<NucleicAcid> util = new ExcelUtil<NucleicAcid>(NucleicAcid.class);
        return util.exportExcel(list, "核酸信息数据");
    }

    /**
     * 新增核酸信息
     */
    @GetMapping("/add")
    public String add( ModelMap mmap) {
        // 取身份信息
        SysUser user = getSysUser();
        SysDept sysDept = iSysDeptService.selectDeptById(user.getDeptId());
        mmap.put("deptName", sysDept.getDeptName());
        mmap.put("deptId", user.getDeptId());
        return prefix + "/add";
    }

    /**
     * 新增保存核酸信息
     */
    @RequiresPermissions("nucleicAcid:acid:add")
    @Log(title = "核酸信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NucleicAcid nucleicAcid) {
        List<NaPersonnel> naPersonnelList = nucleicAcid.getNaPersonnelList();
        if(naPersonnelList!=null){
            for(int i=0;i<naPersonnelList.size();i++) {
                NaPersonnel naPersonnel=naPersonnelList.get(i);

                SysUser user = userService.selectSysUserByPerIdentityTypeANDpIdentity(naPersonnel.getPerIdentityType(), naPersonnel.getPerIdentity());
                if(user==null){
                    return error("新增人员" + naPersonnel.getPerIdentity() + "'失败，因为用户不存在");
                }
                System.out.println(naPersonnel+"   xxxxxxxxxxxxxxxxxxxx");
                //设置上用户id
                naPersonnel.setUserId(user.getUserId().toString());
                naPersonnelList.set(i,naPersonnel);
            }
            nucleicAcid.setNaPersonnelList(naPersonnelList);
        }
        // 取身份信息
        SysUser user = getSysUser();
        SysDept sysDept = iSysDeptService.selectDeptById(user.getDeptId());
        nucleicAcid.setDeptId(user.getDeptId());
        nucleicAcid.setDeptName(sysDept.getDeptName());
        return toAjax(nucleicAcidService.insertNucleicAcid(nucleicAcid));
    }

    /**
     * 修改核酸信息
     */
    @RequiresPermissions("nucleicAcid:acid:edit")
    @GetMapping("/edit/{naId}")
    public String edit(@PathVariable("naId") Long naId, ModelMap mmap) {
        NucleicAcid nucleicAcid = nucleicAcidService.selectNucleicAcidByNaId(naId);
        mmap.put("nucleicAcid", nucleicAcid);
        SysDept sysDept = iSysDeptService.selectDeptById(nucleicAcid.getDeptId());
        mmap.put("deptName", sysDept.getDeptName());
//        System.out.println(mmap.get("deptName"));
//        System.out.println(sysDept.getDeptName());

        return prefix + "/edit";
    }

    /**
     * 修改保存核酸信息
     */
    @RequiresPermissions("nucleicAcid:acid:edit")
    @Log(title = "核酸信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NucleicAcid nucleicAcid) {

        List<NaPersonnel> naPersonnelList = nucleicAcid.getNaPersonnelList();
        if(naPersonnelList!=null){
            for (int i = 0; i < naPersonnelList.size(); i++) {
                NaPersonnel naPersonnel = naPersonnelList.get(i);

                SysUser user = userService.selectSysUserByPerIdentityTypeANDpIdentity(naPersonnel.getPerIdentityType(), naPersonnel.getPerIdentity());
                if (user == null) {
                    return error("修改人员" + naPersonnel.getPerIdentity() + "'失败，因为用户不存在");
                }
                System.out.println(naPersonnel + "   xxxxxxxxxxxxxxxxxxxx");
                //设置上用户id
                naPersonnel.setUserId(user.getUserId().toString());
                naPersonnelList.set(i, naPersonnel);
            }
            nucleicAcid.setNaPersonnelList(naPersonnelList);
        }
        return toAjax(nucleicAcidService.updateNucleicAcid(nucleicAcid));
    }

    /**
     * 删除核酸信息
     */
    @RequiresPermissions("nucleicAcid:acid:remove")
    @Log(title = "核酸信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(nucleicAcidService.deleteNucleicAcidByNaIds(ids));
    }

    /**
     * 受检人员记录详情
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("nucleicAcid:acid:detail")
    @Log(title = "受检人员记录详情", businessType = BusinessType.OTHER)
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        NucleicAcid nucleicAcid = nucleicAcidService.selectNucleicAcidByNaId(id);
        mmap.put("nucleicAcid", nucleicAcid);
//        System.out.println(nucleicAcid);

        List<SysUser> sysUserList = new LinkedList<>();
        for (NaPersonnel naPersonnel : nucleicAcid.getNaPersonnelList()) {
            SysUser sysUser = userService.selectSysUserByPerIdentityTypeANDpIdentity(naPersonnel.getPerIdentityType(), naPersonnel.getPerIdentity());
            System.out.println(sysUser);

            if (sysUser == null) {
                sysUser.setSex(naPersonnel.getPerIdentityType());
                sysUser.setRemark(naPersonnel.getPerIdentity());

            }
            sysUserList.add(sysUser);
        }
        mmap.put("sysUserList", sysUserList);
        return prefix + "/detail";
    }
    /**
     * 校验用户
     */
    @PostMapping("/checkPerIdentity")
    @ResponseBody
    public String checkPerIdentity(NucleicAcid nucleicAcid)
    {
        List<NaPersonnel> naPersonnelList = nucleicAcid.getNaPersonnelList();
        NaPersonnel naPersonnel = naPersonnelList.get(naPersonnelList.size() - 1);
        SysUser user = userService.selectSysUserByPerIdentityTypeANDpIdentity(naPersonnel.getPerIdentityType(), naPersonnel.getPerIdentity());
        if(user==null){
            return "1";
        }
         return "0";

//        SysUser sysUser = new SysUser();
//
//        String s = userService.checkLoginNameUnique(sysUser);
////        return userService.checkUserAllowed();
//        return s;
    }

}
