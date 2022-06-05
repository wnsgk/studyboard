package jboard.board.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginDto {

    @NotEmpty(message = "아이디를 입력해주세요")
    private String username;
    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;

}
