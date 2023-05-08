package com.ruoyi.district.mapper;

import com.ruoyi.district.domain.District;
import java.util.List;

/**
 * 地区信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-01-13
 */
public interface DistrictMapper {

    //获取省的列表
    List<District> getProvince(String pid);

    //获取市的列表
    List<District> getCity(List<String> pid);

    //获取区县的列表
    List<District> getArea(List<String> pid);

    //获取地区信息列表
    List<District> getDistrict();

    //根据代码获取名字
    String getNameByCode(String code);

    //根据代码获取名字
    String getCodeByName(String name);
}

