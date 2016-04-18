INSERT INTO "user_role" ("id", "role") VALUES (1, 1), (2, 0);
INSERT INTO "user_detail" ("id", "email", "name", "password", "role_id")
VALUES (1, 'admin@admin.pl', 'admin', '$2a$10$gDGchQb7dIzgCJQAH04SOerViY9ua7X.mAd8VfNDZc8qp4vpXMy3u', 1),
  (2, 'user@user.pl', 'user', '$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC', 2);

--passwords : user, admin