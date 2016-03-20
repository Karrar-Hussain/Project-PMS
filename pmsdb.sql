-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2016 at 03:19 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pmsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE IF NOT EXISTS `application` (
  `Dweller_Id` text NOT NULL,
  `Application_Type` text NOT NULL,
  `Description` longtext NOT NULL,
  `Time` text NOT NULL,
  `ProblemNo` int(11) NOT NULL,
  `Status` text NOT NULL,
  `Condition` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`Dweller_Id`, `Application_Type`, `Description`, `Time`, `ProblemNo`, `Status`, `Condition`) VALUES
('41', 'Emergency', 'ghvvnbv', '12/06/2015 6:30:01 PM', 30, 'Unsolved', 'Seen'),
('41', 'Emergency', 'uihyiu87878hiuh', '12/06/2015 6:30:06 PM', 31, 'Unsolved', 'Seen'),
('41', 'Emergency', 'hjbjbh767', '12/06/2015 6:30:11 PM', 32, 'Unsolved', 'Seen'),
('41', 'Plumber', 'b,b igb', '12/06/2015 6:30:18 PM', 33, 'Solved', 'Seen'),
('41', 'Electrition', 'jhgb', '12/06/2015 6:30:24 PM', 34, 'Unsolved', 'Seen'),
('41', 'Electrition', 'hgvbvb', '12/06/2015 6:30:31 PM', 35, 'Unsolved', 'Seen'),
('41', 'Plumber', 'cxvxcv', '12/07/2015 2:02:42 PM', 36, 'Unsolved', 'Seen');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `bookingid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `propertyid` int(11) NOT NULL,
  `bookedOn` date NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Booked',
  `bookedTill` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingid`, `userid`, `propertyid`, `bookedOn`, `status`, `bookedTill`) VALUES
(1, 42, 116, '2015-12-03', 'Canceled', '2015-12-10'),
(3, 45, 103, '2015-12-02', 'Canceled', '2015-12-09'),
(22, 42, 103, '2015-12-02', 'Canceled', '2015-12-09'),
(24, 43, 98, '2015-12-02', 'Canceled', '2015-12-09'),
(26, 45, 100, '2015-12-02', 'Canceled', '2015-12-09'),
(27, 21, 99, '2015-12-05', 'Canceled', '2015-12-12'),
(28, 41, 106, '2015-12-06', 'Canceled', '2015-12-13'),
(29, 43, 106, '2015-12-30', 'Booked', '2016-01-06'),
(30, 21, 118, '2015-12-30', 'Booked', '2016-01-06');

-- --------------------------------------------------------

--
-- Table structure for table `cemmercialrentalproperty`
--

