package com.example.board.controller;

import com.example.board.dto.ReplyDTO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping(value = "/board/{bno}")
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno){
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno, HttpStatus.OK);
    }


    @DeleteMapping("/{rno}")
    public ResponseEntity<String> delete(@PathVariable("rno") Long rno){
        replyService.remove(rno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<String> modify(ReplyDTO replyDTO){
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
