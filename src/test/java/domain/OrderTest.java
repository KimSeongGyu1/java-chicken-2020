package domain;

import domain.menu.Menu;
import domain.order.Order;
import domain.order.discountstrategy.CacheDiscountStrategy;
import domain.order.discountstrategy.CardDiscountStrategy;
import domain.table.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @DisplayName("주문 테이블 확인")
    @Test
    void orderTableTest() {
        Order order = new Order(1, 1, 2);
        assertThat(order.isOrderOf(Table.of(1))).isTrue();
    }

    @DisplayName("주문이 치킨인지 확인")
    @ParameterizedTest(name = "{0}")
    @MethodSource("isChickenParams")
    void orderMenuTest(String message, int menuNumber, boolean isChicken) {
        Order order = new Order(1, menuNumber, 2);
        assertThat(order.isChickenOrder()).isEqualTo(isChicken);
    }

    static Stream<Arguments> isChickenParams() {
        return Stream.of(
                Arguments.of("치킨인 경우", 1, true),
                Arguments.of("치킨이 아닌 경우", 21, false)
        );
    }

    @DisplayName("주문 기본 가격 확인")
    @Test
    void orderPriceTest() {
        Order order = new Order(1, 1, 2);
        assertThat(order.calculatePrice(new CardDiscountStrategy())).isEqualTo(32000);
    }
}