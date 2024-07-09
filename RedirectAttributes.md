# `RedirectAttributes` 
- PRG 패턴을 구현할 때 사용하는 인터페이스다.
- 이 인터페이스를 통해 리다이렉트된 페이지로 전달하고자 하는 데이터를 저장할 수 있다.

## 주요기능
1. Flash Attributes: 일회성으로 사용되는 데이터로, 리다이렉트된 요청에 데이터를 전달할때 사용된다. 주로 사용자 피드백 메시지나 임시데이터 전송에 사용된다.
2. URL 템플릿 변수: 리다이렉트할 URL의 템플릿 변수에 값을 바인딩할때 사용된다.

## 1️⃣Flash Attributes

```
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        // 회원 가입 로직 처리 (예: 데이터베이스에 사용자 저장)
        
        // 리다이렉트에 성공 메시지 추가
        redirectAttributes.addFlashAttribute("message", "Registration Successful!");
        return "redirect:/register/success";
    }

    @GetMapping("/register/success")
    public String registerSuccess() {
        return "success";
    }
}

```

```angular2html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Success</title>
</head>
<body>
    <p th:text="${message}"></p>
</body>
</html>

```

- redirectAttributes.addFlashAttribute("message", "Registration Successful!"); 는 message라는 이름으로 플래시 속성을 추가합니다.
- 이 속성은 리다이렉트된 후 /register/success 경로로 전달되어, 성공 메시지를 출력할 수 있습니다.

## 2️⃣URL 템플릿 변수

```
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @PostMapping("/user/create")
    public String createUser(@RequestParam String username, RedirectAttributes redirectAttributes) {
        // 사용자 생성 로직 (예: 데이터베이스에 사용자 저장)
        
        // 사용자 ID를 경로 변수로 사용하여 리다이렉트
        redirectAttributes.addAttribute("username", username);
        return "redirect:/user/{username}/profile";
    }

    @GetMapping("/user/{username}/profile")
    public String userProfile(@PathVariable String username, Model model) {
        // 사용자 프로필 로직 (예: 데이터베이스에서 사용자 정보 조회)
        model.addAttribute("username", username);
        return "profile";
    }
}


```

- redirectAttributes.addAttribute("username", username);는 리다이렉트 URL /user/{username}/profile에 username 값을 바인딩하여 리다이렉트합니다.
- 이는 URI 템플릿 변수로 사용되어 경로에 값을 설정합니다.