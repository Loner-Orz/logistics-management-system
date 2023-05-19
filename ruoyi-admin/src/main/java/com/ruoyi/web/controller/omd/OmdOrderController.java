package com.ruoyi.web.controller.omd;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.omd.domain.OmdOrder;
import com.ruoyi.omd.service.IOmdOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单信息Controller
 *
 * @author loner
 * @date 2023-04-30
 */
@RestController
@RequestMapping("/omd/order")
public class OmdOrderController extends BaseController
{
    @Autowired
    private IOmdOrderService omdOrderService;

    /**
     * 查询订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('omd:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(OmdOrder omdOrder)
    {
        startPage();
        List<OmdOrder> list = omdOrderService.selectOmdOrderList(omdOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('omd:order:export')")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmdOrder omdOrder)
    {
        List<OmdOrder> list = omdOrderService.selectOmdOrderList(omdOrder);
        ExcelUtil<OmdOrder> util = new ExcelUtil<OmdOrder>(OmdOrder.class);
        util.exportExcel(response, list, "订单信息数据");
    }

    /**
     * 获取订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('omd:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(omdOrderService.selectOmdOrderByOrderId(orderId));
    }

    /**
     * 新增订单信息
     */
    @PreAuthorize("@ss.hasPermi('omd:order:add')")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmdOrder omdOrder)
    {
        return toAjax(omdOrderService.insertOmdOrder(omdOrder));
    }

    /**
     * 修改订单信息
     */
    @PreAuthorize("@ss.hasPermi('omd:order:edit')")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmdOrder omdOrder)
    {
        return toAjax(omdOrderService.updateOmdOrder(omdOrder));
    }

    /**
     * 删除订单信息
     */
    @PreAuthorize("@ss.hasPermi('omd:order:remove')")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(omdOrderService.deleteOmdOrderByOrderIds(orderIds));
    }

    /**
     * 获取订单编号选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<OmdOrder> orders = omdOrderService.selectOrderAll();
        return success(orders);
    }
}
