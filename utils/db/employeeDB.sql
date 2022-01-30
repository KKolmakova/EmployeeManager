DROP DATABASE IF EXISTS employeedb;

CREATE DATABASE employeedb WITH ENCODING = 'UTF8';
\connect employeedb
SET client_encoding = 'UTF8';

CREATE TABLE public.employee (
    employee_id integer NOT NULL,
    first_name text,
    last_name text,
    department_id integer,
    job_title text,
    gender text,
    date_of_birth date
);

CREATE SEQUENCE public.employee_employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.employee_employee_id_seq OWNED BY public.employee.employee_id;
ALTER TABLE ONLY public.employee ALTER COLUMN employee_id SET DEFAULT nextval('public.employee_employee_id_seq'::regclass);
ALTER TABLE ONLY public.employee ADD CONSTRAINT employee_pk PRIMARY KEY (employee_id);

INSERT INTO "public"."employee" (FIRST_NAME,LAST_NAME,DEPARTMENT_ID,JOB_TITLE,GENDER,DATE_OF_BIRTH) VALUES
     ('Elena','Kotova',1,'manager','FEMALE','1990-04-08'),
     ('Evgeny','Tahoma',3,'guard','MALE','1998-02-20'),
     ('Nikita','Kolmakov',5,'janitor','MALE','1998-06-19');