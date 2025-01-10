package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImGroupAnnouncementBo;
import com.imai.core.domain.vo.ImGroupAnnouncementVo;
import com.imai.core.service.IImGroupAnnouncementService;
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
 * IM应用/群公告
 * 前端访问路由地址为:/api/v1/groupAnnouncement
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groupAnnouncement")
public class ApiImGroupAnnouncementController extends BaseController {

    private final IImGroupAnnouncementService imGroupAnnouncementService;

    /**
     * 查询群公告列表
     */
    @SaCheckPermission("imcore:groupAnnouncement:list")
    @GetMapping("/list")
    public TableDataInfo<ImGroupAnnouncementVo> list(ImGroupAnnouncementBo bo, PageQuery pageQuery) {
        return imGroupAnnouncementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出群公告列表
     */
    @SaCheckPermission("imcore:groupAnnouncement:export")
    @Log(title = "群公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImGroupAnnouncementBo bo, HttpServletResponse response) {
        List<ImGroupAnnouncementVo> list = imGroupAnnouncementService.queryList(bo);
        ExcelUtil.exportExcel(list, "群公告", ImGroupAnnouncementVo.class, response);
    }

    /**
     * 获取群公告详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:groupAnnouncement:query")
    @GetMapping("/{id}")
    public R<ImGroupAnnouncementVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imGroupAnnouncementService.queryById(id));
    }

    /**
     * 新增群公告
     */
    @SaCheckPermission("imcore:groupAnnouncement:add")
    @Log(title = "群公告", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImGroupAnnouncementBo bo) {
        return toAjax(imGroupAnnouncementService.insertByBo(bo));
    }

    /**
     * 修改群公告
     */
    @SaCheckPermission("imcore:groupAnnouncement:edit")
    @Log(title = "群公告", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImGroupAnnouncementBo bo) {
        return toAjax(imGroupAnnouncementService.updateByBo(bo));
    }

    /**
     * 删除群公告
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:groupAnnouncement:remove")
    @Log(title = "群公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imGroupAnnouncementService.deleteWithValidByIds(List.of(ids), true));
    }
}
