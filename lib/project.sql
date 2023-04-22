--테이블 삭제
drop table service_user CASCADE CONSTRAINTS;
drop table subscribe_service CASCADE CONSTRAINTS;
--drop table category cascade CONSTRAINTS;
drop table payment_system CASCADE CONSTRAINTS;
drop table subscribing CASCADE CONSTRAINTS;

--시퀀스 삭제
drop SEQUENCE subscribe_service_id_seq;
drop SEQUENCE subscribing_id_seq;
drop SEQUENCE payment_system_id_seq;

--시퀀스 생성
create SEQUENCE subscribe_service_id_seq nocache;
create SEQUENCE subscribing_id_seq nocache;
create SEQUENCE payment_system_id_seq nocache;

--user 테이블
create table service_user(
    user_id varchar2(32) primary key, --유저 아이디
    user_pw varchar2(128),  --패스워드
    user_name varchar2(32), --이름
    user_email varchar2(64));   --이메일


--구독 서비스 테이블
create table subscribe_service(
--id number default subscribe_service_id_seq.nextVal primary key
    service_name    varchar2(32) primary key, --서비스 이름 ex)넷플릭스, 왓챠
    service_category varchar(32),   --카테고리 ex)동영상, 음악
--    service_info    varchar2(512),
    service_url     varchar2(256),  --홈페이지
    service_icon_url varchar2(256)  --아이콘 url
);

--카테고리 테이블
--create table category(
--    service_name varchar2(32) primary key,
--    service_category varchar2(32),
--    CONSTRAINT name_fk FOREIGN KEY(service_name) 
--    references subscribe_service(service_name) ON DELETE CASCADE
--);

--요금제 테이블
create table payment_system(
    id number default payment_system_id_seq.nextVal primary key,    --id
    service_name varchar2(32),  --구독 서비스 이름
    tier_name varchar2(32), --요금제 이름 ex) 프리미엄 , 베이직
    info varchar2(512), -- 요금제 정보
    fee number,     --요금
    num_people number,  --동시 시청인원 ex)1 , 4
    constraint payment_service_fk foreign key(service_name) references subscribe_service(service_name)  --fk 제약조건
);

create table subscribing(
    id number default subscribing_id_seq.nextVal primary key,   --id
    user_id varchar2(32),   --유저 아이디
    paysys_id number,   --요금제 id
    num_of_share number,    --공유하고 있는 인원
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



--서비스 테이블
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('웨이브','영상',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('멜론','음악',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('지니','음악',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('스포티파이','음악',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('유튜브뮤직','음악',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('XBox Game Pass','게임',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('Apple One','게임',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('PlayStation Plus','게임',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('밀리의 서제','서적',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('리디셀렉트','서적',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('예스24 북클럽','서적',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('교보문고 sam','서적',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('넷플릭스','영상',null,null);
Insert into PROJECT.SUBSCRIBE_SERVICE (SERVICE_NAME,SERVICE_CATEGORY,SERVICE_URL,SERVICE_ICON_URL) values ('왓챠','영상',null,null);


Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('넷플릭스','프리미엄','○ 영화,시리즈,모바일게임 무제한 이용
○ UHD로시청가능
',17000,4);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('왓챠','베이직','○ 지원하는 최대화질 FUll HD
○ 저장가능한 다운로드 영상수 5개
',7900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('왓챠','프리미엄','○ Ultra HD 4K
 시청가능
○ 저장가능한 다운로드 영상수 100개
',12900,4);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('웨이브','베이직','○ 공중파 예능,드라마 재방송 위주로제공
○ HD 화질
',7900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('웨이브','프리미엄','○ 다운로드 무제한, 영화 6천편 무제한 시청
○ FHD 화질',13900,4);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('멜론','스트리밍플러스티켓30일','○ 온,오프라인 무제한 듣기가능
○ 데이터 연결없이 음악감상
',11400,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('지니','데이터세이프음악감상','○ 데이터 연결없이 음악감상
○ ’데이터세이프설정’및 음질설정 가능
',10900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('스포티파이','개인','○ 광고없이 음악감상하기
○ 다운로드하여 오프라인에서 감상
',10900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('스포티파이','듀오','○ 광고없이 음악감상하기
○ 프리미엄 계정 2개
',16350,2);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('유튜브뮤직','베이직','○ 데이터 연결없이 음악감상',8690,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('유튜브뮤직','프리미엄','○ 데이터 연결 없이 음악감상
○ 광고없음
○ 백그라운드에서 음악감상 가능',10450,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('XBox Game Pass','gold','○ 콘솔, PC 버전 별도 구매 필요
○ PC에서 수백 가지 고품질 게임 플레이 새로운 게임이 계속 추가됩니다
○ Xbox Game Studios는 출시 당일에 게임
을 제공
○회원 특별 할인 및 행사
',11900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('XBox Game Pass','ultimate','○ 콘솔, PC, 클라우드에서 수백 가지 고품질 게임 플레이
○ 새로운 게임이 계속 추가됩니다
○ Xbox Game Studios는 출시 당일에 게임을 제공
○ 독점 회원 특별 할인 및 행사
○ 게임 내 콘텐츠 및 파트너 제품을 포함한 무료 혜택
',7900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('Apple One','개인','○ Music
○ TV+
○ Arcade
○ iCloud 50GB
',14900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('Apple One','가족','○ Music
○ TV+
○ Arcade
○ iCloud 200GB
',20900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('PlayStation Plus','에센셜','○ 월간게임 지원
○ 온라인 멀티플에이어 가능
○ 독점 할인
',7500,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('PlayStation Plus','디럭스','○ 월간게임 지원
○ 온라인 멀티플에이어 가능
○ 독점 할인
○ 클라우드 스토리지
○ 유비소프트 컨텐츠 사용 가능
',12900,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('밀리의 서제','전자책','○ 10만권의 전자책
○ 오디오북, 챗북 기능
',9900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('밀리의 서제','전자책','○ 10만권의 전자책
○ 오디오북,챗북 기능
○ 2달에 한번 종이책 배송
',15900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('리디셀렉트','-','○ 리디페이퍼 사용
○ 편리한 UI
○ 웹소설, 웹툰 등 다양한 장르
',4900,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('예스24 북클럽','55요금제','○ 동시접속자 5대 가능
○ e북 리더기인 크레마 단말기 사용 할 경우 7대 까지 동사 사용 가능
',5500,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('예스24 북클럽','77요금제','○ 동시접속자 5대 가능
○ e북 리더기인 크레마 단말기 사용 할 경우 7대 까지 동사 사용 가능
○ 첫달 1500원 지급 및 다음달 부터 매달 4500원 북클럽 머니 지급으로 예스24에서 종이책 구매 가능
',7700,5);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('교보문고 sam','sam 베이직','○ 전공서적, 수험서등을 지원
○ 2권 제공
○ 180일 동안 열람
',7000,1);
Insert into PROJECT.PAYMENT_SYSTEM (SERVICE_NAME,TIER_NAME,INFO,FEE,NUM_PEOPLE) values ('교보문고 sam','sam 무제한','○ 전공서적, 수험서등을 지원
○ 무제한 열람 가능
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
