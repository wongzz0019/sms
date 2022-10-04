/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : labtest

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/01/2021 20:06:03
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别1男2女3未知',
  `score` int(0) NULL DEFAULT NULL COMMENT '学生成绩',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES (1, '张三', 'aaaaaa', 1, 78);
INSERT INTO `tb_student` VALUES (2, '李四', '111111', 2, 90);
INSERT INTO `tb_student` VALUES (3, '赵六', '123456', 1, 87);
INSERT INTO `tb_student` VALUES (4, '赵六2', '123456', 1, 87);
INSERT INTO `tb_student` VALUES (5, '赵七', '123456', 1, 87);
INSERT INTO `tb_student` VALUES (6, '李子柒', '123456', 0, 89);
INSERT INTO `tb_student` VALUES (7, '李五', '123', 1, 23);
INSERT INTO `tb_student` VALUES (8, '李六', '123', 1, 23);
INSERT INTO `tb_student` VALUES (9, '李琦', '123', 1, 23);
INSERT INTO `tb_student` VALUES (10, '李八', '123', 1, 23);
INSERT INTO `tb_student` VALUES (11, '李十', '123', 1, 23);
INSERT INTO `tb_student` VALUES (12, '李十一', '123', 1, 23);
INSERT INTO `tb_student` VALUES (17, '李九', '123', 1, 23);
INSERT INTO `tb_student` VALUES (18, '管理员', '123456', 1, 100);

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` tinyint(0) NULL DEFAULT NULL,
  `classes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (1, '盛夏', 0, 'Java程序设计', '计算机科学与技术学院');
INSERT INTO `tb_teacher` VALUES (2, '李欢欢', 0, 'JavaScript程序设计', '计算机科学与技术学院');
INSERT INTO `tb_teacher` VALUES (3, '李泽阳', 1, 'Web开发', '人工智能学院');
INSERT INTO `tb_teacher` VALUES (4, '李永鸣', 1, '计算机网络', '人工智能学院');
INSERT INTO `tb_teacher` VALUES (5, '赵丽波', 0, '高等数学', '数统学院');
INSERT INTO `tb_teacher` VALUES (6, '孙志刚', 1, '操作系统原理', '计算机科学与技术学院');
INSERT INTO `tb_teacher` VALUES (7, '雷锋杨', 1, 'SpringBoot', '计算机科学与技术学院');
INSERT INTO `tb_teacher` VALUES (8, '张宇', 0, '毛泽东思想', '马克思学院');
INSERT INTO `tb_teacher` VALUES (9, '李立超', 1, 'Web前端基础', '人工智能学院');
INSERT INTO `tb_teacher` VALUES (25, '柴林燕', 0, 'JavaEE初级', '计算机科学与技术学院');
INSERT INTO `tb_teacher` VALUES (26, '宋红康', 1, 'Java高级进阶', '人工智能学院');
INSERT INTO `tb_teacher` VALUES (27, '秦疆', 1, 'Spring5', '人工智能学院');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户角色，所拥有的权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '123', '123', 'vip1');
INSERT INTO `tb_user` VALUES (2, '124', '123', 'vip2');
INSERT INTO `tb_user` VALUES (3, '125', '123', 'vip3');
INSERT INTO `tb_user` VALUES (4, '126', '123', 'vip1,vip2,vip3');

