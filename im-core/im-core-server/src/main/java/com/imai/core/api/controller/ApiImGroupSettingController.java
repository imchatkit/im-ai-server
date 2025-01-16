package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImGroupSettingBo;
import com.imai.core.domain.vo.ImGroupSettingVo;
import com.imai.core.service.IImGroupSettingService;
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
 * IM应用/群组设置
 * 前端访问路由地址为:/api/v1/groupSetting
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groupSetting")
public class ApiImGroupSettingController extends BaseController {

    private final IImGroupSettingService imGroupSettingService;

    /**
     * 查询群组设置列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImGroupSettingVo> list(ImGroupSettingBo bo, PageQuery pageQuery) {
        return imGroupSettingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出群组设置列表
     */
    @Log(title = "群组设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImGroupSettingBo bo, HttpServletResponse response) {
        List<ImGroupSettingVo> list = imGroupSettingService.queryList(bo);
        ExcelUtil.exportExcel(list, "群组设置", ImGroupSettingVo.class, response);
    }

    /**
     * 获取群组设置详细信息
     *
     * @param fkGroupId 主键
     */
    @GetMapping("/{fkGroupId}")
    public R<ImGroupSettingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long fkGroupId) {
        return R.ok(imGroupSettingService.queryById(fkGroupId));
    }

    /**
     * 新增群组设置
     */
    @Log(title = "群组设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImGroupSettingBo bo) {
        return toAjax(imGroupSettingService.insertByBo(bo));
    }

    /**
     * 修改群组设置
     */
    @Log(title = "群组设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImGroupSettingBo bo) {
        return toAjax(imGroupSettingService.updateByBo(bo));
    }

    /**
     * 删除群组设置
     *
     * @param fkGroupIds 主键串
     */
    @Log(title = "群组设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fkGroupIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] fkGroupIds) {
        return toAjax(imGroupSettingService.deleteWithValidByIds(List.of(fkGroupIds), true));
    }
}
