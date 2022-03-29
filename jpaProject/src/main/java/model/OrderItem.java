package model;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long orderItemId;

    @ManyToOne()
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    @Column(name = "ORDERPRICE")
    private String orderPrice;

    @Column(name = "COUNT")
    private String count;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Orders orders, String orderPrice, String count) {
        this.orderItemId = orderItemId;
        this.orders = orders;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Orders getOrders() {
        return orders;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getCount() {
        return count;
    }
}
