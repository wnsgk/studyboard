package jboard.board.service;

import jboard.board.domain.Member;
import jboard.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {     //동시성 문제 발생 가능
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) { //멤버 수를 세면 더 최적화 가능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member findUser(String name, String password){
        Member user = memberRepository.findByUserName(name);
        if(user == null || !user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }


}
