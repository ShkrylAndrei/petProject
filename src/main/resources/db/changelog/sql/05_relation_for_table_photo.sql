ALTER TABLE public.photo
    ADD FOREIGN KEY (idadvertisment)
    REFERENCES public.advertisment (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;