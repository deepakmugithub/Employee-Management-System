-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 18 avr. 2023 à 19:41
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `dbemp`
--

-- --------------------------------------------------------

--
-- Structure de la table `attendence`
--

DROP TABLE IF EXISTS `attendence`;
CREATE TABLE IF NOT EXISTS `attendence` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL,
  `fHalf` varchar(12) NOT NULL,
  `sHalf` varchar(12) NOT NULL,
  `empId` int NOT NULL,
  PRIMARY KEY (`id`)
) ;

--
-- Déchargement des données de la table `attendence`
--

INSERT INTO `attendence` (`id`, `day`, `fHalf`, `sHalf`, `empId`) VALUES
(1, '2023-03-26', 'Present', 'Present', 101),
(2, '2023-03-26', 'Present', 'Present', 102),
(3, '2023-03-26', 'Present', 'Absent', 104),
(6, '2023-03-14', 'Present', 'Present', 101),
(8, '2023-03-11', 'Present', 'Present', 103),
(7, '2023-03-14', 'Absent', '', 101),
(9, '2023-04-21', 'Present', 'Absent', 1005);

-- --------------------------------------------------------

--
-- Structure de la table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `details` varchar(200) DEFAULT NULL,
  `empId` int NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- Structure de la table `emp`
--

DROP TABLE IF EXISTS `emp`;
CREATE TABLE IF NOT EXISTS `emp` (
  `id` int NOT NULL,
  `fname` varchar(30) NOT NULL,
  `mname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `contact` varchar(15) NOT NULL,
  `emailId` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `address` varchar(30) NOT NULL,
  `education` varchar(30) NOT NULL,
  `fathername` varchar(30) DEFAULT NULL,
  `poste` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Déchargement des données de la table `emp`
--

INSERT INTO `emp` (`id`, `fname`, `mname`, `lname`, `gender`, `contact`, `emailId`, `dob`, `address`, `education`, `fathername`, `poste`) VALUES
(100, 'ilunga', 'ok', 'walter', 'Other', '895048749', 'ertyui@gmail.com', '2010-03-12', 'Mu campus', 'Master', 'ilunga Boss', 'CEO'),
(102, 'koang', 'b Koang', 'Biel', 'Male', '7865432', 'koangB@gmail.com', '2012-10-08', 'Mu campus', 'Doctor', 'Koang Boss', 'DG'),
(103, 'danu', 'dddd', 'Wilson', 'Other', '77763542', 'danu@gmail.com', '2002-12-23', 'prabudhua', 'genus', 'danu Boss', 'sales'),
(101, 'emmet', 'guah', 'g', 'Male', '87856534', 'emmet@gmail.com', '2000-04-09', 'Mu c', 'Doctor', 'emmet Boss', 'Clerk'),
(104, 'niddhi', 'zala', 'mao', 'Female', '104787678', 'niddhi@gmail.com', '2003-06-25', 'dheli', 'degree', 'zala Boss', 'Dean'),
(1005, 'Deepak', 'Kumar', 'Bhai', 'Female', '566677788', 'k@gmail.com', '2005-09-23', 'Mu ', 'Doctor', 'K BOSS', 'Head'),
(1006, 'pokam', 'M', 'And', 'Female', '6734999', 'pk@gmail.com', '2003-12-20', 'Mu Ha', 'doctor', 'PK Boss', 'DG');

-- --------------------------------------------------------

--
-- Structure de la table `leave`
--

DROP TABLE IF EXISTS `leave`;
CREATE TABLE IF NOT EXISTS `leave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  `details` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Déchargement des données de la table `leave`
--

INSERT INTO `leave` (`id`, `type`, `details`) VALUES
(1, 'PL', 'Privilge Leave');

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  `empId` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Déchargement des données de la table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `status`, `empId`) VALUES
(1, 'Emmet', '72001', 'Admin', '101'),
(2, 'walter', '1234', 'Admin', ''),
(3, 'koang b', '72003', 'Admin', ''),
(5, 'Deepak', '72004', 'Admin', '1005'),
(7, 'danu', '1234', 'User', '103'),
(9, 'majook', '1234', '', ''),
(13, 'jinesh', '12345', '', ''),
(11, 'sample', '1234', '', ''),
(12, 'jarsam', '1234', '', ''),
(14, 'pokam', '12345', 'User', '1006');

-- --------------------------------------------------------

--
-- Structure de la table `salary`
--

DROP TABLE IF EXISTS `salary`;
CREATE TABLE IF NOT EXISTS `salary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bSal` float NOT NULL,
  `hra` float DEFAULT NULL,
  `dra` float DEFAULT NULL,
  `mid` float DEFAULT NULL,
  `pf` float DEFAULT NULL,
  `month` varchar(12) DEFAULT NULL,
  `year` int NOT NULL,
  `empId` int NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Déchargement des données de la table `salary`
--

INSERT INTO `salary` (`id`, `bSal`, `hra`, `dra`, `mid`, `pf`, `month`, `year`, `empId`) VALUES
(1, 90000, 45000, 9000, 18000, 3600, 'January', 2023, 104),
(2, 50000, 5, 5, 6, 6, 'February', 2022, 103),
(3, 70000, 897, 452, 45, 78, 'February', 2023, 101),
(4, 40000, 4, 40, 45, 50, 'April', 2023, 102),
(5, 40000, 4, 40, 45, 50, 'June', 2023, 100),
(6, 40000, 4, 40, 45, 50, 'June', 2023, 103),
(7, 78000, 34500, 234, 456, 1000, 'April', 2023, 104),
(8, 84500, 42250, 8450, 16900, 3380, 'May', 2023, 103),
(9, 84500, 42250, 8450, 16900, 3380, 'February', 2023, 103),
(10, 67000, 33500, 6700, 13400, 2680, 'June', 2022, 102),
(11, 65400, 32700, 6540, 13080, 2616, 'May', 2022, 102);

-- --------------------------------------------------------

--
-- Structure de la table `takeleave`
--

DROP TABLE IF EXISTS `takeleave`;
CREATE TABLE IF NOT EXISTS `takeleave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sdate` date NOT NULL,
  `edate` date NOT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `empId` int NOT NULL,
  `leaveId` int NOT NULL,
  `decision` varchar(12) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Déchargement des données de la table `takeleave`
--

INSERT INTO `takeleave` (`id`, `sdate`, `edate`, `reason`, `empId`, `leaveId`, `decision`) VALUES
(1, '2029-03-23', '2031-03-23', 'tired', 103, 4, 'Pending'),
(4, '2023-04-10', '2023-04-16', 'nothing much', 1006, 2, 'Rejected'),
(3, '2023-03-28', '2023-03-31', 'resting , enjoying', 104, 1, 'Accepted'),
(5, '2023-04-05', '2023-04-07', 'resting', 1006, 4, 'Pending'),
(6, '2023-04-01', '2023-04-02', 'festival Mu', 103, 1, 'Pending'),
(7, '2023-04-09', '2023-04-13', 'chop chop', 103, 4, 'Accepted'),
(8, '2023-04-11', '2023-04-14', 'sik', 103, 2, 'Pending');

-- --------------------------------------------------------

--
-- Structure de la table `typeleave`
--

DROP TABLE IF EXISTS `typeleave`;
CREATE TABLE IF NOT EXISTS `typeleave` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(30) DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`id`)
);

--
-- Déchargement des données de la table `typeleave`
--

INSERT INTO `typeleave` (`id`, `type`, `details`) VALUES
(1, 'PL', 'Prevelege Leave'),
(2, 'CL', 'Casual Leave'),
(4, 'Comp Off', 'Compensatory Off');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
