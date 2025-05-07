create table attachment
(
    id   UUID          NOT NULL,
    name VARCHAR(50)  NOT NULL,
    path VARCHAR(100)  NOT NULL,
    constraint pk_attach_id PRIMARY KEY (id)
);

ALTER TABLE character DROP COLUMN image;
ALTER TABLE character ADD COLUMN image_id UUID;
ALTER TABLE character ADD CONSTRAINT fk_character_image_id FOREIGN KEY (image_id) REFERENCES attachment(id);