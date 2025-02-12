package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImConversationMemberBatchAddBo;
import com.imai.core.domain.bo.ImConversationMemberBo;
import com.imai.core.domain.vo.ImConversationMemberVo;
import com.imai.core.service.IImConversationMemberService;
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
 * IM应用/会话成员
 * 前端访问路由地址为:/api/v1/conversationMember
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversationMember")
public class ApiImConversationMemberController extends BaseController {

    private final IImConversationMemberService imConversationMemberService;

    /**
     * 查询会话成员列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImConversationMemberVo> list(ImConversationMemberBo bo, PageQuery pageQuery) {
        return imConversationMemberService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会话成员列表
     */
    @Log(title = "会话成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImConversationMemberBo bo, HttpServletResponse response) {
        List<ImConversationMemberVo> list = imConversationMemberService.queryList(bo);
        ExcelUtil.exportExcel(list, "会话成员", ImConversationMemberVo.class, response);
    }

    /**
     * 获取会话成员详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImConversationMemberVo> getInfo(@NotNull(message = "主键不能为空")
                                             @PathVariable Long id) {
        return R.ok(imConversationMemberService.queryById(id));
    }

    /**
     * 新增会话成员
     */
    @Log(title = "会话成员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImConversationMemberBo bo) {
        return toAjax(imConversationMemberService.insertByBo(bo));
    }

    /**
     * 修改会话成员
     */
    @Log(title = "会话成员", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImConversationMemberBo bo) {
        return toAjax(imConversationMemberService.updateByBo(bo));
    }

    /**
     * 删除会话成员
     *
     * @param ids 主键串
     */
    @Log(title = "会话成员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imConversationMemberService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 批量添加用户到会话
     */
    @Log(title = "批量添加会话成员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/batchAdd")
    public R<Void> batchAddUsers(@Validated @RequestBody ImConversationMemberBatchAddBo bo) {
        return toAjax(imConversationMemberService.batchAddUsersToConversationForApi(bo));
    }

    /**
     * 根据会话ID查询成员列表
     *
     * @param conversationId 会话ID
     */
    @GetMapping("/listByConversation/{conversationId}")
    public R<List<ImConversationMemberVo>> listByConversation(
        @NotNull(message = "会话ID不能为空") @PathVariable Long conversationId) {
        return R.ok(imConversationMemberService.queryListByConversationIdForApi(conversationId));
    }
}
