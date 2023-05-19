package com.ruoyi.omd.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物流信息对象 omd_logistics
 *
 * @author loner
 * @date 2023-05-09
 */
public class OmdLogistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流单号 */
    private Long logisticsId;

    /** 物流公司 */
    @Excel(name = "物流公司")
    private String logisticsCmpy;

    /** 物流状态（0待揽收，1已发货，2派送中，3已签收，4已取消） */
    @Excel(name = "物流状态", readConverterExp = "0=待揽收，1已发货，2派送中，3已签收，4已取消")
    private String status;

    public void setLogisticsId(Long logisticsId)
    {
        this.logisticsId = logisticsId;
    }

    public Long getLogisticsId()
    {
        return logisticsId;
    }
    public void setLogisticsCmpy(String logisticsCmpy)
    {
        this.logisticsCmpy = logisticsCmpy;
    }

    public String getLogisticsCmpy()
    {
        return logisticsCmpy;
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
                .append("logisticsId", getLogisticsId())
                .append("logisticsCmpy", getLogisticsCmpy())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
