INSERT INTO "user_role" ("id", "role") VALUES (1, 1), (2, 0);
INSERT INTO "user_detail" ("id", "email", "name", "password", "role_id")
VALUES (1, 'admin@admin.pl', 'admin', '$2a$10$gDGchQb7dIzgCJQAH04SOerViY9ua7X.mAd8VfNDZc8qp4vpXMy3u', 1),
  (2, 'user@user.pl', 'user', '$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC', 2);

--passwords : user, admin

--

INSERT INTO "payment" ("id", "active", "counter_type", "description", "end_date", "name", "price", "start_date", "type", "unit")
VALUES (6, E'true', 3, E'woda ciepła', NULL, E'Woda ciepła', 10, E'2016-03-01', 1, E'm^3'),
  (7, E'true', NULL, E'opłata za ścieki', NULL, E'Ścieki', 50, E'2016-03-01', 0, E''),
  (8, E'true', NULL, E'opłata za domofon', NULL, E'Domofon', 25, E'2016-03-01', 0, E''),
  (9, E'true', 0, E'opłata za gaz', NULL, E'Gaz', 1, E'2016-03-01', 1, E'm^3'),
  (10, E'true', 1, E'Opłata za prąd', NULL, E'Prąd', 5, E'2016-03-01', 1, E'kW'),
  (11, E'true', NULL, E'Opłata za śmieci', NULL, E'Śmieci', 25, E'2016-03-01', 0, E''),
  (5, E'true', 2, E'Woda zimna', NULL, E'Woda zimna', 5, E'2016-03-01', 1, E'm^3');