-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Máj 29. 11:38
-- Kiszolgáló verziója: 10.4.32-MariaDB
-- PHP verzió: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `dealership`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `cars`
--

CREATE TABLE `cars` (
  `id` int(11) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `year` int(4) NOT NULL,
  `design` varchar(50) NOT NULL,
  `extra` varchar(250) NOT NULL,
  `price` int(11) NOT NULL,
  `rendezvous_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `cars`
--

INSERT INTO `cars` (`id`, `brand`, `type`, `year`, `design`, `extra`, `price`, `rendezvous_date`) VALUES
(1, 'Opel', 'Corsa', 1994, 'Hatchback', 'Szervó', 300000, '2024-05-27 19:39:58'),
(129843, 'Skoda', 'Rs', 2016, 'Octavia', 'Tolatókamera', 800000000, '2024-06-11 00:00:00'),
(187582, 'BMW', '316i', 2000, 'sedan', 'Ülésfűtés', 50000000, '2024-05-31 00:00:00'),
(220984, 'BMW', '330i', 2001, 'sedan', 'klíma', 30000000, '2024-05-30 00:00:00'),
(328152, 'Mercedes', 'Cla45', 2014, 'Sedan', 'Ülésfűtés', 999999999, '2024-06-26 00:00:00'),
(573471, 'Volkswagen', 'Jetta', 2006, 'Sedan', 'GPS', 2500000, '2024-06-11 00:00:00'),
(755425, 'asas', 'asa', 202, 'asas', 'sasa', 20202020, '2024-05-31 00:00:00');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `extra`
--

CREATE TABLE `extra` (
  `id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `name`, `isAdmin`) VALUES
(1, 'admin', 'admin', 'admin@admin.com', 'admin', 1),
(214957, 't1', 't1', 't1@gmil.com', '123456', 0),
(883465, 't34', 't3', 't3@t3.com', '12345', 0),
(961880, 't2', 't2', 't2@t2.hu', '012345', 0);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `extra`
--
ALTER TABLE `extra`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=755426;

--
-- AUTO_INCREMENT a táblához `extra`
--
ALTER TABLE `extra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=983051;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
