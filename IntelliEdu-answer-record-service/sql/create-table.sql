create database if not exists intelli_edu_answer_record;

use intelli_edu_answer_record;

create table if not exists answer_record
(
    `id`               bigint auto_increment comment 'ID' primary key,
    `user_id`          bigint                             not null comment 'Creator User ID',
    `app_id`           bigint                             not null comment 'Application ID',
    `app_type`         tinyint  default 0                 not null comment 'Application Type (0 - Grade, 1 - Evaluation)',
    `strategy`         tinyint  default 0                 not null comment 'Scoring Strategy (0 - Custom, 1 - AI)',
    `answers`          json                               null comment 'User Answer List (JSON)',
    `result_id`        bigint                             null comment 'Result ID',
    `result_name`      varchar(128)                       not null comment 'Result name',
    `result_detail`    text                               null comment 'Result Detail',
    `result_image_url` varchar(1024)                      null comment 'Result Image URL',
    `result_grade`     int                                null comment 'Result Grade, Intended For Grade-Type Applications',
    `create_time`      datetime default CURRENT_TIMESTAMP not null comment 'Creation Time',
    `update_time`      datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    `deleted`          tinyint  default 0                 not null comment 'Is Deleted',
    index idx_user_id (`user_id`),
    index idx_app_id (`app_id`)
) comment 'Answer Record' collate = utf8mb4_unicode_ci;