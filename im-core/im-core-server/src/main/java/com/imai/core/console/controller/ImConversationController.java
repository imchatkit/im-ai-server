package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImConversationBo;
import com.imai.core.domain.vo.ImConversationVo;
import com.imai.core.service.IImConversationService;
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
 * 管理系统/聊天会话基础
 * 前端访问路由地址为:/imai/conversation
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/conversation")
public class ImConversationController extends BaseController {

    private final IImConversationService imConversationService;

    /**
     * 查询聊天会话基础列表
     */
    @SaCheckPermission("imcore:conversation:list")
    @GetMapping("/list")
    public TableDataInfo<ImConversationVo> list(ImConversationBo bo, PageQuery pageQuery) {
        return imConversationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出聊天会话基础列表
     */
    @SaCheckPermission("imcore:conversation:export")
    @Log(title = "聊天会话基础", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImConversationBo bo, HttpServletResponse response) {
        List<ImConversationVo> list = imConversationService.queryList(bo);
        ExcelUtil.exportExcel(list, "聊天会话基础", ImConversationVo.class, response);
    }

    /**
     * 获取聊天会话基础详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:conversation:query")
    @GetMapping("/{id}")
    public R<ImConversationVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(imConversationService.queryById(id));
    }

    /**
     * 新增聊天会话基础
     */
    @SaCheckPermission("imcore:conversation:add")
    @Log(title = "聊天会话基础", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImConversationBo bo) {
        return toAjax(imConversationService.insertByBo(bo));
    }

    /**
     * 修改聊天会话基础
     */
    @SaCheckPermission("imcore:conversation:edit")
    @Log(title = "聊天会话基础", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImConversationBo bo) {
        return toAjax(imConversationService.updateByBo(bo));
    }

    /**
     * 删除聊天会话基础
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:conversation:remove")
    @Log(title = "聊天会话基础", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imConversationService.deleteWithValidByIds(List.of(ids), true));
    }
}
