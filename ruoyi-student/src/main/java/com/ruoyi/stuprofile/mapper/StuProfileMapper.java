package com.ruoyi.stuprofile.mapper;

import java.util.List;
import com.ruoyi.stuprofile.domain.StuProfile;

/**
 * 学生档案3Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-19
 */
public interface StuProfileMapper 
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
     * 删除学生档案3
     * 
     * @param userId 学生档案3主键
     * @return 结果
     */
    public int deleteStuProfileByUserId(Long userId);

    /**
     * 批量删除学生档案3
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStuProfileByUserIds(String[] userIds);
}
