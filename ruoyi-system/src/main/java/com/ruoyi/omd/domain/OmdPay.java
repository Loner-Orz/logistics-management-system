package com.ruoyi.omd.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付信息对象 omd_pay
 *
 * @author loner
 * @date 2023-05-09
 */
public class OmdPay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付单号 */
    private Long payId;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payMethod;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private Long paySum;

    /** 支付状态（0未支付，1已支付） */
    @Excel(name = "支付状态", readConverterExp = "0=未支付，1已支付")
    private String status;

    public void setPayId(Long payId)
    {
        this.payId = payId;
    }

    public Long getPayId()
    {
        return payId;
    }
    public void setPayMethod(String payMethod)
    {
        this.payMethod = payMethod;
    }

    public String getPayMethod()
    {
        return payMethod;
    }
    public void setPaySum(Long paySum)
    {
        this.paySum = paySum;
    }

    public Long getPaySum()
    {
        return paySum;
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
                .append("payId", getPayId())
                .append("payMethod", getPayMethod())
                .append("paySum", getPaySum())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

