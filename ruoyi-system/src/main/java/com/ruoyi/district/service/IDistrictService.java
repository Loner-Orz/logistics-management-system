package com.ruoyi.district.service;

import com.ruoyi.district.domain.District;
import java.util.List;

/**
 * 地区信息Service接口
 *
 * @author ruoyi
 * @date 2023-01-18
 */
public interface IDistrictService {

    /**
     * 查询地区信息
     * @return
     */
    List<District> getDistrict();

    /**
     * 根据代码查名字
     * @param Code
     * @return
     */
    String getNameByCode(String Code);

    /**
     * 根据名字查代码
     * @param Name
     * @return
     */
    String getCodeByName(String Name);
}
