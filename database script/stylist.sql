
drop table users;
CREATE TABLE USERS(user_id number,username varchar2(50) not null, first_name varchar2(50) not null,last_name varchar2(50) not null,email varchar2(50) not null ,password varchar2(50)not null ,
country_id number not null,
PRIMARY KEY (user_id),
CONSTRAINT fk_user_country FOREIGN KEY( country_id ) REFERENCES countries( country_id ) on delete set null
);


create table countries (
    country_id number,
    name VARCHAR2(50),
    code VARCHAR2(5),
    native_name VARCHAR2(100),
    capital VARCHAR2(50),
    region VARCHAR2(20),
    subregion VARCHAR2(30),
    currencies VARCHAR2(20),
    primary key (country_id)
    );
    
create table roles(role_id number, role varchar2(255), primary key(role_id));

create  sequence country_next_id
MINVALUE 1
START WITH 1
INCREMENT BY 1;

create table post(post_id number, author varchar2(50), title varchar2(50), adding_date DATE DEFAULT sysdate NOT NULL, description varchar2(3000));

drop sequence post_next_id;
create  sequence post_next_id
MINVALUE 1
START WITH 1
INCREMENT BY 1;

insert into post(post_id, author, title, description) values(post_next_id.nextVal, 'ASH_GM', 'First Post', 'Adding first post. Only admin and user that is owner can delete it');
insert into post(post_id, author, title, description) values(post_next_id.nextVal, 'JOHN_FIRST', 'Johns Post', 'Adding second post. Only admin and user(John) that is owner can delete it');
insert into post(post_id, author, title, description) values(post_next_id.nextVal, 'NATALIE_GIRL', 'Natalies Post', 'Adding third post. Only admin and user(Natalie) that is owner can delete it');

select * from post;

