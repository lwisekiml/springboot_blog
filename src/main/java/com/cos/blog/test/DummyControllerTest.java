package com.cos.blog.test;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

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
