drop database bankdb

create database bankdb

use bankdb;

  
-- Users
  CREATE TABLE users (
     user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
     user_name VARCHAR(10) NOT NULL,
     pwd VARCHAR(10) NOT NULL,
     email VARCHAR(50) NOT NULL,
     updated_by VARCHAR(25) NOT NULL,
     update_time DATE,
     primary key(user_id)
  );  
