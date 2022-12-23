package com.ruoyi.dormitory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.dormitory.domain.DormitoryStumanage;
import com.ruoyi.dormitory.mapper.DormitoryManageMapper;
import com.ruoyi.dormitory.domain.DormitoryManage;
import com.ruoyi.dormitory.service.IDormitoryManageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 宿舍Service业务层处理
 *
 * @author ruoyi
 * @date 2022-11-19
 */
@Service
public class DormitoryManageServiceImpl implements IDormitoryManageService
{
    @Autowired
    private DormitoryManageMapper dormitoryManageMapper;

    /**
     * 查询宿舍
     *
     * @param id 宿舍主键
     * @return 宿舍
     */
    @Override
    public DormitoryManage selectDormitoryManageById(Long id)
    {
        return dormitoryManageMapper.selectDormitoryManageById(id);
    }

    /**
     * 查询宿舍列表
     *
     * @param dormitoryManage 宿舍
     * @return 宿舍
     */
    @Override
    public List<DormitoryManage> selectDormitoryManageList(DormitoryManage dormitoryManage)
    {
        return dormitoryManageMapper.selectDormitoryManageList(dormitoryManage);
    }

    /**
     * 新增宿舍
     *
     * @param dormitoryManage 宿舍
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDormitoryManage(DormitoryManage dormitoryManage)
    {
        dormitoryManage.setCreateTime(DateUtils.getNowDate());
        int rows = dormitoryManageMapper.insertDormitoryManage(dormitoryManage);
        insertDormitoryStumanage(dormitoryManage);
        return rows;
    }

    /**
     * 修改宿舍
     *
     * @param dormitoryManage 宿舍
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDormitoryManage(DormitoryManage dormitoryManage)
    {
        dormitoryManage.setUpdateTime(DateUtils.getNowDate());
        dormitoryManageMapper.deleteDormitoryStumanageByDormitoryNum(dormitoryManage.getId());
        insertDormitoryStumanage(dormitoryManage);
        return dormitoryManageMapper.updateDormitoryManage(dormitoryManage);
    }

    /**
     * 批量删除宿舍
     *
     * @param ids 需要删除的宿舍主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDormitoryManageByIds(String ids)
    {
        dormitoryManageMapper.deleteDormitoryStumanageByDormitoryNums(Convert.toStrArray(ids));
        return dormitoryManageMapper.deleteDormitoryManageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除宿舍信息
     *
     * @param id 宿舍主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDormitoryManageById(Long id)
    {
        dormitoryManageMapper.deleteDormitoryStumanageByDormitoryNum(id);
        return dormitoryManageMapper.deleteDormitoryManageById(id);
    }

    /**
     * 新增住宿生信息信息
     *
     * @param dormitoryManage 宿舍对象
     */
    public void insertDormitoryStumanage(DormitoryManage dormitoryManage)
    {
        List<DormitoryStumanage> dormitoryStumanageList = dormitoryManage.getDormitoryStumanageList();
        Long id = dormitoryManage.getId();
        if (StringUtils.isNotNull(dormitoryStumanageList))
        {
            List<DormitoryStumanage> list = new ArrayList<DormitoryStumanage>();
            for (DormitoryStumanage dormitoryStumanage : dormitoryStumanageList)
            {
                dormitoryStumanage.setDormitoryNum(id);
                list.add(dormitoryStumanage);
            }
            if (list.size() > 0)
            {
                dormitoryManageMapper.batchDormitoryStumanage(list);
            }
        }
    }
}
