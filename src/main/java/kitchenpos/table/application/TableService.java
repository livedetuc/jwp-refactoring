package kitchenpos.table.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.OrderTableRepository;
import kitchenpos.table.domain.TableChangeEmptyValidator;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;

@Service
public class TableService {
    private final OrderTableRepository orderTableRepository;
    private final TableChangeEmptyValidator tableChangeEmptyValidator;

    public TableService(
        final OrderTableRepository orderTableRepository,
        final TableChangeEmptyValidator tableChangeEmptyValidator) {
        this.orderTableRepository = orderTableRepository;
        this.tableChangeEmptyValidator = tableChangeEmptyValidator;
    }

    @Transactional
    public OrderTableResponse create(final OrderTableRequest orderTableRequest) {
        OrderTable persistOrderTable = orderTableRepository.save(orderTableRequest.toEntity());
        return OrderTableResponse.of(persistOrderTable);
    }

    @Transactional(readOnly = true)
    public List<OrderTableResponse> findAll() {
        return OrderTableResponse.listOf(orderTableRepository.findAll());
    }

    @Transactional
    public OrderTableResponse changeEmpty(final Long orderTableId, final OrderTableRequest orderTableRequest) {
        final OrderTable savedOrderTable = orderTableRepository.findById(orderTableId)
            .orElseThrow(() -> new IllegalArgumentException("등록이 되지 않은 주문테이블은 상태를 변경할 수 없습니다."));
        savedOrderTable.changeEmpty(orderTableRequest.isEmpty(), tableChangeEmptyValidator);
        return OrderTableResponse.of(savedOrderTable);
    }

    @Transactional
    public OrderTableResponse changeNumberOfGuests(final Long orderTableId, final OrderTableRequest orderTableRequest) {
        final OrderTable savedOrderTable = orderTableRepository.findById(orderTableId)
            .orElseThrow(() -> new IllegalArgumentException("등록이 안된 주문테이블은 방문 손님 수를 수정할 수 없습니다."));
        savedOrderTable.changeNumberOfGuests(orderTableRequest.getNumberOfGuests());
        return OrderTableResponse.of(savedOrderTable);
    }
}