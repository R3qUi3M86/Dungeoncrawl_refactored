DROP TABLE IF EXISTS saved_games;
CREATE TABLE saved_games (
                             id serial NOT NULL,
                             player bytea,
                             actor_matrix bytea,
                             item_matrix bytea,
                             decor_matrix bytea,
                             game_map bytea,
                             timestamp timestamp without time zone
);