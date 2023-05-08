package com.ruoyi.whse.mapper;

import com.ruoyi.whse.domain.WhseComm;
import java.util.List;

/**
 * 商品信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-01-13
 */
public interface WhseCommMapper
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
     * 删除商品信息
     *
     * @param commId 商品信息主键
     * @return 结果
     */
    int deleteWhseCommByCommId(Long commId);

    /**
     * 批量删除商品信息
     *
     * @param commIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWhseCommByCommIds(Long[] commIds);

    /**
     * 下拉框获取商品
     *
     * @return 商品集合信息
     */
    public List<WhseComm> selectWhseCommAll();
}
