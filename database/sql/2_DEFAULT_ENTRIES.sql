INSERT INTO images (id, title,type, image_data)
VALUES
    (1, 'Image 1','png', NULL),
    (2, 'Image 2','png', NULL),
    (3, 'Image 3','jpeg', NULL),
    (4, 'Image 4','png', NULL),
    (5, 'Image 5','jpeg', NULL);


INSERT INTO users (id, first_name, last_name, birthdate, role, email, sex, password, image_id)
VALUES
    (1, 'John', 'Doe', '1990-05-15', 'ADMIN', 'john@example.com', 'Male', 'password123', 1),
    (2, 'Jane', 'Smith', '1985-07-28', 'USER', 'jane@example.com', 'Female', 'securepass', 2),
    (3, 'Michael', 'Johnson', '1993-02-10', 'USER', 'michael@example.com', 'Male', 'mypass123', 3),
    (4, 'Emily', 'Davis', '1988-12-03', 'USER', 'emily@example.com', 'Female', 'password456', 4),
    (5, 'David', 'Wilson', '1992-09-21', 'ADMIN', 'david@example.com', 'Male', 'mypassword', 5);

INSERT INTO films (id, title, author, out_date, time, category, image_id)
VALUES
    (1, 'The Shawshank Redemption', 'Frank Darabont', '1994-09-23', 120, ARRAY['ACTION', 'DRAMA'], 1),
    (2, 'Forrest Gump', 'Robert Zemeckis', '1994-07-06', 90, ARRAY['DRAMA'], 2),
    (3, 'Pulp Fiction', 'Quentin Tarantino', '1994-10-14', 110, ARRAY['THRILLER', 'MYSTERY'], 3),
    (4, 'Inception', 'Christopher Nolan', '2010-07-16', 105, ARRAY['SCIENCE_FICTION', 'ADVENTURE'], 4),
    (5, 'The Dark Knight', 'Christopher Nolan', '2008-07-18', 140, ARRAY['CRIME', 'MYSTERY'], 5);

INSERT INTO rates (id,name, note, summary, detail_summary, film_id, user_id)
VALUES
    (1,'Excellent', 5.0, 'Must watch!', 'Outstanding performances...', 1, 1),
    (2,'Good', 4.0, 'Highly recommended', 'A gripping story...', 2, 2),
    (3,'Average', 3.0, 'Decent film', 'Has its moments...', 1, 3),
    (4,'Very Good', 4.5, 'Recommended for moviegoers', 'A superhero masterpiece...', 3, 2),
    (5,'Excellent', 5.0, 'A cinematic classic', 'Memorable dialogues...', 2, 4);

