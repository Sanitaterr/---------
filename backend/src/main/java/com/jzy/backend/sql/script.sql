create table batteries
(
    database_name         varchar(192) default '' not null,
    id                    int auto_increment comment 'id'
        primary key,
    manufacturing_date    datetime                not null comment '生产日期',
    table_name            varchar(192) default '' not null,
    index_name            varchar(192) default '' not null,
    last_inspection_time  datetime                null comment '最近检查时间',
    compress_ops          int                     not null,
    number_of_inspections int                     not null comment '检查次数',
    battery_level         int                     not null comment '电量',
    compress_ops_ok       int                     not null,
    compress_time         int                     not null,
    uncompress_ops        int                     not null,
    uncompress_time       int                     not null,
    constraint batteries_pk2
        unique (id)
)
    comment '电池表';

create table station
(
    INDEX_ID                      bigint unsigned default '' not null,
    id                            int auto_increment comment 'id'
        primary key,
    NAME                          varchar(193)    default '' not null,
    number_of_batteries           int                        not null comment '站点电池总数量',
    TABLE_ID                      bigint unsigned default '' not null,
    number_of_available_batteries int                        not null comment '可用电池数量',
    TYPE                          int                        not null,
    N_FIELDS                      int                        not null,
    PAGE_NO                       int                        not null,
    SPACE                         int                        not null,
    MERGE_THRESHOLD               int                        not null,
    constraint station_pk2
        unique (id)
)
    comment '换电站';

create table user
(
    GRANTEE        varchar(292) default '' not null,
    id             int auto_increment comment 'id'
        primary key,
    TABLE_CATALOG  varchar(512) default '' not null,
    username       varchar(200)            not null comment '用户名',
    TABLE_SCHEMA   varchar(64)  default '' not null,
    password       varchar(200)            not null comment '密码',
    TABLE_NAME     varchar(64)  default '' not null,
    phone          varchar(100)            null comment '手机号',
    COLUMN_NAME    varchar(64)  default '' not null,
    PRIVILEGE_TYPE varchar(64)  default '' not null,
    IS_GRANTABLE   varchar(3)   default '' not null,
    constraint user_pk2
        unique (id)
)
    comment '用户表';


