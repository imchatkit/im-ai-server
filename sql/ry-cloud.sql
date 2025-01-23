/*
 Navicat Premium Dump SQL

 Source Server         : txy-my
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : 106.52.81.52:3306
 Source Schema         : ry-cloud

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 21/01/2025 17:33:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL COMMENT '编号',
  `data_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '数据源名称',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1876817912675946498, 'im-master', 'im_callback_record', '消息回调记录表', NULL, NULL, 'ImCallbackRecord', 'crud', 'com.imcore', 'imcore', 'callbackRecord', '消息回调记录', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:30', NULL, '2024-12-31 09:01:30', NULL);
INSERT INTO `gen_table` VALUES (1876817914131369986, 'im-master', 'im_channel', '频道表', NULL, NULL, 'ImChannel', 'crud', 'com.imcore', 'imcore', 'channel', '频道', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 05:58:37', NULL, '2025-01-03 05:58:37', NULL);
INSERT INTO `gen_table` VALUES (1876817915318358017, 'im-master', 'im_channel_member', '频道成员表', NULL, NULL, 'ImChannelMember', 'crud', 'com.imcore', 'imcore', 'channelMember', '频道成员', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 05:59:16', NULL, '2025-01-03 05:59:16', NULL);
INSERT INTO `gen_table` VALUES (1876817916031389698, 'im-master', 'im_conversation', '聊天会话基础表', NULL, NULL, 'ImConversation', 'crud', 'com.imcore', 'imcore', 'conversation', '聊天会话基础', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:00:02', NULL, '2025-01-03 06:00:02', NULL);
INSERT INTO `gen_table` VALUES (1876817917268709377, 'im-master', 'im_conversation_member', '会话成员表', NULL, NULL, 'ImConversationMember', 'crud', 'com.imcore', 'imcore', 'conversationMember', '会话成员', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:01:07', NULL, '2025-01-03 06:01:07', NULL);
INSERT INTO `gen_table` VALUES (1876817918304702465, 'im-master', 'im_conversation_recent', '首页对话列表', NULL, NULL, 'ImConversationRecent', 'crud', 'com.imcore', 'imcore', 'conversationRecent', '首页对话列', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:01:37', NULL, '2025-01-03 06:01:37', NULL);
INSERT INTO `gen_table` VALUES (1876817919353278465, 'im-master', 'im_conversation_seq', '会话序列号表', NULL, NULL, 'ImConversationSeq', 'crud', 'com.imcore', 'imcore', 'conversationSeq', '会话序列号', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:01:56', NULL, '2025-01-03 06:01:56', NULL);
INSERT INTO `gen_table` VALUES (1876817919999201281, 'im-master', 'im_device', '客户端设备表', NULL, NULL, 'ImDevice', 'crud', 'com.imcore', 'imcore', 'device', '客户端设备', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:02:49', NULL, '2025-01-03 06:02:49', NULL);
INSERT INTO `gen_table` VALUES (1876817921081331714, 'im-master', 'im_device_pts', '设备pts表', NULL, NULL, 'ImDevicePts', 'crud', 'com.imcore', 'imcore', 'devicePts', '设备pts', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:03:12', NULL, '2025-01-03 06:03:12', NULL);
INSERT INTO `gen_table` VALUES (1876817922104741890, 'im-master', 'im_friend', '好友关系表', NULL, NULL, 'ImFriend', 'crud', 'com.imcore', 'imcore', 'friend', '好友关系', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:30', NULL, '2024-12-31 09:01:30', NULL);
INSERT INTO `gen_table` VALUES (1876817923539193858, 'im-master', 'im_friend_request', '好友申请表', NULL, NULL, 'ImFriendRequest', 'crud', 'com.imcore', 'imcore', 'friendRequest', '好友申请', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817924499689474, 'im-master', 'im_group', '群组表', NULL, NULL, 'ImGroup', 'crud', 'com.imcore', 'imcore', 'group', '群组', 'wei', '0', '/', NULL, NULL, 1, '2025-01-06 08:58:45', NULL, '2025-01-06 08:58:45', NULL);
INSERT INTO `gen_table` VALUES (1876817925611180033, 'im-master', 'im_group_announcement', '群公告表', NULL, NULL, 'ImGroupAnnouncement', 'crud', 'com.imcore', 'imcore', 'groupAnnouncement', '群公告', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817926659756034, 'im-master', 'im_group_member', '群成员表', NULL, NULL, 'ImGroupMember', 'crud', 'com.imcore', 'imcore', 'groupMember', '群成员', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:06:18', NULL, '2025-01-03 06:06:18', NULL);
INSERT INTO `gen_table` VALUES (1876817927649611778, 'im-master', 'im_group_setting', '群组设置表', NULL, NULL, 'ImGroupSetting', 'crud', 'com.imcore', 'imcore', 'groupSetting', '群组设置', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817928371032065, 'im-master', 'im_message', '消息存储表', NULL, NULL, 'ImMessage', 'crud', 'com.imcore', 'imcore', 'message', '消息存储', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:09:46', NULL, '2025-01-03 06:09:46', NULL);
INSERT INTO `gen_table` VALUES (1876817930199748609, 'im-master', 'im_msg_read', '消息已读记录表', NULL, NULL, 'ImMsgRead', 'crud', 'com.imcore', 'imcore', 'msgRead', '消息已读记录', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:10:13', NULL, '2025-01-03 06:10:13', NULL);
INSERT INTO `gen_table` VALUES (1876817931239936001, 'im-master', 'im_msg_recall', '消息撤回记录表', NULL, NULL, 'ImMsgRecall', 'crud', 'com.imcore', 'imcore', 'msgRecall', '消息撤回记录', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817931890053121, 'im-master', 'im_msg_receiver', '消息接收表', NULL, NULL, 'ImMsgReceiver', 'crud', 'com.imcore', 'imcore', 'msgReceiver', '消息接收', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 06:12:05', NULL, '2025-01-03 06:12:05', NULL);
INSERT INTO `gen_table` VALUES (1876817932540170242, 'im-master', 'im_msg_reference', '消息引用关系表', NULL, NULL, 'ImMsgReference', 'crud', 'com.imcore', 'imcore', 'msgReference', '消息引用关系', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817933576163330, 'im-master', 'im_msg_reference_path', '消息引用路径表', NULL, NULL, 'ImMsgReferencePath', 'crud', 'com.imcore', 'imcore', 'msgReferencePath', '消息引用路径', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817934570213378, 'im-master', 'im_sensitive_words', '敏感词过滤表', NULL, NULL, 'ImSensitiveWords', 'crud', 'com.imcore', 'imcore', 'sensitiveWords', '敏感词过滤', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817935614595073, 'im-master', 'im_sync', '多端同步表', NULL, NULL, 'ImSync', 'crud', 'com.imcore', 'imcore', 'sync', '多端同步', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817936658976770, 'im-master', 'im_sys_conversation_init', '系统会话初始化表', NULL, NULL, 'ImSysConversationInit', 'crud', 'com.imcore', 'imcore', 'sysConversationInit', '系统会话初始化', 'wei', '0', '/', NULL, NULL, 1, '2025-01-03 05:57:40', NULL, '2025-01-03 05:57:40', NULL);
INSERT INTO `gen_table` VALUES (1876817937820798977, 'im-master', 'im_user', '用户表', NULL, NULL, 'ImUser', 'crud', 'com.imcore', 'imcore', 'user', '用户', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:32', NULL, '2024-12-31 09:01:32', NULL);
INSERT INTO `gen_table` VALUES (1876817939318165506, 'im-master', 'im_user_pts', '用户pts表', NULL, NULL, 'ImUserPts', 'crud', 'com.imcore', 'imcore', 'userPts', '用户pts', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817940035391489, 'im-master', 'im_user_status', '用户状态表', NULL, NULL, 'ImUserStatus', 'crud', 'com.imcore', 'imcore', 'userStatus', '用户状态', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817941075578882, 'im-master', 'im_webhook_config', 'Webhook配置表', NULL, NULL, 'ImWebhookConfig', 'crud', 'com.imcore', 'imcore', 'webhookConfig', 'Webhook配置', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);
INSERT INTO `gen_table` VALUES (1876817942115766273, 'im-master', 'im_workspace', '工作空间表', NULL, NULL, 'ImWorkspace', 'crud', 'com.imcore', 'imcore', 'workspace', '工作空间', 'wei', '0', '/', NULL, NULL, 1, '2024-12-31 09:01:31', NULL, '2024-12-31 09:01:31', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1876817913338646530, 1876817912675946498, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913338646531, 1876817912675946498, 'callback_type', '回调类型', 'tinyint', 'Long', 'callbackType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913338646532, 1876817912675946498, 'callback_url', '回调地址', 'varchar', 'String', 'callbackUrl', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755394, 1876817912675946498, 'request_body', '请求内容', 'text', 'String', 'requestBody', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 4, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755395, 1876817912675946498, 'response_body', '响应内容', 'text', 'String', 'responseBody', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755396, 1876817912675946498, 'callback_status', '', 'tinyint', 'Long', 'callbackStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755397, 1876817912675946498, 'retry_count', '重试次数', 'int', 'Long', 'retryCount', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755398, 1876817912675946498, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 8, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755399, 1876817912675946498, 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 9, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755400, 1876817912675946498, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817913405755401, 1876817912675946498, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 11, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634562, 1876817914131369986, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634563, 1876817914131369986, 'fk_workspace_id', '所属工作空间ID', 'bigint', 'Long', 'fkWorkspaceId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634564, 1876817914131369986, 'fk_conversation_id', '关联的会话ID', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634565, 1876817914131369986, 'channel_name', '频道名称', 'varchar', 'String', 'channelName', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634566, 1876817914131369986, 'channel_type', '频道类型:1公开,2私密', 'tinyint', 'Long', 'channelType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634567, 1876817914131369986, 'topic', '频道主题', 'varchar', 'String', 'topic', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634568, 1876817914131369986, 'description', '频道描述', 'text', 'String', 'description', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634569, 1876817914131369986, 'parent_id', '父频道ID,用于嵌套', 'bigint', 'Long', 'parentId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634570, 1876817914131369986, 'creator_user_id', '创建者ID', 'bigint', 'Long', 'creatorUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634571, 1876817914131369986, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 10, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634572, 1876817914131369986, 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 11, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634573, 1876817914131369986, 'sort_order', '排序号', 'int', 'Long', 'sortOrder', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634574, 1876817914131369986, 'archived', '是否归档:0否,1是', 'tinyint', 'Long', 'archived', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634575, 1876817914131369986, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817914525634576, 1876817914131369986, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 15, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125121, 1876817915318358017, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125122, 1876817915318358017, 'fk_channel_id', '频道ID', 'bigint', 'Long', 'fkChannelId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125123, 1876817915318358017, 'fk_user_id', '用户ID', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125124, 1876817915318358017, 'member_role', '用户权限', 'tinyint', 'Long', 'memberRole', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125125, 1876817915318358017, 'join_time', '加入时间', 'timestamp', 'Date', 'joinTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 5, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125126, 1876817915318358017, 'notification_level', '通知级别:0关闭,1提及时,2所有消息', 'tinyint', 'Long', 'notificationLevel', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125127, 1876817915318358017, 'starred', '是否星标:0否,1是', 'tinyint', 'Long', 'starred', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125128, 1876817915318358017, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817915637125129, 1876817915318358017, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 9, NULL, 1, '2025-01-08 10:27:15', NULL, '2025-01-08 10:27:15');
INSERT INTO `gen_table_column` VALUES (1876817916354351106, 1876817916031389698, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916354351107, 1876817916031389698, 'avatar', '头像', 'varchar', 'String', 'avatar', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916354351108, 1876817916031389698, 'conversation_type', '会话类型: 1-单聊 2-群聊 3-系统通知 4-机器人 5频道', 'tinyint', 'Long', 'conversationType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916354351109, 1876817916031389698, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916354351110, 1876817916031389698, 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 5, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916354351111, 1876817916031389698, 'conversation_status', '会话状态: 1-正常 2-禁用 3-删除 4-归档', 'tinyint', 'Long', 'conversationStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916354351112, 1876817916031389698, 'deleted', '是否删除: 0-否 1-是', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817916547289089, 1876817916031389698, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 8, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756225, 1876817917268709377, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756226, 1876817917268709377, 'fk_conversation_id', '会话ID', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756227, 1876817917268709377, 'fk_user_id', '用户id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756228, 1876817917268709377, 'create_time', '加入时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756229, 1876817917268709377, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 5, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756230, 1876817917268709377, 'extras', '可选	自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 6, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756231, 1876817917268709377, 'user_remark_name', '会话中的备注名', 'varchar', 'String', 'userRemarkName', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 7, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756232, 1876817917268709377, 'role', '角色: 1-普通成员 2-管理员 3-群主 4-访客 5-黑名单', 'tinyint', 'Long', 'role', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756233, 1876817917268709377, 'disturb_flag', '免打扰开关 0-关闭 1开启', 'tinyint', 'Long', 'disturbFlag', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756234, 1876817917268709377, 'top_flag', '置顶开关 0-关闭 1开启', 'tinyint', 'Long', 'topFlag', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756235, 1876817917268709377, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756236, 1876817917268709377, 'mute_at_all', '屏蔽@全体成员 0-不屏蔽 1-屏蔽', 'tinyint', 'Long', 'muteAtAll', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756237, 1876817917268709377, 'muted', '禁言状态: 1-正常发言 2-永久禁言 3-限时禁言', 'tinyint', 'Long', 'muted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817917528756238, 1876817917268709377, 'mute_end_time', '禁言结束时间', 'timestamp', 'Date', 'muteEndTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 14, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052481, 1876817918304702465, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052482, 1876817918304702465, 'fk_user_id', '创建者id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052483, 1876817918304702465, 'fk_conversation_id', '会话id', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052484, 1876817918304702465, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052485, 1876817918304702465, 'last_msg_id', '最后一条消息id', 'bigint', 'Long', 'lastMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052486, 1876817918304702465, 'last_msg_time', '最后一条消息时间，精确到毫秒', 'timestamp', 'Date', 'lastMsgTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052487, 1876817918304702465, 'no_read_count', '未读消息数量', 'bigint', 'Long', 'noReadCount', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052488, 1876817918304702465, 'top_flag', '置顶标志 0不置顶  1置顶', 'tinyint', 'Long', 'topFlag', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052489, 1876817918304702465, 'top_time', '置顶时间,用于排序', 'timestamp', 'Date', 'topTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 9, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052490, 1876817918304702465, 'removed_flag', '对话移除标志 0没移除  1移除', 'tinyint', 'Long', 'removedFlag', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052491, 1876817918304702465, 'removed_time', '移除时间,用于判断是否展示', 'timestamp', 'Date', 'removedTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 11, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052492, 1876817918304702465, 'at_me_flag', '是否有at我的消息 0无,1有 ', 'tinyint', 'Long', 'atMeFlag', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052493, 1876817918304702465, 'at_me_msg_id', '有at我的消息id', 'bigint', 'Long', 'atMeMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817918636052494, 1876817918304702465, 'conversation_type', '会话类型:1单聊,2群聊,3系统通知 5频道', 'tinyint', 'Long', 'conversationType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 14, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817919676239874, 1876817919353278465, 'conversation_id', '主键id', 'bigint', 'Long', 'conversationId', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817919676239875, 1876817919353278465, 'conversation_seq', '会话当前序列号', 'bigint', 'Long', 'conversationSeq', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774081, 1876817919999201281, 'id', '主键id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774082, 1876817919999201281, 'fk_user_id', '用户id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774083, 1876817919999201281, 'valid', '设备不想收到推送提醒', 'int', 'Long', 'valid', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774084, 1876817919999201281, 'push_token', '通知推送token', 'varchar', 'String', 'pushToken', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774085, 1876817919999201281, 'unique_device_code', '设备唯一编码(由设备端生成)', 'varchar', 'String', 'uniqueDeviceCode', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774086, 1876817919999201281, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774087, 1876817919999201281, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774088, 1876817919999201281, 'push_channel', '推送通道 1极光 2友盟', 'varchar', 'String', 'pushChannel', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774089, 1876817919999201281, 'platform', '客户端平台: 1web, 2Android, 3 ios, 4windows, 5mac', 'int', 'Long', 'platform', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774090, 1876817919999201281, 'device_status', '设备状态 0退出登录 1正常', 'int', 'Long', 'deviceStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 10, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774091, 1876817919999201281, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817920313774092, 1876817919999201281, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 12, NULL, 1, '2025-01-08 10:27:16', NULL, '2025-01-08 10:27:16');
INSERT INTO `gen_table_column` VALUES (1876817921400098817, 1876817921081331714, 'id', '主键id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817921400098818, 1876817921081331714, 'fk_user_id', '用户id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817921400098819, 1876817921081331714, 'fk_device_id', '设备id', 'bigint', 'Long', 'fkDeviceId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817921400098820, 1876817921081331714, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817921400098821, 1876817921081331714, 'max_pts', '用户某设备当前最大位点', 'bigint', 'Long', 'maxPts', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817921400098822, 1876817921081331714, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817921400098823, 1876817921081331714, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703298, 1876817922104741890, 'id', '关系ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703299, 1876817922104741890, 'fk_user_id', '用户ID', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703300, 1876817922104741890, 'fk_friend_id', '好友ID', 'bigint', 'Long', 'fkFriendId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703301, 1876817922104741890, 'conversation_id', '单聊会话ID', 'bigint', 'Long', 'conversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703302, 1876817922104741890, 'remark', '备注名', 'varchar', 'String', 'remark', '0', '1', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703303, 1876817922104741890, 'source', '来源: 1-搜索 2-群聊 3-名片', 'tinyint', 'Long', 'source', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703304, 1876817922104741890, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703305, 1876817922104741890, 'status', '状态: 1-正常 2-删除 3-拉黑', 'tinyint', 'Long', 'status', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 8, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703306, 1876817922104741890, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817922427703307, 1876817922104741890, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 10, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240706, 1876817923539193858, 'id', '申请ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240707, 1876817923539193858, 'from_user_id', '申请人ID', 'bigint', 'Long', 'fromUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240708, 1876817923539193858, 'from_user_remark_name', '给接收人的备注', 'varchar', 'String', 'fromUserRemarkName', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240709, 1876817923539193858, 'to_user_id', '接收人ID', 'bigint', 'Long', 'toUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240710, 1876817923539193858, 'message', '验证信息', 'varchar', 'String', 'message', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240711, 1876817923539193858, 'status', '状态: 0-待处理 1-同意 2-拒绝 3-已过期 4-已取消 5-已删除 6-已忽略', 'tinyint', 'Long', 'status', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240712, 1876817923539193858, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240713, 1876817923539193858, 'handle_time', '处理时间', 'timestamp', 'Date', 'handleTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 8, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240714, 1876817923539193858, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817923799240715, 1876817923539193858, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 10, NULL, 1, '2025-01-08 10:27:17', NULL, '2025-01-08 10:27:17');
INSERT INTO `gen_table_column` VALUES (1876817924826845185, 1876817924499689474, 'id', '群组ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845186, 1876817924499689474, 'fk_conversation_id', '关联的会话ID', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845187, 1876817924499689474, 'name', '群名称', 'varchar', 'String', 'name', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845188, 1876817924499689474, 'owner_id', '群主ID', 'bigint', 'Long', 'ownerId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845189, 1876817924499689474, 'group_type', '群类型: 1-普通群 2-部门群 3-企业群', 'tinyint', 'Long', 'groupType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845190, 1876817924499689474, 'max_member_count', '最大成员数', 'int', 'Long', 'maxMemberCount', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845191, 1876817924499689474, 'join_type', '加群方式: 0-自由加入 1-需验证 2-禁止加入', 'tinyint', 'Long', 'joinType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 7, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845192, 1876817924499689474, 'notice', '群公告', 'text', 'String', 'notice', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 8, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845193, 1876817924499689474, 'org_id', '关联组织ID', 'bigint', 'Long', 'orgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845194, 1876817924499689474, 'dept_id', '关联部门ID', 'bigint', 'Long', 'deptId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845195, 1876817924499689474, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 11, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845196, 1876817924499689474, 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 12, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817924826845197, 1876817924499689474, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 13, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817925871226882, 1876817925611180033, 'id', '公告id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817925871226883, 1876817925611180033, 'fk_group_id', '群组id', 'bigint', 'Long', 'fkGroupId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817925871226884, 1876817925611180033, 'content', '公告内容', 'text', 'String', 'content', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 3, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817925871226885, 1876817925611180033, 'create_time', '创建时间，精确到毫秒', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817925871226886, 1876817925611180033, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817925871226887, 1876817925611180033, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 6, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997186, 1876817926659756034, 'id', '唯一id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997187, 1876817926659756034, 'fk_conversation_id', '会话ID', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997188, 1876817926659756034, 'fk_group_id', '群组表id', 'bigint', 'Long', 'fkGroupId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997189, 1876817926659756034, 'fk_conversation_member_id', '会话成员表id', 'bigint', 'Long', 'fkConversationMemberId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997190, 1876817926659756034, 'fk_user_id', '用户id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997191, 1876817926659756034, 'member_invited_join_user', '邀请该成员进群的用户', 'bigint', 'Long', 'memberInvitedJoinUser', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997192, 1876817926659756034, 'create_time', '加入时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997193, 1876817926659756034, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 8, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997194, 1876817926659756034, 'extras', '可选	自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 9, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997195, 1876817926659756034, 'user_group_remark_name', '群聊中用户的备注名', 'varchar', 'String', 'userGroupRemarkName', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 10, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997196, 1876817926659756034, 'role', '角色 1-普通群成员 2-管理员 3-群主', 'tinyint', 'Long', 'role', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997197, 1876817926659756034, 'group_member_status', '群组成员状态  0主动退群  1正常 2被移出群聊', 'tinyint', 'Long', 'groupMemberStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 12, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997198, 1876817926659756034, 'group_member_join_type', '群组成员进群方式: 1创建时加入 2主动扫码加入 3被邀请进入', 'tinyint', 'Long', 'groupMemberJoinType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 13, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817926923997199, 1876817926659756034, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961794, 1876817927649611778, 'fk_group_id', '群组ID', 'bigint', 'Long', 'fkGroupId', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961795, 1876817927649611778, 'all_mute', '全员禁言: 0-否 1-是', 'tinyint', 'Long', 'allMute', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961796, 1876817927649611778, 'member_invite', '成员邀请开关 0-关闭 1-开启', 'tinyint', 'Long', 'memberInvite', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961797, 1876817927649611778, 'member_modify', '成员修改群信息开关 0-关闭 1-开启', 'tinyint', 'Long', 'memberModify', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961798, 1876817927649611778, 'member_visible', '成员列表可见开关 0-关闭 1-开启', 'tinyint', 'Long', 'memberVisible', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961799, 1876817927649611778, 'forbid_add_friend', '禁止群内加好友 0-关闭 1-开启', 'tinyint', 'Long', 'forbidAddFriend', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961800, 1876817927649611778, 'forbid_send_redpacket', '禁止发红包 0-关闭 1-开启', 'tinyint', 'Long', 'forbidSendRedpacket', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961801, 1876817927649611778, 'forbid_send_image', '禁止发图片 0-关闭 1-开启', 'tinyint', 'Long', 'forbidSendImage', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'imageUpload', '', 8, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961802, 1876817927649611778, 'forbid_send_link', '禁止发链接 0-关闭 1-开启', 'tinyint', 'Long', 'forbidSendLink', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961803, 1876817927649611778, 'group_disbanded', '群组是否已解散 0-否 1-是', 'tinyint', 'Long', 'groupDisbanded', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817927980961804, 1876817927649611778, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 11, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187777, 1876817928371032065, 'id', '消息id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187778, 1876817928371032065, 'fk_conversation_id', '会话id', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187779, 1876817928371032065, 'fk_from_user_id', '发送者id', 'bigint', 'Long', 'fkFromUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187780, 1876817928371032065, 'conversation_seq', '会话粒度单调自增序列号', 'bigint', 'Long', 'conversationSeq', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187781, 1876817928371032065, 'local_msg_id', '客户端本地消息id', 'varchar', 'String', 'localMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187782, 1876817928371032065, 'msg_type', '消息类型', 'int', 'Long', 'msgType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 6, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187783, 1876817928371032065, 'payload', '载荷内容如图片视频卡片等不同的参数', 'text', 'String', 'payload', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187784, 1876817928371032065, 'media_url', '媒体文件地址', 'varchar', 'String', 'mediaUrl', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187785, 1876817928371032065, 'msg_text', '文字内容', 'text', 'String', 'msgText', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 9, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187786, 1876817928371032065, 'at_users', '被@用户列表 格式:[{userId:1,name:\"张三\"},{userId:2,name:\"李四\"}]', 'json', 'String', 'atUsers', '0', '1', '1', '1', '1', '1', '1', 'EQ', NULL, '', 10, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187787, 1876817928371032065, 'msg_status', '消息状态 1正常 2已撤回', 'int', 'Long', 'msgStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 11, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187788, 1876817928371032065, 'receiver_only', '接收人,多人用英文逗号分隔-群内指定人员可见场景', 'text', 'String', 'receiverOnly', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 12, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187789, 1876817928371032065, 'receiver_count', '接收方总人数', 'int', 'Long', 'receiverCount', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187790, 1876817928371032065, 'create_time', '创建时间，精确到毫秒', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 14, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187791, 1876817928371032065, 'ref_count', '被引用次数', 'int', 'Long', 'refCount', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 15, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187792, 1876817928371032065, 'ref_type', '引用类型:0原创,1回复,2转发,3引用', 'tinyint', 'Long', 'refType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 16, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187793, 1876817928371032065, 'root_msg_id', '会话根消息ID(第一条被引用的消息)', 'bigint', 'Long', 'rootMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 17, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187794, 1876817928371032065, 'parent_msg_id', '直接引用的消息ID', 'bigint', 'Long', 'parentMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 18, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187795, 1876817928371032065, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 19, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187796, 1876817928371032065, 'at_all', '@全体成员标记 0-否 1-是', 'tinyint', 'Long', 'atAll', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 20, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187797, 1876817928371032065, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 21, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187798, 1876817928371032065, 'app_id', '应用ID', 'varchar', 'String', 'appId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 22, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187799, 1876817928371032065, 'tenant_id', '租户ID', 'varchar', 'String', 'tenantId', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 23, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187800, 1876817928371032065, 'conversation_type', '会话类型:1单聊,2群聊,3聊天室', 'tinyint', 'Long', 'conversationType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 24, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187801, 1876817928371032065, 'to_uid', '接收者ID(单聊必填)', 'bigint', 'Long', 'toUid', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 25, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187802, 1876817928371032065, 'cmd', '命令类型', 'int', 'Long', 'cmd', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 26, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187803, 1876817928371032065, 'persistent', '是否持久化', 'tinyint', 'Long', 'persistent', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 27, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187804, 1876817928371032065, 'priority', '消息优先级', 'tinyint', 'Long', 'priority', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 28, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817928698187805, 1876817928371032065, 'need_receipt', '是否需要回执', 'tinyint', 'Long', 'needReceipt', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 29, NULL, 1, '2025-01-08 10:27:18', NULL, '2025-01-08 10:27:18');
INSERT INTO `gen_table_column` VALUES (1876817930451406849, 1876817930199748609, 'id', '已读表id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515714, 1876817930199748609, 'fk_msg_id', '消息id', 'bigint', 'Long', 'fkMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515715, 1876817930199748609, 'fk_conversation_id', '会话id', 'bigint', 'Long', 'fkConversationId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515716, 1876817930199748609, 'fk_receiver_user_id', '接收方id', 'bigint', 'Long', 'fkReceiverUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515717, 1876817930199748609, 'fk_from_user_id', '发送者id', 'bigint', 'Long', 'fkFromUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515718, 1876817930199748609, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515719, 1876817930199748609, 'read_time', '读取时间', 'timestamp', 'Date', 'readTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515720, 1876817930199748609, 'receiver_time', '接收时间', 'timestamp', 'Date', 'receiverTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 8, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515721, 1876817930199748609, 'read_msg_status', '0未读; 1已读', 'tinyint', 'Long', 'readMsgStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 9, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515722, 1876817930199748609, 'receiver_msg_status', '0未接收; 1已接收', 'tinyint', 'Long', 'receiverMsgStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 10, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817930518515723, 1876817930199748609, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 11, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817931499982849, 1876817931239936001, 'id', '撤回记录id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817931499982850, 1876817931239936001, 'fk_msg_id', '消息id', 'bigint', 'Long', 'fkMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817931499982851, 1876817931239936001, 'fk_user_id', '撤回用户id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817931499982852, 1876817931239936001, 'recall_time', '撤回时间，精确到毫秒', 'timestamp', 'Date', 'recallTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817931499982853, 1876817931239936001, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817931499982854, 1876817931239936001, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 6, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932217208833, 1876817931890053121, 'id', '', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932217208834, 1876817931890053121, 'fk_msg_id', '消息ID', 'bigint', 'Long', 'fkMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932217208835, 1876817931890053121, 'fk_receiver_id', '接收者ID', 'bigint', 'Long', 'fkReceiverId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932217208836, 1876817931890053121, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932217208837, 1876817931890053121, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131650, 1876817932540170242, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131651, 1876817932540170242, 'fk_msg_id', '当前消息ID', 'bigint', 'Long', 'fkMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131652, 1876817932540170242, 'fk_ref_msg_id', '被引用的消息ID', 'bigint', 'Long', 'fkRefMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131653, 1876817932540170242, 'ref_type', '引用类型:1回复,2转发,3引用', 'tinyint', 'Long', 'refType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131654, 1876817932540170242, 'ref_text', '引用时添加的评论文本', 'text', 'String', 'refText', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131655, 1876817932540170242, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131656, 1876817932540170242, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817932863131657, 1876817932540170242, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 8, NULL, 1, '2025-01-08 10:27:19', NULL, '2025-01-08 10:27:19');
INSERT INTO `gen_table_column` VALUES (1876817933840404482, 1876817933576163330, 'id', '主键ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404483, 1876817933576163330, 'fk_msg_id', '消息ID', 'bigint', 'Long', 'fkMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404484, 1876817933576163330, 'ancestor_msg_id', '祖先消息ID', 'bigint', 'Long', 'ancestorMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404485, 1876817933576163330, 'distance', '引用深度(层级距离)', 'int', 'Long', 'distance', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404486, 1876817933576163330, 'path', '引用路径(格式:id1->id2->id3)', 'text', 'String', 'path', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404487, 1876817933576163330, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404488, 1876817933576163330, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817933840404489, 1876817933576163330, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 8, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817934897369089, 1876817934570213378, 'id', '敏感词id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817934897369090, 1876817934570213378, 'word', '敏感词', 'varchar', 'String', 'word', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817934897369091, 1876817934570213378, 'create_time', '创建时间，精确到毫秒', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 3, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817934897369092, 1876817934570213378, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817934897369093, 1876817934570213378, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935878836226, 1876817935614595073, 'id', '同步id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935878836227, 1876817935614595073, 'fk_user_id', '用户id', 'bigint', 'Long', 'fkUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935878836228, 1876817935614595073, 'pts', '用户维度单调递增的PTS位点', 'bigint', 'Long', 'pts', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935878836229, 1876817935614595073, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 4, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935945945090, 1876817935614595073, 'fk_msg_id', '消息id', 'bigint', 'Long', 'fkMsgId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935945945091, 1876817935614595073, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817935945945092, 1876817935614595073, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936914829314, 1876817936658976770, 'id', 'id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936914829315, 1876817936658976770, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 2, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936914829316, 1876817936658976770, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 3, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936914829317, 1876817936658976770, 'conversation_type', '会话类型', 'int', 'Long', 'conversationType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936914829318, 1876817936658976770, 'conversation_name', '会话名称', 'varchar', 'String', 'conversationName', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 5, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936977743873, 1876817936658976770, 'extras', '可选	自定义属性，供开发者扩展使用。', 'json', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', NULL, '', 6, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936977743874, 1876817936658976770, 'avatar', '头像', 'text', 'String', 'avatar', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817936977743875, 1876817936658976770, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:20', NULL, '2025-01-08 10:27:20');
INSERT INTO `gen_table_column` VALUES (1876817938143760386, 1876817937820798977, 'id', '主键id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760387, 1876817937820798977, 'address_code', '手机号地区编码+86等等', 'int', 'Long', 'addressCode', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760388, 1876817937820798977, 'phone', '', 'varchar', 'String', 'phone', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760389, 1876817937820798977, 'id_card_no', '身份证号码', 'varchar', 'String', 'idCardNo', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760390, 1876817937820798977, 'email', '', 'varchar', 'String', 'email', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760391, 1876817937820798977, 'password', '密码', 'varchar', 'String', 'password', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760392, 1876817937820798977, 'sex', '性别 1-男 2-女 3-未知', 'int', 'Long', 'sex', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 7, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760393, 1876817937820798977, 'avatar', '头像', 'varchar', 'String', 'avatar', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760394, 1876817937820798977, 'nickname', '', 'varchar', 'String', 'nickname', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 9, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760395, 1876817937820798977, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 10, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760396, 1876817937820798977, 'last_offline_time', '最后离线时间', 'timestamp', 'Date', 'lastOfflineTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 11, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760397, 1876817937820798977, 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 12, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760398, 1876817937820798977, 'attributes', '可选	自定义属性，供开发者扩展使用', 'text', 'String', 'attributes', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 13, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760399, 1876817937820798977, 'all_valid', '所有设备不推送提醒 1提醒', 'int', 'Long', 'allValid', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760400, 1876817937820798977, 'user_status', '用户状态', 'tinyint', 'Long', 'userStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 15, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938143760401, 1876817937820798977, 'last_login_time', '最后登录时间', 'timestamp', 'Date', 'lastLoginTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 16, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938210869250, 1876817937820798977, 'last_login_ip', '最后登录IP', 'varchar', 'String', 'lastLoginIp', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 17, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938210869251, 1876817937820798977, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 18, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817938210869252, 1876817937820798977, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 19, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817939578212354, 1876817939318165506, 'user_id', '主键id', 'bigint', 'Long', 'userId', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817939645321218, 1876817939318165506, 'pts', '当前最大的同步位点', 'bigint', 'Long', 'pts', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817939645321219, 1876817939318165506, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817939645321220, 1876817939318165506, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 4, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817940362547202, 1876817940035391489, 'user_id', '', 'bigint', 'Long', 'userId', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817940362547203, 1876817940035391489, 'online_status', '在线状态:0离线,1在线', 'tinyint', 'Long', 'onlineStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 2, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817940362547204, 1876817940035391489, 'last_active_time', '最后活跃时间', 'timestamp', 'Date', 'lastActiveTime', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 3, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817940362547205, 1876817940035391489, 'device_info', '设备信息', 'json', 'String', 'deviceInfo', '0', '1', '1', '1', '1', '1', '1', 'EQ', NULL, '', 4, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817940362547206, 1876817940035391489, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817940362547207, 1876817940035391489, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 6, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941339820033, 1876817941075578882, 'id', '配置id', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928898, 1876817941075578882, 'webhook_url', 'webhook地址', 'varchar', 'String', 'webhookUrl', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928899, 1876817941075578882, 'webhook_type', 'webhook类型：1消息 2用户 3群组', 'tinyint', 'Long', 'webhookType', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928900, 1876817941075578882, 'secret_key', '密钥', 'varchar', 'String', 'secretKey', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928901, 1876817941075578882, 'webhook_status', '', 'tinyint', 'Long', 'webhookStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 5, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928902, 1876817941075578882, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928903, 1876817941075578882, 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928904, 1876817941075578882, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817941406928905, 1876817941075578882, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 9, NULL, 1, '2025-01-08 10:27:21', NULL, '2025-01-08 10:27:21');
INSERT INTO `gen_table_column` VALUES (1876817942442921985, 1876817942115766273, 'id', '工作空间ID', 'bigint', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921986, 1876817942115766273, 'workspace_name', '工作空间名称', 'varchar', 'String', 'workspaceName', '0', '1', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921987, 1876817942115766273, 'creator_user_id', '创建者ID', 'bigint', 'Long', 'creatorUserId', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921988, 1876817942115766273, 'description', '工作空间描述', 'text', 'String', 'description', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 4, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921989, 1876817942115766273, 'domain', '工作空间域名', 'varchar', 'String', 'domain', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921990, 1876817942115766273, 'logo_url', 'logo地址', 'varchar', 'String', 'logoUrl', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921991, 1876817942115766273, 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '1', '1', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 7, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921992, 1876817942115766273, 'update_time', '更新时间', 'timestamp', 'Date', 'updateTime', '0', '1', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 8, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921993, 1876817942115766273, 'workspace_status', '空间状态', 'tinyint', 'Long', 'workspaceStatus', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 9, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921994, 1876817942115766273, 'deleted', '是否删除 0-未删除 1-已删除', 'tinyint', 'Long', 'deleted', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');
INSERT INTO `gen_table_column` VALUES (1876817942442921995, 1876817942115766273, 'extras', '可选 自定义属性，供开发者扩展使用', 'text', 'String', 'extras', '0', '1', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 11, NULL, 1, '2025-01-08 10:27:22', NULL, '2025-01-08 10:27:22');

-- ----------------------------
-- Table structure for sys_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_client`;
CREATE TABLE `sys_client`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端id',
  `client_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端key',
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端秘钥',
  `grant_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权类型',
  `device_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `active_timeout` int(11) NULL DEFAULT 1800 COMMENT 'token活跃超时时间',
  `timeout` int(11) NULL DEFAULT 604800 COMMENT 'token固定超时',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统授权表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_client
-- ----------------------------
INSERT INTO `sys_client` VALUES (1, 'e5cd7e4891bf95d1d19206ce24a7b32e', 'pc', 'pc123', 'password,social', 'pc', 1800, 604800, '0', '0', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21');
INSERT INTO `sys_client` VALUES (2, '428a8310cd442757ae699df5d894f051', 'app', 'app123', 'password,sms,social', 'android', 1800, 604800, '0', '0', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` bigint(20) NOT NULL COMMENT '参数主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '000000', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '000000', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '000000', '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (5, '000000', '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (11, '000000', 'OSS预览列表资源开关', 'sys.oss.previewListResource', 'true', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, 'true:开启, false:关闭');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `dept_category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门类别编码',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` bigint(20) NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, '000000', 0, '0', 'XXX科技', NULL, 0, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (101, '000000', 100, '0,100', '深圳总公司', NULL, 1, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (102, '000000', 100, '0,100', '长沙分公司', NULL, 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (103, '000000', 101, '0,100,101', '研发部门', NULL, 1, 1, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (104, '000000', 101, '0,100,101', '市场部门', NULL, 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (105, '000000', 101, '0,100,101', '测试部门', NULL, 3, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (106, '000000', 101, '0,100,101', '财务部门', NULL, 4, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (107, '000000', 101, '0,100,101', '运维部门', NULL, 5, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (108, '000000', 102, '0,100,102', '市场部门', NULL, 1, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);
INSERT INTO `sys_dept` VALUES (109, '000000', 102, '0,100,102', '财务部门', NULL, 2, NULL, '15888888888', 'xxx@qq.com', '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL COMMENT '字典编码',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, '000000', 1, '男', '0', 'sys_user_sex', '', '', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, '000000', 2, '女', '1', 'sys_user_sex', '', '', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, '000000', 3, '未知', '2', 'sys_user_sex', '', '', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, '000000', 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, '000000', 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, '000000', 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, '000000', 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (12, '000000', 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, '000000', 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, '000000', 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, '000000', 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, '000000', 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, '000000', 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, '000000', 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, '000000', 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, '000000', 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, '000000', 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, '000000', 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, '000000', 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, '000000', 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, '000000', 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, '000000', 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, '000000', 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, '000000', 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, '000000', 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (30, '000000', 0, '密码认证', 'password', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '密码认证');
INSERT INTO `sys_dict_data` VALUES (31, '000000', 0, '短信认证', 'sms', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '短信认证');
INSERT INTO `sys_dict_data` VALUES (32, '000000', 0, '邮件认证', 'email', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '邮件认证');
INSERT INTO `sys_dict_data` VALUES (33, '000000', 0, '小程序认证', 'xcx', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '小程序认证');
INSERT INTO `sys_dict_data` VALUES (34, '000000', 0, '三方登录认证', 'social', 'sys_grant_type', 'el-check-tag', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '三方登录认证');
INSERT INTO `sys_dict_data` VALUES (35, '000000', 0, 'PC', 'pc', 'sys_device_type', '', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, 'PC');
INSERT INTO `sys_dict_data` VALUES (36, '000000', 0, '安卓', 'android', 'sys_device_type', '', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '安卓');
INSERT INTO `sys_dict_data` VALUES (37, '000000', 0, 'iOS', 'ios', 'sys_device_type', '', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, 'iOS');
INSERT INTO `sys_dict_data` VALUES (38, '000000', 0, '小程序', 'xcx', 'sys_device_type', '', 'default', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '小程序');
INSERT INTO `sys_dict_data` VALUES (39, '000000', 1, '已撤销', 'cancel', 'wf_business_status', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '已撤销');
INSERT INTO `sys_dict_data` VALUES (40, '000000', 2, '草稿', 'draft', 'wf_business_status', '', 'info', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '草稿');
INSERT INTO `sys_dict_data` VALUES (41, '000000', 3, '待审核', 'waiting', 'wf_business_status', '', 'primary', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '待审核');
INSERT INTO `sys_dict_data` VALUES (42, '000000', 4, '已完成', 'finish', 'wf_business_status', '', 'success', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '已完成');
INSERT INTO `sys_dict_data` VALUES (43, '000000', 5, '已作废', 'invalid', 'wf_business_status', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '已作废');
INSERT INTO `sys_dict_data` VALUES (44, '000000', 6, '已退回', 'back', 'wf_business_status', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '已退回');
INSERT INTO `sys_dict_data` VALUES (45, '000000', 7, '已终止', 'termination', 'wf_business_status', '', 'danger', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '已终止');
INSERT INTO `sys_dict_data` VALUES (46, '000000', 1, '自定义表单', 'static', 'wf_form_type', '', 'success', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '自定义表单');
INSERT INTO `sys_dict_data` VALUES (47, '000000', 2, '动态表单', 'dynamic', 'wf_form_type', '', 'primary', 'N', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '动态表单');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL COMMENT '字典主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `tenant_id`(`tenant_id`, `dict_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '000000', '用户性别', 'sys_user_sex', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '000000', '菜单状态', 'sys_show_hide', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '000000', '系统开关', 'sys_normal_disable', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (6, '000000', '系统是否', 'sys_yes_no', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '000000', '通知类型', 'sys_notice_type', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '000000', '通知状态', 'sys_notice_status', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '000000', '操作类型', 'sys_oper_type', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '000000', '系统状态', 'sys_common_status', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '000000', '授权类型', 'sys_grant_type', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '认证授权类型');
INSERT INTO `sys_dict_type` VALUES (12, '000000', '设备类型', 'sys_device_type', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '客户端设备类型');
INSERT INTO `sys_dict_type` VALUES (13, '000000', '业务状态', 'wf_business_status', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '业务状态列表');
INSERT INTO `sys_dict_type` VALUES (14, '000000', '表单类型', 'wf_form_type', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '表单类型列表');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL COMMENT '访问ID',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `client_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '客户端',
  `device_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '设备类型',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1876570074532487169, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '密码输入错误1次', '2025-01-07 18:02:25');
INSERT INTO `sys_logininfor` VALUES (1876570112834871297, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '密码输入错误2次', '2025-01-07 18:02:35');
INSERT INTO `sys_logininfor` VALUES (1876570147601457153, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码错误', '2025-01-07 18:02:43');
INSERT INTO `sys_logininfor` VALUES (1876570168010940417, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码错误', '2025-01-07 18:02:48');
INSERT INTO `sys_logininfor` VALUES (1876570182569369602, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '密码输入错误3次', '2025-01-07 18:02:51');
INSERT INTO `sys_logininfor` VALUES (1876570263339081730, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '密码输入错误1次', '2025-01-07 18:03:10');
INSERT INTO `sys_logininfor` VALUES (1876570303583428609, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '密码输入错误2次', '2025-01-07 18:03:20');
INSERT INTO `sys_logininfor` VALUES (1876570632903401473, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-07 18:04:39');
INSERT INTO `sys_logininfor` VALUES (1876798368322351105, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-08 09:09:35');
INSERT INTO `sys_logininfor` VALUES (1876900513625346050, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-08 15:55:28');
INSERT INTO `sys_logininfor` VALUES (1876902007103754242, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '退出成功', '2025-01-08 16:01:24');
INSERT INTO `sys_logininfor` VALUES (1876902031074201602, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码错误', '2025-01-08 16:01:30');
INSERT INTO `sys_logininfor` VALUES (1876902049738854402, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码错误', '2025-01-08 16:01:34');
INSERT INTO `sys_logininfor` VALUES (1876902064221786113, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-08 16:01:38');
INSERT INTO `sys_logininfor` VALUES (1876902577986277378, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '退出成功', '2025-01-08 16:03:40');
INSERT INTO `sys_logininfor` VALUES (1876902754977517570, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-08 16:04:23');
INSERT INTO `sys_logininfor` VALUES (1876910791981207554, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '退出成功', '2025-01-08 16:36:19');
INSERT INTO `sys_logininfor` VALUES (1876910804434096130, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码错误', '2025-01-08 16:36:22');
INSERT INTO `sys_logininfor` VALUES (1876910820825436161, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-08 16:36:26');
INSERT INTO `sys_logininfor` VALUES (1876910860360945666, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '退出成功', '2025-01-08 16:36:35');
INSERT INTO `sys_logininfor` VALUES (1876911838267125762, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码已失效', '2025-01-08 16:40:28');
INSERT INTO `sys_logininfor` VALUES (1876911857380569089, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-08 16:40:33');
INSERT INTO `sys_logininfor` VALUES (1877620026557755394, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '1', '验证码错误', '2025-01-10 15:34:33');
INSERT INTO `sys_logininfor` VALUES (1877620047000793090, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-10 15:34:38');
INSERT INTO `sys_logininfor` VALUES (1881630262691909634, '000000', 'admin', 'pc', 'pc', '0:0:0:0:0:0:0:1', '内网IP', 'MSEdge', 'Windows 10 or Windows Server 2016', '0', '登录成功', '2025-01-21 17:09:48');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 3, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 4, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, 'PLUS官网', 0, 5, 'https://gitee.com/dromara/RuoYi-Cloud-Plus', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', 103, 1, '2025-01-06 06:21:20', NULL, NULL, 'RuoYi-Cloud-Plus官网地址');
INSERT INTO `sys_menu` VALUES (5, '测试菜单', 0, 5, 'demo', NULL, '', 1, 0, 'M', '0', '0', '', 'star', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '测试菜单');
INSERT INTO `sys_menu` VALUES (6, '租户管理', 0, 2, 'tenant', NULL, '', 1, 0, 'M', '0', '0', '', 'chart', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '租户管理目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, 'SnailJob控制台', 2, 2, 'http://localhost:8800/snail-job', '', '', 0, 0, 'C', '0', '0', 'monitor:job:list', 'job', 103, 1, '2025-01-06 06:21:20', NULL, NULL, 'SJ定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, 'Sentinel控制台', 2, 3, 'http://localhost:8718', '', '', 0, 0, 'C', '0', '0', 'monitor:sentinel:list', 'sentinel', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '流量控制菜单');
INSERT INTO `sys_menu` VALUES (112, 'Nacos控制台', 2, 4, 'http://localhost:8848/nacos', '', '', 0, 0, 'C', '0', '0', 'monitor:nacos:list', 'nacos', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '服务治理菜单');
INSERT INTO `sys_menu` VALUES (113, 'Admin控制台', 2, 5, 'http://localhost:9100/login', '', '', 0, 0, 'C', '0', '0', 'monitor:server:list', 'server', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (118, '文件管理', 1, 10, 'oss', 'system/oss/index', '', 1, 0, 'C', '0', '0', 'system:oss:list', 'upload', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '文件管理菜单');
INSERT INTO `sys_menu` VALUES (121, '租户管理', 6, 1, 'tenant', 'system/tenant/index', '', 1, 0, 'C', '0', '0', 'system:tenant:list', 'list', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '租户管理菜单');
INSERT INTO `sys_menu` VALUES (122, '租户套餐管理', 6, 2, 'tenantPackage', 'system/tenantPackage/index', '', 1, 0, 'C', '0', '0', 'system:tenantPackage:list', 'form', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '租户套餐管理菜单');
INSERT INTO `sys_menu` VALUES (123, '客户端管理', 1, 11, 'client', 'system/client/index', '', 1, 0, 'C', '0', '0', 'system:client:list', 'international', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '客户端管理菜单');
INSERT INTO `sys_menu` VALUES (124, '缓存监控', 2, 1, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '缓存监控');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '客户端管理查询', 123, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:client:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1062, '客户端管理新增', 123, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:client:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1063, '客户端管理修改', 123, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:client:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1064, '客户端管理删除', 123, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:client:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1065, '客户端管理导出', 123, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:client:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1500, '测试单表', 5, 1, 'demo', 'demo/demo/index', '', 1, 0, 'C', '0', '0', 'demo:demo:list', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '测试单表菜单');
INSERT INTO `sys_menu` VALUES (1501, '测试单表查询', 1500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'demo:demo:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1502, '测试单表新增', 1500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'demo:demo:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1503, '测试单表修改', 1500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'demo:demo:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1504, '测试单表删除', 1500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'demo:demo:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1505, '测试单表导出', 1500, 5, '#', '', '', 1, 0, 'F', '0', '0', 'demo:demo:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1506, '测试树表', 5, 1, 'tree', 'demo/tree/index', '', 1, 0, 'C', '0', '0', 'demo:tree:list', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '测试树表菜单');
INSERT INTO `sys_menu` VALUES (1507, '测试树表查询', 1506, 1, '#', '', '', 1, 0, 'F', '0', '0', 'demo:tree:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1508, '测试树表新增', 1506, 2, '#', '', '', 1, 0, 'F', '0', '0', 'demo:tree:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1509, '测试树表修改', 1506, 3, '#', '', '', 1, 0, 'F', '0', '0', 'demo:tree:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1510, '测试树表删除', 1506, 4, '#', '', '', 1, 0, 'F', '0', '0', 'demo:tree:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1511, '测试树表导出', 1506, 5, '#', '', '', 1, 0, 'F', '0', '0', 'demo:tree:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1600, '文件查询', 118, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1601, '文件上传', 118, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:upload', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1602, '文件下载', 118, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:download', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1603, '文件删除', 118, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:oss:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1606, '租户查询', 121, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenant:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1607, '租户新增', 121, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenant:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1608, '租户修改', 121, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenant:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1609, '租户删除', 121, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenant:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1610, '租户导出', 121, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenant:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1611, '租户套餐查询', 122, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenantPackage:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1612, '租户套餐新增', 122, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenantPackage:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1613, '租户套餐修改', 122, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenantPackage:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1614, '租户套餐删除', 122, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenantPackage:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1615, '租户套餐导出', 122, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:tenantPackage:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1620, '配置列表', 118, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:ossConfig:list', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1621, '配置添加', 118, 6, '#', '', '', 1, 0, 'F', '0', '0', 'system:ossConfig:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1622, '配置编辑', 118, 6, '#', '', '', 1, 0, 'F', '0', '0', 'system:ossConfig:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1623, '配置删除', 118, 6, '#', '', '', 1, 0, 'F', '0', '0', 'system:ossConfig:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11616, '工作流', 0, 6, 'workflow', '', '', 1, 0, 'M', '0', '0', '', 'workflow', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11617, '模型管理', 11616, 2, 'model', 'workflow/model/index', '', 1, 1, 'C', '0', '0', 'workflow:model:list', 'model', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11618, '我的任务', 0, 7, 'task', '', '', 1, 0, 'M', '0', '0', '', 'my-task', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11619, '我的待办', 11618, 2, 'taskWaiting', 'workflow/task/taskWaiting', '', 1, 1, 'C', '0', '0', '', 'waiting', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11620, '流程定义', 11616, 3, 'processDefinition', 'workflow/processDefinition/index', '', 1, 1, 'C', '0', '0', '', 'process-definition', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11621, '流程实例', 11630, 1, 'processInstance', 'workflow/processInstance/index', '', 1, 1, 'C', '0', '0', '', 'tree-table', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11622, '流程分类', 11616, 1, 'category', 'workflow/category/index', '', 1, 0, 'C', '0', '0', 'workflow:category:list', 'category', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11623, '流程分类查询', 11622, 1, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:category:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11624, '流程分类新增', 11622, 2, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:category:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11625, '流程分类修改', 11622, 3, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:category:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11626, '流程分类删除', 11622, 4, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:category:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11627, '流程分类导出', 11622, 5, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:category:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11628, '表单管理', 11616, 5, 'formManage', 'workflow/formManage/index', '', 1, 0, 'C', '0', '0', 'workflow:formManage:list', 'tree-table', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '表单管理菜单');
INSERT INTO `sys_menu` VALUES (11629, '我发起的', 11618, 1, 'myDocument', 'workflow/task/myDocument', '', 1, 1, 'C', '0', '0', '', 'guide', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11630, '流程监控', 11616, 4, 'monitor', '', '', 1, 0, 'M', '0', '0', '', 'monitor', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11631, '待办任务', 11630, 2, 'allTaskWaiting', 'workflow/task/allTaskWaiting', '', 1, 1, 'C', '0', '0', '', 'waiting', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11632, '我的已办', 11618, 3, 'taskFinish', 'workflow/task/taskFinish', '', 1, 1, 'C', '0', '0', '', 'finish', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11633, '我的抄送', 11618, 4, 'taskCopyList', 'workflow/task/taskCopyList', '', 1, 1, 'C', '0', '0', '', 'my-copy', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11638, '请假申请', 5, 1, 'leave', 'workflow/leave/index', '', 1, 0, 'C', '0', '0', 'workflow:leave:list', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '请假申请菜单');
INSERT INTO `sys_menu` VALUES (11639, '请假申请查询', 11638, 1, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:leave:query', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11640, '请假申请新增', 11638, 2, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:leave:add', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11641, '请假申请修改', 11638, 3, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:leave:edit', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11642, '请假申请删除', 11638, 4, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:leave:remove', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11643, '请假申请导出', 11638, 5, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:leave:export', '#', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11644, '表单管理查询', 11628, 1, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:formManage:query', '', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11645, '表单管理新增', 11628, 2, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:formManage:add', '', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11646, '表单管理修改', 11628, 3, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:formManage:edit', '', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11647, '表单管理删除', 11628, 4, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:formManage:remove', '', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (11648, '表单管理导出', 11628, 5, '#', '', '', 1, 0, 'F', '0', '0', 'workflow:formManage:export', 'tree-table', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005189709826, '消息回调记录', 1881634261516177409, 1, 'callbackRecord', 'imcore/callbackRecord/index', NULL, 1, 0, 'C', '0', '0', 'imcore:callbackRecord:list', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '消息回调记录菜单');
INSERT INTO `sys_menu` VALUES (1876818005189709827, '消息回调记录查询', 1876818005189709826, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:callbackRecord:query', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005189709828, '消息回调记录新增', 1876818005189709826, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:callbackRecord:add', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005189709829, '消息回调记录修改', 1876818005189709826, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:callbackRecord:edit', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005189709830, '消息回调记录删除', 1876818005189709826, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:callbackRecord:remove', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005189709831, '消息回调记录导出', 1876818005189709826, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:callbackRecord:export', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005965656066, '频道', 1881634261516177409, 1, 'channel', 'imcore/channel/index', NULL, 1, 0, 'C', '0', '0', 'imcore:channel:list', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '频道菜单');
INSERT INTO `sys_menu` VALUES (1876818005965656067, '频道查询', 1876818005965656066, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channel:query', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005965656068, '频道新增', 1876818005965656066, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channel:add', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005965656069, '频道修改', 1876818005965656066, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channel:edit', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005965656070, '频道删除', 1876818005965656066, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channel:remove', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818005965656071, '频道导出', 1876818005965656066, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channel:export', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006481555458, '频道成员', 1881634261516177409, 1, 'channelMember', 'imcore/channelMember/index', NULL, 1, 0, 'C', '0', '0', 'imcore:channelMember:list', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '频道成员菜单');
INSERT INTO `sys_menu` VALUES (1876818006481555459, '频道成员查询', 1876818006481555458, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channelMember:query', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006481555460, '频道成员新增', 1876818006481555458, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channelMember:add', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006481555461, '频道成员修改', 1876818006481555458, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channelMember:edit', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006481555462, '频道成员删除', 1876818006481555458, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channelMember:remove', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006481555463, '频道成员导出', 1876818006481555458, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:channelMember:export', '#', 103, 1, '2025-01-08 02:32:56', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006934540289, '聊天会话基础', 1881634261516177409, 1, 'conversation', 'imcore/conversation/index', NULL, 1, 0, 'C', '0', '0', 'imcore:conversation:list', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '聊天会话基础菜单');
INSERT INTO `sys_menu` VALUES (1876818006934540290, '聊天会话基础查询', 1876818006934540289, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversation:query', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006934540291, '聊天会话基础新增', 1876818006934540289, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversation:add', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006934540292, '聊天会话基础修改', 1876818006934540289, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversation:edit', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006934540293, '聊天会话基础删除', 1876818006934540289, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversation:remove', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818006934540294, '聊天会话基础导出', 1876818006934540289, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversation:export', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007261696002, '会话成员', 1881634261516177409, 1, 'conversationMember', 'imcore/conversationMember/index', NULL, 1, 0, 'C', '0', '0', 'imcore:conversationMember:list', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '会话成员菜单');
INSERT INTO `sys_menu` VALUES (1876818007261696003, '会话成员查询', 1876818007261696002, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationMember:query', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007261696004, '会话成员新增', 1876818007261696002, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationMember:add', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007261696005, '会话成员修改', 1876818007261696002, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationMember:edit', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007261696006, '会话成员删除', 1876818007261696002, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationMember:remove', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007261696007, '会话成员导出', 1876818007261696002, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationMember:export', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007647571970, '首页对话列', 1881634261516177409, 1, 'conversationRecent', 'imcore/conversationRecent/index', NULL, 1, 0, 'C', '0', '0', 'imcore:conversationRecent:list', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '首页对话列菜单');
INSERT INTO `sys_menu` VALUES (1876818007647571971, '首页对话列查询', 1876818007647571970, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationRecent:query', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007647571972, '首页对话列新增', 1876818007647571970, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationRecent:add', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007647571973, '首页对话列修改', 1876818007647571970, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationRecent:edit', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007647571974, '首页对话列删除', 1876818007647571970, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationRecent:remove', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818007647571975, '首页对话列导出', 1876818007647571970, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationRecent:export', '#', 103, 1, '2025-01-08 02:32:57', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008041836545, '会话序列号', 1881634261516177409, 1, 'conversationSeq', 'imcore/conversationSeq/index', NULL, 1, 0, 'C', '0', '0', 'imcore:conversationSeq:list', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '会话序列号菜单');
INSERT INTO `sys_menu` VALUES (1876818008041836546, '会话序列号查询', 1876818008041836545, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationSeq:query', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008041836547, '会话序列号新增', 1876818008041836545, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationSeq:add', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008041836548, '会话序列号修改', 1876818008041836545, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationSeq:edit', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008041836549, '会话序列号删除', 1876818008041836545, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationSeq:remove', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008041836550, '会话序列号导出', 1876818008041836545, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:conversationSeq:export', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008368992258, '客户端设备', 1881634261516177409, 1, 'device', 'imcore/device/index', NULL, 1, 0, 'C', '0', '0', 'imcore:device:list', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '客户端设备菜单');
INSERT INTO `sys_menu` VALUES (1876818008368992259, '客户端设备查询', 1876818008368992258, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:device:query', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008368992260, '客户端设备新增', 1876818008368992258, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:device:add', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008368992261, '客户端设备修改', 1876818008368992258, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:device:edit', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008368992262, '客户端设备删除', 1876818008368992258, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:device:remove', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008368992263, '客户端设备导出', 1876818008368992258, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:device:export', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008683565058, '设备pts', 1881634261516177409, 1, 'devicePts', 'imcore/devicePts/index', NULL, 1, 0, 'C', '0', '0', 'imcore:devicePts:list', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '设备pts菜单');
INSERT INTO `sys_menu` VALUES (1876818008683565059, '设备pts查询', 1876818008683565058, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:devicePts:query', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008683565060, '设备pts新增', 1876818008683565058, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:devicePts:add', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008683565061, '设备pts修改', 1876818008683565058, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:devicePts:edit', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008683565062, '设备pts删除', 1876818008683565058, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:devicePts:remove', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818008683565063, '设备pts导出', 1876818008683565058, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:devicePts:export', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009014915074, '好友关系', 1881634261516177409, 1, 'friend', 'imcore/friend/index', NULL, 1, 0, 'C', '0', '0', 'imcore:friend:list', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '好友关系菜单');
INSERT INTO `sys_menu` VALUES (1876818009014915075, '好友关系查询', 1876818009014915074, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friend:query', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009014915076, '好友关系新增', 1876818009014915074, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friend:add', '#', 103, 1, '2025-01-08 02:32:58', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009014915077, '好友关系修改', 1876818009014915074, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friend:edit', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009014915078, '好友关系删除', 1876818009014915074, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friend:remove', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009014915079, '好友关系导出', 1876818009014915074, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friend:export', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009400791042, '好友申请', 1881634261516177409, 1, 'friendRequest', 'imcore/friendRequest/index', NULL, 1, 0, 'C', '0', '0', 'imcore:friendRequest:list', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '好友申请菜单');
INSERT INTO `sys_menu` VALUES (1876818009400791043, '好友申请查询', 1876818009400791042, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friendRequest:query', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009400791044, '好友申请新增', 1876818009400791042, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friendRequest:add', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009400791045, '好友申请修改', 1876818009400791042, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friendRequest:edit', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009400791046, '好友申请删除', 1876818009400791042, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friendRequest:remove', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009400791047, '好友申请导出', 1876818009400791042, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:friendRequest:export', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009799249922, '群组', 1881634261516177409, 1, 'group', 'imcore/group/index', NULL, 1, 0, 'C', '0', '0', 'imcore:group:list', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '群组菜单');
INSERT INTO `sys_menu` VALUES (1876818009799249923, '群组查询', 1876818009799249922, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:group:query', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009799249924, '群组新增', 1876818009799249922, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:group:add', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009799249925, '群组修改', 1876818009799249922, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:group:edit', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009799249926, '群组删除', 1876818009799249922, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:group:remove', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818009799249927, '群组导出', 1876818009799249922, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:group:export', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010193514497, '群公告', 1881634261516177409, 1, 'groupAnnouncement', 'imcore/groupAnnouncement/index', NULL, 1, 0, 'C', '0', '0', 'imcore:groupAnnouncement:list', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '群公告菜单');
INSERT INTO `sys_menu` VALUES (1876818010193514498, '群公告查询', 1876818010193514497, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupAnnouncement:query', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010193514499, '群公告新增', 1876818010193514497, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupAnnouncement:add', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010193514500, '群公告修改', 1876818010193514497, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupAnnouncement:edit', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010193514501, '群公告删除', 1876818010193514497, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupAnnouncement:remove', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010193514502, '群公告导出', 1876818010193514497, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupAnnouncement:export', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010524864514, '群成员', 1881634261516177409, 1, 'groupMember', 'imcore/groupMember/index', NULL, 1, 0, 'C', '0', '0', 'imcore:groupMember:list', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '群成员菜单');
INSERT INTO `sys_menu` VALUES (1876818010524864515, '群成员查询', 1876818010524864514, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupMember:query', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010524864516, '群成员新增', 1876818010524864514, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupMember:add', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010524864517, '群成员修改', 1876818010524864514, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupMember:edit', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010524864518, '群成员删除', 1876818010524864514, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupMember:remove', '#', 103, 1, '2025-01-08 02:32:59', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010524864519, '群成员导出', 1876818010524864514, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupMember:export', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010919129089, '群组设置', 1881634261516177409, 1, 'groupSetting', 'imcore/groupSetting/index', NULL, 1, 0, 'C', '0', '0', 'imcore:groupSetting:list', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '群组设置菜单');
INSERT INTO `sys_menu` VALUES (1876818010919129090, '群组设置查询', 1876818010919129089, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupSetting:query', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010919129091, '群组设置新增', 1876818010919129089, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupSetting:add', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010919129092, '群组设置修改', 1876818010919129089, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupSetting:edit', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010919129093, '群组设置删除', 1876818010919129089, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupSetting:remove', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818010919129094, '群组设置导出', 1876818010919129089, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:groupSetting:export', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011305005057, '消息存储', 1881634261516177409, 1, 'message', 'imcore/message/index', NULL, 1, 0, 'C', '0', '0', 'imcore:message:list', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '消息存储菜单');
INSERT INTO `sys_menu` VALUES (1876818011305005058, '消息存储查询', 1876818011305005057, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:message:query', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011305005059, '消息存储新增', 1876818011305005057, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:message:add', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011305005060, '消息存储修改', 1876818011305005057, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:message:edit', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011305005061, '消息存储删除', 1876818011305005057, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:message:remove', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011305005062, '消息存储导出', 1876818011305005057, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:message:export', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011762184193, '消息已读记录', 1881634261516177409, 1, 'msgRead', 'imcore/msgRead/index', NULL, 1, 0, 'C', '0', '0', 'imcore:msgRead:list', '#', 103, 1, '2025-01-08 02:33:00', NULL, NULL, '消息已读记录菜单');
INSERT INTO `sys_menu` VALUES (1876818011762184194, '消息已读记录查询', 1876818011762184193, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRead:query', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011762184195, '消息已读记录新增', 1876818011762184193, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRead:add', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011762184196, '消息已读记录修改', 1876818011762184193, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRead:edit', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011762184197, '消息已读记录删除', 1876818011762184193, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRead:remove', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818011762184198, '消息已读记录导出', 1876818011762184193, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRead:export', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012085145602, '消息撤回记录', 1881634261516177409, 1, 'msgRecall', 'imcore/msgRecall/index', NULL, 1, 0, 'C', '0', '0', 'imcore:msgRecall:list', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '消息撤回记录菜单');
INSERT INTO `sys_menu` VALUES (1876818012085145603, '消息撤回记录查询', 1876818012085145602, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRecall:query', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012085145604, '消息撤回记录新增', 1876818012085145602, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRecall:add', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012085145605, '消息撤回记录修改', 1876818012085145602, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRecall:edit', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012085145606, '消息撤回记录删除', 1876818012085145602, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRecall:remove', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012085145607, '消息撤回记录导出', 1876818012085145602, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgRecall:export', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012475215874, '消息接收', 1881634261516177409, 1, 'msgReceiver', 'imcore/msgReceiver/index', NULL, 1, 0, 'C', '0', '0', 'imcore:msgReceiver:list', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '消息接收菜单');
INSERT INTO `sys_menu` VALUES (1876818012475215875, '消息接收查询', 1876818012475215874, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReceiver:query', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012475215876, '消息接收新增', 1876818012475215874, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReceiver:add', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012475215877, '消息接收修改', 1876818012475215874, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReceiver:edit', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012475215878, '消息接收删除', 1876818012475215874, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReceiver:remove', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012475215879, '消息接收导出', 1876818012475215874, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReceiver:export', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012806565890, '消息引用关系', 1881634261516177409, 1, 'msgReference', 'imcore/msgReference/index', NULL, 1, 0, 'C', '0', '0', 'imcore:msgReference:list', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '消息引用关系菜单');
INSERT INTO `sys_menu` VALUES (1876818012806565891, '消息引用关系查询', 1876818012806565890, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReference:query', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012806565892, '消息引用关系新增', 1876818012806565890, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReference:add', '#', 103, 1, '2025-01-08 02:33:01', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012806565893, '消息引用关系修改', 1876818012806565890, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReference:edit', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012806565894, '消息引用关系删除', 1876818012806565890, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReference:remove', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818012806565895, '消息引用关系导出', 1876818012806565890, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReference:export', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013259550721, '消息引用路径', 1881634261516177409, 1, 'msgReferencePath', 'imcore/msgReferencePath/index', NULL, 1, 0, 'C', '0', '0', 'imcore:msgReferencePath:list', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '消息引用路径菜单');
INSERT INTO `sys_menu` VALUES (1876818013259550722, '消息引用路径查询', 1876818013259550721, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReferencePath:query', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013259550723, '消息引用路径新增', 1876818013259550721, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReferencePath:add', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013259550724, '消息引用路径修改', 1876818013259550721, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReferencePath:edit', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013259550725, '消息引用路径删除', 1876818013259550721, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReferencePath:remove', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013259550726, '消息引用路径导出', 1876818013259550721, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:msgReferencePath:export', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013653815298, '敏感词过滤', 1881634261516177409, 1, 'sensitiveWords', 'imcore/sensitiveWords/index', NULL, 1, 0, 'C', '0', '0', 'imcore:sensitiveWords:list', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '敏感词过滤菜单');
INSERT INTO `sys_menu` VALUES (1876818013653815299, '敏感词过滤查询', 1876818013653815298, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sensitiveWords:query', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013653815300, '敏感词过滤新增', 1876818013653815298, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sensitiveWords:add', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013653815301, '敏感词过滤修改', 1876818013653815298, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sensitiveWords:edit', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013653815302, '敏感词过滤删除', 1876818013653815298, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sensitiveWords:remove', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818013653815303, '敏感词过滤导出', 1876818013653815298, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sensitiveWords:export', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014043885570, '多端同步', 1881634261516177409, 1, 'sync', 'imcore/sync/index', NULL, 1, 0, 'C', '0', '0', 'imcore:sync:list', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '多端同步菜单');
INSERT INTO `sys_menu` VALUES (1876818014043885571, '多端同步查询', 1876818014043885570, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sync:query', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014043885572, '多端同步新增', 1876818014043885570, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sync:add', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014043885573, '多端同步修改', 1876818014043885570, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sync:edit', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014043885574, '多端同步删除', 1876818014043885570, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sync:remove', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014043885575, '多端同步导出', 1876818014043885570, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sync:export', '#', 103, 1, '2025-01-08 02:33:02', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014299738114, '系统会话初始化', 1881634261516177409, 1, 'sysConversationInit', 'imcore/sysConversationInit/index', NULL, 1, 0, 'C', '0', '0', 'imcore:sysConversationInit:list', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '系统会话初始化菜单');
INSERT INTO `sys_menu` VALUES (1876818014299738115, '系统会话初始化查询', 1876818014299738114, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sysConversationInit:query', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014299738116, '系统会话初始化新增', 1876818014299738114, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sysConversationInit:add', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014299738117, '系统会话初始化修改', 1876818014299738114, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sysConversationInit:edit', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014299738118, '系统会话初始化删除', 1876818014299738114, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sysConversationInit:remove', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014299738119, '系统会话初始化导出', 1876818014299738114, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:sysConversationInit:export', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014631088129, '用户', 1881634261516177409, 1, 'user', 'imcore/user/index', NULL, 1, 0, 'C', '0', '0', 'imcore:user:list', '#', 103, 1, '2025-01-08 02:33:03', 1, '2025-01-21 17:26:19', '用户菜单');
INSERT INTO `sys_menu` VALUES (1876818014631088130, '用户查询', 1876818014631088129, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:user:query', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014631088131, '用户新增', 1876818014631088129, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:user:add', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014631088132, '用户修改', 1876818014631088129, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:user:edit', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014631088133, '用户删除', 1876818014631088129, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:user:remove', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818014631088134, '用户导出', 1876818014631088129, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:user:export', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015084072962, '用户pts', 1881634261516177409, 1, 'userPts', 'imcore/userPts/index', NULL, 1, 0, 'C', '0', '0', 'imcore:userPts:list', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '用户pts菜单');
INSERT INTO `sys_menu` VALUES (1876818015084072963, '用户pts查询', 1876818015084072962, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userPts:query', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015084072964, '用户pts新增', 1876818015084072962, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userPts:add', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015084072965, '用户pts修改', 1876818015084072962, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userPts:edit', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015084072966, '用户pts删除', 1876818015084072962, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userPts:remove', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015084072967, '用户pts导出', 1876818015084072962, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userPts:export', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015339925505, '用户状态', 1881634261516177409, 1, 'userStatus', 'imcore/userStatus/index', NULL, 1, 0, 'C', '0', '0', 'imcore:userStatus:list', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '用户状态菜单');
INSERT INTO `sys_menu` VALUES (1876818015339925506, '用户状态查询', 1876818015339925505, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userStatus:query', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015339925507, '用户状态新增', 1876818015339925505, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userStatus:add', '#', 103, 1, '2025-01-08 02:33:03', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015339925508, '用户状态修改', 1876818015339925505, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userStatus:edit', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015339925509, '用户状态删除', 1876818015339925505, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userStatus:remove', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015339925510, '用户状态导出', 1876818015339925505, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:userStatus:export', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015662886914, 'Webhook配置', 1881634261516177409, 1, 'webhookConfig', 'imcore/webhookConfig/index', NULL, 1, 0, 'C', '0', '0', 'imcore:webhookConfig:list', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, 'Webhook配置菜单');
INSERT INTO `sys_menu` VALUES (1876818015662886915, 'Webhook配置查询', 1876818015662886914, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:webhookConfig:query', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015662886916, 'Webhook配置新增', 1876818015662886914, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:webhookConfig:add', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015662886917, 'Webhook配置修改', 1876818015662886914, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:webhookConfig:edit', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015662886918, 'Webhook配置删除', 1876818015662886914, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:webhookConfig:remove', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015662886919, 'Webhook配置导出', 1876818015662886914, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:webhookConfig:export', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015994236930, '工作空间', 1881634261516177409, 1, 'workspace', 'imcore/workspace/index', NULL, 1, 0, 'C', '0', '0', 'imcore:workspace:list', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '工作空间菜单');
INSERT INTO `sys_menu` VALUES (1876818015994236931, '工作空间查询', 1876818015994236930, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:workspace:query', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015994236932, '工作空间新增', 1876818015994236930, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:workspace:add', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015994236933, '工作空间修改', 1876818015994236930, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:workspace:edit', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015994236934, '工作空间删除', 1876818015994236930, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:workspace:remove', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1876818015994236935, '工作空间导出', 1876818015994236930, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'imcore:workspace:export', '#', 103, 1, '2025-01-08 02:33:04', NULL, NULL, '');
INSERT INTO `sys_menu` VALUES (1881634261516177409, 'IM数据管理', 0, 1, 'im', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'message', 103, 1, '2025-01-21 17:25:42', 1, '2025-01-21 17:25:42', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` bigint(20) NOT NULL COMMENT '公告ID',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '000000', '温馨提醒：2018-07-01 新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '000000', '维护通知：2018-07-01 系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 103, 1, '2025-01-06 06:21:21', NULL, NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL COMMENT '日志主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1881634262485061634, '000000', '菜单管理', 1, 'org.dromara.system.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '研发部门', '/menu', '0:0:0:0:0:0:0:1', '', '{\"createDept\":null,\"createBy\":null,\"createTime\":null,\"updateBy\":null,\"updateTime\":null,\"menuId\":null,\"parentId\":0,\"menuName\":\"IM数据管理\",\"orderNum\":1,\"path\":\"im\",\"component\":null,\"queryParam\":null,\"isFrame\":\"1\",\"isCache\":\"0\",\"menuType\":\"M\",\"visible\":\"0\",\"status\":\"0\",\"icon\":\"message\",\"remark\":null}', '{\"code\":200,\"msg\":\"操作成功\",\"data\":null}', 0, '', '2025-01-21 17:25:42', 277);
INSERT INTO `sys_oper_log` VALUES (1881634420467716097, '000000', '菜单管理', 2, 'org.dromara.system.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', '研发部门', '/menu', '0:0:0:0:0:0:0:1', '', '{\"createDept\":103,\"createBy\":null,\"createTime\":\"2025-01-08 02:33:03\",\"updateBy\":null,\"updateTime\":null,\"menuId\":\"1876818014631088129\",\"parentId\":\"1881634261516177409\",\"menuName\":\"用户\",\"orderNum\":1,\"path\":\"user\",\"component\":\"imcore/user/index\",\"queryParam\":null,\"isFrame\":\"1\",\"isCache\":\"0\",\"menuType\":\"C\",\"visible\":\"0\",\"status\":\"0\",\"perms\":\"imcore:user:list\",\"icon\":\"#\",\"remark\":\"用户菜单\"}', '{\"code\":200,\"msg\":\"操作成功\",\"data\":null}', 0, '', '2025-01-21 17:26:20', 172);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `oss_id` bigint(20) NOT NULL COMMENT '对象存储主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '原名',
  `file_suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'URL地址',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '上传人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `service` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'minio' COMMENT '服务商',
  PRIMARY KEY (`oss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'OSS对象存储表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`  (
  `oss_config_id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `config_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置key',
  `access_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'accessKey',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '秘钥',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '桶名称',
  `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '前缀',
  `endpoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '访问站点',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '自定义域名',
  `is_https` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '域',
  `access_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否默认（0=是,1=否）',
  `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '扩展字段',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oss_config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '对象存储配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss_config
-- ----------------------------
INSERT INTO `sys_oss_config` VALUES (1, '000000', 'minio', 'ruoyi', 'ruoyi123', 'ruoyi', '', '127.0.0.1:9000', '', 'N', '', '1', '0', '', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21', NULL);
INSERT INTO `sys_oss_config` VALUES (2, '000000', 'qiniu', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 's3-cn-north-1.qiniucs.com', '', 'N', '', '1', '1', '', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21', NULL);
INSERT INTO `sys_oss_config` VALUES (3, '000000', 'aliyun', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 'oss-cn-beijing.aliyuncs.com', '', 'N', '', '1', '1', '', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21', NULL);
INSERT INTO `sys_oss_config` VALUES (4, '000000', 'qcloud', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi-1250000000', '', 'cos.ap-beijing.myqcloud.com', '', 'N', 'ap-beijing', '1', '1', '', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21', NULL);
INSERT INTO `sys_oss_config` VALUES (5, '000000', 'image', 'ruoyi', 'ruoyi123', 'ruoyi', 'image', '127.0.0.1:9000', '', 'N', '', '1', '1', '', 103, 1, '2025-01-06 06:21:21', 1, '2025-01-06 06:21:21', NULL);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位类别编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, '000000', 103, 'ceo', NULL, '董事长', 1, '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_post` VALUES (2, '000000', 100, 'se', NULL, '项目经理', 2, '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_post` VALUES (3, '000000', 100, 'hr', NULL, '人力资源', 3, '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_post` VALUES (4, '000000', 100, 'user', NULL, '普通员工', 4, '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '000000', '超级管理员', 'superadmin', 1, '1', 1, 1, '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (3, '000000', '本部门及以下', 'test1', 3, '4', 1, 1, '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_role` VALUES (4, '000000', '仅本人', 'test2', 4, '5', 1, 1, '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 101);
INSERT INTO `sys_role_menu` VALUES (3, 102);
INSERT INTO `sys_role_menu` VALUES (3, 103);
INSERT INTO `sys_role_menu` VALUES (3, 104);
INSERT INTO `sys_role_menu` VALUES (3, 105);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 500);
INSERT INTO `sys_role_menu` VALUES (3, 501);
INSERT INTO `sys_role_menu` VALUES (3, 1001);
INSERT INTO `sys_role_menu` VALUES (3, 1002);
INSERT INTO `sys_role_menu` VALUES (3, 1003);
INSERT INTO `sys_role_menu` VALUES (3, 1004);
INSERT INTO `sys_role_menu` VALUES (3, 1005);
INSERT INTO `sys_role_menu` VALUES (3, 1006);
INSERT INTO `sys_role_menu` VALUES (3, 1007);
INSERT INTO `sys_role_menu` VALUES (3, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 1009);
INSERT INTO `sys_role_menu` VALUES (3, 1010);
INSERT INTO `sys_role_menu` VALUES (3, 1011);
INSERT INTO `sys_role_menu` VALUES (3, 1012);
INSERT INTO `sys_role_menu` VALUES (3, 1013);
INSERT INTO `sys_role_menu` VALUES (3, 1014);
INSERT INTO `sys_role_menu` VALUES (3, 1015);
INSERT INTO `sys_role_menu` VALUES (3, 1016);
INSERT INTO `sys_role_menu` VALUES (3, 1017);
INSERT INTO `sys_role_menu` VALUES (3, 1018);
INSERT INTO `sys_role_menu` VALUES (3, 1019);
INSERT INTO `sys_role_menu` VALUES (3, 1020);
INSERT INTO `sys_role_menu` VALUES (3, 1021);
INSERT INTO `sys_role_menu` VALUES (3, 1022);
INSERT INTO `sys_role_menu` VALUES (3, 1023);
INSERT INTO `sys_role_menu` VALUES (3, 1024);
INSERT INTO `sys_role_menu` VALUES (3, 1025);
INSERT INTO `sys_role_menu` VALUES (3, 1026);
INSERT INTO `sys_role_menu` VALUES (3, 1027);
INSERT INTO `sys_role_menu` VALUES (3, 1028);
INSERT INTO `sys_role_menu` VALUES (3, 1029);
INSERT INTO `sys_role_menu` VALUES (3, 1030);
INSERT INTO `sys_role_menu` VALUES (3, 1031);
INSERT INTO `sys_role_menu` VALUES (3, 1032);
INSERT INTO `sys_role_menu` VALUES (3, 1033);
INSERT INTO `sys_role_menu` VALUES (3, 1034);
INSERT INTO `sys_role_menu` VALUES (3, 1035);
INSERT INTO `sys_role_menu` VALUES (3, 1036);
INSERT INTO `sys_role_menu` VALUES (3, 1037);
INSERT INTO `sys_role_menu` VALUES (3, 1038);
INSERT INTO `sys_role_menu` VALUES (3, 1039);
INSERT INTO `sys_role_menu` VALUES (3, 1040);
INSERT INTO `sys_role_menu` VALUES (3, 1041);
INSERT INTO `sys_role_menu` VALUES (3, 1042);
INSERT INTO `sys_role_menu` VALUES (3, 1043);
INSERT INTO `sys_role_menu` VALUES (3, 1044);
INSERT INTO `sys_role_menu` VALUES (3, 1045);
INSERT INTO `sys_role_menu` VALUES (3, 1500);
INSERT INTO `sys_role_menu` VALUES (3, 1501);
INSERT INTO `sys_role_menu` VALUES (3, 1502);
INSERT INTO `sys_role_menu` VALUES (3, 1503);
INSERT INTO `sys_role_menu` VALUES (3, 1504);
INSERT INTO `sys_role_menu` VALUES (3, 1505);
INSERT INTO `sys_role_menu` VALUES (3, 1506);
INSERT INTO `sys_role_menu` VALUES (3, 1507);
INSERT INTO `sys_role_menu` VALUES (3, 1508);
INSERT INTO `sys_role_menu` VALUES (3, 1509);
INSERT INTO `sys_role_menu` VALUES (3, 1510);
INSERT INTO `sys_role_menu` VALUES (3, 1511);
INSERT INTO `sys_role_menu` VALUES (4, 5);
INSERT INTO `sys_role_menu` VALUES (4, 1500);
INSERT INTO `sys_role_menu` VALUES (4, 1501);
INSERT INTO `sys_role_menu` VALUES (4, 1502);
INSERT INTO `sys_role_menu` VALUES (4, 1503);
INSERT INTO `sys_role_menu` VALUES (4, 1504);
INSERT INTO `sys_role_menu` VALUES (4, 1505);
INSERT INTO `sys_role_menu` VALUES (4, 1506);
INSERT INTO `sys_role_menu` VALUES (4, 1507);
INSERT INTO `sys_role_menu` VALUES (4, 1508);
INSERT INTO `sys_role_menu` VALUES (4, 1509);
INSERT INTO `sys_role_menu` VALUES (4, 1510);
INSERT INTO `sys_role_menu` VALUES (4, 1511);

-- ----------------------------
-- Table structure for sys_social
-- ----------------------------
DROP TABLE IF EXISTS `sys_social`;
CREATE TABLE `sys_social`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
  `auth_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '平台+平台唯一id',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户来源',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台编号唯一id',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户的授权令牌',
  `expire_in` int(11) NULL DEFAULT NULL COMMENT '用户的授权令牌的有效期，部分平台可能没有',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '刷新令牌，部分平台可能没有',
  `access_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台的授权信息，部分平台可能没有',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户的 unionid',
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授予的权限，部分平台可能没有',
  `token_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个别平台的授权信息，部分平台可能没有',
  `id_token` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'id token，部分平台可能没有',
  `mac_algorithm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
  `mac_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小米平台用户的附带属性，部分平台可能没有',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户的授权code，部分平台可能没有',
  `oauth_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
  `oauth_token_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Twitter平台用户的附带属性，部分平台可能没有',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '社会化关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_social
-- ----------------------------

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编号',
  `contact_user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `company_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `license_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `intro` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '企业简介',
  `domain` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '域名',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `package_id` bigint(20) NULL DEFAULT NULL COMMENT '租户套餐编号',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `account_count` int(11) NULL DEFAULT -1 COMMENT '用户数量（-1不限制）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '租户状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES (1, '000000', '管理组', '15888888888', 'XXX有限公司', NULL, NULL, '多租户通用后台管理管理系统', NULL, NULL, NULL, NULL, -1, '0', '0', 103, 1, '2025-01-06 06:21:20', NULL, NULL);

-- ----------------------------
-- Table structure for sys_tenant_package
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant_package`;
CREATE TABLE `sys_tenant_package`  (
  `package_id` bigint(20) NOT NULL COMMENT '租户套餐id',
  `package_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `menu_ids` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联菜单id',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`package_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户套餐表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenant_package
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` bigint(20) NULL DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '000000', 103, 'admin', '超管', 'sys_user', 'crazyLionLi@163.com', '15888888888', '0', NULL, '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '0:0:0:0:0:0:0:1', '2025-01-21 17:09:48', 103, 1, '2025-01-06 06:21:20', 1, '2025-01-21 17:09:48', '管理员');
INSERT INTO `sys_user` VALUES (3, '000000', 108, 'test', '本部门及以下 密码666666', 'sys_user', '', '', '0', NULL, '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2025-01-06 06:21:20', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');
INSERT INTO `sys_user` VALUES (4, '000000', 102, 'test1', '仅本人 密码666666', 'sys_user', '', '', '0', NULL, '$2a$10$b8yUzN0C71sbz.PhNOCgJe.Tu1yWC3RNrTyjSQ8p1W0.aaUXUJ.Ne', '0', '0', '127.0.0.1', '2025-01-06 06:21:20', 103, 1, '2025-01-06 06:21:20', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (4, 4);

-- ----------------------------
-- Table structure for test_demo
-- ----------------------------
DROP TABLE IF EXISTS `test_demo`;
CREATE TABLE `test_demo`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '排序号',
  `test_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key键',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_demo
-- ----------------------------
INSERT INTO `test_demo` VALUES (1, '000000', 102, 4, 1, '测试数据权限', '测试', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (2, '000000', 102, 3, 2, '子节点1', '111', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (3, '000000', 102, 3, 3, '子节点2', '222', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (4, '000000', 108, 4, 4, '测试数据', 'demo', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (5, '000000', 108, 3, 13, '子节点11', '1111', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (6, '000000', 108, 3, 12, '子节点22', '2222', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (7, '000000', 108, 3, 11, '子节点33', '3333', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (8, '000000', 108, 3, 10, '子节点44', '4444', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (9, '000000', 108, 3, 9, '子节点55', '5555', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (10, '000000', 108, 3, 8, '子节点66', '6666', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (11, '000000', 108, 3, 7, '子节点77', '7777', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (12, '000000', 108, 3, 6, '子节点88', '8888', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_demo` VALUES (13, '000000', 108, 3, 5, '子节点99', '9999', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for test_tree
-- ----------------------------
DROP TABLE IF EXISTS `test_tree`;
CREATE TABLE `test_tree`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父id',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `tree_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '值',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本',
  `create_dept` bigint(20) NULL DEFAULT NULL COMMENT '创建部门',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `del_flag` int(11) NULL DEFAULT 0 COMMENT '删除标志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '测试树表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_tree
-- ----------------------------
INSERT INTO `test_tree` VALUES (1, '000000', 0, 102, 4, '测试数据权限', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (2, '000000', 1, 102, 3, '子节点1', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (3, '000000', 2, 102, 3, '子节点2', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (4, '000000', 0, 108, 4, '测试树1', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (5, '000000', 4, 108, 3, '子节点11', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (6, '000000', 4, 108, 3, '子节点22', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (7, '000000', 4, 108, 3, '子节点33', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (8, '000000', 5, 108, 3, '子节点44', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (9, '000000', 6, 108, 3, '子节点55', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (10, '000000', 7, 108, 3, '子节点66', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (11, '000000', 7, 108, 3, '子节点77', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (12, '000000', 10, 108, 3, '子节点88', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);
INSERT INTO `test_tree` VALUES (13, '000000', 10, 108, 3, '子节点99', 0, 103, '2025-01-06 06:21:21', 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
