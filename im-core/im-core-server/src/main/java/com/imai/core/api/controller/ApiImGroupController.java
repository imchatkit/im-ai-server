package com.imai.core.api.controller;

import com.imai.core.domain.bo.ImGroupBo;
import com.imai.core.domain.bo.ImGroupConversationBo;
import com.imai.core.domain.vo.ImGroupVo;
import com.imai.core.service.IImConversationService;
import com.imai.core.service.IImGroupService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * IM应用/群组
 * 前端访问路由地址为:/api/v1/group
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/group")
public class ApiImGroupController extends BaseController {

    private final IImGroupService imGroupService;
    private final IImConversationService imConversationService;

    /**
     * 创建群组会话
     */
    @RepeatSubmit()
    @Log(title = "创建群组会话", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    public R<ImGroupBo> createGroup(@RequestBody @Validated ImGroupConversationBo bo) {
        Long userId = LoginHelper.getUserId();
        return R.ok(imConversationService.createGroupConversation(bo, userId));
    }

    /**
     * 查询群组列表
     */
    @GetMapping("/list")
    public TableDataInfo<ImGroupVo> list(ImGroupBo bo, PageQuery pageQuery) {
        return imGroupService.queryPageList(bo, pageQuery);
    }

    /**
     * 获取群组详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ImGroupVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(imGroupService.queryById(id));
    }

    /**
     * 新增群组
     */
    @Log(title = "群组", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImGroupBo bo) {
        return toAjax(imGroupService.insertByBo(bo));
    }

    /**
     * 修改群组
     */
    @Log(title = "群组", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImGroupBo bo) {
        return toAjax(imGroupService.updateByBo(bo));
    }

    /**
     * 查询我加入的群组列表
     */
    @GetMapping("/my/joined")
    public R<List<ImGroupVo>> queryMyJoinedGroups() {
        Long userId = LoginHelper.getUserId();
        return R.ok(imGroupService.queryMyJoinedGroups(userId));
    }

    /**
     * 查询我创建的群组列表
     */
    @GetMapping("/my/created")
    public R<List<ImGroupVo>> queryMyCreatedGroups() {
        Long userId = LoginHelper.getUserId();
        return R.ok(imGroupService.queryMyCreatedGroups(userId));
    }
}
