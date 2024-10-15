package com.example.board.controller;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageRequestDTO;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){
        BoardDTO Boarddto = BoardDTO.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writerEmail(dto.getWriterEmail())
                .build();
        Long bno = boardService.register(Boarddto);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/board/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        BoardDTO dto = boardService.get(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        boardService.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("bno", dto.getBno());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        return "redirect:/board/read";
    }
    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){
        boardService.removeWithReplies(bno);
        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/board/list";
    }
} // main
