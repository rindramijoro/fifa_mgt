create table match_score(
                            id_match_score UUID primary key default uuid_generate_v4(),
                            game UUID references match(id_match) not null
                            home_score int not null default 0,
                            away_score int not null default 0
);