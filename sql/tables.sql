/*
--Create Tables
create table if not exists category(
	id serial primary key,
	creationDate timestamp not null
	name varchar(50) unique not null,
	tokenName varchar(500) unique not null,
	description varchar(500) not null
);

create table if not exists rol(
	id serial primary key,
	name varchar(20)
);

create table if not exists users(
	username varchar(20) primary key,
	fullname varchar(50) not null,
	status boolean not null,
	mail varchar(50) not null,
	userpassword varchar(70) not null,
	usertype integer not null
);


--Foreign keys

alter table users
add constraint fk_usertype
	foreign key(usertype)
	references rol(id) on delete cascade;

--Poblar

--Poblar rol
insert into rol (name) values ('Administrator');
insert into rol (name) values ('Usuario');

--Poblar Usuario
insert into users (username,fullname,status,mail,userpassword,usertype) values ('admin','Administrador',true,'admin@gmail.com','a74a6393d8fba6e2178e4e36e32c73c5bef049290e03fdc3ada929eb03770138',1);
insert into users (username,fullname,status,mail,userpassword,usertype) values ('user','Estudiante',true,'usuario@gmail.com','75cee5b221098c39dc19feca49b7b7cfe46405057d0361b18726990a5f91bf25',2);


--Poblar category
insert into category (creationdate,name,tokenName,description) values (CURRENT_TIMESTAMP,'Juan','CR94RF9IMPE3P48','Prueba Moderna en Paciente');
insert into category (creationdate,name,tokenName,description) values (CURRENT_TIMESTAMP,'Wilsoonnn','C2TZHMK4UX3Q6ZR','Prueba Sinovac2 en Paciente');
insert into category (creationdate,name,tokenName,description) values (CURRENT_TIMESTAMP,'Nicolas','BZW2LES30O49M4X','Prueba Pfizer en Paciente');

--Delete Tables
drop table users;
drop table rol;
drop table category;

--Delete Constraints
alter table users drop constraint fk_usertype;
*/