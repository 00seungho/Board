package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableResolver;

    @Test
    public void inserBorads(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member= Member.builder()
                    .email("user"+i+"@kopo.ac.kr")
                    .name("user"+i)
                    .build();
            Board board = Board.builder()
                    .title("Title"+i)
                    .content("Content"+i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });

    }

    @Transactional
    @Test
    public void testRead(){
        Optional<Board> result = boardRepository.findById(5L);
        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());

    }

    @Test
    public void testReadWithWriter(){
        Object result = boardRepository.getBoardWithWriter(10L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testReadWithReply(){
        List<Object> result = boardRepository.getBoardWithReply(1L);
        for (Object o : result) {
            System.out.println(o);
        }
//        Object[] arr = (
//        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testBoardWithReplyCount(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row->{
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3(){
        Object result = boardRepository.getBoardByBno(99L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1(){
        boardRepository.search1();
    }
    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        boardRepository.searchPage("t","1",pageable);
    }
}
