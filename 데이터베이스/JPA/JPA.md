# JPA(Java Persistence API) 란
- EJB
  - 과거의 자바 표준 (Entity Bean)
  - 과거의 ORM
  - 문제?
    - 코드가 매우 지저분하다.
    - API의 복잡성이 높다. (interface를 많이 구현해야 함)
    - 속도가 느리다.
- Hibernate
  - ORM 프레임워크, Open Source SW
  - ‘Gavin King’ 과 시러스 테크놀로지스 출신 동료들이 EJB2 스타일의 Entity Beans 이용을 대체할 목적으로 개발하였다.
- JPA (Java Persistence API)
 - 현재 자바 진영의 ORM 기술 표준으로, **인터페이스의 모음**이다. 
    - 즉, 실제로 동작하는 것이 아니다.
    - JPA 인터페이스를 구현한 대표적인 오픈소스가 Hibernate라고 할 수 있다.
- JPA 2.1 표준 명세를 구현한 3가지 **구현체**: ```Hibernate```, ```EclipseLink```, ```DataNucleus```
   ![1](https://user-images.githubusercontent.com/62952295/185754875-9d56a7e4-9be4-4cd1-8eac-bcddfe8d6611.png)

 - 버전
    - JPA 1.0(JSR 220) 2006년 : 초기 버전. 복합 키와 연관관계 기능이 부족
    - JPA 2.0(JSR 317) 2009년 : 대부분의 ORM 기능을 포함, JPA Criteria 추가
    - JPA 2.1(JSR 338) 2013년 : 스토어드 프로시저 접근, 컨버터(Converter), 엔티티 그래프 기능이 추가
- Cf) Spring Framework
  - Application 프레임워크, Open Source SW
  - ‘Rod Johnson’ 이 EJB의 여러 문제를 해결하고, 엔터프라이즈 애플리케이션 개발을 좀 더 쉽게 하기 위한 목적으로 만들었다.

## JPA의 동작 과정

 ![2](https://user-images.githubusercontent.com/62952295/185754872-5e09c6dc-1bfd-4a5f-927d-8f4a5b370bad.png)

- JPA는 애플리케이션과 JDBC 사이에서 동작한다.
  - 개발자가 JPA를 사용하면, JPA 내부에서 JDBC API를 사용하여 SQL을 호출하여 DB와 통신한다.
  - 즉, 개발자가 직접 JDBC API를 쓰는 것이 아니다.
## 저장 과정
  ![3](https://user-images.githubusercontent.com/62952295/185754874-7eaf7288-7b64-4fb9-af5a-8dba0920847c.png)

- Ex) MemberDAO에서 객체를 저장하고 싶을 때
  - 개발자는 JPA에 Member 객체를 넘긴다.
  - JPA는
    - 1) Member 엔티티를 분석한다.
    - 2) INSERT SQL을 생성한다.
    - 3) JDBC API를 사용하여 SQL을 DB에 날린다.
## 조회 과정
  ![4](https://user-images.githubusercontent.com/62952295/185754882-a44527ab-bbd9-4cac-a3b0-14df01628c82.png)
- Ex) Member 객체를 조회하고 싶을 때
  - 개발자는 member의 pk 값을 JPA에 넘긴다.
  - JPA는
    - 1) 엔티티의 매핑 정보를 바탕으로 적절한 SELECT SQL을 생성한다.
    - 2) JDBC API를 사용하여 SQL을 DB에 날린다.
    - 3) DB로부터 결과를 받아온다.
    - 4) 결과(ResultSet)를 객체에 모두 매핑한다.
- 쿼리를 JPA가 만들어 주기 때문에 Object와 RDB 간의 **패러다임 불일치를 해결**할 수 있다.
<br>
