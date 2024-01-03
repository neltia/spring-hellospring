/*
데이터 저장소가 선정되지 않은 상태로 가정, 인터페이스로 구현 클래스를 변경할 수 있도록 함
개발을 진행하기 위해 초기 개발 단계에서는 구현체로 가벼운 메모리 기반 데이터 저장소 사용
MemberService -> <interface> MemberRepository <- MemoryMemberRepository

Flow: Client <- Dto -> Controller <- Dto -> Service <- Entity -> DAO(Repository) < Entity > DB
repository class: 데이터 io 역할
*/
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    // Optional<Member> 클래스는 NPE(NullPointException)을 방지하는 Wrapper 클래스
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
