DROP TABLE users;
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL,
                       role VARCHAR NOT NULL,
                       email VARCHAR NOT NULL,
                       birthdate TIMESTAMPTZ,
                       password VARCHAR,
                       image_id INT,
                       FOREIGN KEY (image_id) REFERENCES images(id)
);

DROP TABLE films;
CREATE TABLE films (
                       idFilm SERIAL PRIMARY KEY,
                       title TEXT,
                       author TEXT,
                       out_date DATE,
                       time INT,
                       category VARCHAR[],
                       user_id INT,
                       image_id INT,
                       FOREIGN KEY (image_id) REFERENCES images(id)
);

drop table rates;
CREATE TABLE rates (
                       idRate SERIAL PRIMARY KEY,
                       name TEXT NULL,
                       note FLOAT NOT NULL,
                       summary TEXT,
                       detail_summary TEXT,
                       film_id INT,
                       user_id INT,
                       FOREIGN KEY (film_id) REFERENCES films(idFilm),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);


drop table images;
CREATE TABLE images (
                        id SERIAL PRIMARY KEY,
                        bytes BYTEA,
                        title TEXT,
                        user_id INT NULL,
                        film_id INT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id),
                        FOREIGN KEY (film_id) REFERENCES films(idFilm)
);

/*
drop table user_film;
create table user_film
(
    id SERIAL PRIMARY KEY,
    user_id int not null,
    film_id int not null
);

drop table user_rate;
create table user_rate
(
    id SERIAL PRIMARY KEY,
    user_id int not null,
    rate_id int not null
);

drop table film_rate;
create table film_rate
(
    id SERIAL PRIMARY KEY,
    film_id int not null,
    rate_id int not null
);

drop table film_image;
create table film_image
(
    id SERIAL PRIMARY KEY,
    film_id int not null,
    image_id int not null
);

drop table user_image;
create table user_image
(
    id SERIAL PRIMARY KEY,
    user_id int not null,
    image_id int not null
);

create table user_film_rate
(
    id SERIAL PRIMARY KEY,
    user_id int not null,
    film_id int not null,
    rate_id int not null
);*/

