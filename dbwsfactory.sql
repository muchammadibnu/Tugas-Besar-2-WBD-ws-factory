-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2020 at 09:53 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbwsfactory`
--

-- --------------------------------------------------------

--
-- Table structure for table `chocolate_stock`
--

CREATE TABLE `chocolate_stock` (
  `name` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chocolate_stock`
--

INSERT INTO `chocolate_stock` (`name`, `amount`) VALUES
('Coklat Baru', 5);

-- --------------------------------------------------------

--
-- Table structure for table `harga_coklat`
--

CREATE TABLE `harga_coklat` (
  `name` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `harga_coklat`
--

INSERT INTO `harga_coklat` (`name`, `harga`) VALUES
('Coklat Baru', 12000);

-- --------------------------------------------------------

--
-- Table structure for table `list_bahan`
--

CREATE TABLE `list_bahan` (
  `tanggal_kadaluwarsa` varchar(100) NOT NULL,
  `bahan` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `list_bahan`
--

INSERT INTO `list_bahan` (`tanggal_kadaluwarsa`, `bahan`, `jumlah`) VALUES
('2021/11/19 10:45:40', 'bahan 1', 31),
('2021/11/19 10:56:30', 'bahan 2', 32);

-- --------------------------------------------------------

--
-- Table structure for table `perubahan_saldo`
--

CREATE TABLE `perubahan_saldo` (
  `id` int(11) NOT NULL,
  `saldo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `perubahan_saldo`
--

INSERT INTO `perubahan_saldo` (`id`, `saldo`) VALUES
(1, 1000000),
(2, 2000000),
(3, 3000000),
(4, 4000000),
(5, 3000000);

-- --------------------------------------------------------

--
-- Table structure for table `request_add_stock`
--

CREATE TABLE `request_add_stock` (
  `id` int(11) NOT NULL,
  `chocolate_name` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request_add_stock`
--

INSERT INTO `request_add_stock` (`id`, `chocolate_name`, `amount`, `status`) VALUES
(1, 'Coklat Baru', 2, 'DELIVERED');

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `chocolate_name` varchar(100) NOT NULL,
  `bahan` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`chocolate_name`, `bahan`, `jumlah`) VALUES
('Coklat Baru', 'bahan 1', 12),
('Coklat Baru', 'bahan 2', 14);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chocolate_stock`
--
ALTER TABLE `chocolate_stock`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `harga_coklat`
--
ALTER TABLE `harga_coklat`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `list_bahan`
--
ALTER TABLE `list_bahan`
  ADD PRIMARY KEY (`bahan`),
  ADD UNIQUE KEY `bahan` (`bahan`);

--
-- Indexes for table `perubahan_saldo`
--
ALTER TABLE `perubahan_saldo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `request_add_stock`
--
ALTER TABLE `request_add_stock`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `perubahan_saldo`
--
ALTER TABLE `perubahan_saldo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `request_add_stock`
--
ALTER TABLE `request_add_stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
