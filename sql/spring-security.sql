--DROP TABLE IF EXISTS authority;
--DROP TABLE IF EXISTS clientdetails;
--DROP TABLE IF EXISTS oauth_access_token;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_permission_role;
DROP TABLE IF EXISTS sys_permission;

CREATE TABLE sys_user (
  id int IDENTITY (1, 1) NOT NULL, /*主键*/
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
insert into sys_user(username,password) values ('user','e10adc3949ba59abbe56e057f20f883e');
insert into sys_user(username,password) values ('admin','e10adc3949ba59abbe56e057f20f883e');

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id int IDENTITY (1, 1) NOT NULL, /*主键*/
  name varchar(255) DEFAULT '',
  PRIMARY KEY (id)
);
insert into sys_role(name) values ('USER');
insert into sys_role(name) values ('ADMIN');

DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
  id int IDENTITY (1, 1) NOT NULL, /*主键*/
  url varchar(255) default '',
  name varchar(255) default '',
  descritpion varchar(255) default null,
  pid int default null,
  PRIMARY KEY (id),
);
insert into sys_permission(url,name,pid) values ('/user/common','common',0);
insert into sys_permission(url,name,pid) values ('/user/admin','admin',0);

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  sys_user_id int DEFAULT NULL,
  sys_role_id int DEFAULT NULL
);
insert into sys_user_role(sys_user_id,sys_role_id) values (1,1);
insert into sys_user_role(sys_user_id,sys_role_id) values (2,1);
insert into sys_user_role(sys_user_id,sys_role_id) values (2,2);

DROP TABLE IF EXISTS sys_permission_role;
CREATE TABLE sys_permission_role (
  sys_role_id int DEFAULT NULL,
  sys_permission_id int DEFAULT NULL,
);
insert into sys_permission_role(sys_role_id,sys_permission_id) values (1,1);
insert into sys_permission_role(sys_role_id,sys_permission_id) values (2,1);
insert into sys_permission_role(sys_role_id,sys_permission_id) values (2,2);

--select p.*  from Sys_User u LEFT JOIN sys_role_user sru on u.id= sru.Sys_User_id LEFT JOIN Sys_Role r on sru.Sys_Role_id=r.id LEFT JOIN Sys_permission_role spr on spr.role_id=r.id LEFT JOIN Sys_permission p on p.id =spr.permission_id where u.id=2;