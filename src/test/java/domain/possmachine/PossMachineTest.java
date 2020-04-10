package domain.possmachine;

import domain.menu.Menu;
import domain.order.Order;
import domain.table.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}