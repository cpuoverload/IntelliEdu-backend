create database if not exists intelli_edu_user;

use intelli_edu_user;

create table if not exists `user`
(
    `id`          bigint auto_increment comment 'id' primary key,
    `username`    varchar(256)                           not null comment '账号',
    `password`    varchar(256)                           not null comment '密码',
    `nickname`    varchar(256)                           null comment '昵称',
    `role`        varchar(256) default 'user'            not null comment '角色',
    `create_time` datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `deleted`     tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;