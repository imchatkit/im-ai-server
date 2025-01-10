package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImMsgRecallBo;
import com.imai.core.domain.vo.ImMsgRecallVo;
import com.imai.core.service.IImMsgRecallService;
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
 * 管理系统/消息撤回记录
 * 前端访问路由地址为:/imai/msgRecall
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/msgRecall")
public class ImMsgRecallController extends BaseController {

    private final IImMsgRecallService imMsgRecallService;

    /**
     * 查询消息撤回记录列表
     */
    @SaCheckPermission("imcore:msgRecall:list")
    @GetMapping("/list")
    public TableDataInfo<ImMsgRecallVo> list(ImMsgRecallBo bo, PageQuery pageQuery) {
        return imMsgRecallService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息撤回记录列表
     */
    @SaCheckPermission("imcore:msgRecall:export")
    @Log(title = "消息撤回记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImMsgRecallBo bo, HttpServletResponse response) {
        List<ImMsgRecallVo> list = imMsgRecallService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息撤回记录", ImMsgRecallVo.class, response);
    }

    /**
     * 获取消息撤回记录详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:msgRecall:query")
    @GetMapping("/{id}")
    public R<ImMsgRecallVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imMsgRecallService.queryById(id));
    }

    /**
     * 新增消息撤回记录
     */
    @SaCheckPermission("imcore:msgRecall:add")
    @Log(title = "消息撤回记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImMsgRecallBo bo) {
        return toAjax(imMsgRecallService.insertByBo(bo));
    }

    /**
     * 修改消息撤回记录
     */
    @SaCheckPermission("imcore:msgRecall:edit")
    @Log(title = "消息撤回记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImMsgRecallBo bo) {
        return toAjax(imMsgRecallService.updateByBo(bo));
    }

    /**
     * 删除消息撤回记录
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:msgRecall:remove")
    @Log(title = "消息撤回记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imMsgRecallService.deleteWithValidByIds(List.of(ids), true));
    }
}
