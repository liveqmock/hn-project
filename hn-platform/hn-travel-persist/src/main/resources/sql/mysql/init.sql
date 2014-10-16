/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/16 23:24:16                          */
/*==============================================================*/


drop table if exists blob_data;

drop table if exists date_price;

drop table if exists itinerary;

drop table if exists itinerary_scenic;

drop table if exists picture;

drop table if exists scenic;

drop table if exists scenic_ticket;

drop table if exists ticket;

drop table if exists ticket_kind;

/*==============================================================*/
/* Table: blob_data                                             */
/*==============================================================*/
create table blob_data
(
   id                   bigint not null auto_increment comment '主键',
   data                 text comment '数据',
   primary key (id)
);

/*==============================================================*/
/* Table: date_price                                            */
/*==============================================================*/
create table date_price
(
   id                   bigint not null auto_increment comment '主键',
   kind_id              bigint not null comment '票种ID',
   pdate                date not null comment '日期',
   market_price         double not null comment '市场价',
   now_price            double not null comment '现价',
   total_num            int not null comment '总数量',
   remain_num           int not null comment '剩余数量',
   primary key (id)
);

alter table date_price comment '票种日期价格';

/*==============================================================*/
/* Table: itinerary                                             */
/*==============================================================*/
create table itinerary
(
   id                   bigint not null comment '主键',
   name                 varchar(50) not null comment '名称',
   title                varchar(100) comment '小标题',
   img_uri              varchar(200) not null comment '主图地址',
   type                 int not null comment '类型。1，自由行；2，跟团游。',
   market_price         double not null comment '市场价',
   now_price            double not null comment '现价',
   pay_type             int not null comment '支付方式。1，在线支付；2，线下支付',
   price_instructio     varchar(500) comment '起价说明',
   issue_place          varchar(50) comment '出发地',
   end_place            varchar(50) not null comment '目的地',
   feature_id           bigint comment '产品特色',
   explain_id           bigint comment '行程说明',
   prompt_id            bigint comment '重要提示',
   traffic_id           bigint comment '交通信息',
   status               int not null default 0 comment '状态。0，正常；1，下架',
   primary key (id)
);

alter table itinerary comment '旅行线路【自由行、跟团游】';

/*==============================================================*/
/* Table: itinerary_scenic                                      */
/*==============================================================*/
create table itinerary_scenic
(
   id                   bigint not null comment '景点ID',
   itinerary_id         bigint not null comment '线路ID',
   primary key (id, itinerary_id)
);

alter table itinerary_scenic comment '线程关联的景点';

/*==============================================================*/
/* Table: picture                                               */
/*==============================================================*/
create table picture
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(50) not null comment '名称',
   ref_id               bigint not null comment '关联对象ID',
   ref_type             int not null comment '关联对象类型。1，景点；2，酒店；3，线路',
   desciption           varchar(300) comment '描述',
   raw_uri              varchar(200) not null comment '原图地址',
   arge_uri             varchar(200) comment '大图地址',
   middle_uri           varchar(200) comment '中图地址',
   small_uri            varchar(200) comment '小图地址',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   status               int not null default 0 comment '状态。0，正常；1，未引用',
   primary key (id)
);

/*==============================================================*/
/* Table: scenic                                                */
/*==============================================================*/
create table scenic
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(50) not null comment '名称',
   title                varchar(100) comment '小标题',
   img_uri              varchar(200) not null comment '主图地址',
   area_code            varchar(50) not null comment '所在区县的编码',
   address              varchar(200) not null comment '详细地址',
   longitude            double comment '在地图上的经度',
   latitude             double comment '在地图上的纬度',
   services             varchar(20) comment '支持的服务。1，入园保证；2，快速入园；3，随时退；4，贵就赔。多个用逗号分隔',
   notice_id            bigint comment '预订须知',
   in_time              varchar(20) comment '入园时间',
   introduce_id         bigint comment '景点介绍',
   traffic_id           bigint comment '交通指南',
   low_price            double comment '最低价格',
   good_rate            numeric(5,2) default '100.00' comment '好评率(%)',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   status               int not null default 0 comment '状态。0，正常；1，下架',
   primary key (id)
);

