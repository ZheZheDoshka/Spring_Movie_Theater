DROP TABLE users;
DROP TABLE ticket;
DROP TABLE screening;
DROP TABLE n_row;
DROP TABLE hall;
DROP TABLE movie;

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    username VARCHAR(32) NOT NULL UNIQUE ,
    password VARCHAR(250) NOT NULL ,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE movie (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    name_ru VARCHAR(250) NOT NULL ,
    name_ua VARCHAR(250) NOT NULL ,
    name_en VARCHAR(250) NOT NULL ,
    description_ru VARCHAR(250) ,
    description_ua VARCHAR(250) ,
    description_en VARCHAR(250)
);

CREATE TABLE hall (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    num_row INT NOT NULL,
    name VARCHAR(30)
);

CREATE TABLE n_row (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    hall INT NOT NULL ,
    number INT NOT NULL ,
    seat_capacity INT NOT NULL,
    CONSTRAINT r_hall FOREIGN KEY (hall) REFERENCES hall (id) ON DELETE CASCADE
);

CREATE TABLE screening (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    hall INT NOT NULL ,
    movie_id INT NOT NULL ,
    time DATETIME NOT NULL ,
    base_cost INT NOT NULL ,
    CONSTRAINT scr_movie FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE,
    CONSTRAINT scr_hall FOREIGN KEY (hall) REFERENCES hall (id) ON DELETE CASCADE
);

CREATE TABLE ticket (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    screening INT NOT NULL ,
    num_row INT NOT NULL ,
    seat INT NOT NULL ,
    cost INT NOT NULL ,
    user INT NOT NULL ,
    CONSTRAINT t_user FOREIGN KEY (user) REFERENCES users (id) ON DELETE CASCADE ,
    CONSTRAINT t_screening FOREIGN KEY (screening) REFERENCES screening (id) ON DELETE CASCADE ,
    CONSTRAINT t_row FOREIGN KEY (num_row) REFERENCES n_row (id) ON DELETE CASCADE
);

INSERT INTO users VALUES (1, 'admin1610', '$2a$12$odumr60QQXCkeA/TOhvJZ.GHC5CZO8H4BRRlZSIzrX.0OhrfDpJwO', 'ADMIN');
INSERT INTO users VALUES (2, 'testUser', '$2a$12$odumr60QQXCkeA/TOhvJZ.GHC5CZO8H4BRRlZSIzrX.0OhrfDpJwO', 'USER');

INSERT INTO movie VALUES (1, 'Фильм2', 'Фільм2', 'Movie2', 'Это фильм2', 'Це фільм2', 'Yes, a movie indeed2');
INSERT INTO movie VALUES (2, 'Фильм', 'Фільм', 'Movie', 'Это фильм', 'Це фільм', 'Yes, a movie indeed');

INSERT INTO hall valueS (1, 2, 'H-404');

INSERT INTO n_row values (1, 1, 0, 5);
INSERT INTO n_row values (2, 1, 1, 6);

INSERT INTO screening VALUE (1, 1, 1, '2022-11-05 10:05:00', 10000);
INSERT INTO screening VALUE (2, 1, 1, '2022-11-07 10:05:00', 10000);

INSERT INTO ticket VALUE (1,1, 1, 2, 100, 1);
INSERT INTO ticket VALUE (2,1, 2, 4, 100, 1);