create table club_stats(
                           id_club_stats UUID PRIMARY KEY default uuid_generate_v4(),
                           club UUID references club(id_club) not null,
                           scored int default 0,
                           against int default 0,
                           goal_difference int default 0,
                           clean_sheets int default 0,
                           points int default 0,
                           season UUID references season(id_season) not null
);