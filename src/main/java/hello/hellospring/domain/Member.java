package hello.hellospring.domain;

public class Member {

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
