package domain.table;

import java.util.Objects;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    public static Table of(final int number) {
        return TableRepository.tables()
                .stream()
                .filter(table -> table.isTableOf(number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 테이블 번호입니다."));
    }

    private boolean isTableOf(final int number) {
        return this.number == number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return number == table.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
