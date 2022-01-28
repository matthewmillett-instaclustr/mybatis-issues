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

create table accounts (
   id varchar(20),
   account_name varchar(20),
   company_name varchar(20),
   city varchar(20)
);

insert into accounts (id, account_name, company_name, city) values
('1', 'Account A', 'Company A', 'Canberra'),
('2', 'Account B', 'Company B', 'Brisbane');

create table companies (
  company_name varchar(20),
  company_account varchar(20),
  main_user varchar(20)
);

insert into companies (company_name, company_account, main_user) values
('Company A', '1', '1'),
('Company B', '2', '2');

create table tasks (
    task_name varchar(20),
    status varchar(20)
);

insert into tasks (task_name, status) values
('Task 1', 'completed'),
('Task 2', 'in progress'),
('Task 3', 'queued');