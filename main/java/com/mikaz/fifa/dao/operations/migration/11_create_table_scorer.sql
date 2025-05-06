create table scorer(
                       id_scorer UUID PRIMARY KEY default uuid_generate_v4(),
                       game UUID references match(id_match) not null,
                       player UUID references player(id_player) not null,
                       minute int not null check(minute between 1 and 90),
                    own_goal boolean not null default false
);