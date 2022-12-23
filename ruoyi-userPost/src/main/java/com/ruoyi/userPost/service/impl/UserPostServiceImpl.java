package com.ruoyi.userPost.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.userPost.mapper.UserPostMapper;
import com.ruoyi.userPost.domain.UserPost;
import com.ruoyi.userPost.service.IUserPostService;
import com.ruoyi.common.core.text.Convert;

/**
 * 医务人员分配Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class UserPostServiceImpl implements IUserPostService 
{
    @Autowired
    private UserPostMapper userPostMapper;

    /**
     * 查询医务人员分配
     * 
     * @param userId 医务人员分配主键
     * @return 医务人员分配
     */
    @Override
    public UserPost selectUserPostByUserId(Long userId)
    {
        return userPostMapper.selectUserPostByUserId(userId);
    }
    /**
     * 查询医务人员分配
     *
     * @param postId 站点分配主键
     * @return 医务人员分配
     */
    public List<UserPost> selectUserPostByPostId(Long postId){
        return userPostMapper.selectUserPostByPostId(postId);
    }
    /**
     * 查询医务人员分配列表
     * 
     * @param userPost 医务人员分配
     * @return 医务人员分配
     */
    @Override
    public List<UserPost> selectUserPostList(UserPost userPost)
    {
        return userPostMapper.selectUserPostList(userPost);
    }

    /**
     * 新增医务人员分配
     * 
     * @param userPost 医务人员分配
     * @return 结果
     */
    @Override
    public int insertUserPost(UserPost userPost)
    {
        return userPostMapper.insertUserPost(userPost);
    }

    /**
     * 修改医务人员分配
     * 
     * @param userPost 医务人员分配
     * @return 结果
     */
    @Override
    public int updateUserPost(UserPost userPost)
    {
        return userPostMapper.updateUserPost(userPost);
    }

    /**
     * 批量删除医务人员分配
     * 
     * @param userIds 需要删除的医务人员分配主键
     * @return 结果
     */
    @Override
    public int deleteUserPostByUserIds(String userIds)
    {
        return userPostMapper.deleteUserPostByUserIds(Convert.toStrArray(userIds));
    }

    /**
     * 删除医务人员分配信息
     * 
     * @param userId 医务人员分配主键
     * @return 结果
     */
    @Override
    public int deleteUserPostByUserId(Long userId)
    {
        return userPostMapper.deleteUserPostByUserId(userId);
    }
    /**
     * 删除用户和站点关联信息
     *
     * @param userPost 用户和站点关联信息
     * @return 结果
     */
    public int deleteUserPost(UserPost userPost){
        return  userPostMapper.deleteUserPost(userPost);
    }
}
