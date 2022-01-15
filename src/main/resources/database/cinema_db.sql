DROP TABLE users;
DROP TABLE hall;
DROP TABLE n_row;
DROP TABLE screening;
DROP TABLE movie;
DROP TABLE ticket;

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
    seat_capacity INT NOT NULL
);

CREATE TABLE screening (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    hall INT NOT NULL ,
    movie_id INT NOT NULL ,
    time DATE NOT NULL ,
    base_cost INT NOT NULL ,
    CONSTRAINT scr_movie FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE
);

CREATE TABLE ticket (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    screening INT NOT NULL ,
    num_row INT NOT NULL ,
    seat INT NOT NULL ,
    cost INT NOT NULL
);

INSERT INTO users VALUES (1, 'admin1610', '$2a$12$odumr60QQXCkeA/TOhvJZ.GHC5CZO8H4BRRlZSIzrX.0OhrfDpJwO', 'ADMIN');
INSERT INTO users VALUES (2, 'testUser', '$2a$12$odumr60QQXCkeA/TOhvJZ.GHC5CZO8H4BRRlZSIzrX.0OhrfDpJwO', 'USER');

INSERT INTO movie VALUES (1, 'Фильм', 'Фільм', 'Movie', 'Это фильм', 'Це фільм', 'Yes, a movie indeed');

INSERT INTO hall valueS (1, 2, 'H-404');

INSERT INTO n_row values (1, 1, 1, 5);
INSERT INTO n_row values (2, 1, 2, 6);

INSERT INTO screening VALUE (1, 1, 1, '2022-11-05', 10000);