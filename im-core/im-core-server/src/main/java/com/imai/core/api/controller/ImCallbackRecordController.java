package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImCallbackRecordBo;
import com.imai.core.domain.vo.ImCallbackRecordVo;
import com.imai.core.service.IImCallbackRecordService;
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
 * 消息回调记录
 * 前端访问路由地址为:/imai/callbackRecord
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/callbackRecord")
public class ImCallbackRecordController extends BaseController {

    private final IImCallbackRecordService imCallbackRecordService;

    /**
     * 查询消息回调记录列表
     */
    @SaCheckPermission("imcore:callbackRecord:list")
    @GetMapping("/list")
    public TableDataInfo<ImCallbackRecordVo> list(ImCallbackRecordBo bo, PageQuery pageQuery) {
        return imCallbackRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息回调记录列表
     */
    @SaCheckPermission("imcore:callbackRecord:export")
    @Log(title = "消息回调记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImCallbackRecordBo bo, HttpServletResponse response) {
        List<ImCallbackRecordVo> list = imCallbackRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息回调记录", ImCallbackRecordVo.class, response);
    }

    /**
     * 获取消息回调记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:callbackRecord:query")
    @GetMapping("/{id}")
    public R<ImCallbackRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imCallbackRecordService.queryById(id));
    }

    /**
     * 新增消息回调记录
     */
    @SaCheckPermission("imcore:callbackRecord:add")
    @Log(title = "消息回调记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImCallbackRecordBo bo) {
        return toAjax(imCallbackRecordService.insertByBo(bo));
    }

    /**
     * 修改消息回调记录
     */
    @SaCheckPermission("imcore:callbackRecord:edit")
    @Log(title = "消息回调记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImCallbackRecordBo bo) {
        return toAjax(imCallbackRecordService.updateByBo(bo));
    }

    /**
     * 删除消息回调记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:callbackRecord:remove")
    @Log(title = "消息回调记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imCallbackRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
