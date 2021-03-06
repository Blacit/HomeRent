create table house
(
    id          bigint generated by default as identity
        constraint house_pkey
            primary key,
    description varchar(255)     not null,
    landlord_id bigint           not null,
    outside     varchar(255)     not null,
    price       double precision not null,
    rooms       integer          not null,
    status      varchar(255),
    city_id     bigint
        constraint fkfin1oycsetvc7uktiniuw5ei6
            references city
);

alter table house
    owner to root;