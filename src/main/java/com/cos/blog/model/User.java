package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성 된다.
//@DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id // Primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(오라클 사용시 시퀀스, mysql 사용시 auto_increment 사용)
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 20) // 아이디가 null이면 안되므로
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    // @ColumnDefault("'user'")
    // DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
/*
create table User (
    id integer not null auto_increment,
    createDate datetime(6),
    email varchar(50) not null,
    password varchar(100) not null,
    role varchar(255) default 'user',
    username varchar(20) not null,
    primary key (id)
) engine=InnoDB
*/