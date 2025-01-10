package com.imai.gateway.utils;

import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;

/**
 * @author wei
 * @date 2024/11/20 11:51
 */
@Data
public class AccessLog {

    /**用户编号**/
    private Long userId;

    /**路由**/
    private String targetServer;

    /**协议**/
    private String schema;

    /**请求方法名**/
    private String requestMethod;

    /**访问地址**/
    private String requestUrl;

    /**请求IP**/
    private String clientIp;

    /**查询参数**/
    private MultiValueMap<String, String> queryParams;

    /**请求体**/
    private String requestBody;

    /**请求头**/
    private MultiValueMap<String, String> requestHeaders;

    /**响应体**/
    private String responseBody;

    /**响应头**/
    private MultiValueMap<String, String> responseHeaders;

    /**响应结果**/
    private HttpStatusCode httpStatusCode;

    /**开始请求时间**/
    private LocalDateTime startTime;

    /**结束请求时间**/
    private LocalDateTime endTime;

    /**执行时长，单位：毫秒**/
    private Integer duration;

}
