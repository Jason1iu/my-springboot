DROP TABLE IF EXISTS clientdetails;
DROP TABLE IF EXISTS oauth_access_token;
DROP TABLE IF EXISTS oauth_approvals;
DROP TABLE IF EXISTS oauth_client_details;
DROP TABLE IF EXISTS oauth_client_token;
DROP TABLE IF EXISTS oauth_code;
DROP TABLE IF EXISTS oauth_refresh_token;

-- customized oauth_client_details table
create table clientdetails (
  appId VARCHAR(256) NOT NULL PRIMARY KEY,
  resourceIds VARCHAR(256) DEFAULT NULL,
  appSecret VARCHAR(256) DEFAULT NULL,
  scope VARCHAR(256) DEFAULT NULL,
  grantTypes VARCHAR(256) DEFAULT NULL,
  redirectUrl VARCHAR(256) DEFAULT NULL,
  authorities VARCHAR(256) DEFAULT NULL,
  access_token_validity INTEGER DEFAULT NULL,
  refresh_token_validity INTEGER DEFAULT NULL,
  additionalInformation VARCHAR(4096) DEFAULT NULL,
  autoApproveScopes VARCHAR(256) DEFAULT NULL,
);

create table oauth_access_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token image,
  authentication_id VARCHAR(256) NOT NULL PRIMARY KEY,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
  authentication image,
  refresh_token VARCHAR(256) DEFAULT NULL,
);

create table oauth_approvals (
	userId VARCHAR(256) DEFAULT NULL,
	clientId VARCHAR(256) DEFAULT NULL,
	scope VARCHAR(256) DEFAULT NULL,
	status VARCHAR(10) DEFAULT NULL,
	expiresAt datetime DEFAULT NULL,
	lastModifiedAt datetime  DEFAULT NULL,
);

-- used in tests that use HSQL
create table oauth_client_details (
  client_id VARCHAR(256) NOT NULL PRIMARY KEY,
  resource_ids VARCHAR(256) DEFAULT NULL,
  client_secret VARCHAR(256) DEFAULT NULL,
  scope VARCHAR(256) DEFAULT NULL,
  authorized_grant_types VARCHAR(256) DEFAULT NULL,
  web_server_redirect_uri VARCHAR(256) DEFAULT NULL,
  authorities VARCHAR(256) DEFAULT NULL,
  access_token_validity INTEGER DEFAULT NULL,
  refresh_token_validity INTEGER DEFAULT NULL,
  additional_information VARCHAR(4096) DEFAULT NULL,
  autoapprove VARCHAR(256) DEFAULT NULL,
);
INSERT INTO oauth_client_details VALUES ('dev', '', 'dev', 'app', 'password,client_credentials,authorization_code,refresh_token', 'http://www.baidu.com', '', 3600, 3600, '{\"country\":\"CN\",\"country_code\":\"086\"}', 'false');

create table oauth_client_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token image,
  authentication_id VARCHAR(256) NOT NULL PRIMARY KEY,
  user_name VARCHAR(256) DEFAULT NULL,
  client_id VARCHAR(256) DEFAULT NULL,
);

create table oauth_code (
  code VARCHAR(256) DEFAULT NULL,
  authentication image,
);

create table oauth_refresh_token (
  token_id VARCHAR(256) DEFAULT NULL,
  token image,
  authentication image,
);

update [sys_permission] set url='/**',name='';
delete from [sys_user_role] where sys_user_id=2 and sys_role_id=1;
delete from [sys_permission_role] where sys_permission_id=1 and sys_role_id=2;