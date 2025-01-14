package com.imai.core.openapi.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.imai.core.domain.ImUser;
import com.imai.core.domain.vo.ImUserVo;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户视图对象 im_user
 *
 * @author wei
 * @date 2025-01-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImUser.class)
public class OpenApiImUserVo implements Serializable {

    private ImUserVo imUserVo;

    private String token;
}
