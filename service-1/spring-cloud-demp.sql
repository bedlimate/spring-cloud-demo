create database spring_cloud_demo  ;
create table service_user(
  id int unsigned primary key ,
  name varchar(64) not null default '',
  birthday date not null ,
  now varchar(32) not null
);