package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgRead;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.bo.ImMsgReadBo;
import com.imai.core.domain.vo.ImMsgReadVo;
import com.imai.core.mapper.ImMsgReadMapper;
import com.imai.core.service.IImConversationRecentService;
import com.imai.core.service.IImMsgReadService;
import com.imai.handler.store.ImStoreHandler;
import com.imai.ws.Header;
import com.imai.ws.Route;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.RequestCmdEnum;
import com.imai.ws.enums.MessageDirectionEnum;
import com.imai.ws.enums.MsgTypeEnum;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息已读记录Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@Service
public class ImMsgReadServiceImpl implements IImMsgReadService {

    @jakarta.annotation.Resource
    private ImMsgReadMapper baseMapper;
    @Lazy
    @jakarta.annotation.Resource
    private IImConversationRecentService conversationRecentService;
    @Lazy
    @jakarta.annotation.Resource
    private ImStoreHandler imStoreHandler;

    /**
     * 更新消息已读状态
     *
     * @param msgId          消息ID
     * @param conversationId 会话ID
     * @param receiverId     接收者ID
     * @return 是否更新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateMessageRead(Long msgId, Long conversationId, Long receiverId) {
        // 1. 更新消息已读状态
        LambdaQueryWrapper<ImMsgRead> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ImMsgRead::getFkMsgId, msgId)
            .eq(ImMsgRead::getFkConversationId, conversationId)
            .eq(ImMsgRead::getFkReceiverUserId, receiverId)
            .eq(ImMsgRead::getReadMsgStatus, 0L);

        ImMsgRead update = new ImMsgRead();
        update.setReadMsgStatus(1L);
        update.setReadTime(new Date());
        boolean result = baseMapper.update(update, wrapper) > 0;

        if (result) {
            // 2. 更新会话未读消息计数
            conversationRecentService.updateConversationRead(conversationId, receiverId);

            // 3. 发送WebSocket消息通知
            ImMsgRead msgRead = baseMapper.selectOne(wrapper);
            if (msgRead != null) {
                // 构建已读回执消息
                WebSocketMessage readNotification = WebSocketMessage.builder()
                    .direction(MessageDirectionEnum.PUSH.getCode())
                    .cmd(RequestCmdEnum.NOTIFY.getCode())
                    .header(Header.builder()
                        .localId(String.valueOf(msgId))
                        .timestamp(System.currentTimeMillis())
                        .build())
                    .route(Route.builder()
                        .type(MsgTypeEnum.MSG_READ.getCode())
                        .conversationId(conversationId)
                        .target(Collections.singletonList(msgRead.getFkFromUserId()))
                        .source("server")
                        .build())
                    .build();

                // 创建系统消息并通过handleSystemMessage下发
                ImMessageBo systemMessage = new ImMessageBo();
                systemMessage.setFkFromUserId(receiverId);
                systemMessage.setMsgType((long) MsgTypeEnum.MSG_READ.getCode());

                List<Long> targetUsers = new ArrayList<>();
                targetUsers.add(msgRead.getFkFromUserId());
                targetUsers.add(msgRead.getFkFromUserId());

                // 通知消息发送方和用户其他在线终端
                imStoreHandler.handleSystemMessage(systemMessage, readNotification, targetUsers);
            }

        }

        return result;
    }

    /**
     * 查询消息已读记录
     *
     * @param id 主键
     * @return 消息已读记录
     */
    @Override
    public ImMsgReadVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息已读记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息已读记录分页列表
     */
    @Override
    public TableDataInfo<ImMsgReadVo> queryPageList(ImMsgReadBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgRead> lqw = buildQueryWrapper(bo);
        Page<ImMsgReadVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息已读记录列表
     *
     * @param bo 查询条件
     * @return 消息已读记录列表
     */
    @Override
    public List<ImMsgReadVo> queryList(ImMsgReadBo bo) {
        LambdaQueryWrapper<ImMsgRead> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgRead> buildQueryWrapper(ImMsgReadBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgRead> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgRead::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getFkConversationId() != null, ImMsgRead::getFkConversationId, bo.getFkConversationId());
        lqw.eq(bo.getFkReceiverUserId() != null, ImMsgRead::getFkReceiverUserId, bo.getFkReceiverUserId());
        lqw.eq(bo.getFkFromUserId() != null, ImMsgRead::getFkFromUserId, bo.getFkFromUserId());
        lqw.eq(bo.getReadTime() != null, ImMsgRead::getReadTime, bo.getReadTime());
        lqw.eq(bo.getReceiverTime() != null, ImMsgRead::getReceiverTime, bo.getReceiverTime());
        lqw.eq(bo.getReadMsgStatus() != null, ImMsgRead::getReadMsgStatus, bo.getReadMsgStatus());
        lqw.eq(bo.getReceiverMsgStatus() != null, ImMsgRead::getReceiverMsgStatus, bo.getReceiverMsgStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgRead::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息已读记录
     *
     * @param bo 消息已读记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgReadBo bo) {
        ImMsgRead add = MapstructUtils.convert(bo, ImMsgRead.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息已读记录
     *
     * @param bo 消息已读记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgReadBo bo) {
        ImMsgRead update = MapstructUtils.convert(bo, ImMsgRead.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgRead entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息已读记录信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 批量创建消息已读记录
     *
     * @param msgId          消息ID
     * @param conversationId 会话ID
     * @param fromUserId     发送者ID
     * @param receiverIds    接收者ID列表
     * @return 是否创建成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchCreateMsgRead(Long msgId, Long conversationId, Long fromUserId, Collection<Long> receiverIds) {
        if (receiverIds == null || receiverIds.isEmpty()) {
            return true;
        }

        List<ImMsgRead> readList = new ArrayList<>();
        for (Long receiverId : receiverIds) {
            ImMsgRead msgRead = new ImMsgRead();
            msgRead.setFkMsgId(msgId);
            msgRead.setFkConversationId(conversationId);
            msgRead.setFkFromUserId(fromUserId);
            msgRead.setFkReceiverUserId(receiverId);
            msgRead.setReadMsgStatus(0L); // 0表示未读
            msgRead.setReceiverMsgStatus(0L); // 0表示未接收
            readList.add(msgRead);

            // 更新Redis缓存中的未读消息计数
            // String unreadKey = "msg:unread:" + conversationId + ":" + receiverId;
            // RedisUtils.incr(unreadKey);
        }

        return baseMapper.insertBatch(readList);
    }
}
