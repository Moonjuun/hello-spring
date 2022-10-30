package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 잇는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
        // Optional을 바로 꺼내는건 추천하지 않는다!


        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                // findByName의 결과는 바로 Optionanl이니깐 ifPresent를 바로 사용할 수 있다!
                .ifPresent(m -> {
                    // ifPresent는 이 값이 null이 아니라 어떤 값이 있으면 동작을 한다.
                    // Optionanl이라서 가능, 안썻면 if null을 쓴다!
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                    //
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
