create table restwebservices.canvas_kursuppgift
(
    Uppgift_nr   int          not null,
    Kurskod      varchar(6)   null,
    Uppgiftsnamn varchar(255) null
);

create index Kurskod
    on restwebservices.canvas_kursuppgift (Kurskod);

alter table restwebservices.canvas_kursuppgift
    add primary key (Uppgift_nr);

alter table restwebservices.canvas_kursuppgift
    add constraint canvas_kursuppgift_ibfk_1
        foreign key (Kurskod) references restwebservices.canvas_kurs (Kurskod);

