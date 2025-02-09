# PRG 패턴 (Post/Redirect/Get 패턴)

- PRG 패턴, 또는 **Post/Redirect/Get** 패턴은 웹 개발에서 폼 제출과 관련된 문제를 해결하기 위해 사용되는 디자인 패턴.
- PRG 패턴은 사용자가 웹 폼을 제출했을 때 발생할 수 있는 여러 가지 문제, 특히 폼 재전송 문제를 방지함.


### 1️⃣Post (POST 요청)
- 사용자가 폼을 작성하고 제출합니다. 이때 브라우저는 서버에 POST 요청을 보냅니다.
- 서버는 이 POST 요청을 처리하고 데이터베이스를 업데이트하거나 다른 비즈니스 로직을 수행합니다.

### 2️⃣Redirect (리다이렉션)
- 서버가 POST 요청을 처리한 후, 클라이언트에게 HTTP 리다이렉트 응답 (보통 302 Found 또는 303 See Other 상태 코드)을 보냅니다.
- 이 응답은 클라이언트에게 새로운 URL로 이동하도록 지시합니다.
- 클라이언트 (브라우저)는 이 리다이렉트를 받아들이고 GET 요청을 사용하여 새로운 URL로 이동합니다.

### 3️⃣Get (GET 요청)
- 클라이언트는 서버가 리다이렉트한 URL로 GET 요청을 보냅니다.
- 서버는 이 GET 요청을 처리하고 적절한 페이지 (보통 성공 메시지 페이지 또는 결과 페이지)를 반환합니다.

이 과정의 중요한 점은 사용자가 "뒤로" 혹은 "새로고침" 버튼을 눌러도 동일한 POST 요청이 다시 제출되지 않는다는 것입니다. 

대신, 사용자는 리다이렉트된 GET 요청을 재요청하게 됩니다. 이는 다음과 같은 문제를 방지합니다:

- **폼 재전송 문제**: 사용자가 브라우저의 새로 고침 버튼을 누르면 동일한 POST 요청이 다시 전송되어 데이터베이스에 중복된 데이터가 생성될 수 있습니다.
- **사용자 경험 개선**: 사용자가 폼을 제출한 후 URL이 바뀌기 때문에, 폼을 다시 제출하지 않고도 결과 페이지에 직접 접근할 수 있습니다.



