--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` timestamp,
  `updated_at` timestamp,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USERS_index` (`email`)
);

--
-- Table structure for table `RENTALS`
--

CREATE TABLE `RENTALS` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,RENTALS
  `surface` decimal(10,0) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `owner_id` int NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `MESSAGES`
--

CREATE TABLE `MESSAGES` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rental_id` int,
  `user_id` int,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp,
  PRIMARY KEY (`id`)
);