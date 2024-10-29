package com.example.board.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1씩 증가
    private Long rno;

    private String text;
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board; // 일대 다 관계의 폴인키 설정(참조 무결성 유지)
}
