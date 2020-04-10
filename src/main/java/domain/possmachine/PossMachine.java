package domain.possmachine;

import domain.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PossMachine {
    private List<Order> orders = new ArrayList<>();

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
}
