package kitchenpos.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kitchenpos.domain.table.OrderTable;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_table_id")
    private OrderTable orderTable;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    @Column(name = "ordered_time", updatable = false)
    private LocalDateTime orderedTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLineItem> orderLineItems;

    protected Orders() {
    }

    private Orders(OrderTable orderTable, OrderStatus orderStatus, List<OrderLineItem> orderLineItems) {
        this.orderTable = orderTable;
        this.orderStatus = orderStatus;
        if (orderLineItems == null) {
            this.orderLineItems = new ArrayList<>();
            return;
        }
        
        this.orderLineItems = orderLineItems;
    }

    public static Orders of(OrderTable orderTable, OrderStatus orderStatus) {
        return new Orders(orderTable, orderStatus, null);
    }

    public static Orders of(OrderTable orderTable, List<OrderLineItem> orderLineItems) {
        return new Orders(orderTable, null,  orderLineItems);
    }

    public Long getId() {
        return this.id;
    }

    public OrderTable getOrderTable() {
        return this.orderTable;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return this.orderedTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return this.orderLineItems;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void changeOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
