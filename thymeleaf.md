# 타임리프

### 타임리프 사용 선언

`<html xmlns:th="http://www.thymeleaf.org">`

### 기본 문법

1. 변수 표현식 : ${...}

- `<td th:text="${item.price}">10000</td>`
  
- `<p>Name: <span th:text="${name}">Name</span></p>`
  
- 모델 속성값이나, 타임리프 변수로 선언한 값을 조회할 수 있다.

- 프로퍼티 접근법을 사용한다.(item.getPrice())

2. 문자열 리터럴 : |...|

- 타임리프에서 문자와 표현식 등은 분리되어 있기 때문에 더해서 사용해야 한다.

`<span th:text="'Welcome to our application, ' + ${user.name} + '!'">`

- 다음과 같이 리터럴 대체 문법을 사용하면, 더하기 없이 편리하게 사용할 수 있다.

`<span th:text="|Welcome to our application, ${user.name}!|">`

3. 링크 URL : @{...}

- `th:href="@{/css/bootstrap.min.css}`

-URL 링크 표현식을 사용하면 서블릿 컨텍스트를 자동으로 포함한다

- `th:href="@{/basic/items/{itemId}(itemId=${item.id})}"`

- URL링크 표현식을 사용하면 경로를 템플릿처럼 편리하게 사용할 수 있다.

- `th:href="@{/basic/items/{itemId}(itemId=${item.id}, query='test')}"`

- 경로변수 ({itemId})뿐만 아니라 쿼리 파라미터도 생성한다.
생성 링크: `http://localhost:8080/basic/items/1?query=test`

- `th:href="@{|/basic/items/${item.id}|}"`

- 좀 더 간단히 나타낼 수도 있다.

4. 속성 변경 : th:href
-  `th:href="@{/css/bootstrap.min.css}"`

-  `href="value1"` 을 `th:href="value2"` 의 값으로 변경한다.

- 타임리프 뷰 템플릿을 거치게 되면 원래 값을 `th:xxx` 값으로 변경한다. 만약 값이 없다면 새로 생성한다. HTML을 그대로 볼 때는 `href` 속성이 사용되고, 뷰 템플릿을 거치면 `th:href` 의 값이 `href` 로 대체되면서 동적으로 변경할 수 있다.

- 대부분의 HTML 속성을 `th:xxx` 로 변경할 수 있다.

### -> 타임리프의 핵심을 알 수 있다!!!!

- 핵심은 `th:xxx` 가 붙은 부분은 서버사이드에서 렌더링 되고, 기존 것을 대체한다.

- `th:xxx` 이 없으면 기존 html 의 `xxx` 속성이 그대로 사용된다.

- HTML을 파일로 직접 열었을 때, `th:xxx` 가 있어도 웹 브라우저는 `th:` 속성을 알지 못하므로 무시한다. 따라서 HTML을 파일 보기를 유지하면서 템플릿 기능도 할 수 있다.


### 조건부 렌더링

1. 내용 변겅: th:text

  - `<td th:text="${item.price}">10000</td>`

  - 내용의 값을 `th:text` 의 값으로 변경한다.

  - 여기서는 10000을 `${item.price}` 의 값으로 변경한다.

2. 속성 변경: th:value 

- `th:value="${item.id}"`

- 모델에 있는 item 정보를 획득하고 프로퍼티 접근법으로 출력한다. ( `item.getId()` )

- `value` 속성을 `th:value` 속성으로 변경한다.

3. 반복 출력 : th:each

- `<tr th:each="item : ${items}">`

- 반복은 `th:each` 를 사용한다.
이렇게 하면 모델에 포함된 `items` 컬렉션 데이터가 `item` 변수에 하나씩 포함 되고, 반복문 안에서 `item` 변수를 사용할 수 있다.

- 컬렉션의 수 만큼 `<tr>..</tr>` 이 하위 테그를 포함해서 생성된다.

4. 조건부 출력 : th:if
```
<div th:if="${user != null}">
    <p>Welcome, <span th:text="${user.name}">User</span>!</p>
</div>

```
5. 폼 처리 : - th: action  & th:object

```
<form th:action="@{/submit}" th:object="${formObject}" method="post">
    <input type="text" th:field="*{name}" />
    <button type="submit">Submit</button>
</form>

```

6. Fragment와 insert && replace


- 타임리프에서 Fragment와 Insert는 템플릿의 재사용을 위해 사용됩니다. `th:fragment`는 템플릿 조각을 정의하는 데 사용되고, `th:insert`는 이 템플릿 조각을 포함하는 데 사용됩니다.

- `th:fragment`

- 템플릿 조각을 정의합니다.

- HTML 요소에 `th:fragment` 속성을 추가하여 정의합니다.

```
<div th:fragment="header">
    <h1>Header</h1>
</div>
```