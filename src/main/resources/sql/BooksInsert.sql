Drop Table if exists book;
Create Table book
(
    id        SERIAL,
    title     varchar,
    author    varchar,
    isbn      varchar,
    publisher varchar,
    location  varchar,
    available varchar,
    Primary KEY (id)
);
INSERT INTO book (title, author, isbn, publisher, location, available)
VALUES ('A Knowledge Management Framework That Supports Evolution of Configurable Products', 'Thorsten Krebs', 'ISBN 9783-941216-00-6', 'Rhombos Verlag',
        'Buschhöhe 2', 'Ja'),
       ('Actionable Agile Metrics for Predictability: An Introduction', 'Daniel S. Vacanti ', 'ISBN13 9780986436338', 'ActionableAgile Press', 'Buschhöhe 2',
        'Nein'),
       ('Agile Testing / Der agile Weg zur Qualität', 'Baumgartner, Manfred; Klonk, Martin; Pichler, Helmut; Seidl, Richard; Tanczos, Siegfried',
        '978-3446431942', 'Carl Hanser Verlag', 'Buschhöhe 2', 'Ja');