package com.ruoyi.whse.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.whse.domain.WhseStore;

/**
 * 仓库信息Service接口
 *
 * @author ruoyi
 * @date 2023-01-14
 */
public interface IWhseStoreService
{
    /**
     * 查询仓库信息
     *
     * @param storeId 仓库信息主键
     * @return 仓库信息
     */
    public WhseStore selectWhseStoreByStoreId(Long storeId);

    /**
     * 查询仓库信息列表
     *
     * @param whseStore 仓库信息
     * @return 仓库信息集合
     */
    public List<WhseStore> selectWhseStoreList(WhseStore whseStore);

    /**
     * 新增仓库信息
     *
     * @param whseStore 仓库信息
     * @return 结果
     */
    public int insertWhseStore(WhseStore whseStore);

    /**
     * 修改仓库信息
     *
     * @param whseStore 仓库信息
     * @return 结果
     */
    public int updateWhseStore(WhseStore whseStore);

    /**
     * 批量删除仓库信息
     *
     * @param storeIds 需要删除的仓库信息主键集合
     * @return 结果
     */
    public int deleteWhseStoreByStoreIds(Long[] storeIds);

    /**
     * 删除仓库信息信息
     *
     * @param storeId 仓库信息主键
     * @return 结果
     */
    public int deleteWhseStoreByStoreId(Long storeId);

    /**
     * 根据所有仓库
     *
     * @return 仓库集合信息
     */
    public List<WhseStore> selectWhseStoreAll();
}
