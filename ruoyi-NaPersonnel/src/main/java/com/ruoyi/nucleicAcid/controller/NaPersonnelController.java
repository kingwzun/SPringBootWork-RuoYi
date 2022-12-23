package com.ruoyi.nucleicAcid.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.nucleicAcid.domain.NucleicAcid;
import com.ruoyi.nucleicAcid.service.INucleicAcidService;
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
import com.ruoyi.nucleicAcid.domain.NaPersonnel;
import com.ruoyi.nucleicAcid.service.INaPersonnelService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 信息人员对照表Controller
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Controller
@RequestMapping("/naPersonnel/napersonnel")
public class NaPersonnelController extends BaseController
{
    private String prefix = "naPersonnel/napersonnel";

    @Autowired
    private INaPersonnelService naPersonnelService;
    @Autowired
    private INucleicAcidService nucleicAcidService;
    @RequiresPermissions("naPersonnel:napersonnel:view")
    @GetMapping()
    public String napersonnel()
    {
        return prefix + "/napersonnel";
    }

    /**
     * 查询信息人员对照表列表
     */
    @RequiresPermissions("naPersonnel:napersonnel:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NaPersonnel naPersonnel)
    {
        startPage();
        List<NaPersonnel> list = naPersonnelService.selectNaPersonnelList(naPersonnel);
        List<NucleicAcid> NucleicAcidList=new ArrayList<>();
        if (list.size()>0){
            for (NaPersonnel personnel : list) {
                NucleicAcid acid = nucleicAcidService.selectNucleicAcidByNaId(personnel.getNaId());
//                System.out.println(acid.getNaResult()+"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                if(acid.getNaResult()>0){
                    acid.setNaResult(1L);
                }
                NucleicAcidList.add(acid);

            }
        }

        System.out.println(NucleicAcidList.size());
        return getDataTable(NucleicAcidList);
    }

    /**
     * 导出信息人员对照表列表
     */
    @RequiresPermissions("naPersonnel:napersonnel:export")
    @Log(title = "信息人员对照表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NaPersonnel naPersonnel)
    {
        List<NaPersonnel> list = naPersonnelService.selectNaPersonnelList(naPersonnel);
        ExcelUtil<NaPersonnel> util = new ExcelUtil<NaPersonnel>(NaPersonnel.class);
        return util.exportExcel(list, "信息人员对照表数据");
    }

    /**
     * 新增信息人员对照表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存信息人员对照表
     */
    @RequiresPermissions("naPersonnel:napersonnel:add")
    @Log(title = "信息人员对照表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NaPersonnel naPersonnel)
    {
        return toAjax(naPersonnelService.insertNaPersonnel(naPersonnel));
    }

    /**
     * 修改信息人员对照表
     */
    @RequiresPermissions("naPersonnel:napersonnel:edit")
    @GetMapping("/edit/{naPerId}")
    public String edit(@PathVariable("naPerId") Long naPerId, ModelMap mmap)
    {
        NaPersonnel naPersonnel = naPersonnelService.selectNaPersonnelByNaPerId(naPerId);
        mmap.put("naPersonnel", naPersonnel);
        return prefix + "/edit";
    }

    /**
     * 修改保存信息人员对照表
     */
    @RequiresPermissions("naPersonnel:napersonnel:edit")
    @Log(title = "信息人员对照表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NaPersonnel naPersonnel)
    {
        return toAjax(naPersonnelService.updateNaPersonnel(naPersonnel));
    }

    /**
     * 删除信息人员对照表
     */
    @RequiresPermissions("naPersonnel:napersonnel:remove")
    @Log(title = "信息人员对照表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(naPersonnelService.deleteNaPersonnelByNaPerIds(ids));
    }
}
