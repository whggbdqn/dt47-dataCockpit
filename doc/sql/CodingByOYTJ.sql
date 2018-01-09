/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.25-log : Database - datacockpit
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`datacockpit` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `datacockpit`;

/*Table structure for table `analysistasks` */

DROP TABLE IF EXISTS `analysistasks`;

CREATE TABLE `analysistasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `starttime` datetime DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '企业编号',
  `did` int(11) DEFAULT NULL COMMENT '关联关系编号',
  `taskstatus` int(11) DEFAULT NULL COMMENT '已添加:0,进行中:1，完成:2,失败:-1',
  `rule` varchar(50) DEFAULT NULL,
  `feedback` varchar(1000) DEFAULT NULL,
  `arithmeticid` int(11) DEFAULT NULL COMMENT '算法ID',
  PRIMARY KEY (`id`),
  KEY `did` (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `analysistasks` */

/*Table structure for table `companyinfo` */

DROP TABLE IF EXISTS `companyinfo`;

CREATE TABLE `companyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `corpName` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态    禁：0,不禁1',
  `approval` int(11) DEFAULT NULL COMMENT '审批状态  拒：0，过：1',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `companyinfo` */

insert  into `companyinfo`(`id`,`corpName`,`phone`,`name`,`job`,`password`,`email`,`state`,`approval`,`role_id`) values (1,'香港恒生','1347891102','马人才','总裁','123456','123@emailcom',1,0,1),(2,'恒源祥','1356789110','李冠荣','总经理','123456','123@email.com',1,1,1),(3,'香港金泰','1356689110','王爱玲','总经理','123456','123@email.com',1,1,1),(4,'伊利乳业','1356678110','牛根生','总裁','123456','123@email.com',1,1,1),(5,'百事通','1356678119','李小兰','总裁','123456','123@email.com',1,1,1),(6,'可乐','1356678112','戴维斯','总裁','123456',NULL,NULL,NULL,1),(7,'百事','32513242121',NULL,NULL,NULL,NULL,NULL,NULL,1),(11,'腾讯','1311111111','马化腾','boss','123456','123@email.com',1,1,1),(12,'联想','1311111112',NULL,NULL,'123456','123@email.com',1,1,1),(13,'华为','1311111333','','boss','123456','123@email.com',1,1,1);

/*Table structure for table `datarelation` */

DROP TABLE IF EXISTS `datarelation`;

CREATE TABLE `datarelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '企业ID',
  `tid1` int(11) DEFAULT NULL COMMENT '关联表A字段',
  `tid2` int(11) DEFAULT NULL COMMENT '关联表B字段',
  `col1` int(11) DEFAULT NULL,
  `col2` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '关联关系状态 禁：0,不禁1',
  PRIMARY KEY (`id`),
  KEY `datarelation_ibfk_1` (`tid1`),
  KEY `datarelation_ibfk_2` (`tid2`),
  KEY `datarelation_col_1` (`col1`),
  KEY `datarelation_col_2` (`col2`),
  KEY `datarelation_com_1` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `datarelation` */

insert  into `datarelation`(`id`,`name`,`cid`,`tid1`,`tid2`,`col1`,`col2`,`state`) values (1,'销售到访关联表',NULL,1,2,4,4,1),(2,'销售中介关联表',NULL,1,3,2,2,1),(6,'销售认筹关联',NULL,1,4,5,5,1),(7,'销售与认筹关联表',NULL,1,4,4,5,1),(8,'a表与b表',NULL,2,1,4,5,1);

/*Table structure for table `inform` */

DROP TABLE IF EXISTS `inform`;

CREATE TABLE `inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `details` varchar(1000) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `inform` */

insert  into `inform`(`id`,`title`,`publishDate`,`details`,`publisher`) values (1,'通知公告','2017-08-29 20:17:11','<p>但是否会对各环节</p>\r\n','333'),(2,'会议纪要','2017-08-25 20:24:26','<p>naskdnfaslif &nbsp; dsfdsgf</p>\r\n','444'),(3,'会议通知','2017-08-25 08:38:12','大家好',NULL),(4,'系统升级','2017-08-29 08:41:36','升级维护',NULL),(5,'活动通知','2017-08-28 08:41:51','维护',NULL),(6,'系统维护','2017-08-30 12:40:09','出新活动了',NULL),(7,'毕业通知','2017-08-30 15:16:46','<p>后天毕业典礼</p>\r\n','汪宝宝'),(8,'11','2017-12-28 10:24:40','<p>名</p>\r\n','11'),(9,'下排名','2017-12-28 10:24:58','<p>小明</p>\r\n','小明'),(10,'小明','2017-12-28 10:25:12','<p>小名</p>\r\n','小明'),(11,'小名','2017-12-28 10:25:29','<p>小名</p>\r\n','小名'),(12,'123','2017-12-28 10:50:03','<p>123</p>\r\n','123'),(13,'456','2017-12-28 14:10:06','<p>8888888888888888888888888888888888888888888888888888888888</p>\r\n','465');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `per_name` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`per_name`,`role_id`) values (1,'userPermission',1),(2,'superAdmin',2),(3,'generalAdmin',3);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`role_name`) values (1,'company_user'),(2,'superAdmin'),(3,'admin');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`rid`,`pid`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,3,1),(9,3,2),(10,4,1);

