CREATE TABLE customers
(
    id       VARCHAR(36)  NOT NULL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    document VARCHAR(20),
    email    VARCHAR(255)
);