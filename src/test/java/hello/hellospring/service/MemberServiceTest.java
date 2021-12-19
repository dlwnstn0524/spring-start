package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberSerive;
    MemoryMemberRepository repo;

    @BeforeEach
    void beforEach(){
        repo = new MemoryMemberRepository();
        memberSerive = new MemberService(repo);
    }

    @AfterEach
    void afterEach() {
        repo.clearStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberSerive.join(member);

        //then
        Member findMember = memberSerive.findOne(saveId).get();
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void 중복회원예외(){
        //given
        Member mem1 = new Member();
        mem1.setName("spring");

        Member mem2 = new Member();
        mem2.setName("spring");

        //when
        memberSerive.join(mem1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberSerive.join(mem2));//이름이 같은 Member가 회원가입 시도.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then


    }

    @Test
    void 전체회원조회() {
    }

    @Test
    void findOne() {
    }
}