create database if not exists intelli_edu_user;

use intelli_edu_user;

create table if not exists `user`
(
    `id`          bigint auto_increment comment 'id' primary key,
    `username`    varchar(256)                           not null comment 'User Name',
    `password`    varchar(256)                           not null comment 'Password',
    `nickname`    varchar(256)                           null comment 'Nickname',
    `avatar`      varchar(1024)                          null comment 'User Avatar',
    `role`        varchar(256) default 'user'            not null comment 'User Role (user, admin)',
    `create_time` datetime     default CURRENT_TIMESTAMP not null comment 'Creation Time',
    `update_time` datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    `deleted`     tinyint      default 0                 not null comment 'Is Deleted'
) comment 'User' collate = utf8mb4_unicode_ci;