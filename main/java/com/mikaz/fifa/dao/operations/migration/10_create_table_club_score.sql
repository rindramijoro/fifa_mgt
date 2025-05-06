create table club_score(
                           id_club_score UUID PRIMARY KEY default uuid_generate_v4(),
                           game UUID references match(id_match) not null,
                           club UUID references club(id_club) not null,
                           is_home boolean not null,
                           goal_scored int not null default 0
);