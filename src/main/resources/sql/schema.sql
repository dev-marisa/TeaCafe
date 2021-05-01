CREATE TABLE IF NOT EXISTS teas (
    id                     VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    flavor                   VARCHAR      NOT NULL
    );