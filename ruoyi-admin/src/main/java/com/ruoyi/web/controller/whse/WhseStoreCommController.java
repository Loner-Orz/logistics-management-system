package com.ruoyi.web.controller.whse;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.whse.domain.DTO.StoreCommDto;
import com.ruoyi.whse.domain.WhseComm;
import com.ruoyi.whse.domain.WhseStoreComm;
import com.ruoyi.whse.service.IWhseStoreCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 仓库和商品关联Controller
 *
 * @author ruoyi
 * @date 2023-03-26
 */
@RestController
@RequestMapping("/whse/store_comm")
public class WhseStoreCommController extends BaseController {
    @Autowired
    private IWhseStoreCommService whseStoreCommService;

    /**
     * 获取仓库的商品列表
     */
    @PreAuthorize("@ss.hasPermi('whse:store_comm:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreCommDto commDto)
    {
        startPage();
        List<StoreCommDto> list = whseStoreCommService.selectCommDto(commDto);
        return getDataTable(list);
    }

    /**
     * 获取对应仓库的商品详细信息
     */
    @PreAuthorize("@ss.hasPermi('whse:store_comm:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return success(whseStoreCommService.selectWhseCommByStoreId(storeId));
    }

    /**
     * 新增商品或修改商品数量
     */
    @PreAuthorize("@ss.hasPermi('whse:store_comm:add')")
    @Log(title = "仓库对应商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody StoreCommDto storeCommDto)
    {
        //获取WhseComm对象
        WhseStoreComm storecomm = new WhseStoreComm();
        storecomm.setStoreId(storeCommDto.getStoreId());
        storecomm.setCommId(storeCommDto.getCommId());
        storecomm.setCommCount(storeCommDto.getCommCount());
        //验证商品是否已经存在
        List<StoreCommDto> commList = whseStoreCommService.selectWhseCommByStoreId(storeCommDto.getStoreId());
        if(commList.stream()
                .anyMatch(c -> Objects.equals(c.getCommId(), storeCommDto.getCommId()))){
            //若存在，则在仓库和商品关联表中更新对应商品数量
            //更新数量
            storecomm.setCommCount(storeCommDto.getCommCount()+
                    whseStoreCommService.getCommCount(storeCommDto.getStoreId(),
                                                      storeCommDto.getCommId()));
            System.out.println(storecomm.getCommCount());
            //先删除
            if(whseStoreCommService.deleteCommByCommId(storeCommDto.getStoreId(),storeCommDto.getCommId()) > 0){
                System.out.println(1);
                return toAjax(whseStoreCommService.insertComm(storecomm));
            }
            return toAjax(0);
        }else {
            //不存在，新增商品到仓库和商品关联表中
            return toAjax(whseStoreCommService.insertComm(storecomm));
        }
    }

    /**
     * 删除仓库商品信息
     */
    @PreAuthorize("@ss.hasPermi('whse:store_comm:remove')")
    @Log(title = "仓库对应商品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commIds}/{storeId}")
    public AjaxResult remove(@PathVariable("commIds") Long[] commIds, @PathVariable("storeId") Long storeId)
    {
        return toAjax(whseStoreCommService.deleteCommByCommIds(storeId,commIds));
    }

    /**
     * 导出仓库对应的商品信息
     */
    @Log(title = "商品数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('whse:store_comm:export')")
    @PostMapping("/export/{storeId}")
    public void export(HttpServletResponse response, Long storeId)
    {
        List<StoreCommDto> list = whseStoreCommService.selectWhseCommByStoreId(storeId);
        ExcelUtil<StoreCommDto> util = new ExcelUtil<>(StoreCommDto.class);
        util.exportExcel(response, list, "商品数据");
    }

}
