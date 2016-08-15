/*
 Navicat Premium Data Transfer

 Source Server         : xrt
 Source Server Type    : MySQL
 Source Server Version : 50629
 Source Host           : localhost
 Source Database       : bbs

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : utf-8

 Date: 07/21/2016 19:02:22 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `BOARDS`
-- ----------------------------
DROP TABLE IF EXISTS `BOARDS`;
CREATE TABLE `BOARDS` (
  `boardId` int(11) NOT NULL AUTO_INCREMENT,
  `boardName` varchar(50) NOT NULL,
  `creatorId` int(11) NOT NULL,
  `masterId` int(11) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `parentId` int(11) NOT NULL,
  `point` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`boardId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `BOARDS`
-- ----------------------------
BEGIN;
INSERT INTO `BOARDS` VALUES ('1', '.NET技术', '1', '1', '2016-07-19 09:12:25', '0', '0', '1'), ('2', 'Java技术', '1', '1', '2016-07-19 09:13:18', '0', '0', '1'), ('3', '数据库技术', '1', '1', '2016-07-19 09:13:40', '0', '0', '1'), ('4', '娱乐', '1', '1', '2016-07-19 09:13:55', '0', '0', '1'), ('5', 'C#语言', '1', '1', '2016-07-19 09:14:31', '1', '0', '1'), ('6', 'winForms', '1', '1', '2016-07-19 09:14:54', '1', '0', '1'), ('7', 'ADO.NET', '1', '1', '2016-07-19 09:15:10', '1', '0', '1'), ('8', 'ASP.NET', '1', '1', '2016-07-19 09:15:27', '1', '0', '1'), ('9', 'Java基础', '1', '1', '2016-07-19 09:15:50', '2', '0', '1'), ('10', 'JSP技术', '1', '1', '2016-07-19 09:16:06', '2', '0', '1'), ('11', 'Servlet技术', '1', '1', '2016-07-19 09:16:37', '2', '0', '1'), ('12', 'Eclipse应用', '1', '1', '2016-07-19 09:16:57', '2', '0', '1'), ('13', 'SQL Server基础', '1', '1', '2016-07-19 09:17:25', '3', '0', '1'), ('14', 'SQL Server高级', '1', '1', '2016-07-19 09:17:41', '3', '0', '1'), ('15', '灌水乐园', '1', '1', '2016-07-19 09:17:58', '4', '0', '1');
COMMIT;

-- ----------------------------
--  Table structure for `MESSAGES`
-- ----------------------------
DROP TABLE IF EXISTS `MESSAGES`;
CREATE TABLE `MESSAGES` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mtitle` varchar(200) NOT NULL,
  `mcontents` text NOT NULL,
  `fromid` int(11) DEFAULT NULL,
  `toid` int(11) DEFAULT NULL,
  `isToDel` int(11) DEFAULT NULL,
  `isFromDel` int(11) DEFAULT NULL,
  `sendDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `REPLYS`
-- ----------------------------
DROP TABLE IF EXISTS `REPLYS`;
CREATE TABLE `REPLYS` (
  `replyId` int(11) NOT NULL AUTO_INCREMENT,
  `rtitle` varchar(200) DEFAULT NULL,
  `rcontents` text NOT NULL,
  `rface` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `topicid` int(11) DEFAULT NULL,
  `postdate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `REPLYS`
-- ----------------------------
BEGIN;
INSERT INTO `REPLYS` VALUES ('1', '顶顶顶', '哈哈哈哈', null, '3', '7', '2016-07-21 12:26:14', '2016-07-21 12:26:17', '1'), ('2', '楼主傻逼', '大傻逼', null, '1', '7', '2016-07-21 12:26:40', '2016-07-21 12:26:42', '1'), ('3', '踩踩踩', '嚯嚯嚯', null, '4', '7', '2016-07-21 12:27:08', '2016-07-21 12:27:10', '1'), ('4', '楼主傻逼', '哈哈哈哈', null, '3', '7', '2016-07-21 12:27:35', '2016-07-21 12:27:36', '1'), ('5', '你好', '你好啊', null, '2', '7', '2016-07-21 12:27:56', '2016-07-21 12:27:58', '1'), ('6', '踩踩踩', '嘿嘿嘿', null, '1', '7', '2016-07-21 12:28:28', '2016-07-21 12:28:30', '1'), ('7', null, '是啊，难得一米！！', null, '1', '40', '2016-07-21 15:33:43', '2016-07-21 15:33:45', '1'), ('8', '', '自古二楼出傻逼', null, '2', '40', '2016-07-21 15:49:42', '2016-07-21 15:49:42', '1'), ('9', 'fdasdfaf', 'dfasdfdsfdsfsdfs', null, '1', '41', '2016-07-21 17:58:15', '2016-07-21 17:58:15', '0'), ('10', '', 'fdsasadfdsafascxzvzxcvxczvxz', null, '1', '41', '2016-07-21 17:58:19', '2016-07-21 17:58:19', '0'), ('11', '', 'fsdfsdfdsf', null, '1', '41', '2016-07-21 17:58:22', '2016-07-21 17:58:22', '0');
COMMIT;

-- ----------------------------
--  Table structure for `TOPICS`
-- ----------------------------
DROP TABLE IF EXISTS `TOPICS`;
CREATE TABLE `TOPICS` (
  `topicId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `tcontents` text NOT NULL,
  `treplycontents` text NOT NULL,
  `tface` int(11) DEFAULT NULL,
  `isreply` int(11) DEFAULT NULL,
  `readpoint` int(11) DEFAULT NULL,
  `accesspoint` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `boardid` int(11) DEFAULT NULL,
  `postdate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `TOPICS`
-- ----------------------------
BEGIN;
INSERT INTO `TOPICS` VALUES ('1', '冒个泡', '大家好', ' ', null, null, null, null, '1', '15', '2016-07-20 16:52:34', '2016-07-20 16:52:42', '1'), ('2', '新人报到', '大家好呀', ' ', null, null, null, null, '1', '15', '2016-07-20 16:53:49', '2016-07-20 16:53:53', '1'), ('3', '嗨起来', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 17:04:22', '2016-07-20 17:04:24', '1'), ('4', '嗨起来', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 17:04:26', '2016-07-20 17:04:28', '1'), ('5', '神图镇楼', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 17:04:44', '2016-07-20 17:04:46', '1'), ('6', '有没有人知道', '嗨起来', ' ', null, null, null, null, '1', '15', '2016-07-20 17:04:49', '2016-07-20 17:04:51', '1'), ('7', '神图镇楼', '大家好', ' ', null, null, null, null, '2', '15', '2016-07-20 17:05:02', '2016-07-20 17:05:04', '1'), ('8', '嗨起来', '划水', ' ', null, null, null, null, '3', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('9', '大家好', '嗨起来', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('10', '有没有人知道', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('11', '划水', '嗨起来', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('12', '嗨起来', '求助', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('13', '神图镇楼', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('14', '求助', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('15', '嗨起来', '划水', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('16', '有没有人知道', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('17', '嗨起来', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('18', '嗨起来', '求助', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('19', '神图镇楼', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('20', '求助', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('21', '嗨起来', '划水', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('22', '有没有人知道', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('23', '嗨起来', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('24', '嗨起来', '划水', ' ', null, null, null, null, '3', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('25', '大家好', '嗨起来', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('26', '有没有人知道', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('27', '划水', '嗨起来', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('28', '嗨起来', '求助', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('29', '神图镇楼', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('30', '求助', '嗨起来', ' ', null, null, null, null, '2', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('31', '嗨起来', '划水', ' ', null, null, null, null, '1', '15', '2016-07-20 00:00:00', '2016-07-20 00:00:00', '1'), ('32', '嗨起来', '嗨起来', ' ', null, null, null, null, '2', '14', '2016-07-01 12:43:52', '2016-07-05 12:43:57', '1'), ('33', '神图镇楼', '嗨起来', ' ', null, null, null, null, '2', '14', '2016-07-11 12:44:47', '2016-07-12 12:44:51', '1'), ('34', '有没有人知道', '嗨起来', ' ', null, null, null, null, '1', '14', '2016-07-19 12:45:09', '2016-07-19 12:45:13', '0'), ('35', '神图镇楼', '大家好', ' ', null, null, null, null, '2', '14', '2016-07-16 12:45:00', '2016-07-18 12:45:03', '1'), ('36', '嗨起来', '划水', ' ', null, null, null, null, '3', '14', '2016-07-21 12:44:11', '2016-07-21 12:44:18', '1'), ('37', '大家好', '嗨起来', ' ', null, null, null, null, '1', '14', '2016-07-10 12:44:38', '2016-07-12 12:44:42', '1'), ('38', '有没有人知道', '嗨起来', ' ', null, null, null, null, '2', '14', '2016-07-12 12:44:30', '2016-07-13 12:44:34', '1'), ('39', '划水', '嗨起来', ' ', null, null, null, null, '1', '14', '2016-07-13 12:44:22', '2016-07-14 12:44:26', '0'), ('40', 'JAVA好难啊！！！', '好难啊！！！！', '', null, null, null, null, '3', '9', '2016-07-21 15:01:05', '2016-07-21 15:01:05', '1'), ('41', 'bbbbb', 'aaa', '', null, null, null, null, '1', '5', '2016-07-21 17:58:04', '2016-07-21 18:26:20', '0');
COMMIT;

-- ----------------------------
--  Table structure for `USERS`
-- ----------------------------
DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `loginpwd` varchar(32) NOT NULL,
  `email` varchar(50) NOT NULL,
  `head` varchar(100) NOT NULL,
  `regtime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `point` int(11) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `USERS`
-- ----------------------------
BEGIN;
INSERT INTO `USERS` VALUES ('1', '熊睿滔', '202cb962ac59075b964b07152d234b70', 'xk6465@qq.com', 'imgs/head/5.gif', '2016-07-21 11:06:12', '1', '0'), ('2', '熊大', '202cb962ac59075b964b07152d234b70', 'xrt93826@gmail.com', 'imgs/head/11.gif', '2016-07-21 11:06:46', '1', '0'), ('3', '熊二', '202cb962ac59075b964b07152d234b70', '553430992@qq.com', 'imgs/head/2.gif', '2016-07-21 11:07:15', '1', '0'), ('4', 'xrt', '202cb962ac59075b964b07152d234b70', '553430992@qq.com', 'imgs/head/4.gif', '2016-07-21 11:07:45', '1', '0'), ('5', 'Bichon', '202cb962ac59075b964b07152d234b70', 'xk6465@qq.com', 'imgs/head/3.gif', '2016-07-21 11:08:11', '1', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
