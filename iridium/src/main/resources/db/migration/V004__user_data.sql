DROP TABLE IF EXISTS APP_USER;
DROP TABLE IF EXISTS APP_ROLE;
DROP TABLE IF EXISTS USER_ROLE;

CREATE TABLE APP_USER (
    id UUID NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    constraint pk_user_id PRIMARY KEY (id)
);

CREATE TABLE APP_ROLE (
    id UUID NOT NULL,
    name VARCHAR(15) NOT NULL,
    constraint pk_role_id PRIMARY KEY (id)
);

CREATE TABLE USER_ROLE (
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    constraint pk_user_id_role_id PRIMARY KEY (user_id, role_id)
);

ALTER TABLE IF EXISTS USER_ROLE ADD CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES app_user(id);
ALTER TABLE IF EXISTS USER_ROLE ADD CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES app_role(id);