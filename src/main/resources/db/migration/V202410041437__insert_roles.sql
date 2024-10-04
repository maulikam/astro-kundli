-- Migration script: V202410041437__insert_roles.sql
-- Write your SQL statements here

INSERT INTO public.role (name) VALUES ('USER');
INSERT INTO public.role (name) VALUES ('ADMIN');
