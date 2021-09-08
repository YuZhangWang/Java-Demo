/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : flower

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-11-03 23:38:13
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `adminID`   int(32) NOT NULL AUTO_INCREMENT,
    `adminName` varchar(20) COLLATE utf8_bin  DEFAULT NULL,
    `adminCode` varchar(20) COLLATE utf8_bin NOT NULL,
    `adminPwd`  varchar(32) COLLATE utf8_bin NOT NULL,
    `sex`       char(2) COLLATE utf8_bin      DEFAULT NULL,
    `brith`     varchar(20) COLLATE utf8_bin  DEFAULT NULL,
    `mobile`    varchar(20) COLLATE utf8_bin  DEFAULT NULL,
    `address`   varchar(100) COLLATE utf8_bin DEFAULT NULL,
    PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin`
VALUES ('1', '1142539004', '1142539004', '666', '男', '1997-4', '18344561746', '广州市天河区天源路789');
INSERT INTO `admin`
VALUES ('2', '123', '123', '123', '男', '1996-9', '11111111111', '广州市天河区天源路789');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `goodsID`   int(11) NOT NULL AUTO_INCREMENT,
    `ordersID`  int(50) NOT NULL,
    `productID` int(50) NOT NULL,
    `num`       int(11) NOT NULL,
    `price`     double NOT NULL,
    PRIMARY KEY (`goodsID`),
    KEY         `ordersID` (`ordersID`),
    KEY         `productID` (`productID`),
    CONSTRAINT `ordersID` FOREIGN KEY (`ordersID`) REFERENCES `orders` (`ordersID`),
    CONSTRAINT `productID` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `inventory`
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`
(
    `inventoryID`  int(11) NOT NULL AUTO_INCREMENT,
    `productID`    int(50) DEFAULT NULL,
    `inventoryNum` int(11) DEFAULT NULL,
    PRIMARY KEY (`inventoryID`),
    KEY            `productID` (`productID`),
    CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of inventory
-- ----------------------------

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `ordersID`         int(50) NOT NULL AUTO_INCREMENT,
    `userID`           int(32) NOT NULL,
    `userName`         varchar(20) COLLATE utf8_bin DEFAULT NULL,
    `userPhone`        varchar(20) COLLATE utf8_bin DEFAULT NULL,
    `userEmail`        varchar(30) COLLATE utf8_bin DEFAULT NULL,
    `consigneeName`    varchar(50) COLLATE utf8_bin DEFAULT NULL,
    `consigneePhone`   varchar(20) COLLATE utf8_bin DEFAULT NULL,
    `consigneeAddress` varchar(50) COLLATE utf8_bin DEFAULT NULL,
    `ordersTime`       datetime                     DEFAULT NULL,
    `verify`           varchar(20) COLLATE utf8_bin DEFAULT NULL,
    `ordersMoney`      double                       DEFAULT NULL,
    PRIMARY KEY (`ordersID`),
    KEY                `memberID` (`userID`),
    CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `productID`   int(50) NOT NULL AUTO_INCREMENT,
    `productName` varchar(30) COLLATE utf8_bin NOT NULL,
    `picture`     varchar(50) COLLATE utf8_bin NOT NULL,
    `talkTo`      varchar(100) COLLATE utf8_bin DEFAULT NULL,
    `price`       double(10, 0
) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `materials` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `packaging` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `marketPrice` double(10,0) DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='花材\r\n';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product`
VALUES ('1', '美丽的诺言', 'images/fl_01.jpg', '花儿说，为了你我不愿随风而去；花儿说，珍藏我们的感情就是珍藏记忆；花儿说，珍藏我们的记忆就是珍藏甜蜜！', '298', '1',
        '11枝红玫瑰，满天星（或者水晶草）、黄莺（或者绿叶）间插，随机赠送两只可爱小公仔。', '白色卷边纸内衬，粉色卷边纸多层圆形包装，粉色法式结束扎。', '219');
INSERT INTO `product`
VALUES ('2', '钟爱一生', 'images/fl_02.jpg', '只想和你忘掉一切烦恼，尽情沉醉在最浪漫的气氛中', '159', '1', '11朵香槟玫瑰，黄英，绿叶，红豆搭配', '深棕硬纸外包，咖啡色蝴蝶结束花',
        '258');
INSERT INTO `product`
VALUES ('3', '天天祝福', 'images/fl_03.jpg', '喜欢像是一阵风，而爱你是细水长流', '269', '1', '33朵红玫瑰花，满天星、绿叶外围搭配', '黑色雾面纸高档包装', '358');
INSERT INTO `product`
VALUES ('4', '恋你一生', 'images/fl_04.jpg', '你的轻柔像阵微风，让我从容不迫，想让你知道，我对你始终一往情深', '149', '1', '13朵粉玫瑰  相思梅、栀子叶外围搭配',
        '白色光板内衬纸，英文报纸外层包装，咖啡色蝴蝶结束花', '258');
INSERT INTO `product`
VALUES ('5', '邂逅的公主', 'images/fl_05.jpg',
        '与你相伴，日子里充斥着甜甜的味道，与你相恋，生活里填满了幸福的滋味，与你相守，人生里塞满了快乐的气息，亲爱的，有你的日子，每一天都是幸福，我爱你！\r\n', '150', '1', '粉玫瑰33支，相思梅搭配',
        '粉色包装纸圆形外包，粉色蝴蝶结系扎', '228');
INSERT INTO `product`
VALUES ('6', '情有独钟', 'images/fl_06.jpg', '下雨的时候，给她撑一把雨伞；寒冷的时候，给她一个温暖的臂弯；天黑了，永远有一盏灯为她点亮；晨起时，给她一缕温暖的阳光。爱她，就送她一束33枝的玫瑰花！',
        '129', '1', '33朵红玫瑰', '黑色双面纸黑纱包装', '239');
INSERT INTO `product`
VALUES ('7', '忘情巴黎', 'images/fl_07.jpg', '解开心扉，层层叠叠存储千语万言', '167', '1', '优选33枝嫣红玫瑰，配花搭配，圆形包装', '黑色包装纸精致包扎', '262');
INSERT INTO `product`
VALUES ('8', '爱你到老', 'images/fl_08.jpg', '爱上你是我今生最大的幸福，想你是我最甜蜜的痛苦，和你在一起是我的骄傲。没有你，我就像一只迷失了方向的船', '182', '1', '99朵红玫瑰',
        '黑色纱网高档包装纸', '326');
INSERT INTO `product`
VALUES ('9', '11', '11', '11', '1', '2', '11', '11', '11');
INSERT INTO `product`
VALUES ('10', '1', '1', '1', '1', '3', '1', '1', '1');
INSERT INTO `product`
VALUES ('11', '1', '1', '1', '1', '4', '1', '1', '1');
INSERT INTO `product`
VALUES ('12', '1', '1', '1', '1', '5', '1', '1', '1');

-- ----------------------------
-- Table structure for `shopingcar`
-- ----------------------------
DROP TABLE IF EXISTS `shopingcar`;
CREATE TABLE `shopingcar`
(
    `carID`     int(32) NOT NULL AUTO_INCREMENT,
    `productID` int(50) DEFAULT NULL,
    `userID`    int(50) DEFAULT NULL,
    `num`       int(11) DEFAULT NULL,
    PRIMARY KEY (`carID`),
    KEY         `proudctID` (`productID`),
    KEY         `memberID` (`userID`),
    CONSTRAINT `shopingcar_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`),
    CONSTRAINT `shopingcar_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of shopingcar
-- ----------------------------
INSERT INTO `shopingcar`
VALUES ('1', '2', '1', '1');
INSERT INTO `shopingcar`
VALUES ('2', '2', '1', '1');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`
(
    `type_id`   int(10) NOT NULL,
    `type_name` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`type_id`),
    CONSTRAINT `type_product` FOREIGN KEY (`type_id`) REFERENCES `product` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type`
VALUES ('1', '爱情鲜花');
INSERT INTO `type`
VALUES ('2', '生日鲜花');
INSERT INTO `type`
VALUES ('3', '精品礼盒花\r\n精品礼盒花\r\n精品礼盒花');
INSERT INTO `type`
VALUES ('4', '永生花');
INSERT INTO `type`
VALUES ('5', '开业花篮');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `userID`   int(32) NOT NULL AUTO_INCREMENT,
    `userName` varchar(20) COLLATE utf8_bin  DEFAULT NULL,
    `userCode` varchar(20) COLLATE utf8_bin NOT NULL,
    `userPwd`  varchar(32) COLLATE utf8_bin NOT NULL,
    `sex`      char(2) COLLATE utf8_bin      DEFAULT NULL,
    `brith`    varchar(20) COLLATE utf8_bin  DEFAULT NULL,
    `mobile`   varchar(20) COLLATE utf8_bin  DEFAULT NULL,
    `address`  varchar(100) COLLATE utf8_bin DEFAULT NULL,
    `email`    varchar(50) COLLATE utf8_bin  DEFAULT NULL,
    `qq`       varchar(50) COLLATE utf8_bin  DEFAULT NULL,
    PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('1', '小明', '123', '12a', null, null, null, null, null, null);
INSERT INTO `user`
VALUES ('2', '小李', '222', '22b', null, null, null, null, null, null);
