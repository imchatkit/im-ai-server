package com.imai.core.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 多端同步对象 im_sync
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("im_sync")
public class ImSync extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 同步id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long fkUserId;

    /**
     * 用户维度单调递增的PTS位点
     */
    private Long pts;

    /**
     * 消息id
     */
    private Long fkMsgId;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Long deleted;

    /**
     * 可选 自定义属性，供开发者扩展使用
     */
    private String extras;


}
