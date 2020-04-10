package domain.order;

import domain.menu.Menu;
import domain.order.discountstrategy.DiscountStrategy;
import domain.table.Table;

public class Order {
    private static final int MAX_AMOUNT = 99;

    private final Table table;
    private final Menu menu;
    private final int amount;

    public Order(final int tableNumber, final int menuNumber, final int amount) {
        validateAmount(amount);

        this.table = Table.of(tableNumber);
        this.menu = Menu.of(menuNumber);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("잘못된 수량입니다.");
        }
    }

    public boolean isOrderOf(final Table table) {
        return table.equals(table);
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
}
