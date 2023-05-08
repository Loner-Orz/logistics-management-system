package com.ruoyi.web.controller.whse;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.whse.domain.WhseStore;
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
import com.ruoyi.whse.domain.WhseComm;
import com.ruoyi.whse.service.IWhseCommService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 *
 * @author ruoyi
 * @date 2023-01-13
 */
@RestController
@RequestMapping("/whse/comm")
public class WhseCommController extends BaseController
{
    @Autowired
    private IWhseCommService whseCommService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('whse:comm:list')")
    @GetMapping("/list")
    public TableDataInfo list(WhseComm whseComm)
    {
        startPage();
        List<WhseComm> list = whseCommService.selectWhseCommList(whseComm);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('whse:comm:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WhseComm whseComm)
    {
        List<WhseComm> list = whseCommService.selectWhseCommList(whseComm);
        ExcelUtil<WhseComm> util = new ExcelUtil<WhseComm>(WhseComm.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('whse:comm:query')")
    @GetMapping(value = "/{commId}")
    public AjaxResult getInfo(@PathVariable("commId") Long commId)
    {
        return success(whseCommService.selectWhseCommByCommId(commId));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('whse:comm:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WhseComm whseComm)
    {
        return toAjax(whseCommService.insertWhseComm(whseComm));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('whse:comm:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WhseComm whseComm)
    {
        return toAjax(whseCommService.updateWhseComm(whseComm));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('whse:comm:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commIds}")
    public AjaxResult remove(@PathVariable Long[] commIds)
    {
        return toAjax(whseCommService.deleteWhseCommByCommIds(commIds));
    }

    /**
     * 获取商品选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<WhseComm> whseCommAll = whseCommService.selectWhseCommAll();
        return success(whseCommAll);
    }
}
