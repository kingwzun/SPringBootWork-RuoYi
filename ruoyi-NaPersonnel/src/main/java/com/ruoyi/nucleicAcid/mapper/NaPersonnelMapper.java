package com.ruoyi.nucleicAcid.mapper;

import java.util.List;
import com.ruoyi.nucleicAcid.domain.NaPersonnel;

/**
 * 信息人员对照表Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-25
 */
public interface NaPersonnelMapper 
{
    /**
     * 查询信息人员对照表
     * 
     * @param naPerId 信息人员对照表主键
     * @return 信息人员对照表
     */
    public NaPersonnel selectNaPersonnelByNaPerId(Long naPerId);

    /**
     * 查询信息人员对照表列表
     * 
     * @param naPersonnel 信息人员对照表
     * @return 信息人员对照表集合
     */
    public List<NaPersonnel> selectNaPersonnelList(NaPersonnel naPersonnel);

    /**
     * 新增信息人员对照表
     * 
     * @param naPersonnel 信息人员对照表
     * @return 结果
     */
    public int insertNaPersonnel(NaPersonnel naPersonnel);

    /**
     * 修改信息人员对照表
     * 
     * @param naPersonnel 信息人员对照表
     * @return 结果
     */
    public int updateNaPersonnel(NaPersonnel naPersonnel);

    /**
     * 删除信息人员对照表
     * 
     * @param naPerId 信息人员对照表主键
     * @return 结果
     */
    public int deleteNaPersonnelByNaPerId(Long naPerId);

    /**
     * 批量删除信息人员对照表
     * 
     * @param naPerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNaPersonnelByNaPerIds(String[] naPerIds);
}
