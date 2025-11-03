create table canvas_kurs
(
    Kurskod  varchar(6)   not null
        primary key,
    Kursnamn varchar(255) null
);

create table canvas_kursuppgift
(
    Uppgift_nr   int          not null
        primary key,
    Kurskod      varchar(6)   null,
    Uppgiftsnamn varchar(255) null,
    constraint canvas_kursuppgift_ibfk_1
        foreign key (Kurskod) references canvas_kurs (Kurskod)
);

create index Kurskod
    on canvas_kursuppgift (Kurskod);

create table canvas_student
(
    Användare varchar(8)   not null
        primary key,
    Namn      varchar(255) null
);

create table canvas_betyg
(
    betygID    int        not null
        primary key,
    Uppgift_nr int        null,
    Användare  varchar(8) null,
    Betyg      varchar(2) null,
    constraint canvas_betyg_ibfk_1
        foreign key (Uppgift_nr) references canvas_kursuppgift (Uppgift_nr),
    constraint canvas_betyg_ibfk_2
        foreign key (Användare) references canvas_student (Användare)
);

create index Användare
    on canvas_betyg (Användare);

create index Uppgift_nr
    on canvas_betyg (Uppgift_nr);

create table epok_kurs
(
    Kurskod varchar(6) not null
        primary key
);

create table epok_modul
(
    Kurskod     varchar(6)   null,
    Modulkod    int          null,
    Modulnamn   varchar(255) null,
    Betygsskala json         null,
    constraint epok_modul_ibfk_1
        foreign key (Kurskod) references epok_kurs (Kurskod)
);

create index Kurskod
    on epok_modul (Kurskod);

create table ladok_resultat
(
    Personnummer varchar(12) not null,
    Kurskod      varchar(6)  not null,
    Modulkod     int         not null,
    Datum        date        null,
    Betyg        varchar(2)  null,
    primary key (Personnummer, Kurskod, Modulkod)
);

create table studentits_student
(
    Personnummer varchar(12) not null
        primary key,
    Användare    varchar(8)  null
);

create index AK
    on studentits_student (Användare);

