# 원티드 프리 온보딩 과제

# Unit Test

### JobPostController (채용 공고 등록, 수정, 삭제, 조회, 검색 컨트롤러)
https://github.com/johan1103/wanted-pre-onboarding-backend/issues/14

### JobPostService (채용 공고 등록, 수정, 삭제, 조회, 검색 서비스 로직 수행)
https://github.com/johan1103/wanted-pre-onboarding-backend/issues/15

### Application Controller & Service etc (지원서 저장 컨트롤러 & 서비스, EntityValidator)
https://github.com/johan1103/wanted-pre-onboarding-backend/issues/20

### 결과
전체 테스트 커버리지 90% 달성
<img width="1440" alt="image" src="https://github.com/johan1103/wanted-pre-onboarding-backend/assets/71641610/84476eda-abbc-4945-95ee-407d9fab88ea">

> 테스트커버리지 측정 결과 파일
> 
[jacocoHtml.zip](https://github.com/johan1103/wanted-pre-onboarding-backend/files/12906286/jacocoHtml.zip)

# 요구사항 구현 과정

## 채용 공고 등록

> `EntityValidator`
> 

Entity를 저장하는 `JpaRepository`로 회사 정보를 가져오고, 회사 정보가 존재하지 않으면 잘못된 요청을 한 것이므로 Exception을 Throw해주면 되지만, 이를 전부 `JobPostService`에서 작성해주면 코드의 가독성이 떨어져 보이는 문제가 있었습니다.

또한 엔티티로부터 정보를 가져오면서 조회된 엔티티가 없을 시 Exception을 발생시키는 로직은 자주 사용하는 로직이기에, `EntityValidator` 클래스를 만들어 엔티티들에 대한 validation 책임(기능)은 해당 객체에게 위임해주었습니다.

🌐 [EntityValidator 코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/service/EntityValidator.java)

`Company`정보를 조회하고, 요청받은 데이터를 토대로 `JobPost`를 생성한 이후에, 해당 Entity를 `JobPostRepository`를 통해 insert 쿼리를 생성하고 DB에 요청합니다.

<details>
<summary>Request</summary>
<div markdown="1">

    URI : "/job-post"
    METHOD : "POST"
    REQUEST BODY [JSON] :
    {
        "position":"back-end",
        "content":"wanted labs open recruit!",
        "skills":"java,python,spring boot",
        "compensation":1000000,
        "companyId": 1
    }
</div>
</details>

<details>
<summary>Response</summary>
<div markdown="1">

    {
        "jobPostId": 25
    }
</div>
</details>

## 채용 공고 수정

> `DirtyChecking`
> 

요청받은 RequestBody를 토대로 영속화된 `JobPost` 엔티티의 내부 필드를 변경해서 업데이트를 하는 `Dirty Checking` Update를 사용해서 update를 구현했습니다.

변경 되길 원하는 필드만 선택해서 수정 요청을 할 수 있기 때문에 변경을 원하지 않는 field들은 Request Body인 `JobPostUpdateRequest` 내부에 null값인 상태이고, 변경을 원하는 값은 값이 채워져 있는 상태입니다.

null이 아닌 필드만 `JobPost`의 필드값으로 업데이트 시켜주었습니다.

🌐 [JobPost 코드(필드값 변경 함수)](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/entity/JobPost.java)


<details>
<summary>Request</summary>
<div markdown="1">
    
    URI : "/jop-post"
    METHOD : "PUT"
    REQUEST BODY [JSON] :
    {
        "id":1,
        "position":"front-end",
        "recruitContent":"open!",
        "skills":"angularJs,react,react-native",
        "compensation":3000000
    }
</div>
</details>


<details>
<summary>Response</summary>
<div markdown="1">
	
    {
        "id": 1,
        "position": "front-end",
        "recruitContent": "open!",
        "skills": "angularJs,react,react-native",
        "compensation": 3000000
    }	
</div>
</details>
    

## 채용 공고 삭제

> `Delete 과정` `DeletedApplicationLetter` `N+1 (join fetch)`
> 

채용공고를 삭제하고자 하는 주체는 회사가 될 것이고, 해당 공고에 지원한 지원자가 있더라도 회사 내부 사정으로 인하여 채용공고가 삭제될 일은 충분히 있을 수 있을 것이라고 생각했습니다.

또한 지원서가 삭제가 되면 해당 채용 공고에 지원한 지원자가 채용공고가 삭제되었다는 소식을 알아야 한다고 생각했습니다.

따라서 채용공고 엔티티인 `JobPost`를 삭제하기 이전에, 해당 `JobPost`에 지원한 `ApplicationLetter` 엔티티(채용 공고 지원서)를 먼저 삭제하는 방식으로 서비스 로직을 구성했습니다.

지원자에게 삭제된 채용 공고 정보를 알려주기 위한 `DeletedApplicationLetter` 엔티티를 구현했습니다. 해당 엔티티는 삭제된 채용 공고에 관한 포지션, 채용회사 정보들을 가지며, `User` 엔티티와 `N:1` 관계를 갖습니다.

🌐 [DeletedApplicationLetter 코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/entity/DeletedApplicationLetter.java)

`DeletedApplicationLetter` 을 생성하는 과정에서 ApplicationLetter 엔티티 내부의 Company와 User 엔티티가 필요했기 때문에 `List<DeletedApplicationLetter>`를 생성하는 과정에서 N+1 문제가 발생했습니다.  이를 해결하기 위해 `join fetch` 쿼리를 사용했습니다.

🌐 [fetch join jpql 쿼리 코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/repository/ApplicationLetterRepository.java)



<details>
<summary>Request</summary>
<div markdown="1">

    URI : "/jop-post"
    METHOD : "DELETE"
    REQUEST BODY [JSON] :
    {
        "jobPostId":1
    }
    
</div>
</details>
    
<details>
<summary>Response</summary>
<div markdown="1">
    
    {
        "deleteSuccess": true
    }
    
</div>
</details>

## 채용 공고 목록

> `페이지네이션` `N+1`
> 

채용 공고 목록 전체를 쿼리를 통해 가져오게 될 경우, 데이터가 너무 많으면 메모리 초과가 날 수 있고 쿼리의 성능 또한 느려질 수 있는 문제가 있었습니다.

이를 해결하기 위한 대표적인 방식인 페이지네이션을 적용했습니다. 따라서 응답 값에는 채용 공고 리스트, 현재 페이지, 제공된 데이터 크기, 다음 페이지 존재 여부가 들어가는 방식으로 구성했습니다.

응답 데이터를 생성하는 과정에서 `JobPost` 엔티티의 정보 뿐만이 아닌 Company 엔티티 필드값도 필요했기에 N+1 문제가 발생하는 문제가 있었습니다. 이를 방지하기 위해 `join fetch`쿼리를 적용했습니다.

🌐 [JobPostRepository 쿼리코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/repository/JobPostRepository.java)


<details>
<summary>Request</summary>
<div markdown="1">
	
    URI : "/job-post/list"
    METHOD : "GET"
    QUERY-PARAMETER : page=0 [int] & size=10 [int]
    
</div>
</details>


<details>
<summary>Response</summary>
<div markdown="1">
    
    {
        "page": 0,
        "size": 10,
        "hasNext": true,
        "jobPosts": [
            {
                "jobPostId": 3,
                "companyName": "네이버",
                "country": "한국",
                "region": "정자",
                "position": "back-end[system-monitoring]",
                "compensation": 1000000,
                "skills": "java,python,spring boot,django"
            },
            {
                "jobPostId": 6,
                "companyName": "네이버",
                "country": "한국",
                "region": "정자",
                "position": "back-end[pay-service] junior",
                "compensation": 1000000,
                "skills": "java,python,spring boot,django"
            },
    				....
        ]
    }
</div>
</details>
    

## 채용 공고 검색

> `like`
> 

채용 공고를 검색하기 위해선 검색 키워드가 필요했고, 회사 이름 or 사용 기술 or 채용 포지션을 키워드로 검색할 수 있도록 like 쿼리를 활용했습니다.

페이지네이션 전략은 채용 공고 목록과 동일하게 구현했습니다.

키워드가 존재한다는 특징 말고는 채용 공고 목록 조회와 동일한 목적을 가지는 API라고 생각해서 채용 공고 목록과 똑같은 URI를 사용했습니다. 쿼리 파라미터로 keyword가 주어진다면 채용 공고 검색 서비스 로직을 수행하고 주어지지 않는다면 전체 채용 공고 목록 조회 서비스 로직을 수행하도록 컨트롤러 부분을 구성했습니다.

🌐 [JobPostController 코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/controller/JobPostController.java)

<details>
<summary>Request</summary>
<div markdown="1">
	
    URI : "/job-post/list"
    METHOD : "GET"
    QUERY-PARAMETER : page=0 [int] & size=10 [int] & keyword="spring" [string]
    
</div>
</details>

<details>
<summary>Response</summary>
<div markdown="1">
	
    {
        "page": 0,
        "size": 10,
        "hasNext": true,
        "jobPosts": [
            {
                "jobPostId": 3,
                "companyName": "네이버",
                "country": "한국",
                "region": "정자",
                "position": "back-end[system-monitoring]",
                "compensation": 1000000,
                "skills": "java,python,spring boot,django"
            },
            {
                "jobPostId": 6,
                "companyName": "네이버",
                "country": "한국",
                "region": "정자",
                "position": "back-end[pay-service] junior",
                "compensation": 1000000,
                "skills": "java,python,spring boot,django"
            },
    				....
        ]
    }
    
</div>
</details>
    

## 채용 상세

> `채용 목록과 동일 URI` `Stream api`
> 

채용 상세 정보를 확인하기 위해서 JobPost정보를 DB로부터 가져옵니다.

해당 공고를 올린 회사의 다른 채용 공고를 가져와서 보여주기 위해 join fetch 쿼리를 사용해 연관관계가 매핑된 Company까지 엔티티를 주입해서 가져왔습니다.

가져온 이후 Stream api를 사용해서 현재 조회하고 있는 JobPost인 경우 리스트에서 제외시켜주도록 로직을 구성했습니다.

🌐 [JobPostService detail 코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/service/JobPostService.java)
🌐 [Stream api 활용 코드](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/java/com/wanted/wantedlab/dto/jobPost/response/JobPostDetailInfo.java)


<details>
<summary>Request</summary>
<div markdown="1">
	
    URI : "/job-post/detail"
    METHOD : "GET"
    QUERY-PARAMETER : job-post-id=1 [int]
    
</div>
</details>
    
<details>
<summary>Response</summary>
<div markdown="1">
	
    {
        "jobPostId": 3,
        "companyName": "네이버",
        "country": "한국",
        "region": "정자",
        "position": "back-end[system-monitoring]",
        "compensation": 1000000,
        "skills": "java,python,spring boot,django",
        "recruitContent": "naver open recruit!",
        "companyOtherJobPosts": [
            {
                "jobPostId": 4,
                "position": "front-end",
                "skills": "react,react-natvie"
            },
            {
                "jobPostId": 5,
                "position": "ai-research",
                "skills": "pytorch"
            },
            {
                "jobPostId": 6,
                "position": "back-end[pay-service] junior",
                "skills": "java,python,spring boot,django"
            },
            {
                "jobPostId": 7,
                "position": "back-end[searching-service] junior",
                "skills": "java,python,spring boot,django"
            }
        ]
    }

</div>
</details>
    

## 채용 공고 지원

> `unique키`
> 

요청 받은 공고 지원 정보 중에 사용자와 채용공고의 유효성을 검사한 뒤 엔티티로 가져옵니다. 그 뒤에 ApplicationLetter 엔티티에게 객체 조립 및 생성의 책임을 위임합니다. 이후에 Respoitory 계층을 통해 엔티티를 영속화하는 단계로 서비스 로직을 구성했습니다.

한 지원하는 하나의 공고에 한번의 지원만 허용되므로 이러한 요구사항은 mysql `application-letter` 테이블의 user-id와 job-post-id의 `복합 unique키`를 설정해서 구현했습니다.

🌐 [데이터베이스 스키마 설정 코드(schema.sql)](https://github.com/johan1103/wanted-pre-onboarding-backend/blob/main/src/main/resources/schema.sql)

<details>
<summary>Request</summary>
<div markdown="1">
	
    URI : "/apply"
    METHOD : "POST"
    REQUEST-BOSY [JSON]: 
    {
        "userId":"google@sg1214fgs23h",
        "jobPostId":1,
        "portfolioUrl":"https://github.com/johan1103"
    }
</div>
</details>
    
    
<details>
<summary>Response</summary>
<div markdown="1">
	
    {
        "applyId": 1,
        "jobPostId": 3,
        "userId": "google@sg1214fgs23h"
    }

</div>
</details>

## ERD 설계

<img src="https://user-images.githubusercontent.com/71641610/272816260-80387439-7301-4758-b18c-f6ede33798b2.png" width="700"/>

## application-config.properties
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/"schema name"
spring.datasource.username="database user name"
spring.datasource.password="database password"
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.default_batch_fetch_size=600

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

