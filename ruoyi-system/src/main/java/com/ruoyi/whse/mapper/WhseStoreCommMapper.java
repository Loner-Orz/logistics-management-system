package com.ruoyi.whse.mapper;

import com.ruoyi.whse.domain.DTO.StoreCommDto;
import com.ruoyi.whse.domain.WhseStoreComm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 仓库和商品关联Mapper接口
 *
 * @author ruoyi
 * @date 2023-03-19
 */
public interface WhseStoreCommMapper
{
    /**
     * 查询仓库关联的商品列表
     *
     * @param storeId 仓库Id
     * @return 仓库关联的商品列表
     */
    public List<StoreCommDto> selectWhseCommListByStoreId(Long storeId);

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
    public int updateComm(WhseStoreComm storeComm);

    /**
     * 根据storeComm新增商品信息
     *
     * @param storeComm 新增条件
     * @return 新增商品
     */
    public int insertComm(WhseStoreComm storeComm);

    /**
     * 根据storeId,commId查询仓库中对应商品数量
     *
     * @param storeId 仓库Id commId 商品Id
     * @return 商品数量
     */
    public Long getCommCount( @Param("storeId")Long storeId, @Param("commId")Long commId);

    /**
     * 批量删除仓库商品
     *
     * @param commIds 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteCommByCommIds( @Param("storeId")Long storeId,@Param("commIds")Long[] commIds);

    /**
     * 删除仓库商品
     *
     * @param commId 需要删除的商品信息主键
     * @return 结果
     */
    public int deleteCommByCommId(@Param("storeId") Long storeId, @Param("commId") Long commId);
}

