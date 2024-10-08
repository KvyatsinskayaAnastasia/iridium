alter table race
alter column description
type VARCHAR(1000),
alter column appearance
type VARCHAR(1000);

insert into race (id, name, description, appearance)
values
('3eeb7e9e-6bcd-4f72-bb62-5d00c8cb2b2d', 'Люди', 'Самая распространенная по численности и распространению раса, за долгие годы научившаяся приспосабливаться к различным климатическим условиям и явлениям. Отличается плодовитостью и умением осваивать навыки разных сфер, что помогает выживать в сложных обстоятельствах.', 'Цвет волос и глаз разнообразны, волосы от самых светлых оттенков до рыжих или темных. Глаза голубые, карие, зеленые или серые, но в зависимости от магии могут и изменяться. Рост от 1,50 до 2+м.'),
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', 'Эльфы', 'Одна из самых загадочных рас, которая славится своим умом, хитростью, ловкостью и удивительной реакцией, а так же подозрительностью и хорошо развитыми магическими способностями.', 'Обычно, очень высокие, утонченные и тонкокостные, кожа светлых оттенков, как и волосы (за исключением, если эльф пожелал окрасить их в какой либо другой цвет). Колор глаз разнообразен и чаще всего они голубые, синие, зеленые или серые. Имеют заостренные уши в 10-20 см. и раскосые глаза с длинными ресницами.'),
('b6de88e3-bb11-406b-aa77-583c62b51d05', 'Демоны', 'Демоны очень сильные существа, крепкие и развитые во многих сферах. Всегда идут до конца к своей цели и весьма упрямы. В то же время они достаточно эмоциональны и вспыльчивы отчего некоторые расы их побаиваются. Для Демонов определяющим значением является их статус, который основывается на уважении к силе и четком месте в иерархии. Обладают термоустойчивостью, которая позволяет им сосуществовать в самых высоких температурах. Одной из особенностей демонов является их внешность, которая зависит не от генетики, а от красоты души рожденного существа. Никто точно не знает, сколько они живут.', 'Демоническая физиология сходна с человеческой, однако есть определенные отличия, такие как: кожа, которая имеет различные оттенки от красного, серого, фиолетового до черного. Рост достаточно высок, в среднем от 1,8 до 2,5 метров.'),
('c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'Дворфы (гномы)', 'Коренные жители материка Субтерра, издавна населявшие горы и подземные ходы. Очень ловкие и юркие, потому неплохо ориентируются в подземных тоннелях. Хороши в магии земли, недолюбливают высоту и по сравнению с другими расами весьма малы, но отваги и стремлений им не занимать. Некоторые из дворфов носят бороду, но это присуще лишь тем, кто достиг зрелого возраста, а молодые могут опускать короткую бородку либо быть вовсе чисто выбритыми. Народ веселых коротышек довольно дружелюбен и доброжелателен, но это относится только к «своим», а на чужаков они посматривают с недоверием. В среднем, живут около 500 лет.', 'Полутораметровые полурослики с густыми волосами и пристальным взглядом, у некоторых заостренные уши. Цвет волос как правило сходен с человеческой расой, а глаза чаще карие или темные, но иногда встречаются другие оттенки. Примерный рост от 110 до 160.'),
('4d225c5a-5701-4870-8007-4fd3a3b774ea', 'Нефилимы (Отрекшиеся)', 'Нефилимы — далекие потомки богов, которые сошли с ума и после своего свержения исчезли с лица Иридиума. После многовековой тирании и рабства в оковах жестокой Триады, к нефилимам относятся с подозрением. В некоторых кругах недолюбливают, считая их напоминанием о страшном бремени Слепого Солнца (особенно сильная неприязнь от демонов и эльфов). Крылатые обладают предрасположенностью к магии света и чувствуют инстинктивно собратьев. По характеру разнообразны, имеют хорошую память, тонкую душевную организацию и запоминают нанесенные им обиды. Бывают мстительными и пылкими или спокойными, как каменная плита. Не действуют прямо, предпочитая вести политику в стороне, лишний раз не подставляясь, но если дали обещание, то обязаны его выполнить, а потому крайне выборочны в словах. Продолжительность жизни неизвестна, но старых нефилимов мало кто видел, в основном это те, кто получили сильные увечья или магически выгорели.', 'Отчасти похожи чем-то на людей, имеют разнообразную внешность и форму лиц (человеческих). Цвет глаз различен, от голубых, до карих, рост от 1,6 до 2м. Оттенок волос разный. Цвет крыльев и их величина может быть любыми от черного, красного, коричневого до серого или белого. Основания крыльев весьма чувствительны к прикосновению. Крайне редко можно встретить похожих нефилимов. Любят украшать свое тело тату или раскрасками. Учитывая их сомнительное прошлое, пытаются казаться паиньками, но не всегда у них получается убедительно играть роль из-за собственной зажатости.'),
('e8aef14f-5fe0-4b8b-ab09-0b8237b18970', 'Русалки', 'Русалки — истинные повелители морей, веками существовали в глубинах океанов Иридиума, скрывая свою подлинную силу и мощь от жалких сухопутных созданий, ютящихся на суше. Красивые и неуловимые существа, иногда их можно увидеть отдыхающими на прибрежных скалах или плывущими в океанских волнах. Русалки, как дельфины и киты являются млекопитающими океанов, но в отличие от них не связаны с поверхностью и способны все свое время проводить в холодных глубинах. Они властители умов, подчиняющие ментальной магией как морских животных, так и вторгшихся в их владения гуманоидов. Могут есть сырую пищу.', 'Отчасти похожи чем-то на людей, имеют разнообразную внешность и форму лиц, но всегда имеющие сильные морские черты (жабры, перепончатые уши, чешуя на лице и тп), просто так редко их спутаешь с людьми. Цвет глаз различен, от голубых, до алых. Оттенок волос разный. Формы хвостов цвет кожи или чешуи так же крайне разнообразны. Рост от 1.5 метров и более. Встречаются откровенно огромные, догоняющие размерами китов. Но такие самые малочисленные.');

insert into base_race_magic (race_id, magic_id)
values
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', 'e8e9b1b4-fb6b-4f1e-8b88-083e62bb9c13'),
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', '3f5f2b5d-859d-4471-9b55-1e4a0a4b6a0b'),
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', 'b4a59f7f-2dbe-4a14-b1cb-efa19d7022c0'),
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', 'd834b8a8-c7a3-4387-a7a6-1c59a4f0603b'),
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', '53a8e5fc-04b5-4b92-9a1e-7cb5c88c6b4f'),
('b6de88e3-bb11-406b-aa77-583c62b51d05', 'b73e5c84-0a6e-442a-95f8-8a4c7c3d4622'),
('b6de88e3-bb11-406b-aa77-583c62b51d05', 'e8e9b1b4-fb6b-4f1e-8b88-083e62bb9c13'),
('c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'e8e9b1b4-fb6b-4f1e-8b88-083e62bb9c13'),
('c0b4a396-17d2-4b60-b15b-a1b5b704e124', '3f5f2b5d-859d-4471-9b55-1e4a0a4b6a0b'),
('c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'b4a59f7f-2dbe-4a14-b1cb-efa19d7022c0'),
('c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'd834b8a8-c7a3-4387-a7a6-1c59a4f0603b'),
('4d225c5a-5701-4870-8007-4fd3a3b774ea', '2c85b97d-4584-4f76-b1fa-66d2c2a38f30'),
('e8aef14f-5fe0-4b8b-ab09-0b8237b18970', '3f5f2b5d-859d-4471-9b55-1e4a0a4b6a0b'),
('e8aef14f-5fe0-4b8b-ab09-0b8237b18970', 'd834b8a8-c7a3-4387-a7a6-1c59a4f0603b');

insert into unavailable_race_magic (race_id, magic_id)
values
('d8f73e72-1e80-4c79-a80b-62a4cda028b3', '620d5d6b-91e7-4b6e-9cb6-1e2a3d04b3d5'),
('b6de88e3-bb11-406b-aa77-583c62b51d05', '2c85b97d-4584-4f76-b1fa-66d2c2a38f30'),
('c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'd834b8a8-c7a3-4387-a7a6-1c59a4f0603b'),
('4d225c5a-5701-4870-8007-4fd3a3b774ea', 'b73e5c84-0a6e-442a-95f8-8a4c7c3d4622'),
('e8aef14f-5fe0-4b8b-ab09-0b8237b18970', 'e8e9b1b4-fb6b-4f1e-8b88-083e62bb9c13'),
('e8aef14f-5fe0-4b8b-ab09-0b8237b18970', 'b4a59f7f-2dbe-4a14-b1cb-efa19d7022c0');

insert into race_ability (bonus, race_id, ability_id)
values
(1, '3eeb7e9e-6bcd-4f72-bb62-5d00c8cb2b2d', 'd017b327-bb4a-4e16-ba9a-7d6cc0f02f1a'),
(1, '3eeb7e9e-6bcd-4f72-bb62-5d00c8cb2b2d', 'c3eb2aaf-2b24-48b4-a50a-6f636acfb798'),
(1, 'd8f73e72-1e80-4c79-a80b-62a4cda028b3', 'a5e9c494-9218-4f56-ab80-a594eed7b835'),
(1, 'd8f73e72-1e80-4c79-a80b-62a4cda028b3', 'a19d2e78-4c5c-46e6-8a5f-74d6b38b8dc4'),
(1, 'b6de88e3-bb11-406b-aa77-583c62b51d05', 'a63fe46a-53ed-4d9d-9c84-3599c004da64'),
(1, 'b6de88e3-bb11-406b-aa77-583c62b51d05', 'a19d2e78-4c5c-46e6-8a5f-74d6b38b8dc4'),
(1, 'c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'd017b327-bb4a-4e16-ba9a-7d6cc0f02f1a'),
(1, 'c0b4a396-17d2-4b60-b15b-a1b5b704e124', 'c3a2d634-cd35-4e30-a17d-1b95e0159855'),
(1, '4d225c5a-5701-4870-8007-4fd3a3b774ea', 'c3eb2aaf-2b24-48b4-a50a-6f636acfb798'),
(1, '4d225c5a-5701-4870-8007-4fd3a3b774ea', 'a19d2e78-4c5c-46e6-8a5f-74d6b38b8dc4'),
(1, 'e8aef14f-5fe0-4b8b-ab09-0b8237b18970', 'a63fe46a-53ed-4d9d-9c84-3599c004da64'),
(1, 'e8aef14f-5fe0-4b8b-ab09-0b8237b18970', '3dc02121-b270-454d-8d83-f7cbddc67ae9');