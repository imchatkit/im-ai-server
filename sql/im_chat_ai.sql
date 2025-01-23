/*
 Navicat Premium Dump SQL

 Source Server         : txy-my
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : 106.52.81.52:3306
 Source Schema         : im_chat_ai

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 21/01/2025 17:23:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for im_callback_record
-- ----------------------------
DROP TABLE IF EXISTS `im_callback_record`;
CREATE TABLE `im_callback_record`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `callback_type` tinyint(4) NULL DEFAULT NULL COMMENT '回调类型',
  `callback_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `request_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求内容',
  `response_body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应内容',
  `callback_status` tinyint(4) NULL DEFAULT NULL,
  `retry_count` int(11) NULL DEFAULT 0 COMMENT '重试次数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息回调记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_callback_record
-- ----------------------------

-- ----------------------------
-- Table structure for im_channel
-- ----------------------------
DROP TABLE IF EXISTS `im_channel`;
CREATE TABLE `im_channel`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `fk_workspace_id` bigint(20) NOT NULL COMMENT '所属工作空间ID',
  `fk_conversation_id` bigint(20) NOT NULL COMMENT '关联的会话ID',
  `channel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '频道名称',
  `channel_type` tinyint(4) NOT NULL COMMENT '频道类型:1公开,2私密',
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '频道主题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '频道描述',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父频道ID,用于嵌套',
  `creator_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `sort_order` int(11) NULL DEFAULT 0 COMMENT '排序号',
  `archived` tinyint(4) NULL DEFAULT 0 COMMENT '是否归档:0否,1是',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_workspace_name`(`fk_workspace_id`, `channel_name`) USING BTREE,
  INDEX `idx_workspace_parent`(`fk_workspace_id`, `parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '频道表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_channel
-- ----------------------------

-- ----------------------------
-- Table structure for im_channel_member
-- ----------------------------
DROP TABLE IF EXISTS `im_channel_member`;
CREATE TABLE `im_channel_member`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `fk_channel_id` bigint(20) UNSIGNED NOT NULL COMMENT '频道ID',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `member_role` tinyint(4) NULL DEFAULT NULL COMMENT '用户权限',
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `notification_level` tinyint(4) NULL DEFAULT 1 COMMENT '通知级别:0关闭,1提及时,2所有消息',
  `starred` tinyint(4) NULL DEFAULT 0 COMMENT '是否星标:0否,1是',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_channel_user`(`fk_channel_id`, `fk_user_id`) USING BTREE,
  INDEX `idx_user_channel`(`fk_user_id`, `fk_channel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '频道成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_channel_member
-- ----------------------------

-- ----------------------------
-- Table structure for im_conversation
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation`;
CREATE TABLE `im_conversation`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `conversation_type` tinyint(4) NOT NULL COMMENT '会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `conversation_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '会话状态: 1-正常 2-禁用 3-删除 4-归档',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除: 0-否 1-是',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_tenant_type`(`conversation_type`) USING BTREE,
  INDEX `idx_conversation_type_create`(`conversation_type`, `create_time`) USING BTREE,
  INDEX `idx_type_status_time`(`conversation_type`, `conversation_status`, `create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天会话基础表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_conversation
-- ----------------------------

-- ----------------------------
-- Table structure for im_conversation_member
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation_member`;
CREATE TABLE `im_conversation_member`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `fk_conversation_id` bigint(20) UNSIGNED NOT NULL COMMENT '会话ID',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `user_remark_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会话中的备注名',
  `role` tinyint(4) NULL DEFAULT NULL COMMENT '角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单',
  `disturb_flag` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '免打扰开关 0-关闭 1开启',
  `top_flag` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '置顶开关 0-关闭 1开启',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `mute_at_all` tinyint(1) NOT NULL DEFAULT 0 COMMENT '屏蔽@全体成员 0-不屏蔽 1-屏蔽',
  `muted` tinyint(4) NULL DEFAULT NULL COMMENT '禁言状态: 1-正常发言 2-永久禁言 3-限时禁言',
  `mute_end_time` timestamp NULL DEFAULT NULL COMMENT '禁言结束时间',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_conversation_user`(`fk_conversation_id`, `fk_user_id`) USING BTREE,
  INDEX `conversation_id`(`fk_conversation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会话成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_conversation_member
-- ----------------------------

-- ----------------------------
-- Table structure for im_conversation_recent
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation_recent`;
CREATE TABLE `im_conversation_recent`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `fk_user_id` bigint(20) NOT NULL COMMENT '创建者id',
  `fk_conversation_id` bigint(20) NULL DEFAULT NULL COMMENT '会话id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_msg_id` bigint(20) NULL DEFAULT NULL COMMENT '最后一条消息id',
  `last_msg_time` timestamp NULL DEFAULT NULL COMMENT '最后一条消息时间，精确到毫秒',
  `no_read_count` bigint(20) NULL DEFAULT NULL COMMENT '未读消息数量',
  `top_flag` tinyint(4) NULL DEFAULT 0 COMMENT '置顶标志 0不置顶  1置顶',
  `top_time` timestamp NULL DEFAULT NULL COMMENT '置顶时间,用于排序',
  `removed_flag` tinyint(4) NULL DEFAULT 0 COMMENT '对话移除标志 0没移除  1移除',
  `removed_time` timestamp NULL DEFAULT NULL COMMENT '移除时间,用于判断是否展示',
  `at_me_flag` tinyint(4) NULL DEFAULT 0 COMMENT '是否有at我的消息 0无,1有 ',
  `at_me_msg_id` bigint(20) NULL DEFAULT NULL COMMENT '有at我的消息id',
  `conversation_type` tinyint(4) NULL DEFAULT NULL COMMENT '会话类型:1单聊,2群聊,3系统通知 5频道',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_conversation`(`fk_user_id`, `fk_conversation_id`) USING BTREE COMMENT '用户会话唯一索引',
  INDEX `idx_user_msg_time`(`fk_user_id`, `last_msg_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '首页对话列表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_conversation_recent
-- ----------------------------

-- ----------------------------
-- Table structure for im_conversation_seq
-- ----------------------------
DROP TABLE IF EXISTS `im_conversation_seq`;
CREATE TABLE `im_conversation_seq`  (
  `conversation_id` bigint(20) UNSIGNED NOT NULL COMMENT '主键id',
  `conversation_seq` bigint(20) NOT NULL COMMENT '会话当前序列号',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`conversation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会话序列号表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_conversation_seq
-- ----------------------------

-- ----------------------------
-- Table structure for im_device
-- ----------------------------
DROP TABLE IF EXISTS `im_device`;
CREATE TABLE `im_device`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键id',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `valid` int(11) NULL DEFAULT NULL COMMENT '设备不想收到推送提醒',
  `push_token` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通知推送token',
  `unique_device_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备唯一编码(由设备端生成)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `push_channel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送通道 1极光 2友盟',
  `platform` int(11) NULL DEFAULT NULL COMMENT '客户端平台: 1web, 2Android, 3 ios, 4windows, 5mac',
  `device_status` int(11) NULL DEFAULT NULL COMMENT '设备状态 0退出登录 1正常',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户端设备表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_device
-- ----------------------------

-- ----------------------------
-- Table structure for im_device_pts
-- ----------------------------
DROP TABLE IF EXISTS `im_device_pts`;
CREATE TABLE `im_device_pts`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键id',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `fk_device_id` bigint(20) NOT NULL COMMENT '设备id',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `max_pts` bigint(20) NULL DEFAULT NULL COMMENT '用户某设备当前最大位点',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`fk_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备pts表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_device_pts
-- ----------------------------

-- ----------------------------
-- Table structure for im_friend
-- ----------------------------
DROP TABLE IF EXISTS `im_friend`;
CREATE TABLE `im_friend`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '关系ID',
  `fk_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `fk_friend_id` bigint(20) UNSIGNED NOT NULL COMMENT '好友ID',
  `conversation_id` bigint(20) NOT NULL COMMENT '单聊会话ID',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注名',
  `source` tinyint(4) NULL DEFAULT NULL COMMENT '来源: 1-搜索 2-群聊 3-名片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态: 1-正常 2-删除 3-拉黑',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_user_friend`(`fk_user_id`, `fk_friend_id`) USING BTREE,
  INDEX `idx_conversation`(`conversation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '好友关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_friend
-- ----------------------------

-- ----------------------------
-- Table structure for im_friend_request
-- ----------------------------
DROP TABLE IF EXISTS `im_friend_request`;
CREATE TABLE `im_friend_request`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '申请ID',
  `from_user_id` bigint(20) NOT NULL COMMENT '申请人ID',
  `from_user_remark_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '给接收人的备注',
  `to_user_id` bigint(20) NOT NULL COMMENT '接收人ID',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '验证信息',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `handle_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_to_user`(`to_user_id`, `status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '好友申请表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_friend_request
-- ----------------------------

-- ----------------------------
-- Table structure for im_group
-- ----------------------------
DROP TABLE IF EXISTS `im_group`;
CREATE TABLE `im_group`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '群组ID',
  `fk_conversation_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联的会话ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '群名称',
  `owner_id` bigint(20) NOT NULL COMMENT '群主ID',
  `group_type` tinyint(4) NOT NULL COMMENT '群类型: 1-普通群 2-部门群 3-企业群',
  `max_member_count` int(11) NOT NULL DEFAULT 500 COMMENT '最大成员数',
  `join_type` tinyint(4) NULL DEFAULT 1 COMMENT '加群方式: 0-自由加入 1-需验证 2-禁止加入',
  `notice` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '群公告',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '关联组织ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '关联部门ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_conversation`(`fk_conversation_id`) USING BTREE,
  INDEX `idx_owner`(`owner_id`) USING BTREE,
  INDEX `idx_group_type_create`(`group_type`, `create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群组表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_group
-- ----------------------------

-- ----------------------------
-- Table structure for im_group_announcement
-- ----------------------------
DROP TABLE IF EXISTS `im_group_announcement`;
CREATE TABLE `im_group_announcement`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '公告id',
  `fk_group_id` bigint(20) NOT NULL COMMENT '群组id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公告内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到毫秒',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_group_announcement
-- ----------------------------

-- ----------------------------
-- Table structure for im_group_member
-- ----------------------------
DROP TABLE IF EXISTS `im_group_member`;
CREATE TABLE `im_group_member`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一id',
  `fk_conversation_id` bigint(20) UNSIGNED NOT NULL COMMENT '会话ID',
  `fk_group_id` bigint(20) NOT NULL COMMENT '群组表id',
  `fk_conversation_member_id` bigint(20) NOT NULL COMMENT '会话成员表id',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `member_invited_join_user` bigint(20) NULL DEFAULT 1 COMMENT '邀请该成员进群的用户',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '加入时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `user_group_remark_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群聊中用户的备注名',
  `role` tinyint(4) NOT NULL DEFAULT 1 COMMENT '角色 1-普通群成员 2-管理员 3-群主',
  `group_member_status` tinyint(4) NULL DEFAULT 1 COMMENT '群组成员状态  0主动退群  1正常 2被移出群聊',
  `group_member_join_type` tinyint(4) NULL DEFAULT 1 COMMENT '群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `group_and_user`(`fk_group_id`, `fk_user_id`) USING BTREE,
  INDEX `conversation_id`(`fk_conversation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_group_member
-- ----------------------------

-- ----------------------------
-- Table structure for im_group_setting
-- ----------------------------
DROP TABLE IF EXISTS `im_group_setting`;
CREATE TABLE `im_group_setting`  (
  `fk_group_id` bigint(20) UNSIGNED NOT NULL COMMENT '群组ID',
  `all_mute` tinyint(1) NOT NULL DEFAULT 0 COMMENT '全员禁言: 0-否 1-是',
  `member_invite` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成员邀请开关 0-关闭 1-开启',
  `member_modify` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成员修改群信息开关 0-关闭 1-开启',
  `member_visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成员列表可见开关 0-关闭 1-开启',
  `forbid_add_friend` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止群内加好友 0-关闭 1-开启',
  `forbid_send_redpacket` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止发红包 0-关闭 1-开启',
  `forbid_send_image` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止发图片 0-关闭 1-开启',
  `forbid_send_link` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁止发链接 0-关闭 1-开启',
  `group_disbanded` tinyint(1) NOT NULL DEFAULT 0 COMMENT '群组是否已解散 0-否 1-是',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`fk_group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '群组设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of im_group_setting
-- ----------------------------

-- ----------------------------
-- Table structure for im_message
-- ----------------------------
DROP TABLE IF EXISTS `im_message`;
CREATE TABLE `im_message`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '消息id',
  `fk_conversation_id` bigint(20) NULL DEFAULT NULL COMMENT '会话id',
  `fk_from_user_id` bigint(20) NULL DEFAULT NULL COMMENT '发送者id',
  `conversation_seq` bigint(20) NULL DEFAULT NULL COMMENT '会话粒度单调自增序列号',
  `local_msg_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端本地消息id',
  `msg_type` int(11) NULL DEFAULT NULL COMMENT '消息类型',
  `payload` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '载荷内容如图片视频卡片等不同的参数',
  `media_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '媒体文件地址',
  `msg_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文字内容',
  `at_users` json NULL COMMENT '被@用户列表 格式:[{userId:1,name:\"张三\"},{userId:2,name:\"李四\"}]',
  `msg_status` int(11) NULL DEFAULT 1 COMMENT '消息状态 1正常 2已撤回',
  `receiver_only` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '接收人,多人用英文逗号分隔-群内指定人员可见场景',
  `receiver_count` int(11) NULL DEFAULT NULL COMMENT '接收方总人数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到毫秒',
  `ref_count` int(11) NULL DEFAULT 0 COMMENT '被引用次数',
  `ref_type` tinyint(4) NULL DEFAULT NULL COMMENT '引用类型:0原创,1回复,2转发,3引用',
  `root_msg_id` bigint(20) NULL DEFAULT NULL COMMENT '会话根消息ID(第一条被引用的消息)',
  `parent_msg_id` bigint(20) NULL DEFAULT NULL COMMENT '直接引用的消息ID',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `at_all` tinyint(1) NOT NULL DEFAULT 0 COMMENT '@全体成员标记 0-否 1-是',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `app_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户ID',
  `conversation_type` tinyint(4) NOT NULL COMMENT '会话类型:1单聊,2群聊,3聊天室',
  `to_uid` bigint(20) NULL DEFAULT NULL COMMENT '接收者ID(单聊必填)',
  `cmd` int(11) NOT NULL COMMENT '命令类型',
  `persistent` tinyint(1) NULL DEFAULT 1 COMMENT '是否持久化',
  `priority` tinyint(4) NULL DEFAULT 0 COMMENT '消息优先级',
  `need_receipt` tinyint(1) NULL DEFAULT 0 COMMENT '是否需要回执',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_conversation`(`fk_conversation_id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_msg_type_create`(`msg_type`, `create_time`) USING BTREE,
  INDEX `idx_conversation_time`(`fk_conversation_id`, `create_time`) USING BTREE,
  INDEX `idx_at_all`(`fk_conversation_id`, `at_all`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息存储表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_message
-- ----------------------------

-- ----------------------------
-- Table structure for im_msg_read
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_read`;
CREATE TABLE `im_msg_read`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '已读表id',
  `fk_msg_id` bigint(20) NOT NULL COMMENT '消息id',
  `fk_conversation_id` bigint(20) NOT NULL COMMENT '会话id',
  `fk_receiver_user_id` bigint(20) NOT NULL COMMENT '接收方id',
  `fk_from_user_id` bigint(20) NULL DEFAULT NULL COMMENT '发送者id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '读取时间',
  `receiver_time` timestamp NULL DEFAULT NULL COMMENT '接收时间',
  `read_msg_status` tinyint(4) NULL DEFAULT 0 COMMENT '0未读; 1已读',
  `receiver_msg_status` tinyint(4) NULL DEFAULT 0 COMMENT '0未接收; 1已接收',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `msg_id`(`fk_msg_id`) USING BTREE,
  INDEX `conversation_user`(`fk_conversation_id`, `fk_receiver_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息已读记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_msg_read
-- ----------------------------

-- ----------------------------
-- Table structure for im_msg_recall
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_recall`;
CREATE TABLE `im_msg_recall`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '撤回记录id',
  `fk_msg_id` bigint(20) NOT NULL COMMENT '消息id',
  `fk_user_id` bigint(20) NOT NULL COMMENT '撤回用户id',
  `recall_time` timestamp NULL DEFAULT NULL COMMENT '撤回时间，精确到毫秒',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息撤回记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_msg_recall
-- ----------------------------

-- ----------------------------
-- Table structure for im_msg_receiver
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_receiver`;
CREATE TABLE `im_msg_receiver`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `fk_msg_id` bigint(20) UNSIGNED NOT NULL COMMENT '消息ID',
  `fk_receiver_id` bigint(20) UNSIGNED NOT NULL COMMENT '接收者ID',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_msg_receiver`(`fk_msg_id`, `fk_receiver_id`) USING BTREE,
  INDEX `idx_receiver_time`(`fk_receiver_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息接收表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_msg_receiver
-- ----------------------------

-- ----------------------------
-- Table structure for im_msg_reference
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_reference`;
CREATE TABLE `im_msg_reference`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `fk_msg_id` bigint(20) NOT NULL COMMENT '当前消息ID',
  `fk_ref_msg_id` bigint(20) NOT NULL COMMENT '被引用的消息ID',
  `ref_type` tinyint(4) NOT NULL COMMENT '引用类型:1回复,2转发,3引用',
  `ref_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '引用时添加的评论文本',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_msg_id`(`fk_msg_id`) USING BTREE,
  INDEX `idx_ref_msg_id`(`fk_ref_msg_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息引用关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_msg_reference
-- ----------------------------

-- ----------------------------
-- Table structure for im_msg_reference_path
-- ----------------------------
DROP TABLE IF EXISTS `im_msg_reference_path`;
CREATE TABLE `im_msg_reference_path`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `fk_msg_id` bigint(20) NOT NULL COMMENT '消息ID',
  `ancestor_msg_id` bigint(20) NOT NULL COMMENT '祖先消息ID',
  `distance` int(11) NOT NULL COMMENT '引用深度(层级距离)',
  `path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '引用路径(格式:id1->id2->id3)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_msg_ancestor`(`fk_msg_id`, `ancestor_msg_id`) USING BTREE,
  INDEX `idx_ancestor_msg`(`ancestor_msg_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息引用路径表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_msg_reference_path
-- ----------------------------

-- ----------------------------
-- Table structure for im_sensitive_words
-- ----------------------------
DROP TABLE IF EXISTS `im_sensitive_words`;
CREATE TABLE `im_sensitive_words`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '敏感词id',
  `word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '敏感词',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，精确到毫秒',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '敏感词过滤表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_sensitive_words
-- ----------------------------

-- ----------------------------
-- Table structure for im_sync
-- ----------------------------
DROP TABLE IF EXISTS `im_sync`;
CREATE TABLE `im_sync`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '同步id',
  `fk_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `pts` bigint(20) NULL DEFAULT NULL COMMENT '用户维度单调递增的PTS位点',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fk_msg_id` bigint(20) NULL DEFAULT NULL COMMENT '消息id',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_pts`(`fk_user_id`, `pts`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '多端同步表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_sync
-- ----------------------------

-- ----------------------------
-- Table structure for im_sys_conversation_init
-- ----------------------------
DROP TABLE IF EXISTS `im_sys_conversation_init`;
CREATE TABLE `im_sys_conversation_init`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT 'id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `conversation_type` int(11) NOT NULL COMMENT '会话类型',
  `conversation_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会话名称',
  `extras` json NULL COMMENT '可选	自定义属性，供开发者扩展使用。',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '头像',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统会话初始化表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_sys_conversation_init
-- ----------------------------

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键id',
  `address_code` int(11) NULL DEFAULT NULL COMMENT '手机号地区编码+86等等',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_card_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` int(11) NULL DEFAULT 3 COMMENT '性别 1-男 2-女 3-未知',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_offline_time` timestamp NULL DEFAULT NULL COMMENT '最后离线时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `attributes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选	自定义属性，供开发者扩展使用',
  `all_valid` int(11) NULL DEFAULT 1 COMMENT '所有设备不推送提醒 1提醒',
  `user_status` tinyint(4) NULL DEFAULT NULL COMMENT '用户状态',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_phone`(`id_card_no`, `phone`) USING BTREE COMMENT '唯一索引手机号',
  INDEX `idx_phone`(`phone`) USING BTREE,
  INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_user
-- ----------------------------
INSERT INTO `im_user` VALUES (398120364340178, NULL, NULL, NULL, NULL, NULL, 3, NULL, '言静怡', '2025-01-16 15:25:37', NULL, '2025-01-16 15:25:37', 'irure est', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for im_user_pts
-- ----------------------------
DROP TABLE IF EXISTS `im_user_pts`;
CREATE TABLE `im_user_pts`  (
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '主键id',
  `pts` bigint(20) NULL DEFAULT NULL COMMENT '当前最大的同步位点',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户pts表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_user_pts
-- ----------------------------

-- ----------------------------
-- Table structure for im_user_status
-- ----------------------------
DROP TABLE IF EXISTS `im_user_status`;
CREATE TABLE `im_user_status`  (
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `online_status` tinyint(4) NULL DEFAULT 0 COMMENT '在线状态:0离线,1在线',
  `last_active_time` timestamp NULL DEFAULT NULL COMMENT '最后活跃时间',
  `device_info` json NULL COMMENT '设备信息',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户状态表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_user_status
-- ----------------------------

-- ----------------------------
-- Table structure for im_webhook_config
-- ----------------------------
DROP TABLE IF EXISTS `im_webhook_config`;
CREATE TABLE `im_webhook_config`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '配置id',
  `webhook_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'webhook地址',
  `webhook_type` tinyint(4) NULL DEFAULT NULL COMMENT 'webhook类型：1消息 2用户 3群组',
  `secret_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密钥',
  `webhook_status` tinyint(4) NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Webhook配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_webhook_config
-- ----------------------------

-- ----------------------------
-- Table structure for im_workspace
-- ----------------------------
DROP TABLE IF EXISTS `im_workspace`;
CREATE TABLE `im_workspace`  (
  `id` bigint(20) NOT NULL COMMENT '工作空间ID',
  `workspace_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工作空间名称',
  `creator_user_id` bigint(20) NOT NULL COMMENT '创建者ID',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '工作空间描述',
  `domain` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作空间域名',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'logo地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `workspace_status` tinyint(4) NULL DEFAULT NULL COMMENT '空间状态',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `extras` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '可选 自定义属性，供开发者扩展使用',
  `create_dept` bigint(20) NULL DEFAULT NULL,
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工作空间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of im_workspace
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
