-- Tabela: akcija ok
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (1, 'asteroid', 'spektroskopija', 5.6, 'km/s', 1, 1);
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (2, 'galaksija', 'radio talasi', 7.2, 'mag', 2, 2);
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (3, 'planeta', 'fotometrija', 8.3, 'ppm', 3, 3);
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (4, 'zvezda', 'astrometrija', 2.4, 'm/s', 4, 4);
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (5, 'planeta', 'radio talasi', 9.8, 'm/s', 5, 5);
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (6, 'kometa', 'spektroskopija', 4.7, 'km/s', 6, 6);
INSERT INTO akcija (idakc, naz, teh, rez, mer, posmatranje_idpos, vremenskiuslovi_idusl) VALUES (7, 'crna rupa', 'fotometrija', 6.1, 'mag', 7, 7);

-- Tabela: astronom ok
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (1, 'Petar', 'Petrovic', 'astrofizika', 10, '0641234567', 'petar@astronomija.rs', TO_DATE('1984-05-12', 'YYYY-MM-DD'));
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (2, 'Ana', 'Jovanovic', 'astrobiologija', 7, '0649876543', 'ana@astronomija.rs', TO_DATE('1990-08-24', 'YYYY-MM-DD'));
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (3, 'Marko', 'Markovic', 'kosmologija', 15, '0655671234', 'marko@astronomija.rs', TO_DATE('1978-11-19', 'YYYY-MM-DD'));
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (4, 'Milica', 'Nikolic', 'egzoplanete', 12, '0632349876', 'milica@astronomija.rs', TO_DATE('1985-03-09', 'YYYY-MM-DD'));
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (5, 'Jelena', 'Petrovic', 'astrofizika', 8, '0621112233', 'jelena@astronomija.rs', TO_DATE('1989-06-22', 'YYYY-MM-DD'));
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (6, 'Ivan', 'Jankovic', 'kosmologija', 20, '0613334455', 'ivan@astronomija.rs', TO_DATE('1975-09-30', 'YYYY-MM-DD'));
INSERT INTO astronom (idast, ime, prz, spec, isk, tel, eml, god) VALUES (7, 'Tamara', 'Stojanovic', 'astrobiologija', 5, '0605556677', 'tamara@astronomija.rs', TO_DATE('1995-12-15', 'YYYY-MM-DD'));

