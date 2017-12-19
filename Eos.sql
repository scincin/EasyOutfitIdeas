-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.30-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema eos
--

CREATE DATABASE IF NOT EXISTS eos;
USE eos;

--
-- Definition of table `gelen_sensor`
--

DROP TABLE IF EXISTS `gelen_sensor`;
CREATE TABLE `gelen_sensor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `gelen_nem` varchar(45) NOT NULL,
  `gelen_sicaklik` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gelen_sensor`
--

/*!40000 ALTER TABLE `gelen_sensor` DISABLE KEYS */;
INSERT INTO `gelen_sensor` (`id`,`gelen_nem`,`gelen_sicaklik`) VALUES 
 (2,'16.00','24.00'),
 (3,'16.00','24.00'),
 (4,'16.00','24.00'),
 (5,'16.00','24.00'),
 (6,'16.00','24.00'),
 (7,'15.00','24.00'),
 (8,'16.00','24.00'),
 (9,'15.00','24.00'),
 (10,'15.00','24.00'),
 (11,'15.00','24.00'),
 (12,'15.00','24.00'),
 (13,'16.00','24.00'),
 (14,'16.00','24.00'),
 (15,'16.00','24.00'),
 (16,'15.00','24.00'),
 (17,'16.00','24.00'),
 (18,'15.00','25.00'),
 (19,'15.00','24.00'),
 (20,'14.00','25.00'),
 (21,'15.00','25.00'),
 (22,'15.00','24.00'),
 (23,'14.00','25.00'),
 (24,'15.00','25.00'),
 (25,'14.00','25.00'),
 (26,'14.00','25.00');
/*!40000 ALTER TABLE `gelen_sensor` ENABLE KEYS */;


--
-- Definition of table `oneriler`
--

DROP TABLE IF EXISTS `oneriler`;
CREATE TABLE `oneriler` (
  `idoneriler` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `giysi_tipi` varchar(45) NOT NULL,
  PRIMARY KEY (`idoneriler`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `oneriler`
--

/*!40000 ALTER TABLE `oneriler` DISABLE KEYS */;
INSERT INTO `oneriler` (`idoneriler`,`giysi_tipi`) VALUES 
 (1,'led1'),
 (2,'led2'),
 (3,'led3'),
 (4,'led4');
/*!40000 ALTER TABLE `oneriler` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`name`,`password`) VALUES 
 ('emre','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
