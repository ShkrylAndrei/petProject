ALTER TABLE public.advertisment
    ADD FOREIGN KEY (userid)
    REFERENCES public.userinfo (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;