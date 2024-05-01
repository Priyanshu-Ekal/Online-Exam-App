-- MySQL dump 10.10
--
-- Host: localhost    Database: online-exam
-- ------------------------------------------------------
-- Server version	5.0.11-beta-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--


/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
LOCK TABLES `admin` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `subject` varchar(255) NOT NULL,
  `qno` int(11) NOT NULL,
  `answer` varchar(255) default NULL,
  `op1` varchar(255) default NULL,
  `op2` varchar(255) default NULL,
  `op3` varchar(255) default NULL,
  `op4` varchar(255) default NULL,
  `qtext` varchar(255) default NULL,
  PRIMARY KEY  (`qno`,`subject`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question`
--


/*!40000 ALTER TABLE `question` DISABLE KEYS */;
LOCK TABLES `question` WRITE;
INSERT INTO `question` VALUES ('java',1,'final','static','public','private','final','which modifier is applicable for local variable? '),('maths',1,'4','4','8','12','16','what is 2+2'),('java',2,'default','public','protected','default','none','which access specifier is NOT available in other packages?'),('maths',2,'6','4','6','12','16','what is 3+3'),('java',3,'pathvariable','requestbody','pathvariable','both','none','which annonation will be used for localhost:8080/getdata/101'),('maths',3,'8','4','8','12','16','what is 4+4');
UNLOCK TABLES;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `username` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY  (`subject`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `result`
--


/*!40000 ALTER TABLE `result` DISABLE KEYS */;
LOCK TABLES `result` WRITE;
INSERT INTO `result` VALUES ('priyanshu','java',2),('x','java',0),('xyz','java',3),('x','maths',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) default NULL,
  `mobno` bigint(20) NOT NULL,
  `emailid` varchar(255) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--


/*!40000 ALTER TABLE `user` DISABLE KEYS */;
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('abc','abc456',456789,'abc@gmail.com'),('lmn','opqrs',12345,'lm@123'),('pqr','pqr789',789456123,'pqr@456.com'),('x','y',123,'x@y'),('xyz','xyz123',123456,'xyz@gmail.com');
UNLOCK TABLES;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

