package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImFriend;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 好友关系视图对象 im_friend
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImFriend.class)
public class ImFriendVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关系ID
     */
    @ExcelProperty(value = "关系ID")
    private Long id;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long fkUserId;

    /**
     * 好友ID
     */
    @ExcelProperty(value = "好友ID")
    private Long fkFriendId;

    /**
     * 单聊会话ID
     */
    @ExcelProperty(value = "单聊会话ID")
    private Long conversationId;

    /**
     * 备注名
     */
    @ExcelProperty(value = "备注名")
    private String remark;

    /**
     * 来源: 1-搜索 2-群聊 3-名片
     */
    @ExcelProperty(value = "来源: 1-搜索 2-群聊 3-名片")
    private Long source;

    /**
     * 状态: 1-正常 2-删除 3-拉黑
     */
    @ExcelProperty(value = "状态: 1-正常 2-删除 3-拉黑")
    private Long status;

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
