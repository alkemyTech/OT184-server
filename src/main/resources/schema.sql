USE alkemy;

CREATE TABLE activities
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    image      VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted BOOL         NOT NULL DEFAULT FALSE
);