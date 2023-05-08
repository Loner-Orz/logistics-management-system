package com.ruoyi.district.service.Impl;

import com.ruoyi.district.domain.District;
import com.ruoyi.district.mapper.DistrictMapper;
import com.ruoyi.district.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getDistrict() {
        List<District> list = districtMapper.getDistrict();
        return list;
    }

    @Override
    public String getNameByCode(String Code) {
        String name = districtMapper.getNameByCode(Code);
        return name;
    }

    @Override
    public String getCodeByName(String Name) {
        String code = districtMapper.getCodeByName(Name);
        return code;
    }
}
