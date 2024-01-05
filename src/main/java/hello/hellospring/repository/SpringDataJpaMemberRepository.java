// 스프링 부트 + JPA 구성에서 스프링 데이터 JPA 구성으로 변경
// 리포지토리에 구현 클래스 없이 인터페이스만으로 스프링 데이터 JPA에서 제공하는 CRUD 기능 사용
// 스프링 데이터 JPA = JPA를 편리하게 사용하는 기술 => JPA 선행 학습 필요
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // select m  from Member m where m.name = ? 형태로 스프링 데이터 JPA가 JPQL 구성
}