alter table scenic comment '景点';

/*==============================================================*/
/* Table: scenic_ticket                                         */
/*==============================================================*/
create table scenic_ticket
(
   id                   bigint not null comment '主键',
   scenic_id            bigint not null comment '景点ID',
   name                 varchar(50) not null comment '名称',
   pay_type             int comment '支付方式。1，在线支付；2，线下支付',
   type                 int not null comment '类型。1，单门票；2，组合套餐',
   agreement_id         bigint comment '预订协议',
   status               int not null default 0 comment '状态。0，正常；1，下架',
   primary key (id)
);

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
   id                   bigint not null auto_increment comment '主键',
   cost_id              bigint comment '费用说明',
   notice_id            bigint comment '预订须知',
   refund_id            bigint comment '退款说明',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   primary key (id)
);

alter table ticket comment '票据。景点门票、路线套票';

/*==============================================================*/
/* Table: ticket_kind                                           */
/*==============================================================*/
create table ticket_kind
(
   id                   bigint not null auto_increment comment '主键',
   ticket_id            bigint not null comment '票据ID',
   name                 varchar(50) not null comment '名称',
   desciption           varchar(300) comment '描述',
   market_price         double not null comment '市场价',
   now_price            double not null comment '现价',
   create_time          datetime not null comment '创建时间',
   update_time          datetime not null comment '更新时间',
   status               int not null default 0 comment '状态。0，正常；1，下架',
   primary key (id)
);

alter table ticket_kind comment '票种';

alter table date_price add constraint FK_Reference_10 foreign key (kind_id)
      references ticket_kind (id) on delete restrict on update restrict;

alter table itinerary add constraint FK_Reference_14 foreign key (feature_id)
      references blob_data (id) on delete restrict on update restrict;

alter table itinerary add constraint FK_Reference_15 foreign key (explain_id)
      references blob_data (id) on delete restrict on update restrict;

alter table itinerary add constraint FK_Reference_17 foreign key (prompt_id)
      references blob_data (id) on delete restrict on update restrict;

alter table itinerary add constraint FK_Reference_18 foreign key (traffic_id)
      references blob_data (id) on delete restrict on update restrict;

alter table itinerary add constraint FK_Reference_21 foreign key (id)
      references ticket (id) on delete restrict on update restrict;

alter table itinerary_scenic add constraint FK_Reference_11 foreign key (id)
      references scenic (id) on delete restrict on update restrict;

alter table itinerary_scenic add constraint FK_Reference_12 foreign key (itinerary_id)
      references itinerary (id) on delete restrict on update restrict;

alter table scenic add constraint FK_Reference_1 foreign key (notice_id)
      references blob_data (id) on delete restrict on update restrict;

alter table scenic add constraint FK_Reference_2 foreign key (introduce_id)
      references blob_data (id) on delete restrict on update restrict;

alter table scenic add constraint FK_Reference_3 foreign key (traffic_id)
      references blob_data (id) on delete restrict on update restrict;

alter table scenic_ticket add constraint FK_Reference_23 foreign key (id)
      references ticket (id) on delete restrict on update restrict;

alter table scenic_ticket add constraint FK_Reference_5 foreign key (scenic_id)
      references scenic (id) on delete restrict on update restrict;

alter table scenic_ticket add constraint FK_Reference_9 foreign key (agreement_id)
      references blob_data (id) on delete restrict on update restrict;

alter table ticket add constraint FK_Reference_6 foreign key (cost_id)
      references blob_data (id) on delete restrict on update restrict;

alter table ticket add constraint FK_Reference_7 foreign key (notice_id)
      references blob_data (id) on delete restrict on update restrict;

alter table ticket add constraint FK_Reference_8 foreign key (refund_id)
      references blob_data (id) on delete restrict on update restrict;

alter table ticket_kind add constraint FK_Reference_22 foreign key (ticket_id)
      references ticket (id) on delete restrict on update restrict;

