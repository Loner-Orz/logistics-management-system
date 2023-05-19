package com.ruoyi.omd.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后信息对象 omd_ras
 *
 * @author loner
 * @date 2023-05-09
 */
public class OmdRas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 售后编号 */
    private Long rasId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private Long rasOid;

    /** 售后类型（例如产品瑕疵、错误的尺寸或颜色等） */
    @Excel(name = "售后类型", readConverterExp = "例=如产品瑕疵、错误的尺寸或颜色等")
    private String rasStatus;

    /** 售后具体原因 */
    @Excel(name = "售后具体原因")
    private String rasReason;

    /** 售后状态（0未退货，1未退款，2已完成） */
    @Excel(name = "售后状态", readConverterExp = "0=未退货，1未退款，2已完成")
    private String status;

    public void setRasId(Long rasId)
    {
        this.rasId = rasId;
    }

    public Long getRasId()
    {
        return rasId;
    }
    public void setRasOid(Long rasOid)
    {
        this.rasOid = rasOid;
    }

    public Long getRasOid()
    {
        return rasOid;
    }
    public void setRasStatus(String rasStatus)
    {
        this.rasStatus = rasStatus;
    }

    public String getRasStatus()
    {
        return rasStatus;
    }
    public void setRasReason(String rasReason)
    {
        this.rasReason = rasReason;
    }

    public String getRasReason()
    {
        return rasReason;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("rasId", getRasId())
                .append("rasOid", getRasOid())
                .append("rasStatus", getRasStatus())
                .append("rasReason", getRasReason())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

