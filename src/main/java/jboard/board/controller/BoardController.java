package jboard.board.controller;

import jboard.board.domain.Board;
import jboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/write/new")
    public String createWrite(Model model) {
        model.addAttribute("writeForm", new WriteForm());
        return "write/writeForm";
    }

    @PostMapping("/write/new")
    public String create(@Valid WriteForm writeForm, BindingResult result) {
        if(result.hasErrors()) {
            return "members/writeForm";
        }
        Board board = new Board();
        board.setTitle(writeForm.getTitle());
        board.setBody(writeForm.getBody());
        board.setName(writeForm.getName());

        boardService.createBoard(board);
        return "redirect:/";
    }

    @GetMapping("/board/view/{boardId}")
    public String viewBoard(@PathVariable("boardId") Long boardId, Model model) {

        Board board = boardService.findOne(boardId);
        model.addAttribute("board", board);

        return "board/view";
    }

    @GetMapping("/board/view/{boardId}/edit")
    public String updateBoard(@PathVariable("boardId") Long boardId, Model model) {

        Board board = boardService.findOne(boardId);
        model.addAttribute("form", board);

        return "board/edit";
    }

    @PostMapping("/board/view/{boardId}/edit")
    public String update(@PathVariable("boardId") Long boardId, @ModelAttribute("form") ViewDto viewDto) {

        boardService.updateBoard(boardId, viewDto.getBody(), viewDto.getTitle());

        return "redirect:/board/view/"+boardId;
    }

    @GetMapping("/board/view/{boardId}/delete")
    public String deleteBoard(@PathVariable("boardId") Long boardId) {

        boardService.deleteBoard(boardId);

        return "home";
    }
}
