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

-- INSERT INTO users values (1, 1, 'surname', 'name', 'lastname', '2022-10-10', '1417 777777', 'Voronezh', '+79192301244', 'Maxim@mail.ru', 'some address', 'someAddress');