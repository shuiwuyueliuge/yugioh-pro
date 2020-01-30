/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : ygo_config

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-01-30 15:03:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_properties`;
CREATE TABLE `t_properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置信息的键',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置信息值',
  `application` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名',
  `profile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名online/stage',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分支master/develop',
  `auto_refresh` tinyint(2) unsigned DEFAULT '0' COMMENT '0不可以自动刷新，1可以自动刷新',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_properties
-- ----------------------------
INSERT INTO `t_properties` VALUES ('7', 'spring.sleuth.sampler.probability', '1.0', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('8', 'spring.sleuth.web.client.enabled', 'true', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('9', 'spring.zipkin.sender.type', 'web', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('10', 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('11', 'management.endpoint.bus-refresh.enabled', 'true', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('12', 'spring.cloud.bus.trace.enabled', 'true', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('13', 'eureka.client.serviceUrl.defaultZone', 'http://127.0.0.1:9000/eureka/', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('14', 'spring.rabbitmq.host', '127.0.0.1', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('15', 'spring.rabbitmq.port', '5672', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('16', 'spring.rabbitmq.username', 'guest', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('17', 'spring.rabbitmq.password', 'guest', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('18', 'feign.hystrix.enabled', 'true', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('19', 'feign.client.config.default.loggerLevel', 'full', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('20', 'server.port', '9500', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('21', 'management.endpoint.bus-refresh.enabled', 'true', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:11:54');
INSERT INTO `t_properties` VALUES ('22', 'spring.sleuth.sampler.probability', '1.0', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:12:17');
INSERT INTO `t_properties` VALUES ('23', 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:12:43');
INSERT INTO `t_properties` VALUES ('24', 'spring.cloud.bus.trace.enabled', 'true', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:12:59');
INSERT INTO `t_properties` VALUES ('25', 'eureka.client.serviceUrl.defaultZone', 'http://127.0.0.1:9000/eureka/', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:13:16');
INSERT INTO `t_properties` VALUES ('26', 'async.pool.core-size', '3', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:13:30');
INSERT INTO `t_properties` VALUES ('27', 'async.pool.max-size', '5', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:13:43');
INSERT INTO `t_properties` VALUES ('28', 'async.pool.queue-size', '100', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:13:58');
INSERT INTO `t_properties` VALUES ('29', 'async.pool.thread-name-prefix', 'yugioh-sync-home-asyncThread-', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:14:13');
INSERT INTO `t_properties` VALUES ('30', 'spring.rabbitmq.port', '5672', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:14:38');
INSERT INTO `t_properties` VALUES ('31', 'spring.rabbitmq.host', '127.0.0.1', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:14:54');
INSERT INTO `t_properties` VALUES ('32', 'spring.rabbitmq.username', 'guest', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:15:10');
INSERT INTO `t_properties` VALUES ('33', 'spring.rabbitmq.password', 'guest', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:15:23');
INSERT INTO `t_properties` VALUES ('34', 'spring.data.mongodb.host', '127.0.0.1', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:15:36');
INSERT INTO `t_properties` VALUES ('35', 'spring.data.mongodb.port', '27017', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:15:54');
INSERT INTO `t_properties` VALUES ('36', 'spring.data.mongodb.database', 'test', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:16:06');
INSERT INTO `t_properties` VALUES ('37', 'spring.data.mongodb.password', 'yugioh', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:16:20');
INSERT INTO `t_properties` VALUES ('38', 'spring.data.mongodb.username', 'yugioh', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:47', '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES ('47', 'hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds', '8000', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:19:59');
INSERT INTO `t_properties` VALUES ('48', 'sync-home.ribbon.MaxAutoRetries', '0', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:21:31');
INSERT INTO `t_properties` VALUES ('49', 'sync-home.ribbon.MaxAutoRetriesNextServer', '0', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:21:41');
INSERT INTO `t_properties` VALUES ('50', 'sync-home.ribbon.OkToRetryOnAllOperations', 'false', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('51', 'sync-home.ribbon.http.client.enabled', 'true', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('52', 'sync-home.ribbon.ReadTimeout', '2000', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('53', 'sync-home.ribbon.ConnectTimeout', '2000', 'sync-ourocg', 'stage', '0', '0', null, '2020-01-29 15:53:47', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('54', 'yugioh.sync.ourocg.crawing.corn', '*/1 * * * * ?', 'sync-ourocg', 'stage', '0', '0', '', '2020-01-30 14:45:34', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('55', 'yugioh.sync.ourocg.crawing.enabled', 'false', 'sync-ourocg', 'stage', '0', '0', '', '2020-01-30 14:42:08', '2019-12-04 13:21:56');
