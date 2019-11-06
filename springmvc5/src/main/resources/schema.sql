drop table if exists User;

create table User (
  id identity,
  username varchar(25) not null,
  password varchar(25) not null
);