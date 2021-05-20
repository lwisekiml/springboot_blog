package com.cos.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html>태그가 디자인 됨

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many = Board, User = One
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;
}
/*
create table Board (
    id integer not null auto_increment,
    content longtext,
    count integer default 0 not null,
    createDate datetime(6),
    title varchar(100) not null,
    userId integer,
    primary key (id)
) engine=InnoDB
*/
