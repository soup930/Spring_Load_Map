package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Orders {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private Date orderDate;
    private String status;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        // 기존 관계 제거
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrders(this);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
