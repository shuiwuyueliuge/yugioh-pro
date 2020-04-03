/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : ygo_gateway

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 03/04/2020 15:17:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value_id` tinyint(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES (1, 'After Route Predicate Factory', 1, 'After', 0);
INSERT INTO `t_dict` VALUES (2, 'Before Route Predicate Factory', 1, 'Before', 1);
INSERT INTO `t_dict` VALUES (3, 'Between Route Predicate Factory', 1, 'Between', 2);
INSERT INTO `t_dict` VALUES (4, 'Cookie Route Predicate Factory', 1, 'Cookie', 3);
INSERT INTO `t_dict` VALUES (5, 'Header Route Predicate Factory', 1, 'Header', 4);
INSERT INTO `t_dict` VALUES (6, 'Host Route Predicate Factory', 1, 'Host', 5);
INSERT INTO `t_dict` VALUES (7, 'Method Route Predicate Factory', 1, 'Method', 6);
INSERT INTO `t_dict` VALUES (8, 'Path Route Predicate Factory', 1, 'Path', 7);
INSERT INTO `t_dict` VALUES (9, 'Query Route Predicate Factory', 1, 'Query', 8);
INSERT INTO `t_dict` VALUES (10, 'RemoteAddr Route Predicate Factory', 1, 'RemoteAddr', 9);
INSERT INTO `t_dict` VALUES (11, 'Weight Route Predicate Factory', 1, 'Weight', 10);

-- ----------------------------
-- Table structure for t_predicates
-- ----------------------------
DROP TABLE IF EXISTS `t_predicates`;
CREATE TABLE `t_predicates`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NULL DEFAULT NULL,
  `arg_name` tinyint(10) NULL DEFAULT NULL,
  `arg_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_predicates
-- ----------------------------
INSERT INTO `t_predicates` VALUES (122, 18, 7, '/**', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (123, 18, 6, 'POST,GET', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (124, 18, 0, '2017-01-20T17:42:47.789-07:00[America/Denver]', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (125, 18, 1, '2021-01-20T17:42:47.789-07:00[America/Denver]', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (126, 18, 2, '2017-01-20T17:42:47.789-07:00[America/Denver],2017-01-21T17:42:47.789-07:00[America/Denver]', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (127, 18, 3, 'chocolate,ch.p', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (128, 18, 4, 'X-Request-Id,\\d+', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (129, 18, 5, '**.somehost.org,**.anotherhost.org', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (130, 18, 8, 'red,gree.', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (131, 18, 9, '192.168.1.1/24', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (132, 18, 10, 'group1,8', '2020-04-03 15:10:39', '2020-04-03 15:10:39');
INSERT INTO `t_predicates` VALUES (133, 19, 7, '/**', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (134, 19, 6, 'POST,GET', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (135, 19, 0, '2017-01-20T17:42:47.789-07:00[America/Denver]', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (136, 19, 1, '2021-01-20T17:42:47.789-07:00[America/Denver]', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (137, 19, 2, '2017-01-20T17:42:47.789-07:00[America/Denver],2017-01-21T17:42:47.789-07:00[America/Denver]', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (138, 19, 3, 'chocolate,ch.p', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (139, 19, 4, 'X-Request-Id,\\d+', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (140, 19, 5, '**.somehost.org,**.anotherhost.org', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (141, 19, 8, 'red,gree.', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (142, 19, 9, '192.168.1.1/24', '2020-04-03 15:12:42', '2020-04-03 15:12:42');
INSERT INTO `t_predicates` VALUES (143, 19, 10, 'group1,8', '2020-04-03 15:12:42', '2020-04-03 15:12:42');

-- ----------------------------
-- Table structure for t_route
-- ----------------------------
DROP TABLE IF EXISTS `t_route`;
CREATE TABLE `t_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `state` tinyint(11) NULL DEFAULT 0,
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `service`(`service_id`, `uri`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_route
-- ----------------------------
INSERT INTO `t_route` VALUES (18, 'test2', 'http://www.baidu.com', 1, 0, '2020-04-03 15:10:40', '2020-04-03 15:10:39');
INSERT INTO `t_route` VALUES (19, 'test3', 'http://www.baidu.com', 1, 0, '2020-04-03 15:12:42', '2020-04-03 15:12:42');

SET FOREIGN_KEY_CHECKS = 1;
