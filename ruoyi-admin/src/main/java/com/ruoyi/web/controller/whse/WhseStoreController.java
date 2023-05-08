package com.ruoyi.web.controller.whse;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysDictType;
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
import com.ruoyi.whse.domain.WhseStore;
import com.ruoyi.whse.service.IWhseStoreService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 仓库信息Controller
 *
 * @author ruoyi
 * @date 2023-01-14
 */
@RestController
@RequestMapping("/whse/store")
public class WhseStoreController extends BaseController
{
    @Autowired
    private IWhseStoreService whseStoreService;

    /**
     * 查询仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('whse:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(WhseStore whseStore)
    {
        startPage();
        List<WhseStore> list = whseStoreService.selectWhseStoreList(whseStore);
        return getDataTable(list);
    }

    /**
     * 导出仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('whse:store:export')")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WhseStore whseStore)
    {
        List<WhseStore> list = whseStoreService.selectWhseStoreList(whseStore);
        ExcelUtil<WhseStore> util = new ExcelUtil<WhseStore>(WhseStore.class);
        util.exportExcel(response, list, "仓库信息数据");
    }

    /**
     * 获取仓库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('whse:store:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return success(whseStoreService.selectWhseStoreByStoreId(storeId));
    }

    /**
     * 新增仓库信息
     */
    @PreAuthorize("@ss.hasPermi('whse:store:add')")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody WhseStore whseStore)
    {
        return toAjax(whseStoreService.insertWhseStore(whseStore));
    }

    /**
     * 修改仓库信息
     */
    @PreAuthorize("@ss.hasPermi('whse:store:edit')")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody WhseStore whseStore)
    {
        return toAjax(whseStoreService.updateWhseStore(whseStore));
    }

    /**
     * 删除仓库信息
     */
    @PreAuthorize("@ss.hasPermi('whse:store:remove')")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(whseStoreService.deleteWhseStoreByStoreIds(storeIds));
    }

    /**
     * 获取仓库选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<WhseStore> whseStoreAll = whseStoreService.selectWhseStoreAll();
        return success(whseStoreAll);
    }
}
