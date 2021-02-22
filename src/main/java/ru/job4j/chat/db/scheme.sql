CREATE TABLE IF NOT EXISTS roles (
    id serial primary key,
    role varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS persons (
    id serial primary key,
    "name" varchar (40),
    email varchar (40),
    password varchar (50),
    enabled boolean default true,
    role_id int not null references roles(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id serial primary key,
    content varchar (200),
    createdDate timestamp,
    author_id int not null references persons(id),
    room_id int not null references rooms(id)
);

CREATE TABLE IF NOT EXISTS rooms (
    id serial primary key,
    title varchar(20),
    createdDate timestamp,
    creator_id int not null references persons(id)
);

CREATE TABLE IF NOT EXISTS participants (
	id serial primary key,
	room_id int references rooms(id),
	person_id int references persons(id)
)