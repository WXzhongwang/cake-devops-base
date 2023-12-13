create table app
(
    id                      bigint auto_increment
        primary key,
    app_name                varchar(255)                           not null,
    description             varchar(255)                           null,
    repo                    varchar(255)                           null,
    default_branch          varchar(255)                           null,
    language                varchar(255)                           null,
    develop_mode            varchar(255)                           null,
    owner                   bigint                                 null,
    health_check            varchar(255)                           null,
    status                  varchar(255) default '0'               null,
    is_deleted              varchar(255) default '0'               null,
    gmt_create              datetime     default CURRENT_TIMESTAMP null,
    gmt_modified            datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    department_abbreviation varchar(255)                           null,
    department              varchar(255)                           null,
    creator                 varchar(255)                           null,
    modifier                varchar(255)                           null
)
    charset = utf8mb4;

create table app_env
(
    id            bigint                                 not null
        primary key,
    cluster_id    bigint                                 not null,
    app_id        bigint                                 not null,
    domains       varchar(255)                           null,
    env_name      varchar(255)                           not null,
    env           varchar(12)                            not null,
    max_replicas  int                                    null,
    cpu           decimal(12, 4)                         null,
    memory        decimal(12, 4)                         null,
    auto_scaling  char                                   null,
    need_approval char                                   null,
    status        varchar(255) default '0'               null,
    is_deleted    varchar(255) default '0'               null,
    gmt_create    datetime     default CURRENT_TIMESTAMP null,
    gmt_modified  datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator       varchar(255)                           null,
    modifier      varchar(255)                           null
)
    charset = utf8mb4;

create table app_member
(
    id           bigint auto_increment
        primary key,
    account_id   bigint                                 not null,
    app_id       bigint                                 null,
    roles        varchar(512)                           null,
    status       varchar(255) default '0'               null,
    is_deleted   varchar(255) default '0'               null,
    gmt_create   datetime     default CURRENT_TIMESTAMP null,
    gmt_modified datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator      varchar(255)                           null,
    modifier     varchar(255)                           null
)
    charset = utf8mb4;

create table approval
(
    id              bigint auto_increment
        primary key,
    doc_address     varchar(500)                           not null,
    change_date     datetime                               not null,
    approval_status varchar(64)                            not null,
    comment         varchar(64)                            not null,
    is_deleted      varchar(255) default '0'               null,
    gmt_create      datetime     default CURRENT_TIMESTAMP null,
    gmt_modified    datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator         varchar(255)                           null,
    modifier        varchar(255)                           null
)
    charset = utf8mb4;

create table cluster
(
    id                bigint auto_increment
        primary key,
    name              varchar(255)                           null,
    tags              varchar(255) default '0'               null,
    version           varchar(255)                           null,
    cluster_type      char(12)                               null,
    status            char(2)                                null,
    is_deleted        varchar(255) default '0'               null,
    gmt_create        datetime     default CURRENT_TIMESTAMP null,
    gmt_modified      datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator           varchar(255)                           null,
    modifier          varchar(255)                           null,
    connection_string varchar(200)                           null,
    token             varchar(200)                           null
)
    charset = utf8mb4;

create table group_host
(
    id           bigint auto_increment
        primary key,
    group_id     bigint                                 not null,
    host_id      bigint                                 not null,
    is_deleted   varchar(255) default '0'               null,
    gmt_create   datetime     default CURRENT_TIMESTAMP null,
    gmt_modified datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator      varchar(255)                           null,
    modifier     varchar(255)                           null
)
    charset = utf8mb4;

create table host
(
    id           bigint auto_increment
        primary key,
    name         varchar(255)                           not null,
    host_name    varchar(255)                           not null,
    port         int                                    not null,
    username     varchar(255)                           not null,
    status       varchar(255) default '0'               null,
    is_deleted   varchar(255) default '0'               null,
    gmt_create   datetime     default CURRENT_TIMESTAMP null,
    gmt_modified datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    `desc`       varchar(255)                           null,
    verified     char(2)                                null,
    creator      varchar(255)                           null,
    modifier     varchar(255)                           null,
    pkey         varchar(2000)                          not null
)
    charset = utf8mb4;

