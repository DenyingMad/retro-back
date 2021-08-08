CREATE TABLE card
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    text      VARCHAR               NOT NULL,
    color     VARCHAR(25),
    column_id BIGINT
);
