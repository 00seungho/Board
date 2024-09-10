package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Member;

public interface BoardService {
    //새 글을 등록하는 기능
    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();


        Board board = Board.builder()
                        .bno(dto.getBno())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .writer(member)
                        .build();
        return board;
    }
}
