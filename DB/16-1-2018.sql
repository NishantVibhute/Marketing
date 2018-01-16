/*
SQLyog Community v12.3.2 (64 bit)
MySQL - 5.5.33 : Database - marketing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `joining_details` */

DROP TABLE IF EXISTS `joining_details`;

CREATE TABLE `joining_details` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `payment_mode_id` int(11) NOT NULL,
  `user_status` int(11) NOT NULL,
  `scheme_id` int(11) NOT NULL,
  `member_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_joining_details` (`payment_mode_id`),
  KEY `FK_joining_details_1` (`user_status`),
  KEY `FK_joining_details_2` (`scheme_id`),
  KEY `FK_joining_details_3` (`user_id`),
  KEY `FK_joining_details_4` (`member_type`),
  CONSTRAINT `FK_joining_details` FOREIGN KEY (`payment_mode_id`) REFERENCES `m_payment_mode` (`id`),
  CONSTRAINT `FK_joining_details_1` FOREIGN KEY (`user_status`) REFERENCES `m_status` (`id`),
  CONSTRAINT `FK_joining_details_2` FOREIGN KEY (`scheme_id`) REFERENCES `scheme_details` (`id`),
  CONSTRAINT `FK_joining_details_3` FOREIGN KEY (`user_id`) REFERENCES `userdetails` (`id`),
  CONSTRAINT `FK_joining_details_4` FOREIGN KEY (`member_type`) REFERENCES `m_member_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `joining_details` */

insert  into `joining_details`(`id`,`user_id`,`payment_mode_id`,`user_status`,`scheme_id`,`member_type`) values 
(1,1,1,1,1,1);

/*Table structure for table `m_member_type` */

DROP TABLE IF EXISTS `m_member_type`;

CREATE TABLE `m_member_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `m_member_type` */

insert  into `m_member_type`(`id`,`value`) values 
(1,'Physical'),
(2,'Virtual');

/*Table structure for table `m_payment_mode` */

DROP TABLE IF EXISTS `m_payment_mode`;

CREATE TABLE `m_payment_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `m_payment_mode` */

insert  into `m_payment_mode`(`id`,`value`) values 
(1,'by Cash'),
(2,'by Cheque'),
(3,'by NetBanking');

/*Table structure for table `m_status` */

DROP TABLE IF EXISTS `m_status`;

CREATE TABLE `m_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `m_status` */

insert  into `m_status`(`id`,`value`) values 
(1,'Pending'),
(2,'Confirmed'),
(3,'Rejected');

/*Table structure for table `payment_details` */

DROP TABLE IF EXISTS `payment_details`;

CREATE TABLE `payment_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `joining_id` int(11) NOT NULL,
  `payment_type` int(11) DEFAULT NULL,
  `cheque_no` varchar(50) DEFAULT NULL,
  `cheque_amt` float DEFAULT NULL,
  `cheque_date` date DEFAULT NULL,
  `cheque_bank_name` varchar(255) DEFAULT NULL,
  `utr_no` varchar(255) DEFAULT NULL,
  `payment_status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_payment_details` (`joining_id`),
  KEY `fk_pay_status` (`payment_status`),
  CONSTRAINT `fk_pay_status` FOREIGN KEY (`payment_status`) REFERENCES `m_status` (`id`),
  CONSTRAINT `FK_payment_details` FOREIGN KEY (`joining_id`) REFERENCES `joining_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `payment_details` */

insert  into `payment_details`(`id`,`joining_id`,`payment_type`,`cheque_no`,`cheque_amt`,`cheque_date`,`cheque_bank_name`,`utr_no`,`payment_status`) values 
(1,1,NULL,NULL,NULL,NULL,NULL,NULL,1);

/*Table structure for table `scheme_details` */

DROP TABLE IF EXISTS `scheme_details`;

CREATE TABLE `scheme_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scheme_name` varchar(500) NOT NULL,
  `scheme_description` text NOT NULL,
  `scheme_start_date` date NOT NULL,
  `is_scheme_active` int(11) NOT NULL DEFAULT '1',
  `member_perc` float NOT NULL,
  `company_perc` float NOT NULL,
  `inserted_date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` float NOT NULL,
  `is_closed` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `scheme_details` */

