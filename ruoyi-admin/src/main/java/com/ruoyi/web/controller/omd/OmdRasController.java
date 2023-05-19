package com.ruoyi.omd.controller;

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
import com.ruoyi.omd.domain.OmdRas;
import com.ruoyi.omd.service.IOmdRasService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售后信息Controller
 *
 * @author loner
 * @date 2023-05-09
 */
@RestController
@RequestMapping("/omd/ras")
public class OmdRasController extends BaseController
{
    @Autowired
    private IOmdRasService omdRasService;

    /**
     * 查询售后信息列表
     */
    @PreAuthorize("@ss.hasPermi('omd:ras:list')")
    @GetMapping("/list")
    public TableDataInfo list(OmdRas omdRas)
    {
        startPage();
        List<OmdRas> list = omdRasService.selectOmdRasList(omdRas);
        return getDataTable(list);
    }

    /**
     * 导出售后信息列表
     */
    @PreAuthorize("@ss.hasPermi('omd:ras:export')")
    @Log(title = "售后信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OmdRas omdRas)
    {
        List<OmdRas> list = omdRasService.selectOmdRasList(omdRas);
        ExcelUtil<OmdRas> util = new ExcelUtil<OmdRas>(OmdRas.class);
        util.exportExcel(response, list, "售后信息数据");
    }

    /**
     * 获取售后信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('omd:ras:query')")
    @GetMapping(value = "/{rasId}")
    public AjaxResult getInfo(@PathVariable("rasId") Long rasId)
    {
        return success(omdRasService.selectOmdRasByRasId(rasId));
    }

    /**
     * 新增售后信息
     */
    @PreAuthorize("@ss.hasPermi('omd:ras:add')")
    @Log(title = "售后信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OmdRas omdRas)
    {
        return toAjax(omdRasService.insertOmdRas(omdRas));
    }

    /**
     * 修改售后信息
     */
    @PreAuthorize("@ss.hasPermi('omd:ras:edit')")
    @Log(title = "售后信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OmdRas omdRas)
    {
        return toAjax(omdRasService.updateOmdRas(omdRas));
    }

    /**
     * 删除售后信息
     */
    @PreAuthorize("@ss.hasPermi('omd:ras:remove')")
    @Log(title = "售后信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{rasIds}")
    public AjaxResult remove(@PathVariable Long[] rasIds)
    {
        return toAjax(omdRasService.deleteOmdRasByRasIds(rasIds));
    }
}
