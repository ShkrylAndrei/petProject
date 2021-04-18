CREATE TABLE public.advertisement
(
    id character varying(36) NOT NULL,
    shorttext character varying(300) NOT NULL,
    longtext text,
    type character varying(100) NOT NULL,
    datecreate date NOT NULL,
    userid character varying(36) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.advertisement
    ADD FOREIGN KEY (userid)
    REFERENCES public.userinfo (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;