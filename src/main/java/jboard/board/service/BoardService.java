package jboard.board.service;

import jboard.board.domain.Board;
import jboard.board.repository.BoardRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(Board board) {
        boardRepository.insertBoard(board);
        return board.getId();
    }

    @Transactional
    public void updateBoard(Long id, String body, String title) {
        Board board = boardRepository.findOne(id);

        board.setBody(body);
        board.setTitle(title);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }
}
