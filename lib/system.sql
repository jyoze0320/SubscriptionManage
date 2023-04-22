create user project identified by project; --계정 생성
grant resource,connect to project; -- 리소스롤 connet 권한
grant create view to project;   -- 뷰 생성 권한
alter user project quota UNLIMITED on users; -- 용량 무제한