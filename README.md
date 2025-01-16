## Table 설계 

![image](https://github.com/user-attachments/assets/22390f06-4a41-4706-8445-eab4e9846baf)

- 상영(Screenging) 테이블이 영화(Movie),상영관(Theater) 테이블을 fk로 가지도록 설계.
- 예매 테이블과 좌석 테이블사이에 reservation_seat 중간 테이블을 설계함.

## Multi Module 설계 

1. Common 모듈
   - 공통적으로 사용되는 유틸리티 클래스, 상수, 공통 타입 DTO 등을 포함함.
   - 라이브러리 의존성 최소화 (Lombok 제외 순수 java 코드)
   - 필요한 모듈에서 의존성 추가 가능
  
2. api 모듈 (presentation Layer)
   - 외부와 통신하는 모듈 cotroller와 DtoMapper 포함
   - Controller와 밀접하게 관련되어있는 GlobalExceptiopnHandler 까지 포함.
   - 모든 모듈 의존성 포함 

3. 어플리케이션 모듈
   - 서비스, Domain 모듈 의존성 연결

4. Domain 모듈
   - 내부 비지니스 핵심 로직 (Entity, Repository)
   - Repository 인터페이스 정의

5. infra 모듈
   - Repository 구현체, 데이터 베이스 관련 설정


### 모듈간의 관계 

Layerd Architecture는 기본적으로  api -> application -> domain -> infra의 의존 관계를 가지지만 
Entity를 Domain 모듈에 포함시키면서 api -> application -> domain <- infra 의 의존관계를 가지도록 설계하여 
계층간의 분리와 도메인 계층의 독립성을 유지하려고 함. 


