/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : im_chat_ai

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 11/12/2024 17:19:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for im_callback_record
-- ----------------------------
DROP TABLE IF EXISTS `im_callback_record`;
CREATE TABLE `im_callback_record`  (
  `id` bigint NOT NULL COMMENT '记录id',
  `fk_tenant_id` bigint NOT NULL COMMENT '租户id',
  `callback_type` tinyint NULL DEFAULT NULL COMMENT '回调类型',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回调地址',
  `request_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求内容',
  `response_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '响应内容',
  `callback_status` tinyint NULL DEFAULT NULL,
  `retry_count` int NULL DEFAULT 0 COMMENT '重试次数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_time`(`fk_tenant_id` ASC, `create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息回调记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_channel
-- ----------------------------
DROP TABLE IF EXISTS `im_channel`;
CREATE TABLE `im_channel`  (
  `id` bigint NOT NULL COMMENT '频道ID',
  `fk_workspace_id` bigint NOT NULL COMMENT '所属工作空间ID',
  `fk_conversation_id` bigint NOT NULL COMMENT '关联的会话ID',
  `channel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '频道名称',
  `channel_type` tinyint NOT NULL COMMENT '频道类型:1公开,2私密,3公告',
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '频道主题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '频道描述',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父频道ID,用于嵌套',
  `creator_user_id` bigint NOT NULL COMMENT '创建者ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序号',
  `archived` tinyint NULL DEFAULT 0 COMMENT '是否归档:0否,1是',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_workspace_name`(`fk_workspace_id` ASC, `channel_name` ASC) USING BTREE,
  INDEX `idx_workspace_parent`(`fk_workspace_id` ASC, `parent_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '频道表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_channel_member
-- ----------------------------
DROP TABLE IF EXISTS `im_channel_member`;
CREATE TABLE `im_channel_member`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `fk_channel_id` bigint NOT NULL COMMENT '频道ID',
  `fk_user_id` bigint NOT NULL COMMENT '用户ID',
  `member_role` tinyint NULL DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `notification_level` tinyint NULL DEFAULT 1 COMMENT '通知级别:0关闭,1提及时,2所有消息',
  `starred` tinyint NULL DEFAULT 0 COMMENT '是否星标:0否,1是',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_channel_user`(`fk_channel_id` ASC, `fk_user_id` ASC) USING BTREE,
  INDEX `idx_user_channel`(`fk_user_id` ASC, `fk_channel_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '频道成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_conversation
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation`;
CREATE TABLE `im_conversation`  (
  `id` bigint NOT NULL COMMENT 'Room ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `conversation_type` tinyint NOT NULL COMMENT '会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `conversation_status` tinyint NULL DEFAULT NULL COMMENT '状态',
  `ext` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '扩展字段',
  `fk_tenant_id` bigint NULL DEFAULT NULL COMMENT '租户id',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '删除标记 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_type`(`tenant_id` ASC, `conversation_type` ASC) USING BTREE,
  INDEX `idx_conversation_type_create`(`conversation_type` ASC, `create_time` ASC) USING BTREE,
  INDEX `idx_tenant`(`fk_tenant_id` ASC) USING BTREE,
  INDEX `idx_type_status_time`(`conversation_type` ASC, `conversation_status` ASC, `create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '聊天房间基础表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_conversation_member
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation_member`;
CREATE TABLE `im_conversation_member`  (
  `id` bigint NOT NULL COMMENT '唯一id',
  `fk_conversation_id` bigint NOT NULL COMMENT '会话ID',
  `fk_team_id` bigint NULL DEFAULT NULL COMMENT '团队表id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `fk_user_id` bigint NOT NULL COMMENT '用户id',
  `allow_system_push_status` int NULL DEFAULT 1 COMMENT '是否允许系统推送, 0不推送, 1推送',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `user_remark_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会话中的备注名',
  `role` tinyint NULL DEFAULT NULL COMMENT '角色 1-普通群成员 2-管理员 3-群主',
  `muted` tinyint NOT NULL DEFAULT 1 COMMENT '禁言开关 1-未禁言 2-禁言',
  `disturb_flag` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '免打扰开关 0-关闭 1开启',
  `top_flag` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '置顶开关 0-关闭 1开启',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  `mute_at_all` tinyint(1) NOT NULL DEFAULT '0' COMMENT '屏蔽@全体成员 0-不屏蔽 1-屏蔽',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `conversation_and_user`(`fk_conversation_id` ASC, `fk_user_id` ASC) USING BTREE,
  INDEX `conversation_id`(`fk_conversation_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '房间成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_conversation_recent
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation_recent`;
CREATE TABLE `im_conversation_recent`  (
  `id` bigint NOT NULL COMMENT '对话id',
  `fk_user_id` bigint NOT NULL COMMENT '创建者id',
  `fk_conversation_id` bigint NULL DEFAULT NULL COMMENT '房间id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_msg_id` bigint NULL DEFAULT NULL COMMENT '最后一条消息id',
  `last_msg_time` timestamp NULL DEFAULT NULL COMMENT '最后一条消息时间，精确到毫秒',
  `no_read_count` bigint NULL DEFAULT NULL COMMENT '未读消息数量',
  `top_flag` tinyint NULL DEFAULT 0 COMMENT '置顶标志 0不置顶  1置顶',
  `top_time` timestamp NULL DEFAULT NULL COMMENT '置顶时间,用于排序',
  `removed_flag` tinyint NULL DEFAULT 0 COMMENT '对话移除标志 0没移除  1移除',
  `removed_time` timestamp NULL DEFAULT NULL COMMENT '移除时间,用于判断是否展示',
  `at_me_flag` tinyint NULL DEFAULT 0 COMMENT '是否有at我的消息 0无,1有 ',
  `at_me_msg_id` bigint NULL DEFAULT NULL COMMENT '有at我的消息id',
  `conversation_type` tinyint NULL DEFAULT NULL COMMENT '会话类型:1单聊,2群聊,3系统通知',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_conversation`(`fk_user_id` ASC, `fk_conversation_id` ASC) USING BTREE COMMENT '用户会话唯一索引',
  INDEX `idx_user_msg_time`(`fk_user_id` ASC, `last_msg_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页对话列表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_conversation_seq
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation_seq`;
CREATE TABLE `im_conversation_seq`  (
  `conversation_id` bigint NOT NULL COMMENT '主键id',
  `conversation_seq` bigint NOT NULL,
  PRIMARY KEY (`conversation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '房间序列号表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_department
-- ----------------------------
DROP TABLE IF EXISTS `im_department`;
CREATE TABLE `im_department`  (
  `id` bigint NOT NULL COMMENT '部门ID',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父部门ID',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `dept_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门路径',
  `sort_order` int NULL DEFAULT 0 COMMENT '排序号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_device
-- ----------------------------
DROP TABLE IF EXISTS `im_device`;
CREATE TABLE `im_device`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `fk_user_id` bigint NOT NULL COMMENT '用户id',
  `valid` int NULL DEFAULT NULL COMMENT '设备不想收到推送提醒',
  `push_token` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '通知推送token',
  `unique_device_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备唯一编码(由设备端生成)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `push_channel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '推送通道',
  `platform` int NULL DEFAULT NULL COMMENT '客户端平台: 1web, 2Android, 3 ios, 4windows, 5mac',
  `device_status` int NULL DEFAULT NULL COMMENT '设备状态 0退出登录 1正常',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '客户端设备表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_device_pts
-- ----------------------------
DROP TABLE IF EXISTS `im_device_pts`;
CREATE TABLE `im_device_pts`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `fk_user_id` bigint NOT NULL COMMENT '用户id',
  `fk_device_id` bigint NOT NULL COMMENT '设备id',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `max_pts` bigint NULL DEFAULT NULL COMMENT '客户端当前最大位点',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`fk_user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备pts表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_error_log
-- ----------------------------
DROP TABLE IF EXISTS `im_error_log`;
CREATE TABLE `im_error_log`  (
  `id` bigint NOT NULL COMMENT '日志id',
  `fk_tenant_id` bigint NOT NULL COMMENT '租户id',
  `error_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误类型',
  `error_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误码',
  `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '错误信息',
  `stack_trace` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '堆栈信息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_time`(`fk_tenant_id` ASC, `create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '错误日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_friend
-- ----------------------------
DROP TABLE IF EXISTS `im_friend`;
CREATE TABLE `im_friend`  (
  `id` bigint NOT NULL COMMENT '关系ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `friend_id` bigint NOT NULL COMMENT '好友ID',
  `conversation_id` bigint NOT NULL COMMENT '单聊房间ID',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注名',
  `source` tinyint NULL DEFAULT NULL COMMENT '来源: 1-搜索 2-群聊 3-名片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 1-正常 2-禁用 3-删除',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_friend`(`user_id` ASC, `friend_id` ASC) USING BTREE,
  INDEX `idx_room`(`conversation_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '好友关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_friend_request
-- ----------------------------
DROP TABLE IF EXISTS `im_friend_request`;
CREATE TABLE `im_friend_request`  (
  `id` bigint NOT NULL COMMENT '申请ID',
  `from_user_id` bigint NOT NULL COMMENT '申请人ID',
  `to_user_id` bigint NOT NULL COMMENT '接收人ID',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '验证信息',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态: 0-待处理 1-同意 2-拒绝',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `handle_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_to_user`(`to_user_id` ASC, `status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '好���申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_group
-- ----------------------------
DROP TABLE IF EXISTS `im_group`;
CREATE TABLE `im_group`  (
  `id` bigint NOT NULL COMMENT '群组ID',
  `conversation_id` bigint NOT NULL COMMENT '关联的RoomID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '群名称',
  `owner_id` bigint NOT NULL COMMENT '群主ID',
  `group_type` tinyint NOT NULL COMMENT '群类型: 1-普通群 2-部门群 3-企业群',
  `max_member_count` int NOT NULL DEFAULT 500 COMMENT '最大成员数',
  `join_type` tinyint NULL DEFAULT 1 COMMENT '加群方式: 0-自由加入 1-需验证 2-禁止加入',
  `notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '群公告',
  `org_id` bigint NULL DEFAULT NULL COMMENT '关联组织ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '关联部门ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `ext` json NULL COMMENT '扩展字段',
  `fk_tenant_id` bigint NOT NULL COMMENT '租户id',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_room`(`conversation_id` ASC) USING BTREE,
  INDEX `idx_owner`(`owner_id` ASC) USING BTREE,
  INDEX `idx_group_type_create`(`group_type` ASC, `create_time` ASC) USING BTREE,
  INDEX `idx_tenant`(`fk_tenant_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群组扩展表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_group_announcement
-- ----------------------------
DROP TABLE IF EXISTS `im_group_announcement`;
CREATE TABLE `im_group_announcement`  (
  `id` bigint NOT NULL COMMENT '公告id',
  `fk_group_id` bigint NOT NULL COMMENT '群组id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公告内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到毫秒',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_group_member
-- ----------------------------
DROP TABLE IF EXISTS `im_group_member`;
CREATE TABLE `im_group_member`  (
  `id` bigint NOT NULL COMMENT '唯一id',
  `fk_conversation_id` bigint NOT NULL COMMENT '会话ID',
  `fk_group_id` bigint NOT NULL COMMENT '群组表id',
  `fk_team_id` bigint NULL DEFAULT NULL COMMENT '团队表id',
  `fk_room_member_id` bigint NOT NULL COMMENT '房间成员表id',
  `fk_user_id` bigint NOT NULL COMMENT '用户id',
  `member_invited_join_user` bigint NULL DEFAULT 1 COMMENT '邀请该成员进群的用户',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `user_group_remark_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '群聊中用户的备注名',
  `role` tinyint NOT NULL DEFAULT 1 COMMENT '角色 1-普通群成员 2-管理员 3-群主',
  `group_member_status` tinyint NULL DEFAULT 1 COMMENT '群组成员状态  0主动退群  1正常 2被移出群聊',
  `group_member_join_type` tinyint NULL DEFAULT 1 COMMENT '群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `group_and_user`(`fk_group_id` ASC, `fk_user_id` ASC) USING BTREE,
  INDEX `conversation_id`(`fk_conversation_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_group_setting
-- ----------------------------
DROP TABLE IF EXISTS `im_group_setting`;
CREATE TABLE `im_group_setting` (
  `fk_group_id` bigint NOT NULL COMMENT '群组ID',
  
  -- 基本设置
  `all_mute` tinyint(1) NOT NULL DEFAULT 0 COMMENT '全员禁言开关 0-关闭 1-开启',
  `member_invite` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成员邀请开关 0-关闭 1-开启',
  `member_modify` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成员修改群信息开关 0-关闭 1-开启', 
  `member_visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成员列表可见开关 0-关闭 1-开启',
  
  -- 功能限制
  `forbid_add_friend` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止群内加好友 0-关闭 1-开启',
  `forbid_send_redpacket` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止发红包 0-关闭 1-开启',
  `forbid_send_image` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止发图片 0-关闭 1-开启',
  `forbid_send_link` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止发链接 0-关闭 1-开启',
  
  -- 群状态
  `group_disbanded` tinyint(1) NOT NULL DEFAULT 0 COMMENT '群组是否已解散 0-否 1-是',

  PRIMARY KEY (`fk_group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群组设置表';

-- ----------------------------
-- Table structure for im_msg
-- ----------------------------
DROP TABLE IF EXISTS `im_msg`;
CREATE TABLE `im_msg`  (
  `id` bigint NOT NULL COMMENT '消息id',
  `fk_conversation_id` bigint NULL DEFAULT NULL COMMENT '会话id',
  `fk_from_user_id` bigint NULL DEFAULT NULL COMMENT '发送者id',
  `room_seq` bigint NULL DEFAULT NULL COMMENT '房间粒度单调自增序列号',
  `local_msg_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端本地消息id',
  `msg_type` int NULL DEFAULT NULL COMMENT '消息类型',
  `payload` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '载荷内容如图片视频卡片等不同的参数',
  `msg_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文字内容',
  `at_users` json NULL COMMENT '被@用户列表 格式:[{userId:1,name:"张三"},{userId:2,name:"李四"}]',
  `msg_status` int NULL DEFAULT 1 COMMENT '消息状态 1正常 2已撤回',
  `receiver_only` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '接收人,多人用英文逗号分隔-群内指定人员可见场景',
  `receiver_count` int NULL DEFAULT NULL COMMENT '接收方总人数',
  `read_count` int NULL DEFAULT NULL COMMENT '已读总人数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到毫秒',
  `ref_count` int NULL DEFAULT 0 COMMENT '被引用次数',
  `ref_type` tinyint NULL DEFAULT NULL COMMENT '引用类型:0原创,1回复,2转发,3引用',
  `root_msg_id` bigint NULL DEFAULT NULL COMMENT '会话根消息ID(第一条被引用的消息)',
  `parent_msg_id` bigint NULL DEFAULT NULL COMMENT '直接引用的消息ID',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  `at_all` tinyint(1) NOT NULL DEFAULT '0' COMMENT '@全体成员标记 0-否 1-是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roomid`(`fk_conversation_id` ASC) USING BTREE,
  INDEX `idx_create_time`(`create_time` ASC) USING BTREE,
  INDEX `idx_msg_type_create`(`msg_type` ASC, `create_time` ASC) USING BTREE,
  INDEX `idx_conversation_time`(`fk_conversation_id` ASC, `create_time` ASC) USING BTREE,
  INDEX `idx_at_all` (`fk_conversation_id`, `at_all`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息存储表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_msg_read
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_read`;
CREATE TABLE `im_msg_read`  (
  `id` bigint NOT NULL COMMENT '收件id',
  `fk_msg_id` bigint NOT NULL COMMENT '消息id',
  `fk_conversation_id` bigint NOT NULL COMMENT '会话id',
  `fk_receiver_user_id` bigint NOT NULL COMMENT '接收客户端id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '读取时间',
  `receiver_time` timestamp NULL DEFAULT NULL COMMENT '接收时间',
  `read_msg_status` tinyint NULL DEFAULT 0 COMMENT '0未读; 1已读',
  `receiver_msg_status` tinyint NULL DEFAULT 0 COMMENT '0未接收; 1已接收',
  `fk_from_user_id` bigint NULL DEFAULT NULL COMMENT '发送者id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `msg_id`(`fk_msg_id` ASC) USING BTREE,
  INDEX `room_user`(`fk_conversation_id` ASC, `fk_receiver_user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息已读记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_msg_recall
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_recall`;
CREATE TABLE `im_msg_recall`  (
  `id` bigint NOT NULL COMMENT '撤回记录id',
  `fk_msg_id` bigint NOT NULL COMMENT '消息id',
  `fk_user_id` bigint NOT NULL COMMENT '撤回用户id',
  `recall_time` timestamp NULL DEFAULT NULL COMMENT '撤回时间，精确到毫秒',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息撤回记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_msg_receiver
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_receiver`;
CREATE TABLE `im_msg_receiver`  (
  `id` bigint NOT NULL,
  `msg_id` bigint NOT NULL COMMENT '消息id',
  `receiver_id` bigint NOT NULL COMMENT '接收者id',
  `receive_time` timestamp NULL DEFAULT NULL COMMENT '接收时间',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '已读时间',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态:0未读,1已读,2删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_msg_receiver`(`msg_id` ASC, `receiver_id` ASC) USING BTREE,
  INDEX `idx_receiver_time`(`receiver_id` ASC, `receive_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息接收表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_msg_reference
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_reference`;
CREATE TABLE `im_msg_reference`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `fk_msg_id` bigint NOT NULL COMMENT '当前消息ID',
  `fk_ref_msg_id` bigint NOT NULL COMMENT '被引用的消息ID',
  `ref_type` tinyint NOT NULL COMMENT '引用类型:1回复,2转发,3引用',
  `ref_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '引用时添加的评论文本',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_msg_id`(`fk_msg_id` ASC) USING BTREE,
  INDEX `idx_ref_msg_id`(`fk_ref_msg_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息引用关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_msg_reference_path
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_reference_path`;
CREATE TABLE `im_msg_reference_path`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `fk_msg_id` bigint NOT NULL COMMENT '消息ID',
  `ancestor_msg_id` bigint NOT NULL COMMENT '祖先消息ID',
  `distance` int NOT NULL COMMENT '引用深度(层级距离)',
  `path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '引用路径(格式:id1->id2->id3)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_msg_ancestor`(`fk_msg_id` ASC, `ancestor_msg_id` ASC) USING BTREE,
  INDEX `idx_ancestor_msg`(`ancestor_msg_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息引用路径表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_sensitive_words
-- ----------------------------
DROP TABLE IF EXISTS `im_sensitive_words`;
CREATE TABLE `im_sensitive_words`  (
  `id` bigint NOT NULL COMMENT '敏感词id',
  `word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '敏感词',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到毫秒',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '敏感词过滤表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_sync
-- ----------------------------
DROP TABLE IF EXISTS `im_sync`;
CREATE TABLE `im_sync`  (
  `id` bigint NOT NULL COMMENT '同步id',
  `fk_user_id` bigint NOT NULL COMMENT '用户id',
  `pts` bigint NULL DEFAULT NULL COMMENT '用户维度单调递增的PTS位点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_msg_id` bigint NULL DEFAULT NULL COMMENT '消息id',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_pts`(`fk_user_id` ASC, `pts` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '多端同步表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_sys_conversation_init
-- ----------------------------
DROP TABLE IF EXISTS `im_sys_conversation_init`;
CREATE TABLE `im_sys_conversation_init`  (
  `id` bigint NOT NULL COMMENT 'id',
  `fk_team_id` bigint NOT NULL COMMENT '所属团队id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `conversation_type` int NOT NULL COMMENT '会话类型',
  `conversation_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会话名称',
  `extras` json NULL COMMENT '可选	自定义属性，供开发者扩展使用。',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '头像',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统房间初始化表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_team
-- ----------------------------
DROP TABLE IF EXISTS `im_team`;
CREATE TABLE `im_team`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `fk_user_id` bigint NULL DEFAULT NULL COMMENT '创建者id',
  `team_type` int NULL DEFAULT NULL COMMENT '团队类型 1公司 2学校 3个人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '团队公司组织表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_team_member
-- ----------------------------
DROP TABLE IF EXISTS `im_team_member`;
CREATE TABLE `im_team_member`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `fk_user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '团队内用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_team_organization
-- ----------------------------
DROP TABLE IF EXISTS `im_team_organization`;
CREATE TABLE `im_team_organization`  (
  `id` bigint NOT NULL COMMENT '主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '团队组织架构表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_tenant
-- ----------------------------
DROP TABLE IF EXISTS `im_tenant`;
CREATE TABLE `im_tenant`  (
  `id` bigint NOT NULL COMMENT '租户id',
  `tenant_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户名称',
  `tenant_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户唯一标识码',
  `industry_type` tinyint NULL DEFAULT NULL COMMENT '行业类型',
  `tenant_status` tinyint NULL DEFAULT NULL COMMENT '状态',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '服务期时间',
  `max_user_count` int NULL DEFAULT NULL COMMENT '最大用户数限制',
  `max_group_count` int NULL DEFAULT NULL COMMENT '最大群组数限制',
  `max_msg_per_day` bigint NULL DEFAULT NULL COMMENT '每日最大消息数限制',
  `storage_limit` bigint NULL DEFAULT NULL COMMENT '存储空间限制(MB)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `tenant_code`(`tenant_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '租户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_usage_statistics
-- ----------------------------
DROP TABLE IF EXISTS `im_usage_statistics`;
CREATE TABLE `im_usage_statistics`  (
  `id` bigint NOT NULL COMMENT '统计id',
  `fk_tenant_id` bigint NOT NULL COMMENT '租户id',
  `date_key` date NOT NULL COMMENT '统计日期',
  `msg_count` bigint NULL DEFAULT 0 COMMENT '消息数',
  `api_call_count` bigint NULL DEFAULT 0 COMMENT 'API调用次数',
  `storage_used` bigint NULL DEFAULT 0 COMMENT '已使用存储空间(MB)',
  `bandwidth_used` bigint NULL DEFAULT 0 COMMENT '已使用带宽(MB)',
  `active_user_count` int NULL DEFAULT 0 COMMENT '活跃用户数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_tenant_date`(`fk_tenant_id` ASC, `date_key` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '使用量统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `fk_tenant_id` bigint NOT NULL COMMENT '租户id',
  `address_code` int NULL DEFAULT NULL COMMENT '手机号地区编码+86等等',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `id_card_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱号码',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` int NULL DEFAULT 3 COMMENT '性别 1-男 2-女 3-未知',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_offline_time` timestamp NULL DEFAULT NULL COMMENT '最后离线时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `attributes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `all_valid` int NULL DEFAULT 1 COMMENT '所有设备不推送提醒 1提醒',
  `user_status` tinyint NULL DEFAULT NULL COMMENT '用户状态',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_phone`(`id_card_no` ASC, `phone` ASC) USING BTREE COMMENT '唯一索引手机号',
  INDEX `idx_tenant`(`fk_tenant_id` ASC) USING BTREE,
  INDEX `idx_phone`(`phone` ASC) USING BTREE,
  INDEX `idx_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_user_department
-- ----------------------------
DROP TABLE IF EXISTS `im_user_department`;
CREATE TABLE `im_user_department`  (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职位',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_dept`(`user_id` ASC, `dept_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户部门关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_user_pts
-- ----------------------------
DROP TABLE IF EXISTS `im_user_pts`;
CREATE TABLE `im_user_pts`  (
  `user_id` bigint NOT NULL COMMENT '主键id',
  `pts` bigint NULL DEFAULT NULL COMMENT '当前最大的同步位点',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户pts表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_user_status
-- ----------------------------
DROP TABLE IF EXISTS `im_user_status`;
CREATE TABLE `im_user_status`  (
  `user_id` bigint NOT NULL,
  `online_status` tinyint NULL DEFAULT 0 COMMENT '在线状态:0离线,1在线',
  `last_active_time` timestamp NULL DEFAULT NULL COMMENT '最后活跃时间',
  `device_info` json NULL COMMENT '设备信息',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户状态表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_webhook_config
-- ----------------------------
DROP TABLE IF EXISTS `im_webhook_config`;
CREATE TABLE `im_webhook_config`  (
  `id` bigint NOT NULL COMMENT '配置id',
  `fk_tenant_id` bigint NOT NULL COMMENT '租户id',
  `webhook_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'webhook地址',
  `webhook_type` tinyint NULL DEFAULT NULL COMMENT 'webhook类型：1消息 2用户 3群组',
  `secret_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密钥',
  `webhook_status` tinyint NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'Webhook配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for im_workspace
-- ----------------------------
DROP TABLE IF EXISTS `im_workspace`;
CREATE TABLE `im_workspace`  (
  `id` bigint NOT NULL COMMENT '工作空间ID',
  `workspace_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工作空间名称',
  `creator_user_id` bigint NOT NULL COMMENT '创建者ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '工作空间描述',
  `domain` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作空间域名',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'logo地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `workspace_status` tinyint NULL DEFAULT NULL COMMENT '空间状态',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '工作空间表' ROW_FORMAT = DYNAMIC;



-- 修改数据库字符集
ALTER DATABASE im_chat_ai CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;


SET FOREIGN_KEY_CHECKS = 1;
