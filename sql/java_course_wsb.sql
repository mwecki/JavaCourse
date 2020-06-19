-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 19 Cze 2020, 20:32
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=9 ;

--
-- Zrzut danych tabeli `api_token`
--

INSERT INTO `api_token` (`id`, `created`, `modified`, `user_account_id`, `access_token`, `refresh_token`, `valid_to`) VALUES
(4, '2020-06-19 15:46:45', '2020-06-19 15:46:45', 1, '8fa445c70d81e4a0932b56820d64e2fbab01a0763d6a3c4f410c3872fc6cdbad', '4b0afeb2017caae63350de1c14fc1228a750eb655513bf770f43b146af306c47', '2020-06-19 16:23:40'),
(5, '2020-06-19 15:54:23', '2020-06-19 15:54:23', 1, '24d53f9a0dc9c3f10eed43a651625881d79959671dc170aeb0829f6568315275', '5cb93ec201e8f8efb1df7dfae8356ce5d5e79555dd84f636cd5e26a5bf3e23b3', '2020-06-19 16:24:23'),
(6, '2020-06-19 15:54:52', '2020-06-19 15:54:52', 1, '69bcd433c78920057285d78b22d31c8fda1aa3780513261e531ae35fb85e0615', 'e9361adadd252a9283340210a287eb0d58be770fa1aa9b42e7941a849cb0ca9e', '2020-06-19 16:24:52'),
(7, '2020-06-19 17:01:05', '2020-06-19 17:01:05', 1, 'bd808f1733f3237b231a3b31b557bed284840b12566d0f009a2312ad906fab68', '22cdcfe902b7f2256034750787633f62f49a45d351bd7ee9738cf06e8327a60f', '2020-06-19 17:55:50'),
(8, '2020-06-19 18:13:49', '2020-06-19 18:13:49', 1, 'f6d6b6e827e8e4c906bb21b9d7c3556ac0da13626137dcd33eb76528ea7306f4', '911f56b774fad5d93549c97e666fa4aab63c685c9ed0fecfa896f684112c57f6', '2020-06-19 18:58:47');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `director`
--

INSERT INTO `director` (`id`, `created`, `modified`, `first_name`, `last_name`, `deleted`) VALUES
(1, '2020-06-19 16:58:01', '2020-06-19 18:26:59', 'Martyn', 'Wecki', 0);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

--
-- Zrzut danych tabeli `movie`
--

INSERT INTO `movie` (`id`, `created`, `modified`, `request_status_id`, `genre_id`, `category_id`, `director_id`, `title`, `release_year`, `deleted`) VALUES
(1, '2020-06-19 16:58:07', '2020-06-19 16:58:07', 1, 1, 1, 1, 'Movie Test Title', '2020', 0),
(2, '2020-06-19 18:18:01', '2020-06-19 18:18:01', NULL, 1, 2, 1, 'test888', '2020', NULL),
(3, '2020-06-19 18:27:08', '2020-06-19 18:27:08', 1, 1, 2, 1, 'test888', '2020', 0),
(4, '2020-06-19 18:28:47', '2020-06-19 18:28:47', 1, 1, 2, 1, 'test888', '2020', 0);

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
(3, '2020-06-16 16:32:08', '2020-06-19 16:02:03', 'PG13', 'PG13', 0),
(4, '2020-06-16 16:32:08', '2020-06-17 16:56:42', 'R', 'R', 0),
(5, '2020-06-16 16:32:08', '2020-06-19 16:02:06', 'NC17', 'NC17', 0);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `movie_favorite`
--

INSERT INTO `movie_favorite` (`id`, `created`, `modified`, `movie_id`, `user_account_id`) VALUES
(1, '2020-06-19 17:24:55', '2020-06-19 17:24:55', 1, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `user_account`
--

INSERT INTO `user_account` (`id`, `created`, `modified`, `email`, `pass_hash`, `pass_salt`, `deleted`) VALUES
(1, '2020-06-18 17:41:29', '2020-06-19 15:53:40', 'uzytkownik@java-course-wsb.pl', '445326a9643f32952bd200a7d4ed7f28f4fdada5825b5e67eea4887d78d25adf', 'e02bca8110c4831036e6a8d1e1b911424e8847aab690ad548059b6615e39285e', NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `user_account_role`
--

INSERT INTO `user_account_role` (`id`, `created`, `modified`, `user_account_id`, `role_id`) VALUES
(1, '2020-06-18 17:41:29', '2020-06-18 17:41:29', 1, 2);

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
