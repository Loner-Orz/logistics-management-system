package com.ruoyi.whse.domain;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * 仓库和商品关联对象 whse_store_comm
 *
 * @author ruoyi
 * @date 2023-03-19
 */
public class WhseStoreComm
{
    private static final long serialVersionUID = 1L;

    /** 仓库ID */
    private Long storeId;

    /** 商品ID */
    private Long commId;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long commCount;

    public void setStoreId(Long storeId)
    {
        this.storeId = storeId;
    }

    public Long getStoreId()
    {
        return storeId;
    }
    public void setCommId(Long commId)
    {
        this.commId = commId;
    }

    public Long getCommId()
    {
        return commId;
    }
    public void setCommCount(Long commCount)
    {
        this.commCount = commCount;
    }

    public Long getCommCount()
    {
        return commCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("storeId", getStoreId())
                .append("commId", getCommId())
                .append("commCount", getCommCount())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WhseStoreComm that = (WhseStoreComm) o;
        return Objects.equals(storeId, that.storeId) && Objects.equals(commId, that.commId) && Objects.equals(commCount, that.commCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, commId, commCount);
    }
}

