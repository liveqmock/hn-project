/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/10/26 12:55:05                          */
/*==============================================================*/


drop table if exists blob_data;

drop table if exists date_price;

drop table if exists hotel;

drop table if exists hotel_room;

drop table if exists itinerary;

drop table if exists itinerary_scenic;

drop table if exists picture;

drop table if exists room_kind;

drop table if exists scenic;

drop table if exists scenic_ticket;

drop table if exists ticket;

drop table if exists ticket_kind;

/*==============================================================*/
/* Table: blob_data                                             */
/*==============================================================*/
create table blob_data
(
   id                   bigint not null auto_increment comment '����',
   data                 text comment '����',
   primary key (id)
);

/*==============================================================*/
/* Table: date_price                                            */
/*==============================================================*/
create table date_price
(
   id                   bigint not null auto_increment comment '����',
   kind_id              bigint not null comment 'Ʊ��ID',
   pdate                date not null comment '����',
   market_price         double not null comment '�г���',
   now_price            double not null comment '�ּ�',
   total_num            int not null comment '������',
   remain_num           int not null comment 'ʣ������',
   primary key (id)
);

alter table date_price comment 'Ʊ�����ڼ۸�';

/*==============================================================*/
/* Table: hotel                                                 */
/*==============================================================*/
create table hotel
(
   id                   bigint not null auto_increment comment '����',
   name                 varchar(50) not null comment '����',
   title                varchar(100) comment 'С����',
   img_uri              varchar(200) not null comment '��ͼ��ַ',
   area_code            varchar(50) not null comment '�������صı���',
   address              varchar(200) not null comment '��ϸ��ַ',
   longitude            double comment '�ڵ�ͼ�ϵľ���',
   latitude             double comment '�ڵ�ͼ�ϵ�γ��',
   services             varchar(20) comment '֧�ֵķ���1����ͣ������2����WIFI������ö��ŷָ�',
   facility_id          bigint comment '��ʩ����',
   position_id          bigint comment '��ͨλ��',
   low_price            double comment '��ͼ۸�',
   good_rate            numeric(5,2) default '100.00' comment '������(%)',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

alter table hotel comment '�Ƶ�';

/*==============================================================*/
/* Table: hotel_room                                            */
/*==============================================================*/
create table hotel_room
(
   id                   bigint not null auto_increment comment '����',
   hotel_id             bigint not null comment '�Ƶ�ID',
   name                 varchar(50) not null comment '����',
   info_id              bigint comment '������Ϣ',
   cost_id              bigint comment '����˵��',
   notice_id            bigint comment 'Ԥ����֪',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

alter table hotel_room comment '�Ƶ귿��';

/*==============================================================*/
/* Table: itinerary                                             */
/*==============================================================*/
create table itinerary
(
   id                   bigint not null comment '����',
   name                 varchar(50) not null comment '����',
   title                varchar(100) comment 'С����',
   img_uri              varchar(200) not null comment '��ͼ��ַ',
   type                 int not null comment '���͡�1�������У�2�������Ρ�',
   market_price         double not null comment '�г���',
   now_price            double not null comment '�ּ�',
   pay_type             int not null comment '֧����ʽ��1������֧����2������֧��',
   price_instructio     varchar(500) comment '���˵��',
   issue_place          varchar(50) comment '������',
   end_place            varchar(50) not null comment 'Ŀ�ĵ�',
   feature_id           bigint comment '��Ʒ��ɫ',
   explain_id           bigint comment '�г�˵��',
   prompt_id            bigint comment '��Ҫ��ʾ',
   traffic_id           bigint comment '��ͨ��Ϣ',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

alter table itinerary comment '������·�������С������Ρ�';

/*==============================================================*/
/* Table: itinerary_scenic                                      */
/*==============================================================*/
create table itinerary_scenic
(
   id                   bigint not null comment '����ID',
   itinerary_id         bigint not null comment '��·ID',
   primary key (id, itinerary_id)
);

alter table itinerary_scenic comment '��·�����ľ���';

/*==============================================================*/
/* Table: picture                                               */
/*==============================================================*/
create table picture
(
   id                   bigint not null auto_increment comment '����',
   name                 varchar(50) not null comment '����',
   ref_id               bigint not null comment '��������ID',
   ref_type             int not null comment '�����������͡�1�����㣻2���Ƶꣻ3����·',
   desciption           varchar(300) comment '����',
   raw_uri              varchar(200) not null comment 'ԭͼ��ַ',
   arge_uri             varchar(200) comment '��ͼ��ַ',
   middle_uri           varchar(200) comment '��ͼ��ַ',
   small_uri            varchar(200) comment 'Сͼ��ַ',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   status               int not null default 0 comment '״̬��0��������1��δ����',
   primary key (id)
);

/*==============================================================*/
/* Table: room_kind                                             */
/*==============================================================*/
create table room_kind
(
   id                   bigint not null auto_increment comment '����',
   room_id              bigint not null comment '����ID',
   name                 varchar(50) not null comment '����',
   bed                  varchar(100) not null comment '����',
   breakfast            int not null default 0 comment '��͡�0�����磻1������',
   broadband            int not null default 0 comment '�����0����ѣ�1���ޣ�2������',
   market_price         double not null comment '�г���',
   now_price            double not null comment '�ּ�',
   room_count           int not null comment 'ÿ���Ԥ������',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

alter table room_kind comment '��������';

/*==============================================================*/
/* Table: scenic                                                */
/*==============================================================*/
create table scenic
(
   id                   bigint not null auto_increment comment '����',
   name                 varchar(50) not null comment '����',
   title                varchar(100) comment 'С����',
   img_uri              varchar(200) not null comment '��ͼ��ַ',
   area_code            varchar(50) not null comment '�������صı���',
   address              varchar(200) not null comment '��ϸ��ַ',
   longitude            double comment '�ڵ�ͼ�ϵľ���',
   latitude             double comment '�ڵ�ͼ�ϵ�γ��',
   services             varchar(20) comment '֧�ֵķ���1����԰��֤��2��������԰��3����ʱ�ˣ�4������⡣����ö��ŷָ�',
   notice_id            bigint comment 'Ԥ����֪',
   in_time              varchar(20) comment '��԰ʱ��',
   introduce_id         bigint comment '�������',
   traffic_id           bigint comment '��ָͨ��',
   low_price            double comment '��ͼ۸�',
   good_rate            numeric(5,2) default '100.00' comment '������(%)',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

alter table scenic comment '����';

/*==============================================================*/
/* Table: scenic_ticket                                         */
/*==============================================================*/
create table scenic_ticket
(
   id                   bigint not null comment '����',
   scenic_id            bigint not null comment '����ID',
   name                 varchar(50) not null comment '����',
   pay_type             int comment '֧����ʽ��1������֧����2������֧��',
   type                 int not null comment '���͡�1������Ʊ��2������ײ�',
   agreement_id         bigint comment 'Ԥ��Э��',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
   id                   bigint not null auto_increment comment '����',
   cost_id              bigint comment '����˵��',
   notice_id            bigint comment 'Ԥ����֪',
   refund_id            bigint comment '�˿�˵��',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   primary key (id)
);

alter table ticket comment 'Ʊ�ݡ�������Ʊ��·����Ʊ';

/*==============================================================*/
/* Table: ticket_kind                                           */
/*==============================================================*/
create table ticket_kind
(
   id                   bigint not null auto_increment comment '����',
   ticket_id            bigint not null comment 'Ʊ��ID',
   name                 varchar(50) not null comment '����',
   desciption           varchar(300) comment '����',
   market_price         double not null comment '�г���',
   now_price            double not null comment '�ּ�',
   create_time          datetime not null comment '����ʱ��',
   update_time          datetime not null comment '����ʱ��',
   status               int not null default 0 comment '״̬��0��������1���¼�',
   primary key (id)
);

alter table ticket_kind comment 'Ʊ��';

alter table date_price add constraint FK_Reference_10 foreign key (kind_id)
      references ticket_kind (id) on delete restrict on update restrict;

alter table hotel add constraint FK_Reference_19 foreign key (facility_id)
      references blob_data (id) on delete restrict on update restrict;

alter table hotel add constraint FK_Reference_20 foreign key (position_id)
      references blob_data (id) on delete restrict on update restrict;

alter table hotel_room add constraint FK_Reference_24 foreign key (hotel_id)
      references hotel (id) on delete restrict on update restrict;

alter table hotel_room add constraint FK_Reference_25 foreign key (info_id)
      references blob_data (id) on delete restrict on update restrict;

alter table hotel_room add constraint FK_Reference_26 foreign key (cost_id)
      references blob_data (id) on delete restrict on update restrict;

alter table hotel_room add constraint FK_Reference_27 foreign key (notice_id)
      references blob_data (id) on delete restrict on update restrict;

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

alter table room_kind add constraint FK_Reference_28 foreign key (room_id)
      references hotel_room (id) on delete restrict on update restrict;

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

