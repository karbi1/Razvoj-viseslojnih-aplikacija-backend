INSERT INTO "liga"("id", "naziv", "oznaka")
	values (1, 'Nacionalna kosarkaska asocijacija', 'NBA');

INSERT INTO "liga"("id", "naziv", "oznaka")
	values (2, 'Glavna liga bejzbola', 'MLB');

INSERT INTO "liga"("id", "naziv", "oznaka")
	values (3, 'Nacionalna hokejska liga', 'NHL');

INSERT INTO "liga"("id", "naziv", "oznaka")
	VALUES(4, 'National Football League', 'NFL');


INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
	values (1, 'Srbin', 'SRB');

INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
	values (2, 'Hrvat', 'CRO');

INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
	values (3, 'Amerikanac', 'USA');

INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
	values (4, 'Svedjanin', 'SWE');



INSERT INTO "tim"("id","naziv","osnovan","sediste","liga")
	values(1, 'Denver Nuggets', to_date('13.02.1933.', 'dd.mm.yyyy.'), 'Kolorado', 1);

INSERT INTO "tim"("id","naziv","osnovan","sediste","liga")
	VALUES(2, 'Denver Broncos', to_date('10.05.1930.', 'dd.mm.yyyy.'), 'Kolorado', 4);

INSERT INTO "tim"("id","naziv","osnovan","sediste","liga")
	VALUES(3, 'Toronto Maple Leafs', to_date('07.07.1940.', 'dd.mm.yyyy.'), 'Toronto', 3);

INSERT INTO "tim"("id","naziv","osnovan","sediste","liga")
	values(4, 'Los Angeles Dodgers' , to_date('24.10.1925.' ,'dd,mm.yyyy.'),'Kalifornija', 2);


INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
	VALUES(1, 'Nikola', 'Jokic', 'jik2s', to_date('19.02.1995.', 'dd.mm.yyyy.'), 1, 1);

INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
	VALUES(2, 'Rasmus', 'Sandin', 'sk5s1', to_date('05.02.2000.', 'dd.mm.yyyy.'), 4, 3);

INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
	VALUES(3, 'Drew', 'Lock', '32s1s', to_date('10.10.1996.', 'dd.mm.yyyy.'), 3, 4);

INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
	values(4, 'Chris', 'Taylor' ,'213s1', to_date('29.08.1990.', 'dd.mm.yyyy.'), 3, 2);

--TEST
INSERT INTO "liga"("id", "naziv", "oznaka")
	VALUES(-100, 'Test liga', 'TST');
INSERT INTO "nacionalnost"("id", "naziv", "skracenica")
	values (-100, 'Tester', 'TES');
INSERT INTO "tim"("id","naziv","osnovan","sediste","liga")
	values(-100, 'Tester Nuggets', to_date('13.02.1933.', 'dd.mm.yyyy.'), 'Kolorado', 1);

INSERT INTO "igrac"("id", "ime", "prezime", "broj_reg", "datum_rodjenja", "nacionalnost", "tim")
	values(-100, 'Test', 'Test' ,'213s1', to_date('29.08.1990.', 'dd.mm.yyyy.'), 1, 1);

