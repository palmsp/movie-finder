--liquibase formatted sql

--changeset mariia.pal:add_tags_and_genres
-----------------------------
INSERT into content.content_type(id, name)
values (1, 'movie'),
       (2, 'tv-series');

INSERT into content.genre(id, name, rus_name)
values (1, 'comedy', 'комедия'),
       (2, 'drama', 'драма'),
       (3, 'melodrama', 'меллодрама'),
       (4, 'thriller', 'триллер'),
       (5, 'action', 'боевик'),
       (6, 'fiction', 'фантастика'),
       (7, 'fantasy', 'фэнтези'),
       (8, 'detective', 'детектив'),
       (9, 'cartoon', 'мультфильм'),
       (10, 'horror', 'ужасы'),
       (11, 'adventure', 'приключения'),
       (12, 'war', 'военный'),
       (13, 'family', 'семейный'),
       (14, 'documentary', 'документальный'),
       (15, 'historical', 'исторический'),
       (16, 'biography', 'биография'),
       (17, 'crime', 'криминал'),
       (18, 'western', 'вестерн'),
       (19, 'short film', 'короткометражка'),
       (20, 'musical', 'мюзикл'),
       (21, 'feature film', 'художественный'),
       (22, 'sci-fi', 'научная фантастика'),
       (23, 'romance', 'фильм о любви'),
       (24, 'animation', 'анимационный фильм'),
       (25, 'sport', 'спорт');

INSERT into content.tag(name)
values ('осень'),
       ('зима'),
       ('весна'),
       ('лето'),
       ('природа'),
       ('горы'),
       ('море'),
       ('лес'),
       ('космос'),
       ('животные'),
       ('стихии'),
       ('собаки'),
       ('кошки'),
       ('рождество'),
       ('новый год'),
       ('хэллоуин'),
       ('добрый'),
       ('веселый'),
       ('семейный'),
       ('любовь'),
       ('семья'),
       ('дети'),
       ('подростки'),
       ('школа'),
       ('колледж'),
       ('универ'),
       ('выпускной'),
       ('работа'),
       ('фантастика'),
       ('экшн'),
       ('ретро'),
       ('гангстеры'),
       ('музыка'),
       ('американские комедии'),
       ('маленькие города'),
       ('наука'),
       ('приключение'),
       ('тайны');