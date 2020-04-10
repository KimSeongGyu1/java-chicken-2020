package domain;

public class Order {
    private final int tableNumber;
    private final int menuNumber;
    private final int amount;

    public Order(int tableNumber, int menuNumber, int amount) {
        this.tableNumber = tableNumber;
        this.menuNumber = menuNumber;
        this.amount = amount;
    }

    public boolean isOrderOf(Table table) {
        return table.isTableOf(tableNumber);
    }

    public boolean isOrderOf(Menu menu) {
        return menu.isMenuOf(menuNumber);
    }

    public int calculateNonDisCountPrice() {
        return Menu.of(menuNumber).calculatePrice(amount);
    }
}
