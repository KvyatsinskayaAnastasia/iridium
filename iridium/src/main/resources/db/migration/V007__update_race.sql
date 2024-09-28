ALTER TABLE RACE ADD
appearance VARCHAR(500) NOT NULL;

CREATE TABLE BASE_RACE_MAGIC (
    race_id UUID NOT NULL,
    magic_id UUID NOT NULL,
    constraint pk_base_race_id_magic_id PRIMARY KEY (race_id, magic_id)
);

CREATE TABLE UNAVAILABLE_RACE_MAGIC (
    race_id UUID NOT NULL,
    magic_id UUID NOT NULL,
    constraint pk_unavailable_race_id_magic_id PRIMARY KEY (race_id, magic_id)
);

CREATE TABLE RACE_ABILITY (
    bonus INT4 NOT NULL,
    race_id UUID NOT NULL,
    ability_id UUID NOT NULL,
    constraint pk_race_id_ability_id PRIMARY KEY (race_id, ability_id)
);

ALTER TABLE IF EXISTS BASE_RACE_MAGIC ADD CONSTRAINT fk_base_race_magic_race_id FOREIGN KEY (race_id) REFERENCES race(id);
ALTER TABLE IF EXISTS BASE_RACE_MAGIC ADD CONSTRAINT fk_base_race_magic_magic_id FOREIGN KEY (magic_id) REFERENCES magic(id);
ALTER TABLE IF EXISTS UNAVAILABLE_RACE_MAGIC ADD CONSTRAINT fk_unavailable_race_magic_race_id FOREIGN KEY (race_id) REFERENCES race(id);
ALTER TABLE IF EXISTS UNAVAILABLE_RACE_MAGIC ADD CONSTRAINT fk_unavailable_race_magic_magic_id FOREIGN KEY (magic_id) REFERENCES magic(id);
ALTER TABLE IF EXISTS RACE_ABILITY ADD CONSTRAINT fk_race_ability_race_id FOREIGN KEY (race_id) REFERENCES race(id);
ALTER TABLE IF EXISTS RACE_ABILITY ADD CONSTRAINT fk_race_ability_ability_id FOREIGN KEY (ability_id) REFERENCES ability(id);