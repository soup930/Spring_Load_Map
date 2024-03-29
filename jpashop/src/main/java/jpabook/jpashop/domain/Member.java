package jpabook.jpashop.domain;



import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
