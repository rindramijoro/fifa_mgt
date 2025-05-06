create table coach(
    id_coach UUID PRIMARY KEY default uuid_generate_v4(),
    coach_name varchar(150) not null,
    nationality varchar(150) not null
);