package com.imai.core.domain.bo;

import com.imai.core.domain.ImFriendRequest;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 好友申请业务对象 im_friend_request
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImFriendRequest.class, reverseConvertGenerate = false)
public class ImFriendRequestBo extends BaseEntity {

    /**
     * 申请ID
     */
    @NotNull(message = "申请ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 申请人ID
     */
    @NotNull(message = "申请人ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long fromUserId;

    /**
     * 给接收人的备注
     */
    @NotBlank(message = "给接收人的备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String fromUserRemarkName;

    /**
     * 接收人ID
     */
    @NotNull(message = "接收人ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long toUserId;

    /**
     * 验证信息
     */
    @NotBlank(message = "验证信息不能为空", groups = {AddGroup.class, EditGroup.class})
    private String message;

    /**
     * 状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略
     */
    @NotNull(message = "状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long status;

    /**
     * 处理时间
     */
    @NotNull(message = "处理时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date handleTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @NotNull(message = "是否删除 0-未删除 1-已删除不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    @NotBlank(message = "可选 自定义属性，供开发者扩展使用不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extras;


}
