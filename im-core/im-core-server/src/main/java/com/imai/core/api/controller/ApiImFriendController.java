package com.imai.core.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImFriendBo;
import com.imai.core.domain.vo.ImFriendVo;
import com.imai.core.service.IImFriendService;
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
 * 好友关系
 * 前端访问路由地址为:/imai/friend
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/friend")
public class ApiImFriendController extends BaseController {

    private final IImFriendService imFriendService;

    /**
     * 查询好友关系列表
     */
    @SaCheckPermission("imcore:friend:list")
    @GetMapping("/list")
    public TableDataInfo<ImFriendVo> list(ImFriendBo bo, PageQuery pageQuery) {
        return imFriendService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出好友关系列表
     */
    @SaCheckPermission("imcore:friend:export")
    @Log(title = "好友关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImFriendBo bo, HttpServletResponse response) {
        List<ImFriendVo> list = imFriendService.queryList(bo);
        ExcelUtil.exportExcel(list, "好友关系", ImFriendVo.class, response);
    }

    /**
     * 获取好友关系详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:friend:query")
    @GetMapping("/{id}")
    public R<ImFriendVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imFriendService.queryById(id));
    }

    /**
     * 新增好友关系
     */
    @SaCheckPermission("imcore:friend:add")
    @Log(title = "好友关系", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImFriendBo bo) {
        return toAjax(imFriendService.insertByBo(bo));
    }

    /**
     * 修改好友关系
     */
    @SaCheckPermission("imcore:friend:edit")
    @Log(title = "好友关系", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImFriendBo bo) {
        return toAjax(imFriendService.updateByBo(bo));
    }

    /**
     * 删除好友关系
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:friend:remove")
    @Log(title = "好友关系", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imFriendService.deleteWithValidByIds(List.of(ids), true));
    }
}
