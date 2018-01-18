/*
SQLyog Community v8.82 
MySQL - 5.5.11 : Database - marketing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`marketing` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `marketing`;

/*Table structure for table `joining_details` */

DROP TABLE IF EXISTS `joining_details`;

CREATE TABLE `joining_details` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `payment_mode_id` int(11) NOT NULL,
  `user_status` int(11) NOT NULL,
  `scheme_id` int(11) NOT NULL,
  `member_type` int(11) NOT NULL,
  `join_date` date DEFAULT NULL,
  `request_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `balance` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_joining_details` (`payment_mode_id`),
  KEY `FK_joining_details_1` (`user_status`),
  KEY `FK_joining_details_2` (`scheme_id`),
  KEY `FK_joining_details_3` (`user_id`),
  KEY `FK_joining_details_4` (`member_type`),
  CONSTRAINT `FK_joining_details` FOREIGN KEY (`payment_mode_id`) REFERENCES `m_payment_mode` (`id`),
  CONSTRAINT `FK_joining_details_1` FOREIGN KEY (`user_status`) REFERENCES `m_status` (`id`),
  CONSTRAINT `FK_joining_details_2` FOREIGN KEY (`scheme_id`) REFERENCES `scheme_details` (`id`),
  CONSTRAINT `FK_joining_details_4` FOREIGN KEY (`member_type`) REFERENCES `m_member_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `joining_details` */

insert  into `joining_details`(`id`,`user_id`,`payment_mode_id`,`user_status`,`scheme_id`,`member_type`,`join_date`,`request_date`,`balance`) values (1,1,4,2,1,2,'2018-01-18','2018-01-18 23:46:57',1200),(2,4,1,2,1,1,'2018-01-18','2018-01-18 23:47:55',0),(3,3,1,2,1,1,'2018-01-18','2018-01-18 23:48:35',0),(4,2,1,2,1,1,'2018-01-18','2018-01-18 23:48:51',0),(5,1,1,1,1,1,NULL,'2018-01-18 23:49:39',0),(6,1,5,2,1,2,'2018-01-18','2018-01-18 23:51:31',0),(7,2,5,2,1,2,NULL,'2018-01-18 23:52:59',0),(8,3,5,2,1,2,NULL,'2018-01-18 23:53:05',0);

/*Table structure for table `m_member_type` */

DROP TABLE IF EXISTS `m_member_type`;

CREATE TABLE `m_member_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `m_member_type` */

insert  into `m_member_type`(`id`,`value`) values (1,'Physical'),(2,'Virtual');

/*Table structure for table `m_payment_mode` */

DROP TABLE IF EXISTS `m_payment_mode`;

CREATE TABLE `m_payment_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `m_payment_mode` */

insert  into `m_payment_mode`(`id`,`value`) values (1,'by Cash'),(2,'by Cheque'),(3,'by NetBanking'),(4,'by Company'),(5,'by Rejoining');

/*Table structure for table `m_status` */

DROP TABLE IF EXISTS `m_status`;

CREATE TABLE `m_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `m_status` */

insert  into `m_status`(`id`,`value`) values (1,'Pending'),(2,'Confirmed'),(3,'Rejected');

/*Table structure for table `payment_details` */

DROP TABLE IF EXISTS `payment_details`;

CREATE TABLE `payment_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `joining_id` int(11) NOT NULL,
  `payment_type` int(11) DEFAULT NULL,
  `cheque_no` varchar(50) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `cheque_date` varchar(50) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `utr_no` varchar(255) DEFAULT NULL,
  `payment_status` int(11) DEFAULT '1',
  `payment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_payment_details` (`joining_id`),
  KEY `fk_pay_status` (`payment_status`),
  CONSTRAINT `FK_payment_details` FOREIGN KEY (`joining_id`) REFERENCES `joining_details` (`id`),
  CONSTRAINT `fk_pay_status` FOREIGN KEY (`payment_status`) REFERENCES `m_status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `payment_details` */

