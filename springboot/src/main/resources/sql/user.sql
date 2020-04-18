/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : smallmovie

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-12-28 15:39:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '横店龙套影视演员', '2019-12-21 18:48:16', '西瓜视频', '2');
INSERT INTO `user` VALUES ('2', '我的农村365', '2019-12-21 18:48:19', '西瓜视频', '2');
INSERT INTO `user` VALUES ('3', '信誓旦旦', '2019-12-21 18:52:47', 'bilibili', '3');
INSERT INTO `user` VALUES ('4', '小六视野', '2019-12-21 19:00:42', '西瓜视频', '2');
INSERT INTO `user` VALUES ('5', '冒险雷探长', '2019-12-21 19:08:31', 'youtube', '1');
INSERT INTO `user` VALUES ('6', '鄂东老男孩', '2019-12-21 19:24:53', '西瓜视频', '2');
INSERT INTO `user` VALUES ('7', '巧妇9妹', '2019-12-21 19:25:22', '西瓜视频', '2');
INSERT INTO `user` VALUES ('8', '晶晶的生活日记', '2019-12-21 19:33:50', '西瓜视频', '2');
INSERT INTO `user` VALUES ('9', '康大飞', '2019-12-21 19:49:51', '西瓜视频', '2');
INSERT INTO `user` VALUES ('10', '李飞爆笑视频', '2019-12-21 19:50:24', '西瓜视频', '2');
INSERT INTO `user` VALUES ('11', '鼎盛达人', '2019-12-21 19:51:50', '西瓜视频', '2');
INSERT INTO `user` VALUES ('12', '我是郭杰瑞', '2019-12-21 20:20:43', 'bilibili', '3');
INSERT INTO `user` VALUES ('13', 'Jerry Kowal 我是郭杰瑞', '2019-12-21 20:22:36', 'youtube', '1');
INSERT INTO `user` VALUES ('14', '叶程枫', '2019-12-21 20:23:54', 'bilibili', '3');
INSERT INTO `user` VALUES ('15', '十音Shiyin', '2019-12-21 20:31:37', 'bilibili', '3');
INSERT INTO `user` VALUES ('16', '-LKs-', null, 'bilibili', '3');
INSERT INTO `user` VALUES ('17', '李子柒', null, 'bilibili', '3');
INSERT INTO `user` VALUES ('18', '李飞爆笑', null, 'B站', '3');
INSERT INTO `user` VALUES ('19', '李子柒', null, 'youtube', '1');
INSERT INTO `user` VALUES ('20', '老师好我叫何同学', null, 'B站', '3');
INSERT INTO `user` VALUES ('21', '科技美学', null, 'B站', '3');
INSERT INTO `user` VALUES ('22', '蜗牛小涛', null, 'B站', '3');
INSERT INTO `user` VALUES ('23', '我是都是洞', null, '西瓜视频', '2');
INSERT INTO `user` VALUES ('24', '李永乐老师', null, 'youtube', '1');
INSERT INTO `user` VALUES ('25', '李永乐老师', null, '西瓜视频', '2');
INSERT INTO `user` VALUES ('26', '李永乐老师', null, 'B站', '3');
INSERT INTO `user` VALUES ('27', '华农兄弟', null, 'B站', '3');
INSERT INTO `user` VALUES ('28', 'CodeSheep', null, null, '3');
INSERT INTO `user` VALUES ('29', '老番茄', null, 'B站', '3');
