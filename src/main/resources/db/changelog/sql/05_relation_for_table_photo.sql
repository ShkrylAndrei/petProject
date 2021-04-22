ALTER TABLE public.photo
    ADD FOREIGN KEY (advertismentid)
    REFERENCES public.advertisment (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;