insert  into `payment_details`(`id`,`joining_id`,`payment_type`,`cheque_no`,`amount`,`cheque_date`,`bank_name`,`utr_no`,`payment_status`,`payment_date`) values (1,2,1,'',1000,'','','',2,'2018-01-18 23:47:56'),(2,3,1,'',1000,'','','',2,'2018-01-18 23:48:35'),(3,4,1,'',1000,'','','',2,'2018-01-18 23:48:51'),(4,5,NULL,NULL,NULL,NULL,NULL,NULL,1,'2018-01-18 23:49:39'),(5,6,0,NULL,0,NULL,NULL,NULL,2,'2018-01-18 23:51:31'),(6,7,NULL,NULL,NULL,NULL,NULL,NULL,1,'2018-01-18 23:53:00'),(7,8,NULL,NULL,NULL,NULL,NULL,NULL,1,'2018-01-18 23:53:05');

/*Table structure for table `scheme_1` */

DROP TABLE IF EXISTS `scheme_1`;

CREATE TABLE `scheme_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_join_id` int(11) DEFAULT NULL,
  `child1_join_id` int(11) DEFAULT NULL,
  `child2_join_id` int(11) DEFAULT NULL,
  `child3_join_id` int(11) DEFAULT NULL,
  `join_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `scheme_1` */

insert  into `scheme_1`(`id`,`parent_join_id`,`child1_join_id`,`child2_join_id`,`child3_join_id`,`join_count`) values (1,1,2,3,4,3),(2,2,6,NULL,NULL,1),(3,3,NULL,NULL,NULL,0),(4,4,NULL,NULL,NULL,0),(5,6,NULL,NULL,NULL,0);

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
  `balance` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `scheme_details` */

insert  into `scheme_details`(`id`,`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`inserted_date_time`,`amount`,`is_closed`,`balance`) values (1,'scheme1','kdkdk','2018-01-19',1,40,60,'2018-01-18 23:46:57',1000,0,3000);

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
  `balance` float DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_userdetails` (`user_password_id`),
  CONSTRAINT `FK_userdetails` FOREIGN KEY (`user_password_id`) REFERENCES `userpassworddetails` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `userdetails` */

insert  into `userdetails`(`id`,`first_name`,`middle_name`,`last_name`,`email_id`,`mobile_num`,`address`,`pan_card_no`,`addhar_no`,`bank_name`,`ifsc_code`,`branch_name`,`account_no`,`user_password_id`,`balance`) values (1,'Test1FirstName','Tes1MIddleName','Test1LastName','abc@gmail.com','9869849703','Test1Address','AKDPV2316F','0880-8089-9859-8878','Maharastra bank','7874587545','MUMBAI','457896554',1,400),(2,'Nishant','S','Vibhute','nishantvibhute92@gmail.com','9856987456','GORAI Boraivali','AKDPV2316F','8898-8985-8985-9856','Mumbai','asdfdasf','Gorai','8978745544',3,0),(3,'Nish','S','Vibhute','nishantvibhute23@gmail.com','9856987456','GORAI Boraivali','AKDPV2316F','8898-8985-8985-9856','Mumbai','asdfdasf','Gorai','8978745544',4,0),(4,'N','S','Vibhute','nishantvibhute@gmail.com','9856987456','GORAI Boraivali','AKDPV2316F','8898-8985-8985-9856','Mumbai','asdfdasf','Gorai','8978745544',5,0);

/*Table structure for table `userpassworddetails` */

DROP TABLE IF EXISTS `userpassworddetails`;

CREATE TABLE `userpassworddetails` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `inserted_date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `userpassworddetails` */

insert  into `userpassworddetails`(`id`,`email_id`,`password`,`inserted_date_time`) values (1,'abc@gmail.com','root','2018-01-14 14:21:53'),(2,'xyz@gmail.com','root','2018-01-14 14:22:33'),(3,'nishantvibhute92@gmail.com','root','2018-01-18 11:21:31'),(4,'nishantvibhute23@gmail.com','root','2018-01-18 11:22:35'),(5,'nishantvibhute@gmail.com','root','2018-01-18 11:23:04');

