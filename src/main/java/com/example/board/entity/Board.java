package com.example.board.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //1씩 증가
    private Long bno; 
    private String title;
    private String content;

    @ManyToOne
    private Member writer; // 일대 다 관계의 폴인키 설정(참조 무결성 유지)
}
