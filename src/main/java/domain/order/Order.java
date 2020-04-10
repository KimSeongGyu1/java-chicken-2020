package domain.order;

import domain.menu.Menu;
import domain.order.discountstrategy.DiscountStrategy;
import domain.table.Table;

import java.util.Objects;

public class Order {
    private static final int MAX_AMOUNT = 99;

    private final Table table;
    private final Menu menu;
    private final int amount;

    public Order(final Table table, final Menu menu, final int amount) {
        validateAmount(amount);

        this.table = table;
        this.menu = menu;
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("잘못된 수량입니다.");
        }
    }

    public Order sumUpIfSame(final Order order) {
        if (this.equals(order)) {
            return new Order(table, menu, this.amount + order.amount);
        }
        return this;
    }

    public boolean isOrderOf(final Table table) {
        return this.table.equals(table);
    }

    public boolean isChickenOrder() {
        return menu.isChickenMenu();
    }

    public int roundedAmount(final int roundingValue) {
        return amount / roundingValue;
    }

    public int calculatePrice(final DiscountStrategy discountStrategy) {
        double discountAmount = discountStrategy.calculateDiscountAmount(this);
        double discountRatio = discountStrategy.calculateDiscountRatio();

        return (int) ((calculateDefaultPrice() - discountAmount) * discountRatio);
    }

    private int calculateDefaultPrice() {
        return menu.calculateManyPrice(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(table, order.table) &&
                Objects.equals(menu, order.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, menu);
    }
}
