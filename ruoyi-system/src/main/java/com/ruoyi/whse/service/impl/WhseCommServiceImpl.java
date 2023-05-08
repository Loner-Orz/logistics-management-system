package com.ruoyi.whse.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.whse.mapper.WhseCommMapper;
import com.ruoyi.whse.domain.WhseComm;
import com.ruoyi.whse.service.IWhseCommService;

/**
 * 商品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-01-13
 */
@Service
public class WhseCommServiceImpl implements IWhseCommService
{
    @Autowired
    private WhseCommMapper whseCommMapper;

    /**
     * 查询商品信息
     *
     * @param commId 商品信息主键
     * @return 商品信息
     */
    @Override
    public WhseComm selectWhseCommByCommId(Long commId)
    {
        return whseCommMapper.selectWhseCommByCommId(commId);
    }

    /**
     * 查询商品信息列表
     *
     * @param whseComm 商品信息
     * @return 商品信息
     */
    @Override
    public List<WhseComm> selectWhseCommList(WhseComm whseComm)
    {
        return whseCommMapper.selectWhseCommList(whseComm);
    }

    /**
     * 新增商品信息
     *
     * @param whseComm 商品信息
     * @return 结果
     */
    @Override
    public int insertWhseComm(WhseComm whseComm)
    {
        whseComm.setCreateTime(DateUtils.getNowDate());
        return whseCommMapper.insertWhseComm(whseComm);
    }

    /**
     * 修改商品信息
     *
     * @param whseComm 商品信息
     * @return 结果
     */
    @Override
    public int updateWhseComm(WhseComm whseComm)
    {
        whseComm.setUpdateTime(DateUtils.getNowDate());
        return whseCommMapper.updateWhseComm(whseComm);
    }

    /**
     * 批量删除商品信息
     *
     * @param commIds 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteWhseCommByCommIds(Long[] commIds)
    {
        return whseCommMapper.deleteWhseCommByCommIds(commIds);
    }

    /**
     * 删除商品信息信息
     *
     * @param commId 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteWhseCommByCommId(Long commId)
    {
        return whseCommMapper.deleteWhseCommByCommId(commId);
    }

    /**
     * 下拉框获取商品
     *
     * @return 商品集合信息
     */
    @Override
    public List<WhseComm> selectWhseCommAll() {
        return whseCommMapper.selectWhseCommAll();
    }
}
