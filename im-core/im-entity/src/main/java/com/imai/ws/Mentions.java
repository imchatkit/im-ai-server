package com.imai.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.Builder;
/**
 * @功能
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mentions {
    private Boolean all;                // 是否@所有人
    private List<String> uids;          // @的用户列表
    private String pushContent;         // @推送内容
}
