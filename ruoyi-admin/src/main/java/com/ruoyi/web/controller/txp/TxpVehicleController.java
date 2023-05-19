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
import com.ruoyi.txp.domain.TxpVehicle;
import com.ruoyi.txp.service.ITxpVehicleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆信息Controller
 *
 * @author ruoyi
 * @date 2023-03-17
 */
@RestController
@RequestMapping("/txp/vehicle")
public class TxpVehicleController extends BaseController
{
    @Autowired
    private ITxpVehicleService txpVehicleService;

    /**
     * 查询车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(TxpVehicle txpVehicle)
    {
        startPage();
        List<TxpVehicle> list = txpVehicleService.selectTxpVehicleList(txpVehicle);
        return getDataTable(list);
    }

    /**
     * 导出车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:export')")
    @Log(title = "车辆信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TxpVehicle txpVehicle)
    {
        List<TxpVehicle> list = txpVehicleService.selectTxpVehicleList(txpVehicle);
        ExcelUtil<TxpVehicle> util = new ExcelUtil<TxpVehicle>(TxpVehicle.class);
        util.exportExcel(response, list, "车辆信息数据");
    }

    /**
     * 获取车辆信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:query')")
    @GetMapping(value = "/{vehicleId}")
    public AjaxResult getInfo(@PathVariable("vehicleId") Long vehicleId)
    {
        return success(txpVehicleService.selectTxpVehicleByVehicleId(vehicleId));
    }

    /**
     * 新增车辆信息
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:add')")
    @Log(title = "车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TxpVehicle txpVehicle)
    {
        return toAjax(txpVehicleService.insertTxpVehicle(txpVehicle));
    }

    /**
     * 修改车辆信息
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:edit')")
    @Log(title = "车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TxpVehicle txpVehicle)
    {
        return toAjax(txpVehicleService.updateTxpVehicle(txpVehicle));
    }

    /**
     * 删除车辆信息
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:remove')")
    @Log(title = "车辆信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{vehicleIds}")
    public AjaxResult remove(@PathVariable Long[] vehicleIds)
    {
        return toAjax(txpVehicleService.deleteTxpVehicleByVehicleIds(vehicleIds));
    }

    /**
     * 展示车辆信息列表
     */
    @PreAuthorize("@ss.hasPermi('txp:vehicle:list')")
    @GetMapping("/showlist")
    public TableDataInfo showList(TxpVehicle txpVehicle)
    {
        startPage();
        List<TxpVehicle> list = txpVehicleService.showTxpVehicleList(txpVehicle);
        return getDataTable(list);
    }
}

