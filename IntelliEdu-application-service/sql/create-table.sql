create database if not exists intelli_edu_application;

use intelli_edu_application;

create table if not exists application
(
    `id`            bigint auto_increment comment 'ID' primary key,
    `app_name`      varchar(128)                       not null comment 'Application Name',
    `description`   varchar(2048)                      null comment 'Application Description',
    `image_url`     varchar(1024)                      null comment 'Application Image URL',
    `type`          tinyint  default 0                 not null comment 'Application Type (0 - Grade, 1 - Evaluation)',
    `strategy`      tinyint  default 0                 not null comment 'Scoring Strategy (0 - Custom, 1 - AI)',
    `user_id`       bigint                             not null comment 'Creator User ID',
    `audit_status`  int      default 0                 not null comment 'Audit Status: 0 - Pending, 1 - Approved, 2 - Rejected',
    `auditor_id`    bigint                             null comment 'Auditor User ID',
    `audit_message` varchar(512)                       null comment 'Audit Message',
    `audit_time`    datetime                           null comment 'Audit Time',
    `create_time`   datetime default CURRENT_TIMESTAMP not null comment 'Creation Time',
    `update_time`   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    `deleted`       tinyint  default 0                 not null comment 'Is Deleted',
    index idx_app_name (`app_name`)
) comment 'Application' collate = utf8mb4_unicode_ci;

create table if not exists question
(
    `id`          bigint auto_increment comment 'ID' primary key,
    `questions`   json                               null comment 'Question List (JSON)',
    `app_id`      bigint                             not null comment 'Application ID',
    `user_id`     bigint                             not null comment 'Creator User ID',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment 'Creation Time',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    `deleted`     tinyint  default 0                 not null comment 'Is Deleted',
    index idx_app_id (`app_id`)
) comment 'Question' collate = utf8mb4_unicode_ci;
