INSERT INTO users (first_name, last_name, birthdate, role, email, sex, password)
VALUES
    ('John', 'Doe', '1990-05-15', 'ADMIN', 'john@example.com', 'Male', 'password123'),
    ('Jane', 'Smith', '1985-07-28', 'USER', 'jane@example.com', 'Female', 'securepass'),
    ('Michael', 'Johnson', '1993-02-10', 'USER', 'michael@example.com', 'Male', 'mypass123'),
    ('Emily', 'Davis', '1988-12-03', 'USER', 'emily@example.com', 'Female', 'password456'),
    ('David', 'Wilson', '1992-09-21', 'ADMIN', 'david@example.com', 'Male', 'mypassword');

/*INSERT INTO films (name, author, out_date, category, image, description)
VALUES
    ('The Shawshank Redemption', 'Frank Darabont', '1994-09-23', 'Drama', NULL, 'A story of hope...'),
    ('Inception', 'Christopher Nolan', '2010-07-16', 'Science Fiction', NULL, 'A mind-bending thriller'),
    ('Forrest Gump', 'Robert Zemeckis', '1994-07-06', 'Drama', NULL, 'Life is like a box...'),
    ('The Dark Knight', 'Christopher Nolan', '2008-07-18', 'Action', NULL, 'Why so serious?'),
    ('Pulp Fiction', 'Quentin Tarantino', '1994-10-14', 'Crime', NULL, 'The path of the...');*/

INSERT INTO films (title, author, out_date, time, category, user_id, image_id)
VALUES
    ('The Shawshank Redemption', 'Frank Darabont', '1994-09-23', 120, ARRAY['Action', 'Drama'], 1, 1),
    ('Forrest Gump', 'Robert Zemeckis', '1994-07-06', 90, ARRAY['Drama'], 2, 2),
    ('Pulp Fiction', 'Quentin Tarantino', '1994-10-14', 110, ARRAY['Thriller', 'Mystery'], 3, 3),
    ('Inception', 'Christopher Nolan', '2010-07-16', 105, ARRAY['Science Fiction', 'Adventure'], 4, 4),
    ('The Dark Knight', 'Christopher Nolan', '2008-07-18', 140, ARRAY['Crime', 'Mystery'], 5, 5);

INSERT INTO rates (name, note, summary, detail_summary, film_id, user_id)
VALUES
    ('Excellent', 5.0, 'Must watch!', 'Outstanding performances...', 1, 1),
    ('Good', 4.0, 'Highly recommended', 'A gripping story...', 2, 2),
    ('Average', 3.0, 'Decent film', 'Has its moments...', 1, 3),
    ('Very Good', 4.5, 'Recommended for moviegoers', 'A superhero masterpiece...', 3, 2),
    ('Excellent', 5.0, 'A cinematic classic', 'Memorable dialogues...', 2, 4);

INSERT INTO images ( title, film_id,user_id)
VALUES
    ('Image 1', NULL,1),
    ('Image 2', NULL,2),
    ('Image 3', 3, NULL),
    ('Image 4', 4,NULL),
    ('Image 5', NULL,5);