create table host_group
(
    id           bigint auto_increment
        primary key,
    name         varchar(255)                           not null,
    parent_id    bigint                                 null,
    sort         int                                    not null,
    is_deleted   varchar(255) default '0'               null,
    gmt_create   datetime     default CURRENT_TIMESTAMP null,
    gmt_modified datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator      varchar(255)                           null,
    modifier     varchar(255)                           null
)
    charset = utf8mb4;

create table namespace
(
    id             bigint                                   not null
        primary key,
    cluster_id     bigint                                   not null,
    name           varchar(255)                             not null,
    request_cpu    decimal(12, 4)                           null,
    request_memory decimal(12, 4) default 0.0000            null,
    limit_cpu      decimal(12, 4)                           null,
    limit_memory   decimal(12, 4)                           null,
    max_pods       int                                      null,
    max_gpu        decimal(12, 4)                           null,
    max_services   int                                      null,
    status         char(2)                                  null,
    is_deleted     varchar(255)   default '0'               null,
    gmt_create     datetime       default CURRENT_TIMESTAMP null,
    gmt_modified   datetime       default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator        varchar(255)                             null,
    modifier       varchar(255)                             null
)
    charset = utf8mb4;

create table release_no
(
    id                bigint auto_increment
        primary key,
    app_id            bigint                                 not null,
    release_no        varchar(64)                            null,
    approval_id       bigint                                 null,
    release_date      datetime                               not null,
    release_branch    varchar(255)                           not null,
    release_commit_id varchar(255)                           null,
    release_version   varchar(255)                           null,
    env_id            bigint                                 not null,
    release_status    varchar(255)                           not null,
    rollback          varchar(64)                            null,
    rollback_id       bigint                                 null,
    is_deleted        varchar(255) default '0'               null,
    gmt_create        datetime     default CURRENT_TIMESTAMP null,
    gmt_modified      datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator           varchar(255)                           null,
    modifier          varchar(255)                           null
)
    charset = utf8mb4;

create table terminal_session
(
    id              bigint auto_increment
        primary key,
    session_id      varchar(255)                           not null,
    account_id      bigint                                 null,
    remote_addr     varchar(255)                           null,
    session_closed  tinyint                                null,
    close_time      datetime                               null,
    server_hostname varchar(255)                           null,
    server_addr     varchar(255)                           null,
    session_type    varchar(10)                            null,
    is_deleted      varchar(255) default '0'               null,
    gmt_create      datetime     default CURRENT_TIMESTAMP null,
    gmt_modified    datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator         varchar(255)                           null,
    modifier        varchar(255)                           null
)
    charset = utf8mb4;

create table terminal_session_instance
(
    id                    bigint auto_increment
        primary key,
    session_id            varchar(255)                           not null,
    instance_id           varchar(255)                           null,
    duplicate_instance_id varchar(255)                           null,
    instance_session_type varchar(10)                            null,
    login_user            varchar(255)                           null,
    host_ip               varchar(255)                           null,
    output_size           bigint                                 null,
    instance_closed       tinyint                                null,
    open_time             datetime                               null,
    close_time            datetime                               null,
    is_deleted            varchar(255) default '0'               null,
    gmt_create            datetime     default CURRENT_TIMESTAMP null,
    gmt_modified          datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator               varchar(255)                           null,
    modifier              varchar(255)                           null
)
    charset = utf8mb4;

create table terminal_session_instance_command
(
    id                           bigint auto_increment
        primary key,
    terminal_session_instance_id varchar(255)                           not null,
    prompt                       varchar(255)                           null,
    is_formatted                 varchar(10)  default '0'               null,
    input                        text                                   null,
    input_formatted              text                                   null,
    output                       text                                   null,
    is_deleted                   varchar(255) default '0'               null,
    gmt_create                   datetime     default CURRENT_TIMESTAMP null,
    gmt_modified                 datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    creator                      varchar(255)                           null,
    modifier                     varchar(255)                           null
)
    charset = utf8mb4;


