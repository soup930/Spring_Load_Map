package model;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private String orderDate;

    private String status;

    public Orders() {
    }

    public Orders(Long orderId, Member member, Delivery delivery, String orderDate, String status) {
        this.orderId = orderId;
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Member getMember() {
        return member;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

}
