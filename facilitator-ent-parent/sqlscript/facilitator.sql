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
  `LoginNum` int(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
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

-- ----------------------------
-- Table structure for `fac_project_score`
-- ----------------------------
DROP TABLE IF EXISTS `fac_project_score`;
CREATE TABLE `fac_project_score` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价指标',
  `Name` varchar(100) NOT NULL COMMENT '评价指标',
  `Description` varchar(255) NOT NULL COMMENT '评价内容',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fac_project_score
-- ----------------------------
INSERT INTO `fac_project_score` VALUES ('1', '合同履约情况', '依法规范签订合同');
INSERT INTO `fac_project_score` VALUES ('2', '合同履约情况', '合同双方权利义务明确');
INSERT INTO `fac_project_score` VALUES ('3', '合同履约情况', '按时保质完成委托任务');
INSERT INTO `fac_project_score` VALUES ('4', '合同履约情况', '履约中，提出并实施了科学的、技术的措施');
INSERT INTO `fac_project_score` VALUES ('5', '合同履约情况', '保证了合同的有效实施');
INSERT INTO `fac_project_score` VALUES ('6', '成果文件质量', '成果文件清晰、完整、合法，无任何技术差错');
INSERT INTO `fac_project_score` VALUES ('7', '成果文件质量', '按时保质保量提交委托人，后期无返工');
INSERT INTO `fac_project_score` VALUES ('8', '规范管理及服务质量和业务水平', '项目人员配置齐全，职责明确');
INSERT INTO `fac_project_score` VALUES ('9', '规范管理及服务质量和业务水平', '有专职的负责人');
INSERT INTO `fac_project_score` VALUES ('10', '规范管理及服务质量和业务水平', '全过程服务细致、耐心，服务态度端正');
INSERT INTO `fac_project_score` VALUES ('11', '规范管理及服务质量和业务水平', '从业人员因为无水平高，运作程序规范');
INSERT INTO `fac_project_score` VALUES ('12', '职业道德及企业信誉', '严格遵守执业行为准则、职业道德准则');
INSERT INTO `fac_project_score` VALUES ('13', '职业道德及企业信誉', '以服务质量、企业信誉参与市场竞争');
INSERT INTO `fac_project_score` VALUES ('14', '职业道德及企业信誉', '坚持实事求是，维护委托人合法权益');
INSERT INTO `fac_project_score` VALUES ('15', '职业道德及企业信誉', '严格保守执业过程中的技术和商业机密');
INSERT INTO `fac_project_score` VALUES ('16', '职业道德及企业信誉', '同业互助，共同维护和促进本行业的职业道德和信誉');
INSERT INTO `fac_project_score` VALUES ('17', '使用过程其它方面的综合评价', '服务的各方面周到、细致、无疏漏');
INSERT INTO `fac_project_score` VALUES ('18', '使用过程其它方面的综合评价', '组织协调工作到位，实施过程中无不良影响或造成损失');
INSERT INTO `fac_project_score` VALUES ('19', '使用过程其它方面的综合评价', '无违法违规行为，能提出管理意见');

-- ----------------------------
-- Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志',
  `User_Id` int(11) NOT NULL COMMENT '登录人',
  `Login_Time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `mail_sms_log`
-- ----------------------------
DROP TABLE IF EXISTS `mail_sms_log`;
CREATE TABLE `mail_sms_log` (
  `LOG_ID` char(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '件邮、短信发送记录表',
  `PHONE_OR_MAIL` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SUBJECT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CONTENT` mediumtext COLLATE utf8mb4_unicode_ci,
  `IS_SUCCESS` bit(1) NOT NULL,
  `SEND_DATE` datetime NOT NULL,
  `TYPE` bit(1) NOT NULL,
  PRIMARY KEY (`LOG_ID`),
  UNIQUE KEY `INDEX_LOG_ID` (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
