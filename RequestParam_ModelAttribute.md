# 1️⃣RequestParam과 ModelAttribute

## @RequestParam

- Http 요청에서 특정한 파라미터 값을 메서드의 매개변수로 바인딩할 때 사용한다.
- 주로 쿼리 파라미터나 POST요청의 form데이터에서 값을 추출할 때 사용한다.

-  `@RequestParam ` 어노테이션 다음에 파라미터의 이름을 명시합니다.
 ex) `@RequestParam("itemName") String itemName`과 같이 사용됩니다.
- 기본적으로 필수파라미터로 간주되기때문에 요청에서 파라미터가 누락되면 예외가 발생한다. 따라서 필수가 아닌 파라미터는 `required=false`옴션을 설정한다.
- 기본값으로 `defaultValue`속성을 사용하여 파라미터의 기본값 설정도 가능하다.

```
@PostMapping("/addItem")
public String addItem(@RequestParam("itemName") String itemName,
                      @RequestParam("price") int price,
                      @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity,
                      Model model) {
    // 메서드 내에서 itemName, price, quantity를 사용할 수 있음
    // 처리 로직 작성
    return "resultPage"; // 결과를 보여줄 View 이름 리턴
}

```

## @ModelAttribute
- HTTP 요청 데이터를 Java 객체에 바인딩하여 사용할 수 있게 한다.
- 주로 HTML form을 통해 전송된 데이터를 받아서 DTO 나 Entity 객체에 자동으로 매핑할 때 사용된다.
- `@ModelAttribute` 어노테이션을 매개변수로 사용하여 해당 객체를 자동으로 생성한다.
- HTTP요청 데이터와 바인딩된다 : HTML form을 통해 전송된 데이터가 자동으로 매핑되어 객체의 필드에 설정된다.
- 모델에 자동 추가: 생성된 객체는 자동으로 'Model' 객체에 추가되어 View에서 사용할 수 있다.

```
@PostMapping("/addItem")
public String addItem(@ModelAttribute Item item, Model model) {
    // item 객체에는 HTML form으로 전송된 데이터가 자동으로 매핑됨
    itemRepository.save(item); // 저장 등의 로직 처리
    model.addAttribute("item", item); // 모델에 item 추가
    return "resultPage"; // 결과를 보여줄 View 이름 리턴
}

```

### -> 상황에 따라 알맞게 사용한다

- `@RequestParam`은 HTTP요청의 각각의 파라미터를 개별적으로 받아올때 유리함.
- 사용 예시: HTML form에서 제출된 각각의 필드 값을 메서드의 매개변수로 직접 받아서 사용할 수 있습니다. 예를 들어, 폼에서 사용자 이름, 이메일, 나이 등을 개별적으로 받아오는 경우에 사용됩니다.


- `@ModelAttribute`sms HTTP요청 데이터를 자동으로 자바 객체에 매팽하여 객체를 생성하고, 메서드의 매개변수로 받아올 때 유리하다.
- 사용 예시: HTML form 전체를 하나의 객체로 받아서 처리하거나, 다양한 필드들을 하나의 DTO 객체로 받아올 때 유용합니다.

# 2️⃣ModelAttribute 와 Model

## @ModelAttribute
- 역할: HTTP 요청 데이터를 자동으로 Java객체에 매핑하여 객체를 생성한다.
- 사용시점: HTML Form 데이터를 객체로 변환하거나, 컨트롤러 메서드에서 받은 데이터를 객체로 처리할 때 사용한다.
- 자동추가: 생성된 객체는 자동으로 `Model`에 추가되어 View에서 사용 가능하다.

## Model
- 역할: Controller에서 View로 데이터를 전달할 때 사용된다.
- 사용시점 : 다양한 데이터나 객체를 View로 전달하거나, View가 필요로 하는 데이터를 모델에 추가하여 제공할 때 사용한다.
- 데이터 전달: Congroller에서 처리한 데이터를 View에 전달하는 역할을 한다.

### -> 언제 사용해?
- @ModelAttribute 사용 시: HTML form 데이터를 객체로 변환할 때, 특히 여러 필드를 한 번에 객체로 받아서 처리할 때 유용합니다. 객체 단위로 데이터를 받아야 할 경우에 적합합니다.

- Model 사용 시: 다양한 데이터를 한 번에 View에 전달하거나, 컨트롤러에서 처리한 데이터를 View로 보내야 할 때 유용합니다. 여러 개의 데이터를 한 번에 전달하거나, View에서 필요로 하는 데이터를 추가할 때 사용됩니다.
따라서 데이터를 받는 방식과 데이터를 전달하는 시점에 따라 @ModelAttribute와 Model을 적절히 선택하여 사용하면 됩니다.
