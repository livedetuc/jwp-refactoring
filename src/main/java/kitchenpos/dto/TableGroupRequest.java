package kitchenpos.dto;

import java.util.List;
import kitchenpos.domain.TableGroup;

public class TableGroupRequest {

    private List<Long> orderTableIds;

    public TableGroupRequest(List<Long> orderTableIds) {
        this.orderTableIds = orderTableIds;
    }

    public List<Long> getOrderTableIds() {
        return orderTableIds;
    }

    public TableGroup toEntity() {
        return new TableGroup();
    }
}
