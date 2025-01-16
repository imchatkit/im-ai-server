package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 消息引用关系对象 im_msg_reference
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_msg_reference")
public class ImMsgReference extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 当前消息ID
     */
    private Long fkMsgId;

    /**
     * 被引用的消息ID
     */
    private Long fkRefMsgId;

    /**
     * 引用类型:1回复,2转发,3引用
     */
    private Long refType;

    /**
     * 引用时添加的评论文本
     */
    private String refText;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
