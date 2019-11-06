delete from Users where username = 'bill';
insert into Users (username, password, enabled) 
                values ('bill', '$2a$10$h3psRmdAsPHbEi/NcRVPcuIiDQVImLZtA09XUM/3Lt5cW4438Ym6K', '1');
                
delete from UserAuthorities where username = 'bill';
insert into UserAuthorities (username, authority) 
                values ('bill', 'USER');