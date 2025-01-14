package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImMsgReadBo;
import com.imai.core.domain.vo.ImMsgReadVo;
import com.imai.core.service.IImMsgReadService;
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
 * IM应用/消息已读记录
 * 前端访问路由地址为:/api/v1/msgRead
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/msgRead")
public class ApiImMsgReadController extends BaseController {

    private final IImMsgReadService imMsgReadService;

    /**
     * 查询消息已读记录列表
     */
    @SaCheckPermission("imcore:msgRead:list")
    @GetMapping("/list")
    public TableDataInfo<ImMsgReadVo> list(ImMsgReadBo bo, PageQuery pageQuery) {
        return imMsgReadService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息已读记录列表
     */
    @SaCheckPermission("imcore:msgRead:export")
    @Log(title = "消息已读记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImMsgReadBo bo, HttpServletResponse response) {
        List<ImMsgReadVo> list = imMsgReadService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息已读记录", ImMsgReadVo.class, response);
    }

    /**
     * 获取消息已读记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:msgRead:query")
    @GetMapping("/{id}")
    public R<ImMsgReadVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imMsgReadService.queryById(id));
    }

    /**
     * 新增消息已读记录
     */
    @SaCheckPermission("imcore:msgRead:add")
    @Log(title = "消息已读记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImMsgReadBo bo) {
        return toAjax(imMsgReadService.insertByBo(bo));
    }

    /**
     * 修改消息已读记录
     */
    @SaCheckPermission("imcore:msgRead:edit")
    @Log(title = "消息已读记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImMsgReadBo bo) {
        return toAjax(imMsgReadService.updateByBo(bo));
    }

    /**
     * 删除消息已读记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:msgRead:remove")
    @Log(title = "消息已读记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imMsgReadService.deleteWithValidByIds(List.of(ids), true));
    }
}
