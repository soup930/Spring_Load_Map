package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "DELEVERY_ID")
    private Long deleveryId;

    private String city;

    private String street;

    private String zipCode;

    private String status;

    public Delivery() {
    }

    public Delivery(Long deleveryId, String city, String street, String zipCode, String status) {
        this.deleveryId = deleveryId;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.status = status;
    }

    public Long getDeleveryId() {
        return deleveryId;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStatus() {
        return status;
    }
}
