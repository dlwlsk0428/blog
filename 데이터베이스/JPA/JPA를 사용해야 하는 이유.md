# JPA를 왜 사용해야 하는가?
## 1. SQL 중심적인 개발에서 객체 중심으로 개발
- [SQL 중심적인 개발의 문제점 참고](https://github.com/lemonjelly123/blog/new/main/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4/JPA)
## 2. 생산성
- JPA를 사용하는 것은 마치 Java Collection에 데이터를 넣었다 빼는 것처럼 사용할 수 있게 만든 것이다.
- 간단한 CRUD
  - 저장: `jpa.persist(member)`
  - 조회: `Member member = jpa.find(memberId)`
  - 수정: `member.setName("변경할 이름")`
  - 삭제: `jpa.remove(member)`
- 특히, 수정이 굉장히 간단하다.
  - 객체를 변경하면 그냥 알아서 DB에 UPDATE Query가 나간다.
## 3. 유지보수
- 기존: 필드 변경 시 모든 SQL을 수정해야 한다.
- JPA: 필드만 추가하면 된다. SQL은 JPA가 처리하기 때문에 손댈 것이 없다.
## 4. Object와 RDB 간의 패러다임 불일치 해결
### 1) JPA와 상속
![1](https://user-images.githubusercontent.com/62952295/185803065-cbd8b631-c6fe-4f8b-b829-e179ab4eaf13.png)

- 저장
  - 개발자가 할 일
    - `jpa.persist(album);`
  - 나머진 JPA가 처리
    - `INSERT INTO ITEM ...`
    - `INSERT INTO ALBUM ...`
- 조회
  - 개발자가 할 일
    - `Album album = jpa.find(Album.class, albumId);`
  - 나머진 JPA가 처리
    - `SELECT I.*, A.* FROM ITEM I JOIN ALBUM A ON I.ITEM_ID = A.ITEM_ID`
### 2) JPA와 연관관계
- 객체의 참조로 연관관계 저장 가능
  - `member.setTeam(team);`
  - `jpa.persist(member);`
### 3) JPA와 객체 그래프 탐색
- 신뢰할 수 있는 엔티티, 계층
```JAVA
class MemberService { 
      ...
      public void process() { 
          /* 직접 구현한 DAO에서 객체를 가져온 경우 */
          Member member1 = memberDAO.find(memberId); 
          member1.getTeam(); // 엔티티를 신뢰할 수 없음 
          member1.getOrder().getDelivery(); 
          /* JPA를 통해서 객체를 가져온 경우 */
          Member member2 = jpa.find(Member.class, memberId); 
          member2.getTeam(); // 자유로운 객체 그래프 탐색
          member2.getOrder().getDelivery(); 
      } 
}
```
- 내가 아닌 다른 개발자가 직접 구현한 DAO에서 가져오는 경우
  - DAO에서 직접 어떤 쿼리를 날렸는지 확인하지 않는 이상, 그래프 형태의 관련된 객체들을 모두 잘 가져왔는지 알 수가 없다.
  - 즉, 반환한 엔티티를 신뢰하고 사용할 수 없다.
- JPA를 통해서 가져오는 경우
  - 객체 그래프를 완전히 자유롭게 탐색할 수 있게 된다.
  - **지연 로딩 전략(Lazy Loading)** 사용
    - 관련된 객체를 **사용하는 그 시점에** SELECT Query를 날려서 객체를 가져오는 전략
### 4) JPA와 비교하기
- 동일한 트랜잭션에서 조회한 엔티티는 같음을 보장한다.
```java
String memberId = "100"; 
Member member1 = jpa.find(Member.class, memberId); // DB에서 가져옴 
Member member2 = jpa.find(Member.class, memberId); // 1차 캐시에서 가져옴
member1 == member2; //같다.
```
### 5. JPA의 성능 최적화 기능
- 중간 계층이 있는 경우 아래의 방법으로 성능을 개선할 수 있는 기능이 존재한다.
  - 모아서 쓰는 버퍼링 기능
  - 읽을 때 쓰는 캐싱 기능
- JPA도 JDBC API와 DB 사이에 존재하기 때문에 위의 두 기능이 존재한다.
#### 1) 1차 캐시와 동일성(identity) 보장 - 캐싱 기능
 1. 같은 트랜잭션 안에서는 같은 엔티티를 반환 - **약간의** 조회 성능 향상 (크게 도움 X)
```java
String memberId = "100"; 
Member m1 = jpa.find(Member.class, memberId); // SQL 
Member m2 = jpa.find(Member.class, memberId); // 캐시 (SQL 1번만 실행, m1을 가져옴)
println(m1 == m2) // true
```
- 결과적으로, SQL을 한 번만 실행한다.
 2. DB Isolation Level이 Read Commit이어도 애플리케이션에서 Repeatable Read 보장
#### 2) 트랜잭션을 지원하는 쓰기 지연(transactional write-behind) - *버퍼링 기능*
- INSERT
```java
/** 1. 트랜잭션을 커밋할 때까지 INSERT SQL을 모음 */
transaction.begin();  // [트랜잭션] 시작
em.persist(memberA); 
em.persist(memberB); 
em.persist(memberC); 
// -- 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
// 커밋하는 순간 데이터베이스에 INSERT SQL을 모아서 보낸다. --
/** 2. JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송 */
transaction.commit(); // [트랜잭션] 커밋
```
1. `트랜잭션`을 commit 할 때까지 INSERT SQL을 메모리에 쌓는다.
  - 이렇게 하지 않으면 DB에 INSERT Query를 날리기 위한 네트워크를 3번 타게 된다.
2. JDBC Batch SQL 기능을 사용해서 한 번에 SQL을 전송한다.
  - JDBC Batch를 사용하면 코드가 굉장히 지저분해진다.
  - 지연 로딩 전략(Lazy Loading) 옵션을 사용한다.
- UPDATE
```java
/** 1. UPDATE, DELETE로 인한 로우(ROW)락 시간 최소화 */
transaction.begin();  // [트랜잭션] 시작
changeMember(memberA);  
deleteMember(memberB);  
비즈니스_로직_수행();     // 비즈니스 로직 수행 동안 DB 로우 락이 걸리지 않는다.    
// 커밋하는 순간 데이터베이스에 UPDATE, DELETE SQL을 보낸다.
/** 2. 트랜잭션 커밋 시 UPDATE, DELETE SQL 실행하고, 바로 커밋 */
transaction.commit(); // [트랜잭션] 커밋
```
1. UPDATE, DELETE로 인한 로우(ROW)락 시간 최소화
2. 트랜잭션 커밋 시 UPDATE, DELETE SQL 실행하고, 바로 커밋
#### 3) 지연 로딩(Lazy Loading)
- **지연 로딩**
  - 객체가 실제로 사용될 때 로딩하는 전략
  ![2](https://user-images.githubusercontent.com/62952295/185803067-49fe4597-0d14-49bf-add1-41b8988bd597.png)
  - `memberDAO.find(memberId)`에서는 Member 객체에 대한 SELECT 쿼리만 날린다.
  - `Team team = member.getTeam()`로 Team 객체를 가져온 후에 `team.getName()`처럼 실제로 team 객체를 건드릴 때!
    - 즉, **값이 실제로 필요한 시점**에 JPA가 Team에 대한 SELECT 쿼리를 날린다.
  - Member와 Team 객체 각각 따로 조회하기 때문에 네트워크를 2번 타게 된다.
    - Member를 사용하는 경우에 대부분 Team도 같이 필요하다면 *즉시 로딩*을 사용한다.
- **즉시 로딩**
  - JOIN SQL로 한 번에 연관된 객체까지 미리 조회하는 전략
  ![3](https://user-images.githubusercontent.com/62952295/185803069-aadea641-5bfc-4860-8fd4-1a00ecb73c3c.png)
  - Join을 통해 항상 연관된 모든 객체를 같이 가져온다.
- 애플리케이션 개발할 때는 모두 지연 로딩으로 설정한 후에, 성능 최적화가 필요할 때에 옵션을 변경하는 것을 추천한다.
## 6. 데이터 접근 추상화와 벤더 독립성
## 7. 표준
