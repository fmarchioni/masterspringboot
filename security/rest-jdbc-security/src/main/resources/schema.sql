create table USERS(
      username varchar(128) not null primary key,
      password varchar(512) not null,
      enabled boolean not null);

create table AUTHORITIES (
      username varchar(128) not null,
      authority varchar(128) not null);

create unique index idx_auth_username on authorities (username,authority);