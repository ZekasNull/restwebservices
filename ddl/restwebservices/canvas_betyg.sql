create table restwebservices.canvas_betyg
(
    betygID    int        not null,
    Uppgift_nr int        null,
    Användare  varchar(8) null,
    Betyg      varchar(2) null
);

create index Användare
    on restwebservices.canvas_betyg (Användare);

create index Uppgift_nr
    on restwebservices.canvas_betyg (Uppgift_nr);

alter table restwebservices.canvas_betyg
    add primary key (betygID);

alter table restwebservices.canvas_betyg
    add constraint canvas_betyg_ibfk_1
        foreign key (Uppgift_nr) references restwebservices.canvas_kursuppgift (Uppgift_nr);

alter table restwebservices.canvas_betyg
    add constraint canvas_betyg_ibfk_2
        foreign key (Användare) references restwebservices.canvas_student (Användare);

