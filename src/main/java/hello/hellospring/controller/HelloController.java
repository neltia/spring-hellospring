package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Controller
public class HelloController {

    // html 템플릿에 정적 데이터 전달
    @GetMapping("hello")
    public String Hello(Model model) {
        model.addAttribute("name", "hello!!");
        return "hello-template";
    }

    // html 템플릿에 GET 파라미터 전달
    @GetMapping("hello-get")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody 어노테이션 작성 시 http body 부에 직접 데이터를 추가함, return 값 그대로 반환
    // @Controller가 아닌 @RestController로 사용하면 하위 함수에 모두 @ResponseBody가 붙은 것과 같은 취급
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // json 데이터 반환: 데이터 객체 반환 시 기본 json 변환, 기본 getter&setter 사용
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName2(name);
        return hello;
    }

    // Lombok 활용
    @GetMapping("bye-api")
    @ResponseBody
    public Bye byeApi(@RequestParam("name") String name) {
        Bye bye = new Bye();
        bye.setName(name);
        return bye;
    }

    // Hello 객체: name 값 반환.
    // 객체 데이터 {"name": name} 변환 시 키 값은 setter/getter 기준에 따름
    static class Hello {
        private String name;

        public String getName2() {
            return name;
        }

        public void setName2(String name) {
            this.name = name;
        }
    }

    // Lombok 어노테이션
    @Data
    static class Bye {
        private String name;
    }
}
