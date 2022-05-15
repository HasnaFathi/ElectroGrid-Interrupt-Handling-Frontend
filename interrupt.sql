-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 06:14 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `interrupt`
--

CREATE TABLE `interrupt` (
  `InterruptID` int(11) NOT NULL,
  `interruptCode` varchar(10) NOT NULL,
  `Date` varchar(20) NOT NULL,
  `Duration` decimal(5,2) NOT NULL,
  `Start_time` varchar(12) NOT NULL,
  `End_time` varchar(12) NOT NULL,
  `Region` varchar(30) NOT NULL,
  `Reason` varchar(50) NOT NULL,
  `AdminID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interrupt`
--

INSERT INTO `interrupt` (`InterruptID`, `interruptCode`, `Date`, `Duration`, `Start_time`, `End_time`, `Region`, `Reason`, `AdminID`) VALUES
(2, 'IN002', '2022-09-04', '3.00', '08:00:', '11:00:', 'Colombo', 'Lack of Resources', 'AD001'),
(12, 'IN007', '2022-09-04', '2.00', '09:00:', '11:00:', 'Kandy', 'Break Down', 'AD002'),
(15, 'IN009', '2022-04-23', '1.00', '20:00:', '21:00:', 'Badulla', 'Break Down', 'AD001'),
(16, 'IN010', '2022-04-24', '2.00', '20:00:', '22:00:', 'Kegalle', 'On Demand', 'AD001'),
(25, 'I012', '2022-09-08', '2.00', '1:00', '3:00', 'Colombo', 'Lack of Resources', 'AD001');

--
-- Triggers `interrupt`
--
DELIMITER $$
CREATE TRIGGER `backup_Interrupt` AFTER DELETE ON `interrupt` FOR EACH ROW insert into interrupt_backup VALUES(old.InterruptID,old.interruptCode,old.Date,old.Duration,old.Start_time,old.End_time,old.Region,old.Reason,old.AdminID)
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `interrupt`
--
ALTER TABLE `interrupt`
  ADD PRIMARY KEY (`InterruptID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `interrupt`
--
ALTER TABLE `interrupt`
  MODIFY `InterruptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
