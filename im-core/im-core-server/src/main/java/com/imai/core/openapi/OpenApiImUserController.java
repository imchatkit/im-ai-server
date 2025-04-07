package com.imai.core.openapi;

import com.imai.core.openapi.bo.OpenApiImUseLoginBo;
import com.imai.core.openapi.bo.OpenApiImUseRegisterBo;
import com.imai.core.openapi.vo.OpenApiImUserVo;
import com.imai.core.service.IImUserService;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 开放接口/用户
 * 前端访问路由地址为: /openapi/v1/user
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
     * IM客户端注册
     */
    @PostMapping("/register")
    @RepeatSubmit
    @Log(title = "IM客户端注册", businessType = BusinessType.INSERT)
    public R<OpenApiImUserVo> register(@RequestHeader("X-App-Key") String appKey, @RequestBody @Validated OpenApiImUseRegisterBo bo) {
        return R.ok(imUserService.register(bo));
    }

    /**
     * IM客户端登录
     */
    @PostMapping("/login")
    @RepeatSubmit
    @Log(title = "IM客户端userId登录", businessType = BusinessType.INSERT)
    public R<OpenApiImUserVo> login(@RequestHeader("X-App-Key") String appKey, @RequestBody @Validated OpenApiImUseLoginBo bo) {
        return R.ok(imUserService.login(bo));
    }

    // /**
    //  * IM客户端用户登出
    //  *
    //  */
    // @PostMapping("/logout")
    // public R<OpenApiImUserVo> logout(@RequestBody @Validated OpenApiImUseRegisterBo bo) {
    //     return R.ok(imUserService.login(bo));
    // }
}
