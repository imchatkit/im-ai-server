package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImSyncBo;
import com.imai.core.domain.vo.ImSyncVo;
import com.imai.core.service.IImSyncService;
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
 * 多端同步
 * 前端访问路由地址为:/imai/sync
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sync")
public class ImSyncController extends BaseController {

    private final IImSyncService imSyncService;

    /**
     * 查询多端同步列表
     */
    @SaCheckPermission("imcore:sync:list")
    @GetMapping("/list")
    public TableDataInfo<ImSyncVo> list(ImSyncBo bo, PageQuery pageQuery) {
        return imSyncService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出多端同步列表
     */
    @SaCheckPermission("imcore:sync:export")
    @Log(title = "多端同步", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImSyncBo bo, HttpServletResponse response) {
        List<ImSyncVo> list = imSyncService.queryList(bo);
        ExcelUtil.exportExcel(list, "多端同步", ImSyncVo.class, response);
    }

    /**
     * 获取多端同步详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:sync:query")
    @GetMapping("/{id}")
    public R<ImSyncVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imSyncService.queryById(id));
    }

    /**
     * 新增多端同步
     */
    @SaCheckPermission("imcore:sync:add")
    @Log(title = "多端同步", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImSyncBo bo) {
        return toAjax(imSyncService.insertByBo(bo));
    }

    /**
     * 修改多端同步
     */
    @SaCheckPermission("imcore:sync:edit")
    @Log(title = "多端同步", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImSyncBo bo) {
        return toAjax(imSyncService.updateByBo(bo));
    }

    /**
     * 删除多端同步
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:sync:remove")
    @Log(title = "多端同步", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imSyncService.deleteWithValidByIds(List.of(ids), true));
    }
}
