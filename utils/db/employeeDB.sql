CREATE TABLE IF NOT EXISTS public.employee
(
    employee_id integer NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass),
    first_name text COLLATE pg_catalog."default",
    last_name text COLLATE pg_catalog."default",
    department_id integer,
    job_title text COLLATE pg_catalog."default",
    gender text COLLATE pg_catalog."default",
    date_of_birth date,
    CONSTRAINT employee_pk PRIMARY KEY (employee_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employee OWNER to root;

INSERT INTO "public"."employee" (FIRST_NAME,LAST_NAME,DEPARTMENT_ID,JOB_TITLE,GENDER,DATE_OF_BIRTH) VALUES
     ('Elena','Kotova',1,'manager','FEMALE','1990-04-08'),
     ('Evgeny','Tahoma',3,'guard','MALE','1998-02-20'),
     ('Nikita','Kolmakov',5,'janitor','MALE','1998-06-19');