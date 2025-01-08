package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImDevicePtsBo;
import com.imai.core.domain.vo.ImDevicePtsVo;
import com.imai.core.service.IImDevicePtsService;
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
 * 设备pts
 * 前端访问路由地址为:/imai/devicePts
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/devicePts")
public class ImDevicePtsController extends BaseController {

    private final IImDevicePtsService imDevicePtsService;

    /**
     * 查询设备pts列表
     */
    @SaCheckPermission("imcore:devicePts:list")
    @GetMapping("/list")
    public TableDataInfo<ImDevicePtsVo> list(ImDevicePtsBo bo, PageQuery pageQuery) {
        return imDevicePtsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备pts列表
     */
    @SaCheckPermission("imcore:devicePts:export")
    @Log(title = "设备pts", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImDevicePtsBo bo, HttpServletResponse response) {
        List<ImDevicePtsVo> list = imDevicePtsService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备pts", ImDevicePtsVo.class, response);
    }

    /**
     * 获取设备pts详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:devicePts:query")
    @GetMapping("/{id}")
    public R<ImDevicePtsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imDevicePtsService.queryById(id));
    }

    /**
     * 新增设备pts
     */
    @SaCheckPermission("imcore:devicePts:add")
    @Log(title = "设备pts", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImDevicePtsBo bo) {
        return toAjax(imDevicePtsService.insertByBo(bo));
    }

    /**
     * 修改设备pts
     */
    @SaCheckPermission("imcore:devicePts:edit")
    @Log(title = "设备pts", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImDevicePtsBo bo) {
        return toAjax(imDevicePtsService.updateByBo(bo));
    }

    /**
     * 删除设备pts
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:devicePts:remove")
    @Log(title = "设备pts", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imDevicePtsService.deleteWithValidByIds(List.of(ids), true));
    }
}
