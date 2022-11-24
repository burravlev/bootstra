insert into roles (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users (name, surname, age, username, password)
values
    ('admin', 'admin', 22, 'admin@mail.ru', '$2a$12$PVQFP4pH1KQZFRhYVBNgQ.bwtweu82l2p7SK7rYDTayUsWzSv1O9y'),
    ('user', 'user', 35, 'user@mail.ru', '$2a$12$1Cv41fJSM/mJZwpbcYsTUOUHdcZfx3/1iYbK1CKScVXO2iJZg7eHi');

insert into user_roles (user_id, role_id)
values (2, 2), (1, 1), (1, 2);