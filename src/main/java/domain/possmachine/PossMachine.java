package domain.possmachine;

import domain.order.Order;
import domain.order.discountstrategy.DiscountStrategy;
import domain.table.Table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

    public List<Order> showOrders(Table table) {
        return orders.stream()
                .filter(order -> order.isOrderOf(table))
                .collect(toList());
    }

    public boolean isOrdered(Table table) {
        return orders.stream()
                .anyMatch(order -> order.isOrderOf(table));
    }

    public int pay(Table table, DiscountStrategy discountStrategy) {
        int sum = 0;

        Iterator<Order> iter = orders.iterator();
        while (iter.hasNext()) {
            Order order = iter.next();
            if (order.isOrderOf(table)) {
                sum += order.calculatePrice(discountStrategy);
                iter.remove();
            }
        }

        return sum;
    }
}