/*Table structure for table `virtualuser` */

DROP TABLE IF EXISTS `virtualuser`;

CREATE TABLE `virtualuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `createdate` date NOT NULL,
  `scheme_id` int(11) NOT NULL,
  `balance` float DEFAULT '0',
  `createdFromId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_scheme` (`scheme_id`),
  CONSTRAINT `fk_scheme` FOREIGN KEY (`scheme_id`) REFERENCES `scheme_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `virtualuser` */

insert  into `virtualuser`(`id`,`user_name`,`createdate`,`scheme_id`,`balance`,`createdFromId`) values (1,'Virtual_1','2018-01-18',1,0,0),(2,'Virtual_2','2018-01-18',1,0,0),(3,'Virtual_3','2018-01-18',1,0,0);

/* Procedure structure for procedure `createScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `createScheme` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createScheme`(`scheme_name_in` varchar(500),`scheme_description_in` text,
    `scheme_start_date_in` date,`is_scheme_active_in` int,
    `member_perc_in` float,`company_perc_in` float,`amount_in` float)
BEGIN

DECLARE schemeId INT;    
DECLARE userId INT;
DECLARE joinId INT;
    
    insert into `scheme_details`(`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`amount`)
values(`scheme_name_in`,`scheme_description_in`,`scheme_start_date_in`,`is_scheme_active_in`,`member_perc_in`,`company_perc_in`,`amount_in`);

SELECT LAST_INSERT_ID() INTO schemeId;

IF schemeId <>0 THEN	
		
		INSERT INTO `virtualuser`(`createdate`,`scheme_id`)
		VALUES(CURDATE(),schemeId);
		
		SELECT LAST_INSERT_ID() INTO userId;
		
		Update virtualuser
		set user_name = Concat("Virtual_",userId)
		where id = userId;

		IF userId <>0 THEN
			
			insert into `joining_details`(`user_id`,`payment_mode_id`,`user_status`,`scheme_id`,`member_type`,`join_date`)
			values(userId,4,2,schemeId,2,CURDATE());
			
			SELECT LAST_INSERT_ID() INTO joinId;
			
				IF joinId <>0 THEN
			
					SET @SQL = CONCAT('CREATE TABLE Scheme_',schemeid,'(id int PRIMARY KEY AUTO_INCREMENT, parent_join_id int,child1_join_id int DEFAULT NULL,child2_join_id int DEFAULT NULL,child3_join_id int DEFAULT NULL,join_count int default 0)');

					PREPARE stmt FROM @SQL;
					EXECUTE stmt;
					
					
					
					SET @SQL1 = CONCAT('insert into Scheme_',schemeid,'(parent_join_id) values(',joinId,')');

					PREPARE stmt1 FROM @SQL1;
					EXECUTE stmt1;
					
			
				END IF;	
			

		END IF;		
		
		END IF;

    END */$$
DELIMITER ;

/* Procedure structure for procedure `createUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `createUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createUser`(`first_name_in` varchar(50),
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

/* Procedure structure for procedure `createVirtualUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `createVirtualUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createVirtualUser`(from_id int,scheme_id int)
BEGIN

		declare insertId int;
	
		insert into `virtualuser`(`createdate`,`createdFromId`,`scheme_id`)
		values (curdate(),from_id,scheme_id);

select last_insert_id() into insertId ;

		
		update `virtualuser`
		set user_name = Concat("Virtual_",insertId)
		where id = insertId;
		
		select insertId;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `deductPayment` */

