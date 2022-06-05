package jboard.board.repository;

import jboard.board.domain.Board;
import jboard.board.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public void insertBoard(Board board) {
        em.persist(board);
    }

    public void deleteBoard(Long id) {
        Board board = em.find(Board.class, id);
        em.remove(board);
    }
    public List<Board> findAll() {
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }

    public List<Member> findByMemberName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }
}
