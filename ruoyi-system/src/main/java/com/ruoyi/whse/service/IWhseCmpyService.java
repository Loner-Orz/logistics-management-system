package com.ruoyi.whse.service;

import java.util.List;
import com.ruoyi.whse.domain.WhseCmpy;

/**
 * 往来公司信息Service接口
 *
 * @author ruoyi
 * @date 2023-01-14
 */
public interface IWhseCmpyService
{
    /**
     * 查询往来公司信息
     *
     * @param cmpyId 往来公司信息主键
     * @return 往来公司信息
     */
    public WhseCmpy selectWhseCmpyByCmpyId(Long cmpyId);

    /**
     * 查询往来公司信息列表
     *
     * @param whseCmpy 往来公司信息
     * @return 往来公司信息集合
     */
    public List<WhseCmpy> selectWhseCmpyList(WhseCmpy whseCmpy);

    /**
     * 新增往来公司信息
     *
     * @param whseCmpy 往来公司信息
     * @return 结果
     */
    public int insertWhseCmpy(WhseCmpy whseCmpy);

    /**
     * 修改往来公司信息
     *
     * @param whseCmpy 往来公司信息
     * @return 结果
     */
    public int updateWhseCmpy(WhseCmpy whseCmpy);

    /**
     * 批量删除往来公司信息
     *
     * @param cmpyIds 需要删除的往来公司信息主键集合
     * @return 结果
     */
    public int deleteWhseCmpyByCmpyIds(Long[] cmpyIds);

    /**
     * 删除往来公司信息信息
     *
     * @param cmpyId 往来公司信息主键
     * @return 结果
     */
    public int deleteWhseCmpyByCmpyId(Long cmpyId);
}
