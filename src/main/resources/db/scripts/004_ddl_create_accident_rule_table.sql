--liquibase formatted sql

--changeset arsudakov:1
CREATE TABLE IF NOT EXISTS accident_rule (
    id SERIAL PRIMARY KEY ,
    accident_id INT NOT NULL REFERENCES accidents (id),
    rule_id INT NOT NULL REFERENCES rules (id),
    UNIQUE (accident_id, rule_id)
);

