package jboard.board.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class WriteForm {

    @NotEmpty(message = "제목을 입력해주세요")
    private String title;

    private String body;
    private String name;
}
