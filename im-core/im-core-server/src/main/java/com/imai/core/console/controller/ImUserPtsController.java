package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImUserPtsBo;
import com.imai.core.domain.vo.ImUserPtsVo;
import com.imai.core.service.IImUserPtsService;
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
 * 管理系统/用户pts
 * 前端访问路由地址为:/imai/userPts
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/userPts")
public class ImUserPtsController extends BaseController {

    private final IImUserPtsService imUserPtsService;

    /**
     * 查询用户pts列表
     */
    @SaCheckPermission("imcore:userPts:list")
    @GetMapping("/list")
    public TableDataInfo<ImUserPtsVo> list(ImUserPtsBo bo, PageQuery pageQuery) {
        return imUserPtsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户pts列表
     */
    @SaCheckPermission("imcore:userPts:export")
    @Log(title = "用户pts", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImUserPtsBo bo, HttpServletResponse response) {
        List<ImUserPtsVo> list = imUserPtsService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户pts", ImUserPtsVo.class, response);
    }

    /**
     * 获取用户pts详细信息
     *
     * @param userId 主键
     */
    @SaCheckPermission("imcore:userPts:query")
    @GetMapping("/{userId}")
    public R<ImUserPtsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long userId) {
        return R.ok(imUserPtsService.queryById(userId));
    }

    /**
     * 新增用户pts
     */
    @SaCheckPermission("imcore:userPts:add")
    @Log(title = "用户pts", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImUserPtsBo bo) {
        return toAjax(imUserPtsService.insertByBo(bo));
    }

    /**
     * 修改用户pts
     */
    @SaCheckPermission("imcore:userPts:edit")
    @Log(title = "用户pts", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImUserPtsBo bo) {
        return toAjax(imUserPtsService.updateByBo(bo));
    }

    /**
     * 删除用户pts
     *
     * @param userIds 主键串
     */
    @SaCheckPermission("imcore:userPts:remove")
    @Log(title = "用户pts", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] userIds) {
        return toAjax(imUserPtsService.deleteWithValidByIds(List.of(userIds), true));
    }
}