insert  into `scheme_details`(`id`,`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`inserted_date_time`,`amount`,`is_closed`) values 
(1,'TestScheme','Test','2018-02-10',1,30,70,'2018-01-16 14:44:46',100,0);

/*Table structure for table `userdetails` */

DROP TABLE IF EXISTS `userdetails`;

CREATE TABLE `userdetails` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) NOT NULL,
  `email_id` varchar(250) DEFAULT NULL,
  `mobile_num` varchar(50) DEFAULT NULL,
  `address` varchar(1024) DEFAULT NULL,
  `pan_card_no` varchar(50) DEFAULT NULL,
  `addhar_no` varchar(50) DEFAULT NULL,
  `bank_name` varchar(250) DEFAULT NULL,
  `ifsc_code` varchar(250) DEFAULT NULL,
  `branch_name` varchar(250) DEFAULT NULL,
  `account_no` varchar(250) DEFAULT NULL,
  `user_password_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_userdetails` (`user_password_id`),
  CONSTRAINT `FK_userdetails` FOREIGN KEY (`user_password_id`) REFERENCES `userpassworddetails` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `userdetails` */

insert  into `userdetails`(`id`,`first_name`,`middle_name`,`last_name`,`email_id`,`mobile_num`,`address`,`pan_card_no`,`addhar_no`,`bank_name`,`ifsc_code`,`branch_name`,`account_no`,`user_password_id`) values 
(1,'Test1FirstName','Tes1MIddleName','Test1LastName','abc@gmail.com','9869849703','Test1Address','AKDPV2316F','0880-8089-9859-8878','Maharastra bank','7874587545','MUMBAI','457896554',1);

/*Table structure for table `userpassworddetails` */

DROP TABLE IF EXISTS `userpassworddetails`;

CREATE TABLE `userpassworddetails` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `inserted_date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `userpassworddetails` */

insert  into `userpassworddetails`(`id`,`email_id`,`password`,`inserted_date_time`) values 
(1,'abc@gmail.com','root','2018-01-14 14:21:53'),
(2,'xyz@gmail.com','root','2018-01-14 14:22:33');

/* Procedure structure for procedure `createScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `createScheme` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `createScheme`(`scheme_name_in` varchar(500),`scheme_description_in` text,
    `scheme_start_date_in` date,`is_scheme_active_in` int,
    `member_perc_in` float,`company_perc_in` float,`amount_in` float)
BEGIN
    
    insert into `scheme_details`(`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`amount`)
values(`scheme_name_in`,`scheme_description_in`,`scheme_start_date_in`,`is_scheme_active_in`,`member_perc_in`,`company_perc_in`,`amount_in`);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `createUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `createUser` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `createUser`(`first_name_in` varchar(50),
    `middle_name_in` varchar(50),
    `last_name_in` varchar(50),
    `email_id_in` varchar(250),
    `mobile_num_in` varchar(50),
    `address_in` varchar(1024),
    `pan_card_no_in` varchar(50),
    `addhar_no_in` varchar(50),
    `bank_name_in` varchar(250),
    ifsc_code_in varchar(250),
    `branch_name_in` varchar(250),
    `account_no_in` varchar(250) )
BEGIN
	DECLARE userId INT;
		
		SELECT id INTO userId FROM `userpassworddetails` WHERE `email_id` = email_id_in;
	
    
    insert into `userdetails`(`first_name`,
				`middle_name`,
				`last_name`,
				`email_id`,
				`mobile_num`,
				`address`,
				`pan_card_no`,
				`addhar_no`,
				`bank_name`,
				`ifsc_code`,
				`branch_name`,
				`account_no`,
				`user_password_id`)
	values(`first_name_in`,
				`middle_name_in`,
				`last_name_in`,
				`email_id_in`,
				`mobile_num_in`,
				`address_in`,
				`pan_card_no_in`,
				`addhar_no_in`,
				`bank_name_in`,
				`ifsc_code_in`,
				`branch_name_in`,
				`account_no_in`,
				userId);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `editScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `editScheme` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `editScheme`(`scheme_name_in` varchar(500),`scheme_description_in` text,
    `scheme_start_date_in` date,`is_scheme_active_in` int,
    `member_perc_in` float,`company_perc_in` float,`amount_in` float,isSchemeClosed_in int,id_in int)
BEGIN
    
    update  `scheme_details`
    set `scheme_name` = scheme_name_in,
    scheme_description=scheme_description_in,
    scheme_start_date=scheme_start_date_in,
    is_scheme_active = is_scheme_active_in,
    member_perc = member_perc_in,
    company_perc = company_perc_in,
    amount = amount_in,
    is_closed=isSchemeClosed_in
where id = id_in;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `getCountUserOfScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `getCountUserOfScheme` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `getCountUserOfScheme`(scheme_id_in int, user_status_id int)
BEGIN
	
	SELECT COUNT(id)
FROM `joining_details`
WHERE user_status = user_status_id AND scheme_id=scheme_id_in;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getInprogressScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `getInprogressScheme` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `getInprogressScheme`()
BEGIN
	select id,scheme_name
from `scheme_details`
where is_closed <> 1 ;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getListOFCustomerOnJoinStatus` */

/*!50003 DROP PROCEDURE IF EXISTS  `getListOFCustomerOnJoinStatus` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `getListOFCustomerOnJoinStatus`(scheme_id_in int,status_in int)
BEGIN
	
	select ud.id,concat(ud.`first_name`,' ',ud.`middle_name`,' ',ud.`last_name`),jd.`member_type`
from `userdetails` ud join `joining_details` jd
on ud.id = jd.user_id
where scheme_id = scheme_id_in and user_status = status_in;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getSchemeDetail` */

/*!50003 DROP PROCEDURE IF EXISTS  `getSchemeDetail` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `getSchemeDetail`(id_in int)
BEGIN

		SELECT `id`,`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`amount`,`is_closed`,
IF(CURDATE() >= scheme_start_date, 1, 0) as started
		FROM `scheme_details`
		where id =id_in ;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getSchemeList` */

/*!50003 DROP PROCEDURE IF EXISTS  `getSchemeList` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `getSchemeList`()
BEGIN

		select `id`,`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`amount`,`is_closed`
		from `scheme_details`;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `joinScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `joinScheme` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `joinScheme`(`user_id_in` int,`payment_mode_id_in` int,`user_status_in` int,`scheme_id_in` int,`member_type_in` int)
BEGIN
	DECLARE joinId INT;

		insert into `joining_details`(`user_id`,`payment_mode_id`,`user_status`,`scheme_id`,`member_type`)
		values(`user_id_in`,`payment_mode_id_in`,`user_status_in`,`scheme_id_in`,`member_type_in`);
		
		SELECT LAST_INSERT_ID() into joinId;

if joinId <>0 then	
		
		insert into `payment_details`(`joining_id`)
		values(joinId);
		end if;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `signup` */

/*!50003 DROP PROCEDURE IF EXISTS  `signup` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `signup`(email_id_in varchar(255),password_in varchar(255))
BEGIN
    
    insert into `userpassworddetails`(`email_id`,`password`)
    values(email_id_in,password_in);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `validate_user` */

/*!50003 DROP PROCEDURE IF EXISTS  `validate_user` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `validate_user`(email_id_in varchar(255),pass varchar(255))
BEGIN
    
    select up.email_id,ud.`id`,ud.`first_name`,ud.`middle_name`,
    ud.`last_name`,ud.`mobile_num`,ud.`address`,
    ud.`pan_card_no`,ud.`addhar_no`,ud.`bank_name`,
    ud.`ifsc_code`,ud.`branch_name`,ud.`account_no`
    from `userpassworddetails` up left join `userdetails` ud
    on ud.`user_password_id` = up.id
    where up.`email_id` = email_id_in and up.`password` = pass;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
