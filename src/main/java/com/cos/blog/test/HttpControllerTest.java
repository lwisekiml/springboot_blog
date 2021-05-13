package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    // 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
    // 첫 번째 방법
    // http://localhost:8080/http/get (select)
//    @GetMapping("/http/get")
//    public String getTest() {
//        return"get 요청";
//    }

    // 두 번째 방법
    // http://localhost:8080/http/get?id=2&username=test
//    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username) {
//        return "get 요청 : " + id + ", " + username;
//    }

    // 세 번재 방법
    // http://localhost:8080/http/get?id=2&username=test&password=1234&email=test@naver.com
    @GetMapping("/http/get")
    public String getTest(Member m) { // id=2&username=test&password=1234&email=test@naver.com 값이 Member로 셋팅 // MessageConverter(스프링부트)
        return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // 첫 번째 방법
    // http://localhost:8080/http/post (insert)
//    @PostMapping("/http/post")
//    public String postTest() {
//        return "post 요청";
//    }

    // 두 번째 방법
    // http://localhost:8080/http/post (insert)
    // postman사용 - Body - x-www-form-urlencoded (id, username, password, email 작성 후 send)
//    @PostMapping("/http/post")
//    public String postTest(Member m) {
//        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
//    }

    // 세 번째 방법(텍스트)
    // postman사용 - Body - raw에 text로(또는 JSON형식의 텍스트) 선택 후 '안녕' 이라 하고 send 시 출력됨(raw는 text/palin 이다.)
//    @PostMapping("/http/post") // @RequestBody 가 있어야 출력
//    public String postTest(@RequestBody String text) {
//        return "post 요청 : " + text;
//    }

    // 네 번째 방법
    // postman사용 - Body - raw에 JSON 선택 후(text로 선택 후 전송 시 415 에러)
    // {
    //    "id": 1,
    //    "username": "coss",
    //    "password": "1234567",
    //    "email": "ssar@nate.com"
    //}
    // 작성 뒤 send 시 post 요청 : 1, coss, 1234567, ssar@nate.com 출력됨
    @PostMapping("/http/post") // @RequestBody 가 있어야 출력
    public String postTest(@RequestBody Member m) { // MessageConverter(스프링부트)
        return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/put (update)
    // {
    //    "id": 1,
    //    "password": "12367"
    // }
    // 전송시
    // put 요청 : 1, null, 12367, null
    // 출력
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
