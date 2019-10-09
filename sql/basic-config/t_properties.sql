/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : yogioh

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-10-09 13:40:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_properties`;
CREATE TABLE `t_properties` (
  `id` int(11) NOT NULL,
  `key` varchar(50) NOT NULL COMMENT '配置信息的键',
  `value` varchar(500) NOT NULL COMMENT '配置信息值',
  `application` varchar(50) NOT NULL COMMENT '应用名',
  `profile` varchar(50) NOT NULL COMMENT '文件名',
  `label` varchar(50) NOT NULL COMMENT '分支',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_properties
-- ----------------------------
INSERT INTO `t_properties` VALUES ('1', 'com.didispace.message', 'test-stage-master', 'reptile-ourocg', 'stage', 'master', null, '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('2', 'com.didispace.message', 'test-online-master', 'reptile-ourocg', 'online', 'master', null, '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('3', 'com.didispace.message', 'test-online-develop', 'reptile-ourocg', 'online', 'develop', null, '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('4', 'com.didispace.message', 'hello-online-master', 'hello-service', 'online', 'master', null, '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('5', 'com.didispace.message', 'hello-online-develop', 'hello-service', 'online', 'develop', null, '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('6', 'server.port', '9101', 'reptile-ourocg', 'stage', '1', null, '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('7', 'server.port', '9102', 'reptile-ourocg', 'stage', '2', null, '2019-10-09 13:39:11');
