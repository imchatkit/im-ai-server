package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImMsgRecall;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.bo.ImMsgRecallBo;
import com.imai.core.domain.vo.ImConversationMemberVo;
import com.imai.core.domain.vo.ImMessageVo;
import com.imai.core.domain.vo.ImMsgRecallVo;
import com.imai.core.mapper.ImMsgRecallMapper;
import com.imai.core.service.IImConversationMemberService;
import com.imai.core.service.IImMessageService;
import com.imai.core.service.IImMsgRecallService;
import com.imai.handler.store.ImStoreHandler;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.CmdType;
import com.imai.ws.enums.MessageDirection;
import com.imai.ws.enums.MsgType;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息撤回记录Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
public class ImMsgRecallServiceImpl implements IImMsgRecallService {

    private final ImMsgRecallMapper baseMapper;
    private final IImMessageService messageService;
    private final ImStoreHandler imStoreHandler;
    private final IImConversationMemberService conversationMemberService;

    /**
     * 撤回消息
     *
     * @param msgId  消息ID
     * @param userId 撤回用户ID
     * @return 是否撤回成功
     */
    @Override
    public Boolean recallMessage(Long msgId, Long userId) {
        // 1. 查询消息
        ImMessageVo message = messageService.queryById(msgId);
        if (message == null) {
            return false;
        }

        // 2. 验证权限（只能撤回自己发送的消息）
        if (!userId.equals(message.getFkFromUserId())) {
            return false;
        }

        // 3. 创建撤回记录
        ImMsgRecallBo recallBo = new ImMsgRecallBo();
        recallBo.setFkMsgId(msgId);
        recallBo.setFkUserId(userId);
        recallBo.setRecallTime(new Date());
        if (!insertByBo(recallBo)) {
            return false;
        }

        // 4. 更新消息状态
        ImMessageBo messageBo = new ImMessageBo();
        messageBo.setId(msgId);
        messageBo.setMsgStatus(2L); // 设置消息状态为已撤回
        if (!messageService.updateByBo(messageBo)) {
            return false;
        }

        // 5. 创建撤回系统消息
        ImMessageBo systemMessage = new ImMessageBo();
        systemMessage.setFkFromUserId(userId);
        systemMessage.setMsgType((long) MsgType.MSG_RECALL.getCode());
        systemMessage.setFkConversationId(message.getFkConversationId());

        // 6. 构建撤回通知
        WebSocketMessage recallNotification = new WebSocketMessage();
        recallNotification.setCmd(CmdType.MSG_RECALL.getCode());
        recallNotification.setDirection(MessageDirection.PUSH.getCode());
        recallNotification.getMessageExtra().setMessageId(msgId);
        recallNotification.getMessageExtra().setTimestamp(new Date().getTime());

        // 7. 获取会话所有成员
        List<ImConversationMemberVo> members = conversationMemberService.queryListByConversationIdForApi(message.getFkConversationId());
        List<Long> memberIds = members.stream()
            .map(ImConversationMemberVo::getFkUserId)
            .collect(Collectors.toList());

        // 8. 通过store机制处理撤回消息，发送给所有会话成员
        return imStoreHandler.handleSystemMessage(systemMessage, recallNotification, memberIds);
    }

    /**
     * 查询消息撤回记录
     *
     * @param id 主键
     * @return 消息撤回记录
     */
    @Override
    public ImMsgRecallVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息撤回记录列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息撤回记录分页列表
     */
    @Override
    public TableDataInfo<ImMsgRecallVo> queryPageList(ImMsgRecallBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImMsgRecall> lqw = buildQueryWrapper(bo);
        Page<ImMsgRecallVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息撤回记录列表
     *
     * @param bo 查询条件
     * @return 消息撤回记录列表
     */
    @Override
    public List<ImMsgRecallVo> queryList(ImMsgRecallBo bo) {
        LambdaQueryWrapper<ImMsgRecall> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImMsgRecall> buildQueryWrapper(ImMsgRecallBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImMsgRecall> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFkMsgId() != null, ImMsgRecall::getFkMsgId, bo.getFkMsgId());
        lqw.eq(bo.getFkUserId() != null, ImMsgRecall::getFkUserId, bo.getFkUserId());
        lqw.eq(bo.getRecallTime() != null, ImMsgRecall::getRecallTime, bo.getRecallTime());
        lqw.eq(bo.getDeleted() != null, ImMsgRecall::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImMsgRecall::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增消息撤回记录
     *
     * @param bo 消息撤回记录
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImMsgRecallBo bo) {
        ImMsgRecall add = MapstructUtils.convert(bo, ImMsgRecall.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息撤回记录
     *
     * @param bo 消息撤回记录
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImMsgRecallBo bo) {
        ImMsgRecall update = MapstructUtils.convert(bo, ImMsgRecall.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImMsgRecall entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息撤回记录信息
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
}
