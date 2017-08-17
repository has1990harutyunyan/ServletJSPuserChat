/*
SQLyog Ultimate v11.5 (32 bit)
MySQL - 5.7.18-log : Database - userchat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`userchat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `userchat`;

/*Table structure for table `friend_list` */

DROP TABLE IF EXISTS `friend_list`;

CREATE TABLE `friend_list` (
  `user_id` int(11) unsigned NOT NULL,
  `friend_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `friend_list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `friend_list_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `friend_list` */

insert  into `friend_list`(`user_id`,`friend_id`) values (1,2),(4,2),(5,2),(9,3),(3,4),(9,4),(1,6);

/*Table structure for table `messages` */

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `from_id` int(11) unsigned NOT NULL,
  `to_id` int(11) unsigned NOT NULL,
  `message` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `user` (`id`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `messages` */

insert  into `messages`(`id`,`from_id`,`to_id`,`message`,`date`) values (3,1,4,'123456789','2017-07-02 00:37:28'),(4,1,4,' love you','2017-07-02 00:49:17');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `age` int(3) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`age`,`email`,`password`,`profile_pic`) values (1,'User','User',27,'User','123',NULL),(2,'User','User',16,'some1','369',NULL),(3,'User','User',23,'some2','tik',NULL),(4,'User','User',50,'some3','iva',NULL),(5,'User','User',28,'some4','shant','file_1498995979722do it yourself summer school.jpg'),(6,'User','User',48,'some5','aida','file_1499005126153mini.jpg'),(7,'User','User',1,'some6','888','file_1499010785981'),(8,'User','User',3,'some7','vvvvvvvv','file_15029795286410029902ab97d03c9c4cd4262ea7476b7.jpg'),(9,'User','User',5,'some8','mmm','file_15029972336411 (1).jpg'),(10,'User','User',4,'some9','gjg','file_1502999596926');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
