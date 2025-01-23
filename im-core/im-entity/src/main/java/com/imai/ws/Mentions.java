package com.imai.ws;

import lombok.Data;
import java.util.List;

/**
 * @功能
 */
@Data
public class Mentions {
    private Boolean all;                // 是否@所有人
    private List<String> uids;          // @的用户列表
    private String pushContent;         // @推送内容
} 