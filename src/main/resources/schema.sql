use wanted_labs;
call my_procedure();


CREATE TABLE IF NOT EXISTS company
(
    id      int auto_increment
        primary key,
    name    varchar(255) not null,
    country varchar(255) not null,
    region  varchar(255) not null
);
CREATE TABLE IF NOT EXISTS user
(
    id       varchar(255) not null
        primary key,
    nickname varchar(255) not null
);
CREATE TABLE IF NOT EXISTS job_post
(
    id                int auto_increment primary key,
    position          varchar(255) not null,
    recruit_content longtext     not null,
    skills            varchar(255) not null,
    compensation      int          null,
    company_id        int          not null,
    constraint fk_company
        foreign key (company_id) references company (id)
);
CREATE TABLE IF NOT EXISTS application_letter
(
    id        int auto_increment
        primary key,
    job_post_id int null,
    user_id varchar(255) not null,
    portfolio_link varchar(45)  not null,
    constraint unique_user_job_post
        unique (job_post_id, user_id),
    constraint fk_job_post
        foreign key (job_post_id) references job_post (id),
    constraint fk_user
        foreign key (user_id) references user (id)
);

CREATE TABLE IF NOT EXISTS deleted_application_letter
(
    id        int auto_increment
        primary key,
    job_post_position varchar(255) not null,
    company_name    varchar(255) not null,
    user_id varchar(255) not null,
    constraint fk_user_deleted_letter
        foreign key (user_id) references user (id)
);