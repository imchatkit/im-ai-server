package com.imai.ai;

import org.springframework.stereotype.Service;

/**
 * @author wei
 * @date 2025/3/3 15:58
 */
@Service
public class TipsServiceImpl implements TipsService {
    @Override
    public void getTips(Long conversationId) {


        System.out.println("获取提示");
    }
}
