--liquibase formatted sql

--changeset mariia.pal:create_content_tables
-----------------------------

CREATE TABLE content.content
(
    id           BIGSERIAL,
    name         VARCHAR,
    description  TEXT,
    type_id      INTEGER,
    release_year INTEGER,
    rate         DOUBLE PRECISION,
    external_id  BIGINT,
    version      BIGINT,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE content.tag
(
    id   BIGSERIAL,
    name VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE content.genre
(
    id       BIGINT,
    name     VARCHAR,
    rus_name VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE content.content_type
(
    id   BIGSERIAL,
    name VARCHAR,
    PRIMARY KEY (id)
);

CREATE TABLE content.content_tag_link
(
    content_id BIGINT,
    tag_id     BIGINT,
    CONSTRAINT content_tag_link_pk PRIMARY KEY (content_id, tag_id),
    CONSTRAINT content_id_tag_fk FOREIGN KEY (content_id) REFERENCES content.content (id),
    CONSTRAINT tag_id_fk FOREIGN KEY (tag_id) REFERENCES content.tag (id)
);

CREATE TABLE content.content_genre_link
(
    content_id BIGINT,
    genre_id   BIGINT,
    CONSTRAINT content_genre_link_pk PRIMARY KEY (content_id, genre_id),
    CONSTRAINT content_id_genre_fk FOREIGN KEY (content_id) REFERENCES content.content (id),
    CONSTRAINT genre_id_fk FOREIGN KEY (genre_id) REFERENCES content.genre (id)
);

CREATE TABLE content.content_filter
(
    id             BIGINT,
    name           VARCHAR,
    description    TEXT,
    filter         jsonb,
    current_page   INTEGER,
    pages          INTEGER,
    total          INTEGER,
    limit_per_page INTEGER,
    version        BIGINT,
    PRIMARY KEY (id)
);