package kitchenpos.table.dto;

import kitchenpos.domain.OrderTable;

public class OrderTableResponse {
    private final Long id;
    private final int numberOfGuests;
    private final boolean empty;

    public OrderTableResponse(Long id, int numberOfGuests, boolean empty) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public Long getId() {
        return id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public static OrderTableResponse of(OrderTable orderTable) {
        return new OrderTableResponse(orderTable.getId(),orderTable.getNumberOfGuests(),orderTable.isEmpty());
    }
}