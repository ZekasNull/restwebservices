create table restwebservices.epok_modul
(
    Kurskod     varchar(6)   null,
    Modulkod    int          null,
    Modulnamn   varchar(255) null,
    Betygsskala json         null
);

create index Kurskod
    on restwebservices.epok_modul (Kurskod);

alter table restwebservices.epok_modul
    add constraint epok_modul_ibfk_1
        foreign key (Kurskod) references restwebservices.epok_kurs (Kurskod);

