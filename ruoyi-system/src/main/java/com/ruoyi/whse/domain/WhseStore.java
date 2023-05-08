package com.ruoyi.whse.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 仓库信息对象 whse_store
 *
 * @author ruoyi
 * @date 2023-01-19
 */
public class WhseStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库ID */
    private Long storeId;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storeName;

    /** 仓库负责人 */
    @Excel(name = "仓库负责人")
    private String storeConName;

    /** 仓库电话 */
    @Excel(name = "仓库电话")
    private String storePhone;

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

    /** 仓库详细地址 */
    @Excel(name = "仓库详细地址")
    private String storeAddress;

    /** 仓库状态（0正常，1无货） */
    @Excel(name = "仓库状态", readConverterExp = "0=正常，1无货")
    private String status;

    public void setStoreId(Long storeId)
    {
        this.storeId = storeId;
    }

    public Long getStoreId()
    {
        return storeId;
    }
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStoreName()
    {
        return storeName;
    }
    public void setStoreConName(String storeConName)
    {
        this.storeConName = storeConName;
    }

    public String getStoreConName()
    {
        return storeConName;
    }
    public void setStorePhone(String storePhone)
    {
        this.storePhone = storePhone;
    }

    public String getStorePhone()
    {
        return storePhone;
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
    public void setStoreAddress(String storeAddress)
    {
        this.storeAddress = storeAddress;
    }

    public String getStoreAddress()
    {
        return storeAddress;
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
                .append("storeId", getStoreId())
                .append("storeName", getStoreName())
                .append("storeConName", getStoreConName())
                .append("storePhone", getStorePhone())
                .append("provinceName", getProvinceName())
                .append("provinceCode", getProvinceCode())
                .append("cityName", getCityName())
                .append("cityCode", getCityCode())
                .append("areaName", getAreaName())
                .append("areaCode", getAreaCode())
                .append("storeAddress", getStoreAddress())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
