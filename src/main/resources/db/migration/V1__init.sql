-- SEQUENCE: public.comment_entity_seq

CREATE SEQUENCE IF NOT EXISTS public.comment_entity_seq
    INCREMENT BY 50
    START WITH 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.comment_entity_seq
    OWNER TO postgres;
-- SEQUENCE: public.post_entity_seq

-- DROP SEQUENCE IF EXISTS public.post_entity_seq;

CREATE SEQUENCE IF NOT EXISTS public.post_entity_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.post_entity_seq
    OWNER TO postgres;

-- SEQUENCE: public.topic_entity_seq

-- DROP SEQUENCE IF EXISTS public.topic_entity_seq;

CREATE SEQUENCE IF NOT EXISTS public.topic_entity_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.topic_entity_seq
    OWNER TO postgres;

-- SEQUENCE: public.user_entity_seq

-- DROP SEQUENCE IF EXISTS public.user_entity_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_entity_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.user_entity_seq
    OWNER TO postgres;

-- Table: public.topic_entity

-- DROP TABLE IF EXISTS public.topic_entity;

CREATE TABLE IF NOT EXISTS public.topic_entity
(
    id bigint NOT NULL,
    created date,
    modified date,
    title character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT topic_entity_pkey PRIMARY KEY (id),
    CONSTRAINT uk_kfflubh43nams272mf4xgah23 UNIQUE (title)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.topic_entity
    OWNER to postgres;

-- Table: public.post_entity

-- DROP TABLE IF EXISTS public.post_entity;

CREATE TABLE IF NOT EXISTS public.post_entity
(
    id bigint NOT NULL,
    created date,
    modified date,
    text character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    topic_id bigint,
    CONSTRAINT post_entity_pkey PRIMARY KEY (id),
    CONSTRAINT uk_p8l9j4ln4ubdh7yjhmglbbcqi UNIQUE (text),
    CONSTRAINT fkroqwl905m5dott3n6xynjmm1i FOREIGN KEY (topic_id)
    REFERENCES public.topic_entity (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.post_entity
    OWNER to postgres;

-- Table: public.user_entity

-- DROP TABLE IF EXISTS public.user_entity;

CREATE TABLE IF NOT EXISTS public.user_entity
(
    id bigint NOT NULL,
    created date,
    modified date,
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_entity_pkey PRIMARY KEY (id),
    CONSTRAINT uk_2jsk4eakd0rmvybo409wgwxuw UNIQUE (username)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_entity
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.comment_entity (
id bigint NOT NULL DEFAULT nextval('public.comment_entity_seq'),
    created date,
    modified date,
    text character varying(255) UNIQUE,
    user_id bigint,
    post_id bigint,
    CONSTRAINT comment_entity_pkey PRIMARY KEY (id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id)
    REFERENCES public.user_entity (id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id)
    REFERENCES public.post_entity (id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    );

ALTER TABLE IF EXISTS public.comment_entity
    OWNER to postgres;