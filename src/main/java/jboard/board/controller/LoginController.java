package jboard.board.controller;

import jboard.board.domain.Board;
import jboard.board.domain.Member;
import jboard.board.domain.SessionConstants;
import jboard.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("memberForm", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid LoginDto loginDto, BindingResult result, HttpServletRequest request) throws IllegalAccessException {
        if(result.hasErrors()) {
            return "login";
        }
        Member user = memberService.findUser(loginDto.getUsername(), loginDto.getPassword());
        if(user == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }
        HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, user);

        return "redirect:/";
    }

    @GetMapping("/members/new")
    public String join(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/memberForm";
    }

    @PostMapping("/members/new")
    public String joinMember(@Valid Member member, BindingResult result) {
        if(result.hasErrors()) {
            return "members/memberForm";
        }
        memberService.join(member);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
        }

        return "redirect:/";
    }
}
