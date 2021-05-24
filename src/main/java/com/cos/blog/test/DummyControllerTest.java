package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    /*
    {id} 주소로 파라미터를 전달 받을 수 있음
    http://localhost:8000/blog/dummy/user/3

    현재 DB 상태
    |id |createDate                   |email        |password|role|username|
    |---|-----------------------------|-------------|--------|----|--------|
    |1  |2021-05-24 16:47:07.305000000|ssar@nate.com|1234    |USER|ssar    |
    |2  |2021-05-24 16:47:14.078000000|love@nate.com|1234    |USER|love    |
    |3  |2021-05-24 16:47:21.513000000|cos@nate.com |1234    |USER|cos     |
    */
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4를 찾으면 내각 DB에서 못찾아오게 되면 userrk null이 됨
        // return null이 리턴되면 문제가 되므로
        // Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return

        // 방법 1
//        User user = userRepository.findById(id).get();

        // 방법 2
        // {"id":0,"username":null,"password":null,"email":null,"role":null,"createDate":null}
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User(); // user/4의 경우 없으므로 실행됨
//            }
//        });

        /* 방법 3
        http://localhost:8000/blog/dummy/user/4 실행시 웹 출력
        Mon May 24 16:57:08 KST 2021
        There was an unexpected error (type=Internal Server Error, status=500).
        해당 유저는 없습니다. id:4
        */
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id:" + id);
            }
        });
        // 요청 : 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서
        // user오브젝트를 json으로 변환하여 브라우저에 던져줌
        // http://localhost:8000/blog/dummy/user/3실행 후 F12로 확인 가능(Content-Type: application/json;charset=UTF-8)
        return user;

        // 방법 4 (방법 3과 결과는 같다.)
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
//        });
//        return user;
    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
//    public String join(String username, String password, String email) { // key=value(약속된 규칙)
//    public String join(@RequestParam("username") String u 이런식으로도 가능
    public String join(User user) {
        System.out.println("id:" + user.getId());
        System.out.println("username:" + user.getUsername());
        System.out.println("password:" + user.getPassword());
        System.out.println("email:" + user.getEmail());
        System.out.println("role:" + user.getRole());
        System.out.println("createDate:" + user.getCreateDate());

        user.setRole(RoleType.USER); // username, password, email 작성하여 전송시 role에 USER값 세팅
        userRepository.save(user);
        /*
        insert 할때 role 값이 있어서 NULL이 들어간다.
        into
            User
            (createDate, email, password, role, username)
        values
            (?, ?, ?, ?, ?)

        role값을 default값을 하려면

        User
        (createDate, email, password, username)
        values
        (?, ?, ?, ?)
        이런식으로 되어야 한다.
         */
        return "회원가입이 완료되었습니다.";
    }
}
