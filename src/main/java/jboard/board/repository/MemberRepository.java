package jboard.board.repository;

import jboard.board.domain.Board;
import jboard.board.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public List<Member> findByName(String name) {
        return  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Member findByUserName(String name) {
        return  em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
}
