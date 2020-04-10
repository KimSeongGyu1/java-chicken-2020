package domain.table;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    public static Table of (final int number) {
        return TableRepository.tables()
                .stream()
                .filter(table -> table.isTableOf(number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 테이블 번호입니다."));
    }

    public boolean isTableOf(final int number) {
        return this.number == number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
