CREATE TABLE images (
                        id SERIAL PRIMARY KEY,
                        bytes BYTEA,
                        title TEXT
);


CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL,
                       role VARCHAR NOT NULL,
                       email VARCHAR NOT NULL,
                       sex VARCHAR NOT NULL,
                       birthdate TIMESTAMPTZ,
                       password VARCHAR,
                       image_id INT REFERENCES images(id)
);

CREATE TABLE films (
                       id SERIAL PRIMARY KEY,
                       title TEXT,
                       author TEXT,
                       out_date DATE,
                       time INT,
                       category VARCHAR[],
                       image_id INT REFERENCES images (id)
);

CREATE TABLE rates (
                       id SERIAL PRIMARY KEY,
                       name TEXT NULL,
                       note FLOAT NOT NULL,
                       summary TEXT,
                       detail_summary TEXT,
                       film_id INT REFERENCES films (id),
                       user_id INT REFERENCES users (id)
);
