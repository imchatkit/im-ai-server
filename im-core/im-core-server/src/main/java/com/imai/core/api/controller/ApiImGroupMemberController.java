package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImGroupMemberBo;
import com.imai.core.domain.vo.ImGroupMemberVo;
import com.imai.core.service.IImGroupMemberService;
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
 * IM应用/群成员
 * 前端访问路由地址为:/api/v1/groupMember
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/groupMember")
public class ApiImGroupMemberController extends BaseController {

    private final IImGroupMemberService imGroupMemberService;

    /**
     * 查询群成员列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImGroupMemberVo> list(ImGroupMemberBo bo, PageQuery pageQuery) {
        return imGroupMemberService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出群成员列表
     */
    @Log(title = "群成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImGroupMemberBo bo, HttpServletResponse response) {
        List<ImGroupMemberVo> list = imGroupMemberService.queryList(bo);
        ExcelUtil.exportExcel(list, "群成员", ImGroupMemberVo.class, response);
    }

    /**
     * 获取群成员详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImGroupMemberVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imGroupMemberService.queryById(id));
    }

    /**
     * 新增群成员
     */
    @Log(title = "群成员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImGroupMemberBo bo) {
        return toAjax(imGroupMemberService.insertByBo(bo));
    }

    /**
     * 修改群成员
     */
    @Log(title = "群成员", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImGroupMemberBo bo) {
        return toAjax(imGroupMemberService.updateByBo(bo));
    }

    /**
     * 删除群成员
     *
     * @param ids 主键串
     */
    @Log(title = "群成员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imGroupMemberService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 查询群组所有成员
     *
     * @param groupId 群组ID
     */
    @GetMapping("/group/{groupId}/members")
    public R<List<ImGroupMemberVo>> getGroupMembers(@NotNull(message = "群组ID不能为空")
                                                   @PathVariable Long groupId) {
        return R.ok(imGroupMemberService.queryMembersByGroupId(groupId));
    }

    /**
     * 分页查询群组成员
     *
     * @param groupId 群组ID
     * @param pageQuery 分页参数
     */
    @GetMapping("/group/{groupId}/members/page")
    public TableDataInfo<ImGroupMemberVo> getGroupMembersPage(@NotNull(message = "群组ID不能为空")
                                                             @PathVariable Long groupId,
                                                             PageQuery pageQuery) {
        return imGroupMemberService.queryMembersByGroupIdPage(groupId, pageQuery);
    }

    /**
     * 移除群组成员
     *
     * @param groupId 群组ID
     * @param userId 用户ID
     */
    @DeleteMapping("/group/{groupId}/member/{userId}")
    public R<Void> removeGroupMember(@NotNull(message = "群组ID不能为空")
                                    @PathVariable Long groupId,
                                    @NotNull(message = "用户ID不能为空")
                                    @PathVariable Long userId) {
        return toAjax(imGroupMemberService.removeGroupMember(groupId, userId));
    }
}
