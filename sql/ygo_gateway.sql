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

 Date: 01/04/2020 16:55:02
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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `t_dict` VALUES (9, ' Query Route Predicate Factory', 1, 'Query', 8);
INSERT INTO `t_dict` VALUES (10, 'RemoteAddr Route Predicate Factory', 1, 'RemoteAddr', 9);

-- ----------------------------
-- Table structure for t_predicates
-- ----------------------------
DROP TABLE IF EXISTS `t_predicates`;
CREATE TABLE `t_predicates`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `arg_name` tinyint(10) NULL DEFAULT NULL,
  `arg_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_predicates
-- ----------------------------
INSERT INTO `t_predicates` VALUES (1, 1, 7, '/**', '2020-04-01 16:41:24', '2020-04-01 13:46:57');
INSERT INTO `t_predicates` VALUES (2, 1, 6, 'POST,GET', '2020-04-01 16:42:51', '2020-04-01 16:16:50');

-- ----------------------------
-- Table structure for t_route
-- ----------------------------
DROP TABLE IF EXISTS `t_route`;
CREATE TABLE `t_route`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `setvice_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_route
-- ----------------------------
INSERT INTO `t_route` VALUES (1, 'test', 'http://www.baidu.com', 1, 0, NULL, '2020-04-01 13:45:57');

SET FOREIGN_KEY_CHECKS = 1;
