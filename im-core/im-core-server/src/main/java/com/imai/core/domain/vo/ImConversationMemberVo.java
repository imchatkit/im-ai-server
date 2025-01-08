package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImConversationMember;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 会话成员视图对象 im_conversation_member
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImConversationMember.class)
public class ImConversationMemberVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 会话ID
     */
    @ExcelProperty(value = "会话ID")
    private Long fkConversationId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long fkUserId;

    /**
     * 可选	自定义属性，供开发者扩展使用
     */
    @ExcelProperty(value = "可选	自定义属性，供开发者扩展使用")
    private String extras;

    /**
     * 会话中的备注名
     */
    @ExcelProperty(value = "会话中的备注名")
    private String userRemarkName;

    /**
     * 角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单
     */
    @ExcelProperty(value = "角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单")
    private Long role;

    /**
     * 免打扰开关 0-关闭 1开启
     */
    @ExcelProperty(value = "免打扰开关 0-关闭 1开启")
    private Long disturbFlag;

    /**
     * 置顶开关 0-关闭 1开启
     */
    @ExcelProperty(value = "置顶开关 0-关闭 1开启")
    private Long topFlag;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @ExcelProperty(value = "是否删除 0-未删除 1-已删除")
    private Long deleted;

    /**
     * 屏蔽@全体成员 0-不屏蔽 1-屏蔽
     */
    @ExcelProperty(value = "屏蔽@全体成员 0-不屏蔽 1-屏蔽")
    private Long muteAtAll;

    /**
     * 禁言状态: 1-正常发言 2-永久禁言 3-限时禁言
     */
    @ExcelProperty(value = "禁言状态: 1-正常发言 2-永久禁言 3-限时禁言")
    private Long muted;

    /**
     * 禁言结束时间
     */
    @ExcelProperty(value = "禁言结束时间")
    private Date muteEndTime;


}
