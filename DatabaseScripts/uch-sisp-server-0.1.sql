CREATE SCHEMA `uch_sisp` DEFAULT CHARACTER SET utf8 ;
//creates 
CREATE TABLE `sisp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(50) NOT NULL,
  `gcm_registration_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`user_email`),
  UNIQUE KEY `gcm_registration_id_UNIQUE` (`gcm_registration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
