package com.ruoyi.web.controller.whse;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.whse.domain.WhseCmpy;
import com.ruoyi.whse.service.IWhseCmpyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 往来公司信息Controller
 *
 * @author ruoyi
 * @date 2023-01-14
 */
@Api("公司管理")
@RestController
@RequestMapping("/whse/cmpy")
public class WhseCmpyController extends BaseController
{
    @Autowired
    private IWhseCmpyService whseCmpyService;

    /**
     * 查询往来公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('whse:cmpy:list')")
    @GetMapping("/list")
    public TableDataInfo list(WhseCmpy whseCmpy)
    {
        startPage();
        List<WhseCmpy> list = whseCmpyService.selectWhseCmpyList(whseCmpy);
        return getDataTable(list);
    }

    /**
     * 导出往来公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('whse:cmpy:export')")
    @Log(title = "往来公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WhseCmpy whseCmpy)
    {
        List<WhseCmpy> list = whseCmpyService.selectWhseCmpyList(whseCmpy);
        ExcelUtil<WhseCmpy> util = new ExcelUtil<WhseCmpy>(WhseCmpy.class);
        util.exportExcel(response, list, "往来公司信息数据");
    }

    /**
     * 获取往来公司信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('whse:cmpy:query')")
    @GetMapping(value = "/{cmpyId}")
    public AjaxResult getInfo(@PathVariable("cmpyId") Long cmpyId)
    {
        return success(whseCmpyService.selectWhseCmpyByCmpyId(cmpyId));
    }

    /**
     * 新增往来公司信息
     */
    @PreAuthorize("@ss.hasPermi('whse:cmpy:add')")
    @Log(title = "往来公司信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody WhseCmpy whseCmpy)
    {
        return toAjax(whseCmpyService.insertWhseCmpy(whseCmpy));
    }

    /**
     * 修改往来公司信息
     */
    @PreAuthorize("@ss.hasPermi('whse:cmpy:edit')")
    @Log(title = "往来公司信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody WhseCmpy whseCmpy)
    {
        //System.out.println(whseCmpy);
        return toAjax(whseCmpyService.updateWhseCmpy(whseCmpy));
    }

    /**
     * 删除往来公司信息
     */
    @PreAuthorize("@ss.hasPermi('whse:cmpy:remove')")
    @Log(title = "往来公司信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{cmpyIds}")
    public AjaxResult remove(@PathVariable Long[] cmpyIds)
    {
        return toAjax(whseCmpyService.deleteWhseCmpyByCmpyIds(cmpyIds));
    }
}
