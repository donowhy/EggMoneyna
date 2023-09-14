package shinhan.EggMoneyna.monster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.users.entity.Users;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MonsterEncyclopedia {

    @Id
    @GeneratedValue
    @Column(name = "monsterEncyclopedia_id")
    private Long id;
    private Boolean SOL;
    private Boolean MOLI;
    private Boolean RINO;
    private Boolean SHOO;
    private Boolean DOREMI;
    private Boolean LULULALA;
    private Boolean PLI;
    private Boolean LAY;

    @OneToOne
    @JoinColumn(name = "childs_id")
    @JsonIgnore
    private Child child;


    @Builder
    public MonsterEncyclopedia(Long id, Boolean SOL, Boolean MOLI, Boolean RINO, Boolean SHOO, Boolean DOREMI, Boolean LULULALA, Boolean PLI, Boolean LAY, Child child) {
        this.id = id;
        this.SOL = SOL;
        this.MOLI = MOLI;
        this.RINO = RINO;
        this.SHOO = SHOO;
        this.DOREMI = DOREMI;
        this.LULULALA = LULULALA;
        this.PLI = PLI;
        this.LAY = LAY;
        this.child = child;
    }

    public void setSol(Boolean SOL) {
        this.SOL = SOL;
    }

    public void setMoli(boolean MOLI) {
        this.MOLI = MOLI;
    }

    public void setRino(boolean RINO) {
        this.RINO = RINO;
    }

    public void setShoo(boolean SHOO) {
        this.SHOO = SHOO;
    }

    public void setDoremi(boolean DOREMI) {
        this.DOREMI = DOREMI;
    }
    public void setLululala(boolean LULULALA) {
        this.LULULALA = LULULALA;
    }
    public void setPli(boolean PLI) {
        this.PLI = PLI;
    }
    public void setLay(boolean LAY) {
        this.LAY = LAY;
    }


}
