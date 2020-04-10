package domain.possmachine;

import domain.order.Order;
import domain.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PossMachine {
    public List<Order> orders = new ArrayList<>();

    public PossMachine() {
    }

    public void takeOrder(Order newOrder) {
        Optional<Order> duplicatedOrder = findDuplicatedOrder(newOrder);

        if (duplicatedOrder.isPresent()) {
            Order order = duplicatedOrder.get();
            orders.add(order.sumUpIfSame(newOrder));
            return;
        }

        orders.add(newOrder);
    }

    private Optional<Order> findDuplicatedOrder(Order newOrder) {
        return orders.stream()
                .filter(order -> order.equals(newOrder))
                .findAny();
    }

    public boolean isOrdered(Table table) {
        return orders.stream()
                .anyMatch(order -> order.isOrderOf(table));
    }
}
