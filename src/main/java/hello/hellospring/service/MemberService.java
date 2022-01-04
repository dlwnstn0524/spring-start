package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository repo;

    public MemberService(MemberRepository repo){
        this.repo = repo;
    }
    
    //회원가입
    public Long join(Member member){
        //do not allow same name
        validDuqMem(member); //check Member's name is overlaped.

        repo.save(member);
        return member.getId();
    }

    private void validDuqMem(Member member) {
        repo.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }
    //전체 회원 조회
    public List<Member> findMembers(){
        return repo.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return repo.findById(memberId);
    }
}
