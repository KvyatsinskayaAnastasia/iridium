DELETE FROM RACE_ABILITY;

ALTER TABLE RACE_ABILITY ADD
id UUID NOT NULL;

insert into race_ability (id, bonus, race_id, ability_id)
values
('d6e97f49-1f3a-4b3c-b0b9-1c5e8b6c2d2a', 1, '3eeb7e9e-6bcd-4f72-bb62-5d00c8cb2b2d', 'd017b327-bb4a-4e16-ba9a-7d6cc0f02f1a'),
('e46c4c4e-8a3c-48f7-a7e7-3d8c5f0b1f1c', 1, '3eeb7e9e-6bcd-4f72-bb62-5d00c8cb2b2d', 'c3eb2aaf-2b24-48b4-a50a-6f636acfb798'),
('b0a0f6d5-2c9d-4ce3-a0e7-6c9f2f6b8e9e', 1, 'd8f73e72-1e80-4c79-a80b-62a4cda028b3', 'a5e9c494-9218-4f56-ab80-a594eed7b835'),
('c5ef06d4-d8bc-4a5f-b7ed-e0dcbf5c1a3b', 1, 'd8f73e72-1e80-4c79-a80b-62a4cda028b3', 'a19d2e78-4c5c-46e6-8a5f-74d6b38b8dc4'),
('f8bc7b6a-c69a-46b8-bcc7-c9eae2ec5ab5', 1, 'b6de88e3-bb11-406b-aa77-583c62b51d05', 'a63fe46a-53ed-4d9d-9c84-3599c004da64'),
('7a3f12c3-dbc7-47a0-a591-fbf0f8cb6378', 1, 'b6de88e3-bb11-406b-aa77-583c62b51d05', 'a19d2e78-4c5c-46e6-8a5f-74d6b38b8dc4'),
('aa0fcd09-d0db-43ca-b5ff-e76ef0bfde68', 1, 'c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'd017b327-bb4a-4e16-ba9a-7d6cc0f02f1a'),
('9ad0cb64-d87d-43fb-b15f-f1df12ae7bb1', 1, 'c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'c3a2d634-cd35-4e30-a17d-1b95e0159855'),
('5bc48cb3-f39f-43ec-a763-d10bb60abbe6', 1, '4d225c5a-5701-4870-8007-4fd3a3b774ea', 'c3eb2aaf-2b24-48b4-a50a-6f636acfb798'),
('84ef82be-c98e-44fa-b59d-e3ee8af75db0', 1, '4d225c5a-5701-4870-8007-4fd3a3b774ea', 'a19d2e78-4c5c-46e6-8a5f-74d6b38b8dc4'),
('bd70cb54-c9ca-41ff-a3bb-e6aa432aeec1', 1, 'e8aef14f-5fe0-4b8b-ab09-0b8237b18970', 'a63fe46a-53ed-4d9d-9c84-3599c004da64'),
('fa0bcba7-f925-47cf-b60f-f7ea3dc0aa66', 1, 'e8aef14f-5fe0-4b8b-ab09-0b8237b18970', '3dc02121-b270-454d-8d83-f7cbddc67ae9');