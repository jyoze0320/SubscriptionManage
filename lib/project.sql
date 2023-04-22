--���̺� ����
drop table service_user CASCADE CONSTRAINTS;
drop table subscribe_service CASCADE CONSTRAINTS;
--drop table category cascade CONSTRAINTS;
drop table payment_system CASCADE CONSTRAINTS;
drop table subscribing CASCADE CONSTRAINTS;

--������ ����
drop SEQUENCE subscribe_service_id_seq;
drop SEQUENCE subscribing_id_seq;
drop SEQUENCE payment_system_id_seq;

--������ ����
create SEQUENCE subscribe_service_id_seq nocache;
create SEQUENCE subscribing_id_seq nocache;
create SEQUENCE payment_system_id_seq nocache;

--user ���̺�
create table service_user(
    user_id varchar2(32) primary key, --���� ���̵�
    user_pw varchar2(128),  --�н�����
    user_name varchar2(32), --�̸�
    user_email varchar2(64));   --�̸���


--���� ���� ���̺�
create table subscribe_service(
--id number default subscribe_service_id_seq.nextVal primary key
    service_name    varchar2(32) primary key, --���� �̸� ex)���ø���, ��í
    service_category varchar(32),   --ī�װ� ex)������, ����
--    service_info    varchar2(512),
    service_url     varchar2(256),  --Ȩ������
    service_icon_url varchar2(256)  --������ url
);

--ī�װ� ���̺�
--create table category(
--    service_name varchar2(32) primary key,
--    service_category varchar2(32),
--    CONSTRAINT name_fk FOREIGN KEY(service_name) 
--    references subscribe_service(service_name) ON DELETE CASCADE
--);

--����� ���̺�
create table payment_system(
    id number default payment_system_id_seq.nextVal primary key,    --id
    service_name varchar2(32),  --���� ���� �̸�
    tier_name varchar2(32), --����� �̸� ex) �����̾� , ������
    info varchar2(512), -- ����� ����
    fee number,     --���
    num_people number,  --���� ��û�ο� ex)1 , 4
    constraint payment_service_fk foreign key(service_name) references subscribe_service(service_name)  --fk ��������
);

create table subscribing(
    id number default subscribing_id_seq.nextVal primary key,   --id
    user_id varchar2(32),   --���� ���̵�
    paysys_id number,   --����� id
    num_of_share number,    --�����ϰ� �ִ� �ο�
    constraint subscribing_payment_pk foreign key(paysys_id) references payment_system(id),
    constraint subscribing_user_pk foreign key(user_id) references service_user(user_id),
    constraint subscribing_uk unique(user_id,paysys_id)
);
alter table service_user add user_role varchar2(8) default 'USER';

create or replace view payment_with_category
as
select ps.id as id, ps.service_name as service_name, ps.tier_name as tier_name, ps.fee as fee,
ps.num_people as num_people, ss.service_category as service_category  
from payment_system ps inner join SUBSCRIBE_SERVICE ss on ps.service_name = ss.service_name;


create or replace view category
as
select distinct service_category as category from subscribe_service;

commit;



