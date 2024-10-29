package com.example.board.service;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.ReplyDTO;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;

import java.util.List;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    List<ReplyDTO> getList(Long bno);
    default Reply dtoToEntity(ReplyDTO replyDTO){
        Board board = Board
                .builder()
                .bno(replyDTO.getBno())
                .build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();
        return reply;
    }
    default ReplyDTO entityToDto(Reply reply){
//        BoardDTO boardDTO = Board
//                .builder()
//                .bno(replyDTO.getBno())
//                .build();

        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .bno(reply.getBoard().getBno())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return replyDTO;
    }
}
