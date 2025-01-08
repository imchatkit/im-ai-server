package com.imai.core.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImConversationRecentBo;
import com.imai.core.domain.vo.ImConversationRecentVo;
import com.imai.core.service.IImConversationRecentService;
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
 * 首页对话列
 * 前端访问路由地址为:/imai/conversationRecent
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/conversationRecent")
public class ImConversationRecentController extends BaseController {

    private final IImConversationRecentService imConversationRecentService;

    /**
     * 查询首页对话列列表
     */
    @SaCheckPermission("imcore:conversationRecent:list")
    @GetMapping("/list")
    public TableDataInfo<ImConversationRecentVo> list(ImConversationRecentBo bo, PageQuery pageQuery) {
        return imConversationRecentService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出首页对话列列表
     */
    @SaCheckPermission("imcore:conversationRecent:export")
    @Log(title = "首页对话列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImConversationRecentBo bo, HttpServletResponse response) {
        List<ImConversationRecentVo> list = imConversationRecentService.queryList(bo);
        ExcelUtil.exportExcel(list, "首页对话列", ImConversationRecentVo.class, response);
    }

    /**
     * 获取首页对话列详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:conversationRecent:query")
    @GetMapping("/{id}")
    public R<ImConversationRecentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imConversationRecentService.queryById(id));
    }

    /**
     * 新增首页对话列
     */
    @SaCheckPermission("imcore:conversationRecent:add")
    @Log(title = "首页对话列", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImConversationRecentBo bo) {
        return toAjax(imConversationRecentService.insertByBo(bo));
    }

    /**
     * 修改首页对话列
     */
    @SaCheckPermission("imcore:conversationRecent:edit")
    @Log(title = "首页对话列", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImConversationRecentBo bo) {
        return toAjax(imConversationRecentService.updateByBo(bo));
    }

    /**
     * 删除首页对话列
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:conversationRecent:remove")
    @Log(title = "首页对话列", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imConversationRecentService.deleteWithValidByIds(List.of(ids), true));
    }
}
