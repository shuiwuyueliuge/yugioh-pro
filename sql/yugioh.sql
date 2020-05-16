/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : yugioh

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-05-16 17:14:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_card_adjust
-- ----------------------------
DROP TABLE IF EXISTS `t_card_adjust`;
CREATE TABLE `t_card_adjust` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) NOT NULL COMMENT '卡的主键',
  `adjust` text COMMENT '中文效果',
  `type_val` tinyint(1) DEFAULT '3' COMMENT '卡的类型 3怪兽，2魔法，1陷阱',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卡的效果表';

-- ----------------------------
-- Table structure for t_card_effect
-- ----------------------------
DROP TABLE IF EXISTS `t_card_effect`;
CREATE TABLE `t_card_effect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) NOT NULL COMMENT '卡的主键',
  `effect` text COMMENT '中文效果',
  `effect_nw` text COMMENT '中文效果nw',
  `type_val` tinyint(1) DEFAULT '3' COMMENT '卡的类型 3怪兽，2魔法，1陷阱',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卡的效果表';

-- ----------------------------
-- Table structure for t_card_link
-- ----------------------------
DROP TABLE IF EXISTS `t_card_link`;
CREATE TABLE `t_card_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) NOT NULL COMMENT '卡的主键',
  `link_arrow` tinyint(1) DEFAULT NULL COMMENT '连接指针',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='连接怪兽的指针';

-- ----------------------------
-- Table structure for t_card_package
-- ----------------------------
DROP TABLE IF EXISTS `t_card_package`;
CREATE TABLE `t_card_package` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `race_id` int(11) NOT NULL COMMENT '罕贵度id',
  `package_id` int(11) NOT NULL COMMENT '卡包id',
  `card_id` int(11) NOT NULL COMMENT '卡片id',
  `type_val` tinyint(1) DEFAULT '3' COMMENT '卡的类型 3怪兽，2魔法，1陷阱',
  `number` varchar(15) DEFAULT NULL,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卡对应卡包和罕贵度';

-- ----------------------------
-- Table structure for t_card_type
-- ----------------------------
DROP TABLE IF EXISTS `t_card_type`;
CREATE TABLE `t_card_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) NOT NULL COMMENT '卡的主键',
  `type_st` tinyint(3) DEFAULT NULL COMMENT '种类',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='怪兽卡的种类表';

-- ----------------------------
-- Table structure for t_forbidden
-- ----------------------------
DROP TABLE IF EXISTS `t_forbidden`;
CREATE TABLE `t_forbidden` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(255) DEFAULT NULL,
  `card_id` int(11) DEFAULT NULL,
  `type_val` tinyint(1) DEFAULT '3' COMMENT '卡的类型 3怪兽，2魔法，1陷阱',
  `limit_val` tinyint(1) DEFAULT NULL COMMENT '限制类型 0禁止 1限制 2准限制',
  `limit_time` varchar(255) DEFAULT NULL COMMENT '禁卡表开始时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`card_name`,`limit_val`,`limit_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48400 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='禁卡表';

-- ----------------------------
-- Table structure for t_index
-- ----------------------------
DROP TABLE IF EXISTS `t_index`;
CREATE TABLE `t_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT '' COMMENT '名称',
  `type` tinyint(3) DEFAULT NULL COMMENT '索引类型 1link_arrow, 2attribute, 3race, 4怪兽type_st, 5魔陷type_st，6卡种类, 7专有',
  `type_index` tinyint(4) DEFAULT NULL COMMENT '索引值',
  `img` text,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卡的种族，刻度，属性，类型索引表';

-- ----------------------------
-- Table structure for t_magic_trap
-- ----------------------------
DROP TABLE IF EXISTS `t_magic_trap`;
CREATE TABLE `t_magic_trap` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `hash_id` varchar(255) DEFAULT '' COMMENT '哈希值',
  `password` varchar(10) DEFAULT '' COMMENT '卡片密码',
  `name` varchar(255) DEFAULT '' COMMENT '中文名',
  `name_ja` varchar(255) DEFAULT '' COMMENT '日文名',
  `name_en` varchar(255) DEFAULT '' COMMENT '英文名',
  `name_nw` varchar(255) DEFAULT '' COMMENT 'nw翻译名称',
  `locale` tinyint(1) DEFAULT '3' COMMENT '专有卡：1 ocg专有， 2 tcg专有，3非专有',
  `type_st` tinyint(1) DEFAULT NULL COMMENT '类型',
  `type_val` tinyint(1) DEFAULT '3' COMMENT '卡的类型 2魔法，1陷阱',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='魔法陷阱基础信息表';

-- ----------------------------
-- Table structure for t_monster
-- ----------------------------
DROP TABLE IF EXISTS `t_monster`;
CREATE TABLE `t_monster` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `hash_id` varchar(255) DEFAULT '' COMMENT '哈希值',
  `password` varchar(10) DEFAULT '' COMMENT '卡片密码',
  `name` varchar(255) DEFAULT '' COMMENT '中文名',
  `name_ja` varchar(255) DEFAULT '' COMMENT '日文名',
  `name_en` varchar(255) DEFAULT '' COMMENT '英文名',
  `name_nw` varchar(255) DEFAULT '' COMMENT 'nw翻译名称',
  `locale` tinyint(1) DEFAULT '3' COMMENT '专有卡：1 ocg专有， 2 tcg专有，3非专有',
  `level` tinyint(2) DEFAULT NULL COMMENT '等级',
  `attribute` tinyint(1) DEFAULT NULL COMMENT '属性',
  `race` tinyint(2) DEFAULT NULL COMMENT '种族',
  `atk` int(11) DEFAULT '0' COMMENT '攻击力 ''?'' =-1',
  `def` int(11) DEFAULT '0' COMMENT '防御力 ''?'' = -1, 连接 = -2',
  `pend_l` tinyint(2) DEFAULT '-1' COMMENT '左刻度 不是灵摆怪刻度为-1',
  `pend_r` tinyint(2) DEFAULT '-1' COMMENT '右刻度 不是灵摆怪刻度为-1',
  `link` tinyint(1) DEFAULT '-1' COMMENT '连接值',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='怪兽基础信息表';

-- ----------------------------
-- Table structure for t_package
-- ----------------------------
DROP TABLE IF EXISTS `t_package`;
CREATE TABLE `t_package` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `shot_name` varchar(255) DEFAULT NULL,
  `sell_time` varchar(20) DEFAULT NULL COMMENT '发售时间',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='卡包信息表';

-- ----------------------------
-- Table structure for t_rare
-- ----------------------------
DROP TABLE IF EXISTS `t_rare`;
CREATE TABLE `t_rare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL COMMENT '罕贵度缩写',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='罕贵度种类表';

-- ----------------------------
-- Table structure for t_sync_record
-- ----------------------------
DROP TABLE IF EXISTS `t_sync_record`;
CREATE TABLE `t_sync_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) DEFAULT NULL COMMENT '更新说明,',
  `status` tinyint(4) DEFAULT '0' COMMENT '0新增，1更新',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
