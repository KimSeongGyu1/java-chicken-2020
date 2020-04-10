package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("주문 테이블 확인")
    @Test
    void orderTableTest() {
        Order order = new Order(1, 1, 2);
        assertThat(order.isOrderOf(Table.of(1))).isTrue();
    }

    @DisplayName("주문 메뉴 확인")
    @Test
    void orderMenuTest() {
        Order order = new Order(1, 1, 2);
        assertThat(order.isOrderOf(Menu.of(1))).isTrue();
    }

    @DisplayName("주문 기본 가격 확인")
    @Test
    void orderPriceTest() {
        Order order = new Order(1, 1, 2);
        assertThat(order.calculateNonDisCountPrice()).isEqualTo(32000);
    }
}