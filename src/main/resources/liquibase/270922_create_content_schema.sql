--liquibase formatted sql

--changeset mariia.pal:create_content_schema
-----------------------------

DROP SCHEMA IF EXISTS content CASCADE;
CREATE SCHEMA content;