package com.imai.core.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImChannelMemberBo;
import com.imai.core.domain.vo.ImChannelMemberVo;
import com.imai.core.service.IImChannelMemberService;
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
 * 频道成员
 * 前端访问路由地址为:/imai/channelMember
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/channelMember")
public class ImChannelMemberController extends BaseController {

    private final IImChannelMemberService imChannelMemberService;

    /**
     * 查询频道成员列表
     */
    @SaCheckPermission("imai:channelMember:list")
    @GetMapping("/list")
    public TableDataInfo<ImChannelMemberVo> list(ImChannelMemberBo bo, PageQuery pageQuery) {
        return imChannelMemberService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出频道成员列表
     */
    @SaCheckPermission("imai:channelMember:export")
    @Log(title = "频道成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImChannelMemberBo bo, HttpServletResponse response) {
        List<ImChannelMemberVo> list = imChannelMemberService.queryList(bo);
        ExcelUtil.exportExcel(list, "频道成员", ImChannelMemberVo.class, response);
    }

    /**
     * 获取频道成员详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imai:channelMember:query")
    @GetMapping("/{id}")
    public R<ImChannelMemberVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imChannelMemberService.queryById(id));
    }

    /**
     * 新增频道成员
     */
    @SaCheckPermission("imai:channelMember:add")
    @Log(title = "频道成员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImChannelMemberBo bo) {
        return toAjax(imChannelMemberService.insertByBo(bo));
    }

    /**
     * 修改频道成员
     */
    @SaCheckPermission("imai:channelMember:edit")
    @Log(title = "频道成员", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImChannelMemberBo bo) {
        return toAjax(imChannelMemberService.updateByBo(bo));
    }

    /**
     * 删除频道成员
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imai:channelMember:remove")
    @Log(title = "频道成员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imChannelMemberService.deleteWithValidByIds(List.of(ids), true));
    }
}
