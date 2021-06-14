-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 20, 2020 at 10:40 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

DROP TABLE IF EXISTS `meeting`;
CREATE TABLE IF NOT EXISTS `meeting` (
  `meetID` int(5) NOT NULL AUTO_INCREMENT,
  `meetDate` date NOT NULL,
  `meetTime` time NOT NULL,
  `meetLocation` varchar(30) NOT NULL,
  `studID` int(3) DEFAULT NULL,
  `tchrID` int(3) DEFAULT NULL,
  `orgID` int(3) NOT NULL,
  PRIMARY KEY (`meetID`),
  KEY `studID` (`studID`),
  KEY `tchrID` (`tchrID`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meeting`
--

INSERT INTO `meeting` (`meetID`, `meetDate`, `meetTime`, `meetLocation`, `studID`, `tchrID`, `orgID`) VALUES
(28, '2020-04-01', '12:00:00', 'class01-12', NULL, 456, 123),
(29, '2020-04-01', '12:00:00', 'class01-12', NULL, 123, 123),
(24, '2020-03-22', '15:00:00', 'Class 03-14', 456, NULL, 123),
(25, '2020-03-22', '15:00:00', 'Class 03-14', 123, NULL, 123),
(30, '2020-04-02', '13:30:00', 'library', 456, NULL, 123),
(31, '2020-04-02', '13:30:00', 'library', 123, NULL, 123);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `studID` int(3) NOT NULL AUTO_INCREMENT,
  `studPass` varchar(8) NOT NULL,
  `studName` varchar(30) NOT NULL,
  `programme` varchar(30) NOT NULL,
  PRIMARY KEY (`studID`)
) ENGINE=MyISAM AUTO_INCREMENT=547 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studID`, `studPass`, `studName`, `programme`) VALUES
(123, 'hao12345', 'Chin Chun Hao', 'USENG'),
(456, 'zid12345', 'Zidanne', 'UCOMS');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `tchrID` int(3) NOT NULL AUTO_INCREMENT,
  `tchrPass` varchar(8) NOT NULL,
  `tchrName` varchar(30) NOT NULL,
  `department` varchar(30) NOT NULL,
  PRIMARY KEY (`tchrID`)
) ENGINE=MyISAM AUTO_INCREMENT=457 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`tchrID`, `tchrPass`, `tchrName`, `department`) VALUES
(123, 'mic12345', 'Michael', 'SCCM'),
(456, 'rob12345', 'Robert', 'SCCA');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `status`) VALUES
(1, 'admin'),
(2, 'guest');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
