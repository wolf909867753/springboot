
#请勿手工写入数据 供remember-me功能使用
CREATE TABLE `persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
);

CREATE TABLE `s_user` (
   `id` int(11) NOT NULL auto_increment,
   `dob` date default NULL,
   `email` varchar(50) default NULL,
   `name` varchar(120) default NULL,
   `password` varchar(120) default NULL,
   PRIMARY KEY  (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `springbootdb`.`s_user`(`id`,`dob`,`email`,`name`,`password`) VALUES ( NULL,'2016-12-14','909867753@qq.com','wolf','111111');
INSERT INTO `springbootdb`.`s_user`(`id`,`dob`,`email`,`name`,`password`) VALUES ( NULL,'2016-01-01','13810881664@163.com','admin','111111');
INSERT INTO `springbootdb`.`s_user`(`id`,`dob`,`email`,`name`,`password`) VALUES ( NULL,'2017-07-15','13051781002@163.com','user','111111');

CREATE TABLE `s_role` (
   `id` int(11) NOT NULL auto_increment,
   `name` varchar(100) default NULL,
   `uid` int(11) NOT NULL,
   PRIMARY KEY  (`id`),
   KEY `FKpkoo0xfyi6rd0hs9ybqv92fjp` (`uid`),
   CONSTRAINT `FKpkoo0xfyi6rd0hs9ybqv92fjp` FOREIGN KEY (`uid`) REFERENCES `s_user` (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 INSERT INTO `springbootdb`.`s_role`(`id`,`name`,`uid`) VALUES ( NULL,'ADMIN','1');
 INSERT INTO `springbootdb`.`s_role`(`id`,`name`,`uid`) VALUES ( NULL,'super','2');
 INSERT INTO `springbootdb`.`s_role`(`id`,`name`,`uid`) VALUES ( NULL,'user','3');

CREATE TABLE `s_resource_role` (
   `id` int(11) NOT NULL auto_increment,
   `resource_id` varchar(50) default NULL,
   `role_id` varchar(50) default NULL,
   `update_time` datetime default NULL,
   PRIMARY KEY  (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 INSERT INTO `springbootdb`.`s_resource_role`(`id`,`resource_id`,`role_id`,`update_time`) VALUES ( NULL,'111',NULL,'2016-12-15 12:00:00');
 INSERT INTO `springbootdb`.`s_resource_role`(`id`,`resource_id`,`role_id`,`update_time`) VALUES ( NULL,'222',NULL,'2016-12-15 12:00:00');
 INSERT INTO `springbootdb`.`s_resource_role`(`id`,`resource_id`,`role_id`,`update_time`) VALUES ( NULL,'333',NULL,'2016-12-15 12:00:00');

CREATE TABLE `s_resource` (
   `id` int(11) NOT NULL auto_increment,
   `method_name` varchar(400) default NULL,
   `method_path` varchar(1000) default NULL,
   `remark` varchar(200) default NULL,
   `resource_id` varchar(50) default NULL,
   `resource_name` varchar(400) default NULL,
   `resource_string` varchar(1000) default NULL,
   PRIMARY KEY  (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 INSERT INTO `springbootdb`.`s_resource`(`id`,`method_name`,`method_path`,`remark`,`resource_id`,`resource_name`,`resource_string`) VALUES ( NULL,NULL,NULL,'1','111',NULL,'/hello');
 INSERT INTO `springbootdb`.`s_resource`(`id`,`method_name`,`method_path`,`remark`,`resource_id`,`resource_name`,`resource_string`) VALUES ( NULL,NULL,NULL,'1','222',NULL,'/hello2');
 INSERT INTO `springbootdb`.`s_resource`(`id`,`method_name`,`method_path`,`remark`,`resource_id`,`resource_name`,`resource_string`) VALUES ( NULL,NULL,NULL,'1','333',NULL,'/hello3');
