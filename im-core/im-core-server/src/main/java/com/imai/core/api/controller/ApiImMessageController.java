package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.vo.ImMessageVo;
import com.imai.core.service.IImMessageService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.apache.dubbo.common.utils.LogHelper;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * IM应用/消息存储
 * 前端访问路由地址为:/api/v1/message
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/message")
public class ApiImMessageController extends BaseController {

    private final IImMessageService imMessageService;

    /**
     * 查询消息存储列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImMessageVo> list(ImMessageBo bo, PageQuery pageQuery) {
        Long userId = LoginHelper.getUserId();
        bo.setFkFromUserId(userId);
        return imMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询会话历史消息
     *
     * @param conversationId 会话ID
     * @param pageQuery      分页参数
     */
    @GetMapping("/history/{conversationId}")
    public TableDataInfo<ImMessageVo> getConversationHistory(
            @NotNull(message = "会话ID不能为空") @PathVariable Long conversationId,
            PageQuery pageQuery,
            ImMessageBo bo) {
        bo.setFkConversationId(conversationId);
        Long userId = LoginHelper.getUserId();
        bo.setFkFromUserId(userId);
        return imMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取消息存储详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImMessageVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(imMessageService.queryById(id));
    }

    // /**
    // * 新增消息存储
    // */
    // @Log(title = "消息存储", businessType = BusinessType.INSERT)
    // @RepeatSubmit()
    // @PostMapping()
    // public R<Void> add(@Validated(AddGroup.class) @RequestBody ImMessageBo bo) {
    // return toAjax(imMessageService.insertByBo(bo));
    // }

    // /**
    // * 修改消息存储
    // */
    // @Log(title = "消息存储", businessType = BusinessType.UPDATE)
    // @RepeatSubmit()
    // @PutMapping()
    // public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImMessageBo bo)
    // {
    // return toAjax(imMessageService.updateByBo(bo));
    // }

    // /**
    // * 删除消息存储
    // *
    // * @param ids 主键串
    // */
    // @Log(title = "消息存储", businessType = BusinessType.DELETE)
    // @DeleteMapping("/{ids}")
    // public R<Void> remove(@NotEmpty(message = "主键不能为空")
    // @PathVariable Long[] ids) {
    // return toAjax(imMessageService.deleteWithValidByIds(List.of(ids), true));
    // }

    // /**
    // * 导出消息存储列表
    // */
    // @Log(title = "消息存储", businessType = BusinessType.EXPORT)
    // @PostMapping("/export")
    // public void export(ImMessageBo bo, HttpServletResponse response) {
    // List<ImMessageVo> list = imMessageService.queryList(bo);
    // ExcelUtil.exportExcel(list, "消息存储", ImMessageVo.class, response);
    // }

}
