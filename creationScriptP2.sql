-- MySQL Script generated by MySQL Workbench
-- Sun Dec 12 12:45:30 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mysql81_06_wuolahpop
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mysql81_06_wuolahpop` ;

-- -----------------------------------------------------
-- Schema mysql81_06_wuolahpop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mysql81_06_wuolahpop` DEFAULT CHARACTER SET utf8 ;
USE `mysql81_06_wuolahpop` ;

-- -----------------------------------------------------
-- Table `mysql81_06_wuolahpop`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mysql81_06_wuolahpop`.`users` ;

CREATE TABLE IF NOT EXISTS `mysql81_06_wuolahpop`.`users` (
  `mail` VARCHAR(40) NOT NULL,
  `password` VARCHAR(12) NOT NULL,
  `name` VARCHAR(15) NOT NULL,
  `surname1` VARCHAR(15) NOT NULL,
  `surname2` VARCHAR(15) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`mail`))
ENGINE = InnoDB;

INSERT INTO users
VALUES ("antonio@gmail.com", "1234", "Antonio", "Vega", "de la Cruz", "Madrid");
INSERT INTO users
VALUES ("fernando@gmail.com", "asdf", "Fernando", "Silva", "Gómez", "Barcelona");
INSERT INTO users
VALUES ("carlos@gmail.com", "12df", "Carlos", "Sosa", "Díaz", "Vigo");
INSERT INTO users
VALUES ("javier@gmail.com", "as34", "Javier", "Ruiz", "Romero", "Toledo");


-- -----------------------------------------------------
-- Table `mysql81_06_wuolahpop`.`items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mysql81_06_wuolahpop`.`items` ;

CREATE TABLE IF NOT EXISTS `mysql81_06_wuolahpop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `vendor` VARCHAR(40) NOT NULL,
  `title` VARCHAR(40) NOT NULL,
  `category` VARCHAR(40) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `photo` LONGBLOB NOT NULL,
  `price` FLOAT NOT NULL,
  `state` VARCHAR(45) NOT NULL DEFAULT 'Disponible',
  PRIMARY KEY (`item_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mysql81_06_wuolahpop`.`transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mysql81_06_wuolahpop`.`transactions` ;

CREATE TABLE IF NOT EXISTS `mysql81_06_wuolahpop`.`transactions` (
  `id` VARCHAR(45) NOT NULL,
  `cod_pedido` VARCHAR(45) NOT NULL,
  `coste` FLOAT NOT NULL,
  `fecha_tarjeta` VARCHAR(45) NOT NULL,
  `cv2` INT NOT NULL,
  `tarjeta` BIGINT(19) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
