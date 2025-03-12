package com.imai.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imai.core.domain.ImConversation;
import com.imai.core.domain.ImConversationMember;
import com.imai.core.domain.bo.ImConversationBo;
import com.imai.core.domain.bo.ImConversationMemberBo;
import com.imai.core.domain.bo.ImGroupBo;
import com.imai.core.domain.bo.ImGroupConversationBo;
import com.imai.core.domain.bo.ImGroupMemberBo;
import com.imai.core.domain.bo.ImMessageBo;
import com.imai.core.domain.vo.ImConversationMemberVo;
import com.imai.core.domain.vo.ImConversationVo;
import com.imai.core.mapper.ImConversationMapper;
import com.imai.core.service.IImConversationMemberService;
import com.imai.core.service.IImConversationService;
import com.imai.core.service.IImGroupMemberService;
import com.imai.core.service.IImGroupService;
import com.imai.ws.Content;
import com.imai.ws.MessageExtra;
import com.imai.ws.Route;
import com.imai.ws.WebSocketMessage;
import com.imai.ws.enums.CmdType;
import com.imai.ws.enums.ConversationType;
import com.imai.ws.enums.MessageDirection;
import com.imai.ws.enums.MsgType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 聊天会话基础Service业务层处理
 *
 * @author wei
 * @date 2025-01-07
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ImConversationServiceImpl implements IImConversationService {

    private final ImConversationMapper baseMapper;
    private final IImConversationMemberService conversationMemberService;
    private final IImGroupService groupService;
    private final IImGroupMemberService groupMemberService;
    private final com.imai.handler.store.ImStoreHandlerImpl imStoreHandler;


    /**
     * 查询聊天会话基础
     *
     * @param id 主键
     * @return 聊天会话基础
     */
    @Override
    public ImConversationVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询聊天会话基础列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 聊天会话基础分页列表
     */
    @Override
    public TableDataInfo<ImConversationVo> queryPageList(ImConversationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImConversation> lqw = buildQueryWrapper(bo);
        Page<ImConversationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的聊天会话基础列表
     *
     * @param bo 查询条件
     * @return 聊天会话基础列表
     */
    @Override
    public List<ImConversationVo> queryList(ImConversationBo bo) {
        LambdaQueryWrapper<ImConversation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImConversation> buildQueryWrapper(ImConversationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImConversation> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAvatar()), ImConversation::getAvatar, bo.getAvatar());
        lqw.eq(bo.getConversationType() != null, ImConversation::getConversationType, bo.getConversationType());
        lqw.eq(bo.getConversationStatus() != null, ImConversation::getConversationStatus, bo.getConversationStatus());
        lqw.eq(bo.getDeleted() != null, ImConversation::getDeleted, bo.getDeleted());
        lqw.eq(StringUtils.isNotBlank(bo.getExtras()), ImConversation::getExtras, bo.getExtras());
        return lqw;
    }

    /**
     * 新增聊天会话基础
     *
     * @param bo 聊天会话基础
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImConversationBo bo) {
        ImConversation add = MapstructUtils.convert(bo, ImConversation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            if (add != null) {
                bo.setId(add.getId());
            }
            bo.setCreateTime(add.getCreateTime());
        }
        return flag;
    }

    /**
     * 修改聊天会话基础
     *
     * @param bo 聊天会话基础
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImConversationBo bo) {
        ImConversation update = MapstructUtils.convert(bo, ImConversation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImConversation entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除聊天会话基础信息
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
     * 创建陌生人会话
     *
     * @param bo           会话信息
     * @param targetUserId 目标用户ID
     * @return 是否创建成功
     */
    @Override
    public Boolean createStrangerConversation(ImConversationBo bo, Long targetUserId) {
        // 1. 创建会话
        ImConversation conversation = MapstructUtils.convert(bo, ImConversation.class);
        validEntityBeforeSave(conversation);
        boolean success = baseMapper.insert(conversation) > 0;
        if (!success) {
            return false;
        }

        // 2. 添加当前用户为会话成员
        ImConversationMember currentMember = new ImConversationMember();
        if (conversation != null) {
            currentMember.setFkConversationId(conversation.getId());
        }
        currentMember.setFkUserId(LoginHelper.getUserId());
        // currentMember.setExtras("{}");
        success = conversationMemberService.insertByBo(MapstructUtils.convert(currentMember, ImConversationMemberBo.class));
        if (!success) {
            return false;
        }

        // 3. 添加目标用户为会话成员
        ImConversationMember targetMember = new ImConversationMember();
        targetMember.setFkConversationId(conversation.getId());
        targetMember.setFkUserId(targetUserId);
        // targetMember.setExtras("{}");
        success = conversationMemberService.insertByBo(MapstructUtils.convert(targetMember, ImConversationMemberBo.class));

        // 4. 设置返回的会话ID
        if (success) {
            bo.setId(conversation.getId());
            bo.setCreateTime(conversation.getCreateTime());
        }

        return success;
    }

    /**
     * 查询当前用户加入的会话列表
     *
     * @param pageQuery 分页参数
     * @return 会话分页列表
     */
    @Override
    public TableDataInfo<ImConversationVo> queryJoinedConversations(PageQuery pageQuery) {
        // 1. 获取当前用户ID
        Long currentUserId = LoginHelper.getUserId();

        // 2. 构建会话成员查询条件
        ImConversationMemberBo memberBo = new ImConversationMemberBo();
        memberBo.setFkUserId(currentUserId);
        memberBo.setDeleted(0L); // 未删除的会话成员

        // 3. 查询当前用户的会话成员记录
        List<ImConversationMemberVo> memberVos = conversationMemberService.queryList(memberBo);

        // 4. 提取会话ID列表
        List<Long> conversationIds = memberVos.stream()
            .map(ImConversationMemberVo::getFkConversationId)
            .collect(Collectors.toList());

        // 5. 如果没有加入任何会话，返回空结果
        if (conversationIds.isEmpty()) {
            return TableDataInfo.build(new Page<>());
        }

        // 6. 构建会话查询条件
        LambdaQueryWrapper<ImConversation> lqw = Wrappers.lambdaQuery();
        lqw.in(ImConversation::getId, conversationIds);
        lqw.eq(ImConversation::getDeleted, 0L); // 未删除的会话
        lqw.orderByDesc(ImConversation::getCreateTime); // 按创建时间倒序

        // 7. 查询会话详情并返回分页结果
        Page<ImConversationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 分页查询当前用户加入的会话列表（通过会话成员分页）
     *
     * @param pageQuery 分页参数
     * @return 会话分页列表
     */
    @Override
    public TableDataInfo<ImConversationVo> queryJoinedConversationsByMemberPage(PageQuery pageQuery) {
        // 1. 获取当前用户ID
        Long currentUserId = LoginHelper.getUserId();

        // 2. 构建会话成员查询条件
        ImConversationMemberBo memberBo = new ImConversationMemberBo();
        memberBo.setFkUserId(currentUserId);
        memberBo.setDeleted(0L); // 未删除的会话成员

        // 3. 分页查询当前用户的会话成员记录
        TableDataInfo<ImConversationMemberVo> memberPage = conversationMemberService.queryPageList(memberBo, pageQuery);

        // 4. 如果没有数据，返回空结果
        if (memberPage.getTotal() == 0) {
            return TableDataInfo.build(new Page<>());
        }

        // 5. 提取会话ID列表
        List<Long> conversationIds = memberPage.getRows().stream()
            .map(ImConversationMemberVo::getFkConversationId)
            .collect(Collectors.toList());

        // 6. 构建会话查询条件
        LambdaQueryWrapper<ImConversation> lqw = Wrappers.lambdaQuery();
        lqw.in(ImConversation::getId, conversationIds);
        lqw.eq(ImConversation::getDeleted, 0L); // 未删除的会话
        lqw.orderByDesc(ImConversation::getCreateTime); // 按创建时间倒序

        // 7. 查询会话详情
        List<ImConversationVo> conversations = baseMapper.selectVoList(lqw);

        // 8. 构建分页结果
        Page<ImConversationVo> result = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), memberPage.getTotal());
        result.setRecords(conversations);

        return TableDataInfo.build(result);
    }

    @Override
    @Transactional
    public ImGroupBo createGroupConversation(ImGroupConversationBo bo, Long userId) {

        // 创建会话
        ImConversationBo conversationBo = new ImConversationBo();
        conversationBo.setConversationType((long) ConversationType.GROUP.getCode());

        conversationBo.setExtras(bo.getExtras());
        boolean success = insertByBo(conversationBo);
        if (!success) {
            return null;
        }

        // 创建群组
        ImGroupBo groupBo = new ImGroupBo();
        groupBo.setId(conversationBo.getId()); // 设置群组ID与会话ID一致
        groupBo.setFkConversationId(conversationBo.getId());
        groupBo.setOwnerId(userId);
        groupBo.setGroupType(1L);
        groupBo.setName(bo.getName() == null ? "群聊" : bo.getName());
        groupBo.setJoinType(Long.valueOf(bo.getJoinType()));
        groupBo.setMaxMemberCount(1000L); // 设置默认最大成员数为1000
        groupBo.setExtras(bo.getExtras());
        success = groupService.insertByBo(groupBo);
        if (!success) {
            throw new RuntimeException("创建群组失败");
        }

        // 添加群组成员
        // 确保创建者在成员列表中
        List<Long> allMemberIds = bo.getMemberIds();
        if (!allMemberIds.contains(userId)) {
            allMemberIds.add(userId);
        }

        List<ImGroupMemberBo> groupMembers = allMemberIds.stream().map(memberId -> {
            ImGroupMemberBo memberBo = new ImGroupMemberBo();
            memberBo.setFkConversationId(conversationBo.getId());
            memberBo.setFkGroupId(groupBo.getId());
            memberBo.setFkUserId(memberId);
            memberBo.setRole(memberId.equals(userId) ? 1L : 0); // 1: 群主, 0: 普通成员
            memberBo.setGroupMemberJoinType(1L);
            memberBo.setMemberInvitedJoinUser(userId);

            return memberBo;
        }).toList();

        for (ImGroupMemberBo memberBo : groupMembers) {
            // 先添加会话成员
            ImConversationMemberBo conversationMemberBo = new ImConversationMemberBo();
            conversationMemberBo.setFkConversationId(conversationBo.getId());
            conversationMemberBo.setFkUserId(memberBo.getFkUserId());
            success = conversationMemberService.insertByBo(conversationMemberBo);
            if (!success) {
                throw new RuntimeException("添加会话成员失败");
            }

            // 设置群组成员的会话成员ID
            memberBo.setFkConversationMemberId(conversationMemberBo.getId());
            memberBo.setId(conversationMemberBo.getId());
            success = groupMemberService.insertByBo(memberBo);
            if (!success) {
                throw new RuntimeException("添加群组成员失败");
            }
        }

        //创建成功发送系统消息
        sendNotations(userId, conversationBo, groupBo, allMemberIds);

        return groupBo;
    }

    private void sendNotations(Long userId, ImConversationBo conversationBo, ImGroupBo groupBo, List<Long> allMemberIds) {
        // 发送群创建成功的系统消息
        try {
            // 构造系统消息
            ImMessageBo systemMessageBo = new ImMessageBo();
            systemMessageBo.setFkConversationId(conversationBo.getId());
            systemMessageBo.setFkFromUserId(userId); // 创建者ID作为消息发送者
            systemMessageBo.setConversationSeq(1L); // 第一条消息
            systemMessageBo.setLocalMsgId("system_" + System.currentTimeMillis());
            systemMessageBo.setMsgType((long) MsgType.GROUP_CREATED.getCode()); // 群组创建通知
            systemMessageBo.setMsgText( MsgType.GROUP_CREATED.getDescChinese());
            // systemMessageBo.setPayload("{}");
            // systemMessageBo.setMediaUrl("");
            // systemMessageBo.setAtUsers("");
            systemMessageBo.setMsgStatus(1L); // 正常状态
            systemMessageBo.setReceiverOnly("");
            systemMessageBo.setReceiverCount((long) allMemberIds.size());
            systemMessageBo.setRefCount(0L);
            systemMessageBo.setRefType(0L); // 原创消息
            systemMessageBo.setRootMsgId(0L);
            systemMessageBo.setParentMsgId(0L);
            systemMessageBo.setDeleted(0L);
            systemMessageBo.setAtAll(0L);
            // systemMessageBo.setExtras(String.format("{\"groupName\":\"%s\",\"creatorId\":%d}", groupBo.getName(), userId));
            systemMessageBo.setConversationType((long) ConversationType.GROUP.getCode());
            systemMessageBo.setToUid(0L); // 群聊不需要指定接收者ID
            systemMessageBo.setCmd((long) CmdType.GROUP_CREATE.getCode()); // 群创建命令
            systemMessageBo.setPersistent(1L); // 持久化消息
            systemMessageBo.setPriority(1L); // 普通优先级
            systemMessageBo.setNeedReceipt(0L); // 不需要回执

            // 构造WebSocket消息
            WebSocketMessage webSocketMessage = new WebSocketMessage();
            webSocketMessage.setDirection(MessageDirection.PUSH.getCode()); // 推送消息
            webSocketMessage.setCmd(CmdType.GROUP_CREATE.getCode()); // 群创建命令

            // 设置路由信息
            Route route = new Route();
            route.setType(MsgType.GROUP_CREATED.getCode()); // 系统通知类型
            route.setConversationId(conversationBo.getId());
            // route.setTarget(allMemberIds); // 所有群成员
            route.setSource("server"); // 服务端消息
            webSocketMessage.setRoute(route);

            // 设置消息内容
            Content content = new Content();
            content.setText(MsgType.GROUP_CREATED.getDescChinese());
            // content.setExtension(String.format("{\"groupName\":\"%s\",\"creatorId\":%d}", groupBo.getName(), userId));
            webSocketMessage.setContent(content);

            // 发送系统消息
            imStoreHandler.handleSystemMessage(systemMessageBo, webSocketMessage, allMemberIds);
        } catch (Exception e) {
            // 系统消息发送失败不影响群创建结果
            log.error("发送群创建系统消息失败", e);
        }
    }

}
