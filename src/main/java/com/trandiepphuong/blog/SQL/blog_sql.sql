drop database if exists blog;
create database blog;
use blog;
CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(50),
  lastName VARCHAR(50),
  mobile VARCHAR(15),
  email VARCHAR(50),
  passwordHash VARCHAR(32),
  intro TINYTEXT NULL DEFAULT NULL,
  PRIMARY KEY (id)
);
  
  
CREATE TABLE post (
  id BIGINT NOT NULL AUTO_INCREMENT,
  authorId BIGINT NOT NULL,
  title VARCHAR(75) NOT NULL,
  metaTitle VARCHAR(100) NULL,
  slug VARCHAR(100) NOT NULL,
  summary TINYTEXT NULL,
  published TINYINT(1) NOT NULL DEFAULT 0,
  createdAt DATETIME NOT NULL,
  updatedAt DATETIME NULL DEFAULT NULL,
  publishedAt DATETIME NULL DEFAULT NULL,
  content TEXT NULL DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT `fk_post_user` FOREIGN KEY (`authorId`) REFERENCES `blog`.`user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE category(
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(75) NOT NULL,
  metaTitle VARCHAR(100) NULL DEFAULT NULL,
  content TEXT NULL DEFAULT NULL,
  PRIMARY KEY (id)
 );
 
 CREATE TABLE post_category (
  postId BIGINT NOT NULL,
  categoryId BIGINT NOT NULL,
  PRIMARY KEY (postId, categoryId),
  CONSTRAINT `fk_pc_post` FOREIGN KEY (`postId`) REFERENCES `blog`.`post` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pc_category` FOREIGN KEY (`categoryId`) REFERENCES `blog`.`category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);

