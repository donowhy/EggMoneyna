package shinhan.EggMoneyna.user.follow.entity;


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
    @GeneratedValue
    @Column(name = "relation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Child child;

    @Builder
    public Relation(Long id, Parent parent, Child child) {
        this.id = id;
        this.parent = parent;
        this.child = child;
    }
}
