package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 消息撤回记录对象 im_msg_recall
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_msg_recall")
public class ImMsgRecall extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 撤回记录id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 消息id
     */
    private Long fkMsgId;

    /**
     * 撤回用户id
     */
    private Long fkUserId;

    /**
     * 撤回时间，精确到毫秒
     */
    private Date recallTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
