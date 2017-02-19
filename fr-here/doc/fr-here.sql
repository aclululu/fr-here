/*
Navicat MySQL Data Transfer

Source Server         : openfire
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : fr-here

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2017-02-19 15:07:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ct_appwelcome`
-- ----------------------------
DROP TABLE IF EXISTS `ct_appwelcome`;
CREATE TABLE `ct_appwelcome` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'app进入界面图片',
  `picurl` varchar(100) DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_appwelcome
-- ----------------------------


-- ----------------------------
-- Table structure for `ct_movie`
-- ----------------------------
DROP TABLE IF EXISTS `ct_movie`;
CREATE TABLE `ct_movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `picurl` varchar(200) DEFAULT NULL,
  `movieurl` varchar(200) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `abstract_` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_movie
-- ----------------------------

-- ----------------------------
-- Table structure for `ct_news`
-- ----------------------------
DROP TABLE IF EXISTS `ct_news`;
CREATE TABLE `ct_news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CID` int(11) DEFAULT NULL COMMENT '栏目',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` longtext COMMENT '内容',
  `abstract_` varchar(500) DEFAULT NULL COMMENT '摘要',
  `keywords` varchar(20) DEFAULT NULL COMMENT '关键词',
  `picnews` int(2) DEFAULT NULL COMMENT '是否是图片新闻',
  `picture` varchar(100) DEFAULT NULL COMMENT '图片',
  `origin` varchar(20) DEFAULT NULL COMMENT '来源',
  `cdate` datetime DEFAULT NULL COMMENT '撰写时间',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `editor` varchar(20) DEFAULT NULL COMMENT '修改人',
  `clicks` int(11) DEFAULT NULL COMMENT '点击量',
  `status` int(2) DEFAULT NULL COMMENT '-1未发布 0已发布 1废弃',
  `priority` int(11) DEFAULT NULL COMMENT '优先级  数越大优先级越高',
  PRIMARY KEY (`ID`),
  KEY `FK_NEWS` (`CID`),
  CONSTRAINT `FK_NEWS` FOREIGN KEY (`CID`) REFERENCES `ct_newscolumn` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_news
-- ----------------------------

-- ----------------------------
-- Table structure for `ct_newscolumn`
-- ----------------------------
DROP TABLE IF EXISTS `ct_newscolumn`;
CREATE TABLE `ct_newscolumn` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_newscolumn
-- ----------------------------
INSERT INTO `ct_newscolumn` VALUES ('2', '新闻资讯');
INSERT INTO `ct_newscolumn` VALUES ('3', '国内新闻');
INSERT INTO `ct_newscolumn` VALUES ('4', '广东新闻');
INSERT INTO `ct_newscolumn` VALUES ('5', '佛山新');
INSERT INTO `ct_newscolumn` VALUES ('6', '重点业务');
INSERT INTO `ct_newscolumn` VALUES ('7', '技术资源');
INSERT INTO `ct_newscolumn` VALUES ('8', '员工活动');

-- ----------------------------
-- Table structure for `ct_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `ct_privilege`;
CREATE TABLE `ct_privilege` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PID` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_privilege
-- ----------------------------
INSERT INTO `ct_privilege` VALUES ('14', '0', '管理员管理', '', '管理员管理');
INSERT INTO `ct_privilege` VALUES ('15', '0', '角色管理', '', '角色管理');
INSERT INTO `ct_privilege` VALUES ('16', '0', '权限管理', '', '权限管理');
INSERT INTO `ct_privilege` VALUES ('17', '14', '查询管理员', '/fr-here/rest/sys/user/list', '查询管理员');
INSERT INTO `ct_privilege` VALUES ('18', '14', '添加管理员', '/fr-here/rest/sys/user/toadd', '添加管理员');
INSERT INTO `ct_privilege` VALUES ('19', '15', '查询角色', '/fr-here/rest/sys/role/list', '查询角色');
INSERT INTO `ct_privilege` VALUES ('20', '15', '添加角色', '/fr-here/rest/sys/role/toadd', '添加角色');
INSERT INTO `ct_privilege` VALUES ('21', '16', '查询权限', '/fr-here/rest/sys/privilege/list', '查询权限');
INSERT INTO `ct_privilege` VALUES ('22', '16', '添加权限', '/fr-here/rest/sys/privilege/toadd', '添加权限');
INSERT INTO `ct_privilege` VALUES ('23', '0', 'app欢迎图片管理', '', 'app欢迎图片管理');
INSERT INTO `ct_privilege` VALUES ('25', '23', '图片管理', '/fr-here/rest/app/welcome/list', '图片管理');
INSERT INTO `ct_privilege` VALUES ('26', '0', '视频管理', '', '视频管理');
INSERT INTO `ct_privilege` VALUES ('27', '26', '查询视频', '/fr-here/rest/app/movie/list', '视频管理');
INSERT INTO `ct_privilege` VALUES ('28', '26', '添加视频', '/fr-here/rest/app/movie/toaddorupdate?id=0', '');

-- ----------------------------
-- Table structure for `ct_role`
-- ----------------------------
DROP TABLE IF EXISTS `ct_role`;
CREATE TABLE `ct_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `info` varchar(100) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_role
-- ----------------------------
INSERT INTO `ct_role` VALUES ('8', '超级管理员', '超级管理员');
INSERT INTO `ct_role` VALUES ('9', '角色权限管理', '角色权限管理');
INSERT INTO `ct_role` VALUES ('10', '新闻供需管理员', '新闻供需管理');

-- ----------------------------
-- Table structure for `ct_role_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `ct_role_privilege`;
CREATE TABLE `ct_role_privilege` (
  `roleID` int(11) NOT NULL,
  `privilegeID` int(11) NOT NULL,
  KEY `FK_PRIVILEGE` (`privilegeID`),
  KEY `FK_ROLE` (`roleID`),
  CONSTRAINT `FK_PRIVILEGE` FOREIGN KEY (`privilegeID`) REFERENCES `ct_privilege` (`ID`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`roleID`) REFERENCES `ct_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_role_privilege
-- ----------------------------
INSERT INTO `ct_role_privilege` VALUES ('9', '19');
INSERT INTO `ct_role_privilege` VALUES ('9', '20');
INSERT INTO `ct_role_privilege` VALUES ('9', '21');
INSERT INTO `ct_role_privilege` VALUES ('9', '22');
INSERT INTO `ct_role_privilege` VALUES ('10', '17');
INSERT INTO `ct_role_privilege` VALUES ('10', '18');
INSERT INTO `ct_role_privilege` VALUES ('10', '19');
INSERT INTO `ct_role_privilege` VALUES ('10', '20');
INSERT INTO `ct_role_privilege` VALUES ('10', '21');
INSERT INTO `ct_role_privilege` VALUES ('10', '22');
INSERT INTO `ct_role_privilege` VALUES ('8', '17');
INSERT INTO `ct_role_privilege` VALUES ('8', '18');
INSERT INTO `ct_role_privilege` VALUES ('8', '19');
INSERT INTO `ct_role_privilege` VALUES ('8', '20');
INSERT INTO `ct_role_privilege` VALUES ('8', '21');
INSERT INTO `ct_role_privilege` VALUES ('8', '22');
INSERT INTO `ct_role_privilege` VALUES ('8', '25');
INSERT INTO `ct_role_privilege` VALUES ('8', '27');
INSERT INTO `ct_role_privilege` VALUES ('8', '28');

-- ----------------------------
-- Table structure for `ct_supply`
-- ----------------------------
DROP TABLE IF EXISTS `ct_supply`;
CREATE TABLE `ct_supply` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CID` int(11) DEFAULT NULL COMMENT '栏目',
  `SIO` int(2) DEFAULT NULL COMMENT '供或者需  0  or  1',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `cdate` datetime DEFAULT NULL COMMENT '发布时间',
  `status` int(2) DEFAULT NULL COMMENT '-1未发布 0已发布 1已完成 2已结束',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `suname` varchar(20) DEFAULT NULL COMMENT '发布者',
  `openid` varchar(100) DEFAULT NULL,
  `overdate` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`ID`),
  KEY `FK_SUPPLYD` (`CID`),
  CONSTRAINT `FK_SUPPLYD` FOREIGN KEY (`CID`) REFERENCES `ct_supplycolumn` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ct_supply
-- ----------------------------

-- ----------------------------
-- Table structure for `ct_supplycolumn`
-- ----------------------------
DROP TABLE IF EXISTS `ct_supplycolumn`;
CREATE TABLE `ct_supplycolumn` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk COMMENT='渚涢渶鏍忕洰';

-- ----------------------------
-- Records of ct_supplycolumn
-- ----------------------------


-- ----------------------------
-- Table structure for `ct_sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `ct_sysuser`;
CREATE TABLE `ct_sysuser` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `status` int(2) DEFAULT NULL COMMENT '状态  0正常  -1 锁定',
  `roleID` int(11) DEFAULT NULL COMMENT '关联角色id',
  PRIMARY KEY (`ID`),
  KEY `FK_SYSUSER` (`roleID`),
  CONSTRAINT `FK_SYSUSER` FOREIGN KEY (`roleID`) REFERENCES `ct_role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk COMMENT='鍚庡彴绯荤粺绠＄悊鍛樼敤鎴疯〃';

-- ----------------------------
-- Records of ct_sysuser
-- ----------------------------
INSERT INTO `ct_sysuser` VALUES ('16', 'shli', '123456', '1', '8');
INSERT INTO `ct_sysuser` VALUES ('17', 'shli1', '123456', '1', '9');
INSERT INTO `ct_sysuser` VALUES ('18', 'shli2', '123456', '-1', '10');

-- ----------------------------
-- Procedure structure for `getl`
-- ----------------------------
DROP PROCEDURE IF EXISTS `getl`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getl`(in orgPId varchar(50))
BEGIN
    DECLARE sTemp VARCHAR(1000);
    DECLARE sTempChd VARCHAR(1000);

    #get org child Iplist
    SET sTemp = '#';
    SET sTempChd = orgPId;
   
    WHILE sTempChd is not null DO
         SET sTemp = concat(sTemp,',',sTempChd);
         SELECT group_concat(Id) INTO sTempChd FROM newscolumns where FIND_IN_SET(ParentId,sTempChd)>0;
    END WHILE;
    #SELECT getChildList(orgPId) into orgIdList ;

    # select all torg by orgIdList
    SET @str = CONCAT('select * from newscolumns where FIND_IN_SET(Id,"', sTemp,'")');
    PREPARE stmt FROM @str;
    EXECUTE stmt;
   DEALLOCATE PREPARE stmt;

END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `getChildList2`
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildList2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildList2`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
	DECLARE sTemp VARCHAR(1000);
	DECLARE sTempChd VARCHAR(1000);

	SET sTemp = '$';
	SET sTempChd =cast(rootId as CHAR);

	WHILE sTempChd is not null DO
		SET sTemp = concat(sTemp,',',sTempChd);
		SELECT group_concat(Id) INTO sTempChd FROM productscolumn where FIND_IN_SET(ParentId,sTempChd)>0;
	END WHILE;
	RETURN sTemp;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `getChildLst`
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildLst`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildLst`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
	DECLARE sTemp VARCHAR(1000);
	DECLARE sTempChd VARCHAR(1000);

	SET sTemp = '$';
	SET sTempChd =cast(rootId as CHAR);

	WHILE sTempChd is not null DO
		SET sTemp = concat(sTemp,',',sTempChd);
		SELECT group_concat(Id) INTO sTempChd FROM newscolumns where FIND_IN_SET(ParentId,sTempChd)>0;
	END WHILE;
	RETURN sTemp;
END
;;
DELIMITER ;
