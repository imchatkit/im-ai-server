package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImDeviceBo;
import com.imai.core.domain.vo.ImDeviceVo;
import com.imai.core.service.IImDeviceService;
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
 * IM应用/客户端设备
 * 前端访问路由地址为:/api/v1/device
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/device")
public class ApiImDeviceController extends BaseController {

    private final IImDeviceService imDeviceService;

    /**
     * 查询客户端设备列表
     */
    @SaCheckPermission("imcore:device:list")
    @GetMapping("/list")
    public TableDataInfo<ImDeviceVo> list(ImDeviceBo bo, PageQuery pageQuery) {
        return imDeviceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出客户端设备列表
     */
    @SaCheckPermission("imcore:device:export")
    @Log(title = "客户端设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImDeviceBo bo, HttpServletResponse response) {
        List<ImDeviceVo> list = imDeviceService.queryList(bo);
        ExcelUtil.exportExcel(list, "客户端设备", ImDeviceVo.class, response);
    }

    /**
     * 获取客户端设备详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:device:query")
    @GetMapping("/{id}")
    public R<ImDeviceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imDeviceService.queryById(id));
    }

    /**
     * 新增客户端设备
     */
    @SaCheckPermission("imcore:device:add")
    @Log(title = "客户端设备", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImDeviceBo bo) {
        return toAjax(imDeviceService.insertByBo(bo));
    }

    /**
     * 修改客户端设备
     */
    @SaCheckPermission("imcore:device:edit")
    @Log(title = "客户端设备", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImDeviceBo bo) {
        return toAjax(imDeviceService.updateByBo(bo));
    }

    /**
     * 删除客户端设备
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:device:remove")
    @Log(title = "客户端设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imDeviceService.deleteWithValidByIds(List.of(ids), true));
    }
}
