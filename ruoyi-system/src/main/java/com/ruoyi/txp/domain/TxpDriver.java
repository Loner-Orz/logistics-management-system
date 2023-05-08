package com.ruoyi.txp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驾驶员信息对象 txp_driver
 *
 * @author ruoyi
 * @date 2023-03-17
 */
public class TxpDriver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 驾驶员ID */
    private Long driverId;

    /** 驾驶员姓名 */
    @Excel(name = "驾驶员姓名")
    private String driverName;

    /** 驾驶员性别（0男, 1女, 2未知） */
    @Excel(name = "驾驶员性别", readConverterExp = "0=男,,1=女,,2=未知")
    private String driverGender;

    /** 驾驶员电话 */
    @Excel(name = "驾驶员电话")
    private String driverPhone;

    /** 驾驶员身份证号码 */
    @Excel(name = "驾驶员身份证号码")
    private String driverIdc;

    /** 驾驶员驾驶证号码 */
    @Excel(name = "驾驶员驾驶证号码")
    private String driverLic;

    /** 驾驶证分数 */
    @Excel(name = "驾驶证分数")
    private String driverFra;

    /** 驾驶员状态（2驾照吊销, 0休息, 1运输中） */
    @Excel(name = "驾驶员状态", readConverterExp = "2=驾照吊销,,0=休息,,1=运输中")
    private String status;

    public void setDriverId(Long driverId)
    {
        this.driverId = driverId;
    }

    public Long getDriverId()
    {
        return driverId;
    }
    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
    }

    public String getDriverName()
    {
        return driverName;
    }
    public void setDriverGender(String driverGender)
    {
        this.driverGender = driverGender;
    }

    public String getDriverGender()
    {
        return driverGender;
    }
    public void setDriverPhone(String driverPhone)
    {
        this.driverPhone = driverPhone;
    }

    public String getDriverPhone()
    {
        return driverPhone;
    }
    public void setDriverIdc(String driverIdc)
    {
        this.driverIdc = driverIdc;
    }

    public String getDriverIdc()
    {
        return driverIdc;
    }
    public void setDriverLic(String driverLic)
    {
        this.driverLic = driverLic;
    }

    public String getDriverLic()
    {
        return driverLic;
    }
    public void setDriverFra(String driverFra)
    {
        this.driverFra = driverFra;
    }

    public String getDriverFra()
    {
        return driverFra;
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
                .append("driverId", getDriverId())
                .append("driverName", getDriverName())
                .append("driverGender", getDriverGender())
                .append("driverPhone", getDriverPhone())
                .append("driverIdc", getDriverIdc())
                .append("driverLic", getDriverLic())
                .append("driverFra", getDriverFra())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}

