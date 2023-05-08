package com.ruoyi.district.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地区信息对象 district
 *
 * @author ruoyi
 * @date 2023-01-18
 */
public class District
{
    private static final long serialVersionUID = 1L;

    /** 地区ID */
    private Long id;

    /** 地区代码 */
    @Excel(name = "地区代码")
    private Long code;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

    /** 地区父代码 */
    @Excel(name = "地区父代码")
    private Long pid;

    /** 地区级别1省、2市、3县区 */
    @Excel(name = "地区级别1省、2市、3县区")
    private String level;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCode(Long code)
    {
        this.code = code;
    }

    public Long getCode()
    {
        return code;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPid(Long pid)
    {
        this.pid = pid;
    }

    public Long getPid()
    {
        return pid;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("code", getCode())
                .append("name", getName())
                .append("pid", getPid())
                .append("level", getLevel())
                .toString();
    }
}
