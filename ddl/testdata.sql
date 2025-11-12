/*
Innehåller:
5 studenter
5 kurser
15 moduler (3 för varje kurs)
75 betyg (5 för varje modul/kursuppgift)

3 registrerade betyg i Ladok.

Per pseudoschema blir detta
Canvas:
    5 studenter utan personnummer
    5 kurser
    15 moduler (3 för varje kurs) utan modulkod
    75 betyg (5 för varje modul/kursuppgift)

EPoK
    5 kurser
    15 moduler med modulkod

studentits_student
    5 studenter med personnummer

Ladok
    3 registrerade betyg (alla Gandalfs betyg för D0666N)

*/

-- canvas

-- canvas users
INSERT INTO restwebservices.canvas_student (Användare, Namn)
VALUES
    ('gangre-5', 'Gandalf Grey'),
    ('araele-5', 'Aragorn Elessar'),
    ('legthr-5', 'Legolas Thranduilion'),
    ('frobag-5', 'Frodo Baggins'),
    ('bilbag-5', 'Bilbo Baggins');

-- canvas courses
INSERT INTO restwebservices.canvas_kurs (Kurskod, Kursnamn)
VALUES
    ('D0666N', 'Ockultism och demonologi'),
    ('M1111A', 'Matematik för magiker'),
    ('P2222B', 'Praktisk potionbryggning'),
    ('C3333D', 'Cirkelritualer och besvärjelser'),
    ('T4444E', 'Trollformler och förbannelser');

-- canvas kursuppgifter
INSERT INTO restwebservices.canvas_kursuppgift (Uppgift_nr, Kurskod, Uppgiftsnamn)
VALUES
    (1, 'D0666N', 'Examination'),
    (2, 'D0666N', 'Uppgift 1'),
    (3, 'D0666N', 'Uppgift 2'),
    (4, 'M1111A', 'Examination'),
    (5, 'M1111A', 'Uppgift 1'),
    (6, 'M1111A', 'Uppgift 2'),
    (7, 'P2222B', 'Examination'),
    (8, 'P2222B', 'Uppgift 1'),
    (9, 'P2222B', 'Uppgift 2'),
    (10, 'C3333D', 'Examination'),
    (11, 'C3333D', 'Uppgift 1'),
    (12, 'C3333D', 'Uppgift 2'),
    (13, 'T4444E', 'Examination'),
    (14, 'T4444E', 'Uppgift 1'),
    (15, 'T4444E', 'Uppgift 2');

