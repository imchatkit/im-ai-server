package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImFriendRequestBo;
import com.imai.core.domain.vo.ImFriendRequestVo;
import com.imai.core.service.IImFriendRequestService;
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
 * IM应用/好友申请
 * 前端访问路由地址为:/api/v1/friendRequest
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/friendRequest")
public class ApiImFriendRequestController extends BaseController {

    private final IImFriendRequestService imFriendRequestService;

    /**
     * 查询好友申请列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImFriendRequestVo> list(ImFriendRequestBo bo, PageQuery pageQuery) {
        return imFriendRequestService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出好友申请列表
     */
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
    @GetMapping("/{id}")
    public R<ImFriendRequestVo> getInfo(@NotNull(message = "主键不能为空")
                                        @PathVariable Long id) {
        return R.ok(imFriendRequestService.queryById(id));
    }

    /**
     * 新增好友申请
     */
    @Log(title = "好友申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImFriendRequestBo bo) {
        return toAjax(imFriendRequestService.insertByBo(bo));
    }

    /**
     * 修改好友申请
     */
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
    @Log(title = "好友申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imFriendRequestService.deleteWithValidByIds(List.of(ids), true));
    }
}
