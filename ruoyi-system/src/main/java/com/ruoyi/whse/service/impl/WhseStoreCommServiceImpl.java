package com.ruoyi.whse.service.impl;

import com.ruoyi.whse.domain.DTO.StoreCommDto;
import com.ruoyi.whse.domain.WhseStoreComm;
import com.ruoyi.whse.mapper.WhseCommMapper;
import com.ruoyi.whse.mapper.WhseStoreCommMapper;
import com.ruoyi.whse.mapper.WhseStoreMapper;
import com.ruoyi.whse.service.IWhseStoreCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓库和商品关联Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-26
 */
@Service
public class WhseStoreCommServiceImpl implements IWhseStoreCommService {

    @Autowired
    private WhseStoreMapper whseStoreMapper;

    @Autowired
    private WhseStoreCommMapper whseStoreCommMapper;

    @Autowired
    private WhseCommMapper whseCommMapper;

    /**
     * 根据仓库ID查询商品列表
     *
     * @param storeId 仓库ID
     * @return 商品信息列表
     */
    @Override
    public List<StoreCommDto> selectWhseCommByStoreId(Long storeId) {
        return whseStoreCommMapper.selectWhseCommListByStoreId(storeId);
    }

    /**
     * 根据条件查询商品列表
     *
     * @param commDto 查询条件
     * @return 商品信息列表
     */
    @Override
    public List<StoreCommDto> selectCommDto(StoreCommDto commDto) {
        return whseStoreCommMapper.selectCommDto(commDto);
    }

    /**
     * 根据storeComm更新商品信息
     *
     * @param storeComm 更新条件
     * @return 更新商品信息
     */
    @Override
    public int updateComm(WhseStoreComm storeComm) {
        return whseStoreCommMapper.updateComm(storeComm);
    }

    /**
     * 根据storeComm新增商品信息
     *
     * @param storeComm 新增条件
     * @return 新增商品
     */
    @Override
    public int insertComm(WhseStoreComm storeComm) {
        return whseStoreCommMapper.insertComm(storeComm);
    }

    /**
     * 根据storeId,commId查询仓库中对应商品数量
     *
     * @param storeId 仓库Id commId 商品Id
     * @return 商品数量
     */
    @Override
    public Long getCommCount(Long storeId, Long commId) {
        return whseStoreCommMapper.getCommCount(storeId,commId);
    }

    /**
     * 批量删除仓库商品
     *
     * @param commIds 需要删除的商品信息主键集合
     * @return 结果
     */
    @Override
    public int deleteCommByCommIds(Long storeId, Long[] commIds) {
        return whseStoreCommMapper.deleteCommByCommIds(storeId,commIds);
    }

    /**
     * 删除仓库商品
     *
     * @param commId 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteCommByCommId(Long storeId, Long commId) {
        return whseStoreCommMapper.deleteCommByCommId(storeId,commId);
    }
}
