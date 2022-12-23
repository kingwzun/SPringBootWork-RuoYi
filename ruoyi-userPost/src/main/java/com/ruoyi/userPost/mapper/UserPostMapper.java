package com.ruoyi.userPost.mapper;

import java.util.List;
import com.ruoyi.userPost.domain.UserPost;

/**
 * 医务人员分配Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface UserPostMapper 
{
    /**
     * 查询医务人员分配
     * 
     * @param userId 医务人员分配主键
     * @return 医务人员分配
     */
    public UserPost selectUserPostByUserId(Long userId);

    /**
     * 查询医务人员分配
     *
     * @param postId 站点分配主键
     * @return 医务人员分配
     */
    public List<UserPost> selectUserPostByPostId(Long postId);

    /**
     * 查询医务人员分配列表
     * 
     * @param userPost 医务人员分配
     * @return 医务人员分配集合
     */
    public List<UserPost> selectUserPostList(UserPost userPost);

    /**
     * 新增医务人员分配
     * 
     * @param userPost 医务人员分配
     * @return 结果
     */
    public int insertUserPost(UserPost userPost);

    /**
     * 修改医务人员分配
     * 
     * @param userPost 医务人员分配
     * @return 结果
     */
    public int updateUserPost(UserPost userPost);

    /**
     * 删除医务人员分配
     * 
     * @param userId 医务人员分配主键
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);

    /**
     * 批量删除医务人员分配
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserPostByUserIds(String[] userIds);

    /**
     * 删除用户和站点关联信息
     *
     * @param userPost 用户和站点关联信息
     * @return 结果
     */
    public int deleteUserPost(UserPost userPost);
}
