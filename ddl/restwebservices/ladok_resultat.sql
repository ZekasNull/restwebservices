create table restwebservices.ladok_resultat
(
    Personnummer varchar(12) not null,
    Kurskod      varchar(6)  not null,
    Modulkod     int         not null,
    Datum        date        null,
    Betyg        varchar(2)  null
);

alter table restwebservices.ladok_resultat
    add primary key (Personnummer, Kurskod, Modulkod);

