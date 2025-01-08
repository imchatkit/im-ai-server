package com.imai.core.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.imai.core.domain.ImFriendRequest;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 好友申请视图对象 im_friend_request
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImFriendRequest.class)
public class ImFriendRequestVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 申请ID
     */
    @ExcelProperty(value = "申请ID")
    private Long id;

    /**
     * 申请人ID
     */
    @ExcelProperty(value = "申请人ID")
    private Long fromUserId;

    /**
     * 给接收人的备注
     */
    @ExcelProperty(value = "给接收人的备注")
    private String fromUserRemarkName;

    /**
     * 接收人ID
     */
    @ExcelProperty(value = "接收人ID")
    private Long toUserId;

    /**
     * 验证信息
     */
    @ExcelProperty(value = "验证信息")
    private String message;

    /**
     * 状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略
     */
    @ExcelProperty(value = "状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略")
    private Long status;

    /**
     * 处理时间
     */
    @ExcelProperty(value = "处理时间")
    private Date handleTime;

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
