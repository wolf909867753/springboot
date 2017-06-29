################  cluster DB  ################
CREATE DATABASE springbootdb_cluster;

use springbootdb_cluster;

CREATE TABLE user(
  id INT(10) unsigned PRIMARY KEY NOT NULL COMMENT '用户编号' AUTO_INCREMENT,
  user_name VARCHAR(25) COMMENT '用户名称',
  description VARCHAR(100) COMMENT '描述'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT user VALUES (1 ,'wolf','https://github.com/wolf909867753/springboot');

################  master DB   ################
CREATE DATABASE springbootdb;
use springbootdb;
CREATE TABLE city (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '城市编号',
  province_id int(10) unsigned  NOT NULL COMMENT '省份编号',
  city_name varchar(25) DEFAULT NULL COMMENT '城市名称',
  description varchar(25) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT city VALUES (1 ,1,'潍坊市','我的家在山东省潍坊市。');