/*!50003 DROP PROCEDURE IF EXISTS  `deductPayment` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `deductPayment`(member_type_in int, user_id_in int, scheme_id_in int,amount_in float)
BEGIN
	
		if member_type_in = 1 then
		
			update `userdetails`
			set `balance` = `balance` - amount_in
			where `id` = user_id_in;
			
		else
			update `scheme_details`
			set `balance` = `balance` - amount_in
			where id = scheme_id_in;
		
		end if;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `editScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `editScheme` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `editScheme`(`scheme_name_in` varchar(500),`scheme_description_in` text,
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

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `getCountUserOfScheme`(scheme_id_in int, user_status_id int)
BEGIN
	
	SELECT COUNT(id)
FROM `joining_details`
WHERE user_status = user_status_id AND scheme_id=scheme_id_in;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getInprogressScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `getInprogressScheme` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `getInprogressScheme`()
BEGIN
	select id,scheme_name
from `scheme_details`
where is_closed <> 1 ;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getListOFCustomerOnJoinStatus` */

/*!50003 DROP PROCEDURE IF EXISTS  `getListOFCustomerOnJoinStatus` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `getListOFCustomerOnJoinStatus`(scheme_id_in int,status_in int)
BEGIN

	
	SELECT jd.id,IF(jd.`member_type`= 1, 
				(SELECT CONCAT(ud.`first_name`,' ',ud.`middle_name`,' ',ud.`last_name`)
					FROM  `userdetails` 
					WHERE id = jd.user_id),
				(SELECT user_name
					FROM  virtualuser 
					WHERE id = jd.user_id)	
			),jd.`member_type`,jd.`payment_mode_id`
FROM `userdetails` ud JOIN `joining_details` jd
ON ud.id = jd.user_id
where scheme_id = scheme_id_in and user_status = status_in
order by jd.id;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getSchemeDetail` */

/*!50003 DROP PROCEDURE IF EXISTS  `getSchemeDetail` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `getSchemeDetail`(id_in int)
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

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `getSchemeList`()
BEGIN

		select `id`,`scheme_name`,`scheme_description`,`scheme_start_date`,`is_scheme_active`,`member_perc`,`company_perc`,`amount`,`is_closed`
		from `scheme_details`;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `getSchemePoolDetails` */

/*!50003 DROP PROCEDURE IF EXISTS  `getSchemePoolDetails` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `getSchemePoolDetails`(schemename varchar(50), limitrows int)
BEGIN

SET @SQL4 = CONCAT("SELECT m.value ,m1.value,m2.value,m3.value
FROM ",schemename," s  JOIN `joining_details` jd
ON s.`parent_join_id` = jd.`id` LEFT JOIN `joining_details` jd1
ON s.child1_join_id = jd1.`id` LEFT JOIN `joining_details` jd2
ON s.child2_join_id = jd2.`id` LEFT JOIN `joining_details` jd3
ON s.child3_join_id = jd3.`id` LEFT JOIN `m_member_type` m
ON jd.`member_type` = m.id LEFT JOIN `m_member_type` m1
ON jd1.`member_type` = m1.id LEFT JOIN `m_member_type` m2
ON jd2.`member_type` = m2.id LEFT JOIN `m_member_type` m3
ON jd3.`member_type` = m3.id 
WHERE s.join_count<3
LIMIT ",limitrows,";");

PREPARE stmt4 FROM @SQL4;
					EXECUTE stmt4;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `joinScheme` */

/*!50003 DROP PROCEDURE IF EXISTS  `joinScheme` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `joinScheme`(`user_id_in` int,`payment_mode_id_in` int,`user_status_in` int,`scheme_id_in` int,`member_type_in` int)
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

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `signup`(email_id_in varchar(255),password_in varchar(255))
BEGIN
    
    insert into `userpassworddetails`(`email_id`,`password`)
    values(email_id_in,password_in);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `updatePaymentDetails` */

