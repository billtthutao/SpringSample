delete from User;

insert into User(id,username,password,fullname,street,city,state,zip,phone_number)
values(1,'bill','$2a$10$OI9mjB4GctNXN1fw4ssbCugXfGzocYht5K1GYUHA1kABz4Eqj.lge',
       'hutao','hanxiyilu','wuhan','hb','430000','123456');
       
delete from Taco;

insert into Taco(id,created_at,name) values(1,'2019-01-01','taco1');
insert into Taco(id,created_at,name) values(2,'2019-02-02','taco2');
insert into Taco(id,created_at,name) values(3,'2019-03-03','taco3');