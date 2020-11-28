-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Nov 2020 pada 01.57
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.10

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
-- Struktur dari tabel `chocolate_stock`
--

CREATE TABLE `chocolate_stock` (
  `name` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `chocolate_stock`
--

INSERT INTO `chocolate_stock` (`name`, `amount`) VALUES
('Coklat Baru', 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `harga_coklat`
--

CREATE TABLE `harga_coklat` (
  `name` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `harga_coklat`
--

INSERT INTO `harga_coklat` (`name`, `harga`) VALUES
('Coklat Baru', 12000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_bahan`
--

CREATE TABLE `list_bahan` (
  `tanggal_kadaluwarsa` varchar(100) NOT NULL,
  `bahan` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `list_bahan`
--

INSERT INTO `list_bahan` (`tanggal_kadaluwarsa`, `bahan`, `jumlah`) VALUES
('2021/11/19 10:45:40', 'bahan 1', 31),
('2021/11/19 10:56:30', 'bahan 2', 32);

-- --------------------------------------------------------

--
-- Struktur dari tabel `perubahan_saldo`
--

CREATE TABLE `perubahan_saldo` (
  `id` int(11) NOT NULL,
  `saldo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `perubahan_saldo`
--

INSERT INTO `perubahan_saldo` (`id`, `saldo`) VALUES
(1, 1000000),
(2, 2000000),
(3, 3000000),
(4, 4000000),
(5, 3000000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `request_add_stock`
--

CREATE TABLE `request_add_stock` (
  `id` int(11) NOT NULL,
  `chocolate_name` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'PENDING'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `request_add_stock`
--

INSERT INTO `request_add_stock` (`id`, `chocolate_name`, `amount`, `status`) VALUES
(1, 'Coklat Baru', 2, 'DELIVERED'),
(8, 'Chocolate 2', 1, 'PENDING'),
(9, 'Chocolate 2', 1, 'PENDING');

-- --------------------------------------------------------

--
-- Struktur dari tabel `resep`
--

CREATE TABLE `resep` (
  `chocolate_name` varchar(100) NOT NULL,
  `bahan` varchar(100) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `resep`
--

INSERT INTO `resep` (`chocolate_name`, `bahan`, `jumlah`) VALUES
('Coklat Baru', 'bahan 1', 12),
('Coklat Baru', 'bahan 2', 14);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(1000) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`username`, `email`, `password`, `role`) VALUES
('\"ibnu\"', '\"ibnu@gmail.com\"', '36e07e16be6ff47be311b6b3c7030382', 'user');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `chocolate_stock`
--
ALTER TABLE `chocolate_stock`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeks untuk tabel `harga_coklat`
--
ALTER TABLE `harga_coklat`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeks untuk tabel `list_bahan`
--
ALTER TABLE `list_bahan`
  ADD PRIMARY KEY (`bahan`),
  ADD UNIQUE KEY `bahan` (`bahan`);

--
-- Indeks untuk tabel `perubahan_saldo`
--
ALTER TABLE `perubahan_saldo`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `request_add_stock`
--
ALTER TABLE `request_add_stock`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `perubahan_saldo`
--
ALTER TABLE `perubahan_saldo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `request_add_stock`
--
ALTER TABLE `request_add_stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
