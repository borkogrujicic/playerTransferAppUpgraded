INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');


INSERT INTO klub (id, naziv, budzet) VALUES (1, 'Real Madrid', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (2, 'Barcelona', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (3, 'Manchester United', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (4, 'Bayern Munich', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (5, 'Chelsea', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (6, 'Tottenham Hotspur', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (7, 'Atletico Madrid', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (8, 'Manchester City', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (9, 'Arsenal', 150000000);
INSERT INTO klub (id, naziv, budzet) VALUES (10, 'West Ham', 150000000);


INSERT INTO igrac (id, ime, pozicija, broj_dresa, datum, na_prodaju, klub_id)
			VALUES (1, 'Kun Aguero', 'Napadac', 10, '1988-07-14', false, 2);
			
INSERT INTO igrac (id, ime, pozicija, broj_dresa, datum, na_prodaju, klub_id)
			VALUES (2, 'Sergio Ramos', 'Odbrambeni', 4, '1988-07-14', true, 1);
			
INSERT INTO igrac (id, ime, pozicija, broj_dresa, datum, na_prodaju, klub_id)
			VALUES (3, 'Pol Pogba', 'Vezni', 10, '1988-07-14', true, 3);
			
			
