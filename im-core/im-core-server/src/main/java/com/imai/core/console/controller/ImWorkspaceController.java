package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImWorkspaceBo;
import com.imai.core.domain.vo.ImWorkspaceVo;
import com.imai.core.service.IImWorkspaceService;
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
 * 管理系统/工作空间
 * 前端访问路由地址为:/imai/workspace
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workspace")
public class ImWorkspaceController extends BaseController {

    private final IImWorkspaceService imWorkspaceService;

    /**
     * 查询工作空间列表
     */
    @SaCheckPermission("imcore:workspace:list")
    @GetMapping("/list")
    public TableDataInfo<ImWorkspaceVo> list(ImWorkspaceBo bo, PageQuery pageQuery) {
        return imWorkspaceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出工作空间列表
     */
    @SaCheckPermission("imcore:workspace:export")
    @Log(title = "工作空间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImWorkspaceBo bo, HttpServletResponse response) {
        List<ImWorkspaceVo> list = imWorkspaceService.queryList(bo);
        ExcelUtil.exportExcel(list, "工作空间", ImWorkspaceVo.class, response);
    }

    /**
     * 获取工作空间详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:workspace:query")
    @GetMapping("/{id}")
    public R<ImWorkspaceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imWorkspaceService.queryById(id));
    }

    /**
     * 新增工作空间
     */
    @SaCheckPermission("imcore:workspace:add")
    @Log(title = "工作空间", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImWorkspaceBo bo) {
        return toAjax(imWorkspaceService.insertByBo(bo));
    }

    /**
     * 修改工作空间
     */
    @SaCheckPermission("imcore:workspace:edit")
    @Log(title = "工作空间", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImWorkspaceBo bo) {
        return toAjax(imWorkspaceService.updateByBo(bo));
    }

    /**
     * 删除工作空间
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:workspace:remove")
    @Log(title = "工作空间", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imWorkspaceService.deleteWithValidByIds(List.of(ids), true));
    }
}
