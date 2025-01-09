package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImChannelBo;
import com.imai.core.domain.vo.ImChannelVo;
import com.imai.core.service.IImChannelService;
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
 * 频道
 * 前端访问路由地址为:/imai/channel
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/channel")
public class ApiImChannelController extends BaseController {

    private final IImChannelService imChannelService;

    /**
     * 查询频道列表
     */
    @SaCheckPermission("imcore:channel:list")
    @GetMapping("/list")
    public TableDataInfo<ImChannelVo> list(ImChannelBo bo, PageQuery pageQuery) {
        return imChannelService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出频道列表
     */
    @SaCheckPermission("imcore:channel:export")
    @Log(title = "频道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImChannelBo bo, HttpServletResponse response) {
        List<ImChannelVo> list = imChannelService.queryList(bo);
        ExcelUtil.exportExcel(list, "频道", ImChannelVo.class, response);
    }

    /**
     * 获取频道详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:channel:query")
    @GetMapping("/{id}")
    public R<ImChannelVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imChannelService.queryById(id));
    }

    /**
     * 新增频道
     */
    @SaCheckPermission("imcore:channel:add")
    @Log(title = "频道", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImChannelBo bo) {
        return toAjax(imChannelService.insertByBo(bo));
    }

    /**
     * 修改频道
     */
    @SaCheckPermission("imcore:channel:edit")
    @Log(title = "频道", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImChannelBo bo) {
        return toAjax(imChannelService.updateByBo(bo));
    }

    /**
     * 删除频道
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:channel:remove")
    @Log(title = "频道", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imChannelService.deleteWithValidByIds(List.of(ids), true));
    }
}
