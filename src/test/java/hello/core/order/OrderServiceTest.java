package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.AppConfigure;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfigure appConfigure = new AppConfigure();
        memberService = appConfigure.memberService();
        orderService = appConfigure.orderService();
    }

    @Test
    void createOrder() {
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "kyle", Grade.VIP);
        memberService.join(member);

        //when
        Order order = orderService.createOrder(memberId, "cube", 12000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
