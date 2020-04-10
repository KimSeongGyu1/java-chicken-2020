import domain.CardCache;
import domain.Command;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.order.Order;
import domain.order.discountstrategy.CacheDiscountStrategy;
import domain.order.discountstrategy.CardDiscountStrategy;
import domain.possmachine.PossMachine;
import domain.table.Table;
import domain.table.TableRepository;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        PossMachine possMachine = new PossMachine();

        tryTurnOn(possMachine);
    }

    private static void tryTurnOn(PossMachine possMachine) {
        try {
            turnOn(possMachine);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            tryTurnOn(possMachine);
        }
    }

    private static void turnOn(PossMachine possMachine) {
        Command command = Command.ORDER;
        while (command != Command.TERMINATE) {
            OutputView.printMainMenu();
            command = Command.of(InputView.inputCommandNumber());

            if (command == Command.ORDER) {
                order(possMachine);
            }

            if (command == Command.PAY) {
                OutputView.printPrice(pay(possMachine));
            }

        }
    }

    private static void order(PossMachine possMachine) {
        Table table = selectTable(possMachine);

        OutputView.printMenus(MenuRepository.menus());
        Menu menu = selectMenu();

        Order order = new Order(table, menu, InputView.inputAmount());
        possMachine.takeOrder(order);
    }

    private static int pay(PossMachine possMachine) {
        Table table = selectTable(possMachine);

        OutputView.printOrders(possMachine.showOrders(table));

        CardCache cardCache = CardCache.of(InputView.inputCardOrCache(table));
        if (cardCache == CardCache.CARD) {
            return possMachine.pay(table, new CardDiscountStrategy());
        }
        return possMachine.pay(table, new CacheDiscountStrategy());
    }

    private static Table selectTable(PossMachine possMachine) {
        OutputView.printTables(TableRepository.tables(), possMachine);
        return Table.of(InputView.inputTableNumber());
    }

    private static Menu selectMenu() {
        return Menu.of(InputView.inputMenuNumber());
    }
}
