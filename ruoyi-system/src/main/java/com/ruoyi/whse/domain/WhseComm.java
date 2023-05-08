package com.ruoyi.whse.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.validation.constraints.NotBlank;

/**
 * 商品信息对象 whse_comm
 *
 * @author ruoyi
 * @date 2023-01-13
 */
public class WhseComm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    @NotBlank(message = "商品编码不能为空")
    private Long commId;

    /** 商品名称 */
    @NotBlank(message = "商品名称不能为空")
    @Excel(name = "商品名称")
    private String commName;

    /** 商品单价 */
    @Excel(name = "商品单价")
    private Long commPrice;

    /** 商品类型（0生活用品） */
    @Excel(name = "商品类型", readConverterExp = "0=生活用品")
    private String commType;

    /** 商品状态（0入库 1出库） */
    @Excel(name = "商品状态", readConverterExp = "0=入库,1=出库")
    private String status;

    public void setCommId(Long commId)
    {
        this.commId = commId;
    }

    public Long getCommId()
    {
        return commId;
    }
    public void setCommName(String commName)
    {
        this.commName = commName;
    }

    public String getCommName()
    {
        return commName;
    }

    public void setCommPrice(Long commPrice)
    {
        this.commPrice = commPrice;
    }

    public Long getCommPrice()
    {
        return commPrice;
    }
    public void setCommType(String commType)
    {
        this.commType = commType;
    }

    public String getCommType()
    {
        return commType;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("commId", getCommId())
                .append("commName", getCommName())
                .append("commPrice", getCommPrice())
                .append("commType", getCommType())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
