package com.ruoyi.site.service;

import java.util.List;
import com.ruoyi.site.domain.NaSite;

/**
 * 检测站点Service接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface INaSiteService 
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
     * 批量删除检测站点
     * 
     * @param postIds 需要删除的检测站点主键集合
     * @return 结果
     */
    public int deleteNaSiteByPostIds(String postIds);

    /**
     * 删除检测站点信息
     * 
     * @param postId 检测站点主键
     * @return 结果
     */
    public int deleteNaSiteByPostId(Long postId);
}
