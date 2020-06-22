/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : ry

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-05-09 19:29:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(200) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`,`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `cron_expression` varchar(200) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', '0/20 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_group` varchar(200) DEFAULT NULL,
  `is_nonconcurrent` varchar(1) DEFAULT NULL,
  `requests_recovery` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `job_class_name` varchar(250) NOT NULL,
  `is_durable` varchar(1) NOT NULL,
  `is_nonconcurrent` varchar(1) NOT NULL,
  `is_update_data` varchar(1) NOT NULL,
  `requests_recovery` varchar(1) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', null, 'com.ruoyi.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720028636F6D2E72756F79692E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720029636F6D2E72756F79692E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3130202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E697A0E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672795461736B74000A72794E6F506172616D7374000074000133740001317800);
INSERT INTO `qrtz_job_details` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', null, 'com.ruoyi.project.monitor.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720028636F6D2E72756F79692E70726F6A6563742E6D6F6E69746F722E6A6F622E646F6D61696E2E4A6F6200000000000000010200094C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C000C6D6574686F64506172616D7371007E00094C000D6D697366697265506F6C69637971007E00094C000673746174757371007E000978720029636F6D2E72756F79692E6672616D65776F726B2E7765622E646F6D61696E2E42617365456E7469747900000000000000010200074C0008637265617465427971007E00094C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C0006706172616D7371007E00034C000672656D61726B71007E00094C000B73656172636856616C756571007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787074000561646D696E7372000E6A6176612E7574696C2E44617465686A81014B59741903000078707708000001622CDE29E078707400007070707400013174000E302F3230202A202A202A202A203F740018E7B3BBE7BB9FE9BB98E8AEA4EFBC88E69C89E58F82EFBC897372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000274000672795461736B7400087279506172616D73740002727974000133740001317800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(200) NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RuoyiScheduler', 'PC-2019021912391557401272623', '1557401366900', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `str_prop_1` varchar(512) DEFAULT NULL,
  `str_prop_2` varchar(512) DEFAULT NULL,
  `str_prop_3` varchar(512) DEFAULT NULL,
  `int_prop_1` int(11) DEFAULT NULL,
  `int_prop_2` int(11) DEFAULT NULL,
  `long_prop_1` bigint(20) DEFAULT NULL,
  `long_prop_2` bigint(20) DEFAULT NULL,
  `dec_prop_1` decimal(13,4) DEFAULT NULL,
  `dec_prop_2` decimal(13,4) DEFAULT NULL,
  `bool_prop_1` varchar(1) DEFAULT NULL,
  `bool_prop_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(200) NOT NULL,
  `trigger_group` varchar(200) NOT NULL,
  `job_name` varchar(200) NOT NULL,
  `job_group` varchar(200) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `next_fire_time` bigint(13) DEFAULT NULL,
  `prev_fire_time` bigint(13) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) DEFAULT NULL,
  `calendar_name` varchar(200) DEFAULT NULL,
  `misfire_instr` smallint(2) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME1', 'DEFAULT', 'TASK_CLASS_NAME1', 'DEFAULT', null, '1557401280000', '-1', '5', 'PAUSED', 'CRON', '1557401273000', '0', null, '2', '');
