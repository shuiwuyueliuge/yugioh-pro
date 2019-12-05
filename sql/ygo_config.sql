/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : ygo_config

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 05/12/2019 10:23:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_properties`;
CREATE TABLE `t_properties`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置信息的键',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置信息值',
  `application` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名',
  `profile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名online/stage',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分支master/develop',
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_properties
-- ----------------------------
INSERT INTO `t_properties` VALUES (7, 'spring.sleuth.sampler.probability', '1.0', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (8, 'spring.sleuth.web.client.enabled', 'true', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (9, 'spring.zipkin.sender.type', 'web', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (10, 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (11, 'management.endpoint.bus-refresh.enabled', 'true', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (12, 'spring.cloud.bus.trace.enabled', 'true', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (13, 'eureka.client.serviceUrl.defaultZone', 'http://127.0.0.1:9000/eureka/', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (14, 'spring.rabbitmq.host', '127.0.0.1', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (15, 'spring.rabbitmq.port', '5672', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (16, 'spring.rabbitmq.username', 'guest', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (17, 'spring.rabbitmq.password', 'guest', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (18, 'feign.hystrix.enabled', 'true', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (19, 'feign.client.config.default.loggerLevel', 'full', 'sync-ougocg', 'stage', '0', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (20, 'server.port', '9500', 'sync-home', 'stage', 'develop', '2019-12-03 11:06:27', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES (21, 'management.endpoint.bus-refresh.enabled', 'true', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:11:54');
INSERT INTO `t_properties` VALUES (22, 'spring.sleuth.sampler.probability', '1.0', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:12:17');
INSERT INTO `t_properties` VALUES (23, 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:12:43');
INSERT INTO `t_properties` VALUES (24, 'spring.cloud.bus.trace.enabled', 'true', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:12:59');
INSERT INTO `t_properties` VALUES (25, 'eureka.client.serviceUrl.defaultZone', 'http://127.0.0.1:9000/eureka/', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:13:16');
INSERT INTO `t_properties` VALUES (26, 'async.pool.core-size', '3', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:13:30');
INSERT INTO `t_properties` VALUES (27, 'async.pool.max-size', '5', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:13:43');
INSERT INTO `t_properties` VALUES (28, 'async.pool.queue-size', '100', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:13:58');
INSERT INTO `t_properties` VALUES (29, 'async.pool.thread-name-prefix', 'yugioh-sync-home-asyncThread-', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:14:13');
INSERT INTO `t_properties` VALUES (30, 'spring.rabbitmq.port', '5672', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:14:38');
INSERT INTO `t_properties` VALUES (31, 'spring.rabbitmq.host', '127.0.0.1', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:14:54');
INSERT INTO `t_properties` VALUES (32, 'spring.rabbitmq.username', 'guest', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:15:10');
INSERT INTO `t_properties` VALUES (33, 'spring.rabbitmq.password', 'guest', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:15:23');
INSERT INTO `t_properties` VALUES (34, 'spring.data.mongodb.host', '127.0.0.1', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:15:36');
INSERT INTO `t_properties` VALUES (35, 'spring.data.mongodb.port', '27017', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:15:54');
INSERT INTO `t_properties` VALUES (36, 'spring.data.mongodb.database', 'test', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:16:06');
INSERT INTO `t_properties` VALUES (37, 'spring.data.mongodb.password', 'yugioh', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:16:20');
INSERT INTO `t_properties` VALUES (38, 'spring.data.mongodb.username', 'yugioh', 'sync-home', 'stage', 'develop', NULL, '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES (45, 'feign.client.config.default.connect-timeout', '5000', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:18:01');
INSERT INTO `t_properties` VALUES (46, 'feign.client.config.default.readTimeout', '5000', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:18:19');
INSERT INTO `t_properties` VALUES (47, 'hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds', '8000', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:19:59');
INSERT INTO `t_properties` VALUES (48, 'ribbon.MaxAutoRetries', '0', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:21:31');
INSERT INTO `t_properties` VALUES (49, 'ribbon.MaxAutoRetriesNextServer', '0', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:21:41');
INSERT INTO `t_properties` VALUES (50, 'ribbon.OkToRetryOnAllOperations', 'false', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES (51, 'logging.level.cn.mayu.yugioh.common.core.html', 'debug', 'sync-ougocg', 'stage', '0', NULL, '2019-12-04 13:32:16');

SET FOREIGN_KEY_CHECKS = 1;
