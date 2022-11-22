package hello.core.singletone;

import hello.core.AppConfigure;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class SingletoneTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfigure appConfigure = new AppConfigure();
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfigure.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfigure.memberService();

        //참조 값이 다른 것을 확인
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletoneServiceTest() {
        SingletoneService singletoneService1 = SingletoneService.getInstance();
        SingletoneService singletoneService2 = SingletoneService.getInstance();

        //같은 instance 임을 확인
        assertThat(singletoneService1).isSameAs(singletoneService2);
        //same == 참조를 비교
        //equal equals
    }

    @Test
    @DisplayName("싱글톤 컨테이너")
    void singletoneContainer() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigure.class);

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조 값이 다른 것을 확인
        assertThat(memberService1).isSameAs(memberService2);
    }
}