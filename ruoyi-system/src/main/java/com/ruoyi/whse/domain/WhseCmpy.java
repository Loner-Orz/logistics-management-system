package com.ruoyi.whse.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 往来公司信息对象 whse_cmpy
 *
 * @author ruoyi
 * @date 2023-01-19
 */
public class WhseCmpy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 往来公司ID */
    private Long cmpyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String cmpyName;

    /** 公司负责人 */
    @Excel(name = "公司负责人")
    private String cmpyConName;

    /** 公司电话 */
    @Excel(name = "公司电话")
    private String cmpyPhone;

    /** 公司邮箱 */
    @Excel(name = "公司邮箱")
    private String cmpyEmail;

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

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String cmpyAddress;

    /** 公司状态（0正常，1中断） */
    @Excel(name = "公司状态", readConverterExp = "0=正常，1中断")
    private String status;

    public void setCmpyId(Long cmpyId)
    {
        this.cmpyId = cmpyId;
    }

    public Long getCmpyId()
    {
        return cmpyId;
    }
    public void setCmpyName(String cmpyName)
    {
        this.cmpyName = cmpyName;
    }

    public String getCmpyName()
    {
        return cmpyName;
    }
    public void setCmpyConName(String cmpyConName)
    {
        this.cmpyConName = cmpyConName;
    }

    public String getCmpyConName()
    {
        return cmpyConName;
    }
    public void setCmpyPhone(String cmpyPhone)
    {
        this.cmpyPhone = cmpyPhone;
    }

    public String getCmpyPhone()
    {
        return cmpyPhone;
    }
    public void setCmpyEmail(String cmpyEmail)
    {
        this.cmpyEmail = cmpyEmail;
    }

    public String getCmpyEmail()
    {
        return cmpyEmail;
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
    public void setCmpyAddress(String cmpyAddress)
    {
        this.cmpyAddress = cmpyAddress;
    }

    public String getCmpyAddress()
    {
        return cmpyAddress;
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
                .append("cmpyId", getCmpyId())
                .append("cmpyName", getCmpyName())
                .append("cmpyConName", getCmpyConName())
                .append("cmpyPhone", getCmpyPhone())
                .append("cmpyEmail", getCmpyEmail())
                .append("provinceName", getProvinceName())
                .append("provinceCode", getProvinceCode())
                .append("cityName", getCityName())
                .append("cityCode", getCityCode())
                .append("areaName", getAreaName())
                .append("areaCode", getAreaCode())
                .append("cmpyAddress", getCmpyAddress())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
