package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 get으로 꺼내면 된다!
        return Optional.ofNullable(store.get(id));
        // null이 될 가능성이 있으면 Optional로 감싸준다!
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                        .findAny();
        // 람다를 사용해서 루프로 돌려서 필터를 사용
//        member.getName()이 파라미터로 넘어온 name이랑 같은지 확인한다
//        같은 경우에만 필터링이 되고 찾게 되면 반환하게 된다!
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
//        store.values() 반환, Member들이 반환이 된다
    }

    public void clearStore() {
        store.clear();
    }
    //테스트가 끝나면 메모리 삭제
}
