# 원티드 프리 온보딩 과제

---
## 🛠️ 요구사항 분석

### **채용공고를 등록합니다.**

> 채용 공고 예시
>

```json
{
  "회사_id":회사_id,
  "채용포지션":"백엔드 주니어 개발자",
  "채용보상금":1000000,
  "채용내용":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "사용기술":"Python"
}
```

채용 공고는 회사의 인력을 뽑기 위한 과정이기 때문에 `회사_id` 정보는 필수로 주어져야 합니다.

채용 포지션은 회사의 문화마다 다양한 종류가 있을 수 있지만 채용 포지션이 없는 채용공고는 없으므로 `채용포지션` 정보는 필수로 주어져야 합니다.

채용 보상금은 기업의 채용을 위한 웹 서비스가 자체적으로 운영하는 정보이기 때문에 채용 보상금 정보가 없을 수 있습니다. 보상금이 0원일 때랑 보상금이 책정되지 않았을 때는 다른 의미를 가지기 때문에 `채용보상금` 정보는 존재하지 않을 수 있습니다.

채용 내용은 지원자격에 대한 여러 세부 사항이 들어가기 때문에 `채용내용` 정보는 필수로 주어져야 합니다.

지원자가 지원하고자 하는 채용 공고에 맞는 직무 역량을 사용 기술을 통해 파악할 수 있으므로 `사용기술` 정보는 필수적으로 주어져야 합니다.

### **채용공고를 수정합니다.**

> 공고 수정 예시
>

```json
{
  "채용포지션":"백엔드 주니어 개발자",
  "채용보상금":1500000, # 변경됨
  "채용내용":"원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", # 변경됨
  "사용기술":"Python"
}

or

{
  "채용포지션":"백엔드 주니어 개발자",
  "채용보상금":1000000,
  "채용내용":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
  "사용기술":"Django" # 변경됨
}
```

회사 id외에 모든 값이 수정가능하고, 수정을 하기 위해서는 채용 공고의 식별 정보가 필수적으로 필요합니다.

채용 포지션, 채용 보상금, 채용 내용, 사용 기술 중 원하는 정보만을 변경할 수 있기 때문에 수정 API를 요청할 때 수정이 필요한 필드 외에는 주어지지 않을 수 있습니다.

### **채용공고를 삭제합니다.**

채용 공고를 삭제하는 것은 전적으로 회사의 권한이므로 채용 공고에 지원한 지원자가 있더라도 삭제될 수 있어야 합니다. 이때 삭제될 채용 공고에 지원한 지원 정보도 같이 삭제되어야 합니다.

하지만 지원자도 채용 공고가 삭제되었다는 정보를 알아야 할 필요가 있기 때문에 이러한 정보를 알 수 있도록 삭제된 공고의 간단한 정보에 대한 데이터를 제공해야 합니다.

### **채용공고 목록을 가져옵니다.**

> 공고 목록 예시
>

```json
[
	{
		"채용공고_id": 채용공고_id,
	  "회사명":"원티드랩",
	  "국가":"한국",
	  "지역":"서울",
	  "채용포지션":"백엔드 주니어 개발자",
	  "채용보상금":1500000,
	  "사용기술":"Python"
	},
	{
		"채용공고_id": 채용공고_id,
	  "회사명":"네이버",
	  "국가":"한국",
	  "지역":"판교",
	  "채용포지션":"Django 백엔드 개발자",
	  "채용보상금":1000000,
	  "사용기술":"Django"
	},
  ...
]
```

채용 공고 목록을 가져올 때 전체 목록을 가져오려고 한다면, 채용 공고의 양이 많은 경우 힙 메모리 부족 현상이 발생할 수 있습니다. 이를 방지하기 위해서 일정 개수만큼 끊어서 가져오는 페이지네이션 방식으로 목록을 가져와야 합니다.

페이지네이션으로 공고 목록 기능을 구현한다면 클라이언트를 위해 응답 정보에 공고 목록 뿐이 아닌 현재 페이지와 가져온 데이터 개수, 다음 페이지 존재 여부를 알려줘야 합니다.

### **채용 상세 페이지를 가져옵니다.**

> 채용 상세 페이지 예시
>

```json
Example)
{
	"채용공고_id": 채용공고_id,
  "회사명":"원티드랩",
  "국가":"한국",
  "지역":"서울",
  "채용포지션":"백엔드 주니어 개발자",
  "채용보상금":1500000,
  "사용기술":"Python",
	"채용내용": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
	"회사가올린다른채용공고":[채용공고_id, 채용공고_id, ..] # id List **(선택사항 및 가산점요소).**
}
```

채용 상세 정보를 얻기 위해 채용 공고 식별자는 필수적으로 요청 정보에 포함되어야 합니다.

### **사용자는 채용공고에 지원합니다(선택사항 및 가산점요소).**

### 채용공고 검색 기능 구현**(선택사항 및 가산점요소).**

> 검색 예시
>

```json
# Example - 1) some/url?**search=원티드**
[
	{
		"채용공고_id": 채용공고_id,
	  "회사명":"원티드랩",
	  "국가":"한국",
	  "지역":"서울",
	  "채용포지션":"백엔드 주니어 개발자",
	  "채용보상금":1500000,
	  "사용기술":"Python"
	},
	{
		"채용공고_id": 채용공고_id,
	  "회사명":"원티드코리아",
	  "국가":"한국",
	  "지역":"부산",
	  "채용포지션":"프론트엔드 개발자",
	  "채용보상금":500000,
	  "사용기술":"javascript"
	}
]
# Example - 2) some/url?**search=Django**
[
	{
		"채용공고_id": 채용공고_id,
	  "회사명":"네이버",
	  "국가":"한국",
	  "지역":"판교",
	  "채용포지션":"Django 백엔드 개발자",
	  "채용보상금":1000000,
	  "사용기술":"Django"
	},
	{
		"채용공고_id": 채용공고_id,
	  "회사명":"카카오",
	  "국가":"한국",
	  "지역":"판교",
	  "채용포지션":"Django 백엔드 개발자",
	  "채용보상금":500000,
	  "사용기술":"Python"
	}
  ...
]
```

## ERD 설계
