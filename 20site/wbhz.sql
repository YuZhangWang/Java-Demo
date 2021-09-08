/*
Navicat MySQL Data Transfer

Source Server         : a
Source Server Version : 50087
Source Host           : localhost:3306
Source Database       : wbhz

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2020-11-03 15:38:19
*/

SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`
(
    `admin_id`         int(11) NOT NULL auto_increment,
    `admin_login_name` varchar(50) default NULL,
    `admin_pwd`        varchar(50) default NULL,
    `admin_name`       varchar(50) default NULL,
    `admin_hobby`      varchar(50) default NULL COMMENT '爱好',
    `admin_create`     timestamp NULL default NULL,
    `admin_error`      int(11) default '0' COMMENT '错误次数',
    `admin_state`      int(11) default '0' COMMENT '0-正常，1-锁定',
    PRIMARY KEY (`admin_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin`
VALUES ('1', 'zhangsan', 'E10ADC3949BA59ABBE56E057F20F883E', '张三', '唱歌,跳舞', '2020-10-19 16:44:17', '0', '1');
INSERT INTO `t_admin`
VALUES ('2', 'lisi', 'E10ADC3949BA59ABBE56E057F20F883E', '李四', '唱歌,画画', '2020-11-03 14:32:57', '0', '0');
INSERT INTO `t_admin`
VALUES ('3', 'wangwu', 'E10ADC3949BA59ABBE56E057F20F883E', '王五', '唱歌,吃饭', '2020-11-03 14:33:25', '0', '0');
INSERT INTO `t_admin`
VALUES ('4', 'zhubajie', '96E79218965EB72C92A549DD5A330112', '猪八戒', '唱歌,跳舞,画画,篮球', '2020-11-03 15:17:04', '0', '0');
