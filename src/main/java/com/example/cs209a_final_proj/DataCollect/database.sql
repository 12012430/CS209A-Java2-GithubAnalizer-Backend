drop table if exists issue;
drop table if exists commit;
drop table if exists release;
drop table if exists repo;
create table if not exists repo
(
    repo_id int primary key unique not null,
    repo_name varchar(255) not null
    );
create table if not exists issue
(
    repo_id        int                    not null,
    issue_id       int primary key unique not null,
    issue_number   int                    not null,
    comment_number int                    not null,
    title          varchar(1000)          not null,
    state          varchar(20)            not null,
    create_at      timestamptz              not null,
    closed_at      timestamptz
    );
create table  if not exists commit
(
    repo_id        int                            not null,
    sha            varchar(50) primary key unique not null,
    committed_at   timestamptz                      not null,
    developer_name varchar(200)                   not null
    );
create table  if not exists release
(
    repo_id      int                    not null,
    release_id   int primary key unique not null,
    release_name varchar(1000)          not null,
    released_at  timestamptz              not null,
    commit_between_release int
    );


