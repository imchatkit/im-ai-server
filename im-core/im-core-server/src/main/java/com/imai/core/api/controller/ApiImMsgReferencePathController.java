package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImMsgReferencePathBo;
import com.imai.core.domain.vo.ImMsgReferencePathVo;
import com.imai.core.service.IImMsgReferencePathService;
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
 * IM应用/消息引用路径
 * 前端访问路由地址为:/api/v1/msgReferencePath
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/msgReferencePath")
public class ApiImMsgReferencePathController extends BaseController {

    private final IImMsgReferencePathService imMsgReferencePathService;

    /**
     * 查询消息引用路径列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImMsgReferencePathVo> list(ImMsgReferencePathBo bo, PageQuery pageQuery) {
        return imMsgReferencePathService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息引用路径列表
     */
    @Log(title = "消息引用路径", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImMsgReferencePathBo bo, HttpServletResponse response) {
        List<ImMsgReferencePathVo> list = imMsgReferencePathService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息引用路径", ImMsgReferencePathVo.class, response);
    }

    /**
     * 获取消息引用路径详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImMsgReferencePathVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(imMsgReferencePathService.queryById(id));
    }

    /**
     * 新增消息引用路径
     */
    @Log(title = "消息引用路径", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImMsgReferencePathBo bo) {
        return toAjax(imMsgReferencePathService.insertByBo(bo));
    }

    /**
     * 修改消息引用路径
     */
    @Log(title = "消息引用路径", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImMsgReferencePathBo bo) {
        return toAjax(imMsgReferencePathService.updateByBo(bo));
    }

    /**
     * 删除消息引用路径
     *
     * @param ids 主键串
     */
    @Log(title = "消息引用路径", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imMsgReferencePathService.deleteWithValidByIds(List.of(ids), true));
    }
}
