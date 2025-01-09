package com.imai.core.openapi;

import com.imai.core.domain.vo.ImUserVo;
import com.imai.core.service.IImUserService;
import com.imai.core.openapi.bo.ImUseRegisterBo;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户
 * 前端访问路由地址为:/imai/user
 *
 * @author wei
 * @date 2025-01-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/openapi/v1/user")
public class OpenApiImUserController extends BaseController {

    private final IImUserService imUserService;


    /**
     * 用户登录注册
     */
    @PostMapping("/login")
    @RepeatSubmit
    public R<ImUserVo> login(@RequestBody @Validated ImUseRegisterBo bo) {
        return R.ok(imUserService.login(bo));
    }

}