-- Tabela: eksperiment ok
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (1, 'Mjerenje radijalnih brzina', TO_DATE('2024-01-10', 'YYYY-MM-DD'), TO_DATE('2024-01-20', 'YYYY-MM-DD'), 'Mjerenje brzine kretanja egzoplaneta', 1, 1, NULL);
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (2, 'Spektroskopska analiza', TO_DATE('2024-02-15', 'YYYY-MM-DD'), TO_DATE('2024-02-28', 'YYYY-MM-DD'), 'Analiza svetlosnog spektra kometa', 2, 2, 1);
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (3, 'Polarizacija zvezda', TO_DATE('2024-03-05', 'YYYY-MM-DD'), TO_DATE('2024-03-18', 'YYYY-MM-DD'), 'Posmatranje polarizacije svetla zvezda', 3, 3, 2);
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (4, 'Astrometrijska merenja', TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-12', 'YYYY-MM-DD'), 'Precizna merenja pozicija zvezda', 4, 4, 3);
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (5, 'Posmatranje galaksija', TO_DATE('2024-05-10', 'YYYY-MM-DD'), TO_DATE('2024-05-25', 'YYYY-MM-DD'), 'Fotometrijsko posmatranje udaljenih galaksija', 5, 5, 4);
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (6, 'Proucavanje asteroida', TO_DATE('2024-06-01', 'YYYY-MM-DD'), TO_DATE('2024-06-15', 'YYYY-MM-DD'), 'Spektroskopsko posmatranje asteroida', 6, 6, 5);
INSERT INTO eksperiment (ideksp, naz, poc, kraj, opis, opservatorijum_idops, astronom_idast, eksperiment_ideksp) VALUES (7, 'Detekcija crnih rupa', TO_DATE('2024-07-10', 'YYYY-MM-DD'), TO_DATE('2024-07-30', 'YYYY-MM-DD'), 'Posmatranje sjaja u blizini crnih rupa', 7, 7, 6);

-- Tabela: infracrveni
--INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (1, 'CCD', 'da', 1000);
--INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (2, 'HgCdTe', 'ne', 1200);
INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (3, 'InGaAs', 'da', 800);
INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (4, 'CCD', 'ne', 1100);
--INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (5, 'HgCdTe', 'da', 950);
--INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (6, 'InGaAs', 'ne', 1050);
--INSERT INTO infracrveni (tel_idtel, infr_det, infr_zltprem, infr_rasp) VALUES (7, 'CCD', 'da', 1150);

-- Tabela: lokacija ok
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (1, 'Beograd', 'Srbija', 44.817, 20.457);
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (2, 'Novi Sad', 'Srbija', 45.267, 19.833);
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (3, 'Subotica', 'Srbija', 46.098, 19.667);
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (4, 'Niš', 'Srbija', 43.321, 21.895);
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (5, 'Kragujevac', 'Srbija', 44.012, 20.911);
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (6, 'Zrenjanin', 'Srbija', 45.378, 20.386);
INSERT INTO lokacija (idlok, grd, drz, gvis, gsir) VALUES (7, 'Čačak', 'Srbija', 43.891, 20.349);

-- Tabela: nebeskiobjekat ok
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (1, 'Venera', 'planeta', 0.72, -4.5, 337.620, -10.486);
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (2, 'Halley', 'kometa', 0.56, 5.0, 139.3, -8.0);
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (3, 'Betelgeuse', 'zvezda', 642, 0.42, 88.7929, 7.4071);
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (4, 'Andromeda', 'galaksija', 778, 3.4, 10.6833, 41.2692);
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (5, 'Sagittarius A*', 'crna rupa', 26000, -26.0, 266.41683, -29.00781);
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (6, 'Ceres', 'asteroid', 2.77, 3.34, 291.815, 59.74);
INSERT INTO nebeskiobjekat (idnebobj, ime, tip, udalj, mag, ra, dec) VALUES (7, 'Luna', 'mesec', 0.00257, -12.74, 0, 0);

-- Tabela: ops_ast ok
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (1, 1);
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (2, 2);
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (3, 3);
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (4, 4);
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (5, 5);
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (6, 6);
INSERT INTO ops_ast (opservatorijum_idops, astronom_idast) VALUES (7, 7);

-- Tabela: ops_tel ok
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (1, 1);
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (2, 2);
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (3, 3);
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (4, 4);
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (5, 5);
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (6, 6);
INSERT INTO ops_tel (opservatorijum_idops, teleskop_tel_idtel) VALUES (7, 7);

-- Tabela: opservatorijum ok
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (1, 'Astronomska opservatorija Beograd', 250, TO_DATE('1887-06-01', 'YYYY-MM-DD'), 'drzava', 1);
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (2, 'Opservatorija Novi Sad', 120, TO_DATE('1955-10-12', 'YYYY-MM-DD'), 'univerzitet', 2);
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (3, 'Opservatorija Subotica', 100, TO_DATE('1975-03-25', 'YYYY-MM-DD'), 'drzava', 3);
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (4, 'Opservatorija Niš', 300, TO_DATE('2000-09-18', 'YYYY-MM-DD'), 'vlasnik', 4);
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (5, 'Opservatorija Kragujevac', 180, TO_DATE('1990-07-10', 'YYYY-MM-DD'), 'drzava', 5);
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (6, 'Opservatorija Zrenjanin', 150, TO_DATE('1982-12-05', 'YYYY-MM-DD'), 'univerzitet', 6);
INSERT INTO opservatorijum (idops, naz, vis, dat, vlas, lokacija_idlok) VALUES (7, 'Opservatorija Čačak', 220, TO_DATE('1998-11-23', 'YYYY-MM-DD'), 'vlasnik', 7);

-- Tabela: opticki
INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (1, 'parabolicno', 1200, 4.5);
--INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (2, 'sferno', 1000, 3.8);
--INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (3, 'asfericno', 1500, 5.2);
--INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (4, 'hiperbolicko', 1100, 4.2);
INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (5, 'elipticno', 950, 3.7);
INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (6, 'parabolicno', 1300, 4.8);
--INSERT INTO opticki (tel_idtel, opt_ogl, opt_maxuve, opt_ziza) VALUES (7, 'sferno', 1400, 5.0);

-- Tabela: posmatranje ok
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (1, TO_DATE('2024-01-15', 'YYYY-MM-DD'), TO_DATE('2024-01-16', 'YYYY-MM-DD'), 1, 1, 1);
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (2, TO_DATE('2024-02-10', 'YYYY-MM-DD'), TO_DATE('2024-02-11', 'YYYY-MM-DD'), 2, 2, 2);
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (3, TO_DATE('2024-03-15', 'YYYY-MM-DD'), TO_DATE('2024-03-16', 'YYYY-MM-DD'), 3, 3, 3);
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (4, TO_DATE('2024-04-12', 'YYYY-MM-DD'), TO_DATE('2024-04-13', 'YYYY-MM-DD'), 4, 4, 4);
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (5, TO_DATE('2024-05-20', 'YYYY-MM-DD'), TO_DATE('2024-05-21', 'YYYY-MM-DD'), 5, 5, 5);
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (6, TO_DATE('2024-06-25', 'YYYY-MM-DD'), TO_DATE('2024-06-26', 'YYYY-MM-DD'), 6, 6, 6);
INSERT INTO posmatranje (idpos, poc, kraj, ops_tel_opservatorijum_idops, ops_tel_teleskop_tel_idtel, nebeskiobjekat_idnebobj) VALUES (7, TO_DATE('2024-07-30', 'YYYY-MM-DD'), TO_DATE('2024-07-31', 'YYYY-MM-DD'), 7, 7, 7);

--INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (1, 2.4, 'parabolicna');
INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (2, 5.8, 'plocasta');
--INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (3, 3.2, 'sfericna');
--INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (4, 4.5, 'parabolicna');
--INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (5, 6.0, 'plocasta');
--INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (6, 7.1, 'sfericna');
INSERT INTO radio (tel_idtel, rad_frek, rad_ant) VALUES (7, 8.3, 'parabolicna');

--Tabela: teleskop ok
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (1, 'Hubble', 0.05, 2.4, 2.4, 0.95, 'OPT');
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (2, 'VLA', 0.25, 3.0, 100.0, 0.85, 'RAD');
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (3, 'ALMA', 0.03, 12.0, 12.0, 0.99, 'INFR');
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (4, 'SOFIA', 0.1, 2.5, 3.0, 0.90, 'INFR');
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (5, 'TESS', 0.07, 3.0, 3.0, 0.80, 'OPT');
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (6, 'Keck', 0.05, 10.0, 10.0, 0.98, 'OPT');
INSERT INTO teleskop (tel_idtel, tel_naz, tel_rezl, tel_prec, tel_dmt, tel_kvalsl, tel_type) VALUES (7, 'JAXA', 0.2, 2.5, 5.0, 0.93, 'RAD');

--Tabela: vremenskiuslovi ok
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (1, TO_DATE('2024-12-21', 'YYYY-MM-DD'), 12.5, 80, 5.0, 30, 1);
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (2, TO_DATE('2024-12-22', 'YYYY-MM-DD'), 10.0, 75, 4.0, 40, 2);
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (3, TO_DATE('2024-12-23', 'YYYY-MM-DD'), 9.0, 90, 6.0, 70, 3);
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (4, TO_DATE('2024-12-24', 'YYYY-MM-DD'), 8.0, 85, 8.0, 60, 4);
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (5, TO_DATE('2024-12-25', 'YYYY-MM-DD'), 6.0, 70, 7.0, 50, 5);
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (6, TO_DATE('2024-12-26', 'YYYY-MM-DD'), 15.0, 65, 4.5, 20, 6);
INSERT INTO vremenskiuslovi (idusl, dat, temp, vlaz, brzvet, obl, akcija_idakc) VALUES (7, TO_DATE('2024-12-27', 'YYYY-MM-DD'), 14.0, 80, 3.5, 35, 7);

--Tabela: r_ast_eksp ok
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (1, 1);
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (2, 2);
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (3, 3);
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (4, 4);
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (5, 5);
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (6, 6);
INSERT INTO r_ast_eksp (eksperiment_ideksp, astronom_idast) VALUES (7, 7);

--Tabela: r_ops_ast_pos ok
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (1, 1, 1);
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (2, 2, 2);
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (3, 3, 3);
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (4, 4, 4);
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (5, 5, 5);
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (6, 6, 6);
INSERT INTO r_ops_ast_pos (ops_ast_idops, ops_ast_astronom_idast, posmatranje_idpos) VALUES (7, 7, 7);

