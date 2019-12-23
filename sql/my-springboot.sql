DROP TABLE IF EXISTS clientdetails;
CREATE TABLE clientdetails (
  appId varchar(128) NOT NULL,
  resourceIds varchar(256) DEFAULT NULL,
  appSecret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  grantTypes varchar(256) DEFAULT NULL,
  redirectUrl varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity int DEFAULT NULL,
  refresh_token_validity int DEFAULT NULL,
  additionalInformation varchar(4096) DEFAULT NULL,
  autoApproveScopes varchar(256) DEFAULT NULL,
  PRIMARY KEY (appId)
);

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id varchar(256) DEFAULT NULL,
  token image,
  authentication_id varchar(128) NOT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  authentication image,
  refresh_token varchar(256) DEFAULT NULL,
  PRIMARY KEY (authentication_id)
);

DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
  userId varchar(256) DEFAULT NULL,
  clientId varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  status varchar(10) DEFAULT NULL,
  expiresAt datetime DEFAULT NULL,
  lastModifiedAt datetime DEFAULT NULL
);

DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(128) NOT NULL,
  resource_ids varchar(256) DEFAULT NULL,
  client_secret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  authorized_grant_types varchar(256) DEFAULT NULL,
  web_server_redirect_uri varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity int DEFAULT NULL,
  refresh_token_validity int DEFAULT NULL,
  additional_information varchar(4096) DEFAULT NULL,
  autoapprove varchar(256) DEFAULT NULL,
  PRIMARY KEY (client_id)
);

DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id varchar(256) DEFAULT NULL,
  token image,
  authentication_id varchar(128) NOT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  PRIMARY KEY (authentication_id)
);

DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code varchar(256) DEFAULT NULL,
  authentication image,
);

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id varchar(256) DEFAULT NULL,
  token image,
  authentication image,
);


INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)	
 VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api',	
 /*spring-security-oauth2-read-client-password1234*/'$2a$04$WGq2P9egiOYoOFemBRfsiO9qTcyJtNRnPKNBl5tokP7IP.eZn93km',	
 'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);	
INSERT INTO OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)	
 VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api',	
 /*spring-security-oauth2-read-write-client-password1234*/'$2a$04$soeOR.QFmClXeFIrhJVLWOQxfHjsJLSpWrU1iGxcMGdu.a5hvfY4W',	
 'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

DROP TABLE IF EXISTS authority;
CREATE TABLE authority (
  id bigint IDENTITY (1, 1) NOT NULL , /*主键*/
  name varchar(256) DEFAULT NULL,
  PRIMARY KEY (id),
);
INSERT INTO AUTHORITY(NAME) VALUES ('COMPANY_CREATE');	
INSERT INTO AUTHORITY(NAME) VALUES ('COMPANY_READ');	
INSERT INTO AUTHORITY(NAME) VALUES ('COMPANY_UPDATE');	
INSERT INTO AUTHORITY(NAME) VALUES ('COMPANY_DELETE');	
INSERT INTO AUTHORITY(NAME) VALUES ('DEPARTMENT_CREATE');	
INSERT INTO AUTHORITY(NAME) VALUES ('DEPARTMENT_READ');	
INSERT INTO AUTHORITY(NAME) VALUES ('DEPARTMENT_UPDATE');	
INSERT INTO AUTHORITY(NAME) VALUES ('DEPARTMENT_DELETE');

DROP TABLE IF EXISTS user_;
CREATE TABLE user_ (
  id bigint IDENTITY (1, 1) NOT NULL , /*主键*/
  user_name varchar(256) NOT NULL,
  password varchar(256) NOT NULL,
  account_expired int not null default 0,
  account_locked int not null default 0,
  CREDENTIALS_EXPIRED int not null default 0,
  ENABLED int not null default 0,
  PRIMARY KEY (id),
);
INSERT INTO user_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)	
  VALUES (1, 'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', 0, 0, 0, 1);	
INSERT INTO user_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)	
  VALUES (2, 'reader', /*reader1234*/'$2a$08$dwYz8O.qtUXboGosJFsS4u19LHKW7aCQ0LXXuNlRfjjGKwj5NfKSe', 0, 0, 0, 1);	
INSERT INTO user_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)	
  VALUES (3, 'modifier', /*modifier1234*/'$2a$08$kPjzxewXRGNRiIuL4FtQH.mhMn7ZAFBYKB3ROz.J24IX8vDAcThsG', 0, 0, 0, 1);	
INSERT INTO user_(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)	
  VALUES (4, 'reader2', /*reader1234*/'$2a$08$vVXqh6S8TqfHMs1SlNTu/.J25iUCrpGBpyGExA.9yI.IlDRadR6Ea', 0, 0, 0, 1);	

DROP TABLE IF EXISTS USERS_AUTHORITIES;
CREATE TABLE USERS_AUTHORITIES (
  USER_ID int not null,
  AUTHORITY_ID int not null,
);
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 1);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 2);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 3);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 4);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 5);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 6);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 7);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 8);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (1, 9);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (2, 2);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (2, 6);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 3);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (3, 7);	
INSERT INTO USERS_AUTHORITIES(USER_ID, AUTHORITY_ID) VALUES (4, 9);