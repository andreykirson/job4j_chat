insert into roles(role) values ('ROLE_USER');
insert into roles(role) values ('ROLE_ADMIN');


insert into persons ("id", "name", email, "password", enabled)
values(1, 'Andrey', 'email@gmail.com', '$2a$10$UA2JdrolnCEsDOLaFoal8Ob9vwWWtkhj1p0eohDl8fe2QbmXlyBwO', true);

insert into person_roles ("person_id", "role_id") values(1, 1);