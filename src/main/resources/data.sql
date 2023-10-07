use wanted_labs;
INSERT INTO user (id, nickname) VALUES ('kakao@14123','virtual user 1');
INSERT INTO user (id, nickname) VALUES ('google@sg1214fgs23h','virtual user 2');
INSERT INTO user (id, nickname) VALUES ('johan1103','virtual user 3');

INSERT INTO company (id,name, country, region) VALUES (1,'네이버','한국','정자');
INSERT INTO company (id,name, country, region) VALUES (2,'원티드','한국','서울');
INSERT INTO company (id,name, country, region) VALUES (3,'카카오','한국','판교');
INSERT INTO company (id,name, country, region) VALUES (4,'쿠팡','한국','판교');
INSERT INTO company (id,name, country, region) VALUES (5,'앨리스','한국','선릉');

INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[pay-service]','naver open recruit!','java,python,spring boot,django',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[searching-service]','naver open recruit!','java,python,spring boot,django',2000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[system-monitoring]','naver open recruit!','java,python,spring boot,django',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('front-end','naver open recruit!','react,react-natvie',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('ai-research','naver open recruit!','pytorch',2000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[pay-service] junior','naver open recruit!','java,python,spring boot,django',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[searching-service] junior','naver open recruit!','java,python,spring boot,django',2000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[system-monitoring] junior','naver open recruit!','java,python,spring boot,django',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('front-end junior','naver open recruit!','react,react-natvie',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('ai-research junior','naver open recruit!','pytorch',2000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[pay-service] senior','naver open recruit!','java,python,spring boot,django',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[searching-service] senior','naver open recruit!','java,python,spring boot,django',2000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[system-monitoring] senior','naver open recruit!','java,python,spring boot,django',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('front-end senior','naver open recruit!','react,react-natvie',1000000,1);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('ai-research senior','naver open recruit!','pytorch',2000000,1);

INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end','wanted labs open recruit!','java,python,spring boot,django',1000000,2);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[system-monitoring]','wanted labs recruit!','javascript,nodejs',2000000,2);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end','kakao open recruit!','java,python,spring boot,django',1000000,3);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('front-end','kakao open recruit!','react,react-natvie',1000000,3);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('ai-research','coupang open recruit!','pytorch',2000000,4);

INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end','elice open recruit!','java,python,spring boot,django',1000000,5);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end[system-monitoring]','elice labs recruit!','javascript,nodejs',2000000,5);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('back-end','elice open recruit!','java,python,spring boot,django',1000000,5);
INSERT INTO job_post (position, recruit_content, skills, compensation, company_id)
VALUES ('front-end','elice open recruit!','react,react-natvie',1000000,5);