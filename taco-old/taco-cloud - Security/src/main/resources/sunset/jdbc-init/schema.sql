create table if not exists Users (
  username varchar(10) not null,
  password varchar(100) not null,
  enabled  integer  not null
);

create table if not exists UserAuthorities (
  username varchar(10) not null,
  authority varchar(50) not null
);