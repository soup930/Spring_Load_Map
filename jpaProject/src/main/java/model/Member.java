package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberId;

    private String name;

    private String city;

    private String street;

    private String zipCode;

    public Member() {
    }

    public Member(Long memberId, String name, String city, String street, String zipCode) {
        this.memberId = memberId;
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
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


}
