CREATE TABLE public.userinfo
(
--    id character varying(36) NOT NULL,
    id UUID NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    PRIMARY KEY (id)
);