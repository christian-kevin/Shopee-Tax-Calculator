CREATE DATABASE IF NOT EXISTS `app`;
USE `app`;
CREATE TABLE IF NOT EXISTS `products` (
	`id` VARCHAR(60) NOT NULL, 
	`name` VARCHAR(20) NOT NULL, 
	`price` INTEGER NOT NULL, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
	);
CREATE INDEX `idx_products_name` ON `products`(`name`);

CREATE TABLE IF NOT EXISTS `product_taxes` (
	`id` VARCHAR(60) NOT NULL, 
	`product_id` VARCHAR(60) NOT NULL, 
	`tax_code` SMALLINT NOT NULL, 
	`updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`), 
	constraint fk_product_producttax FOREIGN KEY (product_id) REFERENCES products(id)
	);