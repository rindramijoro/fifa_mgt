create table match(
                      id_match UUID PRIMARY KEY default uuid_generate_v4(),
                      home UUID references club(id_club) not null,
                      away UUID references club(id_club) not null,
                      stadium varchar(150) not null,
                      match_date timestamp not null,
                      match_status status default 'NOT_STARTED',
                      season UUID references season(id_season) not null
);