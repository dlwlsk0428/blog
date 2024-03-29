DataFrame을 만들어 다루기 위한 설치

```
>>> pip install pandas
>>> pip install numpy
>>> pip install matplotlib
```

> pandas : DataFrame을 다루기 위해 사용
>
> numpy : 벡터형 데이터와 행렬을 다룸
>
> matplotlib : 데이터 시각화

<br>

#### 데이터 분석

스칼라 : 하나의 값을 가진 변수 `a = 'hello'`

벡터 : 여러 값을 가진 변수 `b = ['hello', 'world']`

> 데이터 분석은 주로 '벡터'를 다루고, DataFrame의 변수도 벡터

이런 '벡터'를 pandas에서는 Series라고 부르고, numpy에서는 ndarray라 부름

<br>

##### 파이썬에서 제공하는 벡터 다루는 함수들

```
>>> all([1, 1, 1])  #벡터 데이터 모두 True면 True 반환
>>> any([1,0,0])    #한 개라도 True면 True 반환
>>> max([1,2,3])    #가장 큰 값을 반환한다.
>>> min([1,2,3])    #가장 작은 값을 반환한다.
>>> list(range(10)) #0부터 10까지 순열을 만듬
>>> list(range(3,6))    #3부터 5까지 순열을 만듬
>>> list(range(1, 6, 2))    #1부터 6까지 2단위로 순열을 만듬
```

<br>

<br>

#### pandas

```python
import pandas as pd #pandas import
df = pd.read_csv("data.csv") #csv파일 불러오기
```

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FkBJ3k%2FbtqxTKjpkVI%2FjK0Kp2RyqtQ9LbUkJMuGc1%2Fimg.jpg">

<br>

다양한 함수를 활용해서 데이터를 관측할 수 있다.

```python
df.head() #맨 앞 5개를 보여줌
df.tail() #맨 뒤 5개를 보여줌
df[0:2] #특정 관측치 슬라이싱
df.columns #변수명 확인
df.describe() #count, mean(평균), std(표준편차), min, max
```

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FclDY9p%2FbtqxTLJoa4L%2FzYOkqI7r34tQ3DAKVQ6Xy0%2Fimg.jpg">

<br>

##### 특정 변수 기준 그룹 통계값

```python
# column1 변수별로 column2 평균 값 구하기
df.groupby(['column1'])['column2'].mean() 
```

<br>

변수만 따로 저장해서 Series로 자세히 보기

```python
s = movies.movieId
s.index = movies.title
s
```

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcUMouv%2FbtqxSiun6FU%2F7QSJMdMi0nmvEyrrIurkok%2Fimg.jpg">

Series는 크게 index와 value로 나누어짐 (왼쪽:index, 오른쪽:value)

이를 통해 따로 불러오고, 연산하는 것도 가능해진다.

```python
s['Toy Story (1995)'] #이 컬럼이 가진 movieId가 출력됨
print(s*2) #movieId가 *2되어 출력
```




