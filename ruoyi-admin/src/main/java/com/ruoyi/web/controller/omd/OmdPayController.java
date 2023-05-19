package com.ruoyi.web.controller.omd;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.omd.domain.OmdPay;
import com.ruoyi.omd.service.IOmdPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 支付订单信息Controller
 *
 * @author loner
 * @date 2023-04-30
 */
@RestController
@RequestMapping("/omd/pay")
public class OmdPayController extends BaseController {
    @Autowired
    private IOmdPayService omdPayService;

    /**
     * 查询支付信息列表
     */
    @PreAuthorize("@ss.hasPermi('omd:pay:list')")
    @GetMapping("/list")
    public TableDataInfo list(OmdPay omdPay)
    {
        startPage();
        List<OmdPay> list = omdPayService.selectOmdPayList(omdPay);
        return getDataTable(list);
    }
}
