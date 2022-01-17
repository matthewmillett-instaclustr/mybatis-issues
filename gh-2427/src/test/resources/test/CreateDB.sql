drop table users if exists;

create table users (
  id varchar(20),
  full_name varchar(20),
  phone_number varchar(20),
  city varchar(20),
  is_admin boolean
);

insert into users (id, full_name, phone_number, city, is_admin) values
('1', 'John Smith', '+61 400 000 000', 'Sydney', false),
('2', 'Jane Doe', '+61 411 111 111', 'Melbourne', true);
