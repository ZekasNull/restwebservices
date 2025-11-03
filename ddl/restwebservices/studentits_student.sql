create table restwebservices.studentits_student
(
    Personnummer varchar(12) not null,
    Användare    varchar(8)  null
);

create index AK
    on restwebservices.studentits_student (Användare);

alter table restwebservices.studentits_student
    add primary key (Personnummer);

