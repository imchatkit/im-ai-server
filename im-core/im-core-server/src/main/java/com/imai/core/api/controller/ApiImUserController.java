package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImUserBo;
import com.imai.core.domain.vo.ImUserVo;
import com.imai.core.service.IImUserService;
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
 * IM应用/用户
 * 前端访问路由地址为:/api/v1/user
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class ApiImUserController extends BaseController {

    private final IImUserService imUserService;

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImUserVo> list(ImUserBo bo, PageQuery pageQuery) {
        return imUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户列表
     */
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImUserBo bo, HttpServletResponse response) {
        List<ImUserVo> list = imUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户", ImUserVo.class, response);
    }

    /**
     * 获取用户详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImUserVo> getInfo(@NotNull(message = "主键不能为空")
                               @PathVariable Long id) {
        return R.ok(imUserService.queryById(id));
    }

    /**
     * 新增用户
     */
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImUserBo bo) {
        return toAjax(imUserService.insertByBo(bo));
    }

    /**
     * 修改用户
     */
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImUserBo bo) {
        return toAjax(imUserService.updateByBo(bo));
    }

    /**
     * 删除用户
     *
     * @param ids 主键串
     */
    @Log(title = "用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imUserService.deleteWithValidByIds(List.of(ids), true));
    }
}
