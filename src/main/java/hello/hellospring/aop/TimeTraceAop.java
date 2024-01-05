package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// @Aspect : AOP의 기본 모듈, Advice + Pointcut
//  Advice : 타겟(Target, 부가 기능을 부여할 대상이 됨)에 제공할 부가기능을 담음
//  Pointcut : 어드바이스를 적용할 타겟의 메서드를 선별하는 정규표현식, execution으로 시작
// @Component 사용 빈으로 등록, 컴포넌트 하위 어노테이션으로 서비스, 리포지토리 등이 있음
@Aspect
@Component
public class TimeTraceAop {

    // @Around : AOP가 적용될 디렉터리 설정
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            // joinPoint.proceed() 전이 AOP가 적용된 메서드 호출 전 동작
            // proceed() 실행 후가 AOP가 적용된 메서드 호출 후 동작
            // 파이썬 데코레이터의 개념
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END : " + joinPoint.toString() + " " +timeMs + "ms");
        }

    }
}