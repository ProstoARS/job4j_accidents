--liquibase formatted sql

--changeset arsudakov:1
CREATE TABLE IF NOT EXISTS users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       PRIMARY KEY (username)
);
comment on table users is 'Пользователи';
comment on column users.username is 'Login пользователя и первичный ключ';
comment on column users.password is 'Пароль пользователя';
comment on column users.enabled is 'Разрешение на доступ';


--changeset arsudakov:2
CREATE TABLE IF NOT EXISTS authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users(username)
);
comment on table authorities is 'Роли пользователей';
comment on column authorities.username is 'Имя пользователя и внешний ключ';
comment on column authorities.authority is 'Роль пользователя';


