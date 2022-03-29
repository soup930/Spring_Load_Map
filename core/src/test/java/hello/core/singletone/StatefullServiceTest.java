package hello.core.singletone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefullServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefullService statefullService1 = ac.getBean(StatefullService.class);
        StatefullService statefullService2 = ac.getBean(StatefullService.class);

        // ThreadA : A 사용자 10000원 주문
        int userA = statefullService1.order("userA", 10000);
        // ThreadB : B 사용자 20000원 주문
        int userB = statefullService2.order("userB", 20000);

        // ThreadA : 사용자 A 주문 금액 조회
        // int price = statefullService1.getPrice();
        System.out.println("price = " + userA);

        Assertions.assertThat(userB).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefullService statefullService() {
            return new StatefullService();
        }
    }

}