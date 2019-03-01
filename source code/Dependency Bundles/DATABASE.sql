-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2017 at 06:51 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user`
--

-- --------------------------------------------------------

--
-- Table structure for table `core`
--

CREATE TABLE `core` (
  `id` int(11) NOT NULL,
  `enc_name` varchar(500) COLLATE armscii8_bin NOT NULL,
  `enc_email` varchar(500) COLLATE armscii8_bin NOT NULL,
  `enc_mobile` varchar(500) COLLATE armscii8_bin NOT NULL,
  `prev_key` varchar(500) COLLATE armscii8_bin NOT NULL,
  `cur_key` varchar(500) COLLATE armscii8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

--
-- Dumping data for table `core`
--

INSERT INTO `core` (`id`, `enc_name`, `enc_email`, `enc_mobile`, `prev_key`, `cur_key`) VALUES
(1, 'dX4PUjBhHNx1JeYo8AzURg==', '8r1qm0UU1uLueMDw7H8cDw==', '/9eBVMduPIta+odHbS/lQQ==', '51928057559321099573551', '');

-- --------------------------------------------------------

--
-- Table structure for table `timestamp`
--

CREATE TABLE `timestamp` (
  `time` varchar(100) COLLATE armscii8_bin NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

--
-- Dumping data for table `timestamp`
--

INSERT INTO `timestamp` (`time`) VALUES
('2017/03/12');

-- --------------------------------------------------------

--
-- Table structure for table `usertab`
--

CREATE TABLE `usertab` (
  `id` int(11) NOT NULL,
  `name` varchar(420) COLLATE armscii8_bin NOT NULL,
  `email` varchar(420) COLLATE armscii8_bin NOT NULL,
  `password` varchar(420) COLLATE armscii8_bin NOT NULL,
  `mobile` varchar(420) COLLATE armscii8_bin NOT NULL,
  `key_1` varchar(420) COLLATE armscii8_bin NOT NULL,
  `share_key` varchar(420) COLLATE armscii8_bin NOT NULL,
  `mac` varchar(200) COLLATE armscii8_bin NOT NULL,
  `mac_count` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

--
-- Dumping data for table `usertab`
--

INSERT INTO `usertab` (`id`, `name`, `email`, `password`, `mobile`, `key_1`, `share_key`, `mac`, `mac_count`) VALUES
(1, 'name', 'name@name.com', 'password', '9874512365', '51928057559321099573551', '891470', 'FC-AA-14-F3-90-7B', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `core`
--
ALTER TABLE `core`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usertab`
--
ALTER TABLE `usertab`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mobile` (`mobile`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `core`
--
ALTER TABLE `core`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `usertab`
--
ALTER TABLE `usertab`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
