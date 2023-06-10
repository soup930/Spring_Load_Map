package jpabook.jpashop.domain;

import jpabook.jpashop.domain.base.BaseEntity;
import jpabook.jpashop.domain.enu.DeliveryStatus;
import jpabook.jpashop.domain.valuetype.Address;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;
    private DeliveryStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
