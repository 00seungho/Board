package com.example.board.service;

import com.example.board.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO dto = BoardDTO.builder()
                        .
                title("Board Test...")
                        .
                content("Board Test Board Test Board Test")
                        .
                writerEmail("user7@kopo.ac.kr")
                        .
                build();

        Long bno = boardService.register(dto);
        System.out.println("정상적으로 글이 저장되었을까? :" +bno);
    }

}
