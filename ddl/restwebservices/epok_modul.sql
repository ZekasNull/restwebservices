CREATE TABLE restwebservices.epok_modul
(
    Kurskod     VARCHAR(6)            NOT NULL,
    Modulkod    INT                   NOT NULL,
    Modulnamn   VARCHAR(255)          NOT NULL,
    Betygsskala ENUM ('U', 'G', 'VG') NOT NULL
);

CREATE INDEX Kurskod
    ON restwebservices.epok_modul (Kurskod);

ALTER TABLE restwebservices.epok_modul
    ADD CONSTRAINT epok_modul_ibfk_1
        FOREIGN KEY (Kurskod) REFERENCES restwebservices.epok_kurs (Kurskod);

