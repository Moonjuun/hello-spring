package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    // 테스트가 끝나면 메모리 삭제


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result ==member));
//        이런 방법도 있지만 계속 글자를 찍으면서 볼 수는 없다!

        Assertions.assertEquals(member, result);
        // 기대값은 result인데 member와 같으면 o
        // 위 코드로 실행하면 출력된건 없지만 녹색 체크가 된다!!
        // 만약 다르다면 빨간불이 뜬다!!!

        assertThat(member).isEqualTo(result);
        // 요즘은 위와 방식으로 많이 사용한다!!
    }

    @Test
    public void findByName() {
//        Member member1 = new Member();
//        member1.setName("MoonJun");
//        repository.save(member1);
//
//        Member member2 = new Member();
//        member2.setName("Eunbin");
//        repository.save(member2);
//
//        Member result = repository.findByName("MoonJun").get();
//
//        assertThat(result).isEqualTo(member1);


    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Moonjun");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("good");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    //실행 순서는 고정이 안된다! 모든 테스트는 순서와 상관없이 메서드 별로 따로 동작되게 설계해야한다!
    //테스트가 끝나면 메모리를 Clear 시켜줘야한다!!!

    // 미리 테스트를 먼저 만들고 그 다음 구현체를 만들고 실행 시켜보는 것!!
    // 테스트 주도 개발 (TDD)


}
