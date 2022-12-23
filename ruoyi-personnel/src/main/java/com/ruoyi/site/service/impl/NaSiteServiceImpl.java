package com.ruoyi.site.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.site.domain.UserPost;
import com.ruoyi.site.mapper.NaSiteMapper;
import com.ruoyi.site.domain.NaSite;
import com.ruoyi.site.service.INaSiteService;
import com.ruoyi.common.core.text.Convert;

/**
 * 检测站点Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
@Service
public class NaSiteServiceImpl implements INaSiteService 
{
    @Autowired
    private NaSiteMapper naSiteMapper;

    /**
     * 查询检测站点
     * 
     * @param postId 检测站点主键
     * @return 检测站点
     */
    @Override
    public NaSite selectNaSiteByPostId(Long postId)
    {
        return naSiteMapper.selectNaSiteByPostId(postId);
    }

    /**
     * 查询检测站点列表
     * 
     * @param naSite 检测站点
     * @return 检测站点
     */
    @Override
    public List<NaSite> selectNaSiteList(NaSite naSite)
    {
        return naSiteMapper.selectNaSiteList(naSite);
    }

    /**
     * 新增检测站点
     * 
     * @param naSite 检测站点
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNaSite(NaSite naSite)
    {
        naSite.setCreateTime(DateUtils.getNowDate());
        int rows = naSiteMapper.insertNaSite(naSite);
        insertUserPost(naSite);
        return rows;
    }

    /**
     * 修改检测站点
     * 
     * @param naSite 检测站点
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNaSite(NaSite naSite)
    {
        naSite.setUpdateTime(DateUtils.getNowDate());
        naSiteMapper.deleteUserPostByPostId(naSite.getPostId());
        insertUserPost(naSite);
        return naSiteMapper.updateNaSite(naSite);
    }

    /**
     * 批量删除检测站点
     * 
     * @param postIds 需要删除的检测站点主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNaSiteByPostIds(String postIds)
    {
        naSiteMapper.deleteUserPostByPostIds(Convert.toStrArray(postIds));
        return naSiteMapper.deleteNaSiteByPostIds(Convert.toStrArray(postIds));
    }

    /**
     * 删除检测站点信息
     * 
     * @param postId 检测站点主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNaSiteByPostId(Long postId)
    {
        naSiteMapper.deleteUserPostByPostId(postId);
        return naSiteMapper.deleteNaSiteByPostId(postId);
    }

    /**
     * 新增医务人员分配信息
     * 
     * @param naSite 检测站点对象
     */
    public void insertUserPost(NaSite naSite)
    {
        List<UserPost> userPostList = naSite.getUserPostList();
        Long postId = naSite.getPostId();
        if (StringUtils.isNotNull(userPostList))
        {
            List<UserPost> list = new ArrayList<UserPost>();
            for (UserPost userPost : userPostList)
            {
                userPost.setPostId(postId);
                list.add(userPost);
            }
            if (list.size() > 0)
            {
                naSiteMapper.batchUserPost(list);
            }
        }
    }
}
