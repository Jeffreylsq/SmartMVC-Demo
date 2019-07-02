

-- Create user table
create table user(
id int primary key auto_increment,
username varchar(50) unique,
password varchar(50)
);