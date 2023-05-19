package com.ruoyi.omd.mapper;

import java.util.List;
import com.ruoyi.omd.domain.OmdLogistics;

/**
 * 物流信息Mapper接口
 *
 * @author loner
 * @date 2023-05-09
 */
public interface OmdLogisticsMapper
{
    /**
     * 查询物流信息
     *
     * @param logisticsId 物流信息主键
     * @return 物流信息
     */
    public OmdLogistics selectOmdLogisticsByLogisticsId(Long logisticsId);

    /**
     * 查询物流信息列表
     *
     * @param omdLogistics 物流信息
     * @return 物流信息集合
     */
    public List<OmdLogistics> selectOmdLogisticsList(OmdLogistics omdLogistics);

    /**
     * 新增物流信息
     *
     * @param omdLogistics 物流信息
     * @return 结果
     */
    public int insertOmdLogistics(OmdLogistics omdLogistics);

    /**
     * 修改物流信息
     *
     * @param omdLogistics 物流信息
     * @return 结果
     */
    public int updateOmdLogistics(OmdLogistics omdLogistics);

    /**
     * 删除物流信息
     *
     * @param logisticsId 物流信息主键
     * @return 结果
     */
    public int deleteOmdLogisticsByLogisticsId(Long logisticsId);

    /**
     * 批量删除物流信息
     *
     * @param logisticsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOmdLogisticsByLogisticsIds(Long[] logisticsIds);
}

