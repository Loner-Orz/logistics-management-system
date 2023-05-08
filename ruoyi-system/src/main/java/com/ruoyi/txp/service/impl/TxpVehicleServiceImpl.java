package com.ruoyi.txp.service.impl;

import java.util.List;
import java.util.function.Consumer;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.whse.mapper.WhseStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.txp.mapper.TxpVehicleMapper;
import com.ruoyi.txp.domain.TxpVehicle;
import com.ruoyi.txp.service.ITxpVehicleService;

/**
 * 车辆信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-17
 */
@Service
public class TxpVehicleServiceImpl implements ITxpVehicleService
{
    @Autowired
    private TxpVehicleMapper txpVehicleMapper;

    @Autowired
    private WhseStoreMapper whseStoreMapper;

    /**
     * 查询车辆信息
     *
     * @param vehicleId 车辆信息主键
     * @return 车辆信息
     */
    @Override
    public TxpVehicle selectTxpVehicleByVehicleId(Long vehicleId)
    {
        return txpVehicleMapper.selectTxpVehicleByVehicleId(vehicleId);
    }

    /**
     * 查询车辆信息列表
     *
     * @param txpVehicle 车辆信息
     * @return 车辆信息
     */
    @Override
    public List<TxpVehicle> selectTxpVehicleList(TxpVehicle txpVehicle)
    {
        return txpVehicleMapper.selectTxpVehicleList(txpVehicle);
    }

    /**
     * 新增车辆信息
     *
     * @param txpVehicle 车辆信息
     * @return 结果
     */
    @Override
    public int insertTxpVehicle(TxpVehicle txpVehicle)
    {
        txpVehicle.setCreateTime(DateUtils.getNowDate());
        return txpVehicleMapper.insertTxpVehicle(txpVehicle);
    }

    /**
     * 修改车辆信息
     *
     * @param txpVehicle 车辆信息
     * @return 结果
     */
    @Override
    public int updateTxpVehicle(TxpVehicle txpVehicle)
    {
        txpVehicle.setUpdateTime(DateUtils.getNowDate());
        return txpVehicleMapper.updateTxpVehicle(txpVehicle);
    }

    /**
     * 批量删除车辆信息
     *
     * @param vehicleIds 需要删除的车辆信息主键
     * @return 结果
     */
    @Override
    public int deleteTxpVehicleByVehicleIds(Long[] vehicleIds)
    {
        return txpVehicleMapper.deleteTxpVehicleByVehicleIds(vehicleIds);
    }

    /**
     * 删除车辆信息信息
     *
     * @param vehicleId 车辆信息主键
     * @return 结果
     */
    @Override
    public int deleteTxpVehicleByVehicleId(Long vehicleId)
    {
        return txpVehicleMapper.deleteTxpVehicleByVehicleId(vehicleId);
    }

    /**
     * 展示车辆信息列表
     *
     * @param txpVehicle 车辆信息
     * @return 车辆信息集合
     */
    @Override
    public List<TxpVehicle> showTxpVehicleList(TxpVehicle txpVehicle) {
        List<TxpVehicle> vehicleList = txpVehicleMapper.selectTxpVehicleList(txpVehicle);
        vehicleList.forEach(V -> V.setVehicleSid(whseStoreMapper.selectNameById(String.valueOf(V.getVehicleSid()))));
        return vehicleList;
    }
}
