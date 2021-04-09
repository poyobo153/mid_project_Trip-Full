drop table users purge;

create table users(
	id 			    	varchar2(10)		primary key,
	password  	varchar2(10),
	name 			varchar2(20),
	role 				varchar2(5)
);

select * from users;

insert into users values('admin','admin','관리자','Admin');
insert into users values('user1','user1','홍길동','User');