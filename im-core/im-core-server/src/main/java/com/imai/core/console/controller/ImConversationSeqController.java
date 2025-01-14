package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImConversationSeqBo;
import com.imai.core.domain.vo.ImConversationSeqVo;
import com.imai.core.service.IImConversationSeqService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
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
 * 管理系统/会话序列号
 * 前端访问路由地址为:/imai/conversationSeq
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/conversationSeq")
public class ImConversationSeqController extends BaseController {

    private final IImConversationSeqService imConversationSeqService;

    /**
     * 查询会话序列号列表
     */
    @SaCheckPermission("imcore:conversationSeq:list")
    @GetMapping("/list")
    public TableDataInfo<ImConversationSeqVo> list(ImConversationSeqBo bo, PageQuery pageQuery) {
        return imConversationSeqService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会话序列号列表
     */
    @SaCheckPermission("imcore:conversationSeq:export")
    @Log(title = "会话序列号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImConversationSeqBo bo, HttpServletResponse response) {
        List<ImConversationSeqVo> list = imConversationSeqService.queryList(bo);
        ExcelUtil.exportExcel(list, "会话序列号", ImConversationSeqVo.class, response);
    }

    /**
     * 获取会话序列号详细信息
     *
     * @param conversationId 主键
     */
    @SaCheckPermission("imcore:conversationSeq:query")
    @GetMapping("/{conversationId}")
    public R<ImConversationSeqVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long conversationId) {
        return R.ok(imConversationSeqService.queryById(conversationId));
    }

    /**
     * 新增会话序列号
     */
    @SaCheckPermission("imcore:conversationSeq:add")
    @Log(title = "会话序列号", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImConversationSeqBo bo) {
        return toAjax(imConversationSeqService.insertByBo(bo));
    }

    /**
     * 修改会话序列号
     */
    @SaCheckPermission("imcore:conversationSeq:edit")
    @Log(title = "会话序列号", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImConversationSeqBo bo) {
        return toAjax(imConversationSeqService.updateByBo(bo));
    }

    /**
     * 删除会话序列号
     *
     * @param conversationIds 主键串
     */
    @SaCheckPermission("imcore:conversationSeq:remove")
    @Log(title = "会话序列号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{conversationIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] conversationIds) {
        return toAjax(imConversationSeqService.deleteWithValidByIds(List.of(conversationIds), true));
    }
}
