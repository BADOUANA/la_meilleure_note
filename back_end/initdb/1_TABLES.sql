CREATE TABLE images (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR,
                        type VARCHAR,
                        image_data OID
);


CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR NOT NULL,
                       last_name VARCHAR NOT NULL,
                       role VARCHAR NOT NULL,
                       email VARCHAR NOT NULL,
                       sex VARCHAR NOT NULL,
                       birthdate TIMESTAMPTZ,
                       password VARCHAR,
                       image_id INT REFERENCES images(id)
);

CREATE TABLE films (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR,
                       author VARCHAR,
                       out_date DATE,
                       time INT,
                       category VARCHAR[],
                       image_id INT REFERENCES images (id)
);

CREATE TABLE rates (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR NULL,
                       note FLOAT NOT NULL,
                       summary VARCHAR,
                       detail_summary VARCHAR,
                       film_id INT REFERENCES films (id),
                       user_id INT REFERENCES users (id)
);

