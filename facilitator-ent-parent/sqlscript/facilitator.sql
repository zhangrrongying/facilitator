/*
Navicat MySQL Data Transfer

Source Server         : facilitator
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : facilitator

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-04-16 16:49:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for faac_supplier_artisan
-- ----------------------------
DROP TABLE IF EXISTS `faac_supplier_artisan`;
CREATE TABLE `faac_supplier_artisan` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商技术人员表',
  `Name` varchar(50) NOT NULL COMMENT '技术人员名称',
  `Phone` int(11) NOT NULL COMMENT '技术人员电话',
  `Certificate_Imgs` varchar(500) DEFAULT NULL COMMENT '资质证书',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fac_project
-- ----------------------------
DROP TABLE IF EXISTS `fac_project`;
CREATE TABLE `fac_project` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目表',
  `Name` varchar(100) NOT NULL COMMENT '项目名称',
  `Project_Type_Id` int(11) NOT NULL COMMENT '项目类型',
  `Investment` decimal(18,4) NOT NULL COMMENT '投资规模',
  `Serial_Num` varchar(50) DEFAULT NULL COMMENT '编号',
  `Description` varchar(255) DEFAULT NULL COMMENT '描述',
  `Create_User` int(11) DEFAULT NULL COMMENT '创建人',
  `Create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fac_supplier
-- ----------------------------
DROP TABLE IF EXISTS `fac_supplier`;
CREATE TABLE `fac_supplier` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商表',
  `Name` varchar(100) NOT NULL COMMENT '供应商名称',
  `Serial_Num` varchar(50) DEFAULT NULL COMMENT '信用代码',
  `Link_Name` varchar(50) DEFAULT NULL COMMENT '企业简称',
  `Corporation` varchar(50) NOT NULL COMMENT '企业法人',
  `Corporation_Phone` int(11) NOT NULL COMMENT '企业法人电话',
  `Register_Address` varchar(255) NOT NULL COMMENT '注册地址',
  ` Work_Address` varchar(255) NOT NULL COMMENT '办公地址',
  `Registered_Capital` decimal(18,4) NOT NULL COMMENT '注册资本',
  `Link_Man` varchar(50) NOT NULL COMMENT '联系人',
  `Link_Phone` int(11) NOT NULL COMMENT '联系电话',
  `Desk_Phone` varchar(50) DEFAULT NULL COMMENT '座机',
  `Email` varchar(50) NOT NULL COMMENT '邮箱',
  `Certification_Name` varchar(100) DEFAULT NULL COMMENT '资质证书名称',
  `Certification_Level` varchar(50) DEFAULT NULL COMMENT '资质证书等级',
  `Leader` int(11) NOT NULL COMMENT '技术负责人',
  `Artisan` varchar(50) DEFAULT NULL COMMENT '技术人员',
  `License_Imgs` varchar(255) NOT NULL COMMENT '营业执照',
  `Certificate_Imgs` varchar(255) NOT NULL COMMENT '资质证书Img',
  `Identity_Card_Imgs` varchar(255) NOT NULL COMMENT '身份证',
  `Description` varchar(100) DEFAULT NULL COMMENT '企业简介',
  `Create_User` int(11) NOT NULL COMMENT '创建人',
  `Create_Time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fac_supplier_project_relative
-- ----------------------------
DROP TABLE IF EXISTS `fac_supplier_project_relative`;
CREATE TABLE `fac_supplier_project_relative` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表',
  `Supplier_Id` int(11) NOT NULL COMMENT '供应商Id',
  `Project_Id` int(11) NOT NULL COMMENT '项目ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_auth_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_func`;
CREATE TABLE `sys_auth_func` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表',
  `Parent_Key_Id` int(11) DEFAULT NULL COMMENT '父ID',
  `Name` varchar(50) NOT NULL,
  `AuthKey` varchar(20) NOT NULL,
  `Type` tinyint(1) NOT NULL COMMENT '类型 （1-Menu,2-Point）',
  `Url` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL COMMENT '描述',
  `Icon` varchar(50) DEFAULT NULL,
  `Rank` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '色角表',
  `Name` varchar(50) NOT NULL,
  `Status` tinyint(1) NOT NULL COMMENT '状态1-启用 0- 禁用',
  `CreateDate` date NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_func`;
CREATE TABLE `sys_role_func` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限表',
  `Role_Id` int(11) NOT NULL,
  `Func_Id` int(11) NOT NULL COMMENT '权限功能ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户所属角色',
  `Role_Id` int(11) NOT NULL COMMENT '角色ID',
  `User_Id` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统用户表',
  `UserName` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT '名字',
  `Email` varchar(100) NOT NULL COMMENT '邮箱',
  `Phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `Password` varchar(50) NOT NULL,
  `Status` tinyint(1) NOT NULL COMMENT '状态1-启用 0- 禁用',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fac_project_type
-- ----------------------------
DROP TABLE IF EXISTS `fac_project_type`;
CREATE TABLE `fac_project_type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
