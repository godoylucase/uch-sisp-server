CREATE SCHEMA `uch_sisp` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `sisp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(50) NOT NULL,
  `gcm_registration_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`user_email`),
  UNIQUE KEY `gcm_registration_id_UNIQUE` (`gcm_registration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sisp_subscriptions` (
  `id_son` int(11) NOT NULL,
  `id_father` int(11) NOT NULL,
  PRIMARY KEY (`id_son`,`id_father`),
  UNIQUE KEY `id_son_UNIQUE` (`id_son`),
  KEY `fk_father_to_son_idx` (`id_father`),
  CONSTRAINT `fk_son` FOREIGN KEY (`id_son`) REFERENCES `sisp_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_father` FOREIGN KEY (`id_father`) REFERENCES `sisp_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
