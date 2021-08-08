CREATE TABLE comment
(
    id      BIGSERIAL PRIMARY KEY NOT NULL,
    text    VARCHAR               NOT NULL,
    card_id BIGINT
);
