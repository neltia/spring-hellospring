package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA 라이브러리를 받으면 스프링부트는 자동으로 EntityManager 객체 생성
    // JPA는 EntityManager 객체로 모든 동작을 수행
    // findAll(), findByName()에서는 여러 리스트를 조회할 때 pk가 아닌 값으로 검색, 조회하여 객체지향쿼리(JPQL)을 사용함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}