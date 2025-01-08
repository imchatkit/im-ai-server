package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImGroupBo;
import com.imai.core.domain.vo.ImGroupVo;
import com.imai.core.service.IImGroupService;
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
 * 群组
 * 前端访问路由地址为:/imai/group
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/group")
public class ImGroupController extends BaseController {

    private final IImGroupService imGroupService;

    /**
     * 查询群组列表
     */
    @SaCheckPermission("imcore:group:list")
    @GetMapping("/list")
    public TableDataInfo<ImGroupVo> list(ImGroupBo bo, PageQuery pageQuery) {
        return imGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出群组列表
     */
    @SaCheckPermission("imcore:group:export")
    @Log(title = "群组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImGroupBo bo, HttpServletResponse response) {
        List<ImGroupVo> list = imGroupService.queryList(bo);
        ExcelUtil.exportExcel(list, "群组", ImGroupVo.class, response);
    }

    /**
     * 获取群组详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:group:query")
    @GetMapping("/{id}")
    public R<ImGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imGroupService.queryById(id));
    }

    /**
     * 新增群组
     */
    @SaCheckPermission("imcore:group:add")
    @Log(title = "群组", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImGroupBo bo) {
        return toAjax(imGroupService.insertByBo(bo));
    }

    /**
     * 修改群组
     */
    @SaCheckPermission("imcore:group:edit")
    @Log(title = "群组", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImGroupBo bo) {
        return toAjax(imGroupService.updateByBo(bo));
    }

    /**
     * 删除群组
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:group:remove")
    @Log(title = "群组", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imGroupService.deleteWithValidByIds(List.of(ids), true));
    }
}
