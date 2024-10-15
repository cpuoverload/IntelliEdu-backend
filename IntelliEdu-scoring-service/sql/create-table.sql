create database if not exists intelli_edu_scoring;

use intelli_edu_scoring;

create table if not exists scoring
(
    `id`                bigint auto_increment comment 'ID' primary key,
    `app_id`            bigint                             not null comment 'Application ID',
    `result_name`       varchar(128)                       not null comment 'Result name',
    `result_detail`     text                               null comment 'Result Detail',
    `result_image_url`  varchar(1024)                      null comment 'Result Image URL',
    `result_threshold`  int                                null comment 'Score Threshold For This Result, Intended For Grade-Type Applications',
    `result_attributes` json                               null comment 'Result Attribute Array (JSON), Intended For Evaluation-Type Applications',
    `user_id`           bigint                             not null comment 'Creator User ID',
    `create_time`       datetime default CURRENT_TIMESTAMP not null comment 'Creation Time',
    `update_time`       datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    `deleted`           tinyint  default 0                 not null comment 'Is Deleted',
    index idx_app_id (`app_id`)
) comment 'Scoring' collate = utf8mb4_unicode_ci;