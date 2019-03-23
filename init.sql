CREATE DATABASE IF NOT EXISTS `app`;
USE `app`;
CREATE TABLE IF NOT EXISTS `products` (
	`id` VARCHAR(20) NOT NULL, 
	`name` VARCHAR(20) NOT NULL, 
	`price` INTEGER NOT NULL, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
	);

CREATE TABLE IF NOT EXISTS `product_taxes` (
	`id` VARCHAR(20) NOT NULL, 
	`product_id` VARCHAR(20) NOT NULL, 
	`tax_code` SMALLINT NOT NULL, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`), 
	constraint fk_product_producttax FOREIGN KEY (product_id) REFERENCES products(id)
	);