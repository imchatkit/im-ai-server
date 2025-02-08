package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImMsgReferenceBo;
import com.imai.core.domain.vo.ImMsgReferenceVo;
import com.imai.core.service.IImMsgReferenceService;
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
 * 管理系统/消息引用关系
 * 前端访问路由地址为:/imai/msgReference
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/msgReference")
public class ImMsgReferenceController extends BaseController {

    private final IImMsgReferenceService imMsgReferenceService;

    /**
     * 查询消息引用关系列表
     */
    @SaCheckPermission("imcore:msgReference:list")
    @GetMapping("/list")
    public TableDataInfo<ImMsgReferenceVo> list(ImMsgReferenceBo bo, PageQuery pageQuery) {
        return imMsgReferenceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息引用关系列表
     */
    @SaCheckPermission("imcore:msgReference:export")
    @Log(title = "消息引用关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImMsgReferenceBo bo, HttpServletResponse response) {
        List<ImMsgReferenceVo> list = imMsgReferenceService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息引用关系", ImMsgReferenceVo.class, response);
    }

    /**
     * 获取消息引用关系详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:msgReference:query")
    @GetMapping("/{id}")
    public R<ImMsgReferenceVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(imMsgReferenceService.queryById(id));
    }

    /**
     * 新增消息引用关系
     */
    @SaCheckPermission("imcore:msgReference:add")
    @Log(title = "消息引用关系", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImMsgReferenceBo bo) {
        return toAjax(imMsgReferenceService.insertByBo(bo));
    }

    /**
     * 修改消息引用关系
     */
    @SaCheckPermission("imcore:msgReference:edit")
    @Log(title = "消息引用关系", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImMsgReferenceBo bo) {
        return toAjax(imMsgReferenceService.updateByBo(bo));
    }

    /**
     * 删除消息引用关系
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:msgReference:remove")
    @Log(title = "消息引用关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imMsgReferenceService.deleteWithValidByIds(List.of(ids), true));
    }
}
