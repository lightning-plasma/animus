CREATE TABLE main.book
(
    isbn       VARCHAR(13)  NOT NULL,
    title      VARCHAR(100) NOT NULL,
    author     VARCHAR(100) NOT NULL,
    price      INT          NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    primary key (isbn)
)
