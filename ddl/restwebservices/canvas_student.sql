create table restwebservices.canvas_student
(
    Användare varchar(8)   not null,
    Namn      varchar(255) null
);

alter table restwebservices.canvas_student
    add primary key (Användare);

