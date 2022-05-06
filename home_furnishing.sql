/*
 Navicat Premium Data Transfer

 Source Server         : nm
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : home_furnishing

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 06/05/2022 21:39:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (2, 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for furn
-- ----------------------------
DROP TABLE IF EXISTS `furn`;
CREATE TABLE `furn`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `maker` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `sales` int(10) UNSIGNED NOT NULL,
  `stock` int(10) UNSIGNED NOT NULL,
  `img_path` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of furn
-- ----------------------------
INSERT INTO `furn` VALUES (1, ' 北 欧 风 格 小 桌 子 ', ' 熊 猫 家 居 ', 180.00, 666, 7, 'assets/images/product-image/6.jpg');
INSERT INTO `furn` VALUES (2, ' 简 约 风 格 小 椅 子 ', ' 熊 猫 家 居 ', 180.00, 666, 7, 'assets/images/product-image/4.jpg');
INSERT INTO `furn` VALUES (3, ' 典 雅 风 格 小 台 灯 ', ' 蚂 蚁 家 居 ', 180.00, 667, 6, 'assets/images/product-image/14.jpg');
INSERT INTO `furn` VALUES (4, ' 温 馨 风 格 盆 景 架 ', ' 蚂 蚁 家 居 ', 180.00, 667, 6, 'assets/images/product-image/16.jpg');
INSERT INTO `furn` VALUES (5, '1', '1', 1.00, 2, 0, 'assets/images/product-image/2022/5/6/f7c926c8-67d9-432c-ad3a-4de628c4e5c6_212516-15666531161ade.jpg');
INSERT INTO `furn` VALUES (6, 'A龙', '阿龙', 666.00, 2, 10, 'assets/images/product-image/2022/5/6/3cb4e36e-fcdd-4b4c-a07a-cc0dec02b272_along.jpg');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'along', '66c2500371dfb92fd5c8503511ad3b28', 'along@qq.com');
INSERT INTO `member` VALUES (4, 'zy', '4345ed1bd9c52c31610be7c0080981c3', 'aaa@qq.com');
INSERT INTO `member` VALUES (6, '小丁猫', '4345ed1bd9c52c31610be7c0080981c3', 'aaa@qq.com');
INSERT INTO `member` VALUES (8, 'along666', '904cc1287205cf91ea671c9861d9258d', 'along666@qq.com');
INSERT INTO `member` VALUES (9, 'along667', '50155e8bd8793cedfd189b9897742feb', 'along667@qq.com');
INSERT INTO `member` VALUES (10, 'xiaoding', '3db8857530915b45da97f9c4f0a40121', 'xiaodingmao@qq.com');
INSERT INTO `member` VALUES (11, 'aaaaaa', '0b4e7a0e5fe84ad35fb5f95b9ceeac79', 'aaa@qq.com');
INSERT INTO `member` VALUES (12, '11111111', '7fa8282ad93047a4d6fe6111c93b308a', '111@qq.com');
INSERT INTO `member` VALUES (13, '123456', 'e10adc3949ba59abbe56e057f20f883e', '123456@qq.com');
INSERT INTO `member` VALUES (14, 'yueyue', '13921ae3b5169d7b35f6fc965ac8ca6e', 'yueyue@qq.com');
INSERT INTO `member` VALUES (15, 'bbbbbb', '875f26fdb1cecf20ceb4ca028263dec6', 'bbbbbb@qq.com');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1651755531150_8', '2022-05-05 20:58:51', 180.00, 0, 8);
INSERT INTO `order` VALUES ('1651829158447_8', '2022-05-06 17:25:58', 1693.00, 0, 8);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `count` int(11) NOT NULL,
  `total_price` decimal(11, 2) NOT NULL,
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (20, ' 北 欧 风 格 小 桌 子 ', 180.00, 1, 180.00, '1651755531150_8');
INSERT INTO `order_item` VALUES (22, ' 典 雅 风 格 小 台 灯 ', 180.00, 1, 180.00, '1651829158447_8');
INSERT INTO `order_item` VALUES (23, ' 温 馨 风 格 盆 景 架 ', 180.00, 1, 180.00, '1651829158447_8');
INSERT INTO `order_item` VALUES (24, '1', 1.00, 1, 1.00, '1651829158447_8');
INSERT INTO `order_item` VALUES (25, 'A龙', 666.00, 2, 1332.00, '1651829158447_8');

SET FOREIGN_KEY_CHECKS = 1;
