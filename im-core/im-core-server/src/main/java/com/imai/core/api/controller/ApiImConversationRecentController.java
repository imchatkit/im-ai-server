package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.domain.vo.ImConversationRecentVo;
import com.imai.core.service.IImConversationRecentService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IM应用/首页对话列表
 * 前端访问路由地址为:/api/v1/conversationRecent
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversationRecent")
public class ApiImConversationRecentController extends BaseController {

    private final IImConversationRecentService imConversationRecentService;

    /**
     * 查询首页对话列列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImConversationRecentVo> list(ImConversationRecentBo bo, PageQuery pageQuery) {
        Long userId = LoginHelper.getUserId();
        bo.setFkUserId(userId);
        return imConversationRecentService.queryPageList(bo, pageQuery);
    }

    /**
     * 标记会话已读
     *
     * @param conversationId 会话ID
     */
    @PostMapping("/read/{conversationId}")
    @RepeatSubmit()
    public R<Void> markConversationAsRead(@NotNull(message = "会话ID不能为空") @PathVariable Long conversationId) {
        Long userId = LoginHelper.getUserId();
        return toAjax(imConversationRecentService.updateConversationRead(conversationId, userId));
    }

}
