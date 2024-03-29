-- data.sql

create table clients(
    id integer primary key auto_increment,
    name varchar(100),
    cpf varchar(11)
);

create table products(
    id integer primary key auto_increment,
    description varchar(100),
    price numeric(20, 2)
);

create table carts(
    id integer primary key auto_increment,
    client_id integer references clients(id),
    created_at timestamp,
    total numeric(20, 2),
    status varchar(20)
);

create table items(
    id integer primary key auto_increment,
    product_id integer references products(id),
    cart_id integer references carts(id),
    qtd integer
);