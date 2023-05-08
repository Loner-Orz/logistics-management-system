package com.ruoyi.whse.domain.DTO;

import com.ruoyi.common.annotation.Excel;

/**
 * 仓库的商品信息对象 store_comm
 *
 * @author ruoyi
 * @date 2023-03-22
 */
public class StoreCommDto {
    private static final long serialVersionUID = 1L;


    /**
     * 仓库ID
     */
    private Long storeId;

    /**
     * 商品ID
     */
    @Excel(name = "商品编码")
    private Long commId;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String commName;

    /**
     * 商品单价
     */
    @Excel(name = "商品单价")
    private Long commPrice;

    /**
     * 商品数量
     */
    @Excel(name = "商品数量")
    private Long commCount;

    /**
     * 商品类型（0生活用品）
     */
    @Excel(name = "商品类型",dictType = "whse_comm_types")
    private String commType;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getCommId() {
        return commId;
    }

    public void setCommId(Long commId) {
        this.commId = commId;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public Long getCommPrice() {
        return commPrice;
    }

    public void setCommPrice(Long commPrice) {
        this.commPrice = commPrice;
    }

    public Long getCommCount() {
        return commCount;
    }

    public void setCommCount(Long commCount) {
        this.commCount = commCount;
    }

    public String getCommType() {
        return commType;
    }

    public void setCommType(String commType) {
        this.commType = commType;
    }
}
