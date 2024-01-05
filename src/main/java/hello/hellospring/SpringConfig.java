// 차후 구현 클래스를 변경해야 하는 정형화되어 있지 않은 상황에서는 자바 코드로 직접 스프링 빈을 등록하는 것이 유리
// 현 코드의 경우 DB 적용 전이므로 차후 MemoryMemberRepository를 변경해야 함
// 회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 어노테이션을 제거하고 다음 코드 작성
package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}