DROP TABLE IF EXISTS `advices`;
DROP TABLE IF EXISTS `diseases_symptoms`;
DROP TABLE IF EXISTS `symptoms`;
DROP TABLE IF EXISTS `diseases`;
DROP TABLE IF EXISTS `disease_types`;

CREATE TABLE IF NOT EXISTS `disease_types` (
  `id`   INT(11)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `diseases` (
  `id`      INT(11)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name`    VARCHAR(255) NOT NULL,
  `type_id` INT(11)      NOT NULL,
  FOREIGN KEY (`type_id`) REFERENCES `disease_types` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `symptoms` (
  `id`          INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `description` TEXT    NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `diseases_symptoms` (
  `id`         INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `disease_id` INT(11) NOT NULL,
  `symptom_id` INT(11) NOT NULL,
  FOREIGN KEY (`disease_id`) REFERENCES `diseases` (`id`),
  FOREIGN KEY (`symptom_id`) REFERENCES `symptoms` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `advices` (
  `id`         INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `disease_id` INT(11) NOT NULL UNIQUE,
  `text`       TEXT,
  FOREIGN KEY (`disease_id`) REFERENCES `diseases` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
