package com.ruoyi.whse.mapper;

import com.ruoyi.whse.domain.WhseCmpy;
import java.util.List;

/**
 * 往来公司信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-01-14
 */
public interface WhseCmpyMapper
{
    /**
     * 查询往来公司信息
     *
     * @param cmpyId 往来公司信息主键
     * @return 往来公司信息
     */
    WhseCmpy selectWhseCmpyByCmpyId(Long cmpyId);

    /**
     * 查询往来公司信息列表
     *
     * @param whseCmpy 往来公司信息
     * @return 往来公司信息集合
     */
    List<WhseCmpy> selectWhseCmpyList(WhseCmpy whseCmpy);

    /**
     * 新增往来公司信息
     *
     * @param whseCmpy 往来公司信息
     * @return 结果
     */
    int insertWhseCmpy(WhseCmpy whseCmpy);

    /**
     * 修改往来公司信息
     *
     * @param whseCmpy 往来公司信息
     * @return 结果
     */
    int updateWhseCmpy(WhseCmpy whseCmpy);

    /**
     * 删除往来公司信息
     *
     * @param cmpyId 往来公司信息主键
     * @return 结果
     */
    int deleteWhseCmpyByCmpyId(Long cmpyId);

    /**
     * 批量删除往来公司信息
     *
     * @param cmpyIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWhseCmpyByCmpyIds(Long[] cmpyIds);
}
