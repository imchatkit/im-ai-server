package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImSysConversationInitBo;
import com.imai.core.domain.vo.ImSysConversationInitVo;
import com.imai.core.service.IImSysConversationInitService;
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
 * IM应用/系统会话初始化
 * 前端访问路由地址为:/api/v1/sysConversationInit
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sysConversationInit")
public class ApiImSysConversationInitController extends BaseController {

    private final IImSysConversationInitService imSysConversationInitService;

    /**
     * 查询系统会话初始化列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImSysConversationInitVo> list(ImSysConversationInitBo bo, PageQuery pageQuery) {
        return imSysConversationInitService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出系统会话初始化列表
     */
    @Log(title = "系统会话初始化", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImSysConversationInitBo bo, HttpServletResponse response) {
        List<ImSysConversationInitVo> list = imSysConversationInitService.queryList(bo);
        ExcelUtil.exportExcel(list, "系统会话初始化", ImSysConversationInitVo.class, response);
    }

    /**
     * 获取系统会话初始化详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImSysConversationInitVo> getInfo(@NotNull(message = "主键不能为空")
                                              @PathVariable Long id) {
        return R.ok(imSysConversationInitService.queryById(id));
    }

    /**
     * 新增系统会话初始化
     */
    @Log(title = "系统会话初始化", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImSysConversationInitBo bo) {
        return toAjax(imSysConversationInitService.insertByBo(bo));
    }

    /**
     * 修改系统会话初始化
     */
    @Log(title = "系统会话初始化", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImSysConversationInitBo bo) {
        return toAjax(imSysConversationInitService.updateByBo(bo));
    }

    /**
     * 删除系统会话初始化
     *
     * @param ids 主键串
     */
    @Log(title = "系统会话初始化", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imSysConversationInitService.deleteWithValidByIds(List.of(ids), true));
    }
}
