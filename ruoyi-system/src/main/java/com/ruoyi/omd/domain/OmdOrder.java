package com.ruoyi.omd.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单信息对象 omd_order
 *
 * @author loner
 * @date 2023-04-30
 */
public class OmdOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单单号 */
    @Excel(name = "订单单号")
    private Long orderId;

    /** 买家姓名 */
    @Excel(name = "买家姓名")
    private String orderBname;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String orderPhone;

    /** 省-名称 */
    @Excel(name = "省-名称")
    private String provinceName;

    /** 省-行政代号 */
    @Excel(name = "省-行政代号")
    private String provinceCode;

    /** 市-名称 */
    @Excel(name = "市-名称")
    private String cityName;

    /** 市-行政代号 */
    @Excel(name = "市-行政代号")
    private String cityCode;

    /** 区-名称 */
    @Excel(name = "区-名称")
    private String areaName;

    /** 区-行政代号 */
    @Excel(name = "区-行政代号")
    private String areaCode;

    /** 买家详细地址 */
    @Excel(name = "买家详细地址")
    private String drderAddress;

    /** 商品ID */
    @Excel(name = "商品ID")
    private String orderWid;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long orderWcount;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private String orderSid;

    /** 支付ID */
    @Excel(name = "支付ID")
    private String orderPid;

    /** 物流ID */
    @Excel(name = "物流ID")
    private String orderLid;

    /** 订单类型（0普通订单，1拼团订单，2秒杀订单） */
    @Excel(name = "订单类型", readConverterExp = "0=普通订单，1拼团订单，2秒杀订单")
    private String orderType;

    /** 订单状态（0正常，1已取消） */
    @Excel(name = "订单状态", readConverterExp = "0=正常，1已取消")
    private String status;

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setOrderBname(String orderBname)
    {
        this.orderBname = orderBname;
    }

    public String getOrderBname()
    {
        return orderBname;
    }
    public void setOrderPhone(String orderPhone)
    {
        this.orderPhone = orderPhone;
    }

    public String getOrderPhone()
    {
        return orderPhone;
    }
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

    public String getProvinceName()
    {
        return provinceName;
    }
    public void setProvinceCode(String provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceCode()
    {
        return provinceCode;
    }
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityName()
    {
        return cityName;
    }
    public void setCityCode(String cityCode)
    {
        this.cityCode = cityCode;
    }

    public String getCityCode()
    {
        return cityCode;
    }
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getAreaName()
    {
        return areaName;
    }
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode()
    {
        return areaCode;
    }
    public void setDrderAddress(String drderAddress)
    {
        this.drderAddress = drderAddress;
    }

    public String getDrderAddress()
    {
        return drderAddress;
    }
    public void setOrderWid(String orderWid)
    {
        this.orderWid = orderWid;
    }

    public String getOrderWid()
    {
        return orderWid;
    }
    public void setOrderWcount(Long orderWcount)
    {
        this.orderWcount = orderWcount;
    }

    public Long getOrderWcount()
    {
        return orderWcount;
    }
    public void setOrderSid(String orderSid)
    {
        this.orderSid = orderSid;
    }

    public String getOrderSid()
    {
        return orderSid;
    }
    public void setOrderPid(String orderPid)
    {
        this.orderPid = orderPid;
    }

    public String getOrderPid()
    {
        return orderPid;
    }
    public void setOrderLid(String orderLid)
    {
        this.orderLid = orderLid;
    }

    public String getOrderLid()
    {
        return orderLid;
    }
    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getOrderType()
    {
        return orderType;
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
                .append("orderId", getOrderId())
                .append("orderBname", getOrderBname())
                .append("orderPhone", getOrderPhone())
                .append("provinceName", getProvinceName())
                .append("provinceCode", getProvinceCode())
                .append("cityName", getCityName())
                .append("cityCode", getCityCode())
                .append("areaName", getAreaName())
                .append("areaCode", getAreaCode())
                .append("drderAddress", getDrderAddress())
                .append("orderWid", getOrderWid())
                .append("orderWcount", getOrderWcount())
                .append("orderSid", getOrderSid())
                .append("orderPid", getOrderPid())
                .append("orderLid", getOrderLid())
                .append("orderType", getOrderType())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

