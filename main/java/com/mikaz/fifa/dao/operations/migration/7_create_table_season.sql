create table season(
                       id_season UUID PRIMARY KEY default uuid_generate_v4(),
                       season_start int not null,
                       alias char(10),
                       season_status status default 'NOT_STARTED'
);