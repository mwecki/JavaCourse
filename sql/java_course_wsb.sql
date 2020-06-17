-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17 Cze 2020, 21:28
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `java_course_wsb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `actor`
--

CREATE TABLE IF NOT EXISTS `actor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `actor_idx0` (`last_name`),
  KEY `actor_idx1` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `api_token`
--

CREATE TABLE IF NOT EXISTS `api_token` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_account_id` int(10) unsigned NOT NULL,
  `access_token` varchar(255) COLLATE utf8_bin NOT NULL,
  `refresh_token` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `valid_to` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `api_token_idx0` (`access_token`),
  KEY `api_token_idx1` (`created`),
  KEY `api_token_idx2` (`valid_to`),
  KEY `api_token_fk0` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `director`
--

CREATE TABLE IF NOT EXISTS `director` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `director_idx0` (`last_name`),
  KEY `director_idx1` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie`
--

CREATE TABLE IF NOT EXISTS `movie` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `request_status_id` int(10) unsigned DEFAULT NULL,
  `genre_id` int(10) unsigned DEFAULT NULL,
  `category_id` int(10) unsigned DEFAULT NULL,
  `director_id` int(10) unsigned DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `release_year` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `movie_idx0` (`title`),
  KEY `movie_idx1` (`release_year`),
  KEY `movie_fk0` (`genre_id`),
  KEY `movie_fk1` (`director_id`),
  KEY `movie_fk2` (`request_status_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_actor`
--

CREATE TABLE IF NOT EXISTS `movie_actor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `movie_id` int(10) unsigned NOT NULL,
  `actor_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_actor_idx0` (`movie_id`,`actor_id`),
  KEY `movie_actor_fk1` (`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_category`
--

CREATE TABLE IF NOT EXISTS `movie_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `abbr` varchar(20) COLLATE utf8_bin NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_category_idx0` (`abbr`),
  KEY `movie_category_idx1` (`name`),
  KEY `movie_category_idx2` (`deleted`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Zrzut danych tabeli `movie_category`
--

INSERT INTO `movie_category` (`id`, `created`, `modified`, `name`, `abbr`, `deleted`) VALUES
(1, '2020-06-16 16:32:08', '2020-06-17 16:56:01', 'G', 'G', 0),
(2, '2020-06-16 16:32:08', '2020-06-17 16:56:32', 'PG', 'PG', 0),
(3, '2020-06-16 16:32:08', '2020-06-17 16:56:37', 'PG-13', 'PG-13', 0),
(4, '2020-06-16 16:32:08', '2020-06-17 16:56:42', 'R', 'R', 0),
(5, '2020-06-16 16:32:08', '2020-06-17 16:56:47', 'NC-17', 'NC-17', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_favorite`
--

CREATE TABLE IF NOT EXISTS `movie_favorite` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `movie_id` int(10) unsigned NOT NULL,
  `user_account_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_rating_idx0` (`movie_id`,`user_account_id`),
  KEY `movie_rating_fk1` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_genre`
--

CREATE TABLE IF NOT EXISTS `movie_genre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `abbr` varchar(20) COLLATE utf8_bin NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_genre_idx0` (`abbr`),
  KEY `movie_genre_idx1` (`name`),
  KEY `movie_genre_idx2` (`deleted`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Zrzut danych tabeli `movie_genre`
--

INSERT INTO `movie_genre` (`id`, `created`, `modified`, `name`, `abbr`, `deleted`) VALUES
(1, '2020-06-16 16:32:08', '2020-06-16 16:32:08', 'Akcja', 'Action', 0),
(2, '2020-06-16 16:32:08', '2020-06-16 16:32:08', 'Komedia', 'Comedy', 0),
(3, '2020-06-16 16:32:08', '2020-06-16 16:32:08', 'Dramat', 'Drama', 0),
(4, '2020-06-16 16:32:08', '2020-06-16 16:32:08', 'Science fiction', 'Science fiction', 0),
(5, '2020-06-16 16:32:08', '2020-06-16 16:32:08', 'Thriller', 'Thriller', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_library`
--

CREATE TABLE IF NOT EXISTS `movie_library` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `movie_id` int(10) unsigned NOT NULL,
  `user_account_id` int(10) unsigned NOT NULL,
  `movie_library_status_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_library_idx0` (`movie_id`,`user_account_id`),
  KEY `movie_library_fk1` (`user_account_id`),
  KEY `movie_library_fk2` (`movie_library_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_library_status`
--

CREATE TABLE IF NOT EXISTS `movie_library_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `abbr` varchar(20) COLLATE utf8_bin NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_library_status_idx0` (`abbr`),
  KEY `movie_library_status_idx1` (`name`),
  KEY `movie_library_status_idx2` (`deleted`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `movie_library_status`
--

INSERT INTO `movie_library_status` (`id`, `created`, `modified`, `name`, `abbr`, `deleted`) VALUES
(1, '2020-06-16 18:32:32', '2020-06-16 18:32:32', 'Obejrzany', 'Seen', 0),
(2, '2020-06-16 18:32:32', '2020-06-16 18:32:32', 'Chcę obejrzeć', 'Want to see', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_rating`
--

CREATE TABLE IF NOT EXISTS `movie_rating` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `movie_id` int(10) unsigned NOT NULL,
  `user_account_id` int(10) unsigned NOT NULL,
  `rate` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_rating_idx0` (`movie_id`,`user_account_id`),
  KEY `movie_rating_fk1` (`user_account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movie_request_status`
--

CREATE TABLE IF NOT EXISTS `movie_request_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `abbr` varchar(20) COLLATE utf8_bin NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_request_status_idx0` (`abbr`),
  KEY `movie_request_status_idx1` (`name`),
  KEY `movie_request_status_idx2` (`deleted`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Zrzut danych tabeli `movie_request_status`
--

INSERT INTO `movie_request_status` (`id`, `created`, `modified`, `name`, `abbr`, `deleted`) VALUES
(1, '2020-06-16 18:32:43', '2020-06-16 18:32:43', 'Oczekujące', 'Pending', 0),
(2, '2020-06-16 18:32:43', '2020-06-16 18:32:43', 'Zaakceptowane', 'Accepted', 0),
(3, '2020-06-16 18:32:43', '2020-06-16 18:32:43', 'Odrzucone', 'Rejected', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `abbr` varchar(20) COLLATE utf8_bin NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_idx0` (`abbr`),
  KEY `role_idx1` (`name`),
  KEY `role_idx2` (`deleted`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- Zrzut danych tabeli `role`
--

INSERT INTO `role` (`id`, `created`, `modified`, `name`, `abbr`, `deleted`) VALUES
(1, '2020-06-16 18:25:16', '2020-06-16 18:25:16', 'Administrator', 'ADMIN', 0),
(2, '2020-06-16 18:25:16', '2020-06-16 18:25:16', 'Użytkownik', 'USER', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_account`
--

CREATE TABLE IF NOT EXISTS `user_account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `pass_hash` varchar(255) COLLATE utf8_bin NOT NULL,
  `pass_salt` varchar(255) COLLATE utf8_bin NOT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_account_idx0` (`email`),
  KEY `user_account_idx1` (`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_account_role`
--

CREATE TABLE IF NOT EXISTS `user_account_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_account_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_account_role_idx0` (`user_account_id`,`role_id`),
  KEY `user_account_role_fk1` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `api_token`
--
ALTER TABLE `api_token`
  ADD CONSTRAINT `api_token_fk0` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`);

--
-- Ograniczenia dla tabeli `movie`
--
ALTER TABLE `movie`
  ADD CONSTRAINT `movie_fk3` FOREIGN KEY (`category_id`) REFERENCES `movie_category` (`id`),
  ADD CONSTRAINT `movie_fk0` FOREIGN KEY (`genre_id`) REFERENCES `movie_genre` (`id`),
  ADD CONSTRAINT `movie_fk1` FOREIGN KEY (`director_id`) REFERENCES `director` (`id`),
  ADD CONSTRAINT `movie_fk2` FOREIGN KEY (`request_status_id`) REFERENCES `movie_request_status` (`id`);

--
-- Ograniczenia dla tabeli `movie_actor`
--
ALTER TABLE `movie_actor`
  ADD CONSTRAINT `movie_actor_fk1` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`id`),
  ADD CONSTRAINT `movie_actor_fk0` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`);

--
-- Ograniczenia dla tabeli `movie_library`
--
ALTER TABLE `movie_library`
  ADD CONSTRAINT `movie_library_fk2` FOREIGN KEY (`movie_library_status_id`) REFERENCES `movie_library_status` (`id`),
  ADD CONSTRAINT `movie_library_fk0` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  ADD CONSTRAINT `movie_library_fk1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`);

--
-- Ograniczenia dla tabeli `movie_rating`
--
ALTER TABLE `movie_rating`
  ADD CONSTRAINT `movie_rating_fk0` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
  ADD CONSTRAINT `movie_rating_fk1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`);

--
-- Ograniczenia dla tabeli `user_account_role`
--
ALTER TABLE `user_account_role`
  ADD CONSTRAINT `user_account_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `user_account_role_fk0` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
