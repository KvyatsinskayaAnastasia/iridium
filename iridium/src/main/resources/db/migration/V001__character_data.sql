DROP TABLE IF EXISTS ability_skills;
DROP TABLE IF EXISTS character_ability;
DROP TABLE IF EXISTS character_spell;
DROP TABLE IF EXISTS magic_spells;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS ability;
DROP TABLE IF EXISTS spell;
DROP TABLE IF EXISTS magic;
DROP TABLE IF EXISTS character;
DROP TABLE IF EXISTS race;

CREATE TABLE ABILITY (
    id UUID NOT NULL,
    ability_type VARCHAR(10) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    name VARCHAR(100) NOT NULL,
    constraint pk_ability_id PRIMARY KEY (id)
);

CREATE TABLE CHARACTER (
    id UUID NOT NULL,
    age INT4 NOT NULL,
    appearance VARCHAR(500),
    biography VARCHAR(1000) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    image BYTEA,
    name VARCHAR(100) NOT NULL,
    temper VARCHAR(500) NOT NULL,
    race_id UUID NOT NULL,
    aim VARCHAR(100) NOT NULL,
    nationality VARCHAR(100) NOT NULL,
    constraint pk_character_id PRIMARY KEY (id)
);

CREATE TABLE CHARACTER_ABILITY (
    id UUID NOT NULL,
    character_id UUID NOT NULL,
    ability_id UUID NOT NULL,
    level INT4 NOT NULL,
    constraint pk_character_id_ability_id PRIMARY KEY (character_id, ability_id)
);

CREATE TABLE MAGIC (
    id UUID NOT NULL,
    description VARCHAR(1000) NOT NULL,
    name VARCHAR(100) NOT NULL,
    constraint pk_magic_id PRIMARY KEY (id)
);

CREATE TABLE RACE (
    id UUID NOT NULL,
    description VARCHAR(1000) NOT NULL,
    name VARCHAR(100) NOT NULL,
    constraint pk_race_id PRIMARY KEY (id)
);

CREATE TABLE SKILL (
    id UUID NOT NULL,
    ability_id UUID NOT NULL,
    description VARCHAR(1000) NOT NULL,
    min_level INT4 NOT NULL,
    constraint pk_skill_id PRIMARY KEY (id)
);

CREATE TABLE SPELL (
    id UUID NOT NULL,
    magic_id UUID NOT NULL,
    description VARCHAR(1000) NOT NULL,
    level INT4 NOT NULL,
    name VARCHAR(100),
    constraint pk_spell_id PRIMARY KEY (id)
);

CREATE TABLE CHARACTER_SPELL (
    character_id UUID NOT NULL,
    spell_id UUID NOT NULL,
    constraint pk_character_id_spell_id PRIMARY KEY (character_id, spell_id)
);

ALTER TABLE IF EXISTS CHARACTER_ABILITY ADD CONSTRAINT fk_character_ability_ability_id FOREIGN KEY (ability_id) REFERENCES ability(id);
ALTER TABLE IF EXISTS CHARACTER_ABILITY ADD CONSTRAINT fk_character_ability_character_id FOREIGN KEY (character_id) REFERENCES character(id);
ALTER TABLE IF EXISTS CHARACTER ADD CONSTRAINT fk_character_race_id FOREIGN KEY (race_id) REFERENCES race(id);
ALTER TABLE IF EXISTS CHARACTER_SPELL ADD CONSTRAINT fk_character_sprll_spell_id FOREIGN KEY (spell_id) REFERENCES spell(id);
ALTER TABLE IF EXISTS CHARACTER_SPELL ADD CONSTRAINT fk_character_spell_character_id FOREIGN KEY (character_id) REFERENCES character(id);
ALTER TABLE IF EXISTS SPELL ADD CONSTRAINT fk_spell_magic_id FOREIGN KEY (magic_id) REFERENCES magic(id);
ALTER TABLE IF EXISTS SKILL ADD CONSTRAINT fk_skill_ability_id FOREIGN KEY (ability_id) REFERENCES ability(id);