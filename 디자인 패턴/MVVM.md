# MVVM 패턴

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2Fb5ZE36%2Fbtq2lqcQohU%2FekNc9XgOcJcyqfEtMbltTk%2Fimg.png)
MVVM 패턴은 MVC 패턴에서 Controller를 빼고 ViewModel을 추가한 패턴이다.
 

## 역할 및 동작 원리

### View

사용자가 스크린을 통해서 보는 것들에 대한 구조, 레이아웃, 형태를 정의한다. <br>
유저 인터랙션을 받는 역할을 하며, 인터랙션을 받을 시 ViewModel에게 명령을 내림. <br>
(iOS는 ViewController까지 View)
뷰의 역할은 UI에 관련된 것을 다루는것 입니는 것으로, 애니메이션 같은 UI 로직을 포함하되 비즈니스 로직을 포함하지 말아야 함.

### ViewModel
View를 표현하기 위해 만들어진 View를 위한 Model이다. <br>
View와는 Binding을 하여 연결후 View에게서 액션을 받고 또한 View를 업데이트함. <br>
ex) textView에 보여줄 내용을 담당하는 함수 등, View에서 변화가 일어나는 ViewController의 역할을 담당 <br>

### Model
데이터, 비즈니스 로직, 서비스 클라이언트 등으로 구성
(실제 데이터)
 
### 동작 과정

1. View에 입력이 들어오면 ViewModel에게 명령 <br>

2. ViewModel은 필요한 데이터를 Model에게 요청 <br>

3. Model은 ViewModel에게 요청된 데이터를 응답 <br>
 
4. ViewModel은 응답 받은 데이터를 가공해서 저장 <br>

5. View는 ViewModel과의 Data Binding으로 인해 자동으로 갱신됨 <br>

 

 

### MVVM 패턴의 장단점

**장점** <br>
- View와 Model이 서로 전혀 알지 못하기에 독립성을 유지할 수 있음 <br>
- 독립성을 유지하기 때문에 효율적인 유닛테스트가 가능 <br>
- View와 ViewModel을 바인딩하기 때문에 코드의 양이 줄어든다 <br>
- View와 ViewModel의 관계는 N:1 관계 <br>
- 유닛테스트에 유리함 (ViewModel에는 UIKit 관련 코드가 없고 Controller와의 의존성도 없기 때문) <br>
- 개발 기간 동안 개발자와 디자이너가 동시에 독립적으로(병렬적으로) 작업할 수 있다 <br>

<br>

**단점** <br>
- 거대하고 복잡한 앱을 위해서 고안된 디자인 패턴인 만큼, 소형 앱에서 사용하게 되면 오버헤드가 커진다. <br>
- 앱이 너무 거대해지면 앱의 메모리 소모가 데이터 바인딩때문에 커진다. 간단한 UI에서 오히려 ViewModel을 설계하는 어려움이 있을 수 있다  <br>
- 데이터 바인딩이 필수적으로 요구됨 <br>
- 복잡해질수록 Controller처럼 ViewModel이 빠르게 비대해짐 <br>
- 표준화된 틀이 존재하지 않아 사람마다 이해가 다르다 <br>
 

 

### Data Binding 이란?

데이터 바인딩은 Model과 UI 요소 간의 싱크를 맞춰주는 것이라 할 수 있다. <br>

이 패턴을 통해 View와 로직이 분리되어 있어도 한 쪽이 바뀌면 다른 쪽도 업데이트가 이루어져 데이터의 일관성을 유지할 수 있다. <br>
