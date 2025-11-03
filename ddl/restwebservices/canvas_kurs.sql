create table restwebservices.canvas_kurs
(
    Kurskod  varchar(6)   not null,
    Kursnamn varchar(255) null
);

alter table restwebservices.canvas_kurs
    add primary key (Kurskod);

