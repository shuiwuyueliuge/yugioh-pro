/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : ygo_config

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-02-01 14:06:37
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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_properties
-- ----------------------------
INSERT INTO `t_properties` VALUES ('7', 'spring.sleuth.sampler.probability', '1.0', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('8', 'spring.sleuth.web.client.enabled', 'true', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('9', 'spring.zipkin.sender.type', 'web', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('10', 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('11', 'management.endpoint.bus-refresh.enabled', 'true', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('12', 'spring.cloud.bus.trace.enabled', 'true', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('13', 'eureka.client.serviceUrl.defaultZone', 'http://ygo:ygo@127.0.0.1:9000/eureka/', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-02-01 13:41:44', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('14', 'spring.rabbitmq.host', '127.0.0.1', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('15', 'spring.rabbitmq.port', '5672', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('16', 'spring.rabbitmq.username', 'guest', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('17', 'spring.rabbitmq.password', 'guest', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('18', 'feign.hystrix.enabled', 'true', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('19', 'feign.client.config.default.loggerLevel', 'full', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-30 19:23:28', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('20', 'server.port', '9500', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('21', 'management.endpoint.bus-refresh.enabled', 'true', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:11:54');
INSERT INTO `t_properties` VALUES ('22', 'spring.sleuth.sampler.probability', '1.0', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:12:17');
INSERT INTO `t_properties` VALUES ('23', 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:12:43');
INSERT INTO `t_properties` VALUES ('24', 'spring.cloud.bus.trace.enabled', 'true', 'sync-home', 'stage', 'develop', '0', null, '2020-01-29 15:53:46', '2019-12-03 11:12:59');
INSERT INTO `t_properties` VALUES ('25', 'eureka.client.serviceUrl.defaultZone', 'http://ygo:ygo@127.0.0.1:9000/eureka/', 'sync-home', 'stage', 'develop', '0', null, '2020-02-01 13:41:44', '2019-12-03 11:13:16');
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
INSERT INTO `t_properties` VALUES ('47', 'hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds', '8000', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:19:59');
INSERT INTO `t_properties` VALUES ('48', 'sync-home.ribbon.MaxAutoRetries', '0', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:21:31');
INSERT INTO `t_properties` VALUES ('49', 'sync-home.ribbon.MaxAutoRetriesNextServer', '0', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:21:41');
INSERT INTO `t_properties` VALUES ('50', 'sync-home.ribbon.OkToRetryOnAllOperations', 'false', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('51', 'sync-home.ribbon.http.client.enabled', 'true', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('52', 'sync-home.ribbon.ReadTimeout', '2000', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('53', 'sync-home.ribbon.ConnectTimeout', '2000', 'sync-ourocg', 'stage', 'develop', '0', null, '2020-01-30 19:23:28', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('54', 'yugioh.sync.ourocg.crawing.corn', '*/5 * * * * ?', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-30 19:23:28', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('55', 'yugioh.sync.ourocg.crawing.enabled', 'false', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-30 19:23:28', '2019-12-04 13:21:56');
INSERT INTO `t_properties` VALUES ('56', 'spring.zipkin.sender.type', 'rabbit', 'sync-home', 'stage', 'develop', '0', '', '2020-01-30 19:26:50', '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES ('57', 'spring.cloud.stream.bindings.card.save.destination', 'card-save', 'sync-home', 'stage', 'develop', '0', '', '2020-01-30 19:27:32', '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES ('58', 'spring.cloud.stream.bindings.card.update.destination', 'card-update', 'sync-home', 'stage', 'develop', '0', '', '2020-01-30 19:27:59', '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES ('59', 'spring.cloud.stream.bindings.limit.save.destination', 'limit-save', 'sync-home', 'stage', 'develop', '0', '', '2020-01-30 19:28:28', '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES ('60', 'spring.redis.database', '0', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('61', 'spring.redis.password', '', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('62', 'spring.redis.host', '127.0.0.1', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('63', 'spring.redis.port', '6379', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('64', 'spring.redis.timeout', '5000', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('65', 'spring.redis.lettuce.pool.max-active', '200', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('66', 'spring.redis.lettuce.pool.max-wait', '-1', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('67', 'spring.redis.lettuce.pool.max-idle', '5', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('68', 'spring.redis.lettuce.pool.min-idle', '0', 'sync-ourocg', 'stage', 'develop', '0', '', null, '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('69', 'spring.redis.database', '0', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('70', 'spring.redis.password', '', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('71', 'spring.redis.host', '127.0.0.1', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('72', 'spring.redis.port', '6379', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('73', 'spring.redis.timeout', '5000', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('74', 'spring.redis.lettuce.pool.max-active', '200', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('75', 'spring.redis.lettuce.pool.max-wait', '-1', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('76', 'spring.redis.lettuce.pool.max-idle', '5', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('77', 'spring.redis.lettuce.pool.min-idle', '0', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:38:20');
INSERT INTO `t_properties` VALUES ('78', 'server.port', '9400', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('79', 'management.endpoint.bus-refresh.enabled', 'true', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('80', 'spring.sleuth.sampler.probability', '1.0', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('81', 'spring.zipkin.base-url', 'http://127.0.0.1:9411', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('82', 'spring.cloud.bus.trace.enabled', 'true', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('83', 'eureka.client.serviceUrl.defaultZone', 'http://ygo:ygo@127.0.0.1:9000/eureka/', 'sync-local', 'stage', 'develop', '0', '', '2020-02-01 13:41:44', '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('84', 'async.pool.core-size', '3', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('85', 'async.pool.max-size', '5', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('86', 'async.pool.queue-size', '100', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:41:04');
INSERT INTO `t_properties` VALUES ('87', 'async.pool.thread-name-prefix', 'yugioh-sync-local-async-thread-', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('88', 'ftp.img.host', '127.0.0.1', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('89', 'ftp.img.port', '21', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('90', 'ftp.img.user', 'ygo', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('91', 'ftp.img.psw', 'ygo', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('92', 'index.cache.corn', '0 0 1 * * ?', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('93', 'spring.rabbitmq.port', '5672', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('94', 'spring.rabbitmq.host', '127.0.0.1', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('95', 'spring.rabbitmq.username', 'guest', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:43:45');
INSERT INTO `t_properties` VALUES ('96', 'spring.rabbitmq.password', 'guest', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:11');
INSERT INTO `t_properties` VALUES ('97', 'spring.cloud.stream.bindings.card.save.destination', 'card-save', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:11');
INSERT INTO `t_properties` VALUES ('98', 'spring.cloud.stream.bindings.card.save.group', 'card-save-group', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:11');
INSERT INTO `t_properties` VALUES ('99', 'spring.cloud.stream.bindings.card.update.destination', 'card-update', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:11');
INSERT INTO `t_properties` VALUES ('100', 'spring.cloud.stream.bindings.card.update.group', 'card-update-group', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:11');
INSERT INTO `t_properties` VALUES ('101', 'spring.cloud.stream.bindings.limit.save.destination', 'limit-save', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:12');
INSERT INTO `t_properties` VALUES ('102', 'spring.cloud.stream.bindings.limit.save.group', 'limit-save-group', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:12');
INSERT INTO `t_properties` VALUES ('103', 'spring.datasource.driver-class-name', 'com.mysql.cj.jdbc.Driver', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:12');
INSERT INTO `t_properties` VALUES ('104', 'spring.datasource.url', 'jdbc:mysql://127.0.0.1:3306/yugioh?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:47:12');
INSERT INTO `t_properties` VALUES ('105', 'spring.datasource.username', 'root', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('106', 'spring.datasource.password', 'root', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('107', 'spring.datasource.hikari.maximum-pool-size', '20', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('108', 'spring.datasource.hikari.minimum-idle', '5', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('109', 'spring.jpa.database-platform', 'org.hibernate.dialect.MySQL5InnoDBDialect', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('110', 'spring.jpa.show-sql', 'true', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('111', 'spring.jpa.hibernate.ddl-auto', 'none', 'sync-local', 'stage', 'develop', '0', '', null, '2020-01-30 19:49:27');
INSERT INTO `t_properties` VALUES ('112', 'spring.boot.admin.client.instance.service-base-url', 'http://localhost:9700', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-31 12:01:45', '2019-10-09 13:39:11');
INSERT INTO `t_properties` VALUES ('113', 'spring.boot.admin.client.instance.service-base-url', 'http://localhost:9700', 'sync-home', 'stage', 'develop', '0', '', '2020-01-31 12:34:38', '2019-12-03 11:16:26');
INSERT INTO `t_properties` VALUES ('114', 'server.port', '9300', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('115', 'management.endpoints.web.exposure.include', '*', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('116', 'spring.boot.admin.client.instance.service-base-url', 'http://127.0.0.1:9700', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('117', 'management.endpoints.web.exposure.include', '*', 'sync-local', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('118', 'spring.boot.admin.client.instance.service-base-url', 'http://127.0.0.1:9700', 'sync-local', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('119', 'management.endpoints.web.exposure.include', '*', 'sync-home', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('120', 'management.endpoints.web.exposure.include', '*', 'monitor', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('121', 'eureka.client.serviceUrl.defaultZone', 'http://ygo:ygo@127.0.0.1:9000/eureka/', 'monitor', 'stage', 'develop', '0', '', '2020-02-01 13:45:05', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('122', 'server.port', '9700', 'monitor', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('123', 'management.endpoint.bus-refresh.enabled', 'true', 'monitor', 'stage', 'develop', '0', '', '2020-01-31 13:18:38', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('124', 'spring.cloud.bus.trace.enabled', 'true', 'monitor', 'stage', 'develop', '0', '', '2020-01-31 13:12:52', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('125', 'eureka.instance.prefer-ip-address', 'true', 'monitor', 'stage', 'develop', '0', '', '2020-02-01 13:42:18', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('126', 'eureka.instance.prefer-ip-address', 'true', 'sync-home', 'stage', 'develop', '0', '', '2020-02-01 13:42:45', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('127', 'eureka.instance.prefer-ip-address', 'true', 'sync-local', 'stage', 'develop', '0', '', '2020-02-01 13:43:05', '2020-01-30 19:37:40');
INSERT INTO `t_properties` VALUES ('128', 'eureka.instance.prefer-ip-address', 'true', 'sync-ourocg', 'stage', 'develop', '0', '', '2020-02-01 13:43:23', '2020-01-30 19:37:40');
