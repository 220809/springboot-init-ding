# 创建数据库
create database if not exists your_db;
# 使用数据库
use your_db;
# 创建用户表
create table if not exists user
          (
              id           bigint auto_increment comment 'id' primary key,
              username     varchar(256)                           null comment '用户昵称',
              user_account  varchar(256)                           not null comment '账号',
              password varchar(256)                           not null comment '密码',
              user_avatar   varchar(1024)  default 'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_1280.png'
                  null comment '用户头像',
              gender       tinyint                                null comment '性别 0-未知，1-男，2-女',
              user_role     tinyint default 0            not null comment '用户角色：0-user / 1-admin',
              create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
              update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
              deleted     tinyint      default 0                 not null comment '是否删除 0-未删除，1-已删除',
              constraint uni_user_account
                  unique (user_account)
          ) comment '用户' charset utf8;