-- canvas grades
INSERT INTO restwebservices.canvas_betyg (betygID, Uppgift_nr, Användare, Betyg)
VALUES
    (1, 1, 'gangre-5', 'VG'),
    (2, 1, 'araele-5', 'G'),
    (3, 1, 'legthr-5', 'VG'),
    (4, 1, 'frobag-5', 'VG'),
    (5, 1, 'bilbag-5', 'G'),
    (6, 2, 'gangre-5', 'G'),
    (7, 2, 'araele-5', 'U'),
    (8, 2, 'legthr-5', NULL),
    (9, 2, 'frobag-5', 'G'),
    (10, 2, 'bilbag-5', 'VG'),
    (11, 3, 'gangre-5', 'VG'),
    (12, 3, 'araele-5', NULL),
    (13, 3, 'legthr-5', NULL),
    (14, 3, 'frobag-5', NULL),
    (15, 3, 'bilbag-5', 'G'),
    (16, 4, 'gangre-5', 'VG'),
    (17, 4, 'araele-5', 'G'),
    (18, 4, 'legthr-5', 'VG'),
    (19, 4, 'frobag-5', 'VG'),
    (20, 4, 'bilbag-5', 'G'),
    (21, 5, 'gangre-5', 'G'),
    (22, 5, 'araele-5', 'U'),
    (23, 5, 'legthr-5', NULL),
    (24, 5, 'frobag-5', 'G'),
    (25, 5, 'bilbag-5', 'VG'),
    (26, 6, 'gangre-5', 'VG'),
    (27, 6, 'araele-5', 'G'),
    (28, 6, 'legthr-5', 'VG'),
    (29, 6, 'frobag-5', 'VG'),
    (30, 6, 'bilbag-5', 'G'),
    (31, 7, 'gangre-5', 'G'),
    (32, 7, 'araele-5', 'U'),
    (33, 7, 'legthr-5', NULL),
    (34, 7, 'frobag-5', 'G'),
    (35, 7, 'bilbag-5', 'VG'),
    (36, 8, 'gangre-5', 'VG'),
    (37, 8, 'araele-5', 'G'),
    (38, 8, 'legthr-5', 'VG'),
    (39, 8, 'frobag-5', 'VG'),
    (40, 8, 'bilbag-5', 'G'),
    (41, 9, 'gangre-5', 'G'),
    (42, 9, 'araele-5', 'U'),
    (43, 9, 'legthr-5', NULL),
    (44, 9, 'frobag-5', 'G'),
    (45, 9, 'bilbag-5', 'VG'),
    (46, 10, 'gangre-5', 'VG'),
    (47, 10, 'araele-5', 'G'),
    (48, 10, 'legthr-5', 'VG'),
    (49, 10, 'frobag-5', 'VG'),
    (50, 10, 'bilbag-5', 'G'),
    (51, 11, 'gangre-5', 'G'),
    (52, 11, 'araele-5', 'U'),
    (53, 11, 'legthr-5', NULL),
    (54, 11, 'frobag-5', 'G'),
    (55, 11, 'bilbag-5', 'VG'),
    (56, 12, 'gangre-5', 'VG'),
    (57, 12, 'araele-5', 'G'),
    (58, 12, 'legthr-5', NULL),
    (59, 12, 'frobag-5', 'VG'),
    (60, 12, 'bilbag-5', 'VG'),
    (61, 13, 'gangre-5', 'VG'),
    (62, 13, 'araele-5', NULL),
    (63, 13, 'legthr-5', NULL),
    (64, 13, 'frobag-5', NULL),
    (65, 13, 'bilbag-5', 'G'),
    (66, 14, 'gangre-5', 'VG'),
    (67, 14, 'araele-5', 'G'),
    (68, 14, 'legthr-5', 'VG'),
    (69, 14, 'frobag-5', 'VG'),
    (70, 14, 'bilbag-5', 'G'),
    (71, 15, 'gangre-5', 'G'),
    (72, 15, 'araele-5', 'U'),
    (73, 15, 'legthr-5', NULL),
    (74, 15, 'frobag-5', 'G'),
    (75, 15, 'bilbag-5', 'VG');

-- studentits

-- studentits students
INSERT INTO restwebservices.studentits_student (Användare, Personnummer)
VALUES
    ('gangre-5', '136201012468'),
    ('araele-5', '293103011239'),
    ('legthr-5', '287907157891'),
    ('frobag-5', '296809223457'),
    ('bilbag-5', '287912103579');

-- epok kurskoder
INSERT INTO restwebservices.epok_kurs (Kurskod)
VALUES
    ('D0666N'),
    ('M1111A'),
    ('P2222B'),
    ('C3333D'),
    ('T4444E');

-- epok kursmoduler
INSERT INTO restwebservices.epok_modul (Kurskod, Modulkod, Modulnamn, Betygsskala)
VALUES
    ('D0666N', 1, 'Examination', 'VG'),
    ('D0666N', 2, 'Uppgift 1', 'G'),
    ('D0666N', 3, 'Uppgift 2', 'G'),
    ('M1111A', 4, 'Examination', 'VG'),
    ('M1111A', 5, 'Uppgift 1', 'G'),
    ('M1111A', 6, 'Uppgift 2', 'G'),
    ('P2222B', 7, 'Examination', 'VG'),
    ('P2222B', 8, 'Uppgift 1', 'G'),
    ('P2222B', 9, 'Uppgift 2', 'G'),
    ('C3333D', 10, 'Examination', 'VG'),
    ('C3333D', 11, 'Uppgift 1', 'G'),
    ('C3333D', 12, 'Uppgift 2', 'G'),
    ('T4444E', 13, 'Examination', 'VG'),
    ('T4444E', 14, 'Uppgift 1', 'G'),
    ('T4444E', 15, 'Uppgift 2', 'G');


-- ladok
INSERT INTO restwebservices.ladok_resultat (Personnummer, Kurskod, Modulkod, Datum, Betyg)
VALUES
    (136201012468, 'D0666N', 1, '2025-11-11', 'VG'),
    (136201012468, 'D0666N', 2, '2025-11-11', 'VG'),
    (136201012468, 'D0666N', 3, '2025-11-11', 'VG');