-- ----------------------------
-- Table structure for test_admin
-- ----------------------------
DROP TABLE IF EXISTS `test_admin`;
CREATE TABLE `test_admin`  (
  `id` int(0) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_admin
-- ----------------------------
INSERT INTO `test_admin` VALUES (1, 'admin', '$2a$10$dNhJrLynKgZMyFU41IKGwe/2qac.47UccOALadKwHJhtx61zQFXYe', 'admin');

-- ----------------------------
-- Table structure for test_authorities
-- ----------------------------
DROP TABLE IF EXISTS `test_authorities`;
CREATE TABLE `test_authorities`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_authorities
-- ----------------------------
INSERT INTO `test_authorities` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for test_book
-- ----------------------------
DROP TABLE IF EXISTS `test_book`;
CREATE TABLE `test_book`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `auth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_id` int(0) NOT NULL,
  `count` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_book
-- ----------------------------
INSERT INTO `test_book` VALUES (1, 'Spring Boot Vue全栈开发实战', '王松', 1, 2);
INSERT INTO `test_book` VALUES (2, 'Spring 5开发大全', '柳伟卫', 2, 2);
INSERT INTO `test_book` VALUES (3, '微信小程序开发实战', '黑马程序员', 3, 3);
INSERT INTO `test_book` VALUES (4, 'TCP IP详解卷1：协议 原书第2版', '理查德史蒂文斯', 4, 11);
INSERT INTO `test_book` VALUES (5, '计算机组成与设计  硬件软件接口 第5版', '戴维帕特森', 5, 1);
INSERT INTO `test_book` VALUES (6, '图解HTTP', '上野宣', 6, 9);
INSERT INTO `test_book` VALUES (7, '大话设计模式', '程杰', 7, 4);
INSERT INTO `test_book` VALUES (8, '大话数据结构', '程杰', 8, 5);
INSERT INTO `test_book` VALUES (9, 'java从入门到精通（第4版）', '明日科技', 9, 5);
INSERT INTO `test_book` VALUES (10, 'MySQL必知必会', 'BenForta', 10, 2);
INSERT INTO `test_book` VALUES (11, '图解TCPIP(第5版)', '竹下隆史', 11, 2);
INSERT INTO `test_book` VALUES (12, '看透springMvc源代码分析与实践', '韩路彪', 12, 2);
INSERT INTO `test_book` VALUES (13, 'Spring MVC+ MyBatis快速开发与项目实战', '黄文毅', 13, 2);
INSERT INTO `test_book` VALUES (14, '深入浅出Spring Boot 2.x', 'JoshLong', 14, 3);
INSERT INTO `test_book` VALUES (15, 'SpringBoot2精髓', '李家智', 15, 2);
INSERT INTO `test_book` VALUES (16, '云原生Java-Spring Boot、Spring Cloud与Cloud Foundry弹性系统设计', 'JoshLong', 16, 2);
INSERT INTO `test_book` VALUES (17, 'SpringBoot2企业应用实战', '疯狂软件', 17, 3);
INSERT INTO `test_book` VALUES (18, 'Spring Boot 2+Thymeleaf企业应用实战', '杨恩雄', 18, 2);
INSERT INTO `test_book` VALUES (19, '微服务架构基础（Spring Boot+Spring Cloud+Docker）', '黑马程序员', 19, 2);
INSERT INTO `test_book` VALUES (20, 'JavaEE开发的颠覆者 Spring Boot实战', '汪云飞', 20, 4);
INSERT INTO `test_book` VALUES (21, 'MyBatis从入门到精通', '刘增辉', 21, 1);
INSERT INTO `test_book` VALUES (22, 'Shiro教程', '张开涛', 22, 1);
INSERT INTO `test_book` VALUES (23, '走出软件作坊', '阿朱', 23, 2);
INSERT INTO `test_book` VALUES (24, '程序员的自我修养', '俞甲子', 24, 3);
INSERT INTO `test_book` VALUES (25, 'Head First Java', 'Kathy Sierra', 25, 1);
INSERT INTO `test_book` VALUES (26, 'Java 核心技术：卷1 基础知识', 'Cay S. Horstmann', 26, 2);
INSERT INTO `test_book` VALUES (27, 'Java 编程思想', 'Bruce Eckel', 27, 2);
INSERT INTO `test_book` VALUES (28, 'Java开发实战经典', '李兴华', 28, 3);
INSERT INTO `test_book` VALUES (29, 'Effective Java', 'Joshua Bloch', 29, 4);
INSERT INTO `test_book` VALUES (30, 'Spring揭秘', '王福强', 30, 2);
INSERT INTO `test_book` VALUES (31, '分布式 Java 应用：基础与实践', '林昊', 31, 1);
INSERT INTO `test_book` VALUES (32, 'http权威指南', 'David Gourley', 32, 2);
INSERT INTO `test_book` VALUES (33, '深入理解 Java 虚拟机', '周志明', 33, 1);
INSERT INTO `test_book` VALUES (34, '深入理解 Java 虚拟机', '周志明', 34, 1);
INSERT INTO `test_book` VALUES (35, 'Java编程思想(第4版)', 'Thinking in Java', 35, 2);
INSERT INTO `test_book` VALUES (36, 'JAVA 2核心技术 卷II：高级特性(原书第7版)', 'CayS', 36, 2);
INSERT INTO `test_book` VALUES (38, '西游记', '吴承恩', 37, 10);

-- ----------------------------
-- Table structure for test_brrow
-- ----------------------------
DROP TABLE IF EXISTS `test_brrow`;
CREATE TABLE `test_brrow`  (
  `book_id` bigint(0) NULL DEFAULT NULL,
  `stu_id` bigint(0) NULL DEFAULT NULL,
  `borrow_date` datetime(0) NULL DEFAULT NULL,
  INDEX `boid_sid`(`stu_id`) USING BTREE,
  INDEX `boid_bid`(`book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_brrow
-- ----------------------------
INSERT INTO `test_brrow` VALUES (6, 2018124, '2021-01-09 07:43:57');
INSERT INTO `test_brrow` VALUES (4, 2018137, '2021-01-10 02:02:34');
INSERT INTO `test_brrow` VALUES (4, 2018124, '2021-01-10 02:08:20');

-- ----------------------------
-- Table structure for test_comment
-- ----------------------------
DROP TABLE IF EXISTS `test_comment`;
CREATE TABLE `test_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `parent_comment_id` bigint(0) NULL DEFAULT NULL,
  `admin_comment` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_comment
-- ----------------------------
INSERT INTO `test_comment` VALUES (40, '何锦涛', '管理员能不能加一本西游记的书啊', '2021-01-10 09:13:48', -1, 0);
INSERT INTO `test_comment` VALUES (46, '李瑞达', '管理员计算机组成与设计  硬件软件接口 第5版什么时候有货啊', '2021-01-10 09:31:30', -1, 0);
INSERT INTO `test_comment` VALUES (47, '李琦', '大赞管理员', '2021-01-10 09:31:52', -1, 0);
INSERT INTO `test_comment` VALUES (55, '李琦', '什么时候出新功能啊', '2021-01-10 09:33:46', 47, 0);
INSERT INTO `test_comment` VALUES (56, '李晨曦', '还书怎么还啊', '2021-01-10 09:34:23', -1, 0);
INSERT INTO `test_comment` VALUES (70, '甄文鲁', '点导航栏上面啊', '2021-01-10 09:35:27', 56, 0);
INSERT INTO `test_comment` VALUES (71, '张天昊', '界面好丑，什么时候能优化下啊', '2021-01-10 09:40:09', -1, 0);
INSERT INTO `test_comment` VALUES (72, '李子柒', '反应慢死了，维护一下服务器好不好', '2021-01-10 09:40:45', -1, 0);
INSERT INTO `test_comment` VALUES (74, '管理员', '之后会加的', '2021-01-10 09:43:13', 40, 1);
INSERT INTO `test_comment` VALUES (75, '管理员', '下周进货', '2021-01-10 09:44:59', 46, 1);
INSERT INTO `test_comment` VALUES (76, '管理员', '有时间会再做', '2021-01-10 09:45:53', 55, 1);
INSERT INTO `test_comment` VALUES (77, '管理员', '导航栏---》我的借阅---》还书', '2021-01-10 09:47:13', 56, 1);
INSERT INTO `test_comment` VALUES (79, '管理员', '有心无力T . T', '2021-01-10 09:56:58', 71, 1);
INSERT INTO `test_comment` VALUES (81, '管理员', '下次一定维护', '2021-01-10 09:57:30', 72, 1);
INSERT INTO `test_comment` VALUES (82, '管理员', '欢迎大家光临本系统', '2021-01-10 10:00:08', -1, 1);
INSERT INTO `test_comment` VALUES (83, '何锦涛', '能增加基本世界名著吗', '2021-01-10 10:47:53', -1, 0);
INSERT INTO `test_comment` VALUES (84, '何锦涛', '再来一本红楼梦就更好了', '2021-01-10 10:48:06', 83, 0);
INSERT INTO `test_comment` VALUES (85, '管理员', '下次进货四大名著', '2021-01-10 10:48:36', 84, 1);

-- ----------------------------
-- Table structure for test_student
-- ----------------------------
DROP TABLE IF EXISTS `test_student`;
CREATE TABLE `test_student`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stuname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别1男2女3未知',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stuid` int(0) NULL DEFAULT NULL,
  `classes` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_student
-- ----------------------------
INSERT INTO `test_student` VALUES (1, 'admin', '管理员', 1, '123456', 2018001, '人工智能', 'admin');
INSERT INTO `test_student` VALUES (2, '2018124', '何锦涛', 1, '123456', 2018124, '18计算机一班', 'stu');
INSERT INTO `test_student` VALUES (3, '2018125', '郭明行', 1, '123456', 2018125, 'CFA一班', 'stu');
INSERT INTO `test_student` VALUES (4, '2018126', '宋智乾', 1, '123456', 2018126, '环境一班', 'stu');
INSERT INTO `test_student` VALUES (5, '2018127', '李嘉怡', 0, '123456', 2018127, '电子一班', 'stu');
INSERT INTO `test_student` VALUES (6, '2018128', '李子柒', 0, '123456', 2018128, '土木一班', 'stu');
INSERT INTO `test_student` VALUES (7, '2018129', '李瑞达', 1, '123456', 2018129, '人工智能一班', 'stu');
INSERT INTO `test_student` VALUES (8, '2018130', '张天昊', 1, '123456', 2018130, '会计一班', 'stu');
INSERT INTO `test_student` VALUES (9, '2018131', '李琦', 0, '123456', 2018131, '金融二班', 'stu');
INSERT INTO `test_student` VALUES (10, '2018132', '孙宝旭', 1, '123456', 2018132, '计算机一班', 'stu');
INSERT INTO `test_student` VALUES (11, '2018133', '郭姿彤', 0, '123456', 2018133, '软件一班', 'stu');
INSERT INTO `test_student` VALUES (12, '2018134', '甄文鲁', 1, '123456', 2018134, '大数据二班', 'stu');
INSERT INTO `test_student` VALUES (17, '2018135', '张昕童', 0, '123456', 2018135, '计算机二班', 'stu');
INSERT INTO `test_student` VALUES (25, '2018136', '何锦涛2', 1, '123456', 2018136, '18计算机一班', 'stu');

SET FOREIGN_KEY_CHECKS = 1;
