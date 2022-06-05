package jboard.board.controller;

import jboard.board.domain.Board;
import jboard.board.domain.Member;
import jboard.board.domain.SessionConstants;
import jboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        // 세션에 저장된 회원 조회
        Member loginMember = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }


}