CREATE TABLE IF NOT EXISTS `cemmercialrentalproperty` (
  `crpropertyid` int(11) NOT NULL,
  `rent` int(11) NOT NULL,
  `owner` varchar(20) DEFAULT 'company',
  `renter` varchar(20) DEFAULT NULL,
  `isonrent` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cemmercialrentalproperty`
--

INSERT INTO `cemmercialrentalproperty` (`crpropertyid`, `rent`, `owner`, `renter`, `isonrent`) VALUES
(98, 40000, 'company', NULL, 0),
(99, 25000, 'company', NULL, 1),
(106, 400000, 'company', NULL, 0),
(121, 240000, 'company', NULL, 1),
(131, 24000, 'company', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `commercialforsaleproperty`
--

CREATE TABLE IF NOT EXISTS `commercialforsaleproperty` (
  `cfpropertyid` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `sold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commercialforsaleproperty`
--

INSERT INTO `commercialforsaleproperty` (`cfpropertyid`, `price`, `sold`) VALUES
(101, 1250005, 0),
(103, 22500000, 1),
(116, 240000, 1),
(143, 140000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE IF NOT EXISTS `meeting` (
  `meetingid` int(11) NOT NULL,
  `bookingid` int(11) NOT NULL,
  `meetingOn` text NOT NULL,
  `managerid` int(11) NOT NULL,
  `customerid` int(11) NOT NULL,
  `isSucceeded` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meeting`
--

INSERT INTO `meeting` (`meetingid`, `bookingid`, `meetingOn`, `managerid`, `customerid`, `isSucceeded`) VALUES
(3, 42, '5:30AM  Dec 10, 2015', 42, 42, 0),
(5, 43, '3:00AM  Dec 21, 2015', 42, 43, 0);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE IF NOT EXISTS `property` (
  `propertyID` int(11) NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'Residential',
  `status` varchar(20) NOT NULL DEFAULT 'Full',
  `area` int(11) NOT NULL,
  `location` varchar(100) NOT NULL,
  `image` text CHARACTER SET ascii NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`propertyID`, `type`, `status`, `area`, `location`, `image`) VALUES
(96, 'Residential', 'ForRent', 25, 'looneyWala stopooo', ''),
(98, 'Commercial', 'ForRent', 20, 'Lari Adda', ''),
(99, 'Commercial', 'ForRent', 20, 'Punjab University', ''),
(100, 'Residential', 'ForRent', 20, 'Khayban Garden', 'pms\\112243379_1016482985070257_5405088007187677737_n.jpg'),
(101, 'Commercial', 'ForSale', 15, 'Pull Dingroo', ''),
(103, 'Commercial', 'ForSale', 15, 'Looney Wala Stop', ''),
(105, 'Residential', 'ForSale', 4, 'home city', ''),
(106, 'Commercial', 'ForRent', 60, 'Near Zulfiqar Ali Shaheed Masjid', ''),
(107, 'Residential', 'ForSale', 25, 'fast cfs', ''),
(111, 'Residential', 'ForSale', 24, 'localarea red cre', ''),
(112, 'Residential', 'ForRent', 15, 'Location available', ''),
(115, 'Residential', 'ForSale', 50, 'Lari Adda fsd', ''),
(116, 'Commercial', 'ForSale', 14, 'no location ', ''),
(117, 'Residential', 'ForRent', 24, 'no location', ''),
(118, 'Residential', 'ForSale', 50, 'nathing is ', ''),
(119, 'Residential', 'ForSale', 2, 'NO LOCATION', ''),
(120, 'Residential', 'ForSale', 10, 'a one punjab', ''),
(121, 'Residential', 'ForSale', 1, 'hotel', ''),
(131, 'Commercial', 'Full', 10, 'null', 'PMS'),
(139, 'Commercial', 'Full', 10, 'no Location', 'No Image Path'),
(141, 'Residential', 'ForSale', 10, 'lakfjak', ''),
(142, 'Residential', 'ForSale', 12, 'jjj', 'pms10547586_587906714669859_5160698095125607269_n.jpg'),
(143, 'Commercial', 'ForSale', 42, 'Local Hostels', 'pms12294896_1174824489198331_6675339187056380928_n.jpg'),
(144, 'Residential', 'ForSale', 10, 'pensylvia', ''),
(145, 'Residential', 'ForSale', 23, 'Sarghoda Road', 'pms1449444227_Home.png'),
(146, 'Residential', 'Full', 1, 'No Location', 'path'),
(147, 'Residential', 'ForSale', 5, 'Mariabad, Quetta ', '');

-- --------------------------------------------------------

--
-- Table structure for table `residentialforsaleproperty`
--

CREATE TABLE IF NOT EXISTS `residentialforsaleproperty` (
  `rfpropertyid` int(11) NOT NULL,
  `specificationid` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `sold` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residentialforsaleproperty`
--

INSERT INTO `residentialforsaleproperty` (`rfpropertyid`, `specificationid`, `price`, `sold`) VALUES
(105, 16, 1000000000, 1),
(107, 17, 100005000, 1),
(111, 21, 1200000, 1),
(115, 25, 500000100, 1),
(118, 27, 9000099, 1),
(119, 28, 2500000, 1),
(120, 29, 24000000, 1),
(121, 30, 2000000, 1),
(141, 48, 545454, 1),
(142, 49, 500000, 1),
(144, 50, 10000, 1),
(145, 51, 1200000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `residentialrentalproperty`
--

CREATE TABLE IF NOT EXISTS `residentialrentalproperty` (
  `rrpropertyid` int(11) NOT NULL,
  `specificationid` int(11) NOT NULL,
  `renter` varchar(20) DEFAULT NULL,
  `owner` varchar(20) DEFAULT 'company',
  `rent` int(11) NOT NULL,
  `isonrent` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residentialrentalproperty`
--

INSERT INTO `residentialrentalproperty` (`rrpropertyid`, `specificationid`, `renter`, `owner`, `rent`, `isonrent`) VALUES
(96, 14, NULL, 'company', 10002, 1),
(100, 15, NULL, 'company', 124000, 1),
(112, 23, NULL, 'company', 245000, 1),
(117, 26, NULL, 'company', 510000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `residentialspecification`
--

CREATE TABLE IF NOT EXISTS `residentialspecification` (
  `specificationid` int(11) NOT NULL,
  `nobedroom` int(11) NOT NULL DEFAULT '0',
  `nobathroom` int(11) NOT NULL DEFAULT '0',
  `nokitchen` int(11) NOT NULL DEFAULT '0',
  `nostorey` int(11) NOT NULL DEFAULT '1',
  `teriss` tinyint(1) NOT NULL DEFAULT '0',
  `balcony` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `residentialspecification`
--

INSERT INTO `residentialspecification` (`specificationid`, `nobedroom`, `nobathroom`, `nokitchen`, `nostorey`, `teriss`, `balcony`) VALUES
(14, 4, 4, 5, 1, 1, 1),
(15, 5, 15, 2, 2, 1, 1),
(16, 5, 5, 1, 2, 1, 1),
(17, 10, 20, 10, 10, 1, 1),
(18, 2, 2, 1, 2, 1, 1),
(19, 1, 1, 1, 1, 1, 1),
(20, 5, 5, 5, 5, 1, 1),
(21, 5, 5, 2, 2, 1, 1),
(22, 5, 5, 1, 2, 0, 1),
(23, 1, 1, 1, 1, 0, 1),
(24, 2, 2, 2, 1, 1, 1),
(25, 2, 1, 2, 2, 1, 1),
(26, 1, 2, 2, 2, 1, 1),
(27, 1, 2, 0, 2, 1, 1),
(28, 5, 5, 1, 2, 1, 1),
(29, 2, 2, 2, 1, 1, 1),
(30, 1, 2, 1, 3, 0, 1),
(31, 0, 0, 0, 0, 0, 0),
(32, 0, 0, 0, 0, 0, 0),
(33, 0, 0, 0, 0, 0, 0),
(34, 0, 0, 0, 0, 0, 0),
(35, 0, 0, 0, 0, 0, 0),
(36, 0, 0, 0, 0, 0, 0),
(37, 5, 5, 5, 5, 1, 1),
(38, 5, 5, 5, 5, 1, 1),
(39, 5, 5, 5, 5, 1, 1),
(40, 5, 5, 5, 5, 1, 1),
(41, 5, 5, 5, 5, 1, 1),
(42, 5, 5, 5, 5, 1, 1),
(43, 0, 0, 0, 0, 0, 0),
(44, 1, 2, 1, 1, 1, 1),
(45, 2, 5, 1, 2, 1, 1),
(46, 1, 5, 1, 2, 1, 1),
(47, 4, 4, 1, 4, 1, 0),
(48, 4, 1, 1, 2, 1, 1),
(49, 2, 2, 1, 1, 0, 1),
(50, 1, 1, 1, 2, 1, 1),
(51, 3, 3, 1, 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `user_email` varchar(70) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_image` varchar(300) CHARACTER SET ascii DEFAULT NULL,
  `user_type` varchar(15) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `birthdate` varchar(15) NOT NULL,
  `age` int(11) NOT NULL,
  `marital_status` varchar(1) NOT NULL,
  `contact_no` varchar(20) NOT NULL,
  `address` text
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `user_email`, `user_password`, `user_image`, `user_type`, `gender`, `birthdate`, `age`, `marital_status`, `contact_no`, `address`) VALUES
(21, 'My', 'Admin', 'admin', 'admin', 'Profiles\\\\2nazir.jpg', 'Admin', 'M', '1915-1-1', 0, 'N', '09877675216', 'Hostel'),
(41, 'Usama', 'Aslam', 'usama@gmail.com', 'Chmod123', 'Profiles\\\\1danish2.jpg', 'Deweller', 'M', '1996-6-6', 19, 'Y', '076765746134', 'Hostel'),
(42, 'karrar', 'hussain', 'mno', '123', 'Profiles\\\\one-piece-6580219.jpg', 'Manager', 'F', '12-12-1994', 21, 'Y', '0761257635871', NULL),
(43, 'Karrar', 'Hussain', 'ctm', '123', '', 'Customer', 'M', '1997-1-1', 18, 'Y', '0376257635', 'hostel'),
(45, 'Karrar', 'Hussain', 'karrar@gmail.com', 'Chmod123', '', 'Customer', 'M', '1997-1-1', 18, 'Y', '083286377287', 'hostel'),
(46, 'Danish', 'Dullu', 'dani', '123', 'Profiles\\\\one-piece-6580219.jpg', 'Customer', 'F', '1995-3-11', 20, 'N', '03457888554', 'Lasani pulli, Girls Hostel'),
(47, 'Hassan', 'ahmad', 're@gmail.com', 'As1236456', 'Profiles\\\\11049585_1694474290785133_5734476693157741159_o.jpg', 'Deweller', 'M', '1996-2-27', 19, 'Y', '0987654332', 'gsfsfsfv'),
(48, 'Elec', 'trition', 'elc@gmail.com', 'Pms123456', 'Profiles\\\\144565.jpg.png', 'Electrition', 'M', '1996-1-2', 19, 'Y', '03027384906', 'cbvbxcvxb'),
(49, 'Plu', 'mber', 'plu@gmail.com', 'Pms123456', 'Profiles\\\\light.jpg', 'Plumber', 'M', '1997-1-1', 18, 'Y', '03047698456', 'cgzhfgh'),
(50, 'Emer', 'gency', 'emr@gmail.com', 'Pms123456', 'Profiles\\\\1366x768_kakashi-hatake-sharingan-warior-manga-anime-HD-Wallpaper.jpg', 'Emergency', 'M', '1994-1-1', 21, 'Y', '03025798445', 'ghfghf'),
(51, 'Nazeer', 'Ahmed', 'nazeer@gmail.com', 'Pms123123', 'Profiles589781.jpg.png', 'Customer', 'F', '1994-4-4', 21, 'Y', '03434343433', 'Girls Hostel');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`ProblemNo`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingid`), ADD UNIQUE KEY `bookingid` (`bookingid`), ADD KEY `fk_propertyid` (`propertyid`), ADD KEY `fk_userid` (`userid`);

--
-- Indexes for table `cemmercialrentalproperty`
--
ALTER TABLE `cemmercialrentalproperty`
  ADD PRIMARY KEY (`crpropertyid`), ADD UNIQUE KEY `cpropertyid` (`crpropertyid`);

--
-- Indexes for table `commercialforsaleproperty`
--
ALTER TABLE `commercialforsaleproperty`
  ADD PRIMARY KEY (`cfpropertyid`), ADD UNIQUE KEY `cfpropertyid` (`cfpropertyid`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`meetingid`), ADD UNIQUE KEY `bookingid` (`bookingid`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`propertyID`), ADD UNIQUE KEY `propertyID` (`propertyID`);

--
-- Indexes for table `residentialforsaleproperty`
--
ALTER TABLE `residentialforsaleproperty`
  ADD PRIMARY KEY (`rfpropertyid`), ADD UNIQUE KEY `specificationid` (`specificationid`);

--
-- Indexes for table `residentialrentalproperty`
--
ALTER TABLE `residentialrentalproperty`
  ADD PRIMARY KEY (`rrpropertyid`), ADD UNIQUE KEY `rpropertyid` (`rrpropertyid`), ADD UNIQUE KEY `specificationid` (`specificationid`);

--
-- Indexes for table `residentialspecification`
--
ALTER TABLE `residentialspecification`
  ADD PRIMARY KEY (`specificationid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `user_email` (`user_email`), ADD UNIQUE KEY `user_email_2` (`user_email`), ADD UNIQUE KEY `contact_no` (`contact_no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `ProblemNo` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `meeting`
--
ALTER TABLE `meeting`
  MODIFY `meetingid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `propertyID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=148;
--
-- AUTO_INCREMENT for table `residentialspecification`
--
ALTER TABLE `residentialspecification`
  MODIFY `specificationid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
ADD CONSTRAINT `fk_propertyid` FOREIGN KEY (`propertyid`) REFERENCES `property` (`propertyID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cemmercialrentalproperty`
--
ALTER TABLE `cemmercialrentalproperty`
ADD CONSTRAINT `foreign key` FOREIGN KEY (`crpropertyid`) REFERENCES `property` (`propertyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `commercialforsaleproperty`
--
ALTER TABLE `commercialforsaleproperty`
ADD CONSTRAINT `commercialforsaleproperty_ibfk_1` FOREIGN KEY (`cfpropertyid`) REFERENCES `property` (`propertyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `residentialforsaleproperty`
--
ALTER TABLE `residentialforsaleproperty`
ADD CONSTRAINT `propertyid` FOREIGN KEY (`rfpropertyid`) REFERENCES `property` (`propertyID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `specificationid` FOREIGN KEY (`specificationid`) REFERENCES `residentialspecification` (`specificationid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `residentialrentalproperty`
--
ALTER TABLE `residentialrentalproperty`
ADD CONSTRAINT `rrpropertyid` FOREIGN KEY (`rrpropertyid`) REFERENCES `property` (`propertyID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `rrspecificationid` FOREIGN KEY (`specificationid`) REFERENCES `residentialspecification` (`specificationid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
