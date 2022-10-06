SET FOREIGN_KEY_CHECKS=0;
drop table if exists myusers cascade;
drop table if exists role cascade;
drop table if exists user_roles cascade;
create table myusers
(
    id       bigint not null auto_increment,
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine=InnoDB;
create table role
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB;
create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
) engine=InnoDB;
alter table role
    add constraint UK8sewwnpamngi6b1dwaa88askk unique (name);
alter table user_roles
    add constraint FKrhfovtciq1l558cw6udg0h0d3
        foreign key (role_id)
            references role (id);
alter table user_roles
    add constraint FK37y2w7s1fv84890ulbre91l5r
        foreign key (user_id)
            references myusers (id);
insert into myusers(id, username, password) values(1, 'webper', '$2a$10$2TV2Q64DSpfNHeNJTFqCIupo30kCOjtyCq07x1HwTbDRwiYdlGFue');
insert into role(id,name) values(1, 'ROLE_ADMIN');
insert into user_roles(role_id,user_id) values(1, 1);

