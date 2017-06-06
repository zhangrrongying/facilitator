/*
Navicat MySQL Data Transfer

Source Server         : facilitator
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : facilitator

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-07 07:25:01
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
-- Records of faac_supplier_artisan
-- ----------------------------

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
  `Status` tinyint(1) DEFAULT NULL COMMENT '状态 1：招标中 2：招标完成3：服务中4：服务完成5：评分完成',
  `Is_Del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除0:否1：是',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fac_project
-- ----------------------------
INSERT INTO `fac_project` VALUES ('1', '测试', '3', '100.0000', 'N10000001', '够速度还是空间大', '1', '2017-04-25 22:23:24', '5', '1');
INSERT INTO `fac_project` VALUES ('2', '测试1', '2', '150.0000', 'N1000002', '代售点撒大大', '1', '2017-04-25 22:33:53', '5', '1');
INSERT INTO `fac_project` VALUES ('3', '测试2', '2', '100.0000', 'N00001', '火山口将发生', '1', '2017-05-13 21:29:18', '4', '1');
INSERT INTO `fac_project` VALUES ('4', '空调工程', '1', '150.0000', 'C00000001', '中央空调购买安装', '1', '2017-05-16 22:22:25', '2', '1');
INSERT INTO `fac_project` VALUES ('5', '食堂修建', '2', '200.0000', 'C0000002', '食堂修建', '1', '2017-05-16 22:57:35', '2', '1');
INSERT INTO `fac_project` VALUES ('6', '绵阳中学实验学校电子白板采购项目', '1', '500.0000', '20170501', '绵阳中学实验学校新购100套电子白板采购项目', '1', '2017-06-06 22:12:34', '2', '0');
INSERT INTO `fac_project` VALUES ('7', '绵阳中学空调', '1', '100.0000', 'A00001', '', '1', '2017-06-06 22:44:32', '2', '0');
INSERT INTO `fac_project` VALUES ('8', '绵阳中学操场跑道', '4', '50.0000', 'A0002', 'aaaa', '1', '2017-06-06 22:46:10', '2', '0');
INSERT INTO `fac_project` VALUES ('9', 'asdasd', '2', '100.0000', '1111', '', '1', '2017-06-06 23:52:09', '2', '1');

-- ----------------------------
-- Table structure for fac_project_score
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
-- Table structure for fac_project_score_relative
-- ----------------------------
DROP TABLE IF EXISTS `fac_project_score_relative`;
CREATE TABLE `fac_project_score_relative` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Project_Id` int(11) NOT NULL COMMENT '项目ID',
  `Project_Score_Id` int(11) NOT NULL COMMENT '评价项目ID',
  `Score` decimal(18,2) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fac_project_score_relative
-- ----------------------------
INSERT INTO `fac_project_score_relative` VALUES ('1', '1', '1', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('2', '1', '2', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('3', '1', '3', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('4', '1', '5', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('5', '1', '4', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('6', '1', '6', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('7', '1', '7', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('8', '1', '8', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('9', '1', '9', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('10', '1', '10', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('11', '1', '11', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('12', '1', '12', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('13', '1', '13', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('14', '1', '15', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('15', '1', '14', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('16', '1', '16', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('17', '1', '17', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('18', '1', '18', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('19', '1', '19', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('20', '2', '1', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('21', '2', '2', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('22', '2', '3', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('23', '2', '4', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('24', '2', '5', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('25', '2', '6', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('26', '2', '7', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('27', '2', '12', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('28', '2', '11', '3.00');
INSERT INTO `fac_project_score_relative` VALUES ('29', '2', '10', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('30', '2', '9', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('31', '2', '13', '4.00');
INSERT INTO `fac_project_score_relative` VALUES ('32', '2', '15', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('33', '2', '14', '5.00');
INSERT INTO `fac_project_score_relative` VALUES ('34', '2', '16', '3.00');

-- ----------------------------
-- Table structure for fac_project_type
-- ----------------------------
DROP TABLE IF EXISTS `fac_project_type`;
CREATE TABLE `fac_project_type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fac_project_type
-- ----------------------------
INSERT INTO `fac_project_type` VALUES ('1', '招商代理');
INSERT INTO `fac_project_type` VALUES ('2', '审计造价');
INSERT INTO `fac_project_type` VALUES ('3', '勘查设计');
INSERT INTO `fac_project_type` VALUES ('4', '咨询法律');
INSERT INTO `fac_project_type` VALUES ('5', '工程建设');
INSERT INTO `fac_project_type` VALUES ('6', '工程建设');

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
  `Corporation_Phone` varchar(11) NOT NULL COMMENT '企业法人电话',
  `Register_Address` varchar(255) NOT NULL COMMENT '注册地址',
  `Work_Address` varchar(255) NOT NULL COMMENT '办公地址',
  `Registered_Capital` varchar(50) NOT NULL COMMENT '注册资本',
  `Link_Man` varchar(50) NOT NULL COMMENT '联系人',
  `Link_Phone` varchar(11) NOT NULL COMMENT '联系电话',
  `Desk_Phone` varchar(50) DEFAULT NULL COMMENT '座机',
  `Email` varchar(50) NOT NULL COMMENT '邮箱',
  `Certification_Name` varchar(100) DEFAULT NULL COMMENT '资质证书名称',
  `Certification_Level` varchar(50) DEFAULT NULL COMMENT '资质证书等级',
  `Leader` int(11) DEFAULT NULL COMMENT '技术负责人',
  `Artisan` varchar(50) DEFAULT NULL COMMENT '技术人员',
  `License_Imgs` varchar(255) NOT NULL COMMENT '营业执照',
  `Certificate_Imgs` varchar(255) NOT NULL COMMENT '资质证书Img',
  `Identity_Card_Imgs` varchar(255) NOT NULL COMMENT '身份证',
  `Description` varchar(100) DEFAULT NULL COMMENT '企业简介',
  `Create_User` int(11) NOT NULL COMMENT '创建人',
  `Create_Time` datetime NOT NULL COMMENT '创建时间',
  `Score_Level` decimal(18,2) DEFAULT NULL COMMENT '评分等级',
  `Is_Del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除0：否1：是',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fac_supplier
-- ----------------------------
INSERT INTO `fac_supplier` VALUES ('1', '测试', '阿斯顿撒', '都是', '测试', '1865', '说的', '说的', '500w', '说的', '说的', '是', ' 山东省', '说的', '是', null, null, 'a-1496247442648.jpg', 'a-1496247458653.jpg', 'a-1496247449938.jpg', '12345', '1', '2017-04-23 15:29:50', null, '1');
INSERT INTO `fac_supplier` VALUES ('3', '测试1', '是多少', '是', '测试', '18628240591', '是的是', '是的按时', '是d', '是的', '是的', '是d', ' 都是', '是的', '1', null, null, 'a-1496247143472.jpg', 'a-1496247199511.jpg', 'a-1496247415731.jpg', '是的', '1', '2017-05-03 22:18:42', null, '1');
INSERT INTO `fac_supplier` VALUES ('4', '测试2', '是的', '是d', '是', '是的', '是的', '是的', '是的', '是', '是', '是', '是的', '是的', '2', null, null, 'a-1496247069353.jpg', 'a-1496247083095.jpg', 'a-1496247112522.jpg', '是的', '1', '2017-05-03 22:19:48', null, '1');
INSERT INTO `fac_supplier` VALUES ('5', '绵阳高新招标代理公司', '510000099999', '招标代理公司', '王宝强', '13981112233', '绵阳市高新区', '绵阳市高新区商务大厦', '60000', '张三', '7678732', '2523333', '2123237@qq.com', '招标代理', '一级', null, null, 'QQ图片20160803200447-1496758582532.jpg', '4u壁挂式机柜5-1496758595101.jpg,', '43f6ff1f4134970af0282cec95cad1c8a686', '中古uhguhiuiuiguiguig11', '1', '2017-06-06 22:16:39', null, '0');
INSERT INTO `fac_supplier` VALUES ('6', '四川蓝天代理有限公司', '678898987898908908', 'u影比较大翅膀结婚', '李四', '88999090', '边吃边等传播的产生', '尽快哈JDSD', '787878789', 'SDHHJ ', '2323134134', '56565656', '34544345', '招标打理', '二级', null, null, '2u壁挂式机柜1-1496758747388.jpg', 'b985d4d-1496758771923.jpg,', '3d8fab8-1496758758943.jpg,', '风格风格风格v', '1', '2017-06-06 22:20:07', null, '0');
INSERT INTO `fac_supplier` VALUES ('7', '四川三台台代理', '225656556', '心脏病的尺寸的', '啊哈撒', '21267088', '说的很对加快速度尽快', '送打底裤都疯了快冻死了', '545466', '到实地', '234345', '2132344', '34234', '招标代理', '一级', null, null, 'd0d2b75-1496758901268.jpg', 'd0d2b75-1496758919398.jpg', '1314754-1496758908873.jpg', '水电费刚发给', '1', '2017-06-06 22:22:24', null, '0');
INSERT INTO `fac_supplier` VALUES ('8', 'aaa', '1123', 'asdasd', 'asd', 'asd', 'asdasd', 'asdasd', '1000000', 'asdasd', '', 'asd', '', 'asd', 'asd', null, null, 'IMG_0009-1496763437337.JPG', 'IMG_0002-1496763385756.JPG', 'IMG_0005-1496763456614.JPG', '', '1', '2017-06-06 23:37:39', null, '1');

-- ----------------------------
-- Table structure for fac_supplier_project_relative
-- ----------------------------
DROP TABLE IF EXISTS `fac_supplier_project_relative`;
CREATE TABLE `fac_supplier_project_relative` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '中间表',
  `Supplier_Id` int(11) NOT NULL COMMENT '供应商Id',
  `Project_Id` int(11) NOT NULL COMMENT '项目ID',
  `Bid_Time` datetime DEFAULT NULL COMMENT '中标时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fac_supplier_project_relative
-- ----------------------------
INSERT INTO `fac_supplier_project_relative` VALUES ('1', '1', '1', '2017-04-26 22:05:22');
INSERT INTO `fac_supplier_project_relative` VALUES ('2', '1', '2', '2017-05-03 22:23:20');
INSERT INTO `fac_supplier_project_relative` VALUES ('3', '1', '3', '2017-05-13 21:30:45');
INSERT INTO `fac_supplier_project_relative` VALUES ('4', '3', '4', '2017-05-16 22:56:28');
INSERT INTO `fac_supplier_project_relative` VALUES ('6', '4', '5', '2017-05-31 22:14:58');
INSERT INTO `fac_supplier_project_relative` VALUES ('7', '4', '6', '2017-06-06 22:26:46');
INSERT INTO `fac_supplier_project_relative` VALUES ('8', '5', '7', '2017-06-06 22:44:45');
INSERT INTO `fac_supplier_project_relative` VALUES ('9', '7', '8', '2017-06-06 22:47:49');
INSERT INTO `fac_supplier_project_relative` VALUES ('10', '7', '9', '2017-06-06 23:53:00');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录日志',
  `User_Id` int(11) NOT NULL COMMENT '登录人',
  `Login_Time` datetime NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', '1', '2017-05-13 08:57:36');
INSERT INTO `login_log` VALUES ('2', '1', '2017-05-13 21:09:42');
INSERT INTO `login_log` VALUES ('3', '2', '2017-05-13 21:20:37');
INSERT INTO `login_log` VALUES ('4', '1', '2017-05-13 21:20:56');
INSERT INTO `login_log` VALUES ('5', '2', '2017-05-13 21:21:30');
INSERT INTO `login_log` VALUES ('6', '1', '2017-05-13 21:28:15');
INSERT INTO `login_log` VALUES ('7', '1', '2017-05-13 21:42:16');
INSERT INTO `login_log` VALUES ('8', '1', '2017-05-16 22:03:23');
INSERT INTO `login_log` VALUES ('9', '1', '2017-05-21 10:38:21');
INSERT INTO `login_log` VALUES ('10', '1', '2017-05-21 10:43:07');
INSERT INTO `login_log` VALUES ('11', '1', '2017-05-23 21:14:02');
INSERT INTO `login_log` VALUES ('12', '1', '2017-05-23 21:31:07');
INSERT INTO `login_log` VALUES ('13', '1', '2017-05-24 09:59:21');
INSERT INTO `login_log` VALUES ('14', '1', '2017-05-24 14:29:55');
INSERT INTO `login_log` VALUES ('15', '1', '2017-05-31 20:40:28');
INSERT INTO `login_log` VALUES ('16', '2', '2017-05-31 20:47:32');
INSERT INTO `login_log` VALUES ('17', '2', '2017-05-31 20:56:55');
INSERT INTO `login_log` VALUES ('18', '2', '2017-05-31 21:10:55');
INSERT INTO `login_log` VALUES ('19', '1', '2017-05-31 22:11:39');
INSERT INTO `login_log` VALUES ('20', '1', '2017-05-31 23:05:26');
INSERT INTO `login_log` VALUES ('21', '1', '2017-05-31 23:19:56');
INSERT INTO `login_log` VALUES ('22', '1', '2017-06-01 00:05:20');
INSERT INTO `login_log` VALUES ('23', '1', '2017-06-01 00:05:49');
INSERT INTO `login_log` VALUES ('24', '1', '2017-06-01 22:20:00');
INSERT INTO `login_log` VALUES ('25', '1', '2017-06-01 22:43:21');
INSERT INTO `login_log` VALUES ('26', '1', '2017-06-01 22:52:53');
INSERT INTO `login_log` VALUES ('27', '1', '2017-06-06 22:06:56');
INSERT INTO `login_log` VALUES ('28', '1', '2017-06-06 22:07:56');
INSERT INTO `login_log` VALUES ('29', '3', '2017-06-06 22:26:26');
INSERT INTO `login_log` VALUES ('30', '1', '2017-06-06 22:36:10');
INSERT INTO `login_log` VALUES ('31', '4', '2017-06-06 22:40:39');
INSERT INTO `login_log` VALUES ('32', '1', '2017-06-06 22:41:43');
INSERT INTO `login_log` VALUES ('33', '4', '2017-06-06 22:42:04');
INSERT INTO `login_log` VALUES ('34', '1', '2017-06-06 22:42:56');
INSERT INTO `login_log` VALUES ('35', '4', '2017-06-06 22:47:38');
INSERT INTO `login_log` VALUES ('36', '1', '2017-06-06 22:48:55');
INSERT INTO `login_log` VALUES ('37', '4', '2017-06-06 22:50:28');
INSERT INTO `login_log` VALUES ('38', '1', '2017-06-06 23:34:02');

-- ----------------------------
-- Table structure for mail_sms_log
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

-- ----------------------------
-- Records of mail_sms_log
-- ----------------------------
INSERT INTO `mail_sms_log` VALUES ('716bf7d7-90a7-4b53-ac4c-0b5c50ab1a4d', '12312331@qq.com', '注册成功', '<div>用户【张荣英】已创建成功</div>\r\n<div>链接地址：<a href=\"http://172420r78o.51mypc.cn/index\">http://172420r78o.51mypc.cn/index</a></div>', '', '2017-06-06 22:40:27', '\0');
INSERT INTO `mail_sms_log` VALUES ('c22d245d-07f0-48ae-a205-0343f78fd028', '23234234@qq.com', '注册成功', '<div>用户【张荣英】已创建成功</div>\r\n<div>链接地址：<a href=\"http://172420r78o.51mypc.cn/index\">http://172420r78o.51mypc.cn/index</a></div>', '', '2017-06-06 22:25:10', '\0');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth_func
-- ----------------------------
INSERT INTO `sys_auth_func` VALUES ('1', '0', '系统管理', 'M1', '1', '', '', 'fa fa-cogs', '3');
INSERT INTO `sys_auth_func` VALUES ('2', '1', '用户管理', 'M2', '1', '/pages/user/list', '', '', '1');
INSERT INTO `sys_auth_func` VALUES ('3', '1', '角色管理', 'M3', '1', '/pages/role/list', '', '', '2');
INSERT INTO `sys_auth_func` VALUES ('4', '2', '新增', 'P4', '2', '', '', '', '0');
INSERT INTO `sys_auth_func` VALUES ('5', '2', '编辑', 'P5', '2', '', '', '', '0');
INSERT INTO `sys_auth_func` VALUES ('6', '2', '修改密码', 'P6', '2', '', '', '', '0');
INSERT INTO `sys_auth_func` VALUES ('7', '3', '新增', 'P7', '2', '', '', '', '0');
INSERT INTO `sys_auth_func` VALUES ('8', '3', '编辑', 'P8', '2', '', '', '', '0');
INSERT INTO `sys_auth_func` VALUES ('9', '3', '修改权限', 'P9', '2', '', '', '', '0');
INSERT INTO `sys_auth_func` VALUES ('10', '0', '招标信息', 'M10', '1', null, null, 'fa fa-database', '2');
INSERT INTO `sys_auth_func` VALUES ('11', '10', '供应商信息', 'M11', '1', '/pages/supplier/list', null, null, '1');
INSERT INTO `sys_auth_func` VALUES ('12', '11', '新增', 'P12', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('13', '11', '编辑', 'P13', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('14', '10', '项目信息', 'M14', '1', '/pages/project/list', null, null, '2');
INSERT INTO `sys_auth_func` VALUES ('15', '14', '新增', 'P15', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('16', '14', '编辑', 'P16', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('19', '14', '招标', 'P19', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('20', '14', '评分', 'P20', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('21', '14', '删除', 'P21', '2', null, null, null, '0');
INSERT INTO `sys_auth_func` VALUES ('22', '11', '删除', 'P22', '2', null, null, null, '0');

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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '1', '2017-04-06', '系统管理员');
INSERT INTO `sys_role` VALUES ('2', '评标管理员', '1', '2017-04-07', '评标');
INSERT INTO `sys_role` VALUES ('3', '业主单位', '1', '2017-04-07', '业主');
INSERT INTO `sys_role` VALUES ('4', '供应商', '1', '2017-04-07', '供应商');

-- ----------------------------
-- Table structure for sys_role_func
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_func`;
CREATE TABLE `sys_role_func` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限表',
  `Role_Id` int(11) NOT NULL,
  `Func_Id` int(11) NOT NULL COMMENT '权限功能ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_func
-- ----------------------------
INSERT INTO `sys_role_func` VALUES ('95', '1', '0');
INSERT INTO `sys_role_func` VALUES ('96', '1', '10');
INSERT INTO `sys_role_func` VALUES ('97', '1', '11');
INSERT INTO `sys_role_func` VALUES ('98', '1', '12');
INSERT INTO `sys_role_func` VALUES ('99', '1', '13');
INSERT INTO `sys_role_func` VALUES ('100', '1', '22');
INSERT INTO `sys_role_func` VALUES ('101', '1', '14');
INSERT INTO `sys_role_func` VALUES ('102', '1', '15');
INSERT INTO `sys_role_func` VALUES ('103', '1', '16');
INSERT INTO `sys_role_func` VALUES ('104', '1', '19');
INSERT INTO `sys_role_func` VALUES ('105', '1', '20');
INSERT INTO `sys_role_func` VALUES ('106', '1', '21');
INSERT INTO `sys_role_func` VALUES ('107', '1', '1');
INSERT INTO `sys_role_func` VALUES ('108', '1', '2');
INSERT INTO `sys_role_func` VALUES ('109', '1', '4');
INSERT INTO `sys_role_func` VALUES ('110', '1', '5');
INSERT INTO `sys_role_func` VALUES ('111', '1', '6');
INSERT INTO `sys_role_func` VALUES ('112', '1', '3');
INSERT INTO `sys_role_func` VALUES ('113', '1', '7');
INSERT INTO `sys_role_func` VALUES ('114', '1', '8');
INSERT INTO `sys_role_func` VALUES ('115', '1', '9');
INSERT INTO `sys_role_func` VALUES ('139', '2', '0');
INSERT INTO `sys_role_func` VALUES ('140', '2', '10');
INSERT INTO `sys_role_func` VALUES ('141', '2', '11');
INSERT INTO `sys_role_func` VALUES ('142', '2', '14');
INSERT INTO `sys_role_func` VALUES ('143', '2', '16');
INSERT INTO `sys_role_func` VALUES ('144', '2', '19');
INSERT INTO `sys_role_func` VALUES ('145', '2', '20');
INSERT INTO `sys_role_func` VALUES ('146', '3', '0');
INSERT INTO `sys_role_func` VALUES ('147', '3', '10');
INSERT INTO `sys_role_func` VALUES ('148', '3', '11');
INSERT INTO `sys_role_func` VALUES ('149', '3', '14');
INSERT INTO `sys_role_func` VALUES ('150', '3', '20');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户所属角色',
  `Role_Id` int(11) NOT NULL COMMENT '角色ID',
  `User_Id` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '4', '2');
INSERT INTO `sys_role_user` VALUES ('3', '2', '3');
INSERT INTO `sys_role_user` VALUES ('4', '2', '3');
INSERT INTO `sys_role_user` VALUES ('5', '2', '4');

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
  `LoginNum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '系统管理员', 'admin@qq.com', '', 'GefWZf9VML9Lr3Oc/A1ERw==', '1', '28');
INSERT INTO `sys_user` VALUES ('2', 'zhangry', '张荣英', 'ceshi@qq.com', '18628240591', 'GefWZf9VML9Lr3Oc/A1ERw==', '1', '5');
INSERT INTO `sys_user` VALUES ('3', 'wbg', '王八', '23234234@qq.com', '13244455456', '4QrcOUm6Wau+VuBX8g+IPg==', '1', '1');
INSERT INTO `sys_user` VALUES ('4', 'pingbiao', '评标管理员', '12312331@qq.com', '15882195555', '4QrcOUm6Wau+VuBX8g+IPg==', '1', '4');
