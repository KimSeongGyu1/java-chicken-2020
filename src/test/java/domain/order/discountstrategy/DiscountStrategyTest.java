package domain.order.discountstrategy;

import domain.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("할인 테스트")
class DiscountStrategyTest {

    @DisplayName("치킨 10마리 단위 할인 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("chickenDiscountParams")
    void calculateDiscountAmount(String message, int menuNumber, int amount, double expected) {
        DiscountStrategy discountStrategy = new CacheDiscountStrategy();
        Order order = new Order(1, menuNumber, amount);
        assertThat(discountStrategy.calculateDiscountAmount(order)).isEqualTo(expected);
    }

    static Stream<Arguments> chickenDiscountParams() {
        return Stream.of(
                Arguments.of("치킨아닌 메뉴 10개", 21, 10, 0),
                Arguments.of("치킨 메뉴 10마리", 1, 10, 10000),
                Arguments.of("치킨 메뉴 19마리", 1, 19, 10000),
                Arguments.of("치킨 메뉴 20마리", 1, 20, 20000)
        );
    }

    @DisplayName("현금 할인률 테스트")
    @Test
    void calculateCacheDiscountRatio() {
        assertThat(new CacheDiscountStrategy().calculateDiscountRatio()).isEqualTo(0.95);
    }

    @DisplayName("카드 할인률 테스트")
    @Test
    void calculateCardDiscountRatio() {
        assertThat(new CardDiscountStrategy().calculateDiscountRatio()).isEqualTo(1);
    }
}