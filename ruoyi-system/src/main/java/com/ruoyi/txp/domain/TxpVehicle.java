package com.ruoyi.txp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆信息对象 txp_vehicle
 *
 * @author ruoyi
 * @date 2023-03-17
 */
public class TxpVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆ID */
    private Long vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String vehicleIpn;

    /** 所属仓库 */
    @Excel(name = "所属仓库")
    private Long vehicleSid;

    /** 车辆类型（0大型, 1中型, 2小型） */
    @Excel(name = "车辆类型", readConverterExp = "0=大型,,1=中型,,2=小型")
    private String vehicleType;

    /** 车辆状态（0休息中, 1运输中） */
    @Excel(name = "车辆状态", readConverterExp = "0=休息中,,1=运输中")
    private String vehicleState;

    /** 状态（0未分配, 1已分配） */
    @Excel(name = "状态", readConverterExp = "0=未分配,,1=已分配")
    private String status;

    public void setVehicleId(Long vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId()
    {
        return vehicleId;
    }
    public void setVehicleIpn(String vehicleIpn)
    {
        this.vehicleIpn = vehicleIpn;
    }

    public String getVehicleIpn()
    {
        return vehicleIpn;
    }
    public void setVehicleSid(Long vehicleSid)
    {
        this.vehicleSid = vehicleSid;
    }

    public Long getVehicleSid()
    {
        return vehicleSid;
    }
    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }
    public void setVehicleState(String vehicleState)
    {
        this.vehicleState = vehicleState;
    }

    public String getVehicleState()
    {
        return vehicleState;
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
                .append("vehicleId", getVehicleId())
                .append("vehicleIpn", getVehicleIpn())
                .append("vehicleSid", getVehicleSid())
                .append("vehicleType", getVehicleType())
                .append("vehicleState", getVehicleState())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
