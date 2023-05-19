package com.ruoyi.omd.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.omd.mapper.OmdRasMapper;
import com.ruoyi.omd.domain.OmdRas;
import com.ruoyi.omd.service.IOmdRasService;

/**
 * 售后信息Service业务层处理
 *
 * @author loner
 * @date 2023-05-09
 */
@Service
public class OmdRasServiceImpl implements IOmdRasService
{
    @Autowired
    private OmdRasMapper omdRasMapper;

    /**
     * 查询售后信息
     *
     * @param rasId 售后信息主键
     * @return 售后信息
     */
    @Override
    public OmdRas selectOmdRasByRasId(Long rasId)
    {
        return omdRasMapper.selectOmdRasByRasId(rasId);
    }

    /**
     * 查询售后信息列表
     *
     * @param omdRas 售后信息
     * @return 售后信息
     */
    @Override
    public List<OmdRas> selectOmdRasList(OmdRas omdRas)
    {
        return omdRasMapper.selectOmdRasList(omdRas);
    }

    /**
     * 新增售后信息
     *
     * @param omdRas 售后信息
     * @return 结果
     */
    @Override
    public int insertOmdRas(OmdRas omdRas)
    {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1,1);
        omdRas.setRasId(idGenerator.nextId());
        omdRas.setCreateTime(DateUtils.getNowDate());
        return omdRasMapper.insertOmdRas(omdRas);
    }

    /**
     * 修改售后信息
     *
     * @param omdRas 售后信息
     * @return 结果
     */
    @Override
    public int updateOmdRas(OmdRas omdRas)
    {
        omdRas.setUpdateTime(DateUtils.getNowDate());
        return omdRasMapper.updateOmdRas(omdRas);
    }

    /**
     * 批量删除售后信息
     *
     * @param rasIds 需要删除的售后信息主键
     * @return 结果
     */
    @Override
    public int deleteOmdRasByRasIds(Long[] rasIds)
    {
        return omdRasMapper.deleteOmdRasByRasIds(rasIds);
    }

    /**
     * 删除售后信息信息
     *
     * @param rasId 售后信息主键
     * @return 结果
     */
    @Override
    public int deleteOmdRasByRasId(Long rasId)
    {
        return omdRasMapper.deleteOmdRasByRasId(rasId);
    }
}

