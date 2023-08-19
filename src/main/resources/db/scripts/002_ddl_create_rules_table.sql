--liquibase formatted sql

--changeset arsudakov:1
CREATE TABLE IF NOT EXISTS rules (
    id SERIAL PRIMARY KEY ,
    name TEXT
);

comment on table rules is 'Статьи нарушения';
comment on column rules.id is 'Идентификационный номер статьи нарушения';
comment on column rules.name is 'Название статьи нарушения';
