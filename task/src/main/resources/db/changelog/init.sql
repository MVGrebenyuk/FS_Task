CREATE TABLE users (
    id bigserial primary key,
    bank_id bigint,
    surname varchar,
    name varchar,
    last_name varchar,
    birthday date,
    pass_number varchar,
    city varchar,
    phone varchar,
    email varchar,
    registration varchar,
    current_address varchar
);