--���� ���̺�
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('���̺�','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('���','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('����','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('����Ƽ����','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('��Ʃ�����','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('XBox Game Pass','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('Apple One','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('PlayStation Plus','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('�и��� ����','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('���𼿷�Ʈ','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('����24 ��Ŭ��','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('�������� sam','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('���ø���','����',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('��í','����',null,null);


Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('���ø���','�����̾�','�� ��ȭ,�ø���,����ϰ��� ������ �̿�
�� UHD�ν�û����
',17000,4);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('��í','������','�� �����ϴ� �ִ�ȭ�� FUll HD
�� ���尡���� �ٿ�ε� ����� 5��
',7900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('��í','�����̾�','�� Ultra HD 4K
 ��û����
�� ���尡���� �ٿ�ε� ����� 100��
',12900,4);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('���̺�','������','�� ������ ����,��� ���� ���ַ�����
�� HD ȭ��
',7900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('���̺�','�����̾�','�� �ٿ�ε� ������, ��ȭ 6õ�� ������ ��û
�� FHD ȭ��',13900,4);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('���','��Ʈ�����÷���Ƽ��30��','�� ��,�������� ������ ��Ⱑ��
�� ������ ������� ���ǰ���
',11400,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('����','�����ͼ��������ǰ���','�� ������ ������� ���ǰ���
�� �������ͼ������������� �������� ����
',10900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('����Ƽ����','����','�� ������� ���ǰ����ϱ�
�� �ٿ�ε��Ͽ� �������ο��� ����
',10900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('����Ƽ����','���','�� ������� ���ǰ����ϱ�
�� �����̾� ���� 2��
',16350,2);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('��Ʃ�����','������','�� ������ ������� ���ǰ���',8690,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('��Ʃ�����','�����̾�','�� ������ ���� ���� ���ǰ���
�� �������
�� ��׶��忡�� ���ǰ��� ����',10450,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('XBox Game Pass','gold','�� �ܼ�, PC ���� ���� ���� �ʿ�
�� PC���� ���� ���� ��ǰ�� ���� �÷��� ���ο� ������ ��� �߰��˴ϴ�
�� Xbox Game Studios�� ��� ���Ͽ� ����
�� ����
��ȸ�� Ư�� ���� �� ���
',11900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('XBox Game Pass','ultimate','�� �ܼ�, PC, Ŭ���忡�� ���� ���� ��ǰ�� ���� �÷���
�� ���ο� ������ ��� �߰��˴ϴ�
�� Xbox Game Studios�� ��� ���Ͽ� ������ ����
�� ���� ȸ�� Ư�� ���� �� ���
�� ���� �� ������ �� ��Ʈ�� ��ǰ�� ������ ���� ����
',7900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('Apple One','����','�� Music
�� TV+
�� Arcade
�� iCloud 50GB
',14900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('Apple One','����','�� Music
�� TV+
�� Arcade
�� iCloud 200GB
',20900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('PlayStation Plus','������','�� �������� ����
�� �¶��� ��Ƽ�ÿ��̾� ����
�� ���� ����
',7500,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('PlayStation Plus','�𷰽�','�� �������� ����
�� �¶��� ��Ƽ�ÿ��̾� ����
�� ���� ����
�� Ŭ���� ���丮��
�� �������Ʈ ������ ��� ����
',12900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('�и��� ����','����å','�� 10������ ����å
�� �������, ê�� ���
',9900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('�и��� ����','����å','�� 10������ ����å
�� �������,ê�� ���
�� 2�޿� �ѹ� ����å ���
',15900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('���𼿷�Ʈ','-','�� ���������� ���
�� ���� UI
�� ���Ҽ�, ���� �� �پ��� �帣
',4900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('����24 ��Ŭ��','55�����','�� ���������� 5�� ����
�� e�� �������� ũ���� �ܸ��� ��� �� ��� 7�� ���� ���� ��� ����
',5500,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('����24 ��Ŭ��','77�����','�� ���������� 5�� ����
�� e�� �������� ũ���� �ܸ��� ��� �� ��� 7�� ���� ���� ��� ����
�� ù�� 1500�� ���� �� ������ ���� �Ŵ� 4500�� ��Ŭ�� �Ӵ� �������� ����24���� ����å ���� ����
',7700,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('�������� sam','sam ������','�� ��������, ���輭���� ����
�� 2�� ����
�� 180�� ���� ����
',7000,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('�������� sam','sam ������','�� ��������, ���輭���� ����
�� ������ ���� ����
',9900,1);

create or replace view v_catelist
as
select a1.service_name, a1.tier_name, a1.info, a1.fee, a1.num_people, a2.service_category  
from PAYMENT_SYSTEM a1 inner join SUBSCRIBE_SERVICE a2 on a1.service_name = a2.service_name;


commit;


create or replace view v_catelist as
select a1.service_name, a1.tier_name, 
    a1.info, a1.fee, a1.num_people, a2.service_category,
    a1.id as id  
from PAYMENT_SYSTEM a1 
    inner join SUBSCRIBE_SERVICE a2 
    on a1.service_name = a2.service_name;

commit;
