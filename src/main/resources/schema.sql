CREATE SEQUENCE  HIBERNATE_SEQUENCE START WITH  1 INCREMENT BY 1;

create table if not exists user
(
    id bigint(20) primary key,
    email varchar2(255),
    password varchar2(255),
    first_name varchar2(255),
    last_name varchar2(255),
    mobile varchar2(255),
    created_at timestamp default CURRENT_TIMESTAMP,
    modified_at timestamp default CURRENT_TIMESTAMP
);