/*Table structure for table `tablecolumninfo` */

DROP TABLE IF EXISTS `tablecolumninfo`;

CREATE TABLE `tablecolumninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) DEFAULT NULL,
  `columnName` varchar(50) DEFAULT NULL,
  `columnType` varchar(50) DEFAULT NULL,
  `physicalColumnName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tid` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tablecolumninfo` */

/*Table structure for table `tableinfo` */

DROP TABLE IF EXISTS `tableinfo`;

CREATE TABLE `tableinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `physicalTableName` varchar(200) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '企业ID',
  `state` int(11) DEFAULT NULL COMMENT '0禁用，1启用',
  `showtype` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `tableinfo` */

insert  into `tableinfo`(`id`,`name`,`updateTime`,`physicalTableName`,`cid`,`state`,`showtype`) values (1,'销售数据表','2017-08-30 10:34:31',NULL,1,NULL,'0'),(2,'到访数据表','2017-08-30 03:22:07',NULL,1,NULL,'1'),(9,'认筹表','2017-12-28 11:15:21',NULL,1,NULL,'0'),(10,'经费','2017-12-28 02:22:50',NULL,1,NULL,'0'),(18,'付家东','2017-12-28 09:26:44',NULL,1,NULL,'0');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL COMMENT '角色ID',
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`rid`,`uid`) values (1,1,1),(2,2,2),(3,3,3),(4,4,4);

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态    禁：0,不禁1',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `approval` varchar(10) DEFAULT NULL COMMENT '管理员权限开关',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`id`,`user_name`,`job`,`password`,`phone`,`email`,`state`,`role_id`,`approval`) values (1,'Bill','Manager','123456','123',NULL,1,3,'1'),(2,'Lily','Secratry','123456','234',NULL,1,3,'1'),(3,'Bob','Aassistant','123456','345',NULL,1,3,'1'),(4,'Jack','Customer','123456','567',NULL,1,3,'1'),(5,'superAdmin','SuperAdmin','superAdmin','admin',NULL,1,2,'1');

/*Table structure for table `weidulie` */

DROP TABLE IF EXISTS `weidulie`;

CREATE TABLE `weidulie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lie_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `weidulie` */

insert  into `weidulie`(`id`,`lie_name`) values (1,'项目名称'),(2,'所在区域'),(3,'日期'),(4,'到访人数'),(5,'认筹人数');

/*Table structure for table `xiaoshoushujubiao` */

DROP TABLE IF EXISTS `xiaoshoushujubiao`;

CREATE TABLE `xiaoshoushujubiao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xiangmumingcheng` varchar(50) DEFAULT NULL,
  `suozaiquyu` varchar(40) DEFAULT NULL,
  `times` date DEFAULT NULL,
  `daofangrenshu` int(10) DEFAULT NULL,
  `renchourenshu` int(10) DEFAULT NULL,
  `shows` int(2) DEFAULT NULL,
  `tbName` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `xiaoshoushujubiao` */

