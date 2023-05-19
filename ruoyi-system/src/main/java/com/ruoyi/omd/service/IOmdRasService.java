package com.ruoyi.omd.service;

import java.util.List;
import com.ruoyi.omd.domain.OmdRas;

/**
 * 售后信息Service接口
 *
 * @author loner
 * @date 2023-05-09
 */
public interface IOmdRasService
{
    /**
     * 查询售后信息
     *
     * @param rasId 售后信息主键
     * @return 售后信息
     */
    public OmdRas selectOmdRasByRasId(Long rasId);

    /**
     * 查询售后信息列表
     *
     * @param omdRas 售后信息
     * @return 售后信息集合
     */
    public List<OmdRas> selectOmdRasList(OmdRas omdRas);

    /**
     * 新增售后信息
     *
     * @param omdRas 售后信息
     * @return 结果
     */
    public int insertOmdRas(OmdRas omdRas);

    /**
     * 修改售后信息
     *
     * @param omdRas 售后信息
     * @return 结果
     */
    public int updateOmdRas(OmdRas omdRas);

    /**
     * 批量删除售后信息
     *
     * @param rasIds 需要删除的售后信息主键集合
     * @return 结果
     */
    public int deleteOmdRasByRasIds(Long[] rasIds);

    /**
     * 删除售后信息信息
     *
     * @param rasId 售后信息主键
     * @return 结果
     */
    public int deleteOmdRasByRasId(Long rasId);
}

