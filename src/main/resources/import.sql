
--BLOCKS
INSERT INTO "public"."block" (id,city,flat_number,post_code,street) VALUES (1,'Łódź','1','90-111','Piotrkowska');
INSERT INTO "public"."block" (id,city,flat_number,post_code,street) VALUES (2,'Łódź','2','90-111','Piotrkowska');
INSERT INTO "public"."block" (id,city,flat_number,post_code,street) VALUES (3,'Łódź','3','90-111','Piotrkowska');
INSERT INTO "public"."block" (id,city,flat_number,post_code,street) VALUES (4,'Łódź','4','90-111','Piotrkowska');

--PLACE
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (1,'1',3,1);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (2,'2',3,1);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (3,'3',2,1);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (4,'4',2,1);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (5,'1',2,2);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (6,'2',2,2);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (7,'3',1,2);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (8,'4',1,2);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (9,'5',1,2);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (10,'1',4,3);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (11,'2',4,3);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (12,'3',4,3);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (13,'4',4,3);
INSERT INTO "public"."place" (id,apartment_number,roomers_count,block_id) VALUES (14,'1a',3,4);


--METER
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-1,0.0,{d '2012-06-06'},0.0,0.0,0.0,1);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-2,0.0,{d '2012-06-06'},0.0,0.0,0.0,2);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-3,0.0,{d '2012-06-06'},0.0,0.0,0.0,3);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-4,0.0,{d '2012-06-06'},0.0,0.0,0.0,4);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-5,0.0,{d '2012-06-06'},0.0,0.0,0.0,5);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-6,0.0,{d '2012-06-06'},0.0,0.0,0.0,6);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-7,0.0,{d '2012-06-06'},0.0,0.0,0.0,7);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-8,0.0,{d '2012-06-06'},0.0,0.0,0.0,8);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-9,0.0,{d '2012-06-06'},0.0,0.0,0.0,9);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-10,0.0,{d '2012-06-06'},0.0,0.0,0.0,10);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-11,0.0,{d '2012-06-06'},0.0,0.0,0.0,11);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-12,0.0,{d '2012-06-06'},0.0,0.0,0.0,12);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-13,0.0,{d '2012-06-06'},0.0,0.0,0.0,13);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-14,0.0,{d '2012-06-06'},0.0,0.0,0.0,14);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-15,1.15,{d '2016-03-01'},20.14,11.5,23.3,1);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-16,3.43,{d '2016-04-01'},23.13,15.22,40.23,1);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-17,10.0,{d '2016-05-01'},32.3,22.3,45.0,1);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-18,5.55,{d '2016-03-01'},15.43,2.34,3.55,2);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-19,10.44,{d '2016-04-01'},22.22,5.44,18.33,2);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-20,15.43,{d '2016-05-01'},25.44,15.54,23.3,2);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-21,2.32,{d '2016-03-01'},14.32,9.54,5.343,3);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-22,4.545,{d '2016-04-01'},3.54,9.534,3.43,3);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-23,5.66,{d '2016-05-01'},6.324,17.432,5.342,3);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-24,10.43,{d '2016-03-01'},4.56,13.545,4.34,4);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-25,15.0,{d '2016-04-01'},2.0,5.0,3.0,4);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-26,17.0,{d '2016-05-01'},8.0,6.0,24.0,4);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-27,3.0,{d '2016-03-02'},3.0,3.0,3.0,5);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-28,7.0,{d '2016-04-01'},7.0,7.0,7.0,5);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-29,11.0,{d '2016-05-01'},11.0,11.0,11.0,5);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-30,6.0,{d '2016-03-01'},6.0,6.0,6.0,6);
INSERT INTO "public"."meter" (id,cold_water,date,electricity,gas,hot_water,place_id) VALUES (-31,11.0,{d '2016-04-01'},11.0,11.0,11.0,6);

--USER
INSERT INTO "user_role" ("id", "role") VALUES (1, 1), (2, 0);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (1,'admin@admin.pl','admin','$2a$10$gDGchQb7dIzgCJQAH04SOerViY9ua7X.mAd8VfNDZc8qp4vpXMy3u',null,1);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (2,'user@user.pl','user','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',null,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (3,'user1@user.pl','user1','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',null,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (4,'user2@user.pl','user2','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',null,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (5,'user3@user.pl','user3','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',null,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (6,'user4@user.pl','user4','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',null,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (7,'user5@user.pl','user5','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',null,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (8,'user8@user.plp','user8','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',1,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (9,'user9@user.pl','user9','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',2,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (10,'user10@user.pl','user10','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',3,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (11,'user11@user.pl','user11','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',4,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (12,'user12@user.pl','user12','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC',5,2);
INSERT INTO "public"."user_detail" (id,email,name,password,place_id,role_id) VALUES (13,'user13@user.pl','user13','$2a$10$7SI1.AzyYQt2hjQjCkK2x.3E2a30UpyEI0KmFagyLyp3aHup1GwJC ',6,2);
--passwords : user, admin
