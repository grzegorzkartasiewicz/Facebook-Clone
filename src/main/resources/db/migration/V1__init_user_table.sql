drop table if exists users;
create table users(
    id int primary key auto_increment,
    name varchar(20) not null,
    surname varchar(20) not null,
    age int not null
)