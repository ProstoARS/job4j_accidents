--liquibase formatted sql

--changeset arsudakov:1
CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);
comment on table authorities is 'Роли пользователей';
comment on column authorities.id is 'Идентификатор роли пользователя';
comment on column authorities.authority is 'Роль пользователя';

--changeset arsudakov:2
CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);
comment on table users is 'Пользователи';
comment on column users.id is 'Идентификатор пользователя';
comment on column users.username is 'Login пользователя и первичный ключ';
comment on column users.password is 'Пароль пользователя';
comment on column users.enabled is 'Разрешение на доступ';

--changeset arsudakov:3
insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

--changeset arsudakov:4
insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$2ZfuAoxd5RugqNqeYIWCg.i96p0ATmRnno1u/pEzObg8AwjnwmjRu',
        (select id from authorities where authority = 'ROLE_ADMIN'));


