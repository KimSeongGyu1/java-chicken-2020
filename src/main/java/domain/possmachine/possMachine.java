package domain.possmachine;

import domain.order.Order;

import java.util.List;

public class possMachine {
    private List<Order> orders;

    public possMachine() {
    }

    public void takeOrder(Order order) {
        orders.add(order);
    }
}
