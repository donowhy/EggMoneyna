package shinhan.EggMoneyna.user.follow.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.parent.entity.Parent;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Relation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Child child;

    @Builder
    public Relation(Long id, Parent parent, Child child) {
        this.id = id;
        this.parent = parent;
        this.child = child;
    }
}
