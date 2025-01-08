package com.imai.core.controller;

import java.util.List;

import com.imai.core.domain.bo.ImWebhookConfigBo;
import com.imai.core.domain.vo.ImWebhookConfigVo;
import com.imai.core.service.IImWebhookConfigService;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * Webhook配置
 * 前端访问路由地址为:/imai/webhookConfig
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/webhookConfig")
public class ImWebhookConfigController extends BaseController {

    private final IImWebhookConfigService imWebhookConfigService;

    /**
     * 查询Webhook配置列表
     */
    @SaCheckPermission("imcore:webhookConfig:list")
    @GetMapping("/list")
    public TableDataInfo<ImWebhookConfigVo> list(ImWebhookConfigBo bo, PageQuery pageQuery) {
        return imWebhookConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出Webhook配置列表
     */
    @SaCheckPermission("imcore:webhookConfig:export")
    @Log(title = "Webhook配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImWebhookConfigBo bo, HttpServletResponse response) {
        List<ImWebhookConfigVo> list = imWebhookConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "Webhook配置", ImWebhookConfigVo.class, response);
    }

    /**
     * 获取Webhook配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:webhookConfig:query")
    @GetMapping("/{id}")
    public R<ImWebhookConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imWebhookConfigService.queryById(id));
    }

    /**
     * 新增Webhook配置
     */
    @SaCheckPermission("imcore:webhookConfig:add")
    @Log(title = "Webhook配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImWebhookConfigBo bo) {
        return toAjax(imWebhookConfigService.insertByBo(bo));
    }

    /**
     * 修改Webhook配置
     */
    @SaCheckPermission("imcore:webhookConfig:edit")
    @Log(title = "Webhook配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImWebhookConfigBo bo) {
        return toAjax(imWebhookConfigService.updateByBo(bo));
    }

    /**
     * 删除Webhook配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:webhookConfig:remove")
    @Log(title = "Webhook配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imWebhookConfigService.deleteWithValidByIds(List.of(ids), true));
    }
}
