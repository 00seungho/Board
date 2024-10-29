package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void inserReplys(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            long bno = (long)(Math.random() * 100+1);
            Board board = Board.builder()
                    .bno(bno)
                    .build();

            Reply reply = Reply.builder()
                    .text("Reply"+i)
                    .board(board)
                    .replyer("guest")
                    .build();
            replyRepository.save(reply);

        });
    }

    @Test
    public void testListByBoard(){
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(100L).build());
        replyList.forEach(reply -> System.out.println(reply));
    }
}
