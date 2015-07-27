CREATE SCHEMA `uch_sisp` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `USER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(50) NOT NULL,
  `gcm_registration_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_unique` (`id`),
  UNIQUE KEY `user_email_unique` (`user_email`),
  UNIQUE KEY `gcm_registration_id_unique` (`gcm_registration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `SISP_SUBSCRIPTIONS` (
    `id_son` INT(11) NOT NULL,
    `id_father` INT(11) NOT NULL,
    PRIMARY KEY (`id_son`, `id_father`),
    CONSTRAINT `fk_son` FOREIGN KEY (`id_son`) REFERENCES `USER` (`id`),
    CONSTRAINT `fk_father` FOREIGN KEY (`id_father`) REFERENCES `USER` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
