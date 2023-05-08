package com.ruoyi.whse.service;

import java.util.List;
import com.ruoyi.whse.domain.WhseComm;

/**
 * 商品信息Service接口
 *
 * @author ruoyi
 * @date 2023-01-13
 */
public interface IWhseCommService
{
    /**
     * 查询商品信息
     *
     * @param commId 商品信息主键
     * @return 商品信息
     */
    WhseComm selectWhseCommByCommId(Long commId);

    /**
     * 查询商品信息列表
     *
     * @param whseComm 商品信息
     * @return 商品信息集合
     */
    List<WhseComm> selectWhseCommList(WhseComm whseComm);

    /**
     * 新增商品信息
     *
     * @param whseComm 商品信息
     * @return 结果
     */
    int insertWhseComm(WhseComm whseComm);

    /**
     * 修改商品信息
     *
     * @param whseComm 商品信息
     * @return 结果
     */
    int updateWhseComm(WhseComm whseComm);

    /**
     * 批量删除商品信息
     *
     * @param commIds 需要删除的商品信息主键集合
     * @return 结果
     */
    int deleteWhseCommByCommIds(Long[] commIds);

    /**
     * 删除商品信息信息
     *
     * @param commId 商品信息主键
     * @return 结果
     */
    int deleteWhseCommByCommId(Long commId);

    /**
     * 下拉框获取商品
     *
     * @return 商品集合信息
     */
    public List<WhseComm> selectWhseCommAll();
}
