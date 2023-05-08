package com.ruoyi.web.controller.txp;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.txp.domain.TxpDriver;
import com.ruoyi.txp.service.ITxpDriverService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 驾驶员信息Controller
 *
 * @author ruoyi
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/txp/driver")
public class TxpDriverController extends BaseController
{
    @Autowired
    private ITxpDriverService txpDriverService;

    /**
     * 查询驾驶员信息列表
     */
    @PreAuthorize("@ss.hasPermi('txp:driver:list')")
    @GetMapping("/list")
    public TableDataInfo list(TxpDriver txpDriver)
    {
        startPage();
        List<TxpDriver> list = txpDriverService.selectTxpDriverList(txpDriver);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员信息列表
     */
    @PreAuthorize("@ss.hasPermi('txp:driver:export')")
    @Log(title = "驾驶员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TxpDriver txpDriver)
    {
        List<TxpDriver> list = txpDriverService.selectTxpDriverList(txpDriver);
        ExcelUtil<TxpDriver> util = new ExcelUtil<TxpDriver>(TxpDriver.class);
        util.exportExcel(response, list, "驾驶员信息数据");
    }

    /**
     * 获取驾驶员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('txp:driver:query')")
    @GetMapping(value = "/{driverId}")
    public AjaxResult getInfo(@PathVariable("driverId") Long driverId)
    {
        return success(txpDriverService.selectTxpDriverByDriverId(driverId));
    }

    /**
     * 新增驾驶员信息
     */
    @PreAuthorize("@ss.hasPermi('txp:driver:add')")
    @Log(title = "驾驶员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TxpDriver txpDriver)
    {
        return toAjax(txpDriverService.insertTxpDriver(txpDriver));
    }

    /**
     * 修改驾驶员信息
     */
    @PreAuthorize("@ss.hasPermi('txp:driver:edit')")
    @Log(title = "驾驶员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TxpDriver txpDriver)
    {
        return toAjax(txpDriverService.updateTxpDriver(txpDriver));
    }

    /**
     * 删除驾驶员信息
     */
    @PreAuthorize("@ss.hasPermi('txp:driver:remove')")
    @Log(title = "驾驶员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{driverIds}")
    public AjaxResult remove(@PathVariable Long[] driverIds)
    {
        return toAjax(txpDriverService.deleteTxpDriverByDriverIds(driverIds));
    }
}

