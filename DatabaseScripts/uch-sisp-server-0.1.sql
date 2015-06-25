CREATE SCHEMA `uch_sisp` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `USER` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `user_email` VARCHAR(50) NOT NULL,
    `gcm_registration_id` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
	UNIQUE KEY `id_unique` (`id`),
	UNIQUE KEY `user_email_unique` (`user_email`),
	UNIQUE KEY `gcm_registration_id_unique` (`gcm_registration_id`)
) 	ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `SISP_SUBSCRIPTIONS` (
    `id_son` INT(11) NOT NULL,
    `id_father` INT(11) NOT NULL,
    PRIMARY KEY (`id_son`, `id_father`),
    CONSTRAINT `fk_son` FOREIGN KEY (`id_son`) REFERENCES `USER` (`id`),
    CONSTRAINT `fk_father` FOREIGN KEY (`id_father`) REFERENCES `USER` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
