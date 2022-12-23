package com.ruoyi.stuprofile.service;

import java.util.List;
import com.ruoyi.stuprofile.domain.StuProfile;

/**
 * 学生档案3Service接口
 * 
 * @author ruoyi
 * @date 2022-11-19
 */
public interface IStuProfileService 
{
    /**
     * 查询学生档案3
     * 
     * @param userId 学生档案3主键
     * @return 学生档案3
     */
    public StuProfile selectStuProfileByUserId(Long userId);

    /**
     * 查询学生档案3列表
     * 
     * @param stuProfile 学生档案3
     * @return 学生档案3集合
     */
    public List<StuProfile> selectStuProfileList(StuProfile stuProfile);

    /**
     * 新增学生档案3
     * 
     * @param stuProfile 学生档案3
     * @return 结果
     */
    public int insertStuProfile(StuProfile stuProfile);

    /**
     * 修改学生档案3
     * 
     * @param stuProfile 学生档案3
     * @return 结果
     */
    public int updateStuProfile(StuProfile stuProfile);

    /**
     * 批量删除学生档案3
     * 
     * @param userIds 需要删除的学生档案3主键集合
     * @return 结果
     */
    public int deleteStuProfileByUserIds(String userIds);

    /**
     * 删除学生档案3信息
     * 
     * @param userId 学生档案3主键
     * @return 结果
     */
    public int deleteStuProfileByUserId(Long userId);
}
