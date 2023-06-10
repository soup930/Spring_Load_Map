package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 부모
@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<Child> child = new ArrayList<Child>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }

}
