create table player(
                       id_player UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       player_name varchar(150) not null,
                       jersey_number int not null,
                       age int not null,
                       position positions not null,
                       nationality varchar (150) not null,
                       club UUID references club(id_club)
);