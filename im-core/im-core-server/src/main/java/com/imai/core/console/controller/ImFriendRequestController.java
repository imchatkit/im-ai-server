package com.imai.core.console.controller;

import java.util.List;

import com.imai.core.domain.bo.ImFriendRequestBo;
import com.imai.core.domain.vo.ImFriendRequestVo;
import com.imai.core.service.IImFriendRequestService;
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
 * 管理系统/好友申请
 * 前端访问路由地址为:/imai/friendRequest
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/friendRequest")
public class ImFriendRequestController extends BaseController {

    private final IImFriendRequestService imFriendRequestService;

    /**
     * 查询好友申请列表
     */
    @SaCheckPermission("imcore:friendRequest:list")
    @GetMapping("/list")
    public TableDataInfo<ImFriendRequestVo> list(ImFriendRequestBo bo, PageQuery pageQuery) {
        return imFriendRequestService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出好友申请列表
     */
    @SaCheckPermission("imcore:friendRequest:export")
    @Log(title = "好友申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImFriendRequestBo bo, HttpServletResponse response) {
        List<ImFriendRequestVo> list = imFriendRequestService.queryList(bo);
        ExcelUtil.exportExcel(list, "好友申请", ImFriendRequestVo.class, response);
    }

    /**
     * 获取好友申请详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:friendRequest:query")
    @GetMapping("/{id}")
    public R<ImFriendRequestVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imFriendRequestService.queryById(id));
    }

    /**
     * 新增好友申请
     */
    @SaCheckPermission("imcore:friendRequest:add")
    @Log(title = "好友申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImFriendRequestBo bo) {
        return toAjax(imFriendRequestService.insertByBo(bo));
    }

    /**
     * 修改好友申请
     */
    @SaCheckPermission("imcore:friendRequest:edit")
    @Log(title = "好友申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImFriendRequestBo bo) {
        return toAjax(imFriendRequestService.updateByBo(bo));
    }

    /**
     * 删除好友申请
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:friendRequest:remove")
    @Log(title = "好友申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imFriendRequestService.deleteWithValidByIds(List.of(ids), true));
    }
}
