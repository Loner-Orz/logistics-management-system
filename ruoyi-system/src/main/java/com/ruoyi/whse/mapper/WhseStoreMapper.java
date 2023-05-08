package com.ruoyi.whse.mapper;

import java.util.List;
import com.ruoyi.whse.domain.WhseStore;

/**
 * 仓库信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-01-14
 */
public interface WhseStoreMapper
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
     * 删除仓库信息
     *
     * @param storeId 仓库信息主键
     * @return 结果
     */
    public int deleteWhseStoreByStoreId(Long storeId);

    /**
     * 批量删除仓库信息
     *
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWhseStoreByStoreIds(Long[] storeIds);

    /**
     * 通过 storeId 查询 storeName
     *
     * @param storeId 需要查询的仓库Id
     * @return 结果
     */
    public Long selectNameById(String storeId);

    /**
     * 通过 storeName 查询 storeId
     *
     * @param storeName 需要查询的仓库Name
     * @return 结果
     */
    public String selectIdByName(String storeName);

    /**
     * 根据所有仓库信息
     *
     * @return 仓库集合信息
     */
    public List<WhseStore> selectWhseStoreAll();
}
