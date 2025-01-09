## Table 설계 

![image](https://github.com/user-attachments/assets/22390f06-4a41-4706-8445-eab4e9846baf)

- 상영(Screenging) 테이블이 영화(Movie),상영관(Theater) 테이블을 fk로 가지도록 설계.
- 예매 테이블과 좌석 테이블사이에 reservation_seat 중간 테이블을 설계함.

## Multi Module 설계 

1. Common 모듈
   - 공통적으로 사용되는 유틸리티 클래스, 상수, 공통 타입 DTO 등을 포함함.
   - 라이브러리 의존성 최소화 (Lombok 제외 순수 java 코드)
   - 필요한 모듈에서 의존성 추가 가능

2. Domain 모듈
   - 내부 비지니스 핵심 로직 (Entity, Service, Repository)
   - 공통 Dto(DataResponse 등)을 제외한 비지니스 로직에 밀집하게 관련된 dto 포함
   - 기능이 추가되면 각각의 Domain을 나눌예정 -> moviedomain , orderdomain ...

3. 어플리케이션 모듈
   - 실제 실행 가능한 모듈. 실제 통신에 관련된 로직 포함 (Controller) -> app-api
   - batch 작업같은 독립적인 어플리케이션등도 포함하는것으로 생각했음 -> app-batch와 같이 추가 예정
   - Controller와 밀접하게 관련되어있는 GlobalExceptiopnHandler 까지 포함.
   - Common, Domain 의존 
