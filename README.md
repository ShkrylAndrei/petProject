PET PROJECT OF Shkryl Andrei

you need run this script before fun the application

CREATE DATABASE "DBPet"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
    
    
CREATE SCHEMA liquibase
    AUTHORIZATION postgres;