INSERT INTO `qrtz_triggers` VALUES ('RuoyiScheduler', 'TASK_CLASS_NAME2', 'DEFAULT', 'TASK_CLASS_NAME2', 'DEFAULT', null, '1557401280000', '-1', '5', 'PAUSED', 'CRON', '1557401273000', '0', null, '2', '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES ('2', '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('100', '0', '0', '若依科技', '0', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('101', '100', '0,100', '深圳总公司', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('102', '100', '0,100', '长沙分公司', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('103', '101', '0,100,101', '研发部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('104', '101', '0,100,101', '市场部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('105', '101', '0,100,101', '测试部门', '3', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('106', '101', '0,100,101', '财务部门', '4', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('107', '101', '0,100,101', '运维部门', '5', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('108', '102', '0,100,102', '市场部门', '1', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO `sys_dept` VALUES ('109', '102', '0,100,102', '财务部门', '2', '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '1', '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别男');
INSERT INTO `sys_dict_data` VALUES ('2', '2', '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别女');
INSERT INTO `sys_dict_data` VALUES ('3', '3', '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '性别未知');
INSERT INTO `sys_dict_data` VALUES ('4', '1', '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '显示菜单');
INSERT INTO `sys_dict_data` VALUES ('5', '2', '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES ('6', '1', '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('7', '2', '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('8', '1', '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('9', '2', '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');
INSERT INTO `sys_dict_data` VALUES ('10', '1', '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认是');
INSERT INTO `sys_dict_data` VALUES ('11', '2', '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统默认否');
INSERT INTO `sys_dict_data` VALUES ('12', '1', '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知');
INSERT INTO `sys_dict_data` VALUES ('13', '2', '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '公告');
INSERT INTO `sys_dict_data` VALUES ('14', '1', '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('15', '2', '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '关闭状态');
INSERT INTO `sys_dict_data` VALUES ('16', '1', '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '新增操作');
INSERT INTO `sys_dict_data` VALUES ('17', '2', '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '修改操作');
INSERT INTO `sys_dict_data` VALUES ('18', '3', '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '删除操作');
INSERT INTO `sys_dict_data` VALUES ('19', '4', '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '授权操作');
INSERT INTO `sys_dict_data` VALUES ('20', '5', '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导出操作');
INSERT INTO `sys_dict_data` VALUES ('21', '6', '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '导入操作');
INSERT INTO `sys_dict_data` VALUES ('22', '7', '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '强退操作');
INSERT INTO `sys_dict_data` VALUES ('23', '8', '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '生成操作');
INSERT INTO `sys_dict_data` VALUES ('24', '9', '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '清空操作');
INSERT INTO `sys_dict_data` VALUES ('25', '1', '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '正常状态');
INSERT INTO `sys_dict_data` VALUES ('26', '2', '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('1', '用户性别', 'sys_user_sex', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES ('2', '菜单状态', 'sys_show_hide', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES ('3', '系统开关', 'sys_normal_disable', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统开关列表');
INSERT INTO `sys_dict_type` VALUES ('4', '任务状态', 'sys_job_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '任务状态列表');
INSERT INTO `sys_dict_type` VALUES ('5', '系统是否', 'sys_yes_no', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统是否列表');
INSERT INTO `sys_dict_type` VALUES ('6', '通知类型', 'sys_notice_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知类型列表');
INSERT INTO `sys_dict_type` VALUES ('7', '通知状态', 'sys_notice_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知状态列表');
INSERT INTO `sys_dict_type` VALUES ('8', '操作类型', 'sys_oper_type', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作类型列表');
INSERT INTO `sys_dict_type` VALUES ('9', '系统状态', 'sys_common_status', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL DEFAULT '' COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT '' COMMENT '任务方法',
  `method_params` varchar(50) DEFAULT NULL COMMENT '方法参数',
  `cron_expression` varchar(255) DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务调度表';

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1', 'ryTask', '系统默认（无参）', 'ryNoParams', '', '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-06 19:36:42', '');
INSERT INTO `sys_job` VALUES ('2', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11:33:00', 'admin', '2019-05-06 19:36:44', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) NOT NULL COMMENT '任务组名',
  `method_name` varchar(500) DEFAULT NULL COMMENT '任务方法',
  `method_params` varchar(50) DEFAULT NULL COMMENT '方法参数',
  `job_message` varchar(500) DEFAULT NULL COMMENT '日志信息',
  `status` char(1) DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=830 DEFAULT CHARSET=utf8 COMMENT='定时任务调度日志表';

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES ('1', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:24:27');
INSERT INTO `sys_job_log` VALUES ('2', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:25:00');
INSERT INTO `sys_job_log` VALUES ('3', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：3毫秒', '0', '', '2019-05-06 17:25:20');
INSERT INTO `sys_job_log` VALUES ('4', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:25:40');
INSERT INTO `sys_job_log` VALUES ('5', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:26:00');
INSERT INTO `sys_job_log` VALUES ('6', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:26:20');
INSERT INTO `sys_job_log` VALUES ('7', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:26:40');
INSERT INTO `sys_job_log` VALUES ('8', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:48:22');
INSERT INTO `sys_job_log` VALUES ('9', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:48:22');
INSERT INTO `sys_job_log` VALUES ('10', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:49:10');
INSERT INTO `sys_job_log` VALUES ('11', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:49:20');
INSERT INTO `sys_job_log` VALUES ('12', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:49:20');
INSERT INTO `sys_job_log` VALUES ('13', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:49:30');
INSERT INTO `sys_job_log` VALUES ('14', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:49:40');
INSERT INTO `sys_job_log` VALUES ('15', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:49:40');
INSERT INTO `sys_job_log` VALUES ('16', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:49:50');
INSERT INTO `sys_job_log` VALUES ('17', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:49:58');
INSERT INTO `sys_job_log` VALUES ('18', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:00');
INSERT INTO `sys_job_log` VALUES ('19', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:50:00');
INSERT INTO `sys_job_log` VALUES ('20', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:02');
INSERT INTO `sys_job_log` VALUES ('21', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:04');
INSERT INTO `sys_job_log` VALUES ('22', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:07');
INSERT INTO `sys_job_log` VALUES ('23', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:10');
INSERT INTO `sys_job_log` VALUES ('24', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:50:20');
INSERT INTO `sys_job_log` VALUES ('25', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:20');
INSERT INTO `sys_job_log` VALUES ('26', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:28');
INSERT INTO `sys_job_log` VALUES ('27', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:30');
INSERT INTO `sys_job_log` VALUES ('28', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:30');
INSERT INTO `sys_job_log` VALUES ('29', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:50:40');
INSERT INTO `sys_job_log` VALUES ('30', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:00');
INSERT INTO `sys_job_log` VALUES ('31', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:07');
INSERT INTO `sys_job_log` VALUES ('32', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:14');
INSERT INTO `sys_job_log` VALUES ('33', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:16');
INSERT INTO `sys_job_log` VALUES ('34', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:20');
INSERT INTO `sys_job_log` VALUES ('35', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:21');
INSERT INTO `sys_job_log` VALUES ('36', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:21');
INSERT INTO `sys_job_log` VALUES ('37', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:30');
INSERT INTO `sys_job_log` VALUES ('38', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:30');
INSERT INTO `sys_job_log` VALUES ('39', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:33');
INSERT INTO `sys_job_log` VALUES ('40', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:40');
INSERT INTO `sys_job_log` VALUES ('41', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:51:40');
INSERT INTO `sys_job_log` VALUES ('42', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:51:50');
INSERT INTO `sys_job_log` VALUES ('43', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:52:00');
INSERT INTO `sys_job_log` VALUES ('44', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:52:00');
INSERT INTO `sys_job_log` VALUES ('45', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:52:10');
INSERT INTO `sys_job_log` VALUES ('46', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:52:20');
INSERT INTO `sys_job_log` VALUES ('47', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:52:20');
INSERT INTO `sys_job_log` VALUES ('48', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:52:30');
INSERT INTO `sys_job_log` VALUES ('49', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:52:43');
INSERT INTO `sys_job_log` VALUES ('50', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:52:50');
INSERT INTO `sys_job_log` VALUES ('51', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:53:00');
INSERT INTO `sys_job_log` VALUES ('52', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:53:00');
INSERT INTO `sys_job_log` VALUES ('53', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:53:53');
INSERT INTO `sys_job_log` VALUES ('54', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:00');
INSERT INTO `sys_job_log` VALUES ('55', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:00');
INSERT INTO `sys_job_log` VALUES ('56', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:10');
INSERT INTO `sys_job_log` VALUES ('57', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:20');
INSERT INTO `sys_job_log` VALUES ('58', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:20');
INSERT INTO `sys_job_log` VALUES ('59', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:54:30');
INSERT INTO `sys_job_log` VALUES ('60', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:40');
INSERT INTO `sys_job_log` VALUES ('61', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:54:40');
INSERT INTO `sys_job_log` VALUES ('62', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:54:50');
INSERT INTO `sys_job_log` VALUES ('63', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:00');
INSERT INTO `sys_job_log` VALUES ('64', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:00');
INSERT INTO `sys_job_log` VALUES ('65', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:10');
INSERT INTO `sys_job_log` VALUES ('66', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:20');
INSERT INTO `sys_job_log` VALUES ('67', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:20');
INSERT INTO `sys_job_log` VALUES ('68', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:55:30');
INSERT INTO `sys_job_log` VALUES ('69', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:40');
INSERT INTO `sys_job_log` VALUES ('70', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:40');
INSERT INTO `sys_job_log` VALUES ('71', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:55:50');
INSERT INTO `sys_job_log` VALUES ('72', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:56:00');
INSERT INTO `sys_job_log` VALUES ('73', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:56:00');
INSERT INTO `sys_job_log` VALUES ('74', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:56:10');
INSERT INTO `sys_job_log` VALUES ('75', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:56:20');
INSERT INTO `sys_job_log` VALUES ('76', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:56:20');
INSERT INTO `sys_job_log` VALUES ('77', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:56:30');
INSERT INTO `sys_job_log` VALUES ('78', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:56:40');
INSERT INTO `sys_job_log` VALUES ('79', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：4毫秒', '0', '', '2019-05-06 17:56:40');
INSERT INTO `sys_job_log` VALUES ('80', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:56:50');
INSERT INTO `sys_job_log` VALUES ('81', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:00');
INSERT INTO `sys_job_log` VALUES ('82', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:00');
INSERT INTO `sys_job_log` VALUES ('83', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:10');
INSERT INTO `sys_job_log` VALUES ('84', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:20');
INSERT INTO `sys_job_log` VALUES ('85', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:20');
INSERT INTO `sys_job_log` VALUES ('86', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:30');
INSERT INTO `sys_job_log` VALUES ('87', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:57:40');
INSERT INTO `sys_job_log` VALUES ('88', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:57:40');
INSERT INTO `sys_job_log` VALUES ('89', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:57:50');
INSERT INTO `sys_job_log` VALUES ('90', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:58:00');
INSERT INTO `sys_job_log` VALUES ('91', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:58:00');
INSERT INTO `sys_job_log` VALUES ('92', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:58:10');
INSERT INTO `sys_job_log` VALUES ('93', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:58:20');
INSERT INTO `sys_job_log` VALUES ('94', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:58:20');
INSERT INTO `sys_job_log` VALUES ('95', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:58:30');
INSERT INTO `sys_job_log` VALUES ('96', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:58:40');
INSERT INTO `sys_job_log` VALUES ('97', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:58:40');
INSERT INTO `sys_job_log` VALUES ('98', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:58:50');
INSERT INTO `sys_job_log` VALUES ('99', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:59:00');
INSERT INTO `sys_job_log` VALUES ('100', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 17:59:00');
INSERT INTO `sys_job_log` VALUES ('101', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:59:10');
INSERT INTO `sys_job_log` VALUES ('102', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:59:20');
INSERT INTO `sys_job_log` VALUES ('103', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:59:20');
INSERT INTO `sys_job_log` VALUES ('104', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:59:30');
INSERT INTO `sys_job_log` VALUES ('105', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 17:59:40');
INSERT INTO `sys_job_log` VALUES ('106', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:59:40');
INSERT INTO `sys_job_log` VALUES ('107', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 17:59:50');
INSERT INTO `sys_job_log` VALUES ('108', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:00');
INSERT INTO `sys_job_log` VALUES ('109', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:00');
INSERT INTO `sys_job_log` VALUES ('110', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:10');
INSERT INTO `sys_job_log` VALUES ('111', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:20');
INSERT INTO `sys_job_log` VALUES ('112', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:00:20');
INSERT INTO `sys_job_log` VALUES ('113', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:30');
INSERT INTO `sys_job_log` VALUES ('114', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:40');
INSERT INTO `sys_job_log` VALUES ('115', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:40');
INSERT INTO `sys_job_log` VALUES ('116', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:00:50');
INSERT INTO `sys_job_log` VALUES ('117', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:01:00');
INSERT INTO `sys_job_log` VALUES ('118', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:01:00');
INSERT INTO `sys_job_log` VALUES ('119', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:01:10');
INSERT INTO `sys_job_log` VALUES ('120', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:01:20');
INSERT INTO `sys_job_log` VALUES ('121', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:01:20');
INSERT INTO `sys_job_log` VALUES ('122', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:01:30');
INSERT INTO `sys_job_log` VALUES ('123', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 18:01:40');
INSERT INTO `sys_job_log` VALUES ('124', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:01:40');
INSERT INTO `sys_job_log` VALUES ('125', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 18:01:50');
INSERT INTO `sys_job_log` VALUES ('126', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:02:00');
INSERT INTO `sys_job_log` VALUES ('127', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:00');
INSERT INTO `sys_job_log` VALUES ('128', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:10');
INSERT INTO `sys_job_log` VALUES ('129', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:20');
INSERT INTO `sys_job_log` VALUES ('130', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:20');
INSERT INTO `sys_job_log` VALUES ('131', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:30');
INSERT INTO `sys_job_log` VALUES ('132', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:40');
INSERT INTO `sys_job_log` VALUES ('133', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:40');
INSERT INTO `sys_job_log` VALUES ('134', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:02:50');
INSERT INTO `sys_job_log` VALUES ('135', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:00');
INSERT INTO `sys_job_log` VALUES ('136', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:00');
INSERT INTO `sys_job_log` VALUES ('137', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:10');
INSERT INTO `sys_job_log` VALUES ('138', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:20');
INSERT INTO `sys_job_log` VALUES ('139', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:20');
INSERT INTO `sys_job_log` VALUES ('140', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:30');
INSERT INTO `sys_job_log` VALUES ('141', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:40');
INSERT INTO `sys_job_log` VALUES ('142', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:40');
INSERT INTO `sys_job_log` VALUES ('143', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:03:50');
INSERT INTO `sys_job_log` VALUES ('144', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:00');
INSERT INTO `sys_job_log` VALUES ('145', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:04:00');
INSERT INTO `sys_job_log` VALUES ('146', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:10');
INSERT INTO `sys_job_log` VALUES ('147', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:20');
INSERT INTO `sys_job_log` VALUES ('148', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:20');
INSERT INTO `sys_job_log` VALUES ('149', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:30');
INSERT INTO `sys_job_log` VALUES ('150', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:40');
INSERT INTO `sys_job_log` VALUES ('151', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:04:40');
INSERT INTO `sys_job_log` VALUES ('152', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:04:50');
INSERT INTO `sys_job_log` VALUES ('153', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:05:00');
INSERT INTO `sys_job_log` VALUES ('154', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:05:00');
INSERT INTO `sys_job_log` VALUES ('155', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:05:10');
INSERT INTO `sys_job_log` VALUES ('156', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:05:20');
INSERT INTO `sys_job_log` VALUES ('157', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:05:20');
INSERT INTO `sys_job_log` VALUES ('158', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:05:30');
INSERT INTO `sys_job_log` VALUES ('159', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:05:40');
INSERT INTO `sys_job_log` VALUES ('160', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:05:40');
INSERT INTO `sys_job_log` VALUES ('161', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:05:50');
INSERT INTO `sys_job_log` VALUES ('162', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:00');
INSERT INTO `sys_job_log` VALUES ('163', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：3毫秒', '0', '', '2019-05-06 18:06:00');
INSERT INTO `sys_job_log` VALUES ('164', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:10');
INSERT INTO `sys_job_log` VALUES ('165', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:20');
INSERT INTO `sys_job_log` VALUES ('166', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:20');
INSERT INTO `sys_job_log` VALUES ('167', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:30');
INSERT INTO `sys_job_log` VALUES ('168', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:40');
INSERT INTO `sys_job_log` VALUES ('169', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:06:40');
INSERT INTO `sys_job_log` VALUES ('170', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:06:50');
INSERT INTO `sys_job_log` VALUES ('171', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:00');
INSERT INTO `sys_job_log` VALUES ('172', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:00');
INSERT INTO `sys_job_log` VALUES ('173', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:10');
INSERT INTO `sys_job_log` VALUES ('174', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:20');
INSERT INTO `sys_job_log` VALUES ('175', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:20');
INSERT INTO `sys_job_log` VALUES ('176', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:07:30');
INSERT INTO `sys_job_log` VALUES ('177', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:40');
INSERT INTO `sys_job_log` VALUES ('178', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:07:40');
INSERT INTO `sys_job_log` VALUES ('179', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:07:50');
INSERT INTO `sys_job_log` VALUES ('180', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:08:00');
INSERT INTO `sys_job_log` VALUES ('181', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:08:00');
INSERT INTO `sys_job_log` VALUES ('182', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:08:10');
INSERT INTO `sys_job_log` VALUES ('183', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:08:20');
INSERT INTO `sys_job_log` VALUES ('184', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:08:20');
INSERT INTO `sys_job_log` VALUES ('185', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:08:30');
INSERT INTO `sys_job_log` VALUES ('186', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:08:40');
INSERT INTO `sys_job_log` VALUES ('187', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:08:40');
INSERT INTO `sys_job_log` VALUES ('188', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:08:50');
INSERT INTO `sys_job_log` VALUES ('189', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:00');
INSERT INTO `sys_job_log` VALUES ('190', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:00');
INSERT INTO `sys_job_log` VALUES ('191', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:10');
INSERT INTO `sys_job_log` VALUES ('192', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:20');
INSERT INTO `sys_job_log` VALUES ('193', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:20');
INSERT INTO `sys_job_log` VALUES ('194', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:30');
INSERT INTO `sys_job_log` VALUES ('195', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:09:40');
INSERT INTO `sys_job_log` VALUES ('196', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:40');
INSERT INTO `sys_job_log` VALUES ('197', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:09:50');
INSERT INTO `sys_job_log` VALUES ('198', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:10:00');
INSERT INTO `sys_job_log` VALUES ('199', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:10:00');
INSERT INTO `sys_job_log` VALUES ('200', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:10:10');
INSERT INTO `sys_job_log` VALUES ('201', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:10:20');
INSERT INTO `sys_job_log` VALUES ('202', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:10:20');
INSERT INTO `sys_job_log` VALUES ('203', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:10:30');
INSERT INTO `sys_job_log` VALUES ('204', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:10:40');
INSERT INTO `sys_job_log` VALUES ('205', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:10:40');
INSERT INTO `sys_job_log` VALUES ('206', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:10:50');
INSERT INTO `sys_job_log` VALUES ('207', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:11:00');
INSERT INTO `sys_job_log` VALUES ('208', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:00');
INSERT INTO `sys_job_log` VALUES ('209', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:10');
INSERT INTO `sys_job_log` VALUES ('210', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:20');
INSERT INTO `sys_job_log` VALUES ('211', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:20');
INSERT INTO `sys_job_log` VALUES ('212', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:30');
INSERT INTO `sys_job_log` VALUES ('213', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:40');
INSERT INTO `sys_job_log` VALUES ('214', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:40');
INSERT INTO `sys_job_log` VALUES ('215', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:11:50');
INSERT INTO `sys_job_log` VALUES ('216', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:00');
INSERT INTO `sys_job_log` VALUES ('217', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:00');
INSERT INTO `sys_job_log` VALUES ('218', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:10');
INSERT INTO `sys_job_log` VALUES ('219', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:20');
INSERT INTO `sys_job_log` VALUES ('220', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:20');
INSERT INTO `sys_job_log` VALUES ('221', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:30');
INSERT INTO `sys_job_log` VALUES ('222', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:12:40');
INSERT INTO `sys_job_log` VALUES ('223', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:40');
INSERT INTO `sys_job_log` VALUES ('224', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:12:50');
INSERT INTO `sys_job_log` VALUES ('225', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:00');
INSERT INTO `sys_job_log` VALUES ('226', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:13:00');
INSERT INTO `sys_job_log` VALUES ('227', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:10');
INSERT INTO `sys_job_log` VALUES ('228', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:20');
INSERT INTO `sys_job_log` VALUES ('229', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:20');
INSERT INTO `sys_job_log` VALUES ('230', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:30');
INSERT INTO `sys_job_log` VALUES ('231', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:13:40');
INSERT INTO `sys_job_log` VALUES ('232', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:40');
INSERT INTO `sys_job_log` VALUES ('233', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:13:50');
INSERT INTO `sys_job_log` VALUES ('234', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:14:00');
INSERT INTO `sys_job_log` VALUES ('235', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:14:00');
INSERT INTO `sys_job_log` VALUES ('236', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:14:10');
INSERT INTO `sys_job_log` VALUES ('237', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 18:14:20');
INSERT INTO `sys_job_log` VALUES ('238', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:14:20');
INSERT INTO `sys_job_log` VALUES ('239', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:14:30');
INSERT INTO `sys_job_log` VALUES ('240', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:14:40');
INSERT INTO `sys_job_log` VALUES ('241', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:14:40');
INSERT INTO `sys_job_log` VALUES ('242', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:14:50');
INSERT INTO `sys_job_log` VALUES ('243', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:00');
INSERT INTO `sys_job_log` VALUES ('244', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:15:00');
INSERT INTO `sys_job_log` VALUES ('245', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:10');
INSERT INTO `sys_job_log` VALUES ('246', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:15:20');
INSERT INTO `sys_job_log` VALUES ('247', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:20');
INSERT INTO `sys_job_log` VALUES ('248', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:30');
INSERT INTO `sys_job_log` VALUES ('249', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:40');
INSERT INTO `sys_job_log` VALUES ('250', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:40');
INSERT INTO `sys_job_log` VALUES ('251', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:15:50');
INSERT INTO `sys_job_log` VALUES ('252', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:16:00');
INSERT INTO `sys_job_log` VALUES ('253', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:16:00');
INSERT INTO `sys_job_log` VALUES ('254', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:16:10');
INSERT INTO `sys_job_log` VALUES ('255', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:16:20');
INSERT INTO `sys_job_log` VALUES ('256', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:16:20');
INSERT INTO `sys_job_log` VALUES ('257', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:16:30');
INSERT INTO `sys_job_log` VALUES ('258', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:16:53');
INSERT INTO `sys_job_log` VALUES ('259', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:00');
INSERT INTO `sys_job_log` VALUES ('260', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:00');
INSERT INTO `sys_job_log` VALUES ('261', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:10');
INSERT INTO `sys_job_log` VALUES ('262', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:20');
INSERT INTO `sys_job_log` VALUES ('263', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:17:20');
INSERT INTO `sys_job_log` VALUES ('264', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:30');
INSERT INTO `sys_job_log` VALUES ('265', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:40');
INSERT INTO `sys_job_log` VALUES ('266', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:40');
INSERT INTO `sys_job_log` VALUES ('267', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:17:50');
INSERT INTO `sys_job_log` VALUES ('268', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:00');
INSERT INTO `sys_job_log` VALUES ('269', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:18:00');
INSERT INTO `sys_job_log` VALUES ('270', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:10');
INSERT INTO `sys_job_log` VALUES ('271', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:20');
INSERT INTO `sys_job_log` VALUES ('272', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:20');
INSERT INTO `sys_job_log` VALUES ('273', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:30');
INSERT INTO `sys_job_log` VALUES ('274', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:40');
INSERT INTO `sys_job_log` VALUES ('275', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:40');
INSERT INTO `sys_job_log` VALUES ('276', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:18:50');
INSERT INTO `sys_job_log` VALUES ('277', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:19:00');
INSERT INTO `sys_job_log` VALUES ('278', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:19:00');
INSERT INTO `sys_job_log` VALUES ('279', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:19:10');
INSERT INTO `sys_job_log` VALUES ('280', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：5毫秒', '0', '', '2019-05-06 18:19:20');
INSERT INTO `sys_job_log` VALUES ('281', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:19:20');
INSERT INTO `sys_job_log` VALUES ('282', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:19:30');
INSERT INTO `sys_job_log` VALUES ('283', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:19:40');
INSERT INTO `sys_job_log` VALUES ('284', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:19:40');
INSERT INTO `sys_job_log` VALUES ('285', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:19:50');
INSERT INTO `sys_job_log` VALUES ('286', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:00');
INSERT INTO `sys_job_log` VALUES ('287', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:20:00');
INSERT INTO `sys_job_log` VALUES ('288', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:10');
INSERT INTO `sys_job_log` VALUES ('289', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:20');
INSERT INTO `sys_job_log` VALUES ('290', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:20');
INSERT INTO `sys_job_log` VALUES ('291', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:30');
INSERT INTO `sys_job_log` VALUES ('292', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:40');
INSERT INTO `sys_job_log` VALUES ('293', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:40');
INSERT INTO `sys_job_log` VALUES ('294', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:20:50');
INSERT INTO `sys_job_log` VALUES ('295', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:00');
INSERT INTO `sys_job_log` VALUES ('296', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:00');
INSERT INTO `sys_job_log` VALUES ('297', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:10');
INSERT INTO `sys_job_log` VALUES ('298', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:20');
INSERT INTO `sys_job_log` VALUES ('299', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:20');
INSERT INTO `sys_job_log` VALUES ('300', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：3毫秒', '0', '', '2019-05-06 18:21:30');
INSERT INTO `sys_job_log` VALUES ('301', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:40');
INSERT INTO `sys_job_log` VALUES ('302', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:40');
INSERT INTO `sys_job_log` VALUES ('303', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:21:50');
INSERT INTO `sys_job_log` VALUES ('304', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:00');
INSERT INTO `sys_job_log` VALUES ('305', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:22:00');
INSERT INTO `sys_job_log` VALUES ('306', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:10');
INSERT INTO `sys_job_log` VALUES ('307', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:22:20');
INSERT INTO `sys_job_log` VALUES ('308', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:20');
INSERT INTO `sys_job_log` VALUES ('309', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:30');
INSERT INTO `sys_job_log` VALUES ('310', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:40');
INSERT INTO `sys_job_log` VALUES ('311', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:40');
INSERT INTO `sys_job_log` VALUES ('312', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:22:50');
INSERT INTO `sys_job_log` VALUES ('313', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:00');
INSERT INTO `sys_job_log` VALUES ('314', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:00');
INSERT INTO `sys_job_log` VALUES ('315', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:10');
INSERT INTO `sys_job_log` VALUES ('316', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:23:20');
INSERT INTO `sys_job_log` VALUES ('317', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:20');
INSERT INTO `sys_job_log` VALUES ('318', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:30');
INSERT INTO `sys_job_log` VALUES ('319', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:23:40');
INSERT INTO `sys_job_log` VALUES ('320', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:40');
INSERT INTO `sys_job_log` VALUES ('321', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:23:50');
INSERT INTO `sys_job_log` VALUES ('322', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:00');
INSERT INTO `sys_job_log` VALUES ('323', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:00');
INSERT INTO `sys_job_log` VALUES ('324', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:10');
INSERT INTO `sys_job_log` VALUES ('325', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:24:20');
INSERT INTO `sys_job_log` VALUES ('326', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:20');
INSERT INTO `sys_job_log` VALUES ('327', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:30');
INSERT INTO `sys_job_log` VALUES ('328', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:40');
INSERT INTO `sys_job_log` VALUES ('329', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:40');
INSERT INTO `sys_job_log` VALUES ('330', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:24:50');
INSERT INTO `sys_job_log` VALUES ('331', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:00');
INSERT INTO `sys_job_log` VALUES ('332', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:00');
INSERT INTO `sys_job_log` VALUES ('333', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:10');
INSERT INTO `sys_job_log` VALUES ('334', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:20');
INSERT INTO `sys_job_log` VALUES ('335', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:20');
INSERT INTO `sys_job_log` VALUES ('336', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:30');
INSERT INTO `sys_job_log` VALUES ('337', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:40');
INSERT INTO `sys_job_log` VALUES ('338', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:40');
INSERT INTO `sys_job_log` VALUES ('339', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:25:50');
INSERT INTO `sys_job_log` VALUES ('340', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:26:00');
INSERT INTO `sys_job_log` VALUES ('341', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:26:00');
INSERT INTO `sys_job_log` VALUES ('342', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:26:10');
INSERT INTO `sys_job_log` VALUES ('343', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:26:20');
INSERT INTO `sys_job_log` VALUES ('344', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:26:20');
INSERT INTO `sys_job_log` VALUES ('345', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:26:30');
INSERT INTO `sys_job_log` VALUES ('346', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:26:40');
INSERT INTO `sys_job_log` VALUES ('347', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:26:40');
INSERT INTO `sys_job_log` VALUES ('348', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:26:50');
INSERT INTO `sys_job_log` VALUES ('349', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:00');
INSERT INTO `sys_job_log` VALUES ('350', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:00');
INSERT INTO `sys_job_log` VALUES ('351', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:10');
INSERT INTO `sys_job_log` VALUES ('352', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:20');
INSERT INTO `sys_job_log` VALUES ('353', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:20');
INSERT INTO `sys_job_log` VALUES ('354', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:30');
INSERT INTO `sys_job_log` VALUES ('355', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:40');
INSERT INTO `sys_job_log` VALUES ('356', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:40');
INSERT INTO `sys_job_log` VALUES ('357', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:27:50');
INSERT INTO `sys_job_log` VALUES ('358', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 18:28:00');
INSERT INTO `sys_job_log` VALUES ('359', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:28:00');
INSERT INTO `sys_job_log` VALUES ('360', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:28:10');
INSERT INTO `sys_job_log` VALUES ('361', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:28:20');
INSERT INTO `sys_job_log` VALUES ('362', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:28:20');
INSERT INTO `sys_job_log` VALUES ('363', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:28:30');
INSERT INTO `sys_job_log` VALUES ('364', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:28:40');
INSERT INTO `sys_job_log` VALUES ('365', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:28:40');
INSERT INTO `sys_job_log` VALUES ('366', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:28:50');
INSERT INTO `sys_job_log` VALUES ('367', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:00');
INSERT INTO `sys_job_log` VALUES ('368', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:00');
INSERT INTO `sys_job_log` VALUES ('369', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:10');
INSERT INTO `sys_job_log` VALUES ('370', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:20');
INSERT INTO `sys_job_log` VALUES ('371', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:20');
INSERT INTO `sys_job_log` VALUES ('372', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:30');
INSERT INTO `sys_job_log` VALUES ('373', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:40');
INSERT INTO `sys_job_log` VALUES ('374', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:29:40');
INSERT INTO `sys_job_log` VALUES ('375', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:29:50');
INSERT INTO `sys_job_log` VALUES ('376', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:00');
INSERT INTO `sys_job_log` VALUES ('377', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:00');
INSERT INTO `sys_job_log` VALUES ('378', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:10');
INSERT INTO `sys_job_log` VALUES ('379', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:20');
INSERT INTO `sys_job_log` VALUES ('380', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:20');
INSERT INTO `sys_job_log` VALUES ('381', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:30');
INSERT INTO `sys_job_log` VALUES ('382', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:40');
INSERT INTO `sys_job_log` VALUES ('383', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 18:30:40');
INSERT INTO `sys_job_log` VALUES ('384', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:30:50');
INSERT INTO `sys_job_log` VALUES ('385', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:00');
INSERT INTO `sys_job_log` VALUES ('386', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:00');
INSERT INTO `sys_job_log` VALUES ('387', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:10');
INSERT INTO `sys_job_log` VALUES ('388', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:20');
INSERT INTO `sys_job_log` VALUES ('389', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:20');
INSERT INTO `sys_job_log` VALUES ('390', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:30');
INSERT INTO `sys_job_log` VALUES ('391', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:31:40');
INSERT INTO `sys_job_log` VALUES ('392', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:31:40');
INSERT INTO `sys_job_log` VALUES ('393', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:31:50');
INSERT INTO `sys_job_log` VALUES ('394', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:00');
INSERT INTO `sys_job_log` VALUES ('395', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:00');
INSERT INTO `sys_job_log` VALUES ('396', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:10');
INSERT INTO `sys_job_log` VALUES ('397', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:20');
INSERT INTO `sys_job_log` VALUES ('398', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:20');
INSERT INTO `sys_job_log` VALUES ('399', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:32:30');
INSERT INTO `sys_job_log` VALUES ('400', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:40');
INSERT INTO `sys_job_log` VALUES ('401', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:40');
INSERT INTO `sys_job_log` VALUES ('402', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:32:50');
INSERT INTO `sys_job_log` VALUES ('403', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:00');
INSERT INTO `sys_job_log` VALUES ('404', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:00');
INSERT INTO `sys_job_log` VALUES ('405', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:10');
INSERT INTO `sys_job_log` VALUES ('406', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:33:20');
INSERT INTO `sys_job_log` VALUES ('407', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:20');
INSERT INTO `sys_job_log` VALUES ('408', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:30');
INSERT INTO `sys_job_log` VALUES ('409', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:40');
INSERT INTO `sys_job_log` VALUES ('410', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:40');
INSERT INTO `sys_job_log` VALUES ('411', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:33:50');
INSERT INTO `sys_job_log` VALUES ('412', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:34:00');
INSERT INTO `sys_job_log` VALUES ('413', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:00');
INSERT INTO `sys_job_log` VALUES ('414', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:34:10');
INSERT INTO `sys_job_log` VALUES ('415', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:20');
INSERT INTO `sys_job_log` VALUES ('416', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:20');
INSERT INTO `sys_job_log` VALUES ('417', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:30');
INSERT INTO `sys_job_log` VALUES ('418', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:40');
INSERT INTO `sys_job_log` VALUES ('419', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:40');
INSERT INTO `sys_job_log` VALUES ('420', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:34:50');
INSERT INTO `sys_job_log` VALUES ('421', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:00');
INSERT INTO `sys_job_log` VALUES ('422', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:00');
INSERT INTO `sys_job_log` VALUES ('423', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:10');
INSERT INTO `sys_job_log` VALUES ('424', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:20');
INSERT INTO `sys_job_log` VALUES ('425', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:20');
INSERT INTO `sys_job_log` VALUES ('426', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:30');
INSERT INTO `sys_job_log` VALUES ('427', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:40');
INSERT INTO `sys_job_log` VALUES ('428', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:35:40');
INSERT INTO `sys_job_log` VALUES ('429', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:35:50');
INSERT INTO `sys_job_log` VALUES ('430', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:00');
INSERT INTO `sys_job_log` VALUES ('431', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:00');
INSERT INTO `sys_job_log` VALUES ('432', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:36:10');
INSERT INTO `sys_job_log` VALUES ('433', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:36:20');
INSERT INTO `sys_job_log` VALUES ('434', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:20');
INSERT INTO `sys_job_log` VALUES ('435', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:30');
INSERT INTO `sys_job_log` VALUES ('436', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:40');
INSERT INTO `sys_job_log` VALUES ('437', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:40');
INSERT INTO `sys_job_log` VALUES ('438', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:36:50');
INSERT INTO `sys_job_log` VALUES ('439', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:00');
INSERT INTO `sys_job_log` VALUES ('440', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:00');
INSERT INTO `sys_job_log` VALUES ('441', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:10');
INSERT INTO `sys_job_log` VALUES ('442', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:20');
INSERT INTO `sys_job_log` VALUES ('443', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:20');
INSERT INTO `sys_job_log` VALUES ('444', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:30');
INSERT INTO `sys_job_log` VALUES ('445', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:40');
INSERT INTO `sys_job_log` VALUES ('446', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:37:40');
INSERT INTO `sys_job_log` VALUES ('447', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:37:50');
INSERT INTO `sys_job_log` VALUES ('448', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:00');
INSERT INTO `sys_job_log` VALUES ('449', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:00');
INSERT INTO `sys_job_log` VALUES ('450', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:10');
INSERT INTO `sys_job_log` VALUES ('451', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:20');
INSERT INTO `sys_job_log` VALUES ('452', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:20');
INSERT INTO `sys_job_log` VALUES ('453', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:30');
INSERT INTO `sys_job_log` VALUES ('454', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:40');
INSERT INTO `sys_job_log` VALUES ('455', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:40');
INSERT INTO `sys_job_log` VALUES ('456', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:38:50');
INSERT INTO `sys_job_log` VALUES ('457', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:00');
INSERT INTO `sys_job_log` VALUES ('458', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:39:00');
INSERT INTO `sys_job_log` VALUES ('459', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:10');
INSERT INTO `sys_job_log` VALUES ('460', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:20');
INSERT INTO `sys_job_log` VALUES ('461', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:20');
INSERT INTO `sys_job_log` VALUES ('462', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:30');
INSERT INTO `sys_job_log` VALUES ('463', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:40');
INSERT INTO `sys_job_log` VALUES ('464', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:40');
INSERT INTO `sys_job_log` VALUES ('465', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:39:50');
INSERT INTO `sys_job_log` VALUES ('466', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:40:00');
INSERT INTO `sys_job_log` VALUES ('467', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:40:00');
INSERT INTO `sys_job_log` VALUES ('468', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:40:10');
INSERT INTO `sys_job_log` VALUES ('469', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:40:20');
INSERT INTO `sys_job_log` VALUES ('470', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:40:20');
INSERT INTO `sys_job_log` VALUES ('471', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:40:30');
INSERT INTO `sys_job_log` VALUES ('472', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:40:40');
INSERT INTO `sys_job_log` VALUES ('473', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:40:40');
INSERT INTO `sys_job_log` VALUES ('474', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:40:50');
INSERT INTO `sys_job_log` VALUES ('475', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:00');
INSERT INTO `sys_job_log` VALUES ('476', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:00');
INSERT INTO `sys_job_log` VALUES ('477', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:10');
INSERT INTO `sys_job_log` VALUES ('478', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:41:20');
INSERT INTO `sys_job_log` VALUES ('479', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:20');
INSERT INTO `sys_job_log` VALUES ('480', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:30');
INSERT INTO `sys_job_log` VALUES ('481', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:40');
INSERT INTO `sys_job_log` VALUES ('482', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:40');
INSERT INTO `sys_job_log` VALUES ('483', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:41:50');
INSERT INTO `sys_job_log` VALUES ('484', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:00');
INSERT INTO `sys_job_log` VALUES ('485', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:00');
INSERT INTO `sys_job_log` VALUES ('486', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:10');
INSERT INTO `sys_job_log` VALUES ('487', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:20');
INSERT INTO `sys_job_log` VALUES ('488', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:20');
INSERT INTO `sys_job_log` VALUES ('489', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:30');
INSERT INTO `sys_job_log` VALUES ('490', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:40');
INSERT INTO `sys_job_log` VALUES ('491', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:40');
INSERT INTO `sys_job_log` VALUES ('492', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:42:50');
INSERT INTO `sys_job_log` VALUES ('493', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:00');
INSERT INTO `sys_job_log` VALUES ('494', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:00');
INSERT INTO `sys_job_log` VALUES ('495', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:10');
INSERT INTO `sys_job_log` VALUES ('496', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:20');
INSERT INTO `sys_job_log` VALUES ('497', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:20');
INSERT INTO `sys_job_log` VALUES ('498', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:30');
INSERT INTO `sys_job_log` VALUES ('499', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 18:43:40');
INSERT INTO `sys_job_log` VALUES ('500', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:40');
INSERT INTO `sys_job_log` VALUES ('501', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:43:50');
INSERT INTO `sys_job_log` VALUES ('502', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:00');
INSERT INTO `sys_job_log` VALUES ('503', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:00');
INSERT INTO `sys_job_log` VALUES ('504', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:10');
INSERT INTO `sys_job_log` VALUES ('505', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:20');
INSERT INTO `sys_job_log` VALUES ('506', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:20');
INSERT INTO `sys_job_log` VALUES ('507', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：5毫秒', '0', '', '2019-05-06 18:44:30');
INSERT INTO `sys_job_log` VALUES ('508', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:40');
INSERT INTO `sys_job_log` VALUES ('509', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:40');
INSERT INTO `sys_job_log` VALUES ('510', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:44:50');
INSERT INTO `sys_job_log` VALUES ('511', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:00');
INSERT INTO `sys_job_log` VALUES ('512', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:00');
INSERT INTO `sys_job_log` VALUES ('513', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:10');
INSERT INTO `sys_job_log` VALUES ('514', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:20');
INSERT INTO `sys_job_log` VALUES ('515', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:20');
INSERT INTO `sys_job_log` VALUES ('516', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:30');
INSERT INTO `sys_job_log` VALUES ('517', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:45:40');
INSERT INTO `sys_job_log` VALUES ('518', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:45:40');
INSERT INTO `sys_job_log` VALUES ('519', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:45:50');
INSERT INTO `sys_job_log` VALUES ('520', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:00');
INSERT INTO `sys_job_log` VALUES ('521', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:46:00');
INSERT INTO `sys_job_log` VALUES ('522', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:10');
INSERT INTO `sys_job_log` VALUES ('523', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:46:20');
INSERT INTO `sys_job_log` VALUES ('524', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:20');
INSERT INTO `sys_job_log` VALUES ('525', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:30');
INSERT INTO `sys_job_log` VALUES ('526', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:40');
INSERT INTO `sys_job_log` VALUES ('527', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:40');
INSERT INTO `sys_job_log` VALUES ('528', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:46:50');
INSERT INTO `sys_job_log` VALUES ('529', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:47:00');
INSERT INTO `sys_job_log` VALUES ('530', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:47:00');
INSERT INTO `sys_job_log` VALUES ('531', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:47:10');
INSERT INTO `sys_job_log` VALUES ('532', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:47:20');
INSERT INTO `sys_job_log` VALUES ('533', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:47:20');
INSERT INTO `sys_job_log` VALUES ('534', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:47:30');
INSERT INTO `sys_job_log` VALUES ('535', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:47:40');
INSERT INTO `sys_job_log` VALUES ('536', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:47:40');
INSERT INTO `sys_job_log` VALUES ('537', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:47:50');
INSERT INTO `sys_job_log` VALUES ('538', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:00');
INSERT INTO `sys_job_log` VALUES ('539', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:00');
INSERT INTO `sys_job_log` VALUES ('540', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:48:10');
INSERT INTO `sys_job_log` VALUES ('541', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:20');
INSERT INTO `sys_job_log` VALUES ('542', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:20');
INSERT INTO `sys_job_log` VALUES ('543', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:30');
INSERT INTO `sys_job_log` VALUES ('544', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:40');
INSERT INTO `sys_job_log` VALUES ('545', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:40');
INSERT INTO `sys_job_log` VALUES ('546', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:48:50');
INSERT INTO `sys_job_log` VALUES ('547', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:00');
INSERT INTO `sys_job_log` VALUES ('548', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:00');
INSERT INTO `sys_job_log` VALUES ('549', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:10');
INSERT INTO `sys_job_log` VALUES ('550', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:20');
INSERT INTO `sys_job_log` VALUES ('551', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:20');
INSERT INTO `sys_job_log` VALUES ('552', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:30');
INSERT INTO `sys_job_log` VALUES ('553', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:40');
INSERT INTO `sys_job_log` VALUES ('554', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:49:40');
INSERT INTO `sys_job_log` VALUES ('555', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:49:50');
INSERT INTO `sys_job_log` VALUES ('556', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:00');
INSERT INTO `sys_job_log` VALUES ('557', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:00');
INSERT INTO `sys_job_log` VALUES ('558', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:10');
INSERT INTO `sys_job_log` VALUES ('559', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:20');
INSERT INTO `sys_job_log` VALUES ('560', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:20');
INSERT INTO `sys_job_log` VALUES ('561', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:30');
INSERT INTO `sys_job_log` VALUES ('562', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:50:40');
INSERT INTO `sys_job_log` VALUES ('563', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:50:40');
INSERT INTO `sys_job_log` VALUES ('564', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:50:50');
INSERT INTO `sys_job_log` VALUES ('565', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:00');
INSERT INTO `sys_job_log` VALUES ('566', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:00');
INSERT INTO `sys_job_log` VALUES ('567', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:10');
INSERT INTO `sys_job_log` VALUES ('568', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:20');
INSERT INTO `sys_job_log` VALUES ('569', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:20');
INSERT INTO `sys_job_log` VALUES ('570', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:30');
INSERT INTO `sys_job_log` VALUES ('571', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:40');
INSERT INTO `sys_job_log` VALUES ('572', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:40');
INSERT INTO `sys_job_log` VALUES ('573', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:51:50');
INSERT INTO `sys_job_log` VALUES ('574', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:00');
INSERT INTO `sys_job_log` VALUES ('575', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:00');
INSERT INTO `sys_job_log` VALUES ('576', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:52:10');
INSERT INTO `sys_job_log` VALUES ('577', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:20');
INSERT INTO `sys_job_log` VALUES ('578', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:20');
INSERT INTO `sys_job_log` VALUES ('579', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:30');
INSERT INTO `sys_job_log` VALUES ('580', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:40');
INSERT INTO `sys_job_log` VALUES ('581', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:52:40');
INSERT INTO `sys_job_log` VALUES ('582', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:52:50');
INSERT INTO `sys_job_log` VALUES ('583', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:00');
INSERT INTO `sys_job_log` VALUES ('584', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:00');
INSERT INTO `sys_job_log` VALUES ('585', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:53:10');
INSERT INTO `sys_job_log` VALUES ('586', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:20');
INSERT INTO `sys_job_log` VALUES ('587', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:20');
INSERT INTO `sys_job_log` VALUES ('588', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:30');
INSERT INTO `sys_job_log` VALUES ('589', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:40');
INSERT INTO `sys_job_log` VALUES ('590', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:40');
INSERT INTO `sys_job_log` VALUES ('591', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:53:50');
INSERT INTO `sys_job_log` VALUES ('592', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:54:00');
INSERT INTO `sys_job_log` VALUES ('593', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:00');
INSERT INTO `sys_job_log` VALUES ('594', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:10');
INSERT INTO `sys_job_log` VALUES ('595', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:20');
INSERT INTO `sys_job_log` VALUES ('596', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:20');
INSERT INTO `sys_job_log` VALUES ('597', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:30');
INSERT INTO `sys_job_log` VALUES ('598', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:40');
INSERT INTO `sys_job_log` VALUES ('599', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:54:40');
INSERT INTO `sys_job_log` VALUES ('600', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:54:50');
INSERT INTO `sys_job_log` VALUES ('601', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:00');
INSERT INTO `sys_job_log` VALUES ('602', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:00');
INSERT INTO `sys_job_log` VALUES ('603', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:10');
INSERT INTO `sys_job_log` VALUES ('604', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:55:20');
INSERT INTO `sys_job_log` VALUES ('605', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:20');
INSERT INTO `sys_job_log` VALUES ('606', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:30');
INSERT INTO `sys_job_log` VALUES ('607', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:40');
INSERT INTO `sys_job_log` VALUES ('608', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:55:40');
INSERT INTO `sys_job_log` VALUES ('609', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:55:50');
INSERT INTO `sys_job_log` VALUES ('610', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:00');
INSERT INTO `sys_job_log` VALUES ('611', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:56:00');
INSERT INTO `sys_job_log` VALUES ('612', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:10');
INSERT INTO `sys_job_log` VALUES ('613', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:20');
INSERT INTO `sys_job_log` VALUES ('614', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 18:56:20');
INSERT INTO `sys_job_log` VALUES ('615', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:30');
INSERT INTO `sys_job_log` VALUES ('616', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:40');
INSERT INTO `sys_job_log` VALUES ('617', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:40');
INSERT INTO `sys_job_log` VALUES ('618', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 18:56:50');
INSERT INTO `sys_job_log` VALUES ('619', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:03:20');
INSERT INTO `sys_job_log` VALUES ('620', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:03:20');
INSERT INTO `sys_job_log` VALUES ('621', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:03:30');
INSERT INTO `sys_job_log` VALUES ('622', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:03:40');
INSERT INTO `sys_job_log` VALUES ('623', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:03:40');
INSERT INTO `sys_job_log` VALUES ('624', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:03:50');
INSERT INTO `sys_job_log` VALUES ('625', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 19:04:00');
INSERT INTO `sys_job_log` VALUES ('626', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:04:00');
INSERT INTO `sys_job_log` VALUES ('627', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:04:10');
INSERT INTO `sys_job_log` VALUES ('628', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：7毫秒', '0', '', '2019-05-06 19:04:20');
INSERT INTO `sys_job_log` VALUES ('629', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:04:20');
INSERT INTO `sys_job_log` VALUES ('630', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:04:30');
INSERT INTO `sys_job_log` VALUES ('631', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:04:40');
INSERT INTO `sys_job_log` VALUES ('632', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:04:40');
INSERT INTO `sys_job_log` VALUES ('633', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:04:50');
INSERT INTO `sys_job_log` VALUES ('634', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:05:00');
INSERT INTO `sys_job_log` VALUES ('635', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:05:00');
INSERT INTO `sys_job_log` VALUES ('636', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:05:10');
INSERT INTO `sys_job_log` VALUES ('637', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:05:20');
INSERT INTO `sys_job_log` VALUES ('638', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:05:20');
INSERT INTO `sys_job_log` VALUES ('639', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:05:30');
INSERT INTO `sys_job_log` VALUES ('640', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:05:40');
INSERT INTO `sys_job_log` VALUES ('641', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:05:40');
INSERT INTO `sys_job_log` VALUES ('642', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:08:34');
INSERT INTO `sys_job_log` VALUES ('643', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:08:40');
INSERT INTO `sys_job_log` VALUES ('644', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:08:40');
INSERT INTO `sys_job_log` VALUES ('645', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:08:50');
INSERT INTO `sys_job_log` VALUES ('646', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:09:00');
INSERT INTO `sys_job_log` VALUES ('647', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:09:00');
INSERT INTO `sys_job_log` VALUES ('648', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:09:10');
INSERT INTO `sys_job_log` VALUES ('649', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:09:20');
INSERT INTO `sys_job_log` VALUES ('650', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:09:20');
INSERT INTO `sys_job_log` VALUES ('651', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:09:30');
INSERT INTO `sys_job_log` VALUES ('652', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:09:40');
INSERT INTO `sys_job_log` VALUES ('653', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:09:40');
INSERT INTO `sys_job_log` VALUES ('654', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:09:50');
INSERT INTO `sys_job_log` VALUES ('655', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 19:10:00');
INSERT INTO `sys_job_log` VALUES ('656', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:10:00');
INSERT INTO `sys_job_log` VALUES ('657', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:10:10');
INSERT INTO `sys_job_log` VALUES ('658', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:10:20');
INSERT INTO `sys_job_log` VALUES ('659', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:10:20');
INSERT INTO `sys_job_log` VALUES ('660', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:10:30');
INSERT INTO `sys_job_log` VALUES ('661', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:10:40');
INSERT INTO `sys_job_log` VALUES ('662', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:10:40');
INSERT INTO `sys_job_log` VALUES ('663', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:10:50');
INSERT INTO `sys_job_log` VALUES ('664', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:11:00');
INSERT INTO `sys_job_log` VALUES ('665', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:11:00');
INSERT INTO `sys_job_log` VALUES ('666', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:11:10');
INSERT INTO `sys_job_log` VALUES ('667', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:11:20');
INSERT INTO `sys_job_log` VALUES ('668', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:11:20');
INSERT INTO `sys_job_log` VALUES ('669', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:11:30');
INSERT INTO `sys_job_log` VALUES ('670', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:11:40');
INSERT INTO `sys_job_log` VALUES ('671', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:11:40');
INSERT INTO `sys_job_log` VALUES ('672', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:11:50');
INSERT INTO `sys_job_log` VALUES ('673', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:00');
INSERT INTO `sys_job_log` VALUES ('674', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:00');
INSERT INTO `sys_job_log` VALUES ('675', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:10');
INSERT INTO `sys_job_log` VALUES ('676', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:20');
INSERT INTO `sys_job_log` VALUES ('677', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:12:20');
INSERT INTO `sys_job_log` VALUES ('678', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:30');
INSERT INTO `sys_job_log` VALUES ('679', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:40');
INSERT INTO `sys_job_log` VALUES ('680', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:40');
INSERT INTO `sys_job_log` VALUES ('681', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:12:50');
INSERT INTO `sys_job_log` VALUES ('682', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:13:00');
INSERT INTO `sys_job_log` VALUES ('683', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:13:00');
INSERT INTO `sys_job_log` VALUES ('684', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:13:10');
INSERT INTO `sys_job_log` VALUES ('685', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:13:20');
INSERT INTO `sys_job_log` VALUES ('686', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:13:20');
INSERT INTO `sys_job_log` VALUES ('687', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:13:30');
INSERT INTO `sys_job_log` VALUES ('688', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:13:40');
INSERT INTO `sys_job_log` VALUES ('689', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:13:40');
INSERT INTO `sys_job_log` VALUES ('690', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:13:50');
INSERT INTO `sys_job_log` VALUES ('691', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:00');
INSERT INTO `sys_job_log` VALUES ('692', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:00');
INSERT INTO `sys_job_log` VALUES ('693', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:14:10');
INSERT INTO `sys_job_log` VALUES ('694', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:20');
INSERT INTO `sys_job_log` VALUES ('695', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:20');
INSERT INTO `sys_job_log` VALUES ('696', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:30');
INSERT INTO `sys_job_log` VALUES ('697', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:40');
INSERT INTO `sys_job_log` VALUES ('698', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:40');
INSERT INTO `sys_job_log` VALUES ('699', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:14:50');
INSERT INTO `sys_job_log` VALUES ('700', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:15:00');
INSERT INTO `sys_job_log` VALUES ('701', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:15:00');
INSERT INTO `sys_job_log` VALUES ('702', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:15:10');
INSERT INTO `sys_job_log` VALUES ('703', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:15:20');
INSERT INTO `sys_job_log` VALUES ('704', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:15:20');
INSERT INTO `sys_job_log` VALUES ('705', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:15:30');
INSERT INTO `sys_job_log` VALUES ('706', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 19:15:40');
INSERT INTO `sys_job_log` VALUES ('707', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:15:40');
INSERT INTO `sys_job_log` VALUES ('708', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:15:50');
INSERT INTO `sys_job_log` VALUES ('709', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:16:00');
INSERT INTO `sys_job_log` VALUES ('710', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:16:00');
INSERT INTO `sys_job_log` VALUES ('711', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:16:10');
INSERT INTO `sys_job_log` VALUES ('712', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:16:20');
INSERT INTO `sys_job_log` VALUES ('713', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:16:20');
INSERT INTO `sys_job_log` VALUES ('714', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:16:30');
INSERT INTO `sys_job_log` VALUES ('715', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:16:40');
INSERT INTO `sys_job_log` VALUES ('716', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:16:40');
INSERT INTO `sys_job_log` VALUES ('717', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:16:50');
INSERT INTO `sys_job_log` VALUES ('718', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：4毫秒', '0', '', '2019-05-06 19:17:00');
INSERT INTO `sys_job_log` VALUES ('719', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:17:00');
INSERT INTO `sys_job_log` VALUES ('720', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:17:10');
INSERT INTO `sys_job_log` VALUES ('721', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:17:20');
INSERT INTO `sys_job_log` VALUES ('722', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:17:20');
INSERT INTO `sys_job_log` VALUES ('723', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:17:30');
INSERT INTO `sys_job_log` VALUES ('724', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:17:40');
INSERT INTO `sys_job_log` VALUES ('725', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:17:40');
INSERT INTO `sys_job_log` VALUES ('726', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:17:50');
INSERT INTO `sys_job_log` VALUES ('727', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:18:00');
INSERT INTO `sys_job_log` VALUES ('728', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:00');
INSERT INTO `sys_job_log` VALUES ('729', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:10');
INSERT INTO `sys_job_log` VALUES ('730', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:20');
INSERT INTO `sys_job_log` VALUES ('731', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:20');
INSERT INTO `sys_job_log` VALUES ('732', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:30');
INSERT INTO `sys_job_log` VALUES ('733', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:40');
INSERT INTO `sys_job_log` VALUES ('734', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:18:40');
INSERT INTO `sys_job_log` VALUES ('735', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:18:50');
INSERT INTO `sys_job_log` VALUES ('736', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:00');
INSERT INTO `sys_job_log` VALUES ('737', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:00');
INSERT INTO `sys_job_log` VALUES ('738', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:10');
INSERT INTO `sys_job_log` VALUES ('739', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:19:20');
INSERT INTO `sys_job_log` VALUES ('740', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:20');
INSERT INTO `sys_job_log` VALUES ('741', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:19:30');
INSERT INTO `sys_job_log` VALUES ('742', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:40');
INSERT INTO `sys_job_log` VALUES ('743', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:40');
INSERT INTO `sys_job_log` VALUES ('744', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:19:50');
INSERT INTO `sys_job_log` VALUES ('745', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:26:34');
INSERT INTO `sys_job_log` VALUES ('746', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:26:40');
INSERT INTO `sys_job_log` VALUES ('747', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:26:40');
INSERT INTO `sys_job_log` VALUES ('748', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:26:50');
INSERT INTO `sys_job_log` VALUES ('749', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:27:00');
INSERT INTO `sys_job_log` VALUES ('750', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:00');
INSERT INTO `sys_job_log` VALUES ('751', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:10');
INSERT INTO `sys_job_log` VALUES ('752', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:20');
INSERT INTO `sys_job_log` VALUES ('753', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:20');
INSERT INTO `sys_job_log` VALUES ('754', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:30');
INSERT INTO `sys_job_log` VALUES ('755', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:40');
INSERT INTO `sys_job_log` VALUES ('756', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:40');
INSERT INTO `sys_job_log` VALUES ('757', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:27:50');
INSERT INTO `sys_job_log` VALUES ('758', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:28:00');
INSERT INTO `sys_job_log` VALUES ('759', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:28:00');
INSERT INTO `sys_job_log` VALUES ('760', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:28:20');
INSERT INTO `sys_job_log` VALUES ('761', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:28:20');
INSERT INTO `sys_job_log` VALUES ('762', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:28:30');
INSERT INTO `sys_job_log` VALUES ('763', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:28:40');
INSERT INTO `sys_job_log` VALUES ('764', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:28:40');
INSERT INTO `sys_job_log` VALUES ('765', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:28:50');
INSERT INTO `sys_job_log` VALUES ('766', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:29:00');
INSERT INTO `sys_job_log` VALUES ('767', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:29:00');
INSERT INTO `sys_job_log` VALUES ('768', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:29:10');
INSERT INTO `sys_job_log` VALUES ('769', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:29:20');
INSERT INTO `sys_job_log` VALUES ('770', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:29:20');
INSERT INTO `sys_job_log` VALUES ('771', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:29:30');
INSERT INTO `sys_job_log` VALUES ('772', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:29:40');
INSERT INTO `sys_job_log` VALUES ('773', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:29:40');
INSERT INTO `sys_job_log` VALUES ('774', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:29:50');
INSERT INTO `sys_job_log` VALUES ('775', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:30:00');
INSERT INTO `sys_job_log` VALUES ('776', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:30:00');
INSERT INTO `sys_job_log` VALUES ('777', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:30:10');
INSERT INTO `sys_job_log` VALUES ('778', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:30:20');
INSERT INTO `sys_job_log` VALUES ('779', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:30:20');
INSERT INTO `sys_job_log` VALUES ('780', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:30:30');
INSERT INTO `sys_job_log` VALUES ('781', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:30:40');
INSERT INTO `sys_job_log` VALUES ('782', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:30:40');
INSERT INTO `sys_job_log` VALUES ('783', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:30:50');
INSERT INTO `sys_job_log` VALUES ('784', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:00');
INSERT INTO `sys_job_log` VALUES ('785', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:00');
INSERT INTO `sys_job_log` VALUES ('786', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:10');
INSERT INTO `sys_job_log` VALUES ('787', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:20');
INSERT INTO `sys_job_log` VALUES ('788', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:20');
INSERT INTO `sys_job_log` VALUES ('789', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:30');
INSERT INTO `sys_job_log` VALUES ('790', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:40');
INSERT INTO `sys_job_log` VALUES ('791', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:40');
INSERT INTO `sys_job_log` VALUES ('792', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:31:50');
INSERT INTO `sys_job_log` VALUES ('793', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:00');
INSERT INTO `sys_job_log` VALUES ('794', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:00');
INSERT INTO `sys_job_log` VALUES ('795', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:32:10');
INSERT INTO `sys_job_log` VALUES ('796', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:20');
INSERT INTO `sys_job_log` VALUES ('797', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:20');
INSERT INTO `sys_job_log` VALUES ('798', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:30');
INSERT INTO `sys_job_log` VALUES ('799', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:40');
INSERT INTO `sys_job_log` VALUES ('800', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:40');
INSERT INTO `sys_job_log` VALUES ('801', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:32:50');
INSERT INTO `sys_job_log` VALUES ('802', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:33:00');
INSERT INTO `sys_job_log` VALUES ('803', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:33:00');
INSERT INTO `sys_job_log` VALUES ('804', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:33:10');
INSERT INTO `sys_job_log` VALUES ('805', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:33:20');
INSERT INTO `sys_job_log` VALUES ('806', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 19:33:20');
INSERT INTO `sys_job_log` VALUES ('807', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:34:23');
INSERT INTO `sys_job_log` VALUES ('808', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:34:23');
INSERT INTO `sys_job_log` VALUES ('809', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:34:30');
INSERT INTO `sys_job_log` VALUES ('810', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:34:40');
INSERT INTO `sys_job_log` VALUES ('811', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:34:40');
INSERT INTO `sys_job_log` VALUES ('812', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:34:50');
INSERT INTO `sys_job_log` VALUES ('813', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:35:00');
INSERT INTO `sys_job_log` VALUES ('814', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:35:00');
INSERT INTO `sys_job_log` VALUES ('815', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:35:10');
INSERT INTO `sys_job_log` VALUES ('816', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:35:20');
INSERT INTO `sys_job_log` VALUES ('817', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:35:20');
INSERT INTO `sys_job_log` VALUES ('818', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 19:35:30');
INSERT INTO `sys_job_log` VALUES ('819', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:35:40');
INSERT INTO `sys_job_log` VALUES ('820', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:35:40');
INSERT INTO `sys_job_log` VALUES ('821', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:35:50');
INSERT INTO `sys_job_log` VALUES ('822', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:36:00');
INSERT INTO `sys_job_log` VALUES ('823', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：2毫秒', '0', '', '2019-05-06 19:36:00');
INSERT INTO `sys_job_log` VALUES ('824', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:36:10');
INSERT INTO `sys_job_log` VALUES ('825', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:36:20');
INSERT INTO `sys_job_log` VALUES ('826', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:36:20');
INSERT INTO `sys_job_log` VALUES ('827', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：1毫秒', '0', '', '2019-05-06 19:36:30');
INSERT INTO `sys_job_log` VALUES ('828', 'ryTask', '系统默认（无参）', 'ryNoParams', null, 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:36:40');
INSERT INTO `sys_job_log` VALUES ('829', 'ryTask', '系统默认（有参）', 'ryParams', 'ry', 'ryTask 总共耗时：0毫秒', '0', '', '2019-05-06 19:36:40');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES ('100', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-04-29 12:19:16');
INSERT INTO `sys_logininfor` VALUES ('101', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-04-29 12:19:24');
INSERT INTO `sys_logininfor` VALUES ('102', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-04-29 12:19:29');
INSERT INTO `sys_logininfor` VALUES ('103', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-04-29 12:19:35');
INSERT INTO `sys_logininfor` VALUES ('104', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-04-29 13:12:09');
INSERT INTO `sys_logininfor` VALUES ('105', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-04-29 13:13:15');
INSERT INTO `sys_logininfor` VALUES ('106', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-04-29 13:39:37');
INSERT INTO `sys_logininfor` VALUES ('107', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-04-29 13:39:47');
INSERT INTO `sys_logininfor` VALUES ('108', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-04-29 13:39:50');
INSERT INTO `sys_logininfor` VALUES ('109', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-04-29 13:39:57');
INSERT INTO `sys_logininfor` VALUES ('110', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-04-29 13:41:51');
INSERT INTO `sys_logininfor` VALUES ('111', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 10:05:25');
INSERT INTO `sys_logininfor` VALUES ('112', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-02 10:16:12');
INSERT INTO `sys_logininfor` VALUES ('113', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 12:19:58');
INSERT INTO `sys_logininfor` VALUES ('114', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 12:20:07');
INSERT INTO `sys_logininfor` VALUES ('115', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 12:20:37');
INSERT INTO `sys_logininfor` VALUES ('116', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 16:32:31');
INSERT INTO `sys_logininfor` VALUES ('117', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 16:32:41');
INSERT INTO `sys_logininfor` VALUES ('118', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 16:33:33');
INSERT INTO `sys_logininfor` VALUES ('119', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 16:34:14');
INSERT INTO `sys_logininfor` VALUES ('120', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 16:35:17');
INSERT INTO `sys_logininfor` VALUES ('121', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-02 16:35:42');
INSERT INTO `sys_logininfor` VALUES ('122', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 16:35:54');
INSERT INTO `sys_logininfor` VALUES ('123', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 16:47:06');
INSERT INTO `sys_logininfor` VALUES ('124', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-02 16:47:46');
INSERT INTO `sys_logininfor` VALUES ('125', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 16:51:45');
INSERT INTO `sys_logininfor` VALUES ('126', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 16:52:01');
INSERT INTO `sys_logininfor` VALUES ('127', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 17:15:07');
INSERT INTO `sys_logininfor` VALUES ('128', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 17:36:48');
INSERT INTO `sys_logininfor` VALUES ('129', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-02 17:42:27');
INSERT INTO `sys_logininfor` VALUES ('130', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 17:42:32');
INSERT INTO `sys_logininfor` VALUES ('131', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 17:58:03');
INSERT INTO `sys_logininfor` VALUES ('132', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-02 18:15:21');
INSERT INTO `sys_logininfor` VALUES ('133', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-02 18:15:25');
INSERT INTO `sys_logininfor` VALUES ('134', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 10:25:05');
INSERT INTO `sys_logininfor` VALUES ('135', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 10:50:34');
INSERT INTO `sys_logininfor` VALUES ('136', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:25:18');
INSERT INTO `sys_logininfor` VALUES ('137', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 14:29:37');
INSERT INTO `sys_logininfor` VALUES ('138', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:31:17');
INSERT INTO `sys_logininfor` VALUES ('139', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:32:32');
INSERT INTO `sys_logininfor` VALUES ('140', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 14:32:34');
INSERT INTO `sys_logininfor` VALUES ('141', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:32:43');
INSERT INTO `sys_logininfor` VALUES ('142', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:35:40');
INSERT INTO `sys_logininfor` VALUES ('143', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:40:03');
INSERT INTO `sys_logininfor` VALUES ('144', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:50:29');
INSERT INTO `sys_logininfor` VALUES ('145', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 14:50:32');
INSERT INTO `sys_logininfor` VALUES ('146', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:50:39');
INSERT INTO `sys_logininfor` VALUES ('147', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 14:50:40');
INSERT INTO `sys_logininfor` VALUES ('148', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 14:51:32');
INSERT INTO `sys_logininfor` VALUES ('149', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 14:54:45');
INSERT INTO `sys_logininfor` VALUES ('150', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:04:59');
INSERT INTO `sys_logininfor` VALUES ('151', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:18:32');
INSERT INTO `sys_logininfor` VALUES ('152', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 15:35:01');
INSERT INTO `sys_logininfor` VALUES ('153', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:35:32');
INSERT INTO `sys_logininfor` VALUES ('154', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:35:56');
INSERT INTO `sys_logininfor` VALUES ('155', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 15:36:12');
INSERT INTO `sys_logininfor` VALUES ('156', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 15:37:47');
INSERT INTO `sys_logininfor` VALUES ('157', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:38:23');
INSERT INTO `sys_logininfor` VALUES ('158', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 15:38:27');
INSERT INTO `sys_logininfor` VALUES ('159', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 15:38:42');
INSERT INTO `sys_logininfor` VALUES ('160', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 15:51:05');
INSERT INTO `sys_logininfor` VALUES ('161', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 15:51:16');
INSERT INTO `sys_logininfor` VALUES ('162', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:51:37');
INSERT INTO `sys_logininfor` VALUES ('163', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 15:57:31');
INSERT INTO `sys_logininfor` VALUES ('164', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 15:59:32');
INSERT INTO `sys_logininfor` VALUES ('165', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 17:50:15');
INSERT INTO `sys_logininfor` VALUES ('166', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-03 18:18:25');
INSERT INTO `sys_logininfor` VALUES ('167', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 18:18:49');
INSERT INTO `sys_logininfor` VALUES ('168', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 18:38:49');
INSERT INTO `sys_logininfor` VALUES ('169', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-03 18:45:12');
INSERT INTO `sys_logininfor` VALUES ('170', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-03 18:55:34');
INSERT INTO `sys_logininfor` VALUES ('171', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 09:53:17');
INSERT INTO `sys_logininfor` VALUES ('172', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 10:24:37');
INSERT INTO `sys_logininfor` VALUES ('173', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 11:10:09');
INSERT INTO `sys_logininfor` VALUES ('174', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 11:24:50');
INSERT INTO `sys_logininfor` VALUES ('175', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 12:03:00');
INSERT INTO `sys_logininfor` VALUES ('176', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:37:50');
INSERT INTO `sys_logininfor` VALUES ('177', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:44:42');
INSERT INTO `sys_logininfor` VALUES ('178', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:45:43');
INSERT INTO `sys_logininfor` VALUES ('179', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:45:52');
INSERT INTO `sys_logininfor` VALUES ('180', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:46:09');
INSERT INTO `sys_logininfor` VALUES ('181', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:46:12');
INSERT INTO `sys_logininfor` VALUES ('182', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:46:40');
INSERT INTO `sys_logininfor` VALUES ('183', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:46:44');
INSERT INTO `sys_logininfor` VALUES ('184', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:46:50');
INSERT INTO `sys_logininfor` VALUES ('185', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:47:01');
INSERT INTO `sys_logininfor` VALUES ('186', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:47:17');
INSERT INTO `sys_logininfor` VALUES ('187', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:47:23');
INSERT INTO `sys_logininfor` VALUES ('188', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:47:25');
INSERT INTO `sys_logininfor` VALUES ('189', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '密码输入错误1次', '2019-05-04 14:47:32');
INSERT INTO `sys_logininfor` VALUES ('190', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:47:40');
INSERT INTO `sys_logininfor` VALUES ('191', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:47:46');
INSERT INTO `sys_logininfor` VALUES ('192', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:47:48');
INSERT INTO `sys_logininfor` VALUES ('193', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:48:41');
INSERT INTO `sys_logininfor` VALUES ('194', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-04 14:48:45');
INSERT INTO `sys_logininfor` VALUES ('195', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:48:49');
INSERT INTO `sys_logininfor` VALUES ('196', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-04 14:50:29');
INSERT INTO `sys_logininfor` VALUES ('197', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:50:37');
INSERT INTO `sys_logininfor` VALUES ('198', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 14:52:01');
INSERT INTO `sys_logininfor` VALUES ('199', 'hr', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-04 16:05:36');
INSERT INTO `sys_logininfor` VALUES ('200', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 10:18:26');
INSERT INTO `sys_logininfor` VALUES ('201', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 11:38:47');
INSERT INTO `sys_logininfor` VALUES ('202', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 11:39:09');
INSERT INTO `sys_logininfor` VALUES ('203', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 11:47:14');
INSERT INTO `sys_logininfor` VALUES ('204', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 11:47:18');
INSERT INTO `sys_logininfor` VALUES ('205', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 11:47:26');
INSERT INTO `sys_logininfor` VALUES ('206', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 11:51:02');
INSERT INTO `sys_logininfor` VALUES ('207', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 11:51:33');
INSERT INTO `sys_logininfor` VALUES ('208', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 11:51:37');
INSERT INTO `sys_logininfor` VALUES ('209', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 12:11:06');
INSERT INTO `sys_logininfor` VALUES ('210', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 12:11:10');
INSERT INTO `sys_logininfor` VALUES ('211', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 12:21:27');
INSERT INTO `sys_logininfor` VALUES ('212', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 12:23:12');
INSERT INTO `sys_logininfor` VALUES ('213', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:11:42');
INSERT INTO `sys_logininfor` VALUES ('214', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:19:33');
INSERT INTO `sys_logininfor` VALUES ('215', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 13:19:48');
INSERT INTO `sys_logininfor` VALUES ('216', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:19:52');
INSERT INTO `sys_logininfor` VALUES ('217', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:20:05');
INSERT INTO `sys_logininfor` VALUES ('218', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:23:32');
INSERT INTO `sys_logininfor` VALUES ('219', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:31:23');
INSERT INTO `sys_logininfor` VALUES ('220', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:31:30');
INSERT INTO `sys_logininfor` VALUES ('221', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 13:32:47');
INSERT INTO `sys_logininfor` VALUES ('222', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:32:51');
INSERT INTO `sys_logininfor` VALUES ('223', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:33:09');
INSERT INTO `sys_logininfor` VALUES ('224', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:34:06');
INSERT INTO `sys_logininfor` VALUES ('225', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:34:16');
INSERT INTO `sys_logininfor` VALUES ('226', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:34:25');
INSERT INTO `sys_logininfor` VALUES ('227', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:40:51');
INSERT INTO `sys_logininfor` VALUES ('228', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 13:47:17');
INSERT INTO `sys_logininfor` VALUES ('229', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 13:47:22');
INSERT INTO `sys_logininfor` VALUES ('230', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:24:18');
INSERT INTO `sys_logininfor` VALUES ('231', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:36:36');
INSERT INTO `sys_logininfor` VALUES ('232', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:42:15');
INSERT INTO `sys_logininfor` VALUES ('233', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-06 17:43:34');
INSERT INTO `sys_logininfor` VALUES ('234', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:43:38');
INSERT INTO `sys_logininfor` VALUES ('235', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '退出成功', '2019-05-06 17:44:27');
INSERT INTO `sys_logininfor` VALUES ('236', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:44:31');
INSERT INTO `sys_logininfor` VALUES ('237', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '1', '验证码错误', '2019-05-06 17:49:49');
INSERT INTO `sys_logininfor` VALUES ('238', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:49:53');
INSERT INTO `sys_logininfor` VALUES ('239', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:51:01');
INSERT INTO `sys_logininfor` VALUES ('240', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 17:54:00');
INSERT INTO `sys_logininfor` VALUES ('241', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-06 19:36:35');
INSERT INTO `sys_logininfor` VALUES ('242', 'admin', '127.0.0.1', '内网IP', 'Chrome Mobile', 'Android 6.x', '0', '登录成功', '2019-05-07 17:17:18');
INSERT INTO `sys_logininfor` VALUES ('243', 'admin', '127.0.0.1', '内网IP', 'Chrome Mobile', 'Android 6.x', '0', '登录成功', '2019-05-09 11:41:47');
INSERT INTO `sys_logininfor` VALUES ('244', 'admin', '127.0.0.1', '内网IP', 'Chrome', 'Windows 10', '0', '登录成功', '2019-05-09 19:21:13');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1058 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '1', '#', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES ('2', '系统监控', '0', '2', '#', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES ('3', '系统工具', '0', '3', '#', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES ('100', '用户管理', '1', '1', '/system/user', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu` VALUES ('101', '角色管理', '1', '2', '/system/role', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu` VALUES ('102', '菜单管理', '1', '3', '/system/menu', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES ('103', '部门管理', '1', '4', '/system/dept', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO `sys_menu` VALUES ('104', '岗位管理', '1', '5', '/system/post', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO `sys_menu` VALUES ('105', '字典管理', '1', '6', '/system/dict', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO `sys_menu` VALUES ('106', '参数设置', '1', '7', '/system/config', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu` VALUES ('107', '通知公告', '1', '8', '/system/notice', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu` VALUES ('108', '日志管理', '1', '9', '#', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu` VALUES ('109', '在线用户', '2', '1', '/monitor/online', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu` VALUES ('110', '定时任务', '2', '2', '/monitor/job', 'C', '0', 'monitor:job:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu` VALUES ('111', '数据监控', '2', '3', '/monitor/data', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu` VALUES ('112', '服务监控', '2', '3', '/monitor/server', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu` VALUES ('113', '表单构建', '3', '1', '/tool/build', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu` VALUES ('114', '代码生成', '3', '2', '/tool/gen', 'C', '0', 'tool:gen:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu` VALUES ('115', '系统接口', '3', '3', '/tool/swagger', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu` VALUES ('500', '操作日志', '108', '1', '/monitor/operlog', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu` VALUES ('501', '登录日志', '108', '2', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu` VALUES ('1000', '用户查询', '100', '1', '#', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '100', '2', '#', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1002', '用户修改', '100', '3', '#', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1003', '用户删除', '100', '4', '#', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1004', '用户导出', '100', '5', '#', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1005', '用户导入', '100', '6', '#', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1006', '重置密码', '100', '7', '#', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1007', '角色查询', '101', '1', '#', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1008', '角色新增', '101', '2', '#', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1009', '角色修改', '101', '3', '#', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1010', '角色删除', '101', '4', '#', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1011', '角色导出', '101', '5', '#', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1012', '菜单查询', '102', '1', '#', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1013', '菜单新增', '102', '2', '#', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1014', '菜单修改', '102', '3', '#', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1015', '菜单删除', '102', '4', '#', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1016', '部门查询', '103', '1', '#', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1017', '部门新增', '103', '2', '#', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1018', '部门修改', '103', '3', '#', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1019', '部门删除', '103', '4', '#', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1020', '岗位查询', '104', '1', '#', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1021', '岗位新增', '104', '2', '#', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1022', '岗位修改', '104', '3', '#', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1023', '岗位删除', '104', '4', '#', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1024', '岗位导出', '104', '5', '#', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1025', '字典查询', '105', '1', '#', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1026', '字典新增', '105', '2', '#', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1027', '字典修改', '105', '3', '#', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1028', '字典删除', '105', '4', '#', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1029', '字典导出', '105', '5', '#', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1030', '参数查询', '106', '1', '#', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1031', '参数新增', '106', '2', '#', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1032', '参数修改', '106', '3', '#', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1033', '参数删除', '106', '4', '#', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1034', '参数导出', '106', '5', '#', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1035', '公告查询', '107', '1', '#', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1036', '公告新增', '107', '2', '#', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1037', '公告修改', '107', '3', '#', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1038', '公告删除', '107', '4', '#', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1039', '操作查询', '500', '1', '#', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1040', '操作删除', '500', '2', '#', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1041', '详细信息', '500', '3', '#', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1042', '日志导出', '500', '4', '#', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1043', '登录查询', '501', '1', '#', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1044', '登录删除', '501', '2', '#', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1045', '日志导出', '501', '3', '#', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1046', '在线查询', '109', '1', '#', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1047', '批量强退', '109', '2', '#', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1048', '单条强退', '109', '3', '#', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1049', '任务查询', '110', '1', '#', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1050', '任务新增', '110', '2', '#', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1051', '任务修改', '110', '3', '#', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1052', '任务删除', '110', '4', '#', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1053', '状态修改', '110', '5', '#', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1054', '任务详细', '110', '6', '#', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1055', '任务导出', '110', '7', '#', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1056', '生成查询', '114', '1', '#', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu` VALUES ('1057', '生成代码', '114', '2', '#', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_notice` VALUES ('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('100', '用户管理', '5', 'com.ruoyi.web.controller.system.SysUserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\r\n  \"deptId\" : [ \"\" ],\r\n  \"parentId\" : [ \"\" ],\r\n  \"loginName\" : [ \"\" ],\r\n  \"phonenumber\" : [ \"\" ],\r\n  \"status\" : [ \"\" ],\r\n  \"params[beginTime]\" : [ \"\" ],\r\n  \"params[endTime]\" : [ \"\" ]\r\n}', '0', null, '2019-04-29 13:42:36');
INSERT INTO `sys_oper_log` VALUES ('101', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-02 17:42:19');
INSERT INTO `sys_oper_log` VALUES ('102', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.update()', '1', 'admin', '研发部门', '/system/user/profile/update', '127.0.0.1', '内网IP', '{\r\n  \"id\" : [ \"\" ],\r\n  \"userName\" : [ \"若依\" ],\r\n  \"phonenumber\" : [ \"15888888888\" ],\r\n  \"email\" : [ \"ry@163.com\" ],\r\n  \"sex\" : [ \"1\" ]\r\n}', '0', null, '2019-05-02 17:42:24');
INSERT INTO `sys_oper_log` VALUES ('103', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-02 18:00:24');
INSERT INTO `sys_oper_log` VALUES ('104', '个人信息', '2', 'com.ruoyi.web.controller.system.SysProfileController.updateAvatar()', '1', 'admin', '研发部门', '/system/user/profile/updateAvatar', '127.0.0.1', '内网IP', '{ }', '0', null, '2019-05-02 18:01:24');
INSERT INTO `sys_oper_log` VALUES ('105', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"1\"],\"status\":[\"1\"]}', '1', '不允许修改超级管理员用户', '2019-05-04 10:36:20');
INSERT INTO `sys_oper_log` VALUES ('106', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-04 10:36:23');
INSERT INTO `sys_oper_log` VALUES ('107', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-04 10:36:26');
INSERT INTO `sys_oper_log` VALUES ('108', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-04 10:36:51');
INSERT INTO `sys_oper_log` VALUES ('109', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-04 10:36:53');
INSERT INTO `sys_oper_log` VALUES ('110', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-04 10:38:43');
INSERT INTO `sys_oper_log` VALUES ('111', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-04 10:40:49');
INSERT INTO `sys_oper_log` VALUES ('112', '重置密码', '2', 'com.ruoyi.project.system.user.controller.UserController.resetPwd()', '1', 'admin', '研发部门', '/system/user/resetPwd/2', '127.0.0.1', '内网IP', '{}', '0', null, '2019-05-04 11:00:13');
INSERT INTO `sys_oper_log` VALUES ('113', '用户管理', '5', 'com.ruoyi.project.system.user.controller.UserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\"deptId\":[\"\"],\"parentId\":[\"\"],\"loginName\":[\"\"],\"phonenumber\":[\"\"],\"status\":[\"\"],\"params[beginTime]\":[\"\"],\"params[endTime]\":[\"\"]}', '0', null, '2019-05-04 12:05:35');
INSERT INTO `sys_oper_log` VALUES ('114', '用户管理', '5', 'com.ruoyi.project.system.user.controller.UserController.export()', '1', 'admin', '研发部门', '/system/user/export', '127.0.0.1', '内网IP', '{\"deptId\":[\"\"],\"parentId\":[\"\"],\"loginName\":[\"\"],\"phonenumber\":[\"\"],\"status\":[\"\"],\"params[beginTime]\":[\"\"],\"params[endTime]\":[\"\"]}', '0', null, '2019-05-04 12:06:07');
INSERT INTO `sys_oper_log` VALUES ('115', '用户管理', '1', 'com.ruoyi.project.system.user.controller.UserController.addSave()', '1', 'admin', '研发部门', '/system/user/add', '127.0.0.1', '内网IP', '{\"deptId\":[\"103\"],\"userName\":[\"黄容\"],\"deptName\":[\"研发部门\"],\"phonenumber\":[\"18772101525\"],\"email\":[\"1661605308@qq.com\"],\"loginName\":[\"hr\"],\"password\":[\"123456\"],\"sex\":[\"0\"],\"role\":[\"1\"],\"remark\":[\"\"],\"status\":[\"0\"],\"roleIds\":[\"1\"],\"postIds\":[\"2\"]}', '0', null, '2019-05-04 14:45:40');
INSERT INTO `sys_oper_log` VALUES ('116', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.editSave()', '1', 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\"userId\":[\"3\"],\"deptId\":[\"103\"],\"userName\":[\"黄容\"],\"dept.deptName\":[\"研发部门\"],\"phonenumber\":[\"18772101525\"],\"email\":[\"1661605308@qq.com\"],\"loginName\":[\"hr\"],\"sex\":[\"0\"],\"role\":[\"1\",\"2\"],\"remark\":[\"\"],\"status\":[\"0\"],\"roleIds\":[\"1,2\"],\"postIds\":[\"2\"]}', '0', null, '2019-05-04 14:46:31');
INSERT INTO `sys_oper_log` VALUES ('117', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.editSave()', '1', 'hr', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\"userId\":[\"3\"],\"deptId\":[\"103\"],\"userName\":[\"黄容\"],\"dept.deptName\":[\"研发部门\"],\"phonenumber\":[\"18772101525\"],\"email\":[\"1661605308@qq.com\"],\"loginName\":[\"hr\"],\"sex\":[\"0\"],\"role\":[\"1\"],\"remark\":[\"\"],\"status\":[\"0\"],\"roleIds\":[\"1\"],\"postIds\":[\"2\"]}', '0', null, '2019-05-04 14:47:15');
INSERT INTO `sys_oper_log` VALUES ('118', '角色管理', '4', 'com.ruoyi.project.system.role.controller.RoleController.cancelAuthUser()', '1', 'admin', '研发部门', '/system/role/authUser/cancel', '127.0.0.1', '内网IP', '{\"roleId\":[\"1\"],\"userId\":[\"3\"]}', '0', null, '2019-05-04 14:48:25');
INSERT INTO `sys_oper_log` VALUES ('119', '角色管理', '4', 'com.ruoyi.project.system.role.controller.RoleController.cancelAuthUser()', '1', 'admin', '研发部门', '/system/role/authUser/cancel', '127.0.0.1', '内网IP', '{\"roleId\":[\"1\"],\"userId\":[\"1\"]}', '0', null, '2019-05-04 14:48:28');
INSERT INTO `sys_oper_log` VALUES ('120', '角色管理', '4', 'com.ruoyi.project.system.role.controller.RoleController.cancelAuthUser()', '1', 'admin', '研发部门', '/system/role/authUser/cancel', '127.0.0.1', '内网IP', '{\"roleId\":[\"2\"],\"userId\":[\"2\"]}', '0', null, '2019-05-04 14:48:35');
INSERT INTO `sys_oper_log` VALUES ('121', '角色管理', '4', 'com.ruoyi.project.system.role.controller.RoleController.selectAuthUserAll()', '1', 'admin', '研发部门', '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '{\"roleId\":[\"2\"],\"userIds\":[\"1\"]}', '0', null, '2019-05-04 14:49:48');
INSERT INTO `sys_oper_log` VALUES ('122', '角色管理', '4', 'com.ruoyi.project.system.role.controller.RoleController.selectAuthUserAll()', '1', 'admin', '研发部门', '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '{\"roleId\":[\"2\"],\"userIds\":[\"3\"]}', '0', null, '2019-05-04 14:50:01');
INSERT INTO `sys_oper_log` VALUES ('123', '角色管理', '4', 'com.ruoyi.project.system.role.controller.RoleController.selectAuthUserAll()', '1', 'admin', '研发部门', '/system/role/authUser/selectAll', '127.0.0.1', '内网IP', '{\"roleId\":[\"1\"],\"userIds\":[\"3,1\"]}', '0', null, '2019-05-04 14:50:14');
INSERT INTO `sys_oper_log` VALUES ('124', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:24:27');
INSERT INTO `sys_oper_log` VALUES ('125', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '1', 'Couldn\'t obtain triggers for job: No record found for selection of Trigger with key: \'DEFAULT.TASK_CLASS_NAME1\' and statement: SELECT * FROM QRTZ_CRON_TRIGGERS WHERE SCHED_NAME = \'RuoyiScheduler\' AND TRIGGER_NAME = ? AND TRIGGER_GROUP = ?', '2019-05-06 17:24:32');
INSERT INTO `sys_oper_log` VALUES ('126', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:24:51');
INSERT INTO `sys_oper_log` VALUES ('127', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:24:55');
INSERT INTO `sys_oper_log` VALUES ('128', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:24:59');
INSERT INTO `sys_oper_log` VALUES ('129', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:27:52');
INSERT INTO `sys_oper_log` VALUES ('130', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:27:55');
INSERT INTO `sys_oper_log` VALUES ('131', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:27:57');
INSERT INTO `sys_oper_log` VALUES ('132', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:27:59');
INSERT INTO `sys_oper_log` VALUES ('133', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '1', 'Couldn\'t store trigger \'DEFAULT.MT_1q70bu48bf7a5\' for \'DEFAULT.TASK_CLASS_NAME1\' job:The job (DEFAULT.TASK_CLASS_NAME1) referenced by the trigger does not exist.', '2019-05-06 17:43:06');
INSERT INTO `sys_oper_log` VALUES ('134', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:43:44');
INSERT INTO `sys_oper_log` VALUES ('135', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:43:47');
INSERT INTO `sys_oper_log` VALUES ('136', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:43:49');
INSERT INTO `sys_oper_log` VALUES ('137', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:43:51');
INSERT INTO `sys_oper_log` VALUES ('138', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '1', 'Couldn\'t store trigger \'DEFAULT.MT_5k6bdmapruegq\' for \'DEFAULT.TASK_CLASS_NAME1\' job:The job (DEFAULT.TASK_CLASS_NAME1) referenced by the trigger does not exist.', '2019-05-06 17:43:58');
INSERT INTO `sys_oper_log` VALUES ('139', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '1', 'Couldn\'t store trigger \'DEFAULT.MT_4bbmar6gvr30o\' for \'DEFAULT.TASK_CLASS_NAME1\' job:The job (DEFAULT.TASK_CLASS_NAME1) referenced by the trigger does not exist.', '2019-05-06 17:44:25');
INSERT INTO `sys_oper_log` VALUES ('140', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '1', 'Couldn\'t store trigger \'DEFAULT.MT_5gnv8is7uelo\' for \'DEFAULT.TASK_CLASS_NAME1\' job:The job (DEFAULT.TASK_CLASS_NAME1) referenced by the trigger does not exist.', '2019-05-06 17:44:36');
INSERT INTO `sys_oper_log` VALUES ('141', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:49:58');
INSERT INTO `sys_oper_log` VALUES ('142', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:50:02');
INSERT INTO `sys_oper_log` VALUES ('143', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"]}', '0', null, '2019-05-06 17:50:04');
INSERT INTO `sys_oper_log` VALUES ('144', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:50:07');
INSERT INTO `sys_oper_log` VALUES ('145', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:50:10');
INSERT INTO `sys_oper_log` VALUES ('146', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:50:12');
INSERT INTO `sys_oper_log` VALUES ('147', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:50:14');
INSERT INTO `sys_oper_log` VALUES ('148', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:50:17');
INSERT INTO `sys_oper_log` VALUES ('149', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:50:27');
INSERT INTO `sys_oper_log` VALUES ('150', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"]}', '0', null, '2019-05-06 17:50:29');
INSERT INTO `sys_oper_log` VALUES ('151', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:50:32');
INSERT INTO `sys_oper_log` VALUES ('152', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:51:07');
INSERT INTO `sys_oper_log` VALUES ('153', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:51:09');
INSERT INTO `sys_oper_log` VALUES ('154', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:51:11');
INSERT INTO `sys_oper_log` VALUES ('155', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:51:14');
INSERT INTO `sys_oper_log` VALUES ('156', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"]}', '0', null, '2019-05-06 17:51:16');
INSERT INTO `sys_oper_log` VALUES ('157', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:51:19');
INSERT INTO `sys_oper_log` VALUES ('158', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:51:21');
INSERT INTO `sys_oper_log` VALUES ('159', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"]}', '0', null, '2019-05-06 17:51:30');
INSERT INTO `sys_oper_log` VALUES ('160', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.run()', '1', 'admin', '研发部门', '/monitor/job/run', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"]}', '0', null, '2019-05-06 17:51:33');
INSERT INTO `sys_oper_log` VALUES ('161', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 17:51:36');
INSERT INTO `sys_oper_log` VALUES ('162', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"0\"]}', '0', null, '2019-05-06 17:51:39');
INSERT INTO `sys_oper_log` VALUES ('163', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"1\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 19:36:42');
INSERT INTO `sys_oper_log` VALUES ('164', '定时任务', '2', 'com.ruoyi.project.monitor.job.controller.JobController.changeStatus()', '1', 'admin', '研发部门', '/monitor/job/changeStatus', '127.0.0.1', '内网IP', '{\"jobId\":[\"2\"],\"status\":[\"1\"]}', '0', null, '2019-05-06 19:36:44');
INSERT INTO `sys_oper_log` VALUES ('165', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"3\"],\"status\":[\"1\"]}', '0', null, '2019-05-09 11:42:33');
INSERT INTO `sys_oper_log` VALUES ('166', '用户管理', '2', 'com.ruoyi.project.system.user.controller.UserController.changeStatus()', '1', 'admin', '研发部门', '/system/user/changeStatus', '127.0.0.1', '内网IP', '{\"userId\":[\"3\"],\"status\":[\"0\"]}', '0', null, '2019-05-09 11:42:43');
INSERT INTO `sys_oper_log` VALUES ('167', '重置密码', '2', 'com.ruoyi.project.system.user.controller.UserController.resetPwd()', '1', 'admin', '研发部门', '/system/user/resetPwd/3', '127.0.0.1', '内网IP', '{}', '0', null, '2019-05-09 12:08:26');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'ceo', '董事长', '1', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('2', 'se', '项目经理', '2', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('3', 'hr', '人力资源', '3', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_post` VALUES ('4', 'user', '普通员工', '4', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '1', '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES ('2', '普通角色', 'common', '2', '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('2', '100');
INSERT INTO `sys_role_dept` VALUES ('2', '101');
INSERT INTO `sys_role_dept` VALUES ('2', '105');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '100');
INSERT INTO `sys_role_menu` VALUES ('2', '101');
INSERT INTO `sys_role_menu` VALUES ('2', '102');
INSERT INTO `sys_role_menu` VALUES ('2', '103');
INSERT INTO `sys_role_menu` VALUES ('2', '104');
INSERT INTO `sys_role_menu` VALUES ('2', '105');
INSERT INTO `sys_role_menu` VALUES ('2', '106');
INSERT INTO `sys_role_menu` VALUES ('2', '107');
INSERT INTO `sys_role_menu` VALUES ('2', '108');
INSERT INTO `sys_role_menu` VALUES ('2', '109');
INSERT INTO `sys_role_menu` VALUES ('2', '110');
INSERT INTO `sys_role_menu` VALUES ('2', '111');
INSERT INTO `sys_role_menu` VALUES ('2', '112');
INSERT INTO `sys_role_menu` VALUES ('2', '113');
INSERT INTO `sys_role_menu` VALUES ('2', '114');
INSERT INTO `sys_role_menu` VALUES ('2', '115');
INSERT INTO `sys_role_menu` VALUES ('2', '500');
INSERT INTO `sys_role_menu` VALUES ('2', '501');
INSERT INTO `sys_role_menu` VALUES ('2', '1000');
INSERT INTO `sys_role_menu` VALUES ('2', '1001');
INSERT INTO `sys_role_menu` VALUES ('2', '1002');
INSERT INTO `sys_role_menu` VALUES ('2', '1003');
INSERT INTO `sys_role_menu` VALUES ('2', '1004');
INSERT INTO `sys_role_menu` VALUES ('2', '1005');
INSERT INTO `sys_role_menu` VALUES ('2', '1006');
INSERT INTO `sys_role_menu` VALUES ('2', '1007');
INSERT INTO `sys_role_menu` VALUES ('2', '1008');
INSERT INTO `sys_role_menu` VALUES ('2', '1009');
INSERT INTO `sys_role_menu` VALUES ('2', '1010');
INSERT INTO `sys_role_menu` VALUES ('2', '1011');
INSERT INTO `sys_role_menu` VALUES ('2', '1012');
INSERT INTO `sys_role_menu` VALUES ('2', '1013');
INSERT INTO `sys_role_menu` VALUES ('2', '1014');
INSERT INTO `sys_role_menu` VALUES ('2', '1015');
INSERT INTO `sys_role_menu` VALUES ('2', '1016');
INSERT INTO `sys_role_menu` VALUES ('2', '1017');
INSERT INTO `sys_role_menu` VALUES ('2', '1018');
INSERT INTO `sys_role_menu` VALUES ('2', '1019');
INSERT INTO `sys_role_menu` VALUES ('2', '1020');
INSERT INTO `sys_role_menu` VALUES ('2', '1021');
INSERT INTO `sys_role_menu` VALUES ('2', '1022');
INSERT INTO `sys_role_menu` VALUES ('2', '1023');
INSERT INTO `sys_role_menu` VALUES ('2', '1024');
INSERT INTO `sys_role_menu` VALUES ('2', '1025');
INSERT INTO `sys_role_menu` VALUES ('2', '1026');
INSERT INTO `sys_role_menu` VALUES ('2', '1027');
INSERT INTO `sys_role_menu` VALUES ('2', '1028');
INSERT INTO `sys_role_menu` VALUES ('2', '1029');
INSERT INTO `sys_role_menu` VALUES ('2', '1030');
INSERT INTO `sys_role_menu` VALUES ('2', '1031');
INSERT INTO `sys_role_menu` VALUES ('2', '1032');
INSERT INTO `sys_role_menu` VALUES ('2', '1033');
INSERT INTO `sys_role_menu` VALUES ('2', '1034');
INSERT INTO `sys_role_menu` VALUES ('2', '1035');
INSERT INTO `sys_role_menu` VALUES ('2', '1036');
INSERT INTO `sys_role_menu` VALUES ('2', '1037');
INSERT INTO `sys_role_menu` VALUES ('2', '1038');
INSERT INTO `sys_role_menu` VALUES ('2', '1039');
INSERT INTO `sys_role_menu` VALUES ('2', '1040');
INSERT INTO `sys_role_menu` VALUES ('2', '1041');
INSERT INTO `sys_role_menu` VALUES ('2', '1042');
INSERT INTO `sys_role_menu` VALUES ('2', '1043');
INSERT INTO `sys_role_menu` VALUES ('2', '1044');
INSERT INTO `sys_role_menu` VALUES ('2', '1045');
INSERT INTO `sys_role_menu` VALUES ('2', '1046');
INSERT INTO `sys_role_menu` VALUES ('2', '1047');
INSERT INTO `sys_role_menu` VALUES ('2', '1048');
INSERT INTO `sys_role_menu` VALUES ('2', '1049');
INSERT INTO `sys_role_menu` VALUES ('2', '1050');
INSERT INTO `sys_role_menu` VALUES ('2', '1051');
INSERT INTO `sys_role_menu` VALUES ('2', '1052');
INSERT INTO `sys_role_menu` VALUES ('2', '1053');
INSERT INTO `sys_role_menu` VALUES ('2', '1054');
INSERT INTO `sys_role_menu` VALUES ('2', '1055');
INSERT INTO `sys_role_menu` VALUES ('2', '1056');
INSERT INTO `sys_role_menu` VALUES ('2', '1057');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '2019/05/02/3ee61364208c0af2bd08c46a2d4e4ff3.jpg', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2019-05-09 19:21:13', 'admin', '2018-03-16 11:33:00', 'ry', '2019-05-09 19:21:13', '管理员');
INSERT INTO `sys_user` VALUES ('2', '105', 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2018-03-16 11:33:00', 'admin', '2018-03-16 11:33:00', 'ry', '2019-05-04 10:40:49', '测试员');
INSERT INTO `sys_user` VALUES ('3', '103', 'hr', '黄容', '00', '1661605308@qq.com', '18772101525', '0', '', '43174790574faa99b61638388bb7a3e8', '7a94f7', '0', '0', '127.0.0.1', '2019-05-04 16:05:36', 'admin', '2019-05-04 14:45:40', 'hr', '2019-05-09 11:42:43', '');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('35c3cc2d-ff0a-4691-9409-12937fdf8cd4', 'admin', '研发部门', '127.0.0.1', '内网IP', 'Chrome Mobile', 'Android 6.x', 'on_line', '2019-05-09 19:21:05', '2019-05-09 19:21:14', '1800000');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2');
INSERT INTO `sys_user_post` VALUES ('3', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1');
INSERT INTO `sys_user_role` VALUES ('3', '2');

-- ----------------------------
-- Table structure for t_sys_param
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_param`;
CREATE TABLE `t_sys_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `system` varchar(50) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL COMMENT '参数值',
  `remark` varchar(50) DEFAULT NULL,
  `oper_id` varchar(8) DEFAULT NULL,
  `up_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改日期',
  `up_time` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='参数表';

-- ----------------------------
-- Records of t_sys_param
-- ----------------------------
INSERT INTO `t_sys_param` VALUES ('1', 'verifyInvalidDuration', 'lcwl', '15', '验证码失效时长(单位/分)<发了之后不验证多久失效>', null, null, null);
INSERT INTO `t_sys_param` VALUES ('2', 'verifySendDuration', 'lcwl', '1', '验证码发送时长(单位/分)<发了之后过多久再次发>', null, null, null);
INSERT INTO `t_sys_param` VALUES ('3', 'verifySendHourLimit', 'lcwl', '10', '验证码每小时发送次数限制', '', null, null);
INSERT INTO `t_sys_param` VALUES ('4', 'verifySendDayLimit', 'lcwl', '50', '验证码每天发送次数限制', '', null, null);

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `user_tel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '注册手机号',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '真实名',
  `id_card` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `login_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `pay_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付密码',
  `purse_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '星星链接地址',
  `addr_pass` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址密码',
  `refer_num` int(11) DEFAULT '0' COMMENT '直推人数汇总',
  `under_num` int(11) DEFAULT '0' COMMENT '能量数量（伞下人数）',
  `head_photo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `referer_id` int(11) DEFAULT NULL COMMENT '推荐人',
  `up_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改日期',
  `up_time` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改时间',
  `cre_date` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建日期',
  `cre_time` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建时间',
  `order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务ID',
  `op_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '业务类型类型（01-兑换 02-充币 03-提币 04-购买 05-售卖 06-购买宠物 07-完善资料 08-实名认证 09-推荐奖  10-偷取 11-摘取 12-等级奖励）',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '账号状态（0：不可用，1：可用）',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '最后访问ip',
  `login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `frozen_time` datetime DEFAULT NULL COMMENT '冻结时间',
  `frozen_reason` text COMMENT '冻结原因',
  `algebra` int(11) DEFAULT '0' COMMENT '代数',
  `parent_chain` varchar(2000) DEFAULT NULL COMMENT '父级链',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_INDEX` (`user_tel`),
  UNIQUE KEY `NAME_INDEX` (`user_name`) USING BTREE,
  UNIQUE KEY `ADDRESS_INDEX` (`purse_address`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26001 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('25998', null, '18772101525', null, null, '8f25cdce894231febb9d76adbccea7b1', 'dff45588cb53b99de3795fa149227b69', null, null, '1', '2', null, null, null, null, '20190508', '222808', null, null, '1', '0:0:0:0:0:0:0:1', '2019-05-09 19:01:09', null, null, '1', null);
INSERT INTO `t_user_info` VALUES ('25999', null, '18772101527', null, null, 'e938158e21564f4e462a0ba00ed643d1', 'f71832d9cd3ba4b02871376eeea7b45f', null, null, '1', '1', null, '25998', null, null, '20190508', '223037', null, null, '1', null, '2019-05-08 22:45:22', null, null, '2', '25998');
INSERT INTO `t_user_info` VALUES ('26000', null, '18772101529', null, null, '154588fa50427ce42ee9a3e6e269180f', 'b9d4a0046d0d9d3fada2e022fe106308', null, null, '0', '0', null, '25999', null, null, '20190508', '224522', null, null, '1', null, null, null, null, '3', '25998,25999');

-- ----------------------------
-- Table structure for t_verify_record
-- ----------------------------
DROP TABLE IF EXISTS `t_verify_record`;
CREATE TABLE `t_verify_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `system_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bus_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `acc_type` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `account` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '状态 0发送成功 1已被验证  2已失效',
  `create_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `send_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `invalid_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `verify_time` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `msg_template` varchar(8) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '短信模板',
  PRIMARY KEY (`id`),
  KEY `P_INDEX_VERIFY_RECORD` (`user_id`,`bus_type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of t_verify_record
-- ----------------------------
INSERT INTO `t_verify_record` VALUES ('2', '18772101525', null, 'systemFront', 'Register', '1', '18772101525', '084907', '0', '2019-05-08 15:38:04', '2019-05-08 15:38:04', '2019-05-08 15:53:04', null, '119557');
INSERT INTO `t_verify_record` VALUES ('3', '18772101525', null, 'systemFront', 'Register', '1', '18772101525', '406866', '0', '2019-05-08 15:43:26', '2019-05-08 15:43:26', '2019-05-08 15:58:26', null, '119557');
INSERT INTO `t_verify_record` VALUES ('4', '18772101525', null, 'systemFront', 'Register', '1', '18772101525', '667294', '0', '2019-05-08 15:47:47', '2019-05-08 15:47:47', '2019-05-08 16:02:47', null, '119557');
INSERT INTO `t_verify_record` VALUES ('5', '18772101529', null, 'systemFront', 'Register', '1', '18772101529', '873048', '1', '2019-05-08 22:41:13', '2019-05-08 22:41:13', '2019-05-08 22:56:13', '2019-05-08 22:45:22', '119557');
INSERT INTO `t_verify_record` VALUES ('6', '18772101525', null, 'systemFront', 'ForgetPass', '1', '18772101525', '573706', '1', '2019-05-09 18:59:33', '2019-05-09 18:59:33', '2019-05-09 19:14:33', '2019-05-09 19:00:42', '119557');