/*!50003 DROP PROCEDURE IF EXISTS  `updatePaymentDetails` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updatePaymentDetails`(`joining_id_in` int,`payment_type_in` int,`cheque_no_in` varchar(50),
    cheque_date_in varchar(50),bank_name_in varchar(255),utr_no_in varchar(255),payment_status_in int,amount_in float)
BEGIN
DECLARE jc INT;
	declare schemeid int;
	
	DECLARE memberPerc float;
	DECLARE membertype int;
	DECLARE isExit INT;
	DECLARE payModeId INT;
		
	DECLARE schemejcId INT;
	DECLARE userId INT;
	declare joinUserId int;
	DECLARE joinMemberType INT;
	
	
	IF payment_type_in = 5 then
		select  `user_id`,`member_type` into joinUserId,joinMemberType FROM `joining_details` where id = joining_id_in;
		
		if joinMemberType = 1 then
			
			UPDATE `userdetails`
			SET `balance` = `balance` - amount_in
			WHERE `id` = joinUserId;
		else
			UPDATE `scheme_details`
			SET `balance` = `balance` - amount_in
			WHERE id = joinUserId;
		end if;
	
	
	end if;
	
	
	update `payment_details`
	set `payment_type` = `payment_type_in`,
	`cheque_no`=`cheque_no_in`,
	`cheque_date`=cheque_date_in,
	`bank_name`=bank_name_in,
	`utr_no`=utr_no_in,
	`payment_status` = payment_status_in,
	`amount` = amount_in
	
	where `joining_id`=joining_id_in;
	
	update `joining_details`
	set `user_status` = 2,
	`join_date` = curdate()
	where `id`=joining_id_in;
	
	select scheme_id into schemeid from `joining_details` where `id` = joining_id_in;
	
	
	
	SET @SQL1 = CONCAT("select join_count ,id into  @jc , @schemejcId from Scheme_",schemeid," where join_count<3 limit 1;");
	PREPARE stmt1 FROM @SQL1;
	EXECUTE stmt1;
	
	
IF @jc = 0 THEN
		SET @SQL2 = CONCAT("UPDATE Scheme_",schemeid,"
		SET join_count=join_count+1 ,
			child1_join_id = ",joining_id_in,"
		WHERE id = ",@schemejcId,";"); 
		PREPARE stmt2 FROM @SQL2;
		EXECUTE stmt2;
set isExit =0;		
		
	ELSEIF @jc = 1 THEN
		SET @SQL3 = CONCAT("UPDATE Scheme_",schemeid,"
		SET join_count=join_count+1 ,
			child2_join_id = ",joining_id_in,"
		WHERE id = ",@schemejcId,";"); 
		PREPARE stmt3 FROM @SQL3;
		EXECUTE stmt3;	
		SET isExit =0;		
	
	ELSEIF @jc = 2 THEN
	
		SET @SQL4 = CONCAT("UPDATE Scheme_",schemeid,"
		SET join_count=join_count+1 ,
			child3_join_id = ",joining_id_in,"
		WHERE id = ",@schemejcId,";"); 
		PREPARE stmt4 FROM @SQL4;
					EXECUTE stmt4;
					SET isExit =1;		
	
	
	END IF;
	
		SET @SQL5 = CONCAT('insert into Scheme_',schemeid,'(parent_join_id) values(',joining_id_in,')');
					PREPARE stmt5 FROM @SQL5;
					EXECUTE stmt5;
	
	
	select member_perc into memberPerc from `scheme_details` where id  = schemeid;
	
	 
	
	UPDATE `joining_details`
	SET balance = balance + ((amount_in*(memberPerc))/100)
	WHERE id  = @schemejcId; 
	
	 select `member_type`,`user_id`,`payment_mode_id` into membertype ,userId,payModeId from `joining_details` where id = @schemejcId;
	
	if membertype = 1 then
	
	UPDATE scheme_details
	SET balance = balance + ((amount_in*(100-memberPerc))/100)
	WHERE id  = schemeid;
	
		update `userdetails`
		set `balance` =  balance + ((amount_in*(memberPerc))/100)
		where id = userId;
	else
		-- UPDATE `virtualuser`
		
		update scheme_details
		set balance  =  balance + amount_in
		WHERE id  = schemeid; 
	
	end if;
	
	
	SELECT isExit,userId,membertype,Row_count(),schemeid;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `validate_user` */

/*!50003 DROP PROCEDURE IF EXISTS  `validate_user` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `validate_user`(email_id_in varchar(255),pass varchar(255))
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
