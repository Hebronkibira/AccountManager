insert into customer (id , customer_id , email , first_name , last_name ,pin ) values(0,'ADMIN001','admin@mailtest.com','Admin','admin','$2a$10$l7Ixsb2a5H6YGRBExgUA7OKj03F4l0b5xTEOiBRO5RpL87QVcWTdm');
insert into account (id , account_no , account_type, current_balance, customer_id, date_created ) values (0,'924672347812', 'savings', '20.0','ADMIN001',now());


