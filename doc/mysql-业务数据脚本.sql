/*
 Navicat Premium Data Transfer

 Source Server         : 59.110.50.242
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 59.110.50.242:3306
 Source Schema         : syc

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 09/11/2020 14:48:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司名称',
  `addr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `tel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `contact` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人',
  `province_id` int(0) NULL DEFAULT NULL COMMENT '所属省级ID',
  `city_id` int(0) NULL DEFAULT NULL COMMENT '所属市级ID',
  `area_id` int(0) NULL DEFAULT NULL COMMENT '所属区域ID',
  `is_stop` tinyint(0) NULL DEFAULT NULL COMMENT '是否停用，1=是，0=否',
  `is_root` tinyint(0) NULL DEFAULT NULL COMMENT '是否为运营商公司（1=是，0=否）-- 手动维护',
  `gmt_create` bigint(0) NULL DEFAULT NULL COMMENT '创建时间戳（毫秒数）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公司表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sensor
-- ----------------------------
DROP TABLE IF EXISTS `t_sensor`;
CREATE TABLE `t_sensor`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备名称',
  `type_id` int(0) NOT NULL COMMENT '类型ID',
  `state` tinyint(0) NOT NULL DEFAULT 1 COMMENT '设备状态（1=正常运行，0=故障报修）',
  `site_id` bigint(0) NOT NULL COMMENT '所属试验场（场地）ID',
  `gmt_create` bigint(0) NULL DEFAULT NULL COMMENT '创建时间（毫秒数）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sensor_repair
-- ----------------------------
DROP TABLE IF EXISTS `t_sensor_repair`;
CREATE TABLE `t_sensor_repair`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '报修记录ID',
  `sensor_id` bigint(0) NOT NULL COMMENT '设备ID',
  `gmt_create` bigint(0) NULL DEFAULT NULL COMMENT '创建时间（毫秒数）',
  `state` tinyint(0) NULL DEFAULT 0 COMMENT '报修状态（0=未处理，1-已处理）',
  `apply_user_id` bigint(0) NOT NULL COMMENT '申请用户ID',
  `solve_user_id` bigint(0) NULL DEFAULT NULL COMMENT '处理人ID',
  `solve_time` bigint(0) NULL DEFAULT NULL COMMENT '处理时间（毫秒数）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备报修记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sensor_type
-- ----------------------------
DROP TABLE IF EXISTS `t_sensor_type`;
CREATE TABLE `t_sensor_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '设备类型ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备类型名称',
  `state` tinyint(0) NULL DEFAULT NULL COMMENT '状态（0=启用，1=禁用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备分类ID' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_site
-- ----------------------------
DROP TABLE IF EXISTS `t_site`;
CREATE TABLE `t_site`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '试验场（场地）ID，主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '试验场名称',
  `addr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `lng` decimal(16, 10) NULL DEFAULT 0.0000000000 COMMENT '经度',
  `lat` decimal(16, 10) NULL DEFAULT NULL COMMENT '纬度',
  `company_id` bigint(0) NOT NULL COMMENT '所属公司ID',
  `gmt_create` bigint(0) NULL DEFAULT NULL COMMENT '创建时间（毫秒数）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '试验场（场地）表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `company_id` bigint(0) NOT NULL COMMENT '所属公司ID',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `state` tinyint(0) NOT NULL DEFAULT 0 COMMENT '状态（0=正常，1=锁定）',
  `role_id` tinyint(0) NOT NULL COMMENT '角色ID',
  `gmt_create` bigint(0) NULL DEFAULT NULL,
  `last_time` bigint(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户token（唯一）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '1111222', '2222222', 1, '1', '1', 1, 1, NULL, NULL, '1');

-- ----------------------------
-- Table structure for t_user_site
-- ----------------------------
DROP TABLE IF EXISTS `t_user_site`;
CREATE TABLE `t_user_site`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `site_id` bigint(0) NOT NULL COMMENT '试验场（场地）ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与试验场关联关系表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
