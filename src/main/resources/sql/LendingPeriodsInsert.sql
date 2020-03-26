Drop Table if exists lending_period;
Create Table lending_period
(
    id         SERIAL,
    start_date date,
    end_date   date,
    id_book    integer,
    Primary KEY (id),
    FOREIGN KEY (id_book) REFERENCES book (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO lending_period (id_Book, start_date, end_date)
VALUES (1, '2019-09-30', '2019-10-30'),
       (1, '2019-11-30', '2019-12-30'),
       (1, '2020-01-01', '2020-02-01'),
       (1, '2020-03-01', '2020-04-01'),
       (2, '2020-05-01', '2020-06-01'),
       (2, '2020-07-01', '2020-08-01'),
       (2, '2020-09-01', '2020-10-01'),
       (3, '2020-11-01', '2020-12-01'),
       (3, '2021-01-01', '2020-02-01'),
       (3, '2021-03-01', '2020-04-01');
