package com.ruoyi.whse.service;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.whse.domain.DTO.StoreCommDto;
import com.ruoyi.whse.domain.WhseStoreComm;

import java.util.List;

/**
 * 仓库和商品关联Service接口
 *
 * @author ruoyi
 * @date 2023-03-26
 */
public interface IWhseStoreCommService {
    /**
     * 根据仓库ID查询商品列表
     *
     * @param storeId 仓库ID
     * @return 商品信息列表
     */
    public List<StoreCommDto> selectWhseCommByStoreId(Long storeId);

    /**
     * 根据条件查询商品列表
     *
     * @param commDto 查询条件
     * @return 商品信息列表
     */
    public List<StoreCommDto> selectCommDto(StoreCommDto commDto);

    /**
     * 根据storeComm更新商品信息
     *
     * @param storeComm 更新条件
     * @return 更新商品信息
     */
    int updateComm(WhseStoreComm storeComm);

    /**
     * 根据storeComm新增商品信息
     *
     * @param storeComm 新增条件
     * @return 新增商品
     */
    int insertComm(WhseStoreComm storeComm);

    /**
     * 根据storeId,commId查询仓库中对应商品数量
     *
     * @param storeId 仓库Id commId 商品Id
     * @return 商品数量
     */
    Long getCommCount(Long storeId, Long commId);

    /**
     * 批量删除仓库商品
     *
     * @param commIds 需要删除的商品信息主键集合
     * @return 结果
     */
    int deleteCommByCommIds(Long storeId, Long[] commIds);

    /**
     * 删除仓库商品
     *
     * @param commId 需要删除的商品信息主键
     * @return 结果
     */
    int deleteCommByCommId(Long storeId, Long commId);
}
