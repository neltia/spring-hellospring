package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

// JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행되어야 함
// 해당 클래스 메서드 실행 시 트랜잭션을 시작하고 정상 종료 시 트랜잭션 커밋, 런타임 예외 시 롤백
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    // isPresent() 메서드는 Optional 객체가 값을 가지고 있다면 true, 없다면 false 반환
    // ifPresent() 메서드는 Optional 객체가 값을 가지고 있으면 실행, 없다면 구문을 넘어감
    // 받은 member의 이름 값을 가지고 findByName() 메서드로 조회하는데 이미 있으면 중복 회원임
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent( m -> {
            throw new IllegalThreadStateException("이미 존재하는 회원");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * id로 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
