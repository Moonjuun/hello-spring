package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;

import java.util.List;
import java.util.Optional;
// findBy 로 가져올때 null일 수도 있다. 그러면 null로 반환이 되는데
// null을 바로 반환하는 방법보단 optional로 감싸서 반환하는 걸 선호해서 씀!

public interface MemberRepository {
    Member save(Member member);
//  회원 저장
    Optional<Member> findById(Long id);
//   찾아오는 기능
    Optional<Member> findByName(String name);
//   찾아노는 기능
    List<Member> findAll();
//  저장된 모든 find 반환
}
