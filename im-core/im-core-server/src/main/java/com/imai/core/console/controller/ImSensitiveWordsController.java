package com.imai.core.console.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.imai.core.domain.bo.ImSensitiveWordsBo;
import com.imai.core.domain.vo.ImSensitiveWordsVo;
import com.imai.core.service.IImSensitiveWordsService;
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
 * 敏感词过滤
 * 前端访问路由地址为:/imai/sensitiveWords
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensitiveWords")
public class ImSensitiveWordsController extends BaseController {

    private final IImSensitiveWordsService imSensitiveWordsService;

    /**
     * 查询敏感词过滤列表
     */
    @SaCheckPermission("imcore:sensitiveWords:list")
    @GetMapping("/list")
    public TableDataInfo<ImSensitiveWordsVo> list(ImSensitiveWordsBo bo, PageQuery pageQuery) {
        return imSensitiveWordsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出敏感词过滤列表
     */
    @SaCheckPermission("imcore:sensitiveWords:export")
    @Log(title = "敏感词过滤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImSensitiveWordsBo bo, HttpServletResponse response) {
        List<ImSensitiveWordsVo> list = imSensitiveWordsService.queryList(bo);
        ExcelUtil.exportExcel(list, "敏感词过滤", ImSensitiveWordsVo.class, response);
    }

    /**
     * 获取敏感词过滤详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("imcore:sensitiveWords:query")
    @GetMapping("/{id}")
    public R<ImSensitiveWordsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imSensitiveWordsService.queryById(id));
    }

    /**
     * 新增敏感词过滤
     */
    @SaCheckPermission("imcore:sensitiveWords:add")
    @Log(title = "敏感词过滤", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImSensitiveWordsBo bo) {
        return toAjax(imSensitiveWordsService.insertByBo(bo));
    }

    /**
     * 修改敏感词过滤
     */
    @SaCheckPermission("imcore:sensitiveWords:edit")
    @Log(title = "敏感词过滤", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImSensitiveWordsBo bo) {
        return toAjax(imSensitiveWordsService.updateByBo(bo));
    }

    /**
     * 删除敏感词过滤
     *
     * @param ids 主键串
     */
    @SaCheckPermission("imcore:sensitiveWords:remove")
    @Log(title = "敏感词过滤", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imSensitiveWordsService.deleteWithValidByIds(List.of(ids), true));
    }
}
