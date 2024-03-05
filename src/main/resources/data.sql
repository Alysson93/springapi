create table clients(
    id integer primary key auto_increment,
    name varchar(100)
);

create table products(
    id integer primary key auto_increment,
    description varchar(100)
);

create table carts(
    id integer primary key auto_increment,
    client_id integer references clients(id),
    created_at timestamp,
    total numeric(20, 2)
);

create table items(
    id integer primary key auto_increment,
    product_id integer references products(id),
    cart_id integer references carts(id),
    qtd integer
);