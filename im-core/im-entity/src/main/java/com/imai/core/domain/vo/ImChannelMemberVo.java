package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImChannelMember;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道成员视图对象 im_channel_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImChannelMember.class)
public class ImChannelMemberVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 频道ID
     */
    @ExcelProperty(value = "频道ID")
    private Long fkChannelId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long fkUserId;

    /**
     * 用户权限
     */
    @ExcelProperty(value = "用户权限")
    private Long memberRole;

    /**
     * 加入时间
     */
    @ExcelProperty(value = "加入时间")
    private Date joinTime;

    /**
     * 通知级别:0关闭,1提及时,2所有消息
     */
    @ExcelProperty(value = "通知级别:0关闭,1提及时,2所有消息")
    private Long notificationLevel;

    /**
     * 是否星标:0否,1是
     */
    @ExcelProperty(value = "是否星标:0否,1是")
    private Long starred;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选 自定义属性，供开发者扩展使用")
    private String extras;


}
