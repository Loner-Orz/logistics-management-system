package com.ruoyi.web.controller.district;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.district.domain.District;
import com.ruoyi.district.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 地区信息Controller
 *
 * @author ruoyi
 * @date 2023-01-18
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping("/getDistrict")
    public AjaxResult getDistrict() {
        List<District> list = districtService.getDistrict();
        if (list == null){
            return error("地区数据加载失败");
        }
        return success(list);
    }
}
