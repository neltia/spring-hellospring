package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {

    // @Id: pk 매핑
    // 값이 자동 생성되는 컬럼 명시, 기본 키 생성을 DB에 위임하고 MySQL에서 많이 사용되는 IDENTITY 사용
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // getter/setter 기본 설정
    public Long getId() {
        return id;
    }

    // @Setter 어노테이션을 사용하면 축약 가능
    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
