CREATE TABLE public.advertisment
(
--    id character varying(36) NOT NULL,
    id UUID NOT NULL,
    shorttext character varying(300) NOT NULL,
    longtext text,
    type character varying(100) NOT NULL,
    datecreate timestamp,
    userid UUID NOT NULL,
    PRIMARY KEY (id)
);
