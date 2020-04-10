package domain.order;

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

@DisplayName("주문 테스트")
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

    @DisplayName("카드 주문 가격 확인")
    @ParameterizedTest(name = "{0}")
    @MethodSource("cardPriceParams")
    void orderCardPriceTest(String message, int amount, int expected) {
        Order order = new Order(1, 1, amount);
        assertThat(order.calculatePrice(new CardDiscountStrategy())).isEqualTo(expected);
    }

    static Stream<Arguments> cardPriceParams() {
        return Stream.of(
                Arguments.of("치킨만 10마리 이하", 5, 80000),
                Arguments.of("치킨 10마리 이상", 10, 150000)
        );
    }

    @DisplayName("현금 주문 가격 확인")
    @ParameterizedTest(name = "{0}")
    @MethodSource("cachePriceParams")
    void orderCachePriceTest(String message, int amount, int expected) {
        Order order = new Order(1, 1, amount);
        assertThat(order.calculatePrice(new CacheDiscountStrategy())).isEqualTo(expected);
    }

    static Stream<Arguments> cachePriceParams() {
        return Stream.of(
                Arguments.of("치킨만 10마리 이하", 5, 76000),
                Arguments.of("치킨 10마리 이상", 10, 142500)
        );
    }
}