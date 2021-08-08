CREATE TABLE board
(
    id             BIGSERIAL PRIMARY KEY NOT NULL,
    name           VARCHAR               NOT NULL,
    votes_per_user INT DEFAULT 6,
    creation_date  DATE                  NOT NULL
);

CREATE INDEX idx_board_id ON board (id);
