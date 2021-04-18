CREATE TABLE public.photo
(
    id character varying(36) NOT NULL,
    idadvertisment character varying(36) NOT NULL,
    pathphoto character varying(200) NOT NULL,
    description character varying(300),
    PRIMARY KEY (id)
);