--liquibase formatted sql

--changeset arsudakov:1
CREATE TABLE IF NOT EXISTS type (
    id SERIAL PRIMARY KEY,
    name TEXT
);

comment on table type is 'Тип нарушения';
comment on column type.id is 'Идентификационный номер типа нарушения';
comment on column type.name is 'Название типа нарушения';
