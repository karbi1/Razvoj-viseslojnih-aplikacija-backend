DROP TABLE IF EXISTS nacionalnost CASCADE;
DROP TABLE IF EXISTS liga CASCADE ;
DROP TABLE IF EXISTS tim CASCADE ;
DROP TABLE IF EXISTS igrac CASCADE;

CREATE TABLE nacionalnost
(
	id integer not null,
	naziv varchar(100),
	skracenica varchar(50)
);

CREATE TABLE liga
(
	id integer not null,
	naziv varchar(100),
	oznaka varchar(50)
);

CREATE TABLE tim
(
	id integer not null,
	naziv varchar(100),
	osnovan date,
	sediste varchar(100),
	liga integer not null
);

CREATE TABLE igrac
(
	id integer not null,
	ime varchar(50),
	prezime varchar(50),
	broj_reg varchar(50),
	datum_rodjenja date,
	nacionalnost integer not null,
	tim integer not null
);

ALTER TABLE tim

	ADD CONSTRAINT pk_tim PRIMARY KEY(id);

ALTER TABLE nacionalnost

	ADD CONSTRAINT pk_nacionalnost PRIMARY KEY(id);

ALTER TABLE igrac

	ADD CONSTRAINT pk_igrac PRIMARY KEY(id);


ALTER TABLE liga

	ADD CONSTRAINT pk_liga PRIMARY KEY(id);
ALTER TABLE tim
	ADD CONSTRAINT fk_tim_liga FOREIGN KEY(liga) REFERENCES liga(id);
ALTER TABLE igrac
	ADD CONSTRAINT fk_igrac_tim FOREIGN KEY(tim) REFERENCES tim(id);
ALTER TABLE igrac
	ADD CONSTRAINT fk_igrac_nacionalnost FOREIGN KEY(nacionalnost) REFERENCES nacionalnost(id);
	
CREATE INDEX idxpk_liga ON liga(id);
CREATE INDEX idxpk_igrac ON igrac(id);
CREATE INDEX idxpk_nacionalnost ON nacionalnost(id);
CREATE INDEX idxpk_tim ON tim(id);

CREATE INDEX idxfk_igrac_nacionalnost ON igrac(nacionalnost);
CREATE INDEX idxfk_igrac_tim ON igrac(tim);
CREATE INDEX idxfk_tim_liga ON tim(liga);








CREATE SEQUENCE igrac_seq INCREMENT 1;
CREATE SEQUENCE liga_seq INCREMENT 1;
CREATE SEQUENCE nacionalnost_seq INCREMENT 1;
CREATE SEQUENCE tim_seq INCREMENT 1;


