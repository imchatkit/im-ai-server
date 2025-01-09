package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImUserStatusBo;
import com.imai.core.domain.vo.ImUserStatusVo;
import com.imai.core.service.IImUserStatusService;
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
 * 用户状态
 * 前端访问路由地址为:/imai/userStatus
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/userStatus")
public class ImUserStatusController extends BaseController {

    private final IImUserStatusService imUserStatusService;

    /**
     * 查询用户状态列表
     */
    @SaCheckPermission("imcore:userStatus:list")
    @GetMapping("/list")
    public TableDataInfo<ImUserStatusVo> list(ImUserStatusBo bo, PageQuery pageQuery) {
        return imUserStatusService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户状态列表
     */
    @SaCheckPermission("imcore:userStatus:export")
    @Log(title = "用户状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImUserStatusBo bo, HttpServletResponse response) {
        List<ImUserStatusVo> list = imUserStatusService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户状态", ImUserStatusVo.class, response);
    }

    /**
     * 获取用户状态详细信息
     *
     * @param userId 主键
     */
    @SaCheckPermission("imcore:userStatus:query")
    @GetMapping("/{userId}")
    public R<ImUserStatusVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long userId) {
        return R.ok(imUserStatusService.queryById(userId));
    }

    /**
     * 新增用户状态
     */
    @SaCheckPermission("imcore:userStatus:add")
    @Log(title = "用户状态", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImUserStatusBo bo) {
        return toAjax(imUserStatusService.insertByBo(bo));
    }

    /**
     * 修改用户状态
     */
    @SaCheckPermission("imcore:userStatus:edit")
    @Log(title = "用户状态", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImUserStatusBo bo) {
        return toAjax(imUserStatusService.updateByBo(bo));
    }

    /**
     * 删除用户状态
     *
     * @param userIds 主键串
     */
    @SaCheckPermission("imcore:userStatus:remove")
    @Log(title = "用户状态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] userIds) {
        return toAjax(imUserStatusService.deleteWithValidByIds(List.of(userIds), true));
    }
}
