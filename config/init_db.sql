create table resume
(
    uuid      char(36) not null
        constraint resume_pkey
            primary key,
    full_name text     not null
);
alter table resume
    owner to postgres;


create table if not exists contact
(
    id          serial   not null
        constraint contact_pkey
            primary key,
    resume_uuid char(36) not null references resume (uuid) on delete cascade,
    type        text     not null,
    value       text     not null

);
create unique index contact__uuid_type_index
    on contact (resume_uuid, type);

alter table contact
    owner to postgres;


create table if not exists section
(
    id          serial   not null
        constraint section_pkey
            primary key,
    resume_uuid char(36) not null  references resume (uuid) on delete cascade,
    type        text     not null,
    value       text     not null
);
create unique index if not exists section__uuid_type_index
    on section (resume_uuid, type);

alter table section
    owner to postgres;





