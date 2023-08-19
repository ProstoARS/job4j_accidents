--liquibase formatted sql

--changeset arsudakov:1
CREATE TABLE IF NOT EXISTS accidents (
                           id serial primary key,
                           name text,
                           text text,
                           address text,
                           type_id int references type (id)
);

comment on table accidents is 'Нарушения';
comment on column accidents.id is 'Идентификационный номер нарушения';
comment on column accidents.text is 'Описание нарушения';
comment on column accidents.address is 'Место нарушения';
