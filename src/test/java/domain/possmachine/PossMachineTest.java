package domain.possmachine;

import domain.menu.Menu;
import domain.order.Order;
import domain.order.discountstrategy.CacheDiscountStrategy;
import domain.table.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PossMachineTest {

    @DisplayName("기존 주문 합치기 테스트")
    @Test
    void takeOrder() {
        PossMachine possMachine = new PossMachine();
        Order order = new Order(Table.of(1), Menu.of(1), 50);
        possMachine.takeOrder(order);

        assertThatThrownBy(() -> possMachine.takeOrder(order)).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> tableParams() {
        return Stream.of(
                Arguments.of("주문 된 테이블 확인", 1, true),
                Arguments.of("주문 안 된 테이블 확인", 2, false)
        );
    }

    static Stream<Arguments> cachePriceParams() {
        return Stream.of(
                Arguments.of("치킨만 10마리 이하", 5, 76000),
                Arguments.of("치킨 10마리 이상", 10, 142500)
        );
    }

    @DisplayName("주문된 테이블 확인 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("tableParams")
    void isOrdered(String message, int tableNumber, boolean expected) {
        PossMachine possMachine = new PossMachine();
        possMachine.takeOrder(new Order(Table.of(1), Menu.of(1), 1));

        assertThat(possMachine.isOrdered(Table.of(tableNumber))).isEqualTo(expected);
    }

    @DisplayName("현금 주문 가격 확인")
    @ParameterizedTest(name = "{0}")
    @MethodSource("cachePriceParams")
    void orderCachePriceTest(String message, int amount, int expected) {
        Order order = new Order(Table.of(1), Menu.of(1), amount);
        assertThat(order.calculatePrice(new CacheDiscountStrategy())).isEqualTo(expected);
    }
}