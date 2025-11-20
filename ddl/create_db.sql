CREATE TABLE restwebservices.canvas_kurs
(
    Kurskod  VARCHAR(6)   NOT NULL
        PRIMARY KEY,
    Kursnamn VARCHAR(255) NULL
);

CREATE TABLE restwebservices.canvas_kursuppgift
(
    Uppgift_nr   INT          NOT NULL
        PRIMARY KEY,
    Kurskod      VARCHAR(6)   NULL,
    Uppgiftsnamn VARCHAR(255) NULL,
    CONSTRAINT canvas_kursuppgift_ibfk_1
        FOREIGN KEY (Kurskod) REFERENCES restwebservices.canvas_kurs (Kurskod)
);

CREATE INDEX Kurskod
    ON restwebservices.canvas_kursuppgift (Kurskod);

CREATE TABLE restwebservices.canvas_student
(
    Användare VARCHAR(8)   NOT NULL
        PRIMARY KEY,
    Namn      VARCHAR(255) NULL
);

CREATE TABLE restwebservices.canvas_betyg
(
    betygID    INT        NOT NULL
        PRIMARY KEY,
    Uppgift_nr INT        NULL,
    Användare  VARCHAR(8) NULL,
    Betyg      VARCHAR(2) NULL,
    CONSTRAINT canvas_betyg_ibfk_1
        FOREIGN KEY (Uppgift_nr) REFERENCES restwebservices.canvas_kursuppgift (Uppgift_nr),
    CONSTRAINT canvas_betyg_ibfk_2
        FOREIGN KEY (Användare) REFERENCES restwebservices.canvas_student (Användare)
);

CREATE INDEX Användare
    ON restwebservices.canvas_betyg (Användare);

CREATE INDEX Uppgift_nr
    ON restwebservices.canvas_betyg (Uppgift_nr);

CREATE TABLE restwebservices.epok_kurs
(
    Kurskod VARCHAR(6) NOT NULL
        PRIMARY KEY
);

CREATE TABLE restwebservices.epok_modul
(
    Kurskod     VARCHAR(6)            NOT NULL,
    Modulkod    INT                   NOT NULL,
    Modulnamn   VARCHAR(255)          NOT NULL,
    Betygsskala ENUM ('U', 'G', 'VG') NOT NULL,
    CONSTRAINT epok_modul_ibfk_1
        FOREIGN KEY (Kurskod) REFERENCES restwebservices.epok_kurs (Kurskod)
);

CREATE INDEX Kurskod
    ON restwebservices.epok_modul (Kurskod);

CREATE TABLE restwebservices.ladok_resultat
(
    Personnummer VARCHAR(12) NOT NULL,
    Kurskod      VARCHAR(6)  NOT NULL,
    Modulkod     INT         NOT NULL,
    Datum        DATE        NULL,
    Betyg        VARCHAR(2)  NULL,
    PRIMARY KEY (Personnummer, Kurskod, Modulkod)
);

CREATE TABLE restwebservices.studentits_student
(
    Personnummer VARCHAR(12) NOT NULL
        PRIMARY KEY,
    Användare    VARCHAR(8)  NULL
);

CREATE INDEX AK
    ON restwebservices.studentits_student (Användare);

