INSERT INTO roles (id, description, name, status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3903', 'ADMIN', 'ROLE_ADMIN', true);
INSERT INTO roles (id, description, name, status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3904', 'USER', 'ROLE_USER', true);

INSERT INTO vaccination_type (id, description, name, status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3903', 'SPUTNIK', 'SPUTNIK', true);
INSERT INTO vaccination_type (id, description, name, status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3904', 'ASTRAZENECA', 'ASTRAZENECA', true);
INSERT INTO vaccination_type (id, description, name, status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3905', 'PFIZER', 'PFIZER', true);
INSERT INTO vaccination_type (id, description, name, status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3906', 'JHONSON&JHONSON', 'JHONSON&JHONSON', true);

INSERT INTO employee (id, created_by, created_on, updated_by, updated_on, birth_date, email, identification, names, second_surname, status, surname, vaccination_status) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3903', '', '2000-01-01 00:00:00.000', NULL, '2000-01-01 00:00:00.000', '2000-01-01', 'admin@gmail.com', '0123456789', 'ADMIN', '', true, 'ADMIN', 'Y');
INSERT INTO auth_employee (id, created_by, created_on, updated_by, updated_on, id_employee, "password", status, username) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3903', '', '2022-01-01 00:00:00.000', '', '2022-01-01 00:00:00.000', 'cdcb652b-43d2-40f1-9cd6-a7469b0f3903', '$2a$10$6/Tt/iJQHnGslc86uYdWo.KlqHWWDMoCG88iyzk41xOd.tPZNa456', true, '0123456789');

INSERT INTO auth_role (auth_id, rol_id) VALUES('cdcb652b-43d2-40f1-9cd6-a7469b0f3903', 'cdcb652b-43d2-40f1-9cd6-a7469b0f3903');