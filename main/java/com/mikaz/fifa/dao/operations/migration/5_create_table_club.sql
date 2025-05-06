create table club(
                     id_club UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                     club_name varchar (150) unique not null,
                     acronyme char(3) not null,
                     creation_date int not null,
                     stadium varchar(150) not null,
                     coach UUID references coach(id_coach) not null,
);