insert  into `xiaoshoushujubiao`(`id`,`xiangmumingcheng`,`suozaiquyu`,`times`,`daofangrenshu`,`renchourenshu`,`shows`,`tbName`) values (1,'a','b','2018-01-01',10,2,1,NULL),(2,'aa','bb','2018-01-02',120,20,0,NULL),(3,'a','bb','2018-01-01',10,1,1,'xiaoshoushujubiao'),(4,'a','bb','2018-01-01',10,1,1,'xiaoshoushujubiao'),(5,'a','bb','2018-01-01',10,1,1,'xiaoshoushujubiao'),(6,'a','bb','2018-01-02',10,1,1,'xiaoshoushujubiao'),(7,'a','bb','2018-01-03',10,1,1,'xiaoshoushujubiao'),(8,'a','bb','2018-01-04',10,1,1,'xiaoshoushujubiao'),(9,'a','bb','2018-01-05',10,1,1,'xiaoshoushujubiao'),(10,'a','bb','2018-01-06',10,1,1,'xiaoshoushujubiao'),(11,'a','bb','2018-01-07',10,1,1,'xiaoshoushujubiao'),(12,'a','bb','2018-01-08',10,1,1,'xiaoshoushujubiao'),(13,'a','bb','2018-01-01',10,1,1,'xiaoshoushujubiao'),(14,'a','bb','2018-01-01',10,1,1,'xiaoshoushujubiao'),(15,'a','bb','2018-01-01',10,1,1,'xiaoshoushujubiao'),(16,'a','bb','2018-01-02',10,1,1,'xiaoshoushujubiao'),(17,'a','bb','2018-01-03',10,1,1,'xiaoshoushujubiao'),(18,'a','bb','2018-01-04',10,1,1,'xiaoshoushujubiao'),(19,'a','bb','2018-01-05',10,1,1,'xiaoshoushujubiao'),(20,'a','bb','2018-01-06',10,1,1,'xiaoshoushujubiao'),(21,'a','bb','2018-01-07',10,1,1,'xiaoshoushujubiao'),(22,'a','bb','2018-01-08',10,1,1,'xiaoshoushujubiao'),(23,'a','bb','2018-01-01',10,1,NULL,'xiaoshoushujubiao'),(24,'a','bb','2018-01-01',10,1,NULL,'xiaoshoushujubiao'),(25,'a','bb','2018-01-01',10,1,NULL,'xiaoshoushujubiao'),(26,'a','bb','2018-01-02',10,1,NULL,'xiaoshoushujubiao'),(27,'a','bb','2018-01-03',10,1,NULL,'xiaoshoushujubiao'),(28,'a','bb','2018-01-04',10,1,NULL,'xiaoshoushujubiao'),(29,'a','bb','2018-01-05',10,1,NULL,'xiaoshoushujubiao'),(30,'a','bb','2018-01-06',10,1,NULL,'xiaoshoushujubiao'),(31,'a','bb','2018-01-07',10,1,NULL,'xiaoshoushujubiao'),(32,'a','bb','2018-01-08',10,1,NULL,'xiaoshoushujubiao'),(33,'a','bb','2018-01-01',10,1,NULL,'xiaoshoushujubiao'),(34,'a','bb','2018-01-01',10,1,NULL,'xiaoshoushujubiao'),(35,'a','bb','2018-01-01',10,1,NULL,'xiaoshoushujubiao'),(36,'a','bb','2018-01-02',10,1,NULL,'xiaoshoushujubiao'),(37,'a','bb','2018-01-03',10,1,NULL,'xiaoshoushujubiao'),(38,'a','bb','2018-01-04',10,1,NULL,'xiaoshoushujubiao'),(39,'a','bb','2018-01-05',10,1,NULL,'xiaoshoushujubiao'),(40,'a','bb','2018-01-06',10,1,NULL,'xiaoshoushujubiao'),(41,'a','bb','2018-01-07',10,1,NULL,'xiaoshoushujubiao'),(42,'a','bb','2018-01-08',10,1,NULL,'xiaoshoushujubiao');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

/*!50001 DROP VIEW IF EXISTS `user` */;
/*!50001 DROP TABLE IF EXISTS `user` */;

/*!50001 CREATE TABLE  `user`(
 `realName` varchar(50) NULL ,
 `userName` varchar(50) NULL ,
 `password` varchar(50) NULL ,
 `job` varchar(50) NULL ,
 `email` varchar(50) NULL ,
 `state` int(11) NULL ,
 `role_id` int(11) NOT NULL  default '0' ,
 `approval` varchar(11) NULL 
)*/;

/*View structure for view user */

/*!50001 DROP TABLE IF EXISTS `user` */;
/*!50001 DROP VIEW IF EXISTS `user` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user` AS select `companyinfo`.`corpName` AS `realName`,`companyinfo`.`phone` AS `userName`,`companyinfo`.`password` AS `password`,`companyinfo`.`job` AS `job`,`companyinfo`.`email` AS `email`,`companyinfo`.`state` AS `state`,`companyinfo`.`role_id` AS `role_id`,`companyinfo`.`approval` AS `approval` from `companyinfo` union select `userinfo`.`user_name` AS `realName`,`userinfo`.`phone` AS `userName`,`userinfo`.`password` AS `password`,`userinfo`.`job` AS `job`,`userinfo`.`email` AS `email`,`userinfo`.`state` AS `state`,`userinfo`.`role_id` AS `role_id`,`userinfo`.`approval` AS `approval` from `userinfo` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
