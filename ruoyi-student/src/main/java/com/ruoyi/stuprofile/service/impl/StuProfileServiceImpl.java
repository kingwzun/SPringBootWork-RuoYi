package com.ruoyi.stuprofile.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.stuprofile.mapper.StuProfileMapper;
import com.ruoyi.stuprofile.domain.StuProfile;
import com.ruoyi.stuprofile.service.IStuProfileService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生档案3Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-19
 */
@Service
public class StuProfileServiceImpl implements IStuProfileService 
{
    @Autowired
    private StuProfileMapper stuProfileMapper;

    /**
     * 查询学生档案3
     * 
     * @param userId 学生档案3主键
     * @return 学生档案3
     */
    @Override
    @DataScope(deptAlias = "s", userAlias = "s")
    public StuProfile selectStuProfileByUserId(Long userId)
    {
        return stuProfileMapper.selectStuProfileByUserId(userId);
    }

    /**
     * 查询学生档案3列表
     * 
     * @param stuProfile 学生档案3
     * @return 学生档案3
     */
    @Override
    @DataScope(deptAlias = "s", userAlias = "s")
    public List<StuProfile> selectStuProfileList(StuProfile stuProfile)
    {
        return stuProfileMapper.selectStuProfileList(stuProfile);
    }

    /**
     * 新增学生档案3
     * 
     * @param stuProfile 学生档案3
     * @return 结果
     */
    @Override
    public int insertStuProfile(StuProfile stuProfile)
    {
        return stuProfileMapper.insertStuProfile(stuProfile);
    }

    /**
     * 修改学生档案3
     * 
     * @param stuProfile 学生档案3
     * @return 结果
     */
    @Override
    public int updateStuProfile(StuProfile stuProfile)
    {
        return stuProfileMapper.updateStuProfile(stuProfile);
    }

    /**
     * 批量删除学生档案3
     * 
     * @param userIds 需要删除的学生档案3主键
     * @return 结果
     */
    @Override
    public int deleteStuProfileByUserIds(String userIds)
    {
        return stuProfileMapper.deleteStuProfileByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除学生档案3信息
     * 
     * @param userId 学生档案3主键
     * @return 结果
     */
    @Override
    public int deleteStuProfileByUserId(Long userId)
    {
        return stuProfileMapper.deleteStuProfileByUserId(userId);
    }
}
