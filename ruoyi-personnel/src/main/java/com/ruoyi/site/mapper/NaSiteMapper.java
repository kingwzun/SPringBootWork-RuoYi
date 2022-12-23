package com.ruoyi.site.mapper;

import java.util.List;
import com.ruoyi.site.domain.NaSite;
import com.ruoyi.site.domain.UserPost;

/**
 * 检测站点Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface NaSiteMapper 
{
    /**
     * 查询检测站点
     * 
     * @param postId 检测站点主键
     * @return 检测站点
     */
    public NaSite selectNaSiteByPostId(Long postId);

    /**
     * 查询检测站点列表
     * 
     * @param naSite 检测站点
     * @return 检测站点集合
     */
    public List<NaSite> selectNaSiteList(NaSite naSite);

    /**
     * 新增检测站点
     * 
     * @param naSite 检测站点
     * @return 结果
     */
    public int insertNaSite(NaSite naSite);

    /**
     * 修改检测站点
     * 
     * @param naSite 检测站点
     * @return 结果
     */
    public int updateNaSite(NaSite naSite);

    /**
     * 删除检测站点
     * 
     * @param postId 检测站点主键
     * @return 结果
     */
    public int deleteNaSiteByPostId(Long postId);

    /**
     * 批量删除检测站点
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNaSiteByPostIds(String[] postIds);

    /**
     * 批量删除医务人员分配
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserPostByPostIds(String[] postIds);
    
    /**
     * 批量新增医务人员分配
     * 
     * @param userPostList 医务人员分配列表
     * @return 结果
     */
    public int batchUserPost(List<UserPost> userPostList);
    

    /**
     * 通过检测站点主键删除医务人员分配信息
     * 
     * @param postId 检测站点ID
     * @return 结果
     */
    public int deleteUserPostByPostId(Long postId);
}
