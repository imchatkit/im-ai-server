package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImMsgReceiverBo;
import com.imai.core.domain.vo.ImMsgReceiverVo;
import com.imai.core.service.IImMsgReceiverService;
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
 * IM应用/消息接收
 * 前端访问路由地址为:/api/v1/msgReceiver
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/msgReceiver")
public class ApiImMsgReceiverController extends BaseController {

    private final IImMsgReceiverService imMsgReceiverService;

    /**
     * 查询消息接收列表
     */
    @SaCheckPermission("imcore:msgReceiver:list")
    @GetMapping("/list")
    public TableDataInfo<ImMsgReceiverVo> list(ImMsgReceiverBo bo, PageQuery pageQuery) {
        return imMsgReceiverService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息接收列表
     */
    @SaCheckPermission("imcore:msgReceiver:export")
    @Log(title = "消息接收", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImMsgReceiverBo bo, HttpServletResponse response) {
        List<ImMsgReceiverVo> list = imMsgReceiverService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息接收", ImMsgReceiverVo.class, response);
    }

    /**
     * 获取消息接收详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:msgReceiver:query")
    @GetMapping("/{id}")
    public R<ImMsgReceiverVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imMsgReceiverService.queryById(id));
    }

    /**
     * 新增消息接收
     */
    @SaCheckPermission("imcore:msgReceiver:add")
    @Log(title = "消息接收", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImMsgReceiverBo bo) {
        return toAjax(imMsgReceiverService.insertByBo(bo));
    }

    /**
     * 修改消息接收
     */
    @SaCheckPermission("imcore:msgReceiver:edit")
    @Log(title = "消息接收", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImMsgReceiverBo bo) {
        return toAjax(imMsgReceiverService.updateByBo(bo));
    }

    /**
     * 删除消息接收
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:msgReceiver:remove")
    @Log(title = "消息接收", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imMsgReceiverService.deleteWithValidByIds(List.of(ids), true));